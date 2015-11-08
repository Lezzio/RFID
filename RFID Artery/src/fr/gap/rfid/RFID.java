package fr.gap.rfid;

import java.util.Arrays;
import java.util.List;

import fr.gap.rfid.handler.RFIDHandler;

public class RFID {
	
	public static final int BAUD_RATE = 115200;
	public static final List<String> PORTS = Arrays.asList("COM3, COM6");
	
	public static void main(String ... args){
		new RFIDHandler();
	}

}
