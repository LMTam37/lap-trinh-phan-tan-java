package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import entities.Clazz;
import entities.Teacher;

public class DbClient {
	public static void main(String[] args) throws Exception {
		while (true) {
		try (Socket socket = new Socket("127.0.0.1", 3390)) {
				Command cmd = null;
				printMenu(socket, cmd);
			}
		}
	}

	private static void printMenu(Socket socket, Command cmd) throws Exception {
		System.out.println("1. Lấy thông tin của một giáo viên");
		System.out.println("2. Lấy danh sách giảng dạy của một giáo viên");
		System.out.println("3. Lấy danh sách các lớp học trong một khoảng thời gian");
		System.out.println("4. Liệt kê các lớp vào một ngày trong tuần");
		System.out.println("5. Kết thúc");
		System.out.println("=====================================================");
		Scanner sc = new Scanner(System.in);
		int key = sc.nextInt();
		String id;
		sc.nextLine();
		switch (key) {
		case 1:
			System.out.println("Nhập vào id của giáo viên cần lấy thông tin: ");
			 id = sc.nextLine();
			cmd = new Command(key,new String[] {id +""}) ;
			getTeacherById(socket, cmd);
			break;
		case 2:
			System.out.println("Nhập vào id của giáo viên cần lấy thông tin: ");
			id = sc.nextLine();
			cmd = new Command(key,new String[] {id +""}) ;
			getTeacherClasses(socket, cmd);
			break;
		case 3:
			System.out.println("nhập ngày tháng theo định dạng yyyy-mm-dd");
			System.out.println("Nhập vào từ ngày: ");
			String fromDate = sc.nextLine();
			
			if(!validDate(fromDate)) {
				System.out.println("hãy nhập theo định dạng yyyy-mm-dd");
				break;
			}
			
			System.out.println("Đến ngày: ");
			String toDate = sc.nextLine();
			
			if(!validDate(toDate)) {
				System.out.println("hãy nhập theo định dạng yyyy-mm-dd");
				break;
			}
			cmd = new Command(key, new String[] {fromDate, toDate});
			getClassesBetweenDate(socket, cmd);
			break;
		case 4:
			System.out.println("Nhập vào ngày trong tuần cần tìm lớp");
			String dayOfWeek = sc.nextLine();
			cmd = new Command(key, new String[]{dayOfWeek}); 
			getClassesByDayOfWeek(socket, cmd);
			break;
		case 5:
			System.exit(1);
			break;

		default:
			break;
		}
	}

	private static void sendRequest(Socket socket, Command cmd) throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(cmd);
		oos.flush();
	}
	
	private static Object getResponse(Socket socket) throws Exception {
	    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());		
	    return ois.readObject();
	}
	
	private static void getTeacherById(Socket socket, Command cmd) throws Exception {
		sendRequest(socket, cmd);
		
	    Teacher t = (Teacher) getResponse(socket);
	    
	    System.out.println(t);
	}
	
	private static void getTeacherClasses(Socket socket, Command cmd) throws Exception{
		sendRequest(socket, cmd);
		
		List<Clazz> clazzes = (List<Clazz>) getResponse(socket);
		
		clazzes.forEach(clazz ->{
			System.out.println(clazz);
		});
	}
	
	private static void getClassesBetweenDate(Socket socket, Command cmd) throws Exception{
		sendRequest(socket, cmd);
		List<Clazz> clazzes = (List<Clazz>) getResponse(socket);
		clazzes.forEach(clazz ->{
			System.out.println(clazz);
		});
	}
	
	private static void getClassesByDayOfWeek(Socket socket, Command cmd) throws Exception{
		sendRequest(socket, cmd);
		List<Clazz> clazzes = (List<Clazz>) getResponse(socket);
		clazzes.forEach(clazz ->{
			System.out.println(clazz);
		});
	}
	
	private static boolean validDate(String date) {
		try {
			Date.valueOf(date);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
}
