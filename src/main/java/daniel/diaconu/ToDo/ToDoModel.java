package daniel.diaconu.ToDo;

import java.util.ArrayList;

import javax.validation.Valid;

public class ToDoModel {
	@Valid
	private ArrayList<ToDoItem> todoList = new ArrayList<ToDoItem>();
	
	public ToDoModel() {}
	
	public ToDoModel(ArrayList<ToDoItem> todoList) {
		this.todoList = todoList;
	}

	public ArrayList<ToDoItem> getTodoList() {
		return todoList;
	}

	public void setTodoList(ArrayList<ToDoItem> todoList) {
		this.todoList = todoList;
	}

}
