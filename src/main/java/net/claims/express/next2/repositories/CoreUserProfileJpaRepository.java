package net.claims.express.next2.repositories;

import net.claims.express.next2.entities.CoreUserProfile;
import net.claims.express.next2.security.services.responses.NotificationSearchResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
@EnableJpaRepositories
@Repository
public interface CoreUserProfileJpaRepository extends BaseRepository<CoreUserProfile, String> {

    List<CoreUserProfile> findCoreUserProfileByCoreUserId(String coreUserId);

    @Query(value =  "select pTfPerm.ID from CORE_USER_PROFILE uProf , CORE_COMPANY_PROFILE cProf ," +
            " CORE_PROFILE prof ,  CORE_TASKFLOW_PERM tfPerm , CORE_PROFILE_TASKFLOW_PERM pTfPerm " +
            " where uProf.ID = ?2 and  uProf.CORE_COMPANY_PROFILE_ID = cProf.ID and " +
            " cProf.CORE_PROFILE_ID = prof.ID and prof.CORE_TASKFLOW_ID = tfPerm.CORE_TASKFLOW_ID " +
            " and tfPerm.PERMISSION_VERB = ?1  and tfPerm.ID = pTfPerm.CORE_TASKFLOW_PERM_ID ",
            nativeQuery = true)
    String getCoreProfTfPermId(String role , String userProf);
}
