package net.claims.express.next2.repositories;

import net.claims.express.next2.views.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository
@EnableJpaRepositories
public interface LitigationRepository  extends JpaRepository<Reviews, String> {

}
