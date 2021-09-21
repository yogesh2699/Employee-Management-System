package com.emp.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emp.crud.exception.RecordNotFoundException;
import com.emp.crud.impl.DepartmentImpl;
import com.emp.crud.model.DepartmentEntity;
import com.emp.crud.model.EmployeeEntity;


@RestController
@RequestMapping("/department")
public class DepartmentController {

	
	
	 @Autowired
	 DepartmentImpl departmentservice;
	 @GetMapping
	    public ResponseEntity<List<DepartmentEntity>> getAllEmployees() {
	        List<DepartmentEntity> list = departmentservice.getAllDepartments();
	 
	        return new ResponseEntity<List<DepartmentEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	    }
	 
	 /* GET Mapping for Get Departments based on Location**/
	 @GetMapping("/{location}")
	    public ResponseEntity<List<DepartmentEntity>> getDepartmentByLocation(@PathVariable("location") String location)
	                                                    throws RecordNotFoundException {
	       List<DepartmentEntity> depList = departmentservice.findByDepartment(location);
	 
	        return new ResponseEntity<List<DepartmentEntity>>(depList, new HttpHeaders(), HttpStatus.OK);
	    }
	 
}
