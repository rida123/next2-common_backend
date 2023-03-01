package net.claims.express.next2.services;

import net.claims.express.next2.entities.CarsErrorlog;
import net.claims.express.next2.repositories.DB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarsErrorlogService {

    @Autowired
    DB db;

    public List<CarsErrorlog> findAll() {
        return this.db.errorlogRepository.findAll();
    }

}
