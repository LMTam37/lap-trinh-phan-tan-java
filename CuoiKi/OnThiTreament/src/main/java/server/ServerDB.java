package server;

import java.net.ServerSocket;
import java.net.Socket;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class ServerDB {
	public static void main(String[] args) throws Exception{
		EntityManager em = Persistence.createEntityManagerFactory("ltpt").createEntityManager();
		try(ServerSocket server = new ServerSocket(3390)){
			while(true) {
				Socket socker = server.accept();
				new Thread(new DbProcess(socker, em)).start();
			}
		}
	}
}
