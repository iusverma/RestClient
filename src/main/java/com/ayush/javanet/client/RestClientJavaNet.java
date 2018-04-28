package com.ayush.javanet.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.ayush.input.utils.Constants;
import com.ayush.input.utils.InputRequestUtil;

public class RestClientJavaNet {
	private static final Logger logger = Logger.getLogger(RestClientJavaNet.class);
	public static void execute() {
		try {
			URL url = new URL(Constants.POST_URI_V1_LOGIN);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty(Constants.CONTENT_TYPE,MediaType.APPLICATION_JSON);

			String input = InputRequestUtil.getInputRequest(
					InputRequestUtil.getUserDetails());

			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			
			if(conn.getResponseCode()!=HttpURLConnection.HTTP_CREATED){
				logger.error("Error while connecting to service: "+ conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			logger.debug("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();
		}catch(Exception e) {
			logger.error("Error while opening connection: "+e.getMessage());
		}
	}
}
