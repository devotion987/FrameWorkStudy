package com.wugy.demo.activiti.service;

import com.wugy.demo.activiti.entity.User;

public interface IUserService {

	void insertUser(User user);

	User findByName(String name);
}
