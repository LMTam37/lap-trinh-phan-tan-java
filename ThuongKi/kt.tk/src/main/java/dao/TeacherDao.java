package dao;

import entities.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class TeacherDao {
	private EntityManager em;
	private EntityTransaction trans;

	public TeacherDao(EntityManager em) {
		this.em = em;
		this.trans = em.getTransaction();
	}

	public void insert(Teacher newTeacher) {
		try {
			trans.begin();
			em.persist(newTeacher);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			e.printStackTrace();
		}
	}

	public Teacher getTeacherById(long id) {
		TypedQuery<Teacher> q = em.createNamedQuery("Teacher.getById", Teacher.class);
		q.setParameter(1, id);
		return q.getSingleResult();
	}
}
