package fr.hapercube.RFID.handler;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;


public class RFIDHandler {

	private SerialPort serialPort;
	
	public RFIDHandler(SerialPort serialPort){
		this.serialPort = serialPort;
	}
	public RFIDHandler(){
		for(SerialPort port : SerialPort.getCommPorts())
				System.out.println("Name: " + port.getDescriptivePortName() + " Opened: " + port.isOpen());
	}
	
	public class SerialPortListener implements SerialPortDataListener {

		@Override
		public int getListeningEvents() {
			return 0;
		}

		@Override
		public void serialEvent(SerialPortEvent event) {
			
		}
		
	}
}
