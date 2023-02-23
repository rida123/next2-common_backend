package net.claims.express.next2.controllers;

import net.claims.express.next2.entities.CoreCompanyProfile;
import net.claims.express.next2.entities.CoreProfile;
import net.claims.express.next2.entities.CoreRole;
import net.claims.express.next2.entities.CoreUser;
import net.claims.express.next2.http.StatusCode;
import net.claims.express.next2.http.response.ApiResponse;
import net.claims.express.next2.security.model.SecurityUser;
import net.claims.express.next2.security.services.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
//@CrossOrigin(origins = "*")

@RequestMapping("api/basicAuth")
public class ValidateUserController {

    @Autowired
    JWTService jwtService;




    //start from melhem

    //end from melhem

    @RequestMapping("validate")
    public ApiResponse userIsValid() {
        System.out.println("@Validate controller....");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("coming user::"   + auth.getPrincipal());
        SecurityUser currentUser = (SecurityUser)auth.getPrincipal();
        String name = currentUser.getUsername();
        System.out.println("@Controller: username ==> " + name);

        List<String> loginUserAuthoritiesNames = new ArrayList<>();

        for (GrantedAuthority authority: currentUser.getAuthorities()) {
            loginUserAuthoritiesNames.add(authority.getAuthority());
        }

        //getting corecompany profiles:
       CoreUser coreUser = currentUser.getCoreUser();
        System.out.println("listing my profiles:");
        List<CoreCompanyProfile> myCompanyProfiles = coreUser.getProfiles();

        //preparing EMPTY array list to hold profiles that I can use within my company
        List<CoreProfile> myProfiles = new ArrayList<>();

        for(CoreCompanyProfile companyProfile: myCompanyProfiles) {
            System.out.println("@PROFILE => " + companyProfile.getId());

            CoreProfile coreProfile = companyProfile.getCoreProfile();
            //now inject roles for this user for this core profile:
            Set<CoreRole> roles_per_profile =  currentUser.getUserRolesPerProfile().get(coreUser.getCompany_id() + "." +  coreProfile.getId());
            coreProfile.setRoles(roles_per_profile);
            myProfiles.add(coreProfile);
        }

        System.out.println("FINAL TESTING###");
        System.out.println("for each company profile, list roles that user: " + coreUser.getId() + " has:");
        //TESTING DATA:::
        for (CoreProfile p: myProfiles) {
            System.out.println("PROFILE: " +p.getDescription() + ", user:  " + coreUser.getId() + " has the following roles:");
            System.out.println("----------");
            for (CoreRole r: p.getProfileRoles()) {
                System.out.println("     ROLE: " + r.getDescription());
            }
        }
        System.out.println("done final testing...");
        System.out.println("authoriteis for user: " + name);
        for(String authority : loginUserAuthoritiesNames) {
            System.out.println("authority: " + authority);
        }

        //generate token using configured jwtToken Service:
        String token = this.jwtService.generateToken(name, loginUserAuthoritiesNames);

        Map<String, String> results = new HashMap<>();
        results.put("results", token);

//        return myProfiles;
//        return results;
   //     return "{\"result\": \"ok\", \"name\": \"ok\"}";
        Map<String, Object> afterLogin_data = new HashMap<>();
        afterLogin_data.put("token", token);
//        afterLogin_data.put("profiles", myProfiles);
       return new  ApiResponse(StatusCode.OK.getCode(), "success", "login data", afterLogin_data);
    }

    @RequestMapping("ok")
    public String testingEndpoint() {
        return "testing okay (y)";
    }
}
