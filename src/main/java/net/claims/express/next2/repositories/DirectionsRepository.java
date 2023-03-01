package net.claims.express.next2.repositories;

import net.claims.express.next2.views.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;



@EnableJpaRepositories
@Repository
public interface  DirectionsRepository  extends JpaRepository<Direction, String> {

	
	

	
}
