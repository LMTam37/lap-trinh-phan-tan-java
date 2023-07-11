package entities;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

@Entity
@NamedQueries({ @NamedQuery(name = "Clazz.getTeacherClasses", query = "from Clazz c where c.teacher.teacherId = ?1"),
		@NamedQuery(name = "Clazz.getClassesBetweenDate", query = "from Clazz c where timeStart between ?1 and ?2"),
		@NamedQuery(name = "Clazz.getClassesByDayOfWeek", query = "from Clazz c where dayOfWeek = ?1") })
public class Clazz implements Serializable {
	@Id
	private long classID;
	private String courseCode;
	private int dayOfWeek;
	private Date timeStart;
	private Date timeEnd;
	@ManyToOne
	private Teacher teacher;

	public Clazz() {
	}

	public Clazz(long classID, String courseCode, int dayOfWeek, Date timeStart, Date timeEnd, Teacher teacher) {
		this.classID = classID;
		this.courseCode = courseCode;
		this.dayOfWeek = dayOfWeek;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		this.teacher = teacher;
	}

	public long getClassID() {
		return classID;
	}

	public void setClassID(long classID) {
		this.classID = classID;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public int getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public Date getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(Date timeStart) {
		this.timeStart = timeStart;
	}

	public Date getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@Override
	public String toString() {
		return "Clazz [classID=" + classID + ", courseCode=" + courseCode + ", dayOfWeek=" + dayOfWeek + ", timeStart="
				+ timeStart + ", timeEnd=" + timeEnd + "]";
	}

}
