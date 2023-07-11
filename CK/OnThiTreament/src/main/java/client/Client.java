package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Date;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws Exception {
		try(Socket socket = new Socket("localhost", 3390)){
			while (true) {
				printMenu(socket);
			}
		}
	}
	
	public static void printMenu(Socket socket) throws Exception{
		System.out.println("=======================");
		System.out.println("1. Thêm bệnh nhân");
		System.out.println("2. Tìm kiếm danh sách bác sĩ từng diều trị ở một khoa");
		System.out.println("3. Thống kê số lượt điều trị bệnh");
		System.out.println("4. Thoát");
		System.out.println("=======================");
		System.out.println("chọn từ 1-4");
		int key = new Scanner(System.in).nextInt();
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
		writer.println(key);
		switch (key) {
		case 1:
			System.out.println("Hãy nhập vào id bệnh nhân");
			//id
			writer.println(new Scanner(System.in).nextLine());
			//ten
			System.out.println("Hãy nhập vào tên bệnh nhân");
			writer.println(new Scanner(System.in).nextLine());
			//sdt
			System.out.println("Hãy nhập vào số điện thoại");
			writer.println(new Scanner(System.in).nextLine());
			//gioitinh
			System.out.println("Hãy nhập vào giới tính bệnh nhân");
			writer.println(new Scanner(System.in).nextLine());
			//dob
			String dob = "";
			boolean flag;
			do {
				flag = true;
				System.out.println("Hãy nhập vào ngày sinh bệnh nhân theo mẫu yyyy-MM-dd"); 
				dob = new Scanner(System.in).nextLine();
				try {
					Date.valueOf(dob);
				} catch (Exception e) {
					flag = false;
				}
			} while (!flag);
			writer.println(dob);
			//get result
			System.out.println(reader.readLine());
			break;
		case 2:
			System.out.println(reader.readLine());
			writer.println(new Scanner(System.in).nextLine());
			System.out.println(reader.readLine());
			break;
		case 3:
			System.out.println(reader.readLine());
			writer.println(new Scanner(System.in).nextLine());
			System.out.println(reader.readLine());
			writer.println(new Scanner(System.in).nextLine());
			System.out.println(reader.readLine());
			break;
		case 4:
			System.exit(1);
			break;
		default:
			break;
		}
	}
}
