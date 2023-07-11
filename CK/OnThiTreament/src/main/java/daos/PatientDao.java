package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import models.Patient;

public class PatientDao {
	private EntityManager em;
	private EntityTransaction trans;

	public PatientDao(EntityManager em) {
		this.em = em;
		this.trans = em.getTransaction();
	}
	public void insert(Patient patient) {
		try {
			trans.begin();
			em.persist(patient);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
		}
	}
	
	public boolean addPatient(Patient patient) {
		if(!patient.getId().matches("[0-9]{3}-[0-9]{3}-[0-9]{3}")) {
			return false;
		}
		
		try {
			trans.begin();
			em.persist(patient);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			return false;
		}
		return true;
	}
}
