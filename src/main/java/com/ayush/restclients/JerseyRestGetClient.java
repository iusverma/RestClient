package com.ayush.restclients;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyRestGetClient {
	
	private static final Logger logger = Logger.getLogger(JerseyRestGetClient.class);

	public static void execute() {

		// 1. create a client
		Client webClient = Client.create();

		// 2. set resource uri to hit
		WebResource uri = webClient.resource(Constants.GET_URI);

		// 3. triggering get call
		ClientResponse response = uri.header(Constants.CONTENT_TYPE, MediaType.APPLICATION_JSON)
									 .get(ClientResponse.class);

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
