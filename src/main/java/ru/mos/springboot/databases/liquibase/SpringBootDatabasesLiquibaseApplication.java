package ru.mos.springboot.databases.liquibase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;

@SpringBootApplication(exclude = LiquibaseAutoConfiguration.class)
public class SpringBootDatabasesLiquibaseApplication {

    public static void main(String[] args) {
	SpringApplication.run(SpringBootDatabasesLiquibaseApplication.class, args);
    }

}
