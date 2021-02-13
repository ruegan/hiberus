package com.heroesApi.ctrl;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.heroesApi.model.Heroes;
import com.heroesApi.repo.HeroesRepository;
import com.heroesApi.serv.HeroesService;

import javassist.NotFoundException;

@ExtendWith(MockitoExtension.class)
public class HeroesControllerTest {

	@InjectMocks
    HeroesService heroesService;
    
    @Mock
    HeroesRepository heroesRepository;
    
    @InjectMocks
    HeroesController heroesController;


    @BeforeEach
    public void setup() {
    	heroesController.setService(heroesService);
    }
    
    @Test
    public void testGetById() throws Exception{

    	Heroes heroe1 = new Heroes(1,"superman");
    	Heroes heroe2 = new Heroes(2,"batman");
		List<Heroes> list = new ArrayList<Heroes>();
		list.addAll(Arrays.asList(heroe1, heroe2));

		when(heroesRepository.findAll()).thenReturn(list);
		// when
		List<Heroes> result = heroesController.getAll();

		// then
		assertThat(result.size()).isEqualTo(2);
		
		assertThat(result.get(0).getName())
						.isEqualTo(heroe1.getName());
		
		assertThat(result.get(1).getName())
						.isEqualTo(heroe2.getName());
		
    }
    
	@Test
	public void testModificarHeroe() throws NotFoundException 
	{
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		Heroes heroeToAdd = new Heroes(1,"Deadpool");
		Optional<Heroes> heroe = Optional.of(new Heroes(1,"Superman"));
		
		when(heroesRepository.save(any(Heroes.class))).thenReturn(heroeToAdd);
		
		when(heroesRepository.findById(any(Integer.class))).thenReturn(heroe);
		
		ResponseEntity<Heroes> responseEntity = heroesController.modificarHeroe(heroeToAdd);
		
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
		assertThat(responseEntity.getBody().getName()).isEqualTo(heroeToAdd.getName());
	}
}