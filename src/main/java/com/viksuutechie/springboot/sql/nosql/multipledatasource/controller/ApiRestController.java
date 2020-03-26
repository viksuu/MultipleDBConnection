package com.viksuutechie.springboot.sql.nosql.multipledatasource.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viksuutechie.springboot.sql.nosql.multipledatasource.model.Department;
import com.viksuutechie.springboot.sql.nosql.multipledatasource.model.User;
import com.viksuutechie.springboot.sql.nosql.multipledatasource.repository.mongodb.DepartmentMongoRepository;
import com.viksuutechie.springboot.sql.nosql.multipledatasource.service.ServiceBusinessImpl;

@RestController
@RequestMapping("/users")
public class ApiRestController {
	@Autowired
	private DepartmentMongoRepository departmentRepository;

	@Autowired
	private ServiceBusinessImpl service;

	@PostMapping("mongoDept")
	public Department createDept(@RequestBody Department department) {
		departmentRepository.save(department);
		return department;
	}

	@GetMapping("mongoDept")
	public List listDepts() {
		return departmentRepository.findAll();
	}

	@PutMapping("/{deptId}")
	public Department updateDept(@RequestBody Department department, @PathVariable String deptId) {
		department.setId(deptId);
		departmentRepository.save(department);
		return department;
	}

	@DeleteMapping("/{deptId}")
	public String deleteDept(@PathVariable String deptId) {
		departmentRepository.deleteById(deptId);
		return deptId;
	}

	@PostMapping
	public User createUser(@RequestBody User user) {

		user = service.createUser(user);

		return user;

	}

	@GetMapping
	public List<User> getUsers() {

		List<User> users = service.getUsers();

		return users;

	}

	@GetMapping("userName/{userName}")
	public User getUserByUsername(@PathVariable("userName") String userName) {

		User user = service.getUserByUsername(userName);

		return user;

	}

}
