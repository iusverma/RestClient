package com.ayush.restclients;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.ayush.input.utils.Constants;
import com.ayush.input.utils.InputRequestUtil;
import com.ayush.model.UserDetails;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyRestPostClient {

	private static final Logger logger = Logger.getLogger(JerseyRestGetClient.class);

	public static void execute() {
		Client webClient = Client.create();
		WebResource uri = webClient.resource(Constants.POST_URI_V1_LOGIN);

		UserDetails user = InputRequestUtil.getUserDetails();

		//String input = "{\"username\":\"ayush\",\"password\":\"pa$$word\"}";
		String input = InputRequestUtil.getInputRequest(user);

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
}
