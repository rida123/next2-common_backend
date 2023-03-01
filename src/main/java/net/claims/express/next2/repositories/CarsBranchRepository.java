package net.claims.express.next2.repositories;


import net.claims.express.next2.entities.CarsBranch;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface CarsBranchRepository extends BaseRepository <CarsBranch,String >{
    @Query("select b from CarsBranch b join fetch b.carsInsurance  i  where i.insuranceId = ?1 ")
   List<CarsBranch> findByCarsInsurance(String companyId);
}

