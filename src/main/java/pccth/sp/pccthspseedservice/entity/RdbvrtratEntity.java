package pccth.sp.pccthspseedservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Rdbvrtrate",catalog="jakkarin") //name="tahn",catalog="seeddb" ชื่อตารางของเรา และของตัวหลัก
public class RdbvrtratEntity {
	private Integer saleFROM;
	private Integer saleTO;
	private Integer vrtRATE;
	private Integer vrtRATEAG;
	
	public RdbvrtratEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RdbvrtratEntity(Integer saleFROM, Integer saleTO, Integer vrtRATE, Integer vrtRATEAG) {
		super();
		this.saleFROM = saleFROM;
		this.saleTO = saleTO;
		this.vrtRATE = vrtRATE;
		this.vrtRATEAG = vrtRATEAG;
	}

	@Id
	@Column(name = "saleFROM")
	public Integer getSaleFROM() {
		return saleFROM;
	}

	public void setSaleFROM(Integer saleFROM) {
		this.saleFROM = saleFROM;
	}

	@Column(name = "saleTO")
	public Integer getSaleTO() {
		return saleTO;
	}

	public void setSaleTO(Integer saleTO) {
		this.saleTO = saleTO;
	}

	@Column(name = "vrtRATE")
	public Integer getVrtRATE() {
		return vrtRATE;
	}

	public void setVrtRATE(Integer vrtRATE) {
		this.vrtRATE = vrtRATE;
	}

	@Column(name = "vrtRATEAG")
	public Integer getVrtRATEAG() {
		return vrtRATEAG;
	}

	public void setVrtRATEAG(Integer vrtRATEAG) {
		this.vrtRATEAG = vrtRATEAG;
	}
	
	
	
	
	
	
}
