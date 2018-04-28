package com.ayush.input.utils;

import org.apache.log4j.Logger;

import com.ayush.model.UserDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class InputRequestUtil {
	private static final Logger logger = Logger.getLogger(InputRequestUtil.class);

	public static UserDetails getUserDetails() {
		UserDetails user = new UserDetails();
		user.setUsername("ayush");
		user.setPassword("pa$$word");
		return user;
	}

	public static String getInputRequest(UserDetails user) {
		ObjectMapper objMapper = new ObjectMapper();
		try {
			return objMapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			logger.error("Unable to covert object to string: "+user);
		}
		return null;
	}
}
