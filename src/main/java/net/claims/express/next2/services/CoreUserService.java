package net.claims.express.next2.services;

import net.claims.express.next2.entities.CoreUser;
import net.claims.express.next2.repositories.CoreUserProfileJpaRepository;
import net.claims.express.next2.repositories.CoreUserRepository;
import net.claims.express.next2.repositories.DB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CoreUserService  extends BaseService<CoreUser> {
    @Autowired
    private DB db;

    @Autowired
    private CoreUserRepository coreUserRepository;

  /*  public List<CoreUser> getAllUsers() {
        List<String> usernames= new ArrayList<>();
        List<CoreUser> coreUsers= db.userRepository.findAll();
       *//* coreUsers.forEach(coreUser -> {
            usernames.add(coreUser.getId());
        });
        return  usernames;*//*
        return coreUsers;
    }*/
}
