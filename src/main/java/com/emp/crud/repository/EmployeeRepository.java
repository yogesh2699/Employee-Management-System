package com.emp.crud.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.emp.crud.model.EmployeeEntity;
 
@Repository
public interface EmployeeRepository
          extends PagingAndSortingRepository<EmployeeEntity, Long> {
 
}
