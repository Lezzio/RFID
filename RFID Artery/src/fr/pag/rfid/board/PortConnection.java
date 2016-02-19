package fr.pag.rfid.board;

import com.fazecast.jSerialComm.SerialPort;

public class PortConnection {

	private SerialPort serialPort;
	private int baudRate;

	public PortConnection(SerialPort serialPort, int baudRate) {
		this.setSerialPort(serialPort);
		this.setBaudRate(baudRate);
	}
	
	/*
	 * Empty constructor
	 */
	public PortConnection() {}

	public int getBaudRate() {
		return baudRate;
	}
	public void setBaudRate(int baudRate) {
		this.baudRate = baudRate;
		serialPort.setBaudRate(baudRate);
	}

	public SerialPort getSerialPort() {
		return serialPort;
	}
	public void setSerialPort(SerialPort serialPort) {
		this.serialPort = serialPort;
		serialPort.setBaudRate(baudRate);
	}

	/**
	 * @return state
	 */
	public boolean close() {
		return serialPort.closePort();
	}

}
