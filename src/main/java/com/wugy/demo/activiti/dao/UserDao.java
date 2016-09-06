package com.wugy.demo.activiti.dao;

import com.wugy.demo.activiti.entity.User;

public interface UserDao {

	void insertUser(User user);

	User findByName(String name);
}
