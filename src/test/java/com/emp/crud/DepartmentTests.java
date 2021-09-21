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
import com.emp.crud.impl.DepartmentImpl;
import com.emp.crud.model.DepartmentEntity;
import com.emp.crud.repository.DepartmentRepository;



@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentTests {

	@Autowired
	private DepartmentImpl departmentService;

	
	//Repository mock 
	DepartmentRepository mock = org.mockito.Mockito.mock(DepartmentRepository.class);
	
	

	@Test
    public void getAllDepartmentTest()
    {
        List<DepartmentEntity> list = new ArrayList<DepartmentEntity>();
        DepartmentEntity deptOne = new DepartmentEntity((long) 1,"Security","Delhi");
        DepartmentEntity deptTwo = new DepartmentEntity((long) 2,"R&D","Delhi");
        DepartmentEntity deptThree = new DepartmentEntity((long) 3,"Dev-ops","Delhi");
         
        list.add(deptOne);
        list.add(deptTwo);
        list.add(deptThree);
         
        
        when(mock.findAll()).thenReturn(list);
         
        //test
        List<DepartmentEntity> deptList = departmentService.getAllDepartments();
         
        assertEquals(list.size(), deptList.size());
        
    }
	
	@Test
	public void findByDepartmentTest()
	{
		 List<DepartmentEntity> list = new ArrayList<DepartmentEntity>();
	       
		 DepartmentEntity deptOne = new DepartmentEntity((long) 1,"Security","Delhi");
		
		 try {
			departmentService.createOrUpdateDepartment(deptOne);
		} catch (RecordNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 list.add(deptOne);
		 
		
		 when(mock.findByDepartmentLocation("Delhi"))
		.thenReturn(list);

		 List<DepartmentEntity> deptList = departmentService.findByDepartment("Delhi");

		 assertEquals(deptList.size(),list.size());
	        
	}
	
	
	// unhappy Test Case
	@Test
	public void findByDepartmentTestCase()
	{
		
		 List<DepartmentEntity> list = new ArrayList<DepartmentEntity>();
		DepartmentEntity dept = new DepartmentEntity();
		dept.setId((long)1);
		dept.setDeptName("technical");
		dept.setDeptLoc("Delhi");
		
		when(mock.findByDepartmentLocation("Delhi"))
		.thenReturn(list);
		
		List<DepartmentEntity> deptList = new ArrayList<>();
		
			deptList = mock.findByDepartmentLocation("Delhi");
		
		assertFalse("Department is not be found",deptList == null);
		
	}
	
	
	  @Test
	  public void deletebyDepartment() throws RecordNotFoundException {
	  
	  
	  DepartmentEntity department = new DepartmentEntity();
	  department.setId((long)4);
	  department.setDeptName("R&D");
	  department.setDeptLoc("Mumbai");
	 
	   departmentService.createOrUpdateDepartment(department);
	   
	   doNothing().when(mock).deleteById(department.getId()); 
	 
	   boolean actualProject = departmentService.deleteByDepartment(department);
	    assertEquals(actualProject,true);
	 
	  }
	 
    	
	

}
