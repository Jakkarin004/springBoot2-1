package pccth.sp.pccthspseedservice.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import pccth.sp.pccthspseedservice.entity.TahnEntity;
import pccth.sp.pccthspseedservice.model.TahnModel;
import pccth.sp.pccthspseedservice.repository.TahnJDBCRepository;
import pccth.sp.pccthspseedservice.repository.TahnJPARepository;
import pccth.sp.pccthspseedservice.utils.DateUtil;
import pccth.sp.pccthspseedservice.utils.ThaiExportManager;

@Service
public class TahnService {
	

		@Autowired
		private TahnJPARepository tahnJPARepository;
		
		@Autowired
		private TahnJDBCRepository tahnJDBCRepository;
		
		
		public List<TahnModel> getData() {
			List<TahnModel> tahnModelList =  tahnJDBCRepository.findAll();
			return tahnModelList;
		}
		
		public Object getPdf() {
			
		try {
			//1 ดึงข้อมูล
			List<TahnModel> field =  tahnJDBCRepository.findAll();
			//2 map parameter
			Map<String,Object> params = new HashMap<>();
			params.put("name","จักริน");
			params.put("typeDate",new Date());
			params.put("typeString","2547-02-02");
			params.put("image","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");
			
			
			//3. สร้างรายงาน
			// read file from path resource
			InputStream reportInput = this.getClass().getClassLoader().getResourceAsStream("report/cheeta.jrxml");
			// Compile .jrxml ให้กลายเป็น JasperReport
			JasperReport jasperReport = JasperCompileManager.compileReport(reportInput);
			
			// สร้างรายงาน
			// 1. jasper template
			// 2. parameter
			// 3. datasource
			JasperPrint jasperPrint2 = JasperFillManager.fillReport(jasperReport, params, new JRBeanCollectionDataSource(field));

			// ThaiExportManager ใช้เพื่อ export PDF ที่รองรับภาษาไทย
			// โดยทั่วไปใช้อันนี้
			//JasperExportManager.exportReportToPdf(jasperPrint);
			byte[] s = JasperExportManager.exportReportToPdf(jasperPrint2);
//			byte[] bytes = ThaiExportManager.exportPdf(jasperPrint2);
			// แปลง byte[] เป็น string Base64 เพื่อส่งข้าม API
			String base64 = Base64.getEncoder().encodeToString(s);
//			System.out.println("finish");
			return base64; // หน้าจอรับตัวนี้อ่านค่าแล้ว show
//			return null;
			}catch(Exception e) {
			return e.getMessage();
		}
			
		}
		
		public List<TahnModel> getDataUser(String nameFull,
				String userPass,String firstname,String lastname,Integer age,
				String birthday,String gender, String createDate,String createBy) 
		{
			List<TahnModel> tahnModelList =  tahnJDBCRepository.findUser(nameFull,userPass,firstname,lastname,
		            age,birthday,gender,createDate,createBy);
			return tahnModelList;
		}
		
		public void saveData(TahnModel tahnModel) {	
			
			//ดึง string ที่รับมาจาก frontend เช่น 01/12/2550
		    String buddhistDate = tahnModel.getBirthday();

		    //string → YYYYMMDD ค.ศ.
		    String christianDate = DateUtil.convertDDMMYYYYBuddhistYearToYYYYMMDDChristianYear(buddhistDate);

		    //แปลง YYYYMMDD → java.util.Date
		    Date utilDate = DateUtil.convertYYYYMMDDChristianYearToDate(christianDate);

		    //แปลง util.Date → java.sql.Date
		    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		    
		    tahnModel.setBirthdayDate(sqlDate);
			tahnJDBCRepository.saveData(tahnModel);
		}
		
		public void updateData(int id , TahnModel tahnModel) {
			
			//ดึง string ที่รับมาจาก frontend เช่น 01/12/2550
		    String buddhistDate = tahnModel.getBirthday();

		    //string → YYYYMMDD ค.ศ.
		    String christianDate = DateUtil.convertDDMMYYYYBuddhistYearToYYYYMMDDChristianYear(buddhistDate);

		    //แปลง YYYYMMDD → java.util.Date
		    Date utilDate = DateUtil.convertYYYYMMDDChristianYearToDate(christianDate);

		    //แปลง util.Date → java.sql.Date
		    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		    
		    tahnModel.setBirthdayDate(sqlDate);
			tahnJDBCRepository.updateData(id,tahnModel);
		}
		
		public void deleteData(int id) {
			
			tahnJDBCRepository.deleteData(id);
		}
	
}
