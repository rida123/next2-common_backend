package net.claims.express.next2.controllers;

import net.claims.express.next2.entities.CoreCompanyProfile;
import net.claims.express.next2.entities.CoreProfile;
import net.claims.express.next2.entities.CoreRole;
import net.claims.express.next2.entities.CoreUser;
import net.claims.express.next2.http.StatusCode;
import net.claims.express.next2.http.response.ApiResponse;
import net.claims.express.next2.http.response.MyBaseResponse;
import net.claims.express.next2.security.model.SecurityUser;
import net.claims.express.next2.services.CoreProfileService;
import net.claims.express.next2.services.CoreUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/profiles")
public class ProfilesController extends BaseController {

    @Autowired
    private CoreProfileService profileService;

    @Autowired
    private CoreUserService coreUserService;


    @GetMapping("/all") //TODO: WE MUST HAVE THE COMPANYiD AS PARAMETER
    public List<CoreProfile> getAllProfiles(HttpServletResponse resp)  {
    //    Thread.sleep(5000);
        return this.profileService.findAll();
     /*   resp.setStatus(402); */
    }

    @GetMapping("/{userId}")
    public ApiResponse getAllProfilesForCompany(@PathVariable("userId") String userId) {
        CoreUser user = this.coreUserService.getCoreUser(userId);
      return this.coreUserService.getUserCompanyProfiles(user.getId());
    }


//get profile per user  code and description
    @GetMapping("/getUserProfile")
    public ApiResponse getUserProfile () {



        CoreUser coreUser = getCurrentUser().getCoreUser();
        System.out.println(coreUser.getId());
        System.out.println(coreUser.getEncryptedPwd());

        System.out.println("listing my profiles:");
        List<CoreCompanyProfile> myCompanyProfiles = coreUser.getProfiles();

        //preparing EMPTY array list to hold profiles that I can use within my company
        List<MyBaseResponse> myProfiles = new ArrayList<>();
        List<CoreProfile> userProfiles =  this.coreUserService.getProfilesPerUser(coreUser.getId());

        for(CoreProfile coreProfile: userProfiles) {
            System.out.println("@PROFILE => " + coreProfile.getId());
            MyBaseResponse myBaseResponse = new MyBaseResponse();
         //   CoreProfile coreProfile = companyProfile.getCoreProfile();
            myBaseResponse.setCode(coreProfile.getId());
            myBaseResponse.setDescription(coreProfile.getDescription());
            //now inject roles for this user for this core profile:

//            Set<CoreRole> roles_per_profile =  getCurrentUser().getUserRolesPerProfile().get(coreUser.getCompany_id() + "." +  coreProfile.getId());
//            coreProfile.setRoles(roles_per_profile);
            myProfiles.add(myBaseResponse);
        }
        return new  ApiResponse(StatusCode.OK.getCode(), "success", "get profiles", myProfiles);



    }


//profiles with roles per user
    @GetMapping("/profiles")
    public ApiResponse getProfilesPerUser() {
        CoreUser coreUser = getCurrentUser().getCoreUser();

        List<CoreProfile> userProfiles =  this.coreUserService.getProfilesPerUser(coreUser.getId());
        return new ApiResponse(StatusCode.OK.getCode(), "success", "Profiles by user returned successfully.", userProfiles);
    }//old return type: List<CoreProfile>
}





