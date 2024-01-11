package ru.mos.springboot.databases.liquibase.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
	return DataSourceEnum.getCurrentDataSource();
    }

}
