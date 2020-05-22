package com.example.spring;

import com.example.spring.dao.FakeRepoInterface;
import com.example.spring.model.User;
import com.example.spring.service.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Assert;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ThreadLocalRandom;

//@SpringBootTest
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {
	FakeRepoInterface fakeRepoInterface = new FakeRepoInterface() {
		@Override
		public String insertUser(Long id, User user) {
			return user.getName();
		}

		@Override
		public String findUserById(Long id) {
			return "Mphuluseni";
		}

		@Override
		public String deleteUser(Long id) {
			return "Mphuluseni";
		}
	};
	UserServiceImpl userService = new UserServiceImpl(fakeRepoInterface);
	@Test
	void contextLoads() {

	}

	@Test
	void addUser(){
		Long id = ThreadLocalRandom.current().nextLong(10,100);
		String name = "Mphuluseni";
		String surname = "Khwathisi";
		User user = new User(id, name, surname);

		Assert.assertEquals("Mphuluseni entered",userService.addUser(user));
	}

	@Test
	void removeUser(){
		String  l = "1";
		Long id = Long.parseLong(l);
		String name = "Mphuluseni";
		String surname = "Khwathisi";
		User user = new User(id, name, surname);
		userService.addUser(user);
		Assert.assertEquals("Mphuluseni removed",userService.removeUser(id));
	}

	@Test
	void getUser(){
		String  l = "1";
		Long id = Long.parseLong(l);
		String name = "Mphuluseni";
		String surname = "Khwathisi";
		User user = new User(id, name, surname);
		userService.addUser(user);
		Assert.assertEquals("hello Mphuluseni",userService.getUser(id));
	}

	@Test
	void getUser4(){
		String  l = "1";
		Long id = Long.parseLong(l);
		userService.getUser(id);
		userService.getUser(id);
		userService.getUser(id);
		userService.getUser(id);

		String expected = userService.getUser(id) + "\n" +
				userService.getUser(id)+ "\n" +
				userService.getUser(id)+ "\n" +
				userService.getUser(id);
		Assert.assertEquals("hello Mphuluseni",userService.getUser(id));
	}

	@Test
	@Autowired
	void userSecurityTest(){
		try{
			 TestRestTemplate template = null;
			ResponseEntity<String> result = template.withBasicAuth("Mphuluseni", "1234")
					.getForEntity("/user", String.class);
			Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
		}catch(Exception e){
			System.out.println("Error: "+e);
		}

	}

}
