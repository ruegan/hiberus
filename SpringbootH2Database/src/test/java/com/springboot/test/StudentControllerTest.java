package com.springboot.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.springboot.h2.ctrl.StudentController;
import com.springboot.h2.model.Student;
import com.springboot.h2.serv.StudentService;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
public class StudentControllerTest {
	
    private static final Logger logger = LoggerFactory.getLogger(StudentControllerTest.class);


    @MockBean
    private StudentService service;
    
    @Autowired
    private MockMvc mockMvc;
    
    @Before
    public void setUp() {
    	List<Student> prueba = new ArrayList();
    	Student student = new Student();
    	student.setAge(123);
    	student.setEmailAddress("asd");
    	student.setId(1);
    	student.setName("as");
		prueba.add(student);
        Mockito.when(service.getAll())
          .thenReturn(prueba);
    }
    
    @Test
    public void testGetSpain() throws Exception {

    	MvcResult result = mockMvc.perform(get("/student/getall")
        	      .contentType(MediaType.APPLICATION_JSON))
        	      .andExpect(status().isOk());
    }
	
}
