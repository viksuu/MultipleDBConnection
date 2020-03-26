package com.viksuutechie.springboot.sql.nosql.multipledatasource.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viksuutechie.springboot.sql.nosql.multipledatasource.model.User;
public interface UserMysqlRepository extends JpaRepository<User, Long> {
	User findByUserName(String userName);
}
