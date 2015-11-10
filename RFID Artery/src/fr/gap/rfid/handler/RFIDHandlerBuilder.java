package fr.gap.rfid.handler;

import com.fazecast.jSerialComm.SerialPort;

import fr.gap.rfid.RFID;

public final class RFIDHandlerBuilder {

	private SerialPort serialPort;
	private int baudRate;

	/**
	 * Set the SerialPort where the data to handle will come from
	 * Let it empty (null) to seek a port alone
	 * @param port
	 */
	public RFIDHandlerBuilder setPort(SerialPort port) {

		if (port != null) {
			this.serialPort = port;
		} else {

			for (SerialPort serialPort : SerialPort.getCommPorts()) {
				if (serialPort.getDescriptivePortName().startsWith("Arduino")
						|| RFID.PORTS.contains(serialPort.getSystemPortName())
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
		}
		return this;
	}

	/**
	 * Creates and instantiate the RFIDHandler properly regarding all assigned stuff and applied parameters
	 * @return 
	 * 
	 */
	public RFIDHandler build() {
		serialPort.setBaudRate(baudRate);
		
		return new RFIDHandler(serialPort, null, baudRate);
	}

}
