package fr.gap.rfid;

import java.util.Arrays;
import java.util.List;

import fr.gap.rfid.handler.RFIDHandler;
import fr.gap.rfid.handler.RFIDHandlerBuilder;

public class RFID {
	
	public static final int BAUD_RATE = 115200;
	public static final List<String> PORTS = Arrays.asList("COM3", "COM6");
	public static final RFIDHandler handler = getHandler();
	
	public static void main(String ... args) {}
	
	public static RFIDHandler getHandler() {
		return new RFIDHandlerBuilder()
		.seekPort()
		.setBaudRate(BAUD_RATE)
		.build();
	}

}
