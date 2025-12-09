package pccth.sp.pccthspseedservice.service;

import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import pccth.sp.pccthspseedservice.model.DetailModel;
import pccth.sp.pccthspseedservice.model.HeaderModel;
import pccth.sp.pccthspseedservice.model.RdbvrtrateModel;
import pccth.sp.pccthspseedservice.model.TahnModel;
import pccth.sp.pccthspseedservice.repository.MainJDBCRepository;
import pccth.sp.pccthspseedservice.response.DetailResponseDataHeader;
import pccth.sp.pccthspseedservice.utils.DateUtil;

@Service
public class MainService {
	
	@Autowired
	private MainJDBCRepository mainJDBCRepository;

	public DetailResponseDataHeader findDetailByVdtNo(String vdtNo) {
		List<DetailModel> detailModelList = mainJDBCRepository.findDetailByVdtNo(vdtNo);
		
		DetailResponseDataHeader res = new DetailResponseDataHeader();
		res.setVdtNo(vdtNo);
		res.setDataList(detailModelList);
		
		return res;
	}
	
	public List<HeaderModel> searchHeader(String vdtNo,String vdtDate) {
		return mainJDBCRepository.searchHeader(vdtNo,vdtDate);
	}
	
	private void applyVatLogic(DetailModel detailModel) {
		//ดึง rat ทั้งหมดจาก db
		List<RdbvrtrateModel> rateList = mainJDBCRepository.getAllVatRate();
		
		BigDecimal total = detailModel.getTotalPrice();
		Integer amount = total.intValue();	
		
		//หา record ที่ amount อยูระหว่าง saleFrom - saleTo
		RdbvrtrateModel betweenRate = rateList.stream()
				.filter(r -> amount >= r.getSaleFROM() && amount <= r.getSaleTO())
				.findFirst()
				.orElse(null);
	
		// ค่า vat 7%
		BigDecimal vat7 = total.multiply(new BigDecimal("0.07"));
		// เก็บ vat 7%
		detailModel.setTaxAt(vat7);
		
		// ถ้าไม่พบช่วงใน rdbvrtrate set ค่าอื่น = 0
		if(betweenRate == null) {
			detailModel.setTaxRd(BigDecimal.ZERO);
			detailModel.setTaxRa(BigDecimal.ZERO);
			detailModel.setRefunfFr(BigDecimal.ZERO);
			return;
		}
		
		// หาค่า VRTRATE เป็นตัวเลขคงที่
		 BigDecimal vatDept = BigDecimal.valueOf(betweenRate.getVrtRATE());
		 	detailModel.setTaxRd(vatDept);
		 	
		// หาค่า VRTRATEAG เป็นตัวเลขคงที่
		  BigDecimal vatAgent = BigDecimal.valueOf(betweenRate.getVrtRATEAG());
		  detailModel.setTaxRa(vatAgent);

		//ส่วนต่าง ค่า 2 - ค่า 3
		  BigDecimal diff = vatDept.subtract(vatAgent);
		  detailModel.setRefunfFr(diff);
	}
	
	@Transactional
	public void saveDataAll(List<DetailModel> detailModel ,String vdtNo) {

	    try {
	    	// ถ้ายังไม่มี header insert
		    if (!mainJDBCRepository.checkHeader(vdtNo)) {
		        HeaderModel header = new HeaderModel();
		        header.setVdtNo(vdtNo);
		        header.setCreateBy("ff");
		        mainJDBCRepository.insertHeader(header);
		    }

		    // วนบันทึก detail ทีละตัวแบบ upsert
		    for (DetailModel d : detailModel) {
		        d.setVdtNo(vdtNo);

		        // คำนวณค่าภาษี
		        applyVatLogic(d);

		        // upsert แทนที่จะ insert ตลอด
//		        mainJDBCRepository.saveOrUpdateDetail(d);
		        if (mainJDBCRepository.existsDetail(d.getVdtNo(), d.getId())) {
		            mainJDBCRepository.updateDetail(d);
		        } else {
		            mainJDBCRepository.insertDetail(d);
		        }
		    }
	    } catch (Exception e) {
	    	 // จะ rollback อัตโนมัติ
            throw new RuntimeException("บันทึกข้อมูลล้มเหลวว: " + e.getMessage(), e);
	    }
	}
	
	
	public void updateDetail(DetailModel detail) {
		
		// คำนวณค่าภาษี
        applyVatLogic(detail);
	    mainJDBCRepository.updateDetail(detail);
	}
	
	public void deleteData(int id) {
		mainJDBCRepository.deleteData(id);
	}
	
	public String generatePdf(List<DetailModel> detail) {
	    try {
	        //ดึง vdtNo ของ detail แถวแรก
	        String vdtNo = detail.get(0).getVdtNo();
	        
	        String formattedBuddhistDate = "มีข้อมูลที่ยังไม่ได้ระบุวันที่";

	        // ถ้า vdtNo มีค่า ค่อยไปค้นหา header
	        if (vdtNo != null && !vdtNo.isEmpty()) {

	            List<HeaderModel> header = mainJDBCRepository.searchHeader(vdtNo, null);

	            if (header != null && !header.isEmpty()) {

	                Date vdtDate = header.get(0).getVdtDate();

	                if (vdtDate != null) {
	                    LocalDate localDate = vdtDate.toInstant()
	                            .atZone(ZoneId.systemDefault())
	                            .toLocalDate();

	                    int buddhistYear = localDate.getYear() + 543;

	                    formattedBuddhistDate = String.format("%02d/%02d/%d",
	                            localDate.getDayOfMonth(),
	                            localDate.getMonthValue(),
	                            buddhistYear
	                    );
	                }
	            }
	        }

	        //แปลง ค.ศ. ไป พ.ศ.
	        LocalDate today = LocalDate.now();
	        int buddhistYearToday = today.getYear() + 543;
	        String todayBuddhist = String.format(
	                "%02d/%02d/%d",
	                today.getDayOfMonth(),
	                today.getMonthValue(),
	                buddhistYearToday
	        );

	        //ใส่ค่าลง params
	        Map<String, Object> params = new HashMap<>();
	        params.put("vdtNo", (vdtNo != null && !vdtNo.isEmpty()) ? vdtNo : "มีข้อมูลที่ยังไม่ได้ระบุเลขใบสรุป");
	        params.put("vdtDate", formattedBuddhistDate);
	        params.put("Today", todayBuddhist);

	        //โหลด template
	        InputStream reportInput = this.getClass().getClassLoader().getResourceAsStream("report/jk.jrxml");
	        // Compile .jrxml ให้กลายเป็น JasperReport
	        JasperReport jasperReport = JasperCompileManager.compileReport(reportInput);
	        // สร้างรายงาน
	     	// 1. jasper template
	     	// 2. parameter
	     	// 3. datasource
	        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(detail);
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, ds);

	        byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);
	        // แปลง byte[] เป็น string Base64 เพื่อส่งข้าม API
	        return Base64.getEncoder().encodeToString(pdfBytes);

	    } catch (Exception e) {
	        e.printStackTrace();
	        return e.getMessage();
	    }
	}


	public List<RdbvrtrateModel> getVatTax() {
		List<RdbvrtrateModel> rdbvrtrateModel = mainJDBCRepository.findVatTax();
		return rdbvrtrateModel;
	}

}
