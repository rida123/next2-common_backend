package net.claims.express.next2.repositories;

import java.util.List;

import net.claims.express.next2.entities.CarTrademark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


@Repository
@EnableJpaRepositories
public interface CarTrademarkRepository extends JpaRepository<CarTrademark, Long> {
	@Query("select t from CarTrademark t  join fetch  t.carBrand cb where cb.carBrandId = ?1 ")
	List<CarTrademark> getTrademarksbyBrandId(String brandId);

}
