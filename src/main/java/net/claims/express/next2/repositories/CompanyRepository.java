package net.claims.express.next2.repositories;

import net.claims.express.next2.entities.CoreCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface CompanyRepository extends BaseRepository<CoreCompany, Long> {

}
