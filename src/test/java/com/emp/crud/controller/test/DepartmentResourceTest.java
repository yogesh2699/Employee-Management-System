package com.emp.crud.controller.test;


import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.springframework.web.context.WebApplicationContext;

import com.emp.crud.controller.DepartmentController;
import com.emp.crud.impl.DepartmentImpl;
import com.emp.crud.model.DepartmentEntity;
import com.emp.crud.model.EmployeeEntity;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
@ExtendWith({ RestDocumentationExtension.class, SpringExtension.class})
@WebAppConfiguration
@WebMvcTest(controllers = DepartmentController.class)
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
public class DepartmentResourceTest {
	

	@Autowired 
	private WebApplicationContext context;
	 
	@Autowired
	private MockMvc mockMvc;
		
	@MockBean   
	private DepartmentImpl departmentService;

	
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

		 
		   // done	  
			@Test
		    public void departmentByLocationTest() throws Exception {

				
				Set<EmployeeEntity> listEmployee = new HashSet<EmployeeEntity>();
				EmployeeEntity empOne = new EmployeeEntity(1L, "John", "Paul", "John@gmail.com");
				EmployeeEntity empTwo = new EmployeeEntity(2L, "Alex", "kolenchiski", "alexk@yahoo.com");
				
				listEmployee.add(empOne);
				listEmployee.add(empTwo);
				
				List<DepartmentEntity> list = new ArrayList<DepartmentEntity>();
		        DepartmentEntity deptOne = new DepartmentEntity(1L,"Security","Delhi");
		        deptOne.setEmployees(listEmployee);
		        
		        DepartmentEntity deptThree = new DepartmentEntity(2L,"Cloud Architecture","Delhi");
		         
		        list.add(deptOne);
		        list.add(deptThree);
			         
		        //given
		        given(departmentService.findByDepartment("Delhi")).willReturn(list);
		        
				/* convert object into JSON object */
				String objectJson = requestBody(list);
				
				MvcResult result = mockMvc.perform(get("/department/{location}","Delhi")
				        .contentType("application/json"))
				                .andExpect(status().isOk())
				                .andDo(print())
				                .andDo(document("{methodName}",
				                 preprocessRequest(prettyPrint()),
				                 preprocessResponse(prettyPrint())))
				                .andReturn();
				        
				        Assert.assertEquals(result.getResponse().getContentAsString(), objectJson);
		    }
		  
			
			
}
