package event.guru.services;

import java.util.List;

import javax.ejb.Remote;

import event.guru.DTO.Ticket;

@Remote
public interface TicketWSremote {

	public List<Ticket> GetTickets();

	public void DeleteTicket(int id);

	public void PostTicket(Ticket p);

	public void PutTicket(int id, Ticket p);
	
	public String getStatTicket();
	
	
}
