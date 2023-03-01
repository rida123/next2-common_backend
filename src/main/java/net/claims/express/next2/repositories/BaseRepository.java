package net.claims.express.next2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface BaseRepository<T, U>  extends JpaRepository<T, String> {

}
