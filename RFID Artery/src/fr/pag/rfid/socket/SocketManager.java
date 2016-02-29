package fr.pag.rfid.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketPermission;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import fr.pag.rfid.Debugger;
import fr.pag.rfid.Protocol;

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

}