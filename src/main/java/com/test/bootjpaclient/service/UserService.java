package com.test.bootjpaclient.service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.test.bootjpaclient.domain.AppConstants;
import com.test.bootjpaclient.domain.User;

@Service
public class UserService {

	@Autowired
	private RestTemplate restTemplate;

	Logger logger = LoggerFactory.getLogger(UserService.class);

	public User getUser(int uid) {
		final String uri = AppConstants.END_URL + "user";

		ResponseEntity<User> responseEntity = restTemplate.getForEntity(uri + "/" + uid, User.class);
		logger.info("user :" + responseEntity.getBody());
		return responseEntity.getBody();
	}

	public User getUserWithExchange(int uid) {
		final String uri = AppConstants.END_URL + "user";

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<User> responseEntity = restTemplate.exchange(uri + "/" + uid, HttpMethod.GET, entity,
				User.class);
		logger.info("user age :" + responseEntity.getBody().getAge());
		return responseEntity.getBody();
	}

	public List<User> getAllUsers() {
		final String uri = AppConstants.END_URL + "user/getAllUsers";

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<List<User>> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity,
				new ParameterizedTypeReference<List<User>>() {
				});
		logger.info("user status" + responseEntity.getStatusCode());
		return responseEntity.getBody();
	}

	public String save(User user) {
		final String uri = AppConstants.END_URL + "user/";

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<User> entity = new HttpEntity<User>(user, headers);

		ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
		logger.info("user :" + responseEntity.getBody());
		return responseEntity.getBody();
	}

}
