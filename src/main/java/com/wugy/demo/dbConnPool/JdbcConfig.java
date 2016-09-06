package com.wugy.demo.dbConnPool;

public interface JdbcConfig {

	String DB_NAME = "root";
	String DB_PWD = "wugy";
	String DB_URL = "jdbc:mysql://127.0.0.1:3306/activiti_demo";
	String DRIVER_CLASSNAME = "com.mysql.jdbc.Driver";

	int MAX_ACTIVE = 100;
	int MIN_ACTIVE = 10;
	int INIT_SIZE = 10;
}
