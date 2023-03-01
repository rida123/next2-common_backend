package net.claims.express.next2.repositories;

import net.claims.express.next2.views.PolicyType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface PolicyTypeRepository extends BaseRepository<PolicyType,String> {
//    @Query("select  p from PolicyType p where code in  ('ALL','TPL','MOB')")
//    List<PolicyType> getPolicyType();

}
