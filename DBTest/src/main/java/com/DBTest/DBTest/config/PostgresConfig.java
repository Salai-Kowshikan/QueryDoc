package com.DBTest.DBTest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.DBTest.DBTest.repository.Postgres")
public class PostgresConfig {
}