import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/Todo")
public class TodoRS extends Application{

	@Override
	public Set<Class<?>> getClasses(){
		Set<Class<?>> set = new HashSet<Class<?>>();
		set.add(Todos.class);
		return set;
	}
}