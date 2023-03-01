package net.claims.express.next2.repositories;

import net.claims.express.next2.entities.CarsInsuranceEmployee;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@EnableJpaRepositories
@Repository
public interface CarsInsuranceEmployeeRepository extends BaseRepository<CarsInsuranceEmployee, String>{
    Optional<CarsInsuranceEmployee> findByUsersCode(String coreUserId);
}
