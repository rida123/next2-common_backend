package net.claims.express.next2.repositories;

import net.claims.express.next2.entities.CoreCompany;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface CoreCompanyRepository extends BaseRepository<CoreCompany, String> {
}
