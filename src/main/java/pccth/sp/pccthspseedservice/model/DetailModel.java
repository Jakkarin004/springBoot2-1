package pccth.sp.pccthspseedservice.model;

import java.math.BigDecimal;
import java.util.Date;

public class DetailModel {
	private Integer id;
	private String vdtNo;
	private Integer bookNo;
	private String bookNum;
	private String dateMake;
	private String nameCompany;
	
	private BigDecimal totalPrice;
	private BigDecimal taxAt;
	private BigDecimal taxRd;
	private BigDecimal taxRa;
	private BigDecimal refunfFr;
	
	
	private String idName;
	private String noBranch;
	
	private String createBy;
	private Date createDate;
	private Date updateDate;
	private String updateBy;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVdtNo() {
		return vdtNo;
	}
	public void setVdtNo(String vdtNo) {
		this.vdtNo = vdtNo;
	}
	public Integer getBookNo() {
		return bookNo;
	}
	public void setBookNo(Integer bookNo) {
		this.bookNo = bookNo;
	}
	public String getBookNum() {
		return bookNum;
	}
	public void setBookNum(String bookNum) {
		this.bookNum = bookNum;
	}
	public String getDateMake() {
		return dateMake;
	}
	public void setDateMake(String dateMake) {
		this.dateMake = dateMake;
	}
	public String getNameCompany() {
		return nameCompany;
	}
	public void setNameCompany(String nameCompany) {
		this.nameCompany = nameCompany;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public BigDecimal getTaxAt() {
		return taxAt;
	}
	public void setTaxAt(BigDecimal taxAt) {
		this.taxAt = taxAt;
	}
	public BigDecimal getTaxRd() {
		return taxRd;
	}
	public void setTaxRd(BigDecimal taxRd) {
		this.taxRd = taxRd;
	}
	public BigDecimal getTaxRa() {
		return taxRa;
	}
	public void setTaxRa(BigDecimal taxRa) {
		this.taxRa = taxRa;
	}
	public BigDecimal getRefunfFr() {
		return refunfFr;
	}
	public void setRefunfFr(BigDecimal refunfFr) {
		this.refunfFr = refunfFr;
	}
	public String getIdName() {
		return idName;
	}
	public void setIdName(String idName) {
		this.idName = idName;
	}
	public String getNoBranch() {
		return noBranch;
	}
	public void setNoBranch(String noBranch) {
		this.noBranch = noBranch;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
	
}
