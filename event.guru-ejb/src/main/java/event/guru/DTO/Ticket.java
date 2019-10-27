package event.guru.DTO;

import java.io.Serializable;
import java.util.Date;

public class Ticket implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int idTicket;
	private float prixTicket;
	private String TicketStatus;
	private String TicketType;
	private String Ticketonline;
	private String DateLancementSales;
	
	public int getIdTicket() {
		return idTicket;
	}
	public void setIdTicket(int idTicket) {
		this.idTicket = idTicket;
	}
	public float getPrixTicket() {
		return prixTicket;
	}
	public void setPrixTicket(float prixTicket) {
		this.prixTicket = prixTicket;
	}
	public String getTicketStatus() {
		return TicketStatus;
	}
	public void setTicketStatus(String ticketStatus) {
		TicketStatus = ticketStatus;
	}
	
	public String getTicketType() {
		return TicketType;
	}
	public void setTicketType(String ticketType) {
		TicketType = ticketType;
	}
	public String getTicketonline() {
		return Ticketonline;
	}
	public void setTicketonline(String ticketonline) {
		Ticketonline = ticketonline;
	}
	public String getDateLancementSales() {
		return DateLancementSales;
	}
	public void setDateLancementSales(String dateLancementSales) {
		DateLancementSales = dateLancementSales;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Ticket() {
	}
	@Override
	public String toString() {
		return "Ticket [idTicket=" + idTicket + ", prixTicket=" + prixTicket + "]";
	}
	
	
}
