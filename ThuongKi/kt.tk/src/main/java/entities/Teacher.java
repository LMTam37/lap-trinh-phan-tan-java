package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;

@Entity
@NamedQueries({ @NamedQuery(name = "Teacher.getById", query = "from Teacher t where t.teacherId = ?1") })
public class Teacher implements Serializable {
	@Id
	private long teacherId;
	private String name;
	private String office;
	private String phone;
	private String email;
	@OneToMany(mappedBy = "teacher", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Clazz> classes;

	public Teacher() {
	}

	public Teacher(long teacherId, String name, String office, String phone, String email) {
		this.teacherId = teacherId;
		this.name = name;
		this.office = office;
		this.phone = phone;
		this.email = email;
		this.classes = new ArrayList<Clazz>();
	}

	public long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(long teacherId) {
		this.teacherId = teacherId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Clazz> getClasses() {
		return classes;
	}

	public void setClasses(List<Clazz> classes) {
		this.classes = classes;
	}

	@Override
	public String toString() {
		return "Teacher [teacherId=" + teacherId + ", name=" + name + ", office=" + office + ", phone=" + phone
				+ ", email=" + email + ", classes=" + classes + "]";
	}

}
