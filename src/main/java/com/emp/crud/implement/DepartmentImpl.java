package com.emp.crud.implement;

import java.util.List;

import com.emp.crud.model.DepartmentEntity;


public interface DepartmentImpl {
	
	List<DepartmentEntity> getAllDepartments();
	
	List<DepartmentEntity> findByDepartment(String Username);
}
