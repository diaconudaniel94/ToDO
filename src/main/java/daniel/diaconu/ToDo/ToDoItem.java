package daniel.diaconu.ToDo;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "todo_item")
public class ToDoItem {
	
	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "start_date")
	private Date startDate;
	
	@Column(name = "end_date")
	private Date endDate;
	
	@Column(name = "done")
	private boolean done;
	
	public ToDoItem() {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public ToDoItem(String name,String description,Date startdate,Date enddate) {
        this.name = name;
        this.description = description;
        this.startDate = startdate;
        this.endDate = enddate;
        this.done = false;
    }
	
	 @Override
	    public String toString() {
	        return String.format(
	                "TodoItem[id=%d, name='%s', description='%s', startdate='%s', enddate='%s', finished='%b']",
	                id, name, description, startDate, endDate, done);
	    }
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDone() {
		return done;
	}
	public void setFinished(boolean finished) {
		this.done = finished;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public void setDone(boolean done) {
        this.done = done;
        return;
    }
	
	
}
