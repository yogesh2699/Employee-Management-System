package com.emp.crud.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.crud.exception.RecordNotFoundException;
import com.emp.crud.implement.DepartmentImpl;
import com.emp.crud.model.DepartmentEntity;
import com.emp.crud.repository.DepartmentRepository;



@Service
public class DepartmentService implements DepartmentImpl {
	
	@Autowired
    DepartmentRepository repository;
     
    public List<DepartmentEntity> getAllDepartments()
    {
        List<DepartmentEntity> deptList = repository.findAll();
         
        if(deptList.size() > 0) {
            return deptList;
        } else {
            return new ArrayList<DepartmentEntity>();
        }
    }
    
	/* Department service to fetch data based on location */
   
    public List<DepartmentEntity> findByDepartment(String Username) {
        List<DepartmentEntity> result = repository.findByDepartmentLocation(Username);

        return result;
    }
     
}
