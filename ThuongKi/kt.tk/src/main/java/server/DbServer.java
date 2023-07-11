package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DbServer {
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ltpt");
		EntityManager em = factory.createEntityManager();
		try (ServerSocket server = new ServerSocket(3390)) {
			while (true) {
				Socket socket = server.accept();
				new Thread(new DbProcessThread(socket, em)).start();;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
