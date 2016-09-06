package com.wugy.demo.dbConnPool;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0 {

	private static ComboPooledDataSource datasource;
	static {
		initDataSource();
	}

	public static void initDataSource() {
		long start;
		long end;
		long executionTime;
		start = System.currentTimeMillis();

		datasource = new ComboPooledDataSource();
		try {
			datasource.setDriverClass(JdbcConfig.DRIVER_CLASSNAME);
		} catch (PropertyVetoException e1) {
			e1.printStackTrace();
		}
		datasource.setJdbcUrl(JdbcConfig.DB_URL);
		datasource.setUser(JdbcConfig.DB_NAME);
		datasource.setPassword(JdbcConfig.DB_PWD);

		datasource.setInitialPoolSize(JdbcConfig.INIT_SIZE);
		datasource.setMinPoolSize(JdbcConfig.MIN_ACTIVE);
		datasource.setMaxPoolSize(JdbcConfig.MAX_ACTIVE);

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
		System.out.println("C3P0 setup DataSource  : " + executionTime + " ms");
	}

	public static ComboPooledDataSource getDatasource() {
		return datasource;
	}
}
