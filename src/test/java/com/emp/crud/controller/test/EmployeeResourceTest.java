package com.emp.crud.controller.test;

import org.assertj.core.api.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.emp.crud.controller.EmployeeController;
import com.emp.crud.model.EmployeeEntity;
import com.emp.crud.service.EmployeeImpl;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.*;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

import java.io.IOException;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@WebAppConfiguration
@WebMvcTest(controllers = EmployeeController.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeResourceTest {

	@Autowired
	private MockMvc mockMvc;
	
	
	@MockBean   
	private EmployeeImpl empService;

	
	private static final ObjectMapper MAPPER = new ObjectMapper()
		    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
		    .configure(Feature.AUTO_CLOSE_SOURCE, true)
		    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
		    .registerModule(new JavaTimeModule());
	
	

		  public static String requestBody(Object request) {
		    try {
		      return MAPPER.writeValueAsString(request);
		    } catch (JsonProcessingException e) {
		      throw new RuntimeException(e);
		    }
		  }

		  public static <T> T parseResponse(MvcResult result, Class<T> responseClass) {
		    try {
		      String contentAsString = result.getResponse().getContentAsString();
		      return MAPPER.readValue(contentAsString, responseClass);
		    } catch (IOException e) {
		      throw new RuntimeException(e);
		    }
		  }
	
		  
	@Test
    public void testEmployeeByIdJson() throws Exception {

		EmployeeEntity emp = new EmployeeEntity();
		emp.setId((long) (4));
		emp.setFirstName("Yogesh");
		emp.setLastName("goel");
		emp.setEmail("xyz@gmail.com");
			 
		given(empService.getEmployeeById((long)4)).willReturn(emp);
		
	this.mockMvc.perform(get("/employees/{id}",4)	
				.contentType("application/json"))
				.andExpect(jsonPath("$.firstName" , is(emp.getFirstName())))
				.andExpect(jsonPath("$.lastName" , is(emp.getLastName())))
				.andExpect(jsonPath("$.email" , is(emp.getEmail())))
                .andDo(print()).andExpect(status().isOk());
					
    }
	
	
	 @Test
	    public void shouldFetchAllEmployee() throws Exception {

		 List<EmployeeEntity> list = new ArrayList<EmployeeEntity>();
			EmployeeEntity empOne = new EmployeeEntity((long) 1, "John", "Paul", "howtodoinjava@gmail.com");
			EmployeeEntity empTwo = new EmployeeEntity((long) 2, "Alex", "kolenchiski", "alexk@yahoo.com");
			EmployeeEntity empThree = new EmployeeEntity((long) 4, "Yogesh", "goel", "xyz@gmail.com");
	         
	        list.add(empOne);
	        list.add(empTwo);
	        list.add(empThree);
	        
	        given(empService.getAllEmployees(0, 100)).willReturn(list);

	        this.mockMvc.perform(get("/employees")
	        .contentType(MediaType.APPLICATION_JSON_UTF8))
	                .andExpect(status().isOk())
	                .andDo(print());
	    }
	
	
	@Test
	public void createEmployee() throws Exception {
	   
		EmployeeEntity emp = new EmployeeEntity();
		emp.setId((long) (5));
		emp.setFirstName("Jeff");
		emp.setLastName("Buzz");
		emp.setEmail("Jeff@gmail.com");
		 given(empService.createOrUpdateEmployee(emp)).willReturn(emp);
	     
	    this.mockMvc.perform(post("/employees")
	    		     .contentType(MediaType.APPLICATION_JSON_UTF8)	    
	    		     .content(requestBody(emp)))
	    		     .andExpect(status().isOk())
	               .andDo(print());
	   
	 
	}
	
	@Test
	public void deleteByEmployeeIdTest() throws Exception{
	
		
		EmployeeEntity emp = new EmployeeEntity();
		emp.setId((long) (4));
		emp.setFirstName("Yogesh");
		emp.setLastName("goel");
		emp.setEmail("xyz@gmail.com");
		given(empService.deleteEmployeeById((long) (4))).willReturn(true);
	
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/employees/{id}",(long) (4))
		.contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk())
		.andDo(print())
		.andReturn();
		
		
	
	
  }
	}
