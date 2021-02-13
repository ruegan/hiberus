package com.heroesApi.serv.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.heroesApi.model.Heroes;
import com.heroesApi.repo.HeroesRepository;
import com.heroesApi.serv.HeroesService;
import com.heroesApi.utils.controlErrores.exception.DataNotFoundException;



@Service
public class HeroesServiceImpl implements HeroesService {

	@Autowired
	HeroesRepository repository;

	public void save(final Heroes student) {
		repository.save(student);
	}
	
	@Cacheable(value="getAll")
	public List<Heroes> getAll() {
		final List<Heroes> heroes = new ArrayList<Heroes>();
		repository.findAll().forEach(heroe -> heroes.add(heroe));
		return heroes;
	}
	
	@Cacheable(value="getById", key = "#idHeroe")
	public Heroes getById(Integer idHeroe) {
		final Heroes heroes = repository.findById(idHeroe).get();
		return heroes;
	}
	
	@Cacheable(value="getNameLike", key = "#name")
	public List<Heroes> getNameLike(String name) {
		final List<Heroes> heroes = new ArrayList<Heroes>();
		repository.findByTitleContains(name.toUpperCase()).forEach(heroe -> heroes.add(heroe));
		if(heroes.size()==0) throw new DataNotFoundException("Heroes no encontrados que contengan :: " + name);
		return heroes;
	}
	
	public Heroes modificarHeroe(Heroes heroesDetails) throws DataNotFoundException {
		Heroes heroe = repository.findById(heroesDetails.getId())
               .orElseThrow(() -> new DataNotFoundException("Heroe no encontrado para ese id :: " + heroesDetails.getId()));
		heroe.setId(heroesDetails.getId());
		heroe.setName(heroesDetails.getName());
		return repository.save(heroe);
	}
	
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}
	public void insertHeroe(Heroes heroesDetails) {
		repository.save(heroesDetails);
	}

}
