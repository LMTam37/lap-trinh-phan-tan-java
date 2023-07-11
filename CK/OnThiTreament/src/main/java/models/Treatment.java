package models;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Treatment {
	@Id
	private LocalDate startDate;
	private LocalDate endDate;
	private String diagnosis;
	@Id
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Patient patient;
	@Id
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Department department;
	@Id
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Doctor doctor;

	public Treatment() {
	}

	public Treatment(LocalDate startDate, LocalDate endDate, String diagnosis, Patient patient, Department department,
			Doctor doctor) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.diagnosis = diagnosis;
		this.patient = patient;
		this.department = department;
		this.doctor = doctor;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	@Override
	public String toString() {
		return "Treatment [startDate=" + startDate + ", endDate=" + endDate + ", diagnosis=" + diagnosis + ", patient="
				+ patient + ", department=" + department + ", doctor=" + doctor + "]";
	}

	
}
