package pccth.sp.pccthspseedservice.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Header",catalog="jakkarin") //name="tahn",catalog="seeddb" ชื่อตารางของเรา และของตัวหลัก
public class HeaderEntity {
	private String vdtNo ;
	private Date vdtDate;
	private String createBy;
	private Date createDate;
	
	public HeaderEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public HeaderEntity(String vdtNo, Date vdtDate, String createBy, Date createDate) {
		super();
		this.vdtNo = vdtNo;
		this.vdtDate = vdtDate;
		this.createBy = createBy;
		this.createDate = createDate;
	}

	@Id
	@Column(name = "vdt_no", nullable = false, length = 20)
	public String getVdtNo() {
		return vdtNo;
	}
	public void setVdtNo(String vdtNo) {
		this.vdtNo = vdtNo;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "vdt_date")
	public Date getVdtDate() {
		return vdtDate;
	}
	public void setVdtDate(Date vdtDate) {
		this.vdtDate = vdtDate;
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
}
