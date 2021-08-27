package com.emp.crud.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.emp.crud.model.ProjectEntity;
import com.emp.crud.service.ProjectService;

@RestController
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
     ProjectService projectService;
	
	
	 @GetMapping
	    public ResponseEntity<List<ProjectEntity>> getAllProjects() {
	        List<ProjectEntity> list = projectService.getAllProjects();
	        return new ResponseEntity<List<ProjectEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	    }
}
