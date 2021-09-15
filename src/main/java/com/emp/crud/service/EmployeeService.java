package com.emp.crud.service;

import java.util.List;

import com.emp.crud.exception.RecordNotFoundException;
import com.emp.crud.model.EmployeeEntity;

public interface EmployeeService {

	List<EmployeeEntity> getAllEmployees(Integer pageNo, Integer pageSize);
	
	EmployeeEntity getEmployeeById(Long id) throws RecordNotFoundException ;
	
	EmployeeEntity createOrUpdateEmployee(EmployeeEntity entity) throws RecordNotFoundException ;
	
	boolean deleteEmployeeById(Long id) throws RecordNotFoundException ;
}
