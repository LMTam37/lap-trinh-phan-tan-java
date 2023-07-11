package dao;

import java.sql.Date;
import java.util.List;

import entities.Clazz;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class ClazzDao {
	private EntityManager em;
	private EntityTransaction trans;
	public ClazzDao(EntityManager em) {
		this.em = em;
		trans = em.getTransaction();
	}
	
	public void insert(Clazz newClass) {
		try {
			trans.begin();
			em.persist(newClass);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			e.printStackTrace();
		}
	}
	
	public List<Clazz> getTeacherClasses(long id) {
		TypedQuery<Clazz> q = em.createNamedQuery("Clazz.getTeacherClasses", Clazz.class);
		q.setParameter(1, id);
		return q.getResultList();
	}

	public List<Clazz> getClassesBetweenDate(Date fromDate, Date toDate) {
		TypedQuery<Clazz> q = em.createNamedQuery("Clazz.getClassesBetweenDate", Clazz.class);
		q.setParameter(1, fromDate);
		q.setParameter(2, toDate);
		return q.getResultList();
	}
	
	public List<Clazz> getClassesByDayOfWeek(int dayOfWeek) {
		TypedQuery<Clazz> q = em.createNamedQuery("Clazz.getClassesByDayOfWeek", Clazz.class);
		q.setParameter(1, dayOfWeek);
		return q.getResultList();
	}
}
