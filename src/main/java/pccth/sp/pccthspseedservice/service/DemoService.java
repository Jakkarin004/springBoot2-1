package pccth.sp.pccthspseedservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pccth.sp.pccthspseedservice.entity.DemouserEntity;
import pccth.sp.pccthspseedservice.model.UsersModel;
import pccth.sp.pccthspseedservice.repository.DemoJdbcRepository;
import pccth.sp.pccthspseedservice.repository.DemoRepository;

@Service
public class DemoService {
	@Autowired
	private DemoRepository demoRepository;
	
	@Autowired
	private DemoJdbcRepository demoJdbcRepository;

	public List<DemouserEntity> getDataJPA() {
		List<DemouserEntity> data = demoRepository.findAll();	
		return data;
	}
	
	public List<UsersModel> getDataJDBC() {
		List<UsersModel> data = demoJdbcRepository.findAllJdbc();
		return data;
	}

}
