package com.wugy.demo.dbConnPool;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.sql.DataSource;

import org.junit.Test;

public class TestStability {

	@Test
	public void testStability() {
		String sql = "select * from user where name='1'";
		Timer timer = new Timer();
		// timer.scheduleAtFixedRate(new DBCP_Task(sql), new Date(), 10000);
		// timer.scheduleAtFixedRate(new C3P0_TASK(sql), new Date(), 10000);
		timer.scheduleAtFixedRate(new TomcatJdbcTask(sql), new Date(), 10000);

		System.out.println("===============start===================");

		try {
			Thread.sleep(86400000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class DbcpTask extends TimerTask {

	private DataSource dataSource = Dbcp.getDatasource();

	private String sql;

	public DbcpTask(String sql) {
		this.sql = sql;
	}

	@Override
	public void run() {
		for (int i = 0; i < 1; i++) {
			new Execute(this.sql, 1, dataSource).start();
		}
		System.out.println("DBCP_Task done");
	}
}

class C3p0Task extends TimerTask {

	private DataSource dataSource = C3p0.getDatasource();

	private String sql;

	public C3p0Task(String sql) {
		this.sql = sql;
	}

	@Override
	public void run() {
		for (int i = 0; i < 1; i++) {
			new Execute(this.sql, 1, dataSource).start();
		}
		System.out.println("C3P0_TASK done");
	}
}

class TomcatJdbcTask extends TimerTask {

	private DataSource dataSource = TomcatJDBC.getDatasource();

	private String sql;

	public TomcatJdbcTask(String sql) {
		this.sql = sql;
	}

	@Override
	public void run() {
		for (int i = 0; i < 1; i++) {
			new Execute(this.sql, 1, dataSource).start();
		}
		System.out.println("TomcatJDBC_Task done");
	}
}