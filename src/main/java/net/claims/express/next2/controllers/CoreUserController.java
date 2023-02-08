package net.claims.express.next2.controllers;

import lombok.extern.slf4j.Slf4j;
import net.claims.express.next2.entities.*;
import net.claims.express.next2.http.StatusCode;
import net.claims.express.next2.http.response.ApiResponse;
import net.claims.express.next2.services.CoreCompanyService;
import net.claims.express.next2.services.CoreProfileService;
import net.claims.express.next2.services.CoreUserProfileService;
import net.claims.express.next2.services.CoreUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class    CoreUserController {

    @Autowired
    private CoreUserService coreUserService;

    @Autowired
    private CoreUserProfileService coreUserProfileService;

    @Autowired
    private CoreProfileService coreProfileService;

    @Autowired
    CoreCompanyService coreCompanyService;

    @Autowired

 /*   @Autowired
    private CustomUserService userService;*/

    @GetMapping("/all")
    public ApiResponse getAllUsers() {
        List<CoreUser> users = coreUserService.findAll();
        return new ApiResponse(StatusCode.OK.getCode(), "success", "all users.", users);
    }

    @GetMapping("/delete/{userId}/{profileId}")
    public ApiResponse denyProfileFromUser(@PathVariable("userId") String userId,
                                          @PathVariable("profileId") String profileId){
        return this.coreUserService.grantProfile(profileId, userId);
    }

    @GetMapping("/grant/{userId}/{profileId}")
    public ApiResponse grantProfileToUser(@PathVariable("profileId") String profileId,
                                          @PathVariable("userId") String userId){
        return this.coreUserService.grantProfile(userId, profileId);
    }


    @PostMapping("/{userId}/update-roles")
    public ApiResponse updateUserProfileRoles(String userId, @RequestBody CoreProfile userProfile){
        return this.coreUserService.updateRoles(userId, userProfile);
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
        System.out.println("size of profiles: " + foundCoureUser.getProfiles().size());
        System.out.println("profiles that i have:");
        for (CoreCompanyProfile p: foundCoureUser.getProfiles()) {
            System.out.println("code: " + p.getId());
        }
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
