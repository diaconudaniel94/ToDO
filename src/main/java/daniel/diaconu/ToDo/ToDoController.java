package daniel.diaconu.ToDo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class ToDoController {

	@Autowired
    private ToDoRepository repository;
	
	private ToDoServ service;
	
	 @RequestMapping("/")
	 public String index(Model model) {
	        ArrayList<ToDoItem> todoList = (ArrayList<ToDoItem>) repository.findAll();
	        model.addAttribute("newitem", new ToDoItem());
	        model.addAttribute("items", new ToDoModel(todoList));
	        return "index.html";
	    }

	 	@RequestMapping(value = "/all", method = RequestMethod.POST)
	 	public Object all(Model model)
	 	{
	 		Object res = service.getAll();	 		
	 		return res;
	 	}
	 	
	 	@RequestMapping(value = "/add",method = RequestMethod.POST)
	 	public Object create(@RequestParam(name = "id") String id, @RequestParam String name,@RequestParam String description, @RequestParam String endDate, @RequestParam String startDate) throws ParseException {
	 		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	 		Date start = format.parse(startDate);
	 		Date end = format.parse(endDate);
	 		ToDoItem item = new ToDoItem(name,description,start,end);
	 		System.out.println();
	        repository.save(item);
	 		
	        ObjectMapper mapper = new ObjectMapper();
	        try {
				String jsonInString = mapper.writeValueAsString(item);
				return jsonInString;
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return false;
	 	}
	 	
	 	@RequestMapping(value = "/thisWeek", method = RequestMethod.POST)
	 	public Object perWeek(Model model) {
	 		Object json = service.getAll();
	 		return json;
	 	}
	    
	    
	
}
