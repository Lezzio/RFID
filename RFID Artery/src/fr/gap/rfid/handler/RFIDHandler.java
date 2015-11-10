package fr.gap.rfid.handler;

import java.io.IOException;
import java.util.ArrayList;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import fr.gap.rfid.RFID;


public final class RFIDHandler {

	private SerialPort serialPort;
	private ArrayList<RFIDAction> actions = new ArrayList<RFIDAction>();
	private int baudRate;
	
	public RFIDHandler(SerialPort serialPort, ArrayList<RFIDAction> actions, int baudRate) {
		this.serialPort = serialPort;
		this.actions = actions;
		this.baudRate = baudRate;
		
		serialPort.addDataListener(new SerialPortListener());
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
				SerialPort port = event.getSerialPort();
				
				if(port.bytesAvailable() > 0){
					
					final byte[] readBuffer = new byte[port.bytesAvailable()];
					
					try {
						port.getInputStream().read(readBuffer);
						String data = new String(readBuffer);
						System.out.println(data);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
			} else return;
		}
		
	}
}
