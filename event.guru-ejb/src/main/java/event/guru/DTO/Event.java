package event.guru.DTO;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

/**
 * Entity implementation class for Entity: Event
 *
 */


public class Event implements Serializable {

	  
	
	private int EventId;
	private String Name; 
	private String Picture;
	private String Theme;
	private String Location;
	private Date StartDate;
	private Date EndDate;
	private static final long serialVersionUID = 1L;

	public Event() {
		super();
	}   
	public String getName() {
		return this.Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}   
	public int getEventId() {
		return this.EventId;
	}

	public void setEventId(int EventId) {
		this.EventId = EventId;
	}   
	public String getPicture() {
		return this.Picture;
	}

	public void setPicture(String Picture) {
		this.Picture = Picture;
	}   
	public String getTheme() {
		return this.Theme;
	}

	public void setTheme(String Theme) {
		this.Theme = Theme;
	}   
	public String getLocation() {
		return this.Location;
	}

	public void setLocation(String Location) {
		this.Location = Location;
	}   
	public Date getStartDate() {
		return this.StartDate;
	}

	public void setStartDate(Date StartDate) {
		this.StartDate = StartDate;
	}   
	public Date getEndDate() {
		return this.EndDate;
	}

	public void setEndDate(Date EndDate) {
		this.EndDate = EndDate;
	}
	@Override
	public String toString() {
		return "Event [EventId=" + EventId + ", Name=" + Name + ", Picture=" + Picture + ", Theme=" + Theme
				+ ", Location=" + Location + ", StartDate=" + StartDate + ", EndDate=" + EndDate + "]";
	}
	public Event( String name, String picture, String theme, String location, Date startDate,
			Date endDate) {
		
		Name = name;
		Picture = picture;
		Theme = theme;
		Location = location;
		StartDate = startDate;
		EndDate = endDate;
	}
	public Event(int eventId, String name, String picture, String theme, String location) {
		super();
		EventId = eventId;
		Name = name;
		Picture = picture;
		Theme = theme;
		Location = location;
		
	}
   
}
