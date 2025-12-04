package pccth.sp.pccthspseedservice.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pccth.sp.pccthspseedservice.model.TahnModel;
import pccth.sp.pccthspseedservice.service.TahnService;
import springfox.documentation.spring.web.plugins.Docket;

@RestController
@RequestMapping("Tahn-controller")
public class TahnController {

    private final Docket api;
	
	@Autowired
	private TahnService tahnService;

    TahnController(Docket api) {
        this.api = api;
    }
	
	//Get
	//RequestParam แยกกันเป็น ตัวๆ เข่น firstName
	@GetMapping("/get-data")
	public List<TahnModel> getData() {
		return tahnService.getData();
	}
	
	@GetMapping("/get-pdf")
	public Object getPdf() {
		return tahnService.getPdf();
	}
	
	
	//ส่ง firstName ไปก็จริงแต่ใน service ก็ยังบอกว่ารับ name อยู่ดี
	@GetMapping("/get-data-find-user")
	public List<TahnModel> getDataUser(
			@RequestParam(required = false) String nameFull,
			@RequestParam(required = false) String userPass,
			@RequestParam(required = false) String firstname,
			@RequestParam(required = false) String lastname,
			@RequestParam(required = false) Integer age,
			@RequestParam(required = false) String birthday,
			@RequestParam(required = false) String gender,
			@RequestParam(required = false) String createDate,
			@RequestParam(required = false) String createBy
			) {	
		return tahnService.getDataUser(nameFull,userPass,firstname,lastname,
				age,birthday,gender,createDate,createBy);
	}
		
	//POST
	//RequestBody เอามาทั้งหมด เช่น tahnModel
	@PostMapping("save-data")
	public TahnModel saveData(@RequestBody TahnModel tahnModel) {
		tahnService.saveData(tahnModel);
		 return tahnModel; // ส่งกลับ object ที่ insert แล้ว แก้ปัญหา เพิ่มข้อมูลแล้วไม่ refresh
	}
	
	@PutMapping("/users/{id}")
	public void updateData(@PathVariable int id, @RequestBody TahnModel model) {
		tahnService.updateData(id, model);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteData(@PathVariable int id) {
		tahnService.deleteData(id);
	}
}
