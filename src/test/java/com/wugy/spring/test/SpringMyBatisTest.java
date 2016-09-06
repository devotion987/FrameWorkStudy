package com.wugy.spring.test;

import javax.sql.rowset.serial.SerialBlob;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wugy.demo.activiti.entity.User;
import com.wugy.demo.activiti.service.IUserService;

public class SpringMyBatisTest extends BaseTest {

	@Autowired
	private IUserService userService;

	@Test
	public void testAdd() throws Exception {
		User user = new User();
		user.setName("张三");
		user.setRemark(new SerialBlob("张三".getBytes()));
		userService.insertUser(user);
	}

	@Test
	public void testFindByName() {
		User user = userService.findByName("张");
		System.out.println(user);
	}
}
