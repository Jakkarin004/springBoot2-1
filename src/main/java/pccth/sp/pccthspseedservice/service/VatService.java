package pccth.sp.pccthspseedservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pccth.sp.pccthspseedservice.repository.VatJDBCRepository;

@Service
public class VatService {
	
	private static final Logger logger = LoggerFactory.getLogger(VatService.class);
	
	@Autowired
	private VatJDBCRepository vatJDBCRepository;

    public Object getVat(float purchaseAmount ) {
    	try {
    		logger.info("purchase amount: {}", purchaseAmount);
    		return vatJDBCRepository.getVat(purchaseAmount);
    	} catch (Exception e) {
    		logger.error(e.getMessage());
    	}
		return null;
    }

}
