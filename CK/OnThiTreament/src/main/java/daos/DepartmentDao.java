package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import models.Department;

public class DepartmentDao {
	private EntityManager em;
	private EntityTransaction trans;

	public DepartmentDao(EntityManager em) {
		this.em = em;
		this.trans = em.getTransaction();
	}
	public void insert(Department department) {
		try {
			trans.begin();
			em.persist(department);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
		}
	}
	
}
