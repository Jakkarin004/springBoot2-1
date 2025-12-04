package pccth.sp.pccthspseedservice.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pccth.sp.pccthspseedservice.entity.DemouserEntity;
import pccth.sp.pccthspseedservice.model.UsersModel;

@Repository
public class DemoJdbcRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<UsersModel> findAllJdbc() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT user_id as idUsers, name as firstName, lastname as lastName FROM seeddb.demouser");
		
		List<UsersModel> data = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(UsersModel.class));
		return data;
	}

}
