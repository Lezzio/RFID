package fr.gap.rfid.handler;

import java.util.ArrayList;

import com.fazecast.jSerialComm.SerialPort;

import fr.gap.rfid.RFID;
import fr.gap.rfid.handler.RFIDHandler.SerialPortListener;

public class RFIDHandlerBuilder {

	private SerialPort serialPort;
	private ArrayList<RFIDAction> actions = new ArrayList<RFIDAction>();
	private int baudRate;

	/**
	 * Set the SerialPort where the data to handle will come from
	 * Let it empty (null) to seek a port alone
	 * @param port
	 */
	public RFIDHandlerBuilder setSerialPort(SerialPort port) {

		if (port != null) {
			this.serialPort = port;
		} else {
			seekPort();
		}
		
		return this;
	}
	
	/**
	 * Iterate all ports to seek one which is working
	 */
	public RFIDHandlerBuilder seekPort() {
		
		for (SerialPort serialPort : SerialPort.getCommPorts()) {
			if ((serialPort.getDescriptivePortName().startsWith("Arduino")
					|| RFID.PORTS.contains(serialPort.getSystemPortName()))
					&& serialPort.openPort()) {
				
				this.serialPort = serialPort; //Assign port
				
				System.out.println("Port found & opened: "
						+ serialPort.getDescriptivePortName());
				break;
			}
			System.out.println("Checked -> Name: "
					+ serialPort.getDescriptivePortName() + " Baud: "
					+ serialPort.getBaudRate() + " Opened: " + serialPort.isOpen());
		}
		
		return this;
	}
	
	/**
	 * Add an action to perform when an identification is done
	 */
	public RFIDHandlerBuilder addAction(RFIDAction action){
		actions.add(action);
		
		return this;
	}

	
	/**
	 * Set the max baud rate
	 */
	public RFIDHandlerBuilder setBaudRate(int baudRate) {
		this.baudRate = baudRate;
		
		return this;
	}

	/**
	 * Creates and instantiate the RFIDHandler properly regarding all assigned stuff and applied parameters
	 */
	public RFIDHandler build() {
		ArrayList<RFIDAction> actions = new ArrayList<RFIDAction>();
		actions.addAll(this.actions);
		serialPort.setBaudRate(baudRate);
		
		return new RFIDHandler(serialPort, actions, baudRate);
	}

}
