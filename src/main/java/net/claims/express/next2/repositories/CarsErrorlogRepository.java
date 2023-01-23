package net.claims.express.next2.repositories;

import net.claims.express.next2.entities.CarsErrorlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface CarsErrorlogRepository extends JpaRepository<CarsErrorlog, String> {
}