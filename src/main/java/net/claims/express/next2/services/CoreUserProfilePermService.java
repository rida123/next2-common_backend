package net.claims.express.next2.services;

import net.claims.express.next2.entities.CoreUserProfilePerm;
import net.claims.express.next2.repositories.CoreUserProfilePermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoreUserProfilePermService extends BaseService<CoreUserProfilePerm>{

    @Autowired
    CoreUserProfilePermRepository coreUserProfilePermRepository;
}
