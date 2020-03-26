package com.viksuutechie.springboot.sql.nosql.multipledatasource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.viksuutechie.springboot.sql.nosql.multipledatasource.model.Department;
import com.viksuutechie.springboot.sql.nosql.multipledatasource.model.User;
import com.viksuutechie.springboot.sql.nosql.multipledatasource.repository.mysql.UserMysqlRepository;


@Service
public class ServiceBusinessImpl {

    @Autowired
    private MongoTemplate mongoTemplate;
    
    @Autowired
    private UserMysqlRepository userMysqlRepository;

    public List findAll() {
        return mongoTemplate.findAll(Department.class);
    }

    public Department save(Department department) {
        mongoTemplate.save(department);
        return department;
    }

    public Department update(Department department){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(department.getId()));
        Update update = new Update();
        update.set("name", department.getName());
        update.set("description", department.getDescription());
        return mongoTemplate.findAndModify(query, update, Department.class);
    }

    public List findDepartmentByName(String deptName){
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(deptName));
        return mongoTemplate.find(query, Department.class);
    }

    public void deleteById(String deptId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(deptId));
        mongoTemplate.remove(query, Department.class);
    }
    
    public User createUser(User user) {
		user =  userMysqlRepository.save(user);
		return user;
	}
	
	public List<User> getUsers() {

		List<User> usersList = userMysqlRepository.findAll();

		return usersList;
	}

	public User getUserByUsername(String userName) {

		User user = userMysqlRepository.findByUserName(userName);

		return user;
	}

}
