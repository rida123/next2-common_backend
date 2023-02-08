package net.claims.express.next2.controllers;

import net.claims.express.next2.entities.CoreProfile;
import net.claims.express.next2.entities.CoreUser;
import net.claims.express.next2.http.response.ApiResponse;
import net.claims.express.next2.services.CoreProfileService;
import net.claims.express.next2.services.CoreUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class ProfilesController {

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

    @GetMapping("/{userId}/all-profiles")
    public ApiResponse getAllProfilesForCompany(@PathVariable("userId") String userId) {
        CoreUser user = this.coreUserService.getCoreUser(userId);
      return this.coreUserService.getUserCompanyProfiles(user.getId());
    }

}
