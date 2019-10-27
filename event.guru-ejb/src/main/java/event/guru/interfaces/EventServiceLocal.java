package event.guru.interfaces;


import java.util.List;

import javax.ejb.Local;

import event.guru.DTO.Event;


@Local
public interface EventServiceLocal {
	
	
	List<Event> GetAll();
	public void Delete(int EventId);
	public void Create(Event p);
	public void Update(Event p,int id);
	public Event findById(int id);
	
	
}
