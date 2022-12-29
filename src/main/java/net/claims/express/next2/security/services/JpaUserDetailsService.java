package net.claims.express.next2.security.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.claims.express.next2.entities.CoreUser;
import net.claims.express.next2.repositories.CoreUserProfileJpaRepository;
import net.claims.express.next2.repositories.CoreUserRepository;
import net.claims.express.next2.security.model.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class JpaUserDetailsService implements  UserDetailsService {

    @Autowired
    private final CoreUserRepository coreUserRepository;

    @Autowired
    private final CoreUserProfileJpaRepository coreUserProfileRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("coming username is: " + username);
        CoreUser foundCoureUser;
        try {
           Optional<CoreUser> optionalCoreUser = coreUserRepository.findById(username);

            if(!optionalCoreUser.isPresent()) {
                log.error("User not found in the database");
                throw new UsernameNotFoundException("User not found in the database");
            }

            foundCoureUser = optionalCoreUser.get();
            //get company of this user:
            if(foundCoureUser.getProfiles().size() > 0) {
                String profileId = foundCoureUser.getProfiles().get(0).getId();

                int companyId = Integer.parseInt(profileId.substring(0, profileId.indexOf(".")));
                    foundCoureUser.setCompany_id(companyId);
            }
            log.info("User found in the database");

        System.out.println("----------------------------------------------");

        //returning a spring security user
        return new SecurityUser(coreUserProfileRepository, foundCoureUser);
        }
         catch (Exception exp) {
            System.out.println(exp.getMessage());
            return  null;
        }
// TODO: 12/7/2022 if user is not found in the database => return an exception
      /*  return usr.map(SecurityUser::new)
                .orElseThrow( ()-> {
                    System.out.println("user not found in db");
                    log.error("user not found in the database");
                    return new UsernameNotFoundException("@JpaUserDetailsService: username not found "
                            + username);
                });*/
    }
}
