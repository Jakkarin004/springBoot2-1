package pccth.sp.pccthspseedservice.model;

import java.util.Date;

public class HeaderModel {
	private String vdtNo ;
	private Date vdtDate;
	private String createBy;
	private Date createDate;
	
	
	public String getVdtNo() {
		return vdtNo;
	}
	
	public void setVdtNo(String vdtNo) {
		this.vdtNo = vdtNo;
	}
	
	public Date getVdtDate() {
		return vdtDate;
	}
	
	public void setVdtDate(Date vdtDate) {
		this.vdtDate = vdtDate;
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
	
	
}
