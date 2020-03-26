package com.viksuutechie.springboot.sql.nosql.multipledatasource.config;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = { "com.viksuutechie.springboot.sql.nosql.multipledatasource.repository.mongodb" })
public class ConfigureSecondaryDB {

	@Bean(name = "secondaryDSProps")
	@ConfigurationProperties("mongo.data")
	public MongoProperties dataSourceProperties() {
		return new MongoProperties();
	}

	@Bean(name = "mongoTemplate")
	public MongoTemplate secondaryMongoTemplate() throws Exception {
		return new MongoTemplate(secondaryFactory(dataSourceProperties()));
	}

	@Bean
	public MongoDbFactory secondaryFactory(final MongoProperties mongo) throws Exception {
		return new SimpleMongoClientDbFactory(mongo.getDatabase());
	}

}
