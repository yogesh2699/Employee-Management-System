package com.emp.crud.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.crud.exception.RecordNotFoundException;

import com.emp.crud.model.DepartmentEntity;
import com.emp.crud.model.EmployeeEntity;
import com.emp.crud.repository.DepartmentRepository;
import com.emp.crud.service.DepartmentService;



@Service
public class DepartmentImpl implements DepartmentService {
	
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
   
    public List<DepartmentEntity> findByDepartment(String location) {
        List<DepartmentEntity> result = repository.findByDepartmentLocation(location);

        return result;
    }
     
    public boolean deleteByDepartment(DepartmentEntity entity) {
             
    	 Optional<DepartmentEntity> department = repository.findById(entity.getId());
    	 if(department.isPresent())
         {
         	DepartmentEntity newEntity = department.get();
         	newEntity.setId(entity.getId());
         	newEntity.setDeptName(entity.getDeptName());
         	newEntity.setDeptLoc(entity.getDeptLoc());
         	repository.delete(newEntity);
             return true;
         } else {
                  return false;
    	  }
    }
    
    public DepartmentEntity createOrUpdateDepartment(DepartmentEntity entity) throws RecordNotFoundException
    {
        Optional<DepartmentEntity> department = repository.findById(entity.getId());
         
        if(department.isPresent())
        {
        	DepartmentEntity newEntity = department.get();
        	newEntity.setId(entity.getId());
        	newEntity.setDeptName(entity.getDeptName());
        	newEntity.setDeptLoc(entity.getDeptLoc());
            return newEntity;
        } else {
            entity = repository.save(entity);
            return entity;
        }
    }
}
