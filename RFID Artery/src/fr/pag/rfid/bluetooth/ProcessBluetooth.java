package fr.pag.rfid.bluetooth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.bluetooth.RemoteDevice;
import javax.microedition.io.StreamConnection;

import fr.pag.rfid.Debugger;
import fr.pag.rfid.RFID;
import fr.pag.rfid.database.SQLAdapter;

public class ProcessBluetooth extends Thread {

	private StreamConnection connection;

	public ProcessBluetooth(StreamConnection connection) {
		this.connection = connection;
	}

	@Override
	public void start() {
		super.start();
	}

	@Override
	public void run() {
		try {
			RemoteDevice device = RemoteDevice.getRemoteDevice(connection);
			Debugger.log("Device in range :" + device.getBluetoothAddress());

			// Retrieve streams
			InputStream inStream = connection.openInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inStream));
			
			// Challenge remote
			String reply = bufferedReader.readLine(); // Blocking
			Debugger.log("Reply: " + reply);
			if (isValid(reply)) {
				BluetoothManager.setCustomer(connection);
				Debugger.log("Connexion valide");
			} else {
				connection.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Check if the remote device is the correct customer
	 * 
	 * @param reply
	 *            : Challenge reply
	 * @param signalStrength
	 *            : The signal strength of the connection
	 * @return
	 */
	private static boolean isValid(String reply) {

		// Weaker signal ? -> User further ? /!\ Need widcomm stack
		/*
		try {
			RemoteDevice customerDevice = RemoteDeviceHelper.implGetRemoteDevice(BluetoothManager.getCustomer());
			Debugger.log("RSSI " + RemoteDeviceHelper.readRSSI(customerDevice));
			if (signalStrength < RemoteDeviceHelper.readRSSI(customerDevice)) {
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace(RFID.printWriter);
		}
		 */
		
		// Credentials validity
		String dbUser = "pag";
		String dbPassword = "131817pag&";
		String dbName = "pag1";

		String[] splittedReply = reply.split("#");
		String user = splittedReply[0];
		String password = splittedReply[1];

		Connection connection = (Connection) RFID.sqlDatabase.connect(SQLAdapter.address, dbUser, dbPassword, dbName);
		return RFID.sqlDatabase.isValid(user, password, connection);
	}

	public static void main(String... strings) {
		RFID.printWriter = new PrintWriter(System.out, true);
	}

}
