package fr.pag.rfid.core.socket;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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

			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Server hosting");
			alert.setContentText("Server Socket could not be established in this environment, please contact your administrator");
			alert.show();

			e.printStackTrace();
		}
	}
 
}
