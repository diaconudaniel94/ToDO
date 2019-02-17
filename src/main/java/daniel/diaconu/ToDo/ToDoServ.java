package daniel.diaconu.ToDo;

import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ToDoServ implements ToDoServInterface{
	
	@SuppressWarnings("rawtypes")
	public static Object toJSON(Object object) throws JSONException {
        
        JSONArray json = new JSONArray();
        for (Object value : ((Iterable)object)) {
            json.put(toJSON(value));
        }
        return json;
        
    }
	
	@Autowired
	private ToDoRepository repository;

	@Override
	public Object getAll() {
		Iterable<ToDoItem> todoItems = repository.findAll();
		
		try {
			Object json = toJSON(todoItems);
			return json;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return todoItems;
		
	}

	@Override
	public void delete(Long id) {
		repository.findById(id);
		repository.deleteById(id);
	}

	@Override
	public Object create(ToDoItem todoItem) {
		Date now = Calendar.getInstance().getTime();
		todoItem.setStartDate(now);
		ToDoItem createdItem = repository.save(todoItem);
		try {
			Object json = toJSON(createdItem);
			return json;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return createdItem;
	}

	@Override
	public Object update(Long id, ToDoItem todoItem) {
		ToDoItem updatedTodoItem = repository.save(todoItem);
		try {
			Object json = toJSON(updatedTodoItem);
			return json;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updatedTodoItem;
	}

	@Override
	public Object getItemById(Long id) {
		repository.findById(id);
		Object one = repository.getOne(id);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
            String json = mapper.writeValueAsString(one);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
		return one;
	}
}

