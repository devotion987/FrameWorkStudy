package com.wugy.demo.dbConnPool;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public class Dbcp extends Thread {

	private static BasicDataSource datasource;
	static{
		initDataSource();
	}
	public static void initDataSource() {
		long start;
		long end;
		long executionTime;
		start = System.currentTimeMillis();

		datasource = new BasicDataSource();
		datasource.setUrl(JdbcConfig.DB_URL);
		datasource.setDriverClassName(JdbcConfig.DRIVER_CLASSNAME);
		datasource.setUsername(JdbcConfig.DB_NAME);
		datasource.setPassword(JdbcConfig.DB_PWD);
		
		datasource.setInitialSize(JdbcConfig.INIT_SIZE);
		datasource.setMaxActive(JdbcConfig.MAX_ACTIVE);
		datasource.setMinIdle(JdbcConfig.MIN_ACTIVE);
		datasource.setMaxIdle(JdbcConfig.MAX_ACTIVE);

		//心跳重连配置
//		datasource.setTestWhileIdle(false);
//		datasource.setValidationQuery("SELECT 1");
//		datasource.setTimeBetweenEvictionRunsMillis(10000);

		//回收空链接配置
//		datasource.setRemoveAbandoned(true);
//		datasource.setRemoveAbandonedTimeout(5);
//		datasource.setMinEvictableIdleTimeMillis(30000);

//		ds.setTestOnBorrow(true);
//		ds.setTestOnReturn(false);
//		ds.setMaxWait(10000);
//		ds.setLogAbandoned(true);
		

		for (int i = 0; i < 10; i++) {
			Connection con = null;
			try {
				con = datasource.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		end = System.currentTimeMillis();
		executionTime = end - start;
		System.out.println("DBCP setup DataSource  : " + executionTime + " ms");
		
	}
	public static BasicDataSource getDatasource() {
		return datasource;
	}
}
