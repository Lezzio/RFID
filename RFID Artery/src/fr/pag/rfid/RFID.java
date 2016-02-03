package fr.pag.rfid;

import java.util.Arrays;
import java.util.List;

import fr.pag.rfid.actions.ActionTreat;
import fr.pag.rfid.handler.RFIDHandler;
import fr.pag.rfid.handler.RFIDHandlerBuilder;

public class RFID {
	
	public static final int BAUD_RATE = 115200;
	public static final List<String> PORTS = Arrays.asList("COM3", "COM6");
	public static final RFIDHandler handler = getHandler();
	
	public static void main(String ... args) {
		handler.run();
	}
	
	public static RFIDHandler getHandler() {
		return new RFIDHandlerBuilder()
		.seekPort("Arduino", true)
		.setBaudRate(BAUD_RATE)
		.addAction(new ActionTreat("#"))
		.build();
	}

}
