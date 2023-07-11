package models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Patient extends Person {
	private String gender;
	private LocalDate dateOfBirth;
	@OneToMany(mappedBy = "patient")
	private List<Treatment> treaments;

	public Patient() {
	}

	public Patient(String id, String name, String phone, String gender, LocalDate dateOfBirth) {
		super(id, name, phone);
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public List<Treatment> getTreaments() {
		return treaments;
	}

	public void setTreaments(List<Treatment> treaments) {
		this.treaments = treaments;
	}

	@Override
	public String toString() {
		return "Patient [id= " + super.getId()+", name= " + super.getName()+", phone= "+ super.getPhone()+", gender= " + gender + ", dateOfBirth= " + dateOfBirth + "]";
	}

}
