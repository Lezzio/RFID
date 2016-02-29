package fr.pag.rfid.core.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import fr.pag.rfid.Protocol;

public class Server {

	private ServerSocket serverSocket;

	public void start() throws IOException {
		
			serverSocket = new ServerSocket(Protocol.PORT_HEART);
			System.out.println("Waiting for incoming connection...");

			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						while (true) {
							Socket socket = serverSocket.accept();
							ProcessSocket processSocket = new ProcessSocket(socket);
							processSocket.start();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			thread.start();
	}

}
