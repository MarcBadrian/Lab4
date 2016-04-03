import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONObject;

@XmlRootElement(name = "TodoList")
public class TodoList {

	@XmlElementWrapper(name="Todo")
	private List<Todo> messages;
	
	public List<Todo> getMessages() {
		return messages;
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
