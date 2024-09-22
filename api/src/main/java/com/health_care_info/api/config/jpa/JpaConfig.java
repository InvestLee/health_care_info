package com.health_care_info.api.config.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.health_care_info.db")
@EnableJpaRepositories(basePackages = "com.health_care_info.db")
public class JpaConfig {
}
