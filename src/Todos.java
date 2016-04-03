/*
 * COMP 6302 - Web Services / Internet
 * Lab 4: RESTful ToDo 
 * Marc Badrian - Due 4/2/16
 * 
 */


import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.sql.Timestamp;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;  


@Path("/")
public class Todos {
    Scanner s = new Scanner(System.in);
    ObjectMapper mapper = new ObjectMapper();

    
    private static final ConcurrentHashMap<Integer, String[]> map = new ConcurrentHashMap<Integer, String[]>();
    
	public Todos(){}
	
	TodoList dao = new TodoList();
	
	@POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	//c@Path("/create")
    public String createTodo(Todo todo) {
        //return dao.create(todo);
		return "todo";
    }
	
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	//@Path("/create")
	public Response newTodo(Todo todo) throws JsonParseException, JsonMappingException, IOException{
		//Todo response = mapper.readValue(todo, Todo.class);
		String json = "{'Error':'Error'}";
		try {
			json = new ObjectMapper().writeValueAsString(todo);
		} catch (Exception e) {
			return Response.status(500).build();
		}
		return Response.ok(json,"application/json").build();
	}
		
	private Todo createTodo(int id, String todo_message){
		Todo message = new Todo();
		message.setId(id);
		message.setToDoMessage(todo_message);
		message.setTimestamp();
		return message;
	}

	
	private JAXBElement<Todo> toXml(Todo message){
		return new JAXBElement<Todo>(new QName("Todo"), Todo.class, message);
	}
	
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void findAll() {
		System.out.println("findAll");
		JSONObject json = new JSONObject();  
		};

	@GET @Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Todo findById(@PathParam("id") String id) {
		System.out.println("findById " + id);
		return dao.findById(Integer.parseInt(id));
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Todo create(Todo Todo) {
		return dao.create(Todo);
	}

	@PUT @Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Todo update(Todo Todo) {
		System.out.println("Updating Todo: " + Todo.getId());
		dao.update(Todo);
		return Todo;
	}
	
	@DELETE @Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void remove(@PathParam("id") int id) {
		dao.delete(id);
	}
	
}	

