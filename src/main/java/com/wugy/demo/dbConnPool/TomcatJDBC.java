package com.wugy.demo.dbConnPool;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

public class TomcatJDBC {

	private static DataSource datasource;

	static {
		initDataSource();
	}

	public static void initDataSource() {
		long start;
		long end;
		long executionTime;
		start = System.currentTimeMillis();

		PoolProperties p = new PoolProperties();
		p.setUrl(JdbcConfig.DB_URL);
		p.setDriverClassName(JdbcConfig.DRIVER_CLASSNAME);
		p.setUsername(JdbcConfig.DB_NAME);
		p.setPassword(JdbcConfig.DB_PWD);

		p.setInitialSize(JdbcConfig.INIT_SIZE);
		p.setMaxActive(JdbcConfig.MAX_ACTIVE);
		p.setMinIdle(JdbcConfig.MIN_ACTIVE);
		p.setMaxIdle(JdbcConfig.MAX_ACTIVE);

		// 心跳重连配置

		// p.setTestWhileIdle(true);
		// p.setValidationQuery("SELECT 1");
		// p.setValidationInterval(10000);

		// 回收空链接配置
		// p.setRemoveAbandoned(true);
		// p.setRemoveAbandonedTimeout(10);
		// p.setTimeBetweenEvictionRunsMillis(10000);

		p.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"
				+ "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");

		// p.setJmxEnabled(true);
		// p.setTestOnBorrow(true);
		// p.setTestOnReturn(false);
		// p.setMaxWait(10000);
		// p.setMinEvictableIdleTimeMillis(30000);
		// p.setLogAbandoned(true);

		datasource = new DataSource();
		datasource.setPoolProperties(p);

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
		System.out.println("TomcatJDBC setup DataSource  : " + executionTime + " ms");
	}

	public static DataSource getDatasource() {
		return datasource;
	}

}
