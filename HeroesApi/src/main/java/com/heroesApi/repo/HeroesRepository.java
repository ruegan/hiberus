package com.heroesApi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.heroesApi.model.Heroes;


@Repository
public interface HeroesRepository extends CrudRepository<Heroes, Integer>{
	
	@Query(value= "SELECT * FROM heroes WHERE UPPER(name) like %:title%", nativeQuery = true)
	List<Heroes> findByTitleContains(@Param("title") String title);
}
