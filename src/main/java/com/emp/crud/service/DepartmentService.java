package com.emp.crud.service;

import java.util.List;

import com.emp.crud.model.DepartmentEntity;


public interface DepartmentService {
	
	List<DepartmentEntity> getAllDepartments();
	
	List<DepartmentEntity> findByDepartment(String Username);
}
