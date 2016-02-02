package fr.gap.rfid.handler;

import java.io.IOException;
import java.util.ArrayList;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;


public class RFIDHandler implements Runnable {

	private SerialPort serialPort;
	private ArrayList<RFIDAction> actions = new ArrayList<RFIDAction>();
	private int baudRate;
	
	public RFIDHandler(SerialPort serialPort, ArrayList<RFIDAction> actions, int baudRate) {
		this.serialPort = serialPort;
		this.actions = actions;
		this.baudRate = baudRate;
	}
	
	/**
	 * Returns the baud rate used in the serial communication
	 * 
	 */
	public int getBaudRate() {
		return baudRate;
	}
	public class SerialPortListener implements SerialPortDataListener {

		@Override
		public int getListeningEvents() {
			return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
		}

		@Override
		public void serialEvent(SerialPortEvent event) {
			if(event.getEventType() == SerialPort.LISTENING_EVENT_DATA_RECEIVED){
				
				if(serialPort.bytesAvailable() > 0){
					
					final byte[] readBuffer = new byte[serialPort.bytesAvailable()];
					
					try {
						serialPort.getInputStream().read(readBuffer);
						String data = new String(readBuffer);
						
						//Perform actions
						for(RFIDAction action : actions) {
							action.handle(data);
						}
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
			} else return;
		}
		
	}
	@Override
	public void finalize() {
		System.out.println("DEAD");
	}

	@Override
	public void run() {
		serialPort.addDataListener(new SerialPortListener());
	}
	public void stop() {
		serialPort.removeDataListener();
	}
}
