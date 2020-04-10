package com.yangyu.demo;

import javax.annotation.Resource;

import com.yangyu.demo.entity.source1.AopLogEntity;
import com.yangyu.demo.entity.source1.Client;
import com.yangyu.demo.entity.source1.User;
import com.yangyu.demo.mapper.source1.AopLogMapper;
import com.yangyu.demo.mapper.source1.ClientMapper;
import com.yangyu.demo.mapper.source1.UserMapper;
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
	private ClientMapper clientMapper;
	@Resource
	private UserMapper userMapper;
	@Resource
	private UserService userService;
	@Resource
	private AopLogMapper aopLogMapper;

	@Test
	public void contextLoads() {
		log.info(new BCryptPasswordEncoder().encode("123456"));
	}

	@Test
	public void clientTest() {
		Client client = clientMapper.findById("7C8560DD-D51B-4D0F-9429-6DF5BD84CDFE");
		System.out.println("------------------------------");
		log.info(client.toString());
	}

	@Test
	public void getUser() {
		User x = userMapper.findByLoginName("yangyu");
		System.out.println(x);
	}

	@Test
	public void addUserTesst() {
		User user= new User();
		user.setLoginName("test1");
		user.setName("test1");
		user.setEnname("test1");
		user.setPassword(new BCryptPasswordEncoder().encode("password"));
		userMapper.insertEntity(user);
	}

	@Test
	public void addAopLog() {
		AopLogEntity aoplog = new AopLogEntity();
		aoplog.setActive(false);
		aoplog.setDescription("测试");
		log.info("修改前");
		aopLogMapper.insertEntity(aoplog);
		log.info(aoplog.getId());
		log.info("修改后");
	}

}
