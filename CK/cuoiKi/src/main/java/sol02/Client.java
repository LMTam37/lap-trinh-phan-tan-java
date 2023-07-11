package sol02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws Exception {
		try (Socket socket = new Socket("localhost", 1368)) {
			while (true) {
				printMenu(socket);
			}
		}
	}

	private static void printMenu(Socket socket) throws IOException {
		PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		System.out.println("=======================");
		System.out.println("Nhập vào yêu câu");
		System.out.println("list-projects");
		System.out.println("staff-project-budget");
		System.out.println("exit");
		System.out.println("=======================");
		String key = new Scanner(System.in).nextLine();
		writer.println(key);
		switch (key) {
		case "list-projects":
			String size = "";
			do {
				System.out.println("nhập vào độ lớn của dự án");
				size = new Scanner(System.in).nextLine();
			} while (!validSize(size));
			writer.println(size);
			System.out.println( reader.readLine());
			break;

		case "staff-project-budget":
			System.out.println("Nhập vào mã nhân viên");
			writer.println(new Scanner(System.in).nextLine());
			System.out.println(reader.readLine());
			break;

		case "exit":
			System.exit(1);
			break;

		default:
			break;
		}
	}

	private static boolean validSize(String size) {
		if (size.equals("small")) {
			return true;
		} else if (size.endsWith("medium")) {
			return true;
		} else if (size.endsWith("large")) {
			return true;
		} else if (size.endsWith("very large")) {
			return true;
		}
		return false;
	}
}
