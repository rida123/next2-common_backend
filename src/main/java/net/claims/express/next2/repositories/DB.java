package net.claims.express.next2.repositories;

import net.claims.express.next2.http.response.DoorsListResponse;
import net.claims.express.next2.services.CarsErrorlogService;
import net.claims.express.next2.services.ConstantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class DB {

    @Autowired
    public CarsNotificationRepository carsNotificationRepository;

    @Autowired
    public CoreUserRepository userRepository;
    @Autowired
    public CarsTownRepository carsTownRepository;

    @Autowired
    public CarsErrorlogRepository errorlogRepository;
    @Autowired
    public CarsSupplierRepository carsSupplierRepository;
    @Autowired
    public PolicyTypeRepository policyTypeRepository;
    @Autowired
    public DirectionsRepository directionsRepository;
    @Autowired
    public PartGroupRepository partGroupRepository;
    @Autowired
    public LitigationRepository litigationRepository;

    @Autowired
    public VehicleSizeRepository vehicleSizeRepository;

    @Autowired
    public BodyTypeRepository bodyTypeRepository;
    @Autowired
    public DoorsRepository doorsRepository;
    @Autowired
    public CarTrademarkRepository carTrademarkRepository;
    @Autowired
    public CarBrandRepository carBrandRepository;

    @Autowired
    public CompanyRepository companyRepository;
}
