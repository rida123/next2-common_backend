package net.claims.express.next2.repositories;

import net.claims.express.next2.views.VehicleSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;




@Repository
@EnableJpaRepositories
public interface VehicleSizeRepository  extends JpaRepository<VehicleSize, String>{
	

}
