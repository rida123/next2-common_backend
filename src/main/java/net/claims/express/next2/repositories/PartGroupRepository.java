package net.claims.express.next2.repositories;

import net.claims.express.next2.views.PartGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface PartGroupRepository extends JpaRepository<PartGroup, Long> {

}
