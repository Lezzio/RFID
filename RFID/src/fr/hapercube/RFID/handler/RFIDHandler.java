package fr.hapercube.RFID.handler;

import java.io.IOException;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import fr.hapercube.RFID.RFID;


public class RFIDHandler {

	private SerialPort serialPort;
	
	public RFIDHandler(SerialPort serialPort){
		this.serialPort = serialPort;
	}
	public RFIDHandler(){
		for(SerialPort port : SerialPort.getCommPorts()){
				if(RFID.PORTS.contains(port.getSystemPortName()) && port.openPort()){
					serialPort = port; 
					System.out.println("Port found & opened: " + port.getDescriptivePortName());
					break;
				}
				System.out.println("Checked -> Name: " + port.getDescriptivePortName() + " Baud: " + port.getBaudRate() + " Opened: " + port.isOpen());
		}
		if(serialPort != null){
			serialPort.setBaudRate(RFID.BAUD_RATE);
			serialPort.addDataListener(new SerialPortListener());
		}
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
				
				final byte[] readBuffer = new byte[port.bytesAvailable()];
				if(port.bytesAvailable() > 0){
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
