package pccth.sp.pccthspseedservice.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import pccth.sp.pccthspseedservice.model.DetailModel;
import pccth.sp.pccthspseedservice.model.HeaderModel;
import pccth.sp.pccthspseedservice.model.RdbvrtrateModel;

@Repository
public class MainJDBCRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
		
	//ค้นหาตัวววววว
	public List<DetailModel> findDetailByVdtNo(String vdtNo) {
	    StringBuilder sql = new StringBuilder();
	    sql.append("SELECT ")
	       .append("id, ")
	       .append("vdt_no, ")
	       .append("book_no, ")
	       .append("book_num, ")
	       .append("date_make, ")
	       .append("name_company, ")
	       .append("total_price, ")
	       .append("tax_at, ")
	       .append("tax_rd, ")
	       .append("tax_ra, ")
	       .append("refunf_fr, ")
	       .append("id_name, ")
	       .append("no_branch, ")
	       .append("create_by, ")
	       .append("create_date, ")
	       .append("update_date, ")
	       .append("update_by ")
	       .append("FROM jakkarin.detail ")
	       .append("WHERE vdt_no = ?");

	    return jdbcTemplate.query(
	            sql.toString(),
	            new BeanPropertyRowMapper<>(DetailModel.class),
	            vdtNo
	    );
	}

	//ค้นหาหัวววววววววว
	public List<HeaderModel> searchHeader(String vdtNo, String vdtDate) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT vdt_no, vdt_date, create_by,create_date ")
		   .append("FROM jakkarin.header h ")
		   .append("WHERE 1 = 1");

	    List<Object> params = new ArrayList<>();

//	    if (vdtNo != null && !vdtNo.isEmpty()) {
//	        sql.append(" AND h.vdt_no = ?");
//	        params.add(vdtNo);
//	    }
//
//	    if (vdtDate != null && !vdtDate.isEmpty()) {
//	        sql.append(" AND h.vdt_date = ?");
//	        params.add(vdtDate);
//	    }
	    
	    if (StringUtils.hasText(vdtNo)) {
	        sql.append(" AND h.vdt_no = ?");
	        params.add(vdtNo);
	    }

	    if (StringUtils.hasText(vdtDate)) {
	        sql.append(" AND h.vdt_date = ?");
	        params.add(vdtDate);
	    }

	    return jdbcTemplate.query(
	            sql.toString(),
	            new BeanPropertyRowMapper<>(HeaderModel.class),
	            params.toArray()
	    );
	}

	// method สำหรับการเพิ่ม
	//เช็คเพื่ออัพเดท
	public boolean existsDetail(String vdtNo, int id) {
	    String sql = "SELECT COUNT(*) FROM jakkarin.detail WHERE vdt_no = ? AND id = ?";
	    Integer count = jdbcTemplate.queryForObject(sql, Integer.class, vdtNo, id);
	    return count != null && count > 0;
	}
	
	//แยก
//	public void saveOrUpdateDetail(DetailModel detailModel) {
//	    if (existsDetail(detailModel.getVdtNo(), detailModel.getId())) {
//	        updateDetail(detailModel);// มีแล้ว update แทน
//	    } else {
//	        insertDetail(detailModel);// ไม่มี insert
//	    }
//	}
	
	// method สำหรับการ check header 
	public boolean checkHeader(String vdtNo) {
		String sql = "SELECT COUNT(*) FROM jakkarin.header WHERE vdt_no = ?";
		Integer count = jdbcTemplate.queryForObject(sql,Integer.class,vdtNo);
		return count != null && count > 0;
	}
	
	public void insertHeader(HeaderModel headerModel) {
		String sql = "INSERT INTO jakkarin.header (vdt_no, vdt_date, create_by, create_date) "
				+ "VALUES(?, (CURRENT_DATE), ?, current_timestamp())";
		
		jdbcTemplate.update(sql,
				headerModel.getVdtNo(),
				headerModel.getCreateBy()
				);
	}
	
	public void insertDetail(DetailModel detailModel) {
	    String sql = "INSERT INTO jakkarin.detail (vdt_no, book_no, book_num, date_make, name_company, "
	            + "total_price, tax_at, tax_rd, tax_ra, refunf_fr, id_name, no_branch, create_by, create_date) "
	            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP())";

	    jdbcTemplate.update(sql,
	            detailModel.getVdtNo(),
	            detailModel.getBookNo(),
	            detailModel.getBookNum(),
	            detailModel.getDateMake(),
	            detailModel.getNameCompany(),
	            detailModel.getTotalPrice(),
	            detailModel.getTaxAt(),
	            detailModel.getTaxRd(),
	            detailModel.getTaxRa(),
	            detailModel.getRefunfFr(),
	            detailModel.getIdName(),
	            detailModel.getNoBranch(),
	            detailModel.getCreateBy()
	    );
	}
	
	public void updateDetail(DetailModel detail) {
	    StringBuilder sql = new StringBuilder();
	    sql.append("UPDATE jakkarin.detail SET ");
	    sql.append("book_no = ?, ");
	    sql.append("book_num = ?, ");
	    sql.append("date_make = ?, ");
	    sql.append("name_company = ?, ");
	    sql.append("total_price = ?, ");
	    sql.append("tax_at = ?, ");
	    sql.append("tax_rd = ?, ");
	    sql.append("tax_ra = ?, ");
	    sql.append("refunf_fr = ?, ");
	    sql.append("id_name = ?, ");
	    sql.append("no_branch = ?, ");
	    sql.append("update_by = ?, ");
	    sql.append("update_date = CASE WHEN ? IS NULL THEN NULL ELSE CURRENT_TIMESTAMP() END ");
	    sql.append("WHERE vdt_no = ? AND id = ?");

	    jdbcTemplate.update(sql.toString(),
	        detail.getBookNo(),
	        detail.getBookNum(),
	        detail.getDateMake(),
	        detail.getNameCompany(),
	        detail.getTotalPrice(),
	        detail.getTaxAt(),
	        detail.getTaxRd(),
	        detail.getTaxRa(),
	        detail.getRefunfFr(),
	        detail.getIdName(),
	        detail.getNoBranch(),
	        detail.getUpdateBy(),// สำหรับ update_by
	        detail.getUpdateBy(),// สำหรับตรวจ null
	        detail.getVdtNo(),
	        detail.getId()
	    );
	}


	public List<RdbvrtrateModel> getAllVatRate(){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT saleFROM, saleTO, vrtRATE, vrtRATEAG FROM jakkarin.rdbvrtrate ");
		
		return jdbcTemplate.query(sql.toString(),(rs,rowNum) -> {
			RdbvrtrateModel rdbvrtrateModel = new RdbvrtrateModel(); //สร้าง object
				rdbvrtrateModel.setSaleFROM(rs.getInt("saleFROM"));//ดึงค่าเป็น int และเอาไปเก็บใน object
				rdbvrtrateModel.setSaleTO(rs.getInt("saleTO"));
				rdbvrtrateModel.setVrtRATE(rs.getInt("vrtRATE"));
				rdbvrtrateModel.setVrtRATEAG(rs.getInt("vrtRATEAG"));
		        return rdbvrtrateModel; // ส่งค่ากลับไปที่ List
		});
	}
	
	public void deleteData(int id) {
		String sql = "DELETE FROM jakkarin.detail WHERE id = ?";
		jdbcTemplate.update(sql,id);
	}
	
	public List<RdbvrtrateModel> findVatTax() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM jakkarin.rdbvrtrate");
		
		List<RdbvrtrateModel> list = jdbcTemplate.query(sql.toString(),
				new BeanPropertyRowMapper(RdbvrtrateModel.class));
		return list;
	}
	
	
}
