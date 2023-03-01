package net.claims.express.next2.repositories;

import net.claims.express.next2.entities.CoreCompany;
import net.claims.express.next2.entities.CoreProfile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
@EnableJpaRepositories
@Repository
public interface CoreProfileRepository extends BaseRepository<CoreProfile, String>{

//    List<CoreProfile> findByCoreCompany(CoreCompany c);

/*    @Query(value = "select co from CoreProfile  co join fetch co.where co.code not in ?1 ")
public List<CoreProfile> findByCodeNotIn(List<String> allProfiles);*/
}
