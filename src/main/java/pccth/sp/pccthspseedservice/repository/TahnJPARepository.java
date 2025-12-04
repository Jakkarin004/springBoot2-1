package pccth.sp.pccthspseedservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pccth.sp.pccthspseedservice.entity.TahnEntity;

public interface TahnJPARepository extends JpaRepository<TahnEntity, Integer> {
//	List<TahnEntity> findByName(String name);

}
