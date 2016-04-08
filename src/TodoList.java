import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONObject;

@XmlRootElement(name = "todoList")
public class TodoList {

	@XmlElementWrapper(name="todo")
	private static List<Todo> messages = new ArrayList<Todo>();
	
	public static List<Todo> getMessages() {
		return messages;
	}
	
	public static void setMessages(List<Todo> p_messages) {
		messages = p_messages;
	}
	
    public List<Todo> findAll() {
        List<Todo> list = new ArrayList<Todo>();
        return list;
    }
    
    public Todo findById(int id) {
        Todo Todo = null;
        return Todo;
    }   
    
    public Todo create(Todo Todo) {
        Todo.getId();
        Todo.getMessage();
        Todo.getTimestamp();
        //Todo.setId(id);
        return Todo;
    }

    public boolean delete(int id) {
    	return false;
    }

	public void update(Todo todo) {
		// TODO Auto-generated method stub
		
	}
	
}
