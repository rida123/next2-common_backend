package net.claims.express.next2.services;

import com.sun.jdi.request.DuplicateRequestException;
import net.claims.express.next2.entities.*;
import net.claims.express.next2.exceptions.BadRequestException;
import net.claims.express.next2.exceptions.NotFoundException;
import net.claims.express.next2.http.StatusCode;
import net.claims.express.next2.http.response.ApiResponse;
import net.claims.express.next2.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CoreUserService  extends BaseService<CoreUser> {
    @Autowired
    private DB db;

    @Autowired
    CarsInsuranceEmployeeService carsInsuranceEmployeeService;

    @Autowired
    private CoreCompanyProfileService companyProfileService;

    @Autowired
    private CoreCompanyService companyService;

    @Autowired
    private CoreProfileService profileService;
    @Autowired
    private CoreUserRepository coreUserRepository;

    @Autowired
    private CoreUserProfileService coreUserProfileService;
    @Autowired
    private CoreCompanyRepository coreCompanyRepository;

    //old params: (String coreUserId, CoreCompanyProfile profile)
    @Transactional
    public ApiResponse grantProfile(String coreUserId, String profileId ) {
        // ----Audit info: current user using the system
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println("current user using the system is: " + currentPrincipalName);
        // ---end Audit info:
        Optional<CarsInsuranceEmployee> optionalEmployee = this.carsInsuranceEmployeeService.findByUsersCode(coreUserId);
        //1-if coreUserId is wrong => throw exception
        CarsInsuranceEmployee employeeInfo = optionalEmployee.orElseThrow(() -> new BadRequestException("user of ID: " + coreUserId + " doesn't exist"));
        //2- if profileId is wrong => throw exception

        Optional<CoreProfile> checkProfile = this.profileService.findById(profileId);
        if (checkProfile.isEmpty()) {
            throw new BadRequestException("No profile with id: " + profileId + " exists");
        }

        //correct parameters(pathVariables => proceed to grant user)
        //usersInsurance in the database represents company that user works for
        String company_branch_info = employeeInfo.getUsersBranchId();
        String insurance_companyId = String.valueOf(employeeInfo.getUsersBranchId().substring(0, company_branch_info.indexOf(".")));
        Optional<CoreCompany> optionalCoreCompany = this.companyService.findById(insurance_companyId);

        /*now get the CoreCompanyProfile that represents the profile we want to grant to
        the user using the insurance_companyId and core_profile id*/

        Optional<CoreCompanyProfile> optionalFoundProfile = this.companyProfileService.findByCoreProfileAndCoreCompany(profileId, insurance_companyId);
        if (optionalFoundProfile.isEmpty()) {
            throw new NotFoundException("Company " + optionalCoreCompany.get().getName() + " has not been granted " + profileId + " yet.");
        }


        //end from controller
        //1- get this user profiles, if profileToAdd is not here => add
        Optional<CoreUser> optionalCoreUser = this.coreUserRepository.findById(coreUserId);


        CoreUser user = optionalCoreUser.get(); //exception handling is done in upper part: optionalEmployee
        //exception handling is done in upper part: checkProfile
        CoreCompanyProfile profileToAdd = optionalFoundProfile.get();

        List<CoreCompanyProfile> original_companyProfiles = user.getProfiles();

        //testing:
        /*for (CoreCompanyProfile p: original_companyProfiles) {
            System.out.println("company profile id: " + p.getId());
        }*/
        //end testing
        if (!original_companyProfiles.contains(profileToAdd)) {

            String userProfileId = coreUserId + "." + profileToAdd.getId();
            CoreUserProfile coreUserProfile = new CoreUserProfile();
            coreUserProfile.setId(userProfileId);
            coreUserProfile.setFullFlag(1);
            coreUserProfile.setCoreUserId(coreUserId);
            coreUserProfile.setCoreCompanyProfileId(profileToAdd.getId());
            coreUserProfile.setSysVersionNumber(1L);
            //set audit info:
            coreUserProfile.setSysCreatedDate(LocalDateTime.now());
            coreUserProfile.setSysCreatedBy(currentPrincipalName);
            coreUserProfile.setSysUpdatedDate(LocalDateTime.now());
            coreUserProfile.setSysUpdatedBy(currentPrincipalName);

            this.coreUserProfileService.save(coreUserProfile);

            //save is done manually because fields like id in coreUserProfile is dynamically generated
            //old work: boolean result = user.getProfiles().add(profile);
            //old work:     profile.getCoreUsers().add(user);
        } else {
            throw new DuplicateRequestException("profile: " + profileToAdd.getCoreProfile().getName() + " already exist");
        }
        return new ApiResponse(StatusCode.OK.getCode(), "success", "profile has been created successfully.", profileToAdd);
    }

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
