package db;

import java.time.LocalDate;

import daos.DepartmentDao;
import daos.DoctorDao;
import daos.PatientDao;
import daos.TreatmentDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import models.Department;
import models.Doctor;
import models.Patient;
import models.Treatment;

public class App {
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ltpt");
		EntityManager em = factory.createEntityManager();
		DepartmentDao departmentDao = new DepartmentDao(em);
		DoctorDao doctorDao = new DoctorDao(em);
		PatientDao patientDao = new PatientDao(em);
		TreatmentDao treatmentDao = new TreatmentDao(em);
//		App.createDate(departmentDao, doctorDao, patientDao, treatmentDao);
//		if(patientDao.addPatient(new Patient("000-000-000", "Patient", "842", "Male", LocalDate.of(2003, 5, 5)))){
//			System.out.println("Thêm thành công");
//		}else {
//			System.out.println("Thêm thất bại");
//		}
//		doctorDao.getDoctorsByDepartment("Department01").forEach(System.out::println);
		System.out.println(doctorDao.getNoTreatmentsByDoctors(7,2023));
	}
	
	private static void createDate(DepartmentDao departmentDao, DoctorDao doctorDao, PatientDao patientDao, TreatmentDao treatmentDao) {
		Department department = new Department("D01", "Department01", "12A");
		Doctor doctor = new Doctor("Dr01", "Doctor01", "844", "Gynecologists");
		Patient patient = new Patient("P01", "Patient", "842", "Male", LocalDate.of(2003, 5, 5));
		Treatment treatment = new Treatment(LocalDate.of(2023, 7, 9), LocalDate.of(2023, 7, 10), "dont know", patient, department, doctor);
		departmentDao.insert(department);
		doctorDao.insert(doctor);
		patientDao.insert(patient);
		treatmentDao.insert(treatment);
		department = new Department("D01", "Department01", "12A");
		doctor = new Doctor("Dr02", "Doctor01", "844", "Gynecologists");
		patient = new Patient("P02", "Patient", "842", "Male", LocalDate.of(2003, 5, 5));
		treatment = new Treatment(LocalDate.of(2023, 7, 9), LocalDate.of(2023, 7, 10), "dont know", patient, department, doctor);
		departmentDao.insert(department);
		doctorDao.insert(doctor);
		patientDao.insert(patient);
		treatmentDao.insert(treatment);
		department = new Department("D01", "Department01", "12A");
		patient = new Patient("P03", "Patient", "842", "Male", LocalDate.of(2003, 5, 5));
		treatment = new Treatment(LocalDate.of(2023, 7, 9), LocalDate.of(2023, 7, 10), "dont know", patient, department, doctor);
		departmentDao.insert(department);
		doctorDao.insert(doctor);
		patientDao.insert(patient);
		treatmentDao.insert(treatment);
	}
}
