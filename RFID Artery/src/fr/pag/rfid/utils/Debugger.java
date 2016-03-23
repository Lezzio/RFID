package fr.pag.rfid.utils;

import fr.pag.rfid.RFID;

public class Debugger {
	
	/**
	 * Useful method to write in the proper debug stream
	 * @param msg : Debug message
	 */
	public static void log(String msg) {
		RFID.printWriter.println(msg);
	}

}
