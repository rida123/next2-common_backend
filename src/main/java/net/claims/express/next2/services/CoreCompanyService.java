package net.claims.express.next2.services;

import net.claims.express.next2.entities.CoreCompany;
import net.claims.express.next2.repositories.CoreCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class CoreCompanyService extends BaseService<CoreCompany> {

    @Autowired
    CoreCompanyRepository coreCompanyRepository;
}
