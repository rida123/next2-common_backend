package net.claims.express.next2.services;

import com.sun.jdi.request.DuplicateRequestException;
import net.claims.express.next2.entities.*;
import net.claims.express.next2.exceptions.BadRequestException;
import net.claims.express.next2.exceptions.NotFoundException;
import net.claims.express.next2.http.StatusCode;
import net.claims.express.next2.http.requests.AddUserRequest;
import net.claims.express.next2.http.response.ApiResponse;
import net.claims.express.next2.http.response.MyBaseResponse;
import net.claims.express.next2.http.response.UserInfo;
import net.claims.express.next2.repositories.*;
import net.claims.express.next2.security.services.PwdEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class CoreUserService extends BaseService<CoreUser> {
    @Autowired
    private DB db;

    @Autowired
    CarsInsuranceEmployeeService carsInsuranceEmployeeService;
    @Autowired
    private PwdEncoder passwordEncoder;

    @Autowired
    private CoreCompanyProfileRepository coreCompanyProfileRepository;

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
    public ApiResponse grantProfile(String coreUserId, String profileId) {
        // ----Audit info: current user using the system
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println("current user using the system is: " + currentPrincipalName);
        // ---end Audit info:

        //1-if coreUserId is wrong => getEmployeeInfo will throw an exception
        CarsInsuranceEmployee employeeInfo = getEmployeeInfo(coreUserId);
        //2- if profileId is wrong => throw exception
        CoreProfile checkProfile = this.getCoreProfile(profileId);


        //now: correct parameters({pathVariables} => proceed to grant user)
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
        CoreUser user = this.getCoreUser(coreUserId);
        //exception handling is done in upper part: checkProfile
        CoreCompanyProfile profileToAdd = optionalFoundProfile.get();

        List<CoreCompanyProfile> original_companyProfiles = user.getProfiles();

        if (!original_companyProfiles.contains(profileToAdd)) {
            CoreUserProfile addedUserProfile = addUserProfile(coreUserId, profileToAdd, currentPrincipalName);
        } else {
            throw new DuplicateRequestException("profile: " + profileToAdd.getCoreProfile().getName() + " already exist");
        }
        //return updated list of CoreProfiles for user after new profile grant:
        List<CoreProfile> updatedProfiles = this.getProfilesPerUser(coreUserId);
        return new ApiResponse(StatusCode.OK.getCode(), "success", "profile has been created successfully.", updatedProfiles);
    }

    /**
     * this function will return all company profiles that are not granted for a given user yet.
     * this function is used in the front-end where we want to add a profile for a user, so we show list
     * of company profiles missing to that user
     * @param userId
     * @return
     */
    public ApiResponse getMissingCompanyProfilesByUser(String userId) {
        CarsInsuranceEmployee employeeInfo = this.getEmployeeInfo(userId);
        String companyId = employeeInfo.getInsuranceEmployeeId().substring(0, employeeInfo.getInsuranceEmployeeId().indexOf("."));

        List<CoreCompanyProfile> unGrantedCompanyProfiles = new ArrayList<>();
        List<CoreProfile> unGrantedProfiles = new ArrayList<>();
List<MyBaseResponse> myBaseResponses = new ArrayList<>();
        List<CoreProfile> companyProfiles = this.companyProfileService.getProfilesByCompany(companyId);
//        List<CoreUserProfile> registeredProfiles = this.coreUserProfileService.getUserProfiles(userId);
        List<String> registeredProfiles =new ArrayList<>();
                this.coreUserProfileService.getUserProfiles(userId).forEach( p -> {
                    String companyProfile = p.getCoreCompanyProfileId();
                    registeredProfiles.add(companyProfile.substring(companyProfile.indexOf(".") + 1));
        });
        unGrantedCompanyProfiles = this.coreCompanyProfileRepository.findByCoreCompanyIdAndNotWithinUserProfiles(companyId, registeredProfiles);
       unGrantedCompanyProfiles.forEach( p -> {
           String company_profile_id = p.getId();
           unGrantedProfiles.add(this.profileService.findById(company_profile_id.substring(company_profile_id.indexOf(".") + 1)).get());
        });

        /*for (CoreProfile p : companyProfiles) {

        }*/
        unGrantedProfiles.forEach(
                coreProfile->{
                    MyBaseResponse myBaseResponse = new MyBaseResponse();
                    myBaseResponse.setCode(coreProfile.getCode());
                    myBaseResponse.setDescription(coreProfile.getDescription());
                    myBaseResponses.add(myBaseResponse);
                }
        );
        return new ApiResponse(StatusCode.OK.getCode(), "success", "Company profiles not granted for user " +userId  , myBaseResponses);
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

        return this.coreUserProfileService.saveAndFlush(coreUserProfile);
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
    public ApiResponse revokeProfile(String userId, String profileId) {
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

        CoreUser core_user = this.getCoreUser(userId);
        CoreCompanyProfile profileToRemove = this.getCompanyProfile(profileId, insurance_companyId);

        List<CoreCompanyProfile> original_companyProfiles = core_user.getProfiles();


        if (original_companyProfiles.contains(profileToRemove)) {
            //profile to remove from core_user_profile table:
            String userProfileId = core_user.getId() + "." + profileToRemove.getId();
            this.coreUserProfileService.deleteById(userProfileId);
        } else {
            System.out.println("user doesn't have this profile as first");
            throw new NotFoundException("profile: " + profileToRemove.getCoreProfile().getName() + " user doen't have this profile to remove");
        }

        //now get list of profiles for this user after revoking certain profile
        List<CoreProfile> remainingProfiles = this.getProfilesPerUser(userId);

        return new ApiResponse(StatusCode.OK.getCode(), "success", "profile has been removed successfully.", remainingProfiles);
    }

    /**
     * get all profiles that a user is enrolled in
     *
     * @param userId
     * @return
     */
    public List<CoreProfile> getProfilesPerUser(String userId) {

        CoreUser foundCoureUser = this.getCoreUser(userId);

        /*testing:
        System.out.println("profiles that i have:");
        for (CoreCompanyProfile p: foundCoureUser.getProfiles()) {
            System.out.println("code: " + p.getId());
        }*/
        List<CoreUserProfile> registeredProfiles = this.coreUserProfileService.getUserProfiles(foundCoureUser.getId());

        List<CoreProfile> myCoreProfiles = new ArrayList<>();

        for (CoreUserProfile core_user_profile : registeredProfiles) {
            String coreCompanyProfileId = core_user_profile.getCoreCompanyProfileId();
            //now fetch profile_id:
            String coreProfileId = (coreCompanyProfileId.substring(coreCompanyProfileId.indexOf(".") + 1));
            //now fetch CoreProfile object consisting of all roles for this profile part of these roles
            // are granted to the user and others not granted yet
            CoreProfile coreProfile = this.getCoreProfile(coreProfileId);

            Set<CoreRole> allRolesPerProfile = coreProfile.getProfileRoles();

            if (core_user_profile.getUserRoles() != null) {
                for (CoreRole granted_role : core_user_profile.getUserRoles()) {
                    for (CoreRole role : allRolesPerProfile) {
                        if (granted_role.equals(role)) {
                            role.setGranted(true);
                            break;
                        }
                    }
                }
            }

            coreProfile.setRoles(allRolesPerProfile);
            myCoreProfiles.add(coreProfile);
        }
        System.out.println("end looping through map");
        return myCoreProfiles;
    }

    public CoreUser getCoreUser(String coreUserId) {
        Optional<CoreUser> checkUser = this.coreUserRepository.findById(coreUserId);
        CoreUser user = checkUser.orElseThrow(() -> new NotFoundException("user of ID: " + coreUserId + " doesn't exist"));
        return user;
    }

    public CoreProfile getCoreProfile(String profileId) {
        Optional<CoreProfile> checkProfile = this.profileService.findById(profileId);
        CoreProfile profile = checkProfile.orElseThrow(() -> new BadRequestException("profile of ID: " + profileId + " doesn't exist"));
        return profile;
    }

    @Transactional
    public ApiResponse updateRoles(String userId, CoreProfile profile_with_updateRoles) {
        // ----Audit info: current user using the system
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        CoreUser user = this.getCoreUser(userId); //1


        CarsInsuranceEmployee employeeInfo = getEmployeeInfo(userId); //2- employee-company information

        //correct parameters(pathVariables => proceed to grant user)
        //usersInsurance in the database represents company that user works for


        String insuranceEmpId = employeeInfo.getInsuranceEmployeeId();
        String insurance_companyId = insuranceEmpId.substring(0, insuranceEmpId.indexOf("."));


        Optional<CoreCompany> optionalCoreCompany = this.companyService.findById(insurance_companyId);


        /*now get the CoreCompanyProfile that represents the profile we want to update its roles and if its
         * not provided to the company yet, we send an error*/

        CoreCompanyProfile foundCompanyProfile = this.getCompanyProfile(profile_with_updateRoles.getId(), insurance_companyId);

        Optional<CoreUserProfile> optionalCoreUserProfile =
                this.coreUserProfileService.findById(userId + "." + foundCompanyProfile.getId());

        if (optionalCoreUserProfile.isEmpty()) {
            //We throw an exception  because user is supposed to have this profile as prerequisite in order
            // to modify his roles:
            throw new BadRequestException("User must have " + foundCompanyProfile.getId());
        } else {
            //user has this profile and our job is to update his roles as modified in front-end app
            CoreUserProfile userProfile = optionalCoreUserProfile.get();
            Set<CoreRole> userProfileRoles = userProfile.getUserRoles();

            System.out.println("-------------testing results roles-----------");
            System.out.println("count of roles: " + userProfileRoles.size());

            CoreUserProfilePerm perm;
            for (CoreRole role : profile_with_updateRoles.getProfileRoles()) {
                System.out.println("role: " + role.getDescription() + ", granted => " + role.getGranted());
                if (role.getGranted() == true) { //if granted = true and not already found in core_user_profile_perm tbl
                    if (!this.coreUserProfilePermService.existById(userProfile.getId() + "." + role.getId())) {
                        perm = new CoreUserProfilePerm();
                        perm.setId(userProfile.getId() + "." + role.getId());
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
                } else {
                    //revoke any role that is has granted = false;
                    if (this.coreUserProfilePermService.existById(userProfile.getId() + "." + role.getId())) {
                        this.coreUserProfilePermService.deleteById(userProfile.getId() + "." + role.getId());
                    }
                }
            }//end looping through roles:
            return new ApiResponse(StatusCode.OK.getCode(), "success", "Roles updated successfully.", this.getProfilesPerUser(userId));

        }

    }

    public CoreCompanyProfile getCompanyProfile(String profileId, String companyId) {
        return this.companyProfileService.
                findByCoreProfileAndCoreCompany(profileId, companyId).orElseThrow(() -> new NotFoundException("company profile of ID: " + profileId + " doesn't exist"));

    }


    public ApiResponse addUser(AddUserRequest addUserRequest) {
        Optional<CoreUser> coreUserOptional = db.coreUserRepository.findById(addUserRequest.getUserName());
        ApiResponse apiResponse = new ApiResponse();
        coreUserOptional.ifPresentOrElse((value)
                        -> {

                    apiResponse.setData(value);
                    apiResponse.setStatusCode(StatusCode.FAILED.getCode());
                    apiResponse.setMessage("User already exist.");
                    apiResponse.setTitle("failed");

                },
                ()
                        -> {

                    CoreUser coreUser = new CoreUser();
                    coreUser.setCompany_id(addUserRequest.getCompanyId());
                    coreUser.setId(addUserRequest.getUserName());
                    String encoded_input_password = this.passwordEncoder.passwordEncoder().encode(addUserRequest.getPassword());
                    coreUser.setActiveFlag(1);
                    coreUser.setEncryptedPwd(encoded_input_password);
                    coreUser.setSysCreatedDate(LocalDateTime.now());
                    coreUser.setSysVersionNumber(1l);
                    coreUser.setSysCreatedBy("anonymous");
                    coreUser.setSysUpdatedDate(LocalDateTime.now());

                    CoreUser savedCoreUser = db.coreUserRepository.save(coreUser);
                    CarsInsuranceEmployee carsInsuranceEmployee = new CarsInsuranceEmployee();
                    carsInsuranceEmployee.setUsersState("RL");
                    carsInsuranceEmployee.setUsersAbrev(addUserRequest.getFirstName().charAt(0) + "" + addUserRequest.getLastName().charAt(0));
                    carsInsuranceEmployee.setUsersCode(addUserRequest.getUserName());
                    carsInsuranceEmployee.setUsersBranch(new BigDecimal(addUserRequest.getBranchId()));
                    carsInsuranceEmployee.setUsersInsurance(new BigDecimal(addUserRequest.getCompanyId()));
                    carsInsuranceEmployee.setUsersBranchId(addUserRequest.getCompanyId() + "." + addUserRequest.getBranchId());
                    carsInsuranceEmployee.setInsuranceEmployeeId(addUserRequest.getCompanyId() + "." + addUserRequest.getBranchId() + "." + addUserRequest.getUserName());
                    carsInsuranceEmployee.setSysCreatedDate(LocalDateTime.now());

                    carsInsuranceEmployee.setUserLimitLawyerFees(addUserRequest.getUserLimitLawyerFees());
                    carsInsuranceEmployee.setUserLimitDoctorFees(addUserRequest.getUserLimitDoctorFees());
                    carsInsuranceEmployee.setUserLimitHospitalFees(addUserRequest.getUserLimitHospitalFees());
                    carsInsuranceEmployee.setUserLimitExpertFees(addUserRequest.getUserLimitExpertFees());
                    carsInsuranceEmployee.setUserLimitSurveyFees(addUserRequest.getUserLimitSurveyFees());
                    carsInsuranceEmployee.setUserLimitExceedPercentage(addUserRequest.getUserLimitExceedPercentage());
                    carsInsuranceEmployee.setUsersLimit(addUserRequest.getPaymentLimit());

                    carsInsuranceEmployee.setSysCreatedDate(LocalDateTime.now());
                    carsInsuranceEmployee.setSysVersionNumber(1l);
                    carsInsuranceEmployee.setSysCreatedBy("anonymous");
                    carsInsuranceEmployee.setSysUpdatedDate(LocalDateTime.now());

                    carsInsuranceEmployee.setUserLimitRecovery(addUserRequest.getRecoverLimit());
                    db.carsInsuranceEmployeeRepository.save(carsInsuranceEmployee);

                    CoreUserPreference coreUserPreference = new CoreUserPreference();
                    coreUserPreference.setCoreUser(savedCoreUser);
                    coreUserPreference.setUserEmail(addUserRequest.getEmail());
                    coreUserPreference.setLocale("en");
                    coreUserPreference.setSysCreatedDate(LocalDateTime.now());


                    coreUserPreference.setSysVersionNumber(1l);
                    coreUserPreference.setSysCreatedBy("anonymous");
                    coreUserPreference.setSysUpdatedDate(LocalDateTime.now());
                    coreUserPreference.setLastLoginDate(LocalDateTime.now());

                    Optional<CoreCompany> coreCompanyOptional = db.coreCompanyRepository.findById(String.valueOf(addUserRequest.getCompanyId()));
                    coreCompanyOptional.ifPresentOrElse(
                            (value)
                                    -> {
                                coreUserPreference.setCoreCompany(value);
                                coreUserPreference.setCompanyName(value.getName());

                                apiResponse.setStatusCode(StatusCode.OK.getCode());
                                apiResponse.setMessage("User inserted.");
                                apiResponse.setTitle("success");
                            },
                            ()
                                    -> {


                                apiResponse.setStatusCode(StatusCode.FAILED.getCode());
                                apiResponse.setMessage("Company not found.");
                                apiResponse.setTitle("failed");
                            }


                    );

                    coreUserPreference.setSkinId("coreSkinSmall");
                    coreUserPreference.setDisplayName(addUserRequest.getFirstName() + " " + addUserRequest.getLastName());
                    db.coreUserPreferenceRepository.save(coreUserPreference);

                }

        );


        return apiResponse;
    }

    public ApiResponse updateUser(String userId, int active) {
        ApiResponse apiResponse = new ApiResponse();
        Optional<CoreUser> coreUserOptional = db.coreUserRepository.findById(userId);
        coreUserOptional.ifPresentOrElse(
                (coreUser)->{
                    coreUser.setSysUpdatedDate(LocalDateTime.now());
                    coreUser.setActiveFlag(active);
                    db.coreUserRepository.save(coreUser);
                    apiResponse.setStatusCode(StatusCode.OK.getCode());
                    apiResponse.setTitle("success");
                    apiResponse.setTitle(" active status was changed");
                },
                ()->{
                  apiResponse.setStatusCode(StatusCode.FAILED.getCode());
                  apiResponse.setTitle("User not found");
                    apiResponse.setTitle("Invalid User ");

                }
        );
        return  apiResponse ;
    }



    public ApiResponse searchUser(String username, String name) {
        ApiResponse apiResponse = new ApiResponse();
        List <UserInfo> userInfoList= new ArrayList<>();
        if((username==null || username.isEmpty()) && (name==null||name.isEmpty())){
           List<CoreUser>coreUsers = db.userRepository.findAll();
           coreUsers.forEach((coreUser)->{
               UserInfo userInfo = new UserInfo();

               userInfo.setActive(coreUser.getActiveFlag());
               userInfo.setUserName(coreUser.getId());
            //   userInfo.setBranchId(coreUser.getCompany_id());
               if(coreUser.getId()!=null){
               Optional <CarsInsuranceEmployee>carsInsuranceEmployeeOptional  = db.carsInsuranceEmployeeRepository.findByUsersCode(coreUser.getId());
               carsInsuranceEmployeeOptional.ifPresentOrElse(
                       (carsInsuranceEmployee)->{
                           userInfo.setPaymentLimit(carsInsuranceEmployee.getUsersLimit());
                           userInfo.setRecoverLimit(carsInsuranceEmployee.getUserLimitRecovery());
                           userInfo.setUserLimitDoctorFees(carsInsuranceEmployee.getUserLimitDoctorFees());
                           userInfo.setUserLimitLawyerFees(carsInsuranceEmployee.getUserLimitLawyerFees());
                           userInfo.setUserLimitHospitalFees(carsInsuranceEmployee.getUserLimitHospitalFees());
                           userInfo.setUserLimitSurveyFees(carsInsuranceEmployee.getUserLimitSurveyFees());
                           userInfo.setUserLimitTaxiFees(carsInsuranceEmployee.getUserLimitTaxiFees());
                           userInfo.setUserLimitExpertFees(carsInsuranceEmployee.getUserLimitExpertFees());
                           userInfo.setUserLimitExceedPercentage(carsInsuranceEmployee.getUserLimitExceedPercentage());
                           if(!carsInsuranceEmployee.getUsersBranchId().isEmpty()&&carsInsuranceEmployee.getUsersBranchId()!=null){
                           userInfo.setBranchId(carsInsuranceEmployee.getUsersBranchId().split(".")[1]);
                           }

                       },
                       ()->{

                       }


               );

               }

               Optional <CoreUserPreference> coreUserPreferenceOptional  = db.coreUserPreferenceRepository.findByCoreUser(coreUser.getId());

               coreUserPreferenceOptional.ifPresentOrElse(
                       (coreUserPreference)->{
                userInfo.setEmail(coreUserPreference.getUserEmail());
               userInfo.setCompanyId(coreUserPreference.getCoreCompany().getId());
               userInfo.setCompanyDescription(coreUserPreference.getCoreCompany().getLegalName());
               userInfo.setDisplayName(coreUserPreference.getDisplayName());




                       },
                       ()->{

                       }


               );




               userInfoList.add(userInfo);
           });
            apiResponse.setData(userInfoList);

        }

            return apiResponse;
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
