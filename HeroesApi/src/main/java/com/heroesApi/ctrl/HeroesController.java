package com.heroesApi.ctrl;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.heroesApi.model.Heroes;
import com.heroesApi.serv.HeroesService;

import javassist.NotFoundException;



@RestController
@RequestMapping("/heroes")
public class HeroesController {
	

	private final Logger log = LoggerFactory.getLogger(this.getClass()); 

	@Autowired
	HeroesService service;

	
	@GetMapping(value= "/getall", produces= "application/json")
	@Timed(name = "timerName")
	public List<Heroes> getAll() {
		log.info("Llamada getAll heroes.");
		return service.getAll();
	}
	
	@GetMapping(value= "/getbyid/{id}", produces= "application/json")
	public Heroes getById(@PathVariable("id") Integer id) {
		log.info("Llamada getbyid heroes.");
		return service.getById(id);
	}
	
	@GetMapping(value= "/getnamelike/{name}", produces= "application/json")
	public List<Heroes> getNameLike(@PathVariable("name") String name) {
		log.info("Llamada getbyid heroes.");
		return service.getNameLike(name);
	}
	
	@PostMapping(value= "/modifheroe", consumes = "application/json", produces= "application/json")
	public ResponseEntity<Heroes> modificarHeroe(@Valid @RequestBody Heroes member) throws NotFoundException {
		log.info("Llamada getbyid heroes.");
		final Heroes updatedHeroes = service.modificarHeroe(member);
		return ResponseEntity.ok(updatedHeroes);
	}
	
	@GetMapping(value= "/deleteheroe/{id}", produces= "application/json")
	public void deleteHeroe(@PathVariable("id") Integer id) {
		log.info("Llamada deleteHeroe heroes.");
		service.deleteById(id);
	}

	public HeroesService getService() {
		return service;
	}

	public void setService(HeroesService service) {
		this.service = service;
	}
	
}
