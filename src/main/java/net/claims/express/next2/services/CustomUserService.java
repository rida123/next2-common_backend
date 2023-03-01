package net.claims.express.next2.services;

import net.claims.express.next2.entities.CoreUser;
import net.claims.express.next2.repositories.CoreUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService extends BaseService<CoreUser> {

    @Autowired
    CoreUserRepository coreUserRepository;

}
