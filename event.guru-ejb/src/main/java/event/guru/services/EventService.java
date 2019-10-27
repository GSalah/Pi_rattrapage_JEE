package event.guru.services;

import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import javax.persistence.EntityManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import event.guru.DTO.Event;
import event.guru.interfaces.EventServiceRemote;


@Stateful
@LocalBean
public class EventService implements EventServiceRemote {
	
	
	EntityManager em ;
	@Override
	public List<Event> GetAll() {
		//Client client = ClientBuilder.newClient();
		//WebTarget target = client.target("http://localhost:31618/api/PubWebApi/");
		//WebTarget hello =target.path("");
		//Response response =hello.request().get();
		
		//String result=response.readEntity(String.class);
		
		//PublicationDTO[] pubs =  response.readEntity(PublicationDTO[].class);

		//response.close();
		//return pubs;
		List<Event>  lasp = new ArrayList<Event>();
    	Client client = ClientBuilder.newClient();
    	
    	WebTarget web = client.target("http://10.0.0.1:4640/api/EventWebApi/"); 
    	
    	Response response = web.request().get();
    	
    	String result = response.readEntity(String.class); 
    	
    	//System.out.println(result);
    	JsonReader jsonReader = Json.createReader(new StringReader(result));
    	JsonArray object =  jsonReader.readArray();
    	
    	 
    	for (int i=0;i<object.size();i++)
    	{
    	 
    		Event m = new Event();
    	 //String dateString;
       	 m.setEventId(object.getJsonObject(i).getInt("EventId")); 
    	 m.setName(object.getJsonObject(i).getString("Name")); 
    	 m.setPicture(object.getJsonObject(i).getString("Picture")); 
    	 m.setTheme(object.getJsonObject(i).getString("Theme")); 
    	 m.setLocation(object.getJsonObject(i).getString("Location"));
SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    	 
    	 String dateString;
    	 dateString = object.getJsonObject(i).get("StartDate").toString();
    	 dateString = object.getJsonObject(i).get("EndDate").toString();
    	dateString = dateString.replaceAll("\"", "");
    	 Date date = null;
    	 try {
        
    	  date = sourceFormat.parse(dateString);
    	   
    	 } 
    	 catch (ParseException e) {
    	   e.printStackTrace();
    	 }
    	 m.setStartDate(date);
    	 m.setEndDate(date);
    	
    	 lasp.add(m);
    	}
    	

return lasp;    	
	}

	

	@Override
	public void Create(Event p) {
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://10.0.0.1:4640/api/Create");
		WebTarget hello =target.path("");
		
		Response response =hello.request().post(Entity.entity(p, MediaType.APPLICATION_JSON) );
		
		
		String result=response.readEntity(String.class);
		System.out.println(result);
		
		

		response.close();		
	}
	
	
	

	    @Override
		public void Update(Event p , int id) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://10.0.0.1:4640/api/Update?id="+id);
		WebTarget hello =target.path("");
		
		Response response =hello.request().put(Entity.entity(p, MediaType.APPLICATION_JSON) );
		
		String result=response.readEntity(String.class);
		System.out.println(result);

		response.close();
		
	}
	
	@Override
	public void Delete(Event eve)
	{
		Client cl = ClientBuilder.newClient();
		WebTarget target = cl.target("http://10.0.0.1:4640/api/EventWebApi?id="+eve.getEventId()); 
		WebTarget hello = target.path("");     	
    	Response res=(Response) hello.request().delete();
	}



	@Override
	public Event findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}



	

}
