package sol01;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class StaffDao {
	private EntityManager em;
	private EntityTransaction trans;

	public StaffDao(EntityManager em) {
		this.em = em;
		this.trans = em.getTransaction();
	}

	public void insert(Staff staff) {
		try {
			trans.begin();
			em.persist(staff);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
		}
	}
	
	public double getProjectBudgetByEmp(int emp_id){
		TypedQuery<Object> q = em.createNamedQuery("Staff.getProjectBudgetByEmp", Object.class);
		q.setParameter(1, emp_id);
		return (Double) q.getSingleResult();
	}
}
