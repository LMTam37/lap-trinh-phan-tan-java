package sol01;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class ProjectDao {
	private EntityManager em;
	private EntityTransaction trans;

	public ProjectDao(EntityManager em) {
		this.em = em;
		this.trans = em.getTransaction();
	}

	public void insert(Project project) {
		try {
			trans.begin();
			em.persist(project);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
		}
	}
	
	public List<Project> getProjectBySize(Size size){
		TypedQuery<Project> q = em.createNamedQuery("Project.getProjectBySize", Project.class);
		q.setParameter(1, size);
		return q.getResultList();
	}
	
	public List<Project> getProjectByYear(int year){
		TypedQuery<Project> q = em.createNamedQuery("Project.getProjectByYear", Project.class);
		q.setParameter("date", year);
		return q.getResultList();
	}
}
