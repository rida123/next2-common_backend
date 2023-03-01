package net.claims.express.next2.repositories;

import net.claims.express.next2.entities.CoreUserPreference;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface CoreUserPreferenceRepository  extends  BaseRepository<CoreUserPreference,String>{

@Query(value = "select  up  from CoreUserPreference up join fetch up.coreUser u" +
        " join fetch up.coreCompany c where "  +
        " u.id = ?1  ")
    Optional<CoreUserPreference> findByCoreUser(String id);



    @Query(" Select up from CoreUserPreference up  " +
            " join fetch up.coreUser u " +
            "   join fetch up.coreCompany c  where " +
            " lower(up.displayName) LIKE  %?1%  and  lower(u.id)  LIKE %?2% ")
            List<CoreUserPreference> searchByDisplayNameAndUserName( String displayName ,String username );

    @Query(" Select up from CoreUserPreference up  " +
            " join fetch up.coreUser u " +
            "   join fetch up.coreCompany c  where " +
            "  lower(u.id) LIKE %?1% ")
    List<CoreUserPreference> searchByUserName( String username );

    @Query(" Select up from CoreUserPreference up  " +
            " join fetch up.coreUser u " +
            "   join fetch up.coreCompany c  where " +
            " lower(up.displayName) LIKE  %?1% ")
    List<CoreUserPreference> searchByDisplayName(String displayName);
}
