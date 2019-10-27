package services;

import java.io.BufferedReader;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.hk2.utilities.reflection.Logger;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.glassfish.jersey.filter.LoggingFilter;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.specimpl.MultivaluedMapImpl;

import entities.Token;
import entities.User;
import entities.UserLogin;
import event.guru.DTO.Event;

public class test {
	
	public static void Logo() {
		Form form = new Form();
        form.param("grant_type", "password");
        form.param("username", "admin@acloudguru.com");
        form.param("password", "Azerty@12");
        JerseyClientBuilder jerseyClientBuilder = new JerseyClientBuilder().register(new LoggingFilter(java.util.logging.Logger.getAnonymousLogger(), true));
        JerseyWebTarget jerseyWebTarget =      jerseyClientBuilder.build().target("http://10.0.0.1:4640/oauth/token");        
        Response response = jerseyWebTarget.request().accept(MediaType.APPLICATION_FORM_URLENCODED).post(Entity.form(form));
        Token token = response.readEntity(Token.class);
        System.out.println(token.getAccessToken());
        //System.out.println(response);
	}
	
	public static String Login(String username, String password) {
		// TODO Auto-generated method stub
		Form form = new Form();
        form.param("grant_type", "password");
        form.param("username", username);
        form.param("password", password);
        JerseyClientBuilder jerseyClientBuilder = new JerseyClientBuilder().register(new LoggingFilter(java.util.logging.Logger.getAnonymousLogger(), true));
        JerseyWebTarget jerseyWebTarget =      jerseyClientBuilder.build().target("http://10.0.0.1:4640/oauth/token");        
        Response response = jerseyWebTarget.request().accept(MediaType.APPLICATION_FORM_URLENCODED).post(Entity.form(form));
        if (response.getStatus() == 200) {
        	Token token = response.readEntity(Token.class);
        	response.close();
        	String endpointURL ="http://10.0.0.1:4640/api/tenant/user/admin@acloudguru.com/";
        	JerseyClientBuilder client = new JerseyClientBuilder().register(new LoggingFilter(java.util.logging.Logger.getAnonymousLogger(), true));
        	JerseyWebTarget target =  client.build().target(endpointURL); 
    	    // Provide the authorization information
    	    target.register((ClientRequestFilter) requestContext -> {
    	    	 requestContext.getHeaders().add("Authorization", "Bearer "+token.getAccessToken());
    	    	});
    
    	    //Invocation.Builder invocationBuilder =  target.request();
    	    Response response_user = target.request().accept(MediaType.APPLICATION_JSON).get();
    	    if(response_user.getStatus() != 200) {
    	    	throw new RuntimeException("Failed : HTTP error code : "+ response_user.getStatus());
    	    }
    	    else {
    	    	User result = response_user.readEntity(User.class);
    	    	result.setToken(token.getAccessToken());
    	    	response_user.close();
    	    	return result.toString();
    	    	
    	    }       	
        	
        }
        return "access_denied";	    
	}
	
	
	public static String register() {
		Form form = new Form();
        form.param("Email", "test12322@test.com");
        form.param("Password", "Azerty@12");
        form.param("ConfirmPassword", "Azerty@12");
        form.param("TenantName", "Tenant");
        JerseyClientBuilder client = new JerseyClientBuilder().register(new LoggingFilter(java.util.logging.Logger.getAnonymousLogger(), true));
        JerseyWebTarget target =      client.build().target("http://10.0.0.1:4640/api/tenant/create");        
        Response response = target.request().accept(MediaType.APPLICATION_FORM_URLENCODED).post(Entity.form(form));
        if (response.getStatus() == 201) {
        	return "ok";
        }
        return "ko";
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String username = "admin@acloudguru.com";
		String password = "Azerty@12";
		//System.out.println(Login(username,password));
		System.out.println(register());
		//GetALL();
	}

}
