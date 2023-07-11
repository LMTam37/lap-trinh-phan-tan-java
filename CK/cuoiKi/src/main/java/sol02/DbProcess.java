package sol02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import jakarta.persistence.EntityManager;
import sol01.ProjectDao;
import sol01.Size;
import sol01.StaffDao;

public class DbProcess implements Runnable {
	private Socket socket;
	private EntityManager em;

	public DbProcess(Socket socket, EntityManager em) {
		this.socket = socket;
		this.em = em;
	}

	@Override
	public void run() {
		PrintWriter writer = null;
		BufferedReader reader = null;
		try {
			writer = new PrintWriter(socket.getOutputStream(), true);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			ProjectDao projectDao = new ProjectDao(em);
			StaffDao staffDao = new StaffDao(em);
			while (true) {
				String sel = reader.readLine();
				if (sel.equals("list-projects")) {
					String inputSize = reader.readLine();
					Size size = null;
					if (inputSize.equals("small")) {
						size = size.small;
					} else if (inputSize.equals("medium")) {
						size = size.medium;
					} else if (inputSize.equals("large")) {
						size = size.large;
					} else if (inputSize.equals("very large")) {
						size = size.veryLarge;
					}
					writer.println(projectDao.getProjectBySize(size));
				} else if (sel.equals("staff-project-budget")) {
					int year = Integer.getInteger(reader.readLine());
					writer.println(staffDao.getProjectBudgetByEmp(year));
				}
			}
		} catch (Exception e) {
			//in case catch exception, this will return for error for client
			writer.println("error");
			e.printStackTrace();
		}
	}

}
