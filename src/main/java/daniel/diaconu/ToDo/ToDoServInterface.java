package daniel.diaconu.ToDo;

public interface ToDoServInterface {
	Object getAll();
	void delete(Long id);
	Object create(ToDoItem todoItem);
	Object update(Long id, ToDoItem todoItem);
	Object getItemById(Long id);
}
