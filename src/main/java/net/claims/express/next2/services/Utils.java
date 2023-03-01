package net.claims.express.next2.services;

import net.claims.express.next2.entities.CoreRole;
import net.claims.express.next2.entities.CoreUserProfile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Utils {

    public static Map<String, Set<CoreRole>> getRolesForInvolvedProfiles(List<CoreUserProfile> coreUser_companyProfiles) {
        Map<String, Set<CoreRole>> userRolesPerProfile = new HashMap<>();

        for(CoreUserProfile profile: coreUser_companyProfiles) {
            userRolesPerProfile.put(profile.getCoreCompanyProfileId(), profile.getUserRoles());
            System.out.println("@@@_" + profile.getCoreCompanyProfileId());
        }
        return userRolesPerProfile;
    }
}
