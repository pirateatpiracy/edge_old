package com.leadics.TextGuruDummy.rest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;




@Path("/sms")
public class TextGuru {
	
	//public static Map<Long,OtpGeneration>  phoneAndOtpMap = new HashMap<Long,OtpGeneration>();
	private static final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static String username="leadics_com";
	static String password="34457836";
	
	//"username="+prop.getProperty("username")
	
	static{
		Properties prop = new Properties();
		InputStream input = null;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		input = classLoader.getResourceAsStream("config.properties");		
		try {
			prop.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
   
	
	@GET
	@Path("/mobile")
	@Produces(MediaType.APPLICATION_JSON)
	public Response send(@QueryParam("username")String username , @QueryParam("password")String password,@QueryParam("source")String source , @QueryParam("dmobile")String dmobile, @QueryParam("message")String message) {
		Response response = null;
		try {
			if (this.username.equals(username) && this.password.equals(password)) {
				response = Response.status(Status.OK).build();
				System.out.println(message);
			} else {
				response = Response.status(Status.UNAUTHORIZED).build();
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	
	
	









}
