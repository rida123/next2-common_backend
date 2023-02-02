package net.claims.express.next2.services;

import net.claims.express.next2.entities.CoreUserProfile;
import net.claims.express.next2.repositories.CoreUserProfileJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoreUserProfileService extends BaseService<CoreUserProfile> {


    @Autowired
    CoreUserProfileJpaRepository coreUserProfileJpaRepository;

    public List<CoreUserProfile> getProfilesPerUser(String coreUserId) {
        return this.coreUserProfileJpaRepository.findCoreUserProfileByCoreUserId(coreUserId);
    }


}
