package com.wugy.demo.activiti.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wugy.demo.activiti.dao.UserDao;
import com.wugy.demo.activiti.entity.User;
import com.wugy.demo.activiti.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserDao userDao;

	@Override
	public void insertUser(User user) {
		userDao.insertUser(user);
	}

	@Override
	public User findByName(String name) {
		return userDao.findByName(name);
	}

}
