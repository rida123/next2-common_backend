package net.claims.express.next2.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class DB {

@Autowired
public CarsNotificationRepository carsNotificationRepository;
}
