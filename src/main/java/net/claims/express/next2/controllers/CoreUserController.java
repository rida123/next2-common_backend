package net.claims.express.next2.controllers;

import lombok.extern.slf4j.Slf4j;
import net.claims.express.next2.entities.*;
import net.claims.express.next2.security.model.SecurityAuthority;
import net.claims.express.next2.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class CoreUserController {

    @Autowired
    private CoreUserService coreUserService;

    @Autowired
    private CoreUserProfileService coreUserProfileService;

    @Autowired
    private CoreProfileService coreProfileService;

 /*   @Autowired
    private CustomUserService userService;*/

    @GetMapping("/all")
    public List<CoreUser> getAllUsers() {
        List<CoreUser> users = coreUserService.findAll();
        return users;
    }

    @GetMapping("/{userId}/profiles")
    public List<CoreProfile> getProfilesPerUser(@PathVariable String userId) {

        CoreUser foundCoureUser;
        Optional<CoreUser> optionalCoreUser = this.coreUserService.findById(userId);

        if (!optionalCoreUser.isPresent()) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        }

        foundCoureUser = optionalCoreUser.get();
//        List<CoreUserProfile> registeredProfiles = this.coreUserProfileRepository.findCoreUserProfileByCoreUserId(coreUser.getId());

        List<CoreUserProfile> registeredProfiles = this.coreUserProfileService.getProfilesPerUser(foundCoureUser.getId());

        List<CoreProfile> myCoreProfiles = new ArrayList<>();

        for (CoreUserProfile core_user_profile : registeredProfiles) {
            String coreCompanyProfileId = core_user_profile.getCoreCompanyProfileId();
            //now fetch profile_id:
            String coreProfileId = (coreCompanyProfileId.substring(coreCompanyProfileId.indexOf(".") + 1));
            //now fetch CoreProfile object consisting of all roles for this profile part of these roles
            // are granted to the user and others not granted yet
            Optional<CoreProfile> optionalCoreProfile = this.coreProfileService.findById(coreProfileId);

            if (!optionalCoreProfile.isPresent()) {
                log.error("User not found in the database");
                throw new UsernameNotFoundException("User not found in the database");
            }

            CoreProfile coreProfile = optionalCoreProfile.get();

            Set<CoreRole> allRolesPerProfile = coreProfile.getProfileRoles();


            for (CoreRole granted_role : core_user_profile.getUserRoles()) {
                for (CoreRole role : allRolesPerProfile) {
                    if (granted_role.equals(role)) {
                        role.setGranted(true);
                        break;
                    }
                }
            }

            coreProfile.setRoles(allRolesPerProfile);
            myCoreProfiles.add(coreProfile);
        }
        System.out.println("end looping through map");
        return myCoreProfiles;
    }
}
