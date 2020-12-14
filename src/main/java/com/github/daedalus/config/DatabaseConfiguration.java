package com.github.daedalus.config;

import com.github.daedalus.Application;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.github.daedalus")
@EntityScan(basePackageClasses = Application.class)
@Configuration
public class DatabaseConfiguration {
}
