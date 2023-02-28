package net.claims.express.next2.services;

import java.util.List;
import java.util.Optional;

import net.claims.express.next2.http.response.ApiResponse;
import net.claims.express.next2.repositories.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService<T> {

	@Autowired
	BaseRepository<T, String> baseRepository;

	public Optional<T> findById(String id) {
		return baseRepository.findById(id);
	}

	public void deleteById(String id) {
		baseRepository.deleteById(id);
	}

	public T save(T entity) {
		return baseRepository.save(entity);
	}

	public boolean existById(String id) {
		return baseRepository.existsById(id);
	}

	public T saveAndFlush(T entity) {
		return baseRepository.saveAndFlush(entity);
	}

	public <T> List<T> findAll() {
		return (List<T>) baseRepository.findAll();
	}

}
