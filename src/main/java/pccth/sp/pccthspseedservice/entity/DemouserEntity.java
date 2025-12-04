package pccth.sp.pccthspseedservice.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="demouser", catalog="seeddb")
public class DemouserEntity {

    private Long userId;
    private String name;
    private String lastname;
    private Long age;
    private String gender;
    private Date birthday;
    private Date createdDate;
    private String createdBy;
    private Date updatedDate;
    private String updatedBy;


    public DemouserEntity(Long userId, String name, String lastname, Long age, String gender, Date birthday, Date createdDate,
                    String createdBy, Date updatedDate, String updatedBy) {
        super();
        this.userId = userId;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.gender = gender;
        this.birthday = birthday;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.updatedDate = updatedDate;
        this.updatedBy = updatedBy;
    }



    public DemouserEntity() {
        super();
        // TODO Auto-generated constructor stub
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Column(name = "name", length = 1000)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "lastname", length = 1000)
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Column(name = "age", length = 20)
    public Long getAge() {
        return age;
    }
    public void setAge(Long age) {
        this.age = age;
    }

    @Column(name = "gender")
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "birthday")
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Column(name = "created_by", length = 20)
    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date")
    public Date getUpdatedDate() {
        return updatedDate;
    }
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Column(name = "updated_by", length = 100)
    public String getUpdatedBy() {
        return updatedBy;
    }
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
