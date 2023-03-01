package net.claims.express.next2.repositories;

import net.claims.express.next2.entities.CoreUserPreference;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface CoreUserPreferenceRepository  extends  BaseRepository<CoreUserPreference,String>{
}
