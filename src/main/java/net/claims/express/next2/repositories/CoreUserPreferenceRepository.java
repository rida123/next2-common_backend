package net.claims.express.next2.repositories;

import net.claims.express.next2.entities.CoreUserPreference;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface CoreUserPreferenceRepository  extends  BaseRepository<CoreUserPreference,String>{

@Query(value = "select  up  from CoreUserPreference up join fetch up.coreUser u" +
        " join fetch up.coreCompany c where "  +
        " u.id = ?1  ")
    Optional<CoreUserPreference> findByCoreUser(String id);
}
