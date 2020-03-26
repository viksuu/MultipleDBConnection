package com.viksuutechie.springboot.sql.nosql.multipledatasource.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Department")
public class Department {

    @Id
    private String id;

    @Indexed(name = "deptName")
    private String name;
    private String description;
    private List employees;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List getEmployees() {
		return employees;
	}
	public void setEmployees(List employees) {
		this.employees = employees;
	}
	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", description=" + description + ", employees=" + employees
				+ "]";
	}
}    
	
    
    