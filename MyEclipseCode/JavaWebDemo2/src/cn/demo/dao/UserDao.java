package cn.demo.dao;

import cn.demo.domain.User;

public interface UserDao {

	void add(User user);

	User find(String username, String password);

	boolean find(String username);

}