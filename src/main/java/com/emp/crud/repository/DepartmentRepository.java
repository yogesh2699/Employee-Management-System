package com.emp.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.emp.crud.model.DepartmentEntity;


public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long>{

	
	@Query(value="SELECT * FROM departments WHERE dept_location=?1",nativeQuery=true)
	List<DepartmentEntity> findByDepartmentLocation(String dept_location);
	
	
}
