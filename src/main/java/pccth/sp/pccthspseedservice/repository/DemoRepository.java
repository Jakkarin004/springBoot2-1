package pccth.sp.pccthspseedservice.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pccth.sp.pccthspseedservice.entity.DemouserEntity;

@Repository
public interface DemoRepository extends JpaRepository<DemouserEntity,Long> {
    
}
