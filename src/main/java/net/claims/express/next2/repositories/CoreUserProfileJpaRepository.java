package net.claims.express.next2.repositories;

import net.claims.express.next2.entities.CoreUserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
@EnableJpaRepositories
@Repository
public interface CoreUserProfileJpaRepository extends JpaRepository<CoreUserProfile, String> {

    List<CoreUserProfile> findCoreUserProfileByCoreUserId(String coreUserId);
}
