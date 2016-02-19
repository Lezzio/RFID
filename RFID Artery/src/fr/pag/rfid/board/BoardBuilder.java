package fr.pag.rfid.board;

import java.util.ArrayList;

import com.fazecast.jSerialComm.SerialPort;

import fr.pag.rfid.RFID;
import fr.pag.rfid.handler.BoardAction;
import fr.pag.rfid.handler.BoardHandler;

public class BoardBuilder {

	private SerialPort serialPort;
	private ArrayList<BoardAction> actions = new ArrayList<BoardAction>();
	private int baudRate;
	private String portName;
	private String name;

	/**
	 * Set the SerialPort where the data to handle will come from
	 * Let it empty (null) to seek a port alone
	 * @param port
	 */
	public BoardBuilder setSerialPort(SerialPort port) {

		if (port != null) {
			this.serialPort = port;
		}
		
		return this;
	}
	
	/**
	 * Iterate all ports to seek one which is working
	 */
	public BoardBuilder seekPort(String portName, boolean talk) {
		
		this.portName = portName;
		for (SerialPort serialPort : SerialPort.getCommPorts()) {
			if ((serialPort.getDescriptivePortName().contains(portName)
					|| RFID.PORTS.contains(serialPort.getSystemPortName()))
					&& serialPort.openPort()) {
				
				this.serialPort = serialPort; //Assign port
				
				System.out.println("Port found & opened: "
						+ serialPort.getDescriptivePortName());
				break;
			}
			if(talk) System.out.println("Checked -> portName: "
						+ serialPort.getDescriptivePortName() + " Baud: "
						+ serialPort.getBaudRate() + " Opened: " + serialPort.isOpen());
		}
		
		return this;
	}
	
	/**
	 * Add an action to perform when an identification is done
	 */
	public BoardBuilder addAction(BoardAction action){
		actions.add(action);
		
		return this;
	}

	
	/**
	 * Set the max baud rate
	 */
	public BoardBuilder setBaudRate(int baudRate) {
		this.baudRate = baudRate;
		
		return this;
	}
	
	public BoardBuilder setName(String name) {
		this.name = name;
		
		return this;
	}

	/**
	 * Creates and instantiate the RFIDHandler properly regarding all assigned stuff and applied parameters
	 */
	public Board build() {
		
		
		if(serialPort == null) {
			System.out.println("NullPointer due to SerialPort : " + serialPort);
			waitGear(); //Blocking
		} 
		
		Board board = new Board(serialPort, baudRate);
		board.setHandler(new BoardHandler(board, actions));
		
		return board;
	}
	
	private void waitGear() {
		System.out.println("Waiting for gear ...");
		while(serialPort == null) {
			seekPort(portName, false);
		}
	}



}
