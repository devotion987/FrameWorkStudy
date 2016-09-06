package com.wugy.demo.dbConnPool;

import java.util.Scanner;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class TestWacthPool {

	private static BasicDataSource DBCP_DS = Dbcp.getDatasource();
	private static ComboPooledDataSource C3P0_DS = C3p0.getDatasource();
	private static DataSource tomcatJDBC_DS = TomcatJDBC.getDatasource();

	private static int EXECUTE_COUNT = 1;
	private static String SQL = "select * from user where name='1'";

	public void watchDbcp() {
		new Execute(SQL, EXECUTE_COUNT, DBCP_DS).run();
		System.out.println("=================================");
		System.out.println("******         DBCP        ******");
		System.out.println("Active : " + DBCP_DS.getNumActive());
		System.out.println("Idle   : " + DBCP_DS.getNumIdle());
		System.out.println("=================================");
	}

	public void watchC3p0() {
		new Execute(SQL, EXECUTE_COUNT, C3P0_DS).run();
		System.out.println("=================================");
		System.out.println("******         C3P0        ******");
		// System.out.println("Active : " + C3P0.);
		// System.out.println("Idle : " + C3P0.getNumIdle());
		System.out.println("=================================");
	}

	public void watchTomcatJdbc() {
		new Execute(SQL, EXECUTE_COUNT, tomcatJDBC_DS).run();
		System.out.println("=================================");
		System.out.println("******      TomcatJDBC     ******");
		System.out.println("Active : " + tomcatJDBC_DS.getActive());
		System.out.println("Idle   : " + tomcatJDBC_DS.getIdle());
		System.out.println("=================================");
	}

	@SuppressWarnings({ "resource" })
	@Test
	public void testWatchPool() {
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("请选择执行的连接池，1：DBCP 2：C3P0 3：TomcatJDBC");
			int type = sc.nextInt();
			switch (type) {
			case 1:
				watchDbcp();
				break;
			case 2:
				watchC3p0();
				break;
			case 3:
				watchTomcatJdbc();
				break;
			default:
				System.out.println("选择非法，程序自动退出！");
				System.exit(0);
			}
		}
	}
}
