package sol01;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;

@Entity
@NamedQueries({
	@NamedQuery(name = "Project.getProjectBySize", query = "from Project where size = ?1"),
	@NamedQuery(name = "Project.getProjectByYear", query = "from Project where year(start_date) = :date")	
})
public class Project {
	@Id
	private long code;
	@Column(columnDefinition = "varchar(100)", nullable = false)
	private String name;
	@Column(columnDefinition = "varchar(255)", nullable = false)
	private String description;
	@Column(nullable = false)
	private Date start_date;
	@Column(nullable = false)
	private Date end_date;
	@Column(nullable = false)
	private Size size;
	@Column(nullable = false)
	private double budget;
	@OneToMany(mappedBy = "project")
	private List<Staff> staffs;

	public Project() {
	}

	public Project(long code, String name, String description, Date start_date, Date end_date, Size size,
			double budget) {
		this.code = code;
		this.name = name;
		this.description = description;
		this.start_date = start_date;
		this.end_date = end_date;
		this.size = size;
		this.budget = budget;
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	@Override
	public String toString() {
		return "Project [code=" + code + ", name=" + name + ", description=" + description + ", start_date="
				+ start_date + ", end_date=" + end_date + ", size=" + size + ", budget=" + budget + "]";
	}

}
