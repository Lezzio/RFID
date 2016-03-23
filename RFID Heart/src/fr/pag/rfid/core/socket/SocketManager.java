package fr.pag.rfid.core.socket;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class SocketManager {
	
	private static ArrayList<Socket> issuers = new ArrayList<Socket>();
	private static Server server;

	public static void addIssuer(Socket socket) {
		issuers.add(socket);
	}
	public static void removeIssuer(Socket socket) {
		issuers.remove(socket);
	}
	
	public static void initServer() {
		server = new Server();
		try {
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 
}
