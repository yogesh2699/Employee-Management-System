package com.emp.crud;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.emp.crud.model.DepartmentEntity;
import com.emp.crud.model.EmployeeEntity;
import com.emp.crud.service.DepartmentService;
import com.emp.crud.service.EmployeeService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentTests {

	@Autowired
	private DepartmentService departmentService;

	
	DepartmentService mock = org.mockito.Mockito.mock(DepartmentService.class);

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
         
        when(mock.getAllDepartments()).thenReturn(list);
         
        //test
        List<DepartmentEntity> deptList = mock.getAllDepartments();
         
        assertEquals(3, deptList.size());
        verify(mock, times(1)).getAllDepartments();
    }

}
