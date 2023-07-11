package sol01;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class Order {
	private long id;
	private String employeeId;
	private List<OrderDetails> details;
	private Date cretaeDate;

	public Order() {
	}

	public Order(long id, String employeeId, List<OrderDetails> details, Date cretaeDate) {
		this.id = id;
		this.employeeId = employeeId;
		this.details = details;
		this.cretaeDate = cretaeDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public List<OrderDetails> getDetails() {
		return details;
	}

	public void setDetails(List<OrderDetails> details) {
		this.details = details;
	}

	public Date getCretaeDate() {
		return cretaeDate;
	}

	public void setCretaeDate(Date cretaeDate) {
		this.cretaeDate = cretaeDate;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", employeeId=" + employeeId + ", details=" + details + ", cretaeDate=" + cretaeDate
				+ "]";
	}

}
