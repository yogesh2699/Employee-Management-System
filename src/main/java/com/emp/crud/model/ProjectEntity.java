package com.emp.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PROJECT")
public class ProjectEntity {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "projectId")
	private Long projectId;
	    
	@Column(name="work_on_hours")
	private Long work_on_hours;
	
	@Column(name="name")
	private String name;
	
	@Column(name="location")
	private String location;
	
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public Long getWork_on_hours() {
		return work_on_hours;
	}
	public void setWork_on_hours(Long work_on_hours) {
		this.work_on_hours = work_on_hours;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	
	
}
