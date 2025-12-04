package pccth.sp.pccthspseedservice.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rdbvrtrate", catalog = "seeddb")
public class RdbvrtrateEntity {

	@Id
    @Column(name = "SALEFROM")
    private BigDecimal saleFrom;

	@Column(name = "SALETO")
    private BigDecimal saleTo;

    @Column(name = "VRTRATE")
    private BigDecimal vrtRate;

    @Column(name = "VRTRATEAG")
    private BigDecimal vrtRateAg;
    
    public BigDecimal getSaleFrom() {
		return saleFrom;
	}

	public void setSaleFrom(BigDecimal saleFrom) {
		this.saleFrom = saleFrom;
	}

	public BigDecimal getSaleTo() {
		return saleTo;
	}

	public void setSaleTo(BigDecimal saleTo) {
		this.saleTo = saleTo;
	}

	public BigDecimal getVrtRate() {
		return vrtRate;
	}

	public void setVrtRate(BigDecimal vrtRate) {
		this.vrtRate = vrtRate;
	}

	public BigDecimal getVrtRateAg() {
		return vrtRateAg;
	}

	public void setVrtRateEg(BigDecimal vrtRateAg) {
		this.vrtRateAg = vrtRateAg;
	}

    
}
