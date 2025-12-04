package pccth.sp.pccthspseedservice.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;



import pccth.sp.pccthspseedservice.entity.VatEntity;
import pccth.sp.pccthspseedservice.model.VatModel;

@Repository
public class VatJDBCRepository {

	@Autowired
	private static JdbcTemplate jdbcTemplate;
	
	public Object getVat(float amount) {
		if (amount < 2000.00) {
			return new VatModel();
		} else if (amount > 199999.99) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT MAX(VRTRATE) AS vrtrate, MAX(VRTRATEAG) AS vrtrateag, MAX(VRTRATE)-MAX(VRTRATEAG) AS charge FROM rdbvrtrate " );
			VatModel vatModel = jdbcTemplate.queryForObject(sql.toString(), new BeanPropertyRowMapper<>(VatModel.class));
			return vatModel;
		} else {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT VRTRATE AS vrtrate, VRTRATEAG AS vrtrateag, VRTRATE-VRTRATEAG AS charge FROM rdbvrtrate WHERE ? BETWEEN rdbvrtrate.SALEFROM AND rdbvrtrate.SALETO " );
			List<VatModel> vatModel = jdbcTemplate.query(sql.toString(), new Object[] {amount}, new BeanPropertyRowMapper<>(VatModel.class));
			return vatModel;
		}
	}
}
