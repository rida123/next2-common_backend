package net.claims.express.next2.repositories;

import net.claims.express.next2.entities.CarsTown;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface CarsTownRepository  extends BaseRepository<CarsTown,String>{


}
