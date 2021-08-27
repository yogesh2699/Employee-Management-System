package com.emp.crud;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.emp.crud.model.DepartmentEntity;
import com.emp.crud.model.EmployeeEntity;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	
		/*
		 * DepartmentEntity dep1 = new DepartmentEntity();
		 * dep1.setDeptName("engineering"); dep1.setDeptLoc("mumbai"); EmployeeEntity
		 * emp1 = new EmployeeEntity();
		 * 
		 * emp1.setFirstName("Manu"); emp1.setLastName("Manjunatha");
		 * emp1.setDepartment(dep1); emp1.setEmail("manu.m@coding.com");
		 * 
		 * Set<EmployeeEntity> s = new HashSet<>();
		 * 
		 * s.add(emp1); dep1.setEmployees(s);
		 */
	}

}
