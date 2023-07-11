package db;

import java.sql.Date;
import java.util.List;

import dao.ClazzDao;
import dao.TeacherDao;
import entities.Clazz;
import entities.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AppDB {
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ltpt");
		EntityManager em = factory.createEntityManager();
		TeacherDao teacherDao = new TeacherDao(em);
//		ClazzDao classDao = new ClazzDao(em);
		createData(teacherDao);
	}

	private static void createData(TeacherDao teacherDao) {

		Teacher t1 = new Teacher(1, "Than Thi Det", "A", "0987654321", "1@gmail.com");
		Teacher t2 = new Teacher(2, "Nguyen Van Teo", "C", "0987654321", "1@gmail.com");
		Teacher t3 = new Teacher(3, "Tran Van Coi", "B", "0987654321", "1@gmail.com");
		t1.getClasses().add(new Clazz(101, "C01", 1, new Date(2023 - 1900, 0, 1), new Date(2023 - 1900, 4, 1), t1));
		t1.getClasses().add(new Clazz(102, "C02", 2, new Date(2023 - 1900, 1, 1), new Date(2023 - 1900, 5, 1), t1));
		t1.getClasses().add(new Clazz(103, "C03", 3, new Date(2023 - 1900, 2, 1), new Date(2023 - 1900, 6, 1), t1));

		t2.getClasses().add(new Clazz(104, "D01", 4, new Date(2023 - 1900, 3, 1), new Date(2023 - 1900, 7, 1), t1));
		t2.getClasses().add(new Clazz(105, "D02", 5, new Date(2023 - 1900, 4, 1), new Date(2023 - 1900, 8, 1), t1));
		t2.getClasses().add(new Clazz(106, "D03", 6, new Date(2023 - 1900, 5, 1), new Date(2023 - 1900, 9, 1), t1));

		t3.getClasses().add(new Clazz(107, "C04", 7, new Date(2023 - 1900, 6, 1), new Date(2023 - 1900, 10, 1), t1));
		t3.getClasses().add(new Clazz(108, "D04", 1, new Date(2023 - 1900, 7, 1), new Date(2023 - 1900, 11, 1), t1));
		t3.getClasses().add(new Clazz(109, "C05", 2, new Date(2023 - 1900, 8, 1), new Date(2023 - 1900, 12, 1), t1));

		teacherDao.insert(t1);
		teacherDao.insert(t2);
		teacherDao.insert(t3);
	}

	public static Teacher getTeacherById(TeacherDao teacherDao, long id) {
		return teacherDao.getTeacherById(id);
	}

	public static List<Clazz> getTeacherClasses(ClazzDao clazzDao, long id) {
		return clazzDao.getTeacherClasses(id);
	}

	public static List<Clazz> getClassesBetweenDate(ClazzDao clazzDao, Date fromDate, Date toDate) {
		return clazzDao.getClassesBetweenDate(fromDate, toDate);
	}
	
	public static List<Clazz> getClassesByDayOfWeek(ClazzDao clazzDao, int dayOfWeek) {
		return clazzDao.getClassesByDayOfWeek(dayOfWeek);
	}
}
