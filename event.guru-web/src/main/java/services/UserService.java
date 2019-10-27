package services;
import java.io.StringReader;
import java.util.List;

import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.glassfish.jersey.filter.LoggingFilter;

import entities.Token;
import entities.User;
import interfaces.UserBeanService;

public class UserService implements UserBeanService{

	public String GlobalEndPoint = "localhost:4640";
	@Override
	public List<User> GetAll() {
	    return null;
	}

	@Override
	public void Delete(String Id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Create(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User findById(String Id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String TenantRegister(String email, String password, String confirm_password, String tenant_name) {
		Form form = new Form();
        form.param("Email", email);
        form.param("Password", password);
        form.param("ConfirmPassword", confirm_password);
        form.param("TenantName", tenant_name);
        JerseyClientBuilder client = new JerseyClientBuilder().register(new LoggingFilter(java.util.logging.Logger.getAnonymousLogger(), true));
        JerseyWebTarget target =      client.build().target("http://"+GlobalEndPoint+"/api/tenant/create");        
        Response response = target.request().accept(MediaType.APPLICATION_FORM_URLENCODED).post(Entity.form(form));
        if (response.getStatus() == 201) {
        	return "ok";
        }
        return "ko";
	}
	
	@Override
	public String Register(String email, String password, String confirm_password) {
		Form form = new Form();
        form.param("Email", email);
        form.param("Password", password);
        form.param("ConfirmPassword", confirm_password);
        JerseyClientBuilder client = new JerseyClientBuilder().register(new LoggingFilter(java.util.logging.Logger.getAnonymousLogger(), true));
        JerseyWebTarget target =      client.build().target("http://"+GlobalEndPoint+"/api/tenant/create");        
        Response response = target.request().accept(MediaType.APPLICATION_FORM_URLENCODED).post(Entity.form(form));
        if (response.getStatus() == 201) {
        	return "ok";
        }
        return "ko";
	}

	@Override
	public User Login(String username, String password) {
		Form form = new Form();
        form.param("grant_type", "password");
        form.param("username", username);
        form.param("password", password);
        JerseyClientBuilder jerseyClientBuilder = new JerseyClientBuilder().register(new LoggingFilter(java.util.logging.Logger.getAnonymousLogger(), true));
        JerseyWebTarget jerseyWebTarget =      jerseyClientBuilder.build().target("http://"+GlobalEndPoint+"/oauth/token");        
        Response response = jerseyWebTarget.request().accept(MediaType.APPLICATION_FORM_URLENCODED).post(Entity.form(form));
        if (response.getStatus() == 200) {
        	Token token = response.readEntity(Token.class);
        	response.close();
        	String endpointURL ="http://"+GlobalEndPoint+"/api/tenant/user/"+username+"/";
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
    	    	return result;
    	    	
    	    }       	
        
        }
        return null;	    
	}
	
	


}
