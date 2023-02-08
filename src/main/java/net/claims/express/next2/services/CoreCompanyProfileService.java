package net.claims.express.next2.services;

import net.claims.express.next2.entities.CoreCompany;
import net.claims.express.next2.entities.CoreCompanyProfile;
import net.claims.express.next2.entities.CoreProfile;
import net.claims.express.next2.exceptions.BadRequestException;
import net.claims.express.next2.exceptions.NotFoundException;
import net.claims.express.next2.repositories.CoreCompanyProfileRepository;
import net.claims.express.next2.repositories.CoreCompanyRepository;
import net.claims.express.next2.repositories.CoreProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoreCompanyProfileService extends BaseService<CoreCompanyProfile> {

    @Autowired
    CoreCompanyProfileRepository companyProfileRepository;

    @Autowired
    CoreProfileRepository profileRepository;

    @Autowired
    CoreCompanyRepository companyRepository;


    public CoreCompany findCompanyById(String companyId) {
        Optional<CoreCompany>  optional_company = companyRepository.findById(companyId);
        CoreCompany  foundCompany = optional_company.orElseThrow( () -> new NotFoundException("company of ID: " + companyId + " doesn't exist") );
        return foundCompany;
    }

   public Optional<CoreCompanyProfile> findByCoreProfileAndCoreCompany(String profileId, String companyId) {

       Optional<CoreProfile>  optional_profile = profileRepository.findById(profileId);
       CoreProfile foundProfile = optional_profile.orElseThrow( () -> new NotFoundException("profile of ID: " + profileId + " doesn't exist") );

        CoreCompany company = this.findCompanyById(companyId);

       Optional<CoreCompanyProfile> optionalCompanyProfile =  companyProfileRepository.findByCoreProfileAndCoreCompany(foundProfile, company);

        return  optionalCompanyProfile;
    }

    public List<CoreProfile> getProfilesByCompany(String companyId) {
      CoreCompany company = this.findCompanyById(companyId);

      return company.getCompanyProfiles();
       }

}

