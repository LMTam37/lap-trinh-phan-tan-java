package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import models.Treatment;

public class TreatmentDao {
	private EntityManager em;
	private EntityTransaction trans;

	public TreatmentDao(EntityManager em) {
		this.em = em;
		this.trans = em.getTransaction();
	}

	public void insert(Treatment treatment) {
		try {
			trans.begin();
			em.persist(treatment);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
		}
	}
}
