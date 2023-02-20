package net.claims.express.next2.controllers;

import lombok.extern.slf4j.Slf4j;
import net.claims.express.next2.entities.CoreProfile;
import net.claims.express.next2.entities.CoreUser;
import net.claims.express.next2.http.StatusCode;
import net.claims.express.next2.http.requests.AddUserRequest;
import net.claims.express.next2.http.response.ApiResponse;
import net.claims.express.next2.services.CoreCompanyService;
import net.claims.express.next2.services.CoreProfileService;
import net.claims.express.next2.services.CoreUserProfileService;
import net.claims.express.next2.services.CoreUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
/**
 * get all users
 * todo: ask jean if we want to return users for a certain company => pass companyId
 */
    @GetMapping("/all")
    public ApiResponse getAllUsers() {
        List<CoreUser> users = coreUserService.findAll();
        return new ApiResponse(StatusCode.OK.getCode(), "success", "all users.", users);
    }

    @GetMapping("/delete/{userId}/{profileId}")
    public ApiResponse denyProfileFromUser(@PathVariable("userId") String userId,
                                          @PathVariable("profileId") String profileId){
        return this.coreUserService.revokeProfile(userId, profileId);
    }

@GetMapping("/grant/{userId}/{profileId}")
    public ApiResponse grantProfileToUser(@PathVariable("profileId") String profileId,
                                          @PathVariable("userId") String userId){
        return this.coreUserService.grantProfile(userId, profileId);
    }

    @PostMapping("/addUser")
    private ApiResponse addUser (@RequestBody AddUserRequest addUserRequest){

        return this.coreUserService.addUser(addUserRequest);
    }



    @PostMapping("/{userId}/update-roles")
    public ApiResponse updateUserProfileRoles(@PathVariable String userId, @RequestBody CoreProfile userProfile){
//<<<<<<< HEAD
        System.out.println("we are in update roles");
//=======
//>>>>>>> d06db8ffa8fa877fd7c38566271c068cd92cce7a
        return this.coreUserService.updateRoles(userId, userProfile);
    }

    @GetMapping("/{userId}/profiles")
    public ApiResponse getProfilesPerUser(@PathVariable String userId) {
        List<CoreProfile> userProfiles =  this.coreUserService.getProfilesPerUser(userId);
        return new ApiResponse(StatusCode.OK.getCode(), "success", "Profiles by user returned successfully.", userProfiles);
    }//old return type: List<CoreProfile>
}
