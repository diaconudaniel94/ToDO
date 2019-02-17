package daniel.diaconu.ToDo;

import java.util.ArrayList;

import javax.validation.Valid;

public class ToDoView {

	@Valid
	private ArrayList<ToDoItem> todoList = new ArrayList<ToDoItem>();
	
	public void ToDoModel() {}
		
	public void ToDoModel(ArrayList<ToDoItem> todoList) {
		this.todoList = todoList;
	}

	public ArrayList<ToDoItem> getTodoList() {
		return todoList;
	}

	public void setTodoList(ArrayList<ToDoItem> todoList) {
		this.todoList = todoList;
	}
	
}
