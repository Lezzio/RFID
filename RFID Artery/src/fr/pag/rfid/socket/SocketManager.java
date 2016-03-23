package fr.pag.rfid.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import com.pag.objects.Basket;

import fr.pag.rfid.utils.Debugger;

public class SocketManager {

	private static ArrayList<Socket> receivers = new ArrayList<Socket>();

	private static InetAddress localAddress;
	private static String prefix = "[Socket] ";

	static {
		try {
			setLocalAddress(InetAddress.getLocalHost());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public static void findReceivers(String address, int port, int timeout) {
		String subnet = address.substring(0, address.length() - address.split("\\.")[3].length());
		for (int i = 0; i < 256; i++) {
			String target = subnet + i;
			Socket socket = new Socket();
			try {
				socket.connect(new InetSocketAddress(target, port), timeout);
				Debugger.log(prefix + socket.getInetAddress() + " -> " + (socket.isConnected() ? "trying" : "false"));
				ProcessSocket processSocket = new ProcessSocket(socket);
				processSocket.start();
			} catch (IOException e) {
				continue;
			}
		}
	}

	public static ArrayList<Socket> getReceivers() {
		return receivers;
	}

	public static void addReceiver(Socket socket) {
		receivers.add(socket);
	}

	public static void removeReceiver(Socket socket) {
		receivers.remove(socket);
	}

	public static InetAddress getLocalAddress() {
		return localAddress;
	}

	public static void setLocalAddress(InetAddress localAddress) {
		SocketManager.localAddress = localAddress;
	}

	public static FutureTask<Basket> getBasket(final ArrayList<String> itemQueue) {

		FutureTask<Basket> futureTask = new FutureTask<>(new Callable<Basket>() {

			@Override
			public Basket call() throws Exception {
				for (Socket socket : receivers) {
					try {
						BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
						BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						// Serialize item list
						String builder = "";
						for(String str : itemQueue) {
							builder += str + "#";
						}
						// Send item list
						bufferedWriter.write(builder + "\n");
						bufferedWriter.flush();

						// Read incoming basket
						String serializedBasket = bufferedReader.readLine();
						Basket basket = Basket.fromString(serializedBasket);
						
						return basket;
					} catch (IOException e) {
						e.printStackTrace();
					}

				}

				return new Basket();
			}
		});
		futureTask.run();
		
		return futureTask;
	}

}