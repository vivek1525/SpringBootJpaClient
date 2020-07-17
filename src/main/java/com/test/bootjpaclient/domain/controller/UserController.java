package com.test.bootjpaclient.domain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.bootjpaclient.domain.User;
import com.test.bootjpaclient.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/{uid}")
	public User getUser(@PathVariable int uid) {
		User user = service.getUser(uid);
		return user;
	}
	
	@GetMapping("/withExchange/{uid}")
	public User getUserWithExchange(@PathVariable int uid) {
		User user = service.getUserWithExchange(uid);
		return user;
	}
	
	@GetMapping("/getAllUsers")
	public List<User> getAllUsers() {
		List<User> user = service.getAllUsers();
		return user;
	}
	
	@PostMapping("/")
	public String addUser(@RequestBody User user) {
		return service.save(user);
	}
}
