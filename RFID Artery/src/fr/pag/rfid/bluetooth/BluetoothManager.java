package fr.pag.rfid.bluetooth;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.microedition.io.StreamConnection;

import com.pag.objects.Basket;
import com.pag.objects.Item;

import fr.pag.rfid.RFID;
import fr.pag.rfid.async.ThreadPool;
import fr.pag.rfid.interact.Customer;
import fr.pag.rfid.interact.Platform;
import fr.pag.rfid.utils.Debugger;
import fr.pag.rfid.utils.Validate;

public class BluetoothManager {

	private static BluetoothServer server;
	private static boolean state = false;
	
	private static Customer customer;

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

	public static void setCustomer(Customer customer) {
		Debugger.log("Client set to " + customer);
		BluetoothManager.customer = customer;
	}
	
	public static Customer getCustomer() {
		return customer;
	}

	public static boolean hasCustomer() {
		return Validate.notNull(customer);
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
