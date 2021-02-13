package com.heroesApi.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.heroesApi.Appmain;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import static org.hamcrest.Matchers.is;


@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Appmain.class)
public class HeroesControllerTest {

	@Autowired
	private WebApplicationContext webAppContext;
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
	    MockitoAnnotations.initMocks(this);
	    mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
	}
    @Test
    public void getById() throws Exception {
    	
        mockMvc.perform(get("/heroes/getbyid/{id}", 1))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("id", is(1)))
        .andExpect(jsonPath("name", is("Superman")));
    }

}