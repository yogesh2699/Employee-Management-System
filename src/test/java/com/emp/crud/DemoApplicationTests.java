package com.emp.crud;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.emp.crud.exception.RecordNotFoundException;
import com.emp.crud.implement.EmployeeImp;
import com.emp.crud.model.DepartmentEntity;
import com.emp.crud.model.EmployeeEntity;
import com.emp.crud.service.EmployeeService;



@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private EmployeeService employeeService;


	@Test
	public void empSave() throws RecordNotFoundException {
		
		EmployeeEntity emp = new EmployeeEntity();
		emp.setId((long) (1));
		emp.setFirstName("Yogesh");
		emp.setLastName("goel");
		emp.setEmail("xyz@gmail.com");
		
		//emp.setDepartment(new DepartmentEntity((long) 1,"R&D","India"));
		

		EmployeeEntity savedEmp = employeeService.createOrUpdateEmployee(emp);

		assertEquals(emp.getId(), savedEmp.getId());
	}

	@Test
	public void getEmployeeByIdTest() throws RecordNotFoundException {

		// true test case

		when(employeeService.getEmployeeById((long) 1))
				.thenReturn(new EmployeeEntity((long) 1, "Yogesh", "Goel", "yogesh@email.com"));

		EmployeeEntity emp = employeeService.getEmployeeById((long) 1);

		System.out.print(emp);
		assertEquals("Yogesh", emp.getFirstName());
		assertEquals("Goel", emp.getLastName());
		assertEquals("yogesh@email.com", emp.getEmail());

	}
	
	@Test
	public void getAllEmployee()
	{
		List<EmployeeEntity> list = new ArrayList<EmployeeEntity>();
		EmployeeEntity empOne = new EmployeeEntity((long) 1, "John", "John", "howtodoinjava@gmail.com");
		EmployeeEntity empTwo = new EmployeeEntity((long) 2, "Alex", "kolenchiski", "alexk@yahoo.com");
		EmployeeEntity empThree = new EmployeeEntity((long) 3, "Steve", "Waugh", "swaugh@gmail.com");
         
        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);
         
        when(employeeService.getAllEmployees(1, 10)).thenReturn(list);
         
        //test
        List<EmployeeEntity> empList = employeeService.getAllEmployees(1, 10);
         
        assertEquals(3, empList.size());
        verify(employeeService, times(1)).getAllEmployees(1, 10);
	}

}
