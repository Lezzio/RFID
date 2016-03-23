package fr.pag.rfid.core.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.pag.objects.Basket;

import fr.pag.rfid.core.cypher.Decrypter;
import fr.pag.rfid.core.handler.BasketManager;
import fr.pag.rfid.utils.Protocol;

public class ProcessSocket extends Thread {

	private Socket socket;
	
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
			final OutputStream outStream = socket.getOutputStream();
			final InputStream inStream = socket.getInputStream();

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inStream));
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outStream));
			
			//Unwanted connection ?
			String recievedCode = Decrypter.decrypt(bufferedReader.readLine());
			
		 	if(recievedCode != null && recievedCode.equals(Protocol.ACCESS_HEART)) {
		 		//Allow connection
		 		outStream.write(1);
		 		outStream.flush();
		 		System.out.println("Issuer found : " + socket.getInetAddress());
		 		SocketManager.addIssuer(socket);
		 		
		 		//Wait for request
		 		while(true) {
		 			String str = bufferedReader.readLine();
		 			if(str != null) {
		 				String[] codes = str.split("#");
		 				Basket basket = BasketManager.getBasket(codes);
		 				String serializedBasked = basket.toString();
		 				bufferedWriter.write(serializedBasked + "\n");
		 				bufferedWriter.flush();
		 				System.out.println("Basket request treated");
		 			}
		 		}
		 	} else {
		 		socket.close();
		 	}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
