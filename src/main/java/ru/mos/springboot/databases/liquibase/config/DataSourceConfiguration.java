package ru.mos.springboot.databases.liquibase.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.util.Pair;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "ru.mos.springboot.databases.liquibase")
public class DataSourceConfiguration {

    @Bean(name = "dataSources")
    @Primary
    public Map<Object, Object> getDataSources(DataSourceProperties dataSourceProperties) {
	return dataSourceProperties.getDatasource().stream().map(sourceProperty -> {
	    DataSource dataSource = DataSourceBuilder.create()
			    .url(sourceProperty.getUrl())
			    .username(sourceProperty.getUsername())
			    .password(sourceProperty.getPassword())
			    .driverClassName(sourceProperty.getDriverClassName())
			    .type(HikariDataSource.class)
			    .build();
	    return Pair.of(sourceProperty.getName(), dataSource);
	}).collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
    }

    @Bean(name = "routingDataSource")
    @DependsOn("dataSources")
    public DataSource dataSource(Map<Object, Object> dataSources) {
	AbstractRoutingDataSource routingDataSource = new RoutingDataSource();
	routingDataSource.setTargetDataSources(dataSources);
	routingDataSource.setDefaultTargetDataSource(dataSources.get(DataSourceEnum.EMWEB));
	routingDataSource.afterPropertiesSet();
	return routingDataSource;
    }

}
