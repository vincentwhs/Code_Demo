package junit.test;

import java.util.Date;

import org.junit.Test;

import cn.demo.dao.UserDao;
import cn.demo.dao.impl.UserDaoImpl;
import cn.demo.domain.User;

public class TestDemo1 {

	@Test
	public void testadd(){
		User user = new User();
		
		user.setEmail("email");
		user.setId("22222222222");
		user.setUsername("name");
		user.setPassword("123");
		user.setNickname("nickname");
		user.setBirthday(new Date());
		
		UserDao impl = new UserDaoImpl();
		impl.add(user);
	}
}
