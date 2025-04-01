package com.example.bootcamp.user.configuration.jpa;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.bootcamp.user.ports.driven.mysql.repository")
public class JpaConfig {
}
