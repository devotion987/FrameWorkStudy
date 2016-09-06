package com.wugy.demo.dbConnPool;

import java.util.Properties;

import javax.sql.DataSource;

public class TestPerformance {

	private static void testDbcp(String sql, int threadCount, int executeCount) {
		// init DataSource
		DataSource dataSource = Dbcp.getDatasource();
		System.out.println("DBCP start : " + System.currentTimeMillis());
		for (int i = 0; i < threadCount; i++) {
			new Execute(sql, executeCount, dataSource).start();
		}
	}

	private static void testC3p0(String sql, int threadCount, int executeCount) {
		// init DataSource
		DataSource dataSource = C3p0.getDatasource();
		System.out.println("C3P0 start : " + System.currentTimeMillis());
		for (int i = 0; i < threadCount; i++) {
			new Execute(sql, executeCount, dataSource).start();
		}
	}

	private static void testTomcatJdbc(String sql, int threadCount, int executeCount) {
		// init DataSource
		DataSource dataSource = TomcatJDBC.getDatasource();
		System.out.println("TomcatJDBC start : " + System.currentTimeMillis());
		for (int i = 0; i < threadCount; i++) {
			new Execute(sql, executeCount, dataSource).start();
		}
	}

	private static void testProxool(String sql, int threadCount, int executeCount) {
		// init
		try {
			Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Properties properties = new Properties();
		properties.setProperty("proxool.maximum-connection-count", "20");
		properties.setProperty("proxool.house-keeping-test-sql", "select CURRENT_DATE");
		properties.setProperty("user", JdbcConfig.DB_NAME);
		properties.setProperty("password", JdbcConfig.DB_PWD);
		String alias = "aub";
		String driverClass = JdbcConfig.DRIVER_CLASSNAME;
		String driverUrl = JdbcConfig.DB_URL;
		String url = "proxool." + alias + ":" + driverClass + ":" + driverUrl;

		long start;
		long end;
		long executionTime;
		start = System.currentTimeMillis();
		for (int i = 0; i < threadCount; i++) {
			new Proxool(sql, executeCount, url, properties).start();
		}
		end = System.currentTimeMillis();
		executionTime = end - start;
		System.out.println("Proxool total  : " + executionTime + " ms");
		System.out.println("Proxool average: " + executionTime / (threadCount * executeCount) + " ms");
	}

	public static void main(String[] args) {
		if (args.length != 4) {
			System.out.println("args lenth error : " + args.length);
			return;
		}
		int type = Integer.valueOf(args[0]);
		int threadCount = Integer.valueOf(args[1]);
		int executeCount = Integer.valueOf(args[2]);
		String sql = args[3];
		System.out.println("type        : " + type);
		System.out.println("threadCount : " + threadCount);
		System.out.println("executeCount: " + executeCount);
		System.out.println("sql         : " + sql);

		switch (type) {
		case 1:
			System.out.println("===================================");
			testDbcp(sql, threadCount, executeCount);
			break;
		case 2:
			System.out.println("===================================");
			testC3p0(sql, threadCount, executeCount);
			break;
		case 3:
			System.out.println("===================================");
			testTomcatJdbc(sql, threadCount, executeCount);
			break;
		case 4:
			System.out.println("===================================");
			testProxool(sql, threadCount, executeCount);
			break;
		default:
			System.out.println("===================================");
			System.out.println("unkown type : " + type);
			break;
		}
	}

}
