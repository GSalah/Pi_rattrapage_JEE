package managedBeans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import event.guru.DTO.Ticket;
import event.guru.services.TicketWSremote;




@ManagedBean
public class TicketBean {

	private int id;
	private float price;
	private String type;
	private String ticketOnline;
	private String ticketStatus;
	private String dateLancement;
	private String stat;
	private List<Ticket> listTickets;
	
	@EJB
	private TicketWSremote ticketWS;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public TicketBean() {
	}
	
	public List<Ticket> getListTickets() {
		return ticketWS.GetTickets();
	}
	public void setListTickets(List<Ticket> listTickets) {
		this.listTickets = listTickets;
	}
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	public String getTicketOnline() {
		return ticketOnline;
	}
	public void setTicketOnline(String ticketOnline) {
		this.ticketOnline = ticketOnline;
	}
	public String getTicketStatus() {
		return ticketStatus;
	}
	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}
	public String getDateLancement() {
		return dateLancement;
	}
	public void setDateLancement(String dateLancement) {
		this.dateLancement = dateLancement;
	}
	public TicketWSremote getTicketWS() {
		return ticketWS;
	}
	public void setTicketWS(TicketWSremote ticketWS) {
		this.ticketWS = ticketWS;
	}
	
	public List<Ticket> getAllTickets() {

		return listTickets;
	}
	
	public String addTicket(){
		String navigaTo = null;
		//Date datedemande = new Date();
		//AspNetUser user = congeService.getUserById(connectedUser.getId());
		Ticket ticket = new Ticket();
		ticket.setPrixTicket(price);
		ticket.setTicketType(type);
		ticket.setDateLancementSales(dateLancement);
		ticket.setTicketonline(ticketOnline);
		ticket.setTicketStatus(ticketStatus);
		ticketWS.PostTicket(ticket);
		navigaTo = "/pages/ListTicket?faces-redirect=true";
		return navigaTo;
	}
	public void delete(int id){
		ticketWS.DeleteTicket(id);
	}

	public String getStat() {
		System.out.println(ticketWS.getStatTicket());
		return 	ticketWS.getStatTicket();
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	
}
