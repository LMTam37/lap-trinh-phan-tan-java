package sol01;

import java.sql.Date;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class App {
	public static void main(String[] args) {
		EntityManager em = Persistence.createEntityManagerFactory("ltpt").createEntityManager();
		ProjectDao projectDao = new ProjectDao(em);
		StaffDao staffDao = new StaffDao(em);
		generateData(projectDao, staffDao);
		projectDao.getProjectBySize(Size.large).forEach(System.out::println);
		projectDao.getProjectByYear(2023).forEach(System.out::println);
		System.out.println(staffDao.getProjectBudgetByEmp(101));
		
	}

	private static void generateData(ProjectDao projectDao, StaffDao staffDao) {
		Project project = new Project(1, "Du an 1", "", new Date(2023 - 1900, 5, 3), new Date(2023 - 1900, 11, 9),
				Size.medium, 1000);
		projectDao.insert(project);
		staffDao.insert(new Staff(101, "Nguyen Van A", new Date(2003 - 1900, 1, 1), "A@gmail.com", "02315659", project));
		staffDao.insert(new Staff(102, "Nguyen Van B", new Date(2001 - 1900, 5, 1), "B@gmail.com", "02315659", project));
		staffDao.insert(new Staff(103, "Nguyen Van C", new Date(2002 - 1900, 2, 1), "C@gmail.com", "02315659", project));

		project = new Project(2, "Du an 2", "", new Date(2021 - 1900, 5, 3), new Date(2023 - 1900, 11, 9), Size.large,
				100000);
		projectDao.insert(project);
		staffDao.insert(new Staff(104, "Nguyen Van D", new Date(1999 - 1900, 11, 11), "D@gmail.com", "02315659", project));
		staffDao.insert(new Staff(105, "Nguyen Van E", new Date(1996 - 1900, 5, 12), "E@gmail.com", "02315659", project));
		staffDao.insert(new Staff(106, "Nguyen Van F", new Date(1997 - 1900, 2, 17), "F@gmail.com", "02315659", project));

		project = new Project(3, "Du an 3", "", new Date(2017 - 1900, 5, 3), new Date(2023 - 1900, 11, 9),
				Size.veryLarge, 90000);
		projectDao.insert(project);
		staffDao.insert(new Staff(107, "Nguyen Van G", new Date(2004 - 1900, 6, 8), "G@gmail.com", "02315659", project));
		staffDao.insert(new Staff(108, "Nguyen Van H", new Date(2005 - 1900, 8, 2), "H@gmail.com", "02315659", project));
		staffDao.insert(new Staff(109, "Nguyen Van I", new Date(2006 - 1900, 2, 12), "I@gmail.com", "02315659", project));
	}

}
