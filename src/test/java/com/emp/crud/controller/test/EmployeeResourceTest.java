package com.emp.crud.controller.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.emp.crud.controller.EmployeeController;
import com.emp.crud.impl.EmployeeImpl;
import com.emp.crud.model.EmployeeEntity;
import com.emp.crud.repository.EmployeeRepository;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@ExtendWith({ RestDocumentationExtension.class, SpringExtension.class})
@WebAppConfiguration
@WebMvcTest(controllers = EmployeeController.class)
//@WebMvcTest(controllers = EmployeeRepository.class)
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
public class EmployeeResourceTest {

	
   @Autowired private WebApplicationContext context;
	 
    
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@MockBean   
	private EmployeeImpl empService;

	
	EmployeeRepository mock = org.mockito.Mockito.mock(EmployeeRepository.class);
	
	
	  @BeforeEach
	  public void setUp(WebApplicationContext webApplicationContext,
	  RestDocumentationContextProvider restDocumentation) {
	  
	  this.mockMvc = MockMvcBuilders .webAppContextSetup(webApplicationContext)
	  .apply(documentationConfiguration(restDocumentation)) .build(); }
	 
	
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
	
	// done	  
	@Test
    public void employeeByIdTest() throws Exception {

		EmployeeEntity emp = new EmployeeEntity();
		emp.setId((long) (4));
		emp.setFirstName("Yogesh");
		emp.setLastName("goel");
		emp.setEmail("xyz@gmail.com");
		
		
		String uri = "/employees/4";
		/* convert object into JSON object */
		String objectJson = requestBody(emp);
		
		given(empService.getEmployeeById((long)4)).willReturn(emp);
		//given(repository.findById(4L).get()).willReturn(Optional.of(emp));
		
		//when(mock.findById(4L)).thenReturn(Optional.of(emp));
		
		  MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri)
			      .accept(MediaType.APPLICATION_JSON_VALUE))
		  // .andExpect(MockMvcResultMatchers.content().json(objectJson))
		  .andDo(document("{methodName}",
				  preprocessRequest(prettyPrint()),
		          preprocessResponse(prettyPrint())))
		  .andReturn();
		 
		  int status = result.getResponse().getStatus();
		  assertEquals(200, status);
		  assertEquals(result.getResponse().getContentAsString(), objectJson);
			
			}
	
	
	// Done
	 @Test
	    public void shouldFetchAllEmployeeTest() throws Exception {

		 List<EmployeeEntity> list = new ArrayList<EmployeeEntity>();
			EmployeeEntity empOne = new EmployeeEntity(1L, "John", "Paul", "howtodoinjava@gmail.com");
			EmployeeEntity empTwo = new EmployeeEntity(2L, "Alex", "kolenchiski", "alexk@yahoo.com");
			EmployeeEntity empThree = new EmployeeEntity(4L, "Yogesh", "goel", "xyz@gmail.com");
	         
	        list.add(empOne);
	        list.add(empTwo);
	        list.add(empThree);
	        
	        
	        given(empService.getAllEmployee()).willReturn(list);

	        MvcResult result = mockMvc.perform(get("/employees/all")
	        .contentType("application/json"))
	                .andExpect(status().isOk())
	                .andDo(print())
	                .andDo(document("{methodName}",
	                 preprocessRequest(prettyPrint()),
	                 preprocessResponse(prettyPrint())))
	                .andReturn();
	        
	        Assert.assertEquals(result.getResponse().getContentAsString(), MAPPER.writeValueAsString(list));
	    }
	
	
	@Test
	public void createOrUpdateEmployeeTest() throws Exception {
	   
		EmployeeEntity emp = new EmployeeEntity();
		emp.setId((long) (5));
		emp.setFirstName("Jeff");
		emp.setLastName("Buzz");
		emp.setEmail("Jeff@gmail.com");
		 
		given(empService.createOrUpdateEmployee(emp)).willReturn(emp);
		 
		/* convert object into JSON object */
		String objectJson = requestBody(emp);
		
		MvcResult result  = mockMvc.perform(post("/employees")
	    		     .contentType(MediaType.APPLICATION_JSON_UTF8)	    
	    		     .content(requestBody(emp)))
	    		     .andExpect(status().isOk())
	               .andDo(print())
	               .andDo(document("{methodName}",
	                        preprocessRequest(prettyPrint()),
	                        preprocessResponse(prettyPrint())))
	               .andReturn();
		
	   
	 
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
		.andDo(document("{methodName}",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))
		.andReturn();
		
		Assert.assertEquals(result.getResponse().getContentAsString(), "true");
	
  }
	}
