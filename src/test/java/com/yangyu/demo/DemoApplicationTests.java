package com.yangyu.demo;

import java.util.Date;

import javax.annotation.Resource;

import com.yangyu.demo.dao.source1.AopLogDao;
import com.yangyu.demo.dao.source1.ClientDao;
import com.yangyu.demo.dao.source1.UserDao;
import com.yangyu.demo.entity.source1.AopLogEntity;
import com.yangyu.demo.entity.source1.Client;
import com.yangyu.demo.entity.source1.User;
import com.yangyu.demo.service.UserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DemoApplicationTests {

	@Resource
	private ClientDao clientDao;
	@Resource
	private UserDao userDao;
	@Resource
	private UserService userService;
	@Resource
	private AopLogDao aopLogDao;

	@Test
	public void contextLoads() {
		log.info(new BCryptPasswordEncoder().encode("123456"));
	}

	@Test
	public void clientTest() {
		Client client = clientDao.findById("7C8560DD-D51B-4D0F-9429-6DF5BD84CDFE");
		// Collection<GrantedAuthority> x = client.getAuthorities();
		System.out.println(client);
	}

	@Test
	public void getUser() {
		User x = userDao.findByLoginName("yangyu");
		// Collection<GrantedAuthority> x = client.getAuthorities();
		// userService.save(x);
		System.out.println(x);
	}

	@Test
	public void addUserTesst() {
		User user= new User();
		user.setId("test1");
		user.setLoginName("test1");
		user.setName("test1");
		user.setEnname("test1");
		user.setPassword(new BCryptPasswordEncoder().encode("password"));
		userDao.insertEntity(user);
	}

	@Test
	public void addAopLog() {
		AopLogEntity aoplog = new AopLogEntity();
		aoplog.setId("98E42F7D-970E-41FA-B128-AD42EF347B0A");
		aoplog.setActive(false);
		aoplog.setDescription("测试");
		log.info("修改前");
		aopLogDao.updateById(aoplog);
		log.info("修改后");
	}

}
