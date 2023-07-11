package sol02;

import java.net.ServerSocket;
import java.net.Socket;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class Server {
	public static void main(String[] args) throws Exception{
		EntityManager em = Persistence.createEntityManagerFactory("ltpt").createEntityManager();
		try(ServerSocket server = new ServerSocket(1368)){
			while (true) {
				Socket socket = server.accept();
				new Thread(new DbProcess(socket, em)).start();
			}
		}
	}
}
