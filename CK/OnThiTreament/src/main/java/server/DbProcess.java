package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import daos.DoctorDao;
import daos.PatientDao;
import jakarta.persistence.EntityManager;
import models.Patient;

public class DbProcess implements Runnable {
	private Socket socket;
	private EntityManager em;

	public DbProcess(Socket socket, EntityManager em) {
		this.socket = socket;
		this.em = em;
	}

	@Override
	public void run() {
		PatientDao patientDao = new PatientDao(em);
		DoctorDao doctorDao = new DoctorDao(em);
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
			while (true) {
				String sel = reader.readLine();
				if (sel.equals("1")) {
					String id = reader.readLine();
					String name = reader.readLine();
					String phone = reader.readLine();
					String gender = reader.readLine();
					// reader get dob client had sended, then parse to Date, and split
					// year,month,date for contribute to localdate
					Date date = Date.valueOf(reader.readLine());
					LocalDate dob = LocalDate.of(date.getYear(), date.getMonth(), date.getDate());
					if (patientDao.addPatient(new Patient(id, name, phone, gender, dob))) {
						writer.println("Thêm thành công");
					} else {
						writer.println("Thêm thất bại");
					}
				} else if (sel.equals("2")) {
					writer.println("Nhập vào tên khoa");
					String departmentName = reader.readLine();
					writer.println(doctorDao.getDoctorsByDepartment(departmentName));
				} else if (sel.endsWith("3")) {
					writer.println("Nhập vào tháng");
					int month = Integer.parseInt(reader.readLine());
					writer.println("Nhập vào năm");
					int year = Integer.parseInt(reader.readLine());
					writer.println(doctorDao.getNoTreatmentsByDoctors(month, year));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
