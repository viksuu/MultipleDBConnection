package com.viksuutechie.springboot.sql.nosql.multipledatasource.repository.mongodb;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.viksuutechie.springboot.sql.nosql.multipledatasource.model.Department;

@Repository
public interface DepartmentMongoRepository extends MongoRepository<Department,String> {

    @Query(value = "{'employees.name': ?0}", fields = "{'employees' : 0}")
    Department findDepartmentByEmployeeName(String empName);

    List findDepartmentByName(String name);
}