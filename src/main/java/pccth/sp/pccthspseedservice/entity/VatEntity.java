package pccth.sp.pccthspseedservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rdbvrtrate", catalog = "seeddb")
public class VatEntity 
{
	private Float salefrom;
	private Float saleto;
	private Float vrtrate;
	private Float vrtrateag;
	
	
	public VatEntity(Float salefrom, Float saleto, Float vrtrate, Float vrtrateag ) 
	{
		super();
		this.salefrom = salefrom;
		this.saleto = saleto;
		this.vrtrate = vrtrate;
		this.vrtrateag = vrtrateag;
		
	}

	public VatEntity() 
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Float getSalefrom() 
	{
		return salefrom;
	}

	public void setSalefrom(Float salefrom) 
	{
		this.salefrom = salefrom;
	}

	@Column(name = "saleto", length = 20)
	public Float getSaleto() 
	{
		return saleto;
	}

	public void setSaleto(Float saleto) 
	{
		this.saleto = saleto;
	}

	@Column(name = "vrtrate", length = 20)
	public Float getVrtrate() 
	{
		return vrtrate;
	}

	public void setVrtrate(Float vrtrate) 
	{
		this.vrtrate = vrtrate;
	}

	@Column(name = "vrtrateag", length = 20)
	public Float getVrtrateag() 
	{
		return vrtrateag;
	}

	public void setVrtrateag(Float vrtrateag) 
	{
		this.vrtrateag = vrtrateag;
	}

}
