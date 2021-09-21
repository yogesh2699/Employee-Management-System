package com.emp.crud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.emp.crud.exception.RecordNotFoundException;
import com.emp.crud.impl.EmployeeImpl;
import com.emp.crud.model.DepartmentEntity;
import com.emp.crud.model.EmployeeEntity;
import com.emp.crud.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeTests {

	@Autowired
	public EmployeeImpl employeeService;
	
	
	EmployeeRepository mock = org.mockito.Mockito.mock(EmployeeRepository.class);


	@Test
	public void empSave() throws RecordNotFoundException {
		
		EmployeeEntity emp = new EmployeeEntity();
		emp.setId((long) (4));
		emp.setFirstName("Yogesh");
		emp.setLastName("goel");
		emp.setEmail("xyz@gmail.com");
		
		when(mock.save(emp)).thenReturn(new EmployeeEntity((long) 4,"Yogesh","goel","xyz@gmail.com"));
		EmployeeEntity savedEmp = employeeService.createOrUpdateEmployee(emp);

		assertEquals(emp.getId(), savedEmp.getId());
	}

	// unhappy test case
	@Test
	public void getEmployeeByIdTestCase() throws RecordNotFoundException {

		EmployeeEntity emp = new EmployeeEntity();
		emp.setId((long) 1);
		emp.setFirstName("Yogesh");
		emp.setLastName("goel");
		emp.setEmail("xyz@gmail.com");
		
		Object returnEmployee = new EmployeeEntity();
		
		try {
				employeeService.getEmployeeById((long) 4);
		} 
		catch(Exception e) {
		assertFalse("Employee is not be found",returnEmployee != null);
		}
	}
	
	
	
	
	@Test
	public void getAllEmployee()
	{
		List<EmployeeEntity> list = new ArrayList<EmployeeEntity>();
		EmployeeEntity empOne = new EmployeeEntity((long) 1, "John", "Paul", "howtodoinjava@gmail.com");
		EmployeeEntity empTwo = new EmployeeEntity((long) 2, "Alex", "kolenchiski", "alexk@yahoo.com");
		EmployeeEntity empThree = new EmployeeEntity((long) 4, "Yogesh", "goel", "xyz@gmail.com");
         
        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);
         
        when(mock.findAll()).thenReturn(list);
         
        //test
        List<EmployeeEntity> empList =  employeeService.getAllEmployees(0, 100);
         
        assertEquals(list.size(), empList.size());

	}

	// unhappy test case
	
	@Test
	public void getAllEmployeeTestCase()
	{
		List<EmployeeEntity> list = new ArrayList<EmployeeEntity>();
		
		EmployeeEntity empOne =new EmployeeEntity((long) 1, "John", "Paul", "howtodoinjava@gmail.com");
		EmployeeEntity empTwo = new EmployeeEntity((long) 2, "Alex", "kolenchiski", "alexk@yahoo.com");
		EmployeeEntity empThree = new EmployeeEntity((long) 4, "Yogesh", "goel", "xyz@gmail.com");
         
        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);
         
        when(mock.findAll()).thenReturn(list);
         
        //test
        List<EmployeeEntity> empList =  employeeService.getAllEmployees(0, 100);
         try {
            assertEquals(list.get(0).getEmail(), empList.get(0).getEmail());
           
         }catch(Exception e)
         {
        	 System.out.print("Record not matched");
         }
        
	}
	
	 @Test
	  public void deletebyEmployee() throws RecordNotFoundException {
	  
		 EmployeeEntity emp = new EmployeeEntity();
			emp.setId((long) 5);
			emp.setFirstName("Alex");
			emp.setLastName("Paul");
			emp.setEmail("xyz@gmail.com");
			
	 	 employeeService.createOrUpdateEmployee(emp);
			
	   
	   doNothing().when(mock).deleteById(emp.getId()); 
	 
	   boolean actualProject = employeeService.deleteEmployeeById(emp.getId());
	    assertEquals(actualProject,true);
	 
	  }
	
	
	
	
	
	
}