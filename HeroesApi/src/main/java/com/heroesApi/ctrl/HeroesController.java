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

import com.heroesApi.model.Heroes;
import com.heroesApi.serv.HeroesService;
import com.heroesApi.serv.impl.HeroesServiceImpl;
import com.heroesApi.utils.annotation.Temporizado;

@RestController
@RequestMapping("/heroes")
public class HeroesController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass()); 

	@Autowired
	HeroesServiceImpl service;

	@GetMapping(value= "/getall", produces= "application/json")
	@Temporizado
	public List<Heroes> getAll() {
		log.info("Llamada getAll.");
		return service.getAll();
	}
	
	@GetMapping(value= "/getbyid/{id}", produces= "application/json")
	@Temporizado
	public Heroes getById(@PathVariable("id") Integer id) {
		log.info("Llamada getById.");
		return service.getById(id);
	}
	
	@GetMapping(value= "/getnamelike/{name}", produces= "application/json")
	@Temporizado
	public List<Heroes> getNameLike(@PathVariable("name") String name) {
		log.info("Llamada getNameLike.");
		return service.getNameLike(name);
	}
	
	@PostMapping(value= "/modifheroe", consumes = "application/json", produces= "application/json")
	@Temporizado
	public ResponseEntity<Heroes> modificarHeroe(@Valid @RequestBody Heroes member){
		log.info("Llamada modificarHeroe.");
		final Heroes updatedHeroes = service.modificarHeroe(member);
		return ResponseEntity.ok(updatedHeroes);
	}
	
	@GetMapping(value= "/deleteheroe/{id}", produces= "application/json")
	@Temporizado
	public void deleteHeroe(@PathVariable("id") Integer id) {
		log.info("Llamada deleteHeroe.");
		service.deleteById(id);
	}


	public HeroesService getService() {
		return service;
	}

	public void setService(HeroesServiceImpl service) {
		this.service = service;
	}
	
}
