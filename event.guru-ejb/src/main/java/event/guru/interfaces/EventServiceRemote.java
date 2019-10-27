package event.guru.interfaces;

import java.util.List;

import javax.ejb.Remote;

import event.guru.DTO.Event;


@Remote
public interface EventServiceRemote {

	List<Event> GetAll();
	public void Delete(Event EventId);
	public void Create(Event p);
	public void Update(Event p,int id);
	public Event findById(int id);
	
}
