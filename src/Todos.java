import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/")
public class Todos {
	
	public Todos(){}
	
	@GET
	@Produces({ MediaType.APPLICATION_XML })
	@Path("/xml/{id}")
	public Response getMessageXML(@PathParam("id") String id) {
		int int_id = Integer.parseInt(id);
		List<Todo> list = TodoList.getMessages();
		Todo temp_todo = new Todo();
		temp_todo.setId(int_id);
		int index = list.indexOf(temp_todo);
		Todo todo;
		if(index >= 0) {
			todo = list.get(index);
		}else{
			todo = new Todo();
			todo.setToDoMessage("Error!");
		}
		String json = "";	
		System.out.println("Todo message:" + todo.getMessage());
		try {
			json = new ObjectMapper().writeValueAsString(todo);
		} catch (Exception e) {
			return Response.status(500).build();
		}
		return Response.ok(json,"application/xml").build();
	}
	
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/json/{id}")
	public Response getMessageJSON(@PathParam("id") String id) {
		int int_id = Integer.parseInt(id);
		List<Todo> list = TodoList.getMessages();
		Todo temp_todo = new Todo();
		temp_todo.setId(int_id);
		int index = list.indexOf(temp_todo);
		Todo todo;
		if(index >= 0) {
			todo = list.get(index);
		}else{
			todo = new Todo();
			todo.setToDoMessage("Error!");
		}
		String json = "";	
		System.out.println("Todo message:" + todo.getMessage());
		try {
			json = new ObjectMapper().writeValueAsString(todo);
		} catch (Exception e) {
			return Response.status(500).build();
		}
		return Response.ok(json,"application/json").build();
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/create")
	public Response createMessage(@FormParam("id") String id, @FormParam("message") String p_message){
		List<Todo> list= TodoList.getMessages();
		int int_id = Integer.parseInt(id);
		System.out.println("New Todo -- Id: " + id);
		System.out.println("Todo Message: " + p_message);
		Todo temp_todo = new Todo();
		temp_todo.setToDoMessage(p_message);
		temp_todo.setId(int_id);
		ServerResponse s = new ServerResponse();
		try{
			list.add(temp_todo);
			TodoList.setMessages(list);
			s.code = "Success!";
			s.message = "Todo created!";
		}
		catch(Exception e){
			s.code = "Error!";
			s.message = "Issue creating Todo!";
		}
		String json = "";	
		try {
			json = new ObjectMapper().writeValueAsString(s);
		} catch (Exception e) {
			return Response.status(500).build();
		}
		return Response.ok(json,"application/json").build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/all/json")
	public Response getAllMessagesJSON() {
		List<Todo> list= TodoList.getMessages();
		list.forEach(todo->System.out.println("ID: " + todo.getId() + " Todo Message: " + todo.getMessage()));
		String json = "";	
		try {
			json = new ObjectMapper().writeValueAsString(list);
		} catch (Exception e) {
			return Response.status(500).build();
		}
		return Response.ok(json,"application/json").build();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_XML })
	@Path("/all/xml")
	public Response getAllMessagesXML() {
		List<Todo> list= TodoList.getMessages();
		list.forEach(todo->System.out.println("ID: " + todo.getId() + " Todo Message: " + todo.getMessage()));
		String xml = "";	
		try {
			xml = new ObjectMapper().writeValueAsString(list);
		} catch (Exception e) {
			return Response.status(500).build();
		}
		return Response.ok(xml,"application/xml").build();
	}

	@DELETE
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/delete/{id}")
	public Response deleteId(@PathParam("id") String id) {
		List<Todo> list= TodoList.getMessages();
		int int_id = Integer.parseInt(id);
		Todo temp_todo = new Todo();
		temp_todo.setId(int_id);
		int index = list.indexOf(temp_todo);
		ServerResponse s = new ServerResponse();
		if(index >= 0) {
			list.remove(index);
			s.code = "Success!";
			s.message = "Todo Message deleted!";
		}
		else{
			s.code = "Error!";
			s.message = "Issue deleting Todo Message!";
		}
		String json = "";	
		try {
			json = new ObjectMapper().writeValueAsString(s);
		} catch (Exception e) {
			return Response.status(500).build();
		}
		return Response.ok(json,"application/json").build();
	}

}
