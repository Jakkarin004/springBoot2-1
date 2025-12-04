package pccth.sp.pccthspseedservice.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import net.sf.jasperreports.engine.util.ObjectUtils;
import pccth.sp.pccthspseedservice.model.TahnModel;
import pccth.sp.pccthspseedservice.utils.DateUtil;

@Repository
public class TahnJDBCRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<TahnModel> findAll() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM jakkarin.users");
		
		List<TahnModel> list = jdbcTemplate.query(sql.toString(),
				new BeanPropertyRowMapper(TahnModel.class));
		return list;
	}
	
	public List<TahnModel> findUser(
			String nameFull,String userPsss,String firstname,
			String lastname,Integer age,String birthday,
			String gender,String createDate,String createBy
			)
	{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT "
				+ "id,user_pass as userPass,firstname,"
				+ "lastname,birthday,age,gender,create_date as createDate,"
				+ "create_by as createBy "
				+ "FROM jakkarin.users WHERE 1 = 1");
		
		List<Object> params = new ArrayList<>();
		
		if(nameFull != null && !nameFull.isEmpty()) {
		    String[] keywords = nameFull.split("\\s+"); // แยกตาม space
		    for(String empty : keywords) {
		        sql.append(" AND (firstname LIKE ? OR lastname LIKE ?)");
		        params.add("%" + empty + "%");
		        params.add("%" + empty + "%");
		    }
		}
		
		if(userPsss != null && !userPsss.isEmpty()) {
			sql.append(" AND user_pass LIKE ?");
			params.add("%" + userPsss + "%");
		}
		
		if(firstname != null && !firstname.isEmpty()) {
			sql.append(" AND firstname LIKE ?");
			params.add("%" + firstname + "%");
		}
		
		if(lastname != null && !lastname.isEmpty()) {
			sql.append(" AND lastname LIKE ?");
			params.add("%" + lastname + "%");
		}
		
		if(age != null) {
			sql.append(" AND age LIKE ?");
			params.add("%" + age + "%");
		}
		
		if(birthday != null && !birthday.isEmpty()) {
			sql.append(" AND birthday LIKE ?");
			params.add("%" + birthday + "%");
		}
		
		if(gender != null && !gender.isEmpty()) {
			sql.append(" AND gender LIKE ?");
			params.add("%" + gender + "%");
		}
		
		if(createDate != null) {
			sql.append(" AND create_date LIKE ?");
			params.add("%" + createDate + "%");
		}
		
		if(createBy != null && !createBy.isEmpty()) {
			sql.append(" AND create_by LIKE ?");
			params.add("%" + createBy + "%");
		}
		
		return jdbcTemplate.query(
				sql.toString(),
				new BeanPropertyRowMapper<>(TahnModel.class),
				params.toArray()
				);
	}
	
	public void saveData(TahnModel tahnModel) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO jakkarin.users(user_pass, firstname, lastname, birthday, age, gender, create_date, create_by) ");
		sql.append("VALUES(?,?,?,?,?,?,?,?)");
		
		jdbcTemplate.update(
				sql.toString()
				,tahnModel.getUserPass()
				,tahnModel.getFirstname()
				,tahnModel.getLastname()
				,tahnModel.getBirthdayDate()
				,tahnModel.getAge()
				,tahnModel.getGender()
				,new java.sql.Timestamp(System.currentTimeMillis())
				,tahnModel.getCreateBy());
	}
	
	public void updateData(int id,TahnModel tahnModel) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE jakkarin.users SET ");
		sql.append("user_pass = ?, ");
		sql.append("firstname = ?, ");
		sql.append("lastname = ?, ");
		sql.append("birthday = ?, ");
		sql.append("age = ?, ");
		sql.append("gender = ?, ");
		sql.append("create_date = ?, ");
		sql.append("create_by = ? ");
		sql.append("WHERE id = ?");
		
		jdbcTemplate.update(
				sql.toString(),
				tahnModel.getUserPass(),
				tahnModel.getFirstname(),
				tahnModel.getLastname(),
				tahnModel.getBirthdayDate(),
				tahnModel.getAge(),
				tahnModel.getGender(),
				tahnModel.getCreateDate(),
				tahnModel.getCreateBy(),
				id
		);
	}
	
	
	public void deleteData(int id) {
		String sql = "DELETE FROM jakkarin.users WHERE id = ?";
		jdbcTemplate.update(sql,id);
	}
	
	
	
}
