package net.claims.express.next2.repositories;

import net.claims.express.next2.entities.CoreResourceBundle;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface CoreResourceBundleRepository  extends BaseRepository<CoreResourceBundle,String>{

    List<CoreResourceBundle> findByLocale(String local);
}
