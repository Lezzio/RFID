package fr.pag.rfid.socket;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import fr.pag.rfid.RFID;
import fr.pag.rfid.cypher.Encrypter;
import fr.pag.rfid.utils.Debugger;
import fr.pag.rfid.utils.Protocol;

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

			// Authentication
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
			String encryptedCode = Encrypter.encrypt(Protocol.ACCESS_HEART);
			bufferedWriter.write(encryptedCode + "\n");
			bufferedWriter.flush();

			//Remote allow ?
			int access = inputStream.read();
			if (access == 1) {
				Debugger.log("Receiver found : " + socket.getInetAddress());
				SocketManager.addReceiver(socket);
			}

		} catch (IOException e) {
			e.printStackTrace(RFID.printWriter);
		}
	}

}
