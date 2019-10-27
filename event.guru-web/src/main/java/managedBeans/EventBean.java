package managedBeans;
import java.io.IOException;
import java.util.Date;
import java.util.List;


import javax.faces.bean.ManagedBean;


import event.guru.DTO.*;
import event.guru.services.EventService;

@ManagedBean

public class EventBean {
	private int EventId;
	private String Name; 
	private String Picture;
	private String Theme;
	private String Location;
	private Date StartDate;
	private Date EndDate;
	private List<Event> Event;
	private static final long serialVersionUID = 1L;
	
	EventService E = new EventService();
	
	
	public List<Event> getEvent() {
		return Event;
	}


	public void setEvent(List<Event> event) {
		Event = event;
	}


	public List<Event> getEvents() {
		Event=E.GetAll();
		return Event;
	}
	
	public String supprimer(Event e){
		E.Delete(e);
		return "/event?faces-redirect=true";
		
		
	}
	
	public String addEvent()  {
		E.Create(new Event(Name, Picture,
		 Theme,
		 Location,
		 StartDate,
		 EndDate
		 ));
		
		return "event.jsf";

	}
	
public String Modifier(Event e) throws IOException{
		
		this.setEventId(e.getEventId());
		this.setName(e.getName());
		this.setPicture(e.getPicture());
		this.setTheme(e.getTheme());
		this.setLocation(e.getLocation());
		this.setStartDate(e.getStartDate());
		this.setEndDate(e.getEndDate());
		
		return "edit.jsf";
		
		
	}
	
	public String MAJEvent(int id){
		 Event p = E.findById(id);
		 
		 	p.setEventId(EventId);
			p.setName(Name);
			p.setPicture(Picture);
			p.setTheme(Theme);
			p.setLocation(Location);
			p.setStartDate(StartDate);
			p.setEndDate(EndDate);
		E.Update(p,id);
		 
		 return "event.jsf";
	}
	
	



	public int getEventId() {
		return EventId;
	}


	public void setEventId(int eventId) {
		EventId = eventId;
	}


	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}


	public String getPicture() {
		return Picture;
	}


	public void setPicture(String picture) {
		Picture = picture;
	}


	public String getTheme() {
		return Theme;
	}


	public void setTheme(String theme) {
		Theme = theme;
	}


	public String getLocation() {
		return Location;
	}


	public void setLocation(String location) {
		Location = location;
	}


	public Date getStartDate() {
		return StartDate;
	}


	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}


	public Date getEndDate() {
		return EndDate;
	}


	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}


	public EventService getE() {
		return E;
	}


	public void setE(EventService e) {
		E = e;
	}
	
	
}
