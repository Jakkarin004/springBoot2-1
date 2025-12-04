package pccth.sp.pccthspseedservice.model;

import java.util.Date;

public class TahnModel {
	private Integer id;
	private String userPass;
	private String firstname;
	private String lastname;
	private String birthday;
	private Integer age;
	private String gender;
	private Date createDate;
	private String createBy;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
	
	private java.sql.Date birthdayDate; // หรือ java.util.Date ก็ได้

	public java.sql.Date getBirthdayDate() {
	    return birthdayDate;
	}

	public void setBirthdayDate(java.sql.Date birthdayDate) {
	    this.birthdayDate = birthdayDate;
	}
	
	
	
}
