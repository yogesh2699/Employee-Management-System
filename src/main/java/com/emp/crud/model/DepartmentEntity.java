package com.emp.crud.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author Yogesh Goel
 *
 */
@Entity
@Table(name="DEPARTMENTS")
public class DepartmentEntity {


	public DepartmentEntity() {
		super();
	}

	public DepartmentEntity(Long id, String deptName, String deptLoc) {
		super();
		this.id = id;
		this.deptName = deptName;
		this.deptLoc = deptLoc;
	}

	@Id
    @Column(name="dep_id")
    private Long id;

	@Column(name="dept_name")
    private String deptName;
    
    @Column(name="dept_location", nullable=false, length=200)
    private String deptLoc;
    
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<EmployeeEntity> employees = new HashSet<EmployeeEntity>(0);

    
    public Set<EmployeeEntity> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<EmployeeEntity> employees) {
		this.employees = employees;
	}	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptLoc() {
		return deptLoc;
	}

	public void setDeptLoc(String deptLoc) {
		this.deptLoc = deptLoc;
	}
    
   
}