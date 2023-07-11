package daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import models.Doctor;

public class DoctorDao {
	private EntityManager em;
	private EntityTransaction trans;

	public DoctorDao(EntityManager em) {
		this.em = em;
		this.trans = em.getTransaction();
	}
	public void insert(Doctor doctor) {
		try {
			trans.begin();
			em.persist(doctor);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
		}
	}
	
	public List<Doctor> getDoctorsByDepartment(String deptName){
		TypedQuery<Doctor> q = em.createNamedQuery("Doctor.getDoctorsByDepartment", Doctor.class);
		q.setParameter(1, deptName);
		return q.getResultList();
	}

	public Map<Doctor, Integer> getNoTreatmentsByDoctors(int month, int year){
		Map<Doctor, Integer> map = new HashMap<>();
		Query q = em.createQuery("select t.doctor.id, t.doctor.name, t.doctor.phone, t.doctor.speciality, count(*) from Treatment t where month(startDate) = ?1 and year(startDate) = ?2 group by t.doctor.id");
		q.setParameter(1, month);
		q.setParameter(2, year);
		//parse the query to Object array then split the Object array to create key: Doctor and value: number for Map
		List<Object[]> list = q.getResultList();
		for(Object[] result : list) {
			System.out.println(result[0] + ", " + result[1] + ", " + result[2] + ", " + result[3] + ", " + result[4]);
			map.put(new Doctor(result[0].toString(), result[1].toString(), result[2].toString(), result[3].toString()), Integer.parseInt(result[4].toString()));
		}
		return map;
	}
}
