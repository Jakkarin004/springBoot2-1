package pccth.sp.pccthspseedservice.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pccth.sp.pccthspseedservice.entity.RdbvrtrateEntity;
import pccth.sp.pccthspseedservice.repository.RdbvrtrateJpaRepository;
import pccth.sp.pccthspseedservice.response.RdbVrtRateWoonResponse;

@Service
public class RdbvrtrateService {

	@Autowired
	private RdbvrtrateJpaRepository rdbvrtrateJpaRepository;
	
	public RdbVrtRateWoonResponse calVat(int amount) {
		RdbVrtRateWoonResponse response = new RdbVrtRateWoonResponse();
		RdbvrtrateEntity data = rdbvrtrateJpaRepository.getDataByAmount(amount);
	    float vatRate = 7.0f / 100.0f;
	    float vatAmount = amount * vatRate;
	    
//		response.setVatAmount(vatAmount);
//		response.setSaleReturn(data.getVrtRateAg());
//		BigDecimal fee = data.getVrtRate().subtract(data.getVrtRateAg());
//		response.setVatFee(fee);
//		response.setVatReturn(data.getVrtRate());
//		
		return response;
	}
	
	public RdbvrtrateEntity calVatAll(int amount) {
		RdbvrtrateEntity data = rdbvrtrateJpaRepository.getDataByAmount(amount);
		return data;
	}
}
