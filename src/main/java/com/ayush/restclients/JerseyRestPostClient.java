package com.ayush.restclients;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.ayush.model.UserDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyRestPostClient {

	private static final Logger logger = Logger.getLogger(JerseyRestGetClient.class);

	public static void execute() {
		Client webClient = Client.create();
		WebResource uri = webClient.resource(Constants.POST_URI_V1_LOGIN);

		UserDetails user = getUserDetails();

		//String input = "{\"username\":\"ayush\",\"password\":\"pa$$word\"}";
		String input = getInputRequest(user);

		ClientResponse response = uri.header(Constants.CONTENT_TYPE, MediaType.APPLICATION_JSON)
									 .post(ClientResponse.class,input);

		// 4. check the response status code
		if(response.getStatus() != 200) {
			logger.error("Error in getting response from service: "+response.getStatus());
			return;
		}

		// 5. print response
		String output = response.getEntity(String.class).toString();
		logger.info("Service response: "+output);
	}

	private static UserDetails getUserDetails() {
		UserDetails user = new UserDetails();
		user.setUsername("ayush");
		user.setPassword("pa$$word");
		return user;
	}

	private static String getInputRequest(UserDetails user) {
		ObjectMapper objMapper = new ObjectMapper();
		try {
			return objMapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			logger.error("Unable to covert object to string: "+user);
		}
		return null;
	}
}
