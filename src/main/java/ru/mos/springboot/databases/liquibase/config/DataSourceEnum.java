package ru.mos.springboot.databases.liquibase.config;

public enum DataSourceEnum {

    EMWEB, DISTRICT;

    private static final ThreadLocal<DataSourceEnum> CURRENT_DATA_SOURCE = new ThreadLocal<>();

    public static DataSourceEnum getCurrentDataSource() {
	return CURRENT_DATA_SOURCE.get();
    }

    public static void setCurrentDataSource(DataSourceEnum name) {
	CURRENT_DATA_SOURCE.set(name);
    }

    public static void clear() {
	CURRENT_DATA_SOURCE.remove();
    }

}
