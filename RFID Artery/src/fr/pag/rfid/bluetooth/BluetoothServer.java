package fr.pag.rfid.bluetooth;

import java.io.IOException;

import javax.bluetooth.LocalDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

import fr.pag.rfid.Debugger;

public class BluetoothServer {

	private LocalDevice holder;
	private StreamConnection connection;
	private UUID uuid;
	private String connectionString;

	public BluetoothServer(LocalDevice holder, int agent) throws IOException {

		holder.setDiscoverable(agent);
		this.holder = holder;

		// Create a UUID for SPP & Connection String
		uuid = new UUID("1101", true);
		connectionString = "btspp://localhost:" + uuid + ";name=PAG";
	}

	public void start() throws IOException {

		// Open server
		StreamConnectionNotifier streamConnNotifier = (StreamConnectionNotifier) Connector.open(connectionString);

		// Wait for client connection
		Debugger.log("Bluetooth server ready! Waiting for clients ...");
		while (true) {
			
			connection = streamConnNotifier.acceptAndOpen(); // Blocking
			
			// When a connection is opened process it asynchronously
			ProcessBluetooth process = new ProcessBluetooth(connection);
			process.start();
		}
	}

}
