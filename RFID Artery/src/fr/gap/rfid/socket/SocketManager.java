package fr.gap.rfid.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class SocketManager {
	
	public void initSocket(InetAddress adress, int port) {
		try {
			initSocket(new Socket(adress, port));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void initSocket(Socket socket) {
		
	}

}