package fr.pag.rfid.core.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import fr.pag.rfid.Protocol;
import fr.pag.rfid.core.cypher.Decrypter;

public class ProcessSocket extends Thread {

	private Socket socket;
	
	private InputStream inputStream;
	private OutputStream outputStream;

	public ProcessSocket(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void start() {
		super.start();
	}

	@Override
	public void run() {
		try {
			inputStream = socket.getInputStream();
			outputStream = socket.getOutputStream();
			
			//Unwanted connection ?
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			String recievedCode = Decrypter.decrypt(bufferedReader.readLine());
			
		 	if(recievedCode.equals(Protocol.ACCESS_HEART)) {
		 		//Allow connection
		 		outputStream.write(1);
		 		outputStream.flush();
		 		System.out.println("Issuer found : " + socket.getInetAddress());
		 		SocketManager.addIssuer(socket);
		 	}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
