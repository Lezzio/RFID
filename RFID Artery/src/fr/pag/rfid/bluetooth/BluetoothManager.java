package fr.pag.rfid.bluetooth;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.microedition.io.StreamConnection;

import fr.pag.rfid.Debugger;
import fr.pag.rfid.RFID;
import fr.pag.rfid.async.ThreadPool;

public class BluetoothManager {

	private static BluetoothServer server;
	private static boolean state = false;

	private static StreamConnection customer;
	private static OutputStream outCustomer;

	public static void initServer() throws IOException, BluetoothStateException {

		// Setup local device
		System.setProperty("bluecove.stack", "winsock");
		LocalDevice localDevice = LocalDevice.getLocalDevice();

		// Launch server asynchronously
		ThreadPool.executeTask(new Runnable() {

			@Override
			public void run() {
				try {
					server = new BluetoothServer(localDevice, DiscoveryAgent.GIAC);
					server.start(); // Blocking
				} catch (IOException e) {
					e.printStackTrace(RFID.printWriter);
					state = false;
				}
			}
		});

		// All done ?
		state = true;

	}

	public static synchronized void setCustomer(StreamConnection connection) {
		try {
		customer = connection;
		outCustomer = connection.openOutputStream();
		} catch(IOException e) {
			e.printStackTrace(RFID.printWriter);
		}
	}

	public static StreamConnection getCustomer() {
		return customer;
	}

	public static synchronized boolean writeToCustomer(String jsonMsg) {
		
	     PrintStream printStream = new PrintStream(outCustomer);
		 printStream.println(jsonMsg);
		 printStream.flush();
		 
		return true;
	}
	
	public static synchronized boolean writeToCustomer(Object object) {
		try {
			Debugger.log("Customer: " + customer);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(outCustomer);
			objectOutputStream.writeObject(object);
			objectOutputStream.flush();
		} catch(IOException e) {
			e.printStackTrace(RFID.printWriter);
			return false;
		}
		return true;
	}

	public static void closeCustomer() {
		try {
			customer.close();
		} catch (IOException e) {
			e.printStackTrace(RFID.printWriter);
		}
	}
	
	public static boolean isFree() {
		return customer != null;
	}

	public static void main(String... args) {

		try {
			BluetoothManager.initServer();
			state = true;
		} catch (IOException e) {
			Debugger.log("Bluetooth service is not available");
			state = false;
			e.printStackTrace(RFID.printWriter);
		}

	}

	public static boolean getState() {
		return state;
	}

}
