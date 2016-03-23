package fr.pag.rfid;

public class Debugger {
	
	/**
	 * Useful method to write in the proper debug stream
	 * @param msg : Debug message
	 */
	public static void log(String msg) {
		RFID.printWriter.println(msg);
	}

}
