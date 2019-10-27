package event.guru.services;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import event.guru.DTO.Ticket;



@Stateless
@LocalBean
public class TicketWS implements TicketWSremote {

	@Override
	public List<Ticket> GetTickets() {
		List<Ticket> lasp = new ArrayList<Ticket>();
		Client client = ClientBuilder.newClient();

		WebTarget web = client.target("http://localhost:4640/api/Ticket");

		Response response = web.request().get();

		String result = response.readEntity(String.class);

		// System.out.println(result);
		JsonReader jsonReader = Json.createReader(new StringReader(result));
		JsonArray object = jsonReader.readArray();

		for (int i = 0; i < object.size(); i++) {

			Ticket m = new Ticket();
			m.setIdTicket(object.getJsonObject(i).getInt("idTicket"));
			m.setPrixTicket(object.getJsonObject(i).getInt("prixTicket"));
			m.setDateLancementSales(object.getJsonObject(i).get("DateLancementSales").toString());
			m.setTicketonline(object.getJsonObject(i).get("Ticketonline").toString());
			m.setTicketType(object.getJsonObject(i).get("TicketType").toString());
			m.setTicketStatus(object.getJsonObject(i).get("TicketStatus").toString());
			// m.setProjectName(object.getJsonObject(i).get("projectName").toString());

			// m.setSen(obj.get("Seniority").toString());

			lasp.add(m);
		}

		return lasp;
	}

	@Override
	public void DeleteTicket(int id) {
		// TODO Auto-generated method stub
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:4640/api/Ticketvip?id=" + id);
		WebTarget hello = target.path("");
		Response response = hello.request(MediaType.APPLICATION_JSON_TYPE, MediaType.TEXT_PLAIN_TYPE).delete();

		System.out.println("LOG DELETED" + response.getStatus());
		String result = response.readEntity(String.class);
		System.out.println("XXXXXXXXXXX:" + result);

		response.close();
	}

	public static final String baseUri = "http://localhost:4640/api/Rec";

	private Client client = null;
	private WebTarget target = null;

	public void Test() {
		client = ClientBuilder.newClient();
		target = client.target(baseUri);
	}

	public void reloadUri() {
		target = null;
		target = client.target(baseUri);
	}

	@Override
	public void PostTicket(Ticket p) {

		reloadUri();
		// String input = "{\"site\"😕"www.9threes.com\",\"message\"😕"is new
		// domain\"}";
		Ticket pack = new Ticket();
		//pack.setIdTicket(p.getIdTicket());
		pack.setPrixTicket(p.getPrixTicket());
		pack.setTicketType(p.getTicketType());
		pack.setDateLancementSales(p.getDateLancementSales());
		pack.setTicketonline(p.getTicketonline());
		pack.setTicketStatus(p.getTicketStatus());

		target = target.path("/Tick");
		// POST Request from Jersey Client
		Response response = target.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(pack, MediaType.APPLICATION_JSON), Response.class);
		System.out.println(response);
		if (response.getStatus() == 200) {
			System.out.println("😕post success");

		} else
			System.out.println("😕fatal error");
	}


	@Override
	public void PutTicket(int id, Ticket p) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getStatTicket() {
		// TODO Auto-generated method stub
		Client client = ClientBuilder.newClient();

		WebTarget web = client.target("http://localhost:4640/api/statTicket");

		Response response = web.request().get();

		String result = response.readEntity(String.class);
		System.out.println(result);
		return result;
	}

}
