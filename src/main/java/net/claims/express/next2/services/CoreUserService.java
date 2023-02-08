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
import java.time.LocalDate;
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
    private CoreUserProfilePermService coreUserProfilePermService;
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

        //1-if coreUserId is wrong => getEmployeeInfo will throw an exception
        CarsInsuranceEmployee employeeInfo = getEmployeeInfo(coreUserId);
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
            CoreUserProfile addedUserProfile = addUserProfile(coreUserId, profileToAdd, currentPrincipalName);
        } else {
            throw new DuplicateRequestException("profile: " + profileToAdd.getCoreProfile().getName() + " already exist");
        }
        return new ApiResponse(StatusCode.OK.getCode(), "success", "profile has been created successfully.", profileToAdd);
    }

    /**
     * we get company id from user
     * @param userId
     * @return
     */
    public ApiResponse getUserCompanyProfiles(String userId) {
        CarsInsuranceEmployee employeeInfo = this.getEmployeeInfo(userId);
        String companyId = employeeInfo.getInsuranceEmployeeId().substring(0, employeeInfo.getInsuranceEmployeeId().indexOf("."));

        List<CoreProfile> companyProfiles = this.companyProfileService.getProfilesByCompany(companyId);
        return new ApiResponse(StatusCode.OK.getCode(), "success", "Company profiles returned successfully.", companyProfiles);
    }

    @Transactional
    CoreUserProfile addUserProfile(String coreUserId, CoreCompanyProfile profileToAdd, String loginUser) {

        String userProfileId = coreUserId + "." + profileToAdd.getId();
        CoreUserProfile coreUserProfile = new CoreUserProfile();
        coreUserProfile.setId(userProfileId);
        coreUserProfile.setFullFlag(1);
        coreUserProfile.setCoreUserId(coreUserId);
        coreUserProfile.setCoreCompanyProfileId(profileToAdd.getId());
        coreUserProfile.setSysVersionNumber(1L);
        //set audit info:
        coreUserProfile.setSysCreatedDate(LocalDateTime.now());
        coreUserProfile.setSysCreatedBy(loginUser);
        coreUserProfile.setSysUpdatedDate(LocalDateTime.now());
        coreUserProfile.setSysUpdatedBy(loginUser);

       return this.coreUserProfileService.save(coreUserProfile);
        //todo: NOTE:    //save is done manually because fields like id in coreUserProfile is dynamically generated
        //            //old work: boolean result = user.getProfiles().add(profile);
        //            //old work:     profile.getCoreUsers().add(user);
    }

    public CarsInsuranceEmployee getEmployeeInfo(String coreUserId) {
        Optional<CarsInsuranceEmployee> checkEmployee = this.carsInsuranceEmployeeService.findByUsersCode(coreUserId);
        CarsInsuranceEmployee employeeInfo = checkEmployee.orElseThrow(() -> new BadRequestException("user of ID: " + coreUserId + " doesn't exist"));
        return employeeInfo;
    }
    @Transactional
    public ApiResponse denyProfile(String userId, String profileId) {
        CoreUser user = this.getCoreUser(userId);
        Optional<CoreProfile> checkProfile = this.profileService.findById(profileId);
        if (checkProfile.isEmpty()) {
            throw new BadRequestException("No profile with id: " + profileId + " exists");
        }
        //start
        CarsInsuranceEmployee employeeInfo = getEmployeeInfo(userId);

        //correct parameters(pathVariables => proceed to grant user)
        //usersInsurance in the database represents company that user works for
        String insurance_companyId = String.valueOf(employeeInfo.getUsersInsurance());
        Optional<CoreCompany> optionalCoreCompany = this.companyService.findById(insurance_companyId);

        /*now get the CoreCompanyProfile that represents the profile we want to grant to
        the user using the insurance_companyId and core_profile id*/

        Optional<CoreCompanyProfile> optionalFoundProfile = this.companyProfileService.findByCoreProfileAndCoreCompany(profileId, insurance_companyId);
        if (optionalFoundProfile.isEmpty()) {
            throw new NotFoundException("Company " + optionalCoreCompany.get().getName() + " has not been granted " + profileId + " yet.");
        }

        //end
        //start

        //1- get this user profiles, if profileToAdd is not here => add
        Optional<CoreUser> optionalCoreUser = this.coreUserRepository.findById(userId);


        CoreUser core_user = optionalCoreUser.get(); //exception handling is done in helper method:getEmployeeInfo()
        //exception handling is done in upper part: checkProfile
        CoreCompanyProfile profileToRemove = optionalFoundProfile.get();

        List<CoreCompanyProfile> original_companyProfiles = core_user.getProfiles();


        if (original_companyProfiles.contains(profileToRemove)) {
            //profile to remove from core_user_profile table:
            String userProfileId = core_user.getId() + "." + profileToRemove.getId();
           this.coreUserProfileService.deleteById(userProfileId);
        }
        else {
            System.out.println("user doesn't have this profile as first");
            throw new NotFoundException("profile: " + profileToRemove.getCoreProfile().getName() + " user doen't have this profile to remove");
        }

        return new ApiResponse(StatusCode.OK.getCode(), "success", "profile has been removed successfully.", null);
    }

    public CoreUser getCoreUser(String coreUserId) {
        Optional<CoreUser> checkUser = this.coreUserRepository.findById(coreUserId);
        CoreUser user = checkUser.orElseThrow( () -> new NotFoundException("user of ID: " + coreUserId + " doesn't exist") );
        return user;
    }

    public CoreProfile getCoreProfile(String profileId) {
        Optional<CoreProfile> checkProfile = this.profileService.findById(profileId);
        CoreProfile profile = checkProfile.orElseThrow( () -> new BadRequestException("profile of ID: " + profileId + " doesn't exist") );
        return profile;
    }

    @Transactional
    public ApiResponse updateRoles(String userId, CoreProfile coreProfile) {
        // ----Audit info: current user using the system
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        CoreUser user = this.getCoreUser(userId); //1


        CarsInsuranceEmployee employeeInfo = getEmployeeInfo(userId); //2- employee-company information

        //correct parameters(pathVariables => proceed to grant user)
        //usersInsurance in the database represents company that user works for
        String insurance_companyId = String.valueOf(employeeInfo.getUsersInsurance());

        Optional<CoreCompany> optionalCoreCompany = this.companyService.findById(insurance_companyId);


        /*now get the CoreCompanyProfile that represents the profile we want to update its roles and if its
        * not provided to the company yet, we send an error*/

        CoreCompanyProfile foundCompanyProfile = this.getCompanyProfile(coreProfile.getId(), insurance_companyId);

        Optional<CoreUserProfile> optionalCoreUserProfile =
                this.coreUserProfileService.findById(userId + foundCompanyProfile.getId());

        if(optionalCoreUserProfile.isEmpty()) {
            //We throw an exception  because user is supposed to have this profile as prerequisite in order
            // to modify his roles:
            throw new BadRequestException("User must have " + foundCompanyProfile.getId());
        } else {
            //user has this profile and our job is to update his roles as modified in front-end app
            CoreUserProfile userProfile = optionalCoreUserProfile.get();
            Set<CoreRole> myRoles = userProfile.getUserRoles();

            System.out.println("-----------------testing results roles-----------");
            System.out.println("count of roles: " + myRoles.size());

            CoreUserProfilePerm perm;
            for (CoreRole role: coreProfile.getProfileRoles()) {
                System.out.println("role: " + role.getDescription()  + ", granted => " + role.getGranted());
                if(role.getGranted() == true) {
                    perm = new CoreUserProfilePerm();
                    perm.setId(userProfile.getId() + role.getId());
                    perm.setCoreUserProfile(userProfile);
                    perm.setCoreRole(role);
                    String userProfile_taskFlow_perm = this.coreUserProfileService.getCoreProfTfPermId(role.getId(), userProfile.getId());
                    perm.setCoreProfileTaskflowPerm(userProfile_taskFlow_perm);
                    perm.setSysVersionNumber(1L);
                    perm.setSysCreatedDate(LocalDateTime.now());
                    perm.setSysCreatedBy(currentPrincipalName);
                    perm.setSysUpdatedBy(currentPrincipalName);
                    perm.setSysUpdatedDate(LocalDateTime.now());

                    this.coreUserProfilePermService.save(perm);
                }
                else {
                    //revoke any role that is has granted = false;
                    this.coreUserProfilePermService.deleteById(userProfile.getId() + role.getId());
                }
            }//end looping through roles:
            return new ApiResponse(StatusCode.OK.getCode(), "success", "Roles updated successfully.", coreProfile);
            
        }

    }

    public CoreCompanyProfile getCompanyProfile(String profileId, String companyId) {
       return  this.companyProfileService.
                    findByCoreProfileAndCoreCompany(profileId, companyId).orElseThrow( ()-> new NotFoundException("company profile of ID: " + profileId + " doesn't exist") );

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
