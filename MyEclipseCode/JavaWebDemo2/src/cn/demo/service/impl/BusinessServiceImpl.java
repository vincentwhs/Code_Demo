package cn.demo.service.impl;

import static org.hamcrest.CoreMatchers.nullValue;

import cn.demo.dao.UserDao;
import cn.demo.dao.impl.UserDaoImpl;
import cn.demo.domain.User;
import cn.demo.exception.UserExistException;
import cn.demo.utils.ServiceUtils;

public class BusinessServiceImpl {
	
	private UserDao dao = new UserDaoImpl();

	public void register(User user) throws UserExistException{
		boolean b = dao.find(user.getUsername());
		
		if (b) {
			throw new UserExistException();
		}else {
			
			user.setPassword(ServiceUtils.md5(user.getPassword()));
			
			dao.add(user);
		}
	}
	
	public  User login(String username,String password) {
		
		password = ServiceUtils.md5(password);
		return dao.find(username, password);
	}
}
