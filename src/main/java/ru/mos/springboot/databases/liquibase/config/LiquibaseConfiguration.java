package ru.mos.springboot.databases.liquibase.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.task.TaskExecutor;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@AllArgsConstructor
@ConditionalOnProperty(prefix = "spring.liquibase", name = "enabled", matchIfMissing = true)
@EnableConfigurationProperties(LiquibaseProperties.class)
public class LiquibaseConfiguration {

    private LiquibaseProperties properties;

    private DataSourceProperties dataSourceProperties;

    @Bean
    @DependsOn("routingDataSource")
    public MultiNameDataSourceSpringLiquibase liquibaseMultiTenancy(Map<Object, Object> dataSources, @Qualifier("taskExecutor") TaskExecutor taskExecutor) {
	// to run changeSets of the liquibase asynchronous
	MultiNameDataSourceSpringLiquibase liquibase = new MultiNameDataSourceSpringLiquibase(taskExecutor);
	dataSources.forEach((name, dataSource) -> liquibase.addDataSource((DataSourceEnum) name, (DataSource) dataSource));
	dataSourceProperties.getDatasource().forEach(dbProperty -> {
	    if (dbProperty.getLiquibase() != null) {
		liquibase.addLiquibaseProperties(dbProperty.getName(), dbProperty.getLiquibase());
	    }
	});

	liquibase.setContexts(properties.getContexts());
	liquibase.setChangeLog(properties.getChangeLog());
	liquibase.setDefaultSchema(properties.getDefaultSchema());
	liquibase.setDropFirst(properties.isDropFirst());
	liquibase.setShouldRun(properties.isEnabled());
	return liquibase;
    }

}
