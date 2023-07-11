package server;

import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Date;
import java.util.List;

import client.Command;
import dao.ClazzDao;
import dao.TeacherDao;
import entities.Clazz;
import entities.Teacher;
import jakarta.persistence.EntityManager;

public class DbProcessThread implements Runnable {
	private Socket socket;
	private EntityManager em;
	private ClazzDao clazzDao;
	private TeacherDao teacherDao;

	public DbProcessThread(Socket socket, EntityManager em) {
		this.socket = socket;
		this.em = em;
		clazzDao = new ClazzDao(em);
		teacherDao = new TeacherDao(em);
	}

	@Override
	public void run() {
		try {
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			Command cmd = (Command) ois.readObject();

			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			int number = cmd.getNumbers();
			while (true) {
				if (number == 1) {
					int id = Integer.parseInt(cmd.getParams()[0]);
					Teacher teacher = getTeacherById(teacherDao, id);
					oos.writeObject(teacher);
					oos.flush();
				} else if (number == 2) {
					int id = Integer.parseInt(cmd.getParams()[0]);
					List<Clazz> classes = getTeacherClasses(clazzDao, id);
					oos.writeObject(classes);
					oos.flush();
				} else if (number == 3) {
					Date fromDate = Date.valueOf(cmd.getParams()[0]);
					Date toDate = Date.valueOf(cmd.getParams()[1]);
					List<Clazz> classes = getClassesBetweenDate(clazzDao, fromDate, toDate);
					oos.writeObject(classes);
					oos.flush();
				} else if (number == 4) {
					int dayOfWeek = Integer.parseInt(cmd.getParams()[0]);
					List<Clazz> classes = getClassesByDayOfWeek(clazzDao, dayOfWeek);
					oos.writeObject(classes);
					oos.flush();
				}
			}
		} catch (Exception e) {
		}
	}

	public Teacher getTeacherById(TeacherDao teacherDao, long id) {
		return teacherDao.getTeacherById(id);
	}

	public List<Clazz> getTeacherClasses(ClazzDao clazzDao, long id) {
		return clazzDao.getTeacherClasses(id);
	}

	public List<Clazz> getClassesBetweenDate(ClazzDao clazzDao, Date fromDate, Date toDate) {
		return clazzDao.getClassesBetweenDate(fromDate, toDate);
	}

	public List<Clazz> getClassesByDayOfWeek(ClazzDao clazzDao, int dayOfWeek) {
		return clazzDao.getClassesByDayOfWeek(dayOfWeek);
	}
}
