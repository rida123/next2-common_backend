package net.claims.express.next2.repositories;

import net.claims.express.next2.entities.CoreCompany;
import net.claims.express.next2.entities.CoreCompanyProfile;
import net.claims.express.next2.entities.CoreProfile;

import java.util.List;
import java.util.Optional;

public interface CoreCompanyProfileRepository extends BaseRepository<CoreCompanyProfile, String> {

    Optional<CoreCompanyProfile> findByCoreProfileAndCoreCompany(CoreProfile p, CoreCompany c);

}
