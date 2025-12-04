package pccth.sp.pccthspseedservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pccth.sp.pccthspseedservice.service.DemoService;

@RestController
@RequestMapping("/demo")
public class DemoController {
	
	@Autowired
	private DemoService demoService;
	
	@GetMapping("/get-demo")
	public Object getDemo() {
		System.out.println("/demo/get-demo");
		return "Result of GET /demo/get-demo is Success";
	}
	
	@PostMapping("/post-demo")
	public int printInteger() {
		System.out.println("/demo/post-demo");
		System.out.println("Result of POST /demo/post-demo is Success");
		return 0;
	}
	
	@GetMapping("/get-jpa")
	public Object getJpa() {
		return demoService.getDataJPA();
	}
	
	@GetMapping("/get-jdbc")
	public Object getJdbc() {
		return demoService.getDataJDBC();
	}
}
