package pccth.sp.pccthspseedservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pccth.sp.pccthspseedservice.entity.RdbvrtrateEntity;
import pccth.sp.pccthspseedservice.response.RdbVrtRateWoonResponse;
import pccth.sp.pccthspseedservice.service.RdbvrtrateService;

@RestController
@RequestMapping("/rdbVrtRate")
public class RdbVrtRateController {
	
	@Autowired
	private RdbvrtrateService rdbvrtrateService;
	
	@GetMapping("/cal-vat")
	public RdbVrtRateWoonResponse calVat(int amount) {
		return rdbvrtrateService.calVat(amount);
	
	}
	
//	@GetMapping("/cal-vatAll")
//	public RdbvrtrateEntity calVatAll(int amount) {
//		return rdbvrtrateService.calVatAll(amount);
//	
//	}
	

}
