package com.emp.crud.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.emp.crud.exception.RecordNotFoundException;
import com.emp.crud.model.EmployeeEntity;
import com.emp.crud.repository.EmployeeRepository;
import com.emp.crud.service.EmployeeService;
 
@Service
public class EmployeeImpl implements EmployeeService{
     
    @Autowired
    EmployeeRepository repository;
     
	/*
	 * public List<EmployeeEntity> getAllEmployees() { List<EmployeeEntity>
	 * employeeList = repository.findAll();
	 * 
	 * if(employeeList.size() > 0) { return employeeList; } else { return new
	 * ArrayList<EmployeeEntity>(); } }
	 */
    
	/* pagination with sorting technique */
    
    public List<EmployeeEntity> getAllEmployees(Integer pageNo, Integer pageSize)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize);
 
        Page<EmployeeEntity> pagedResult = repository.findAll(paging);
         
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<EmployeeEntity>();
        }
    }
     
    public List<EmployeeEntity> getAllEmployee()
    {
    	 return (List<EmployeeEntity>) repository.findAll();
       
    }
    public EmployeeEntity getEmployeeById(Long i) throws RecordNotFoundException 
    {
        Optional<EmployeeEntity> employee = repository.findById(i);
         
        if(employee.isPresent()) {
            return employee.get();
        } else {
            //throw new RecordNotFoundException("No employee record exist for given id");
        	throw null;
        }
    }
     
    /* change this method*/
    public EmployeeEntity createOrUpdateEmployee(EmployeeEntity entity) throws RecordNotFoundException
    {
        Optional<EmployeeEntity> employee = repository.findById(entity.getId());
         
        if(employee.isPresent())
        {
            EmployeeEntity newEntity = employee.get();
            newEntity.setEmail(entity.getEmail());
            newEntity.setFirstName(entity.getFirstName());
            newEntity.setLastName(entity.getLastName());
 
            newEntity = repository.save(newEntity);
             
            return newEntity;
        } else {
            entity = repository.save(entity);
             System.out.print(entity);
            return entity;
        }
    }
     /* change this method*/
    
    
    public boolean deleteEmployeeById(Long id) throws RecordNotFoundException
    {
        Optional<EmployeeEntity> employee = repository.findById(id);
         
        if(employee.isPresent())
        {
            repository.deleteById(id);
            return true;
        } else {
            //throw new RecordNotFoundException("No employee record exist for given id");
            return false;
        }
    }

	

	
}