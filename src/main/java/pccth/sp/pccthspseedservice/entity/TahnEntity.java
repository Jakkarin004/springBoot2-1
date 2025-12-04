package pccth.sp.pccthspseedservice.entity;

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
@Table(name="users",catalog="jakkarin") //name="tahn",catalog="seeddb" ชื่อตารางของเรา และของตัวหลัก
public class TahnEntity {
	private Integer id;
	private String userPass;
	private String firstname;
	private String lastname;
	private String birthday;
	private Integer age;
	private String gender;
	private Date createDate;
	private String createBy;
	
	public TahnEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public TahnEntity(Integer id, String userPass, String firstname, String lastname, String birthday, Integer age,
			String gender, Date createDate, String createBy) {
		super();
		this.id = id;
		this.userPass = userPass;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthday = birthday;
		this.age = age;
		this.gender = gender;
		this.createDate = createDate;
		this.createBy = createBy;
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

	@Column(name = "user_pass")
	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	@Column(name = "firstname")
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Column(name = "lastname")
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Column(name = "birthday")
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Column(name = "age")
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "gender")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "create_by")
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
	
	
}
