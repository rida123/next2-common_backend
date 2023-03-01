package net.claims.express.next2.services;

import net.claims.express.next2.entities.CoreProfile;
import net.claims.express.next2.repositories.CoreProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoreProfileService extends BaseService<CoreProfile> {

    @Autowired
    CoreProfileRepository coreProfileRepository;

}
