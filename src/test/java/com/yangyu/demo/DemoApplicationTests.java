package com.yangyu.demo;

import java.util.Collection;
import java.util.Set;

import com.yangyu.demo.entity.source1.AopLogEntity;
import com.yangyu.demo.entity.source1.Client;
import com.yangyu.demo.entity.source1.User;
import com.yangyu.demo.repository.source1.AopLogRep;
import com.yangyu.demo.repository.source1.ClientRep;
import com.yangyu.demo.repository.source1.UserRep;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DemoApplicationTests {

	@Autowired
	private ClientRep clientRep;
	@Autowired
	private UserRep userRep;
	@Autowired
	private AopLogRep aopLogRep;

	@Test
	public void contextLoads() {
		log.info(new BCryptPasswordEncoder().encode("123456"));
	}

	@Test
	public void clientTest() {
		Client client = clientRep.findById("7C8560DD-D51B-4D0F-9429-6DF5BD84CDFE").get();
		Collection<GrantedAuthority> x = client.getAuthorities();
		System.out.println(x);
	}

	@Test
	public void addUserTesst() {
		User user= new User();
		user.setLoginName("test");
		user.setName("test");
		user.setPassword(new BCryptPasswordEncoder().encode("password"));
		userRep.save(user);
	}

	@Test
	public void addAopLog() {
		AopLogEntity aoplog= new AopLogEntity();
		aoplog.getId();
		log.info(aoplog.getId());
		aopLogRep.save(aoplog);
	}

}
