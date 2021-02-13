package com.heroesApi.ctrl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heroesApi.model.Heroes;
import com.heroesApi.serv.HeroesService;



@RestController		// Useful to create the RESTful webservices.
@RequestMapping("/heroes")
public class HeroesController {
	

	private final Logger log = LoggerFactory.getLogger(this.getClass()); 

	// @Autowired annotation provides the automatic dependency injection.
	@Autowired
	HeroesService service;


	@GetMapping(value= "/getall", produces= "application/json")
	public List<Heroes> getAll() {
		log.info("Getting student details from the database.");
		return service.getAll();
	}
	
	@GetMapping(value= "/getbyid/{id}", produces= "application/json")
	public Heroes getbyid(@PathVariable("id") Integer id) {
		log.info("Getting student details from the database.");
		return service.getById(id);
	}
	
	@GetMapping(value= "/getnamelike/{name}", produces= "application/json")
	public List<Heroes> getbyid(@PathVariable("name") String name) {
		log.info("Getting student details from the database.");
		return service.getNameLike(name);
	}
}
