package pccth.sp.pccthspseedservice.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Detail",catalog="jakkarin") //name="tahn",catalog="seeddb" ชื่อตารางของเรา และของตัวหลัก
public class DetailEntity {
	private Integer id;
	private String vdtNo;
	private String bookNo;
	private Integer bookNum;
	private Date dateMake;
	private String nameCompany;
	
	private BigDecimal totalPrice;
	private BigDecimal taxAt;
	private BigDecimal taxRd;
	private BigDecimal taxRa;
	private BigDecimal refunfFr;
	
	
	private Integer idName;
	private Integer noBranch;
	
	private String createBy;
	private Date createDate;
	private Date updateDate;
	private String updateBy;
	
	
	public DetailEntity() {
		super();
		// TODO Auto-generated constructor stub
	}


	public DetailEntity(Integer id, String vdtNo, String bookNo, Integer bookNum, Date dateMake, String nameCompany,
			BigDecimal totalPrice, BigDecimal taxAt, BigDecimal taxRd, BigDecimal taxRa, BigDecimal refunfFr,
			Integer idName, Integer noBranch, String createBy, Date createDate, Date updateDate, String updateBy) {
		super();
		this.id = id;
		this.vdtNo = vdtNo;
		this.bookNo = bookNo;
		this.bookNum = bookNum;
		this.dateMake = dateMake;
		this.nameCompany = nameCompany;
		this.totalPrice = totalPrice;
		this.taxAt = taxAt;
		this.taxRd = taxRd;
		this.taxRa = taxRa;
		this.refunfFr = refunfFr;
		this.idName = idName;
		this.noBranch = noBranch;
		this.createBy = createBy;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.updateBy = updateBy;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true , nullable = false )
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	@Column(name = "vdt_no", nullable = false, length = 20)
	public String getVdtNo() {
		return vdtNo;
	}


	public void setVdtNo(String vdtNo) {
		this.vdtNo = vdtNo;
	}


	@Column(name = "book_no")
	public String getBookNo() {
		return bookNo;
	}


	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}

	@Column(name = "book_num")
	public Integer getBookNum() {
		return bookNum;
	}


	public void setBookNum(Integer bookNum) {
		this.bookNum = bookNum;
	}

	@Column(name = "date_make")
	public Date getDateMake() {
		return dateMake;
	}


	public void setDateMake(Date dateMake) {
		this.dateMake = dateMake;
	}

	@Column(name = "name_company")
	public String getNameCompany() {
		return nameCompany;
	}


	public void setNameCompany(String nameCompany) {
		this.nameCompany = nameCompany;
	}

	@Column(name = "total_price",precision = 10, scale = 2)
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Column(name = "tax_at",precision = 10, scale = 2)
	public BigDecimal getTaxAt() {
		return taxAt;
	}


	public void setTaxAt(BigDecimal taxAt) {
		this.taxAt = taxAt;
	}


	@Column(name = "tax_rd",precision = 10, scale = 2)
	public BigDecimal getTaxRd() {
		return taxRd;
	}


	public void setTaxRd(BigDecimal taxRd) {
		this.taxRd = taxRd;
	}


	@Column(name = "tax_ra",precision = 10, scale = 2)
	public BigDecimal getTaxRa() {
		return taxRa;
	}


	public void setTaxRa(BigDecimal taxRa) {
		this.taxRa = taxRa;
	}


	@Column(name = "refunf_fr",precision = 10, scale = 2)
	public BigDecimal getRefunfFr() {
		return refunfFr;
	}


	public void setRefunfFr(BigDecimal refunfFr) {
		this.refunfFr = refunfFr;
	}


	@Column(name = "id_name",precision = 10, scale = 2)
	public Integer getIdName() {
		return idName;
	}


	public void setIdName(Integer idName) {
		this.idName = idName;
	}


	@Column(name = "no_branch")
	public Integer getNoBranch() {
		return noBranch;
	}


	public void setNoBranch(Integer noBranch) {
		this.noBranch = noBranch;
	}


	@Column(name = "create_by")
	public String getCreateBy() {
		return createBy;
	}


	
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date")
	public Date getUpdateDate() {
		return updateDate;
	}

	
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "update_by")
	public String getUpdateBy() {
		return updateBy;
	}


	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
	
	
	
	
}
