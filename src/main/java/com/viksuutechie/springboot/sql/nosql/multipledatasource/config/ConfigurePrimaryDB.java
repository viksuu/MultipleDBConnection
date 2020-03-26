package com.viksuutechie.springboot.sql.nosql.multipledatasource.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(transactionManagerRef = "primaryTransactionManager", entityManagerFactoryRef = "primaryEntityManagerFactory", basePackages = {
		"com.viksuutechie.springboot.sql.nosql.multipledatasource.repository.mysql" })
public class ConfigurePrimaryDB {
	@Primary
	@Bean(name = "primaryDSProps")
	@ConfigurationProperties("spring.datasource")
	public DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}

	@Primary
	@Bean(name = "primaryDataSource")
	@ConfigurationProperties("spring.datasource")
	public DataSource dataSource(@Qualifier("primaryDSProps") DataSourceProperties properties) {
		return properties.initializeDataSourceBuilder().build();
	}

	@Primary
	@Bean(name = "primaryEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("primaryDataSource") DataSource primaryDataSource) {

		return builder.dataSource(primaryDataSource)
				.packages("com.viksuutechie.springboot.sql.nosql.multipledatasource.repository.mysql")
				.persistenceUnit("User").build();

	}

	@Primary
	@Bean(name = "primaryTransactionManager")
	public PlatformTransactionManager primaryTransactionManager(
			@Qualifier("primaryEntityManagerFactory") EntityManagerFactory primaryEntityManagerFactory) {
		return new JpaTransactionManager(primaryEntityManagerFactory);
	}
}
