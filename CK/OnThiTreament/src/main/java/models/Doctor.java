package models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;

@Entity
@NamedQueries({
		@NamedQuery(name = "Doctor.getDoctorsByDepartment", query = "FROM Doctor WHERE id IN (SELECT tm.doctor.id FROM Treatment tm JOIN Department d ON tm.department.id = d.id WHERE d.name = ?1 GROUP BY tm.doctor.id)") })
public class Doctor extends Person {
	private String speciality;
	@OneToMany(mappedBy = "doctor")
	private List<Treatment> treaments;

	public Doctor() {
	}

	public Doctor(String id, String name, String phone) {
		super(id, name, phone);
	}

	public Doctor(String id, String name, String phone, String speciality) {
		super(id, name, phone);
		this.speciality = speciality;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public List<Treatment> getTreaments() {
		return treaments;
	}

	public void setTreaments(List<Treatment> treaments) {
		this.treaments = treaments;
	}

	@Override
	public String toString() {
		return "Doctor [id= " + super.getId() + ", name= " + super.getName() + ", phone= " + super.getPhone()
				+ ", speciality= " + speciality + "]";
	}

}
