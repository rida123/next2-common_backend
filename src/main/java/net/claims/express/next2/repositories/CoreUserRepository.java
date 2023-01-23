package net.claims.express.next2.repositories;

import net.claims.express.next2.entities.CoreUser;
import net.claims.express.next2.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@EnableJpaRepositories
@Repository
public interface CoreUserRepository extends BaseRepository<CoreUser, String> {

 /*   @Query("SELECT u from CoreUser u where u.coreUserId = :username")
    Optional<CoreUser> findCoreUserByUsername(String username);*/


    @Query(value = " SELECT CONFIG_VALUE FROM CORE_CONFIGURATION WHERE ID = ?1 ", nativeQuery = true)
    String findConfigByKey(String key);
}
