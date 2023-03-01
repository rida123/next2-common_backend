package net.claims.express.next2.repositories;

import net.claims.express.next2.entities.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


@Repository
@EnableJpaRepositories
public interface CarBrandRepository extends BaseRepository<CarBrand, Long> {

}
