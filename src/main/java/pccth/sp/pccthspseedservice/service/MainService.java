package pccth.sp.pccthspseedservice.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pccth.sp.pccthspseedservice.model.DetailModel;
import pccth.sp.pccthspseedservice.model.HeaderModel;
import pccth.sp.pccthspseedservice.model.RdbvrtrateModel;
import pccth.sp.pccthspseedservice.repository.MainJDBCRepository;
import pccth.sp.pccthspseedservice.response.DetailResponseDataHeader;

@Service
public class MainService {
	
	@Autowired
	private MainJDBCRepository mainJDBCRepository;
	
	public List<HeaderModel> getVat() {
		List<HeaderModel> headerModelList = mainJDBCRepository.findVate();
		return headerModelList;
	}
	
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
		
		// ถ้าไม่พบช่วงใน rdbvrtrate → ค่าอื่น = 0
		if(betweenRate == null) {
			detailModel.setTaxRd(BigDecimal.ZERO);
			detailModel.setTaxRa(BigDecimal.ZERO);
			detailModel.setRefunfFr(BigDecimal.ZERO);
			return;
		}
		
		// หาค่า VRTRATE เป็นตัวเลขคงที่
		 BigDecimal vatDept = BigDecimal.valueOf(betweenRate.getVrtRATE());
		 	detailModel.setTaxRd(vatDept);
		 	
		// คหาค่า VRTRATEAG เป็นตัวเลขคงที่
		  BigDecimal vatAgent = BigDecimal.valueOf(betweenRate.getVrtRATEAG());
		  detailModel.setTaxRa(vatAgent);

		// ค่าที่ 4: ส่วนต่าง (ค่า 2 - ค่า 3)
		    // -----------------------------------
		  BigDecimal diff = vatDept.subtract(vatAgent);
		  detailModel.setRefunfFr(diff);
	}
	
//	public void saveDataAll(List<DetailModel> detailModel ,String vdtNo) {
//		// สร้าง Header ถ้ายังไม่มี
//	    if (!mainJDBCRepository.checkHeader(vdtNo)) {
//	        HeaderModel header = new HeaderModel();
//	        header.setVdtNo(vdtNo);
//	        header.setCreateBy("ff");
//	        mainJDBCRepository.insertHeader(header);
//	    }
//
//	    // insert detail
//	    for (DetailModel d : detailModel) {
//	        d.setVdtNo(vdtNo); // สำคัญ! ต้อง set vdt_no ก่อน insert
//	        // คำนวณก่อน Insert
//	        applyVatLogic(d);
//	        
//	        mainJDBCRepository.insertDetail(d);
//	    }
//	}
	
	public void saveDataAll(List<DetailModel> detailModel ,String vdtNo) {

	    // ถ้ายังไม่มี header → insert
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
	        mainJDBCRepository.saveOrUpdateDetail(d);
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

}
