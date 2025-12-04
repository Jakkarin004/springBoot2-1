package pccth.sp.pccthspseedservice.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pccth.sp.pccthspseedservice.entity.RdbvrtrateEntity;

public interface RdbvrtrateJpaRepository extends JpaRepository<RdbvrtrateEntity,BigDecimal> {

	@Query(value = "SELECT * FROM rdbvrtrate WHERE :amount BETWEEN SALEFROM AND SALETO",nativeQuery = true)
	RdbvrtrateEntity getDataByAmount(int amount);
	
	
}

