package fr.hapercube.RFID;

import java.util.Arrays;
import java.util.List;

import fr.hapercube.RFID.handler.RFIDHandler;

public class RFID {
	
	public static final int BAUD_RATE = 115200;
	public static final List<String> PORTS = Arrays.asList("COM3");
	
	public static void main(String ... args){
		new RFIDHandler();
	}

}
