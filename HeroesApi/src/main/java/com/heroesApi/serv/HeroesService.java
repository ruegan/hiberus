package com.heroesApi.serv;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heroesApi.model.Heroes;
import com.heroesApi.repo.HeroesRepository;



@Service
public class HeroesService {

	// @Autowired annotation provides the automatic dependency injection.
	@Autowired
	HeroesRepository repository;

	// Save student entity in the h2 database.
	public void save(final Heroes student) {
		repository.save(student);
	}

	// Get all students from the h2 database.
	public List<Heroes> getAll() {
		final List<Heroes> heroes = new ArrayList<Heroes>();
		repository.findAll().forEach(heroe -> heroes.add(heroe));
		return heroes;
	}
	public Heroes getById(Integer idHeroe) {
		final Heroes heroes = repository.findById(idHeroe).get();
		return heroes;
	}
	
	public List<Heroes> getNameLike(String name) {
		final List<Heroes> heroes = new ArrayList<Heroes>();
		repository.findByTitleContains(name).forEach(heroe -> heroes.add(heroe));
		return heroes;
	}
	
}
