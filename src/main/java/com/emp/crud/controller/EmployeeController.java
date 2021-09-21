package com.emp.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emp.crud.exception.RecordNotFoundException;
import com.emp.crud.impl.EmployeeImpl;
import com.emp.crud.model.EmployeeEntity;

 
@RestController
@RequestMapping("/employees")
public class EmployeeController
{
    @Autowired
    EmployeeImpl service;
    
   
 
    @GetMapping
    public ResponseEntity<List<EmployeeEntity>> getAllEmployees(
                        @RequestParam(defaultValue = "0") Integer pageNo, 
                        @RequestParam(defaultValue = "10") Integer pageSize) 
    {
        List<EmployeeEntity> list = service.getAllEmployees(pageNo, pageSize);
 
        return new ResponseEntity<List<EmployeeEntity>>(list, new HttpHeaders(), HttpStatus.OK); 
    }
    
    
	
	  @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<List<EmployeeEntity>> fetchAllEmployees() {
	  List<EmployeeEntity> list = service.getAllEmployee();
	  
	  return new ResponseEntity<List<EmployeeEntity>>(list, new HttpHeaders(),
	  HttpStatus.OK); }
	 
 
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        EmployeeEntity entity = service.getEmployeeById(id);
 
        return new ResponseEntity<EmployeeEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    }
 
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeEntity> createOrUpdateEmployee(EmployeeEntity employee)
                                                    throws RecordNotFoundException {
        EmployeeEntity updated = service.createOrUpdateEmployee(employee);
        return new ResponseEntity<EmployeeEntity>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public boolean deleteEmployeeById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        
        return service.deleteEmployeeById(id);
    }
 
}