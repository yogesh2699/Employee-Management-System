package com.emp.crud.implement;

import java.util.List;

import com.emp.crud.exception.RecordNotFoundException;
import com.emp.crud.model.EmployeeEntity;

public interface EmployeeImp {

	List<EmployeeEntity> getAllEmployees(Integer pageNo, Integer pageSize);
	
	EmployeeEntity getEmployeeById(Long id) throws RecordNotFoundException ;
	
	EmployeeEntity createOrUpdateEmployee(EmployeeEntity entity) throws RecordNotFoundException ;
	
	void deleteEmployeeById(Long id) throws RecordNotFoundException ;
}
