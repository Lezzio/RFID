package fr.pag.rfid.bluetooth;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.bluetooth.RemoteDevice;
import javax.microedition.io.StreamConnection;

import fr.pag.rfid.Debugger;
import fr.pag.rfid.Protocol;
import fr.pag.rfid.RFID;
import fr.pag.rfid.cypher.Encrypter;
import fr.pag.rfid.database.SQLAdapter;
import fr.pag.rfid.interact.Customer;

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
			Debugger.log("Device in range : " + device.getBluetoothAddress());

			// Retrieve streams
			InputStream inStream = connection.openInputStream();
			OutputStream outStream = connection.openOutputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inStream));
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outStream));

			// Write access needed
			String encryptedCode = Encrypter.encrypt(Protocol.ACCESS_BLUETOOTH);
			bufferedWriter.write(encryptedCode + "\n");
			bufferedWriter.flush();
			
			// Challenge remote
			String reply = bufferedReader.readLine(); // Blocking

			Customer customer = getCustomer(reply, inStream, outStream);

			if (customer != null) {
				BluetoothManager.setCustomer(customer);
				Debugger.log("Connexion valide");
				
				Debugger.log("Waiting request...");
				// Ensure an rssi update or protocol request while connected 
				while (true) {
					DataInputStream dataInputStream = new DataInputStream(inStream);
					byte request = dataInputStream.readByte();
					
					//Client stopped connection
					if(request == -1) {
						connection.close();
						BluetoothManager.setCustomer(null);
						break;
					}
					
					// Inferior to 0 means rssi
					if(request < 0) {
						Debugger.log("RSSI Update: " + request);
						customer.setDistanceIndicator(request);
					} else if(request == Protocol.TRANSACTION_CANCEL) {
						//Clear items
						
					} else if(request == Protocol.TRANSACTION_DONE) {
						try {
							RFID.platform.openCloseGate();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				
			} else {
				inStream.close();
				outStream.close();
				connection.close();
			}

		} catch (IOException e) {
			BluetoothManager.setCustomer(null);
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
	private static Customer getCustomer(String reply, InputStream inStream, OutputStream outStream) {

		// Weaker signal ? -> User further ? /!\ Need widcomm stack to detect
		// form server!
		/*
		 * try { RemoteDevice customerDevice =
		 * RemoteDeviceHelper.implGetRemoteDevice(BluetoothManager.getCustomer()
		 * ); Debugger.log("RSSI " +
		 * RemoteDeviceHelper.readRSSI(customerDevice)); if (signalStrength <
		 * RemoteDeviceHelper.readRSSI(customerDevice)) { return false; } }
		 * catch (IOException e) { e.printStackTrace(RFID.printWriter); }
		 */

		// Credentials validity
		String dbUser = "pag";
		String dbPassword = "131817pag&";
		String dbName = "pag1";

		String[] splittedReply = reply.split("#");
		Short rssi = Short.valueOf(splittedReply[0]);
		String user = splittedReply[1];
		String password = splittedReply[2];

		Customer customer = new Customer(inStream, outStream, rssi);

		// Weaker signal ? -> User further ?
		Debugger.log("RSSI " + rssi);
		if(BluetoothManager.getCustomer() != null) {
			Debugger.log("Current: " + rssi);
			if (BluetoothManager.getCustomer().getDistanceIndicator() > rssi)  {
					System.out.println("Rejected connection too far " + rssi);
			return null;
		}
	}

		Connection connection = (Connection) RFID.sqlDatabase.connect(SQLAdapter.address, dbUser, dbPassword, dbName);
		if (RFID.sqlDatabase.isValid(user, password, connection)) {
			return customer;
		}

		return null;
	}

	public static void main(String... strings) {
		RFID.printWriter = new PrintWriter(System.out, true);
	}

}
