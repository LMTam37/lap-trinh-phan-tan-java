package sol01;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

@Entity
@NamedQueries({
		@NamedQuery(name = "Staff.getProjectBudgetByEmp", query = "select sum(p.budget) from Staff s join Project p on s.project.code = p.code where emp_id = ?1 group by emp_id") })
public class Staff {
	@Id
	private long emp_id;
	@Column(nullable = false)
	private String fullname;
	@Column(nullable = false)
	private Date dob;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String cellphone;
	@ManyToOne
	private Project project;

	public Staff() {
	}

	public Staff(long emp_id, String fullname, Date dob, String email, String cellphone, Project project) {
		this.emp_id = emp_id;
		this.fullname = fullname;
		this.dob = dob;
		this.email = email;
		this.cellphone = cellphone;
		this.project = project;
	}

	public long getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(long emp_id) {
		this.emp_id = emp_id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	@Override
	public String toString() {
		return "Staff [emp_id=" + emp_id + ", fullname=" + fullname + ", dob=" + dob + ", email=" + email
				+ ", cellphone=" + cellphone + "]";
	}

}
