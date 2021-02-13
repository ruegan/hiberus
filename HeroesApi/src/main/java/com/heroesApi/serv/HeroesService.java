package com.heroesApi.serv;

import java.util.List;

import org.springframework.stereotype.Service;

import com.heroesApi.model.Heroes;
import com.heroesApi.utils.controlErrores.exception.DataNotFoundException;

import javassist.NotFoundException;



@Service
public interface HeroesService {

	public void save(final Heroes student);
	public List<Heroes> getAll();
	public Heroes getById(Integer idHeroe);
	public List<Heroes> getNameLike(String name);
	public Heroes modificarHeroe(Heroes heroesDetails) throws DataNotFoundException;
	public void deleteById(Integer id);
	public void insertHeroe(Heroes heroesDetails);
	
}
