package ru.mos.springboot.databases.liquibase.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@ConfigurationProperties("spring")
public class DataSourceProperties {

    private List<DataSourceProperty> datasource = new ArrayList<>();

    @Data
    public static class DataSourceProperty {
	private DataSourceEnum name;
	private String driverClassName;
	private String url;
	private String username;
	private String password;
	private LiquibaseProperties liquibase;
    }

}
