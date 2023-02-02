package net.claims.express.next2.services;

import lombok.extern.log4j.Log4j2;
import net.claims.express.next2.entities.CarsInsuranceEmployee;
import net.claims.express.next2.repositories.CarsInsuranceEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * todo: add log4j2
 */
@Service
public class CarsInsuranceEmployeeService extends  BaseService<CarsInsuranceEmployee> {

    @Autowired
    CarsInsuranceEmployeeRepository carsInsuranceEmployeeRepository;

    public Optional<CarsInsuranceEmployee> findByUsersCode(String coreUserId){
        return this.carsInsuranceEmployeeRepository.findByUsersCode(coreUserId);
    }

}
