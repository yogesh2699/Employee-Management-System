package com.emp.crud.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.emp.crud.model.ProjectEntity;
import com.emp.crud.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository projectrepository;
	
	 public List<ProjectEntity> getAllProjects()
	    {
	        List<ProjectEntity> projectList = projectrepository.findAll();
	         
	        if(projectList.size() > 0) {
	            return projectList;
	        } else {
	            return new ArrayList<ProjectEntity>();
	        }
	    }
}
