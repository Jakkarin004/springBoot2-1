package pccth.sp.pccthspseedservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pccth.sp.pccthspseedservice.service.VatService;

@RestController
@RequestMapping("/vat")
public class VatController {
	
	@Autowired
	private VatService VatService;
	
	@GetMapping("/get-vat")
	public Object getVat(@RequestParam("purchaseAmount") float purchaseAmount) {
		return VatService.getVat(purchaseAmount);
	}

}
