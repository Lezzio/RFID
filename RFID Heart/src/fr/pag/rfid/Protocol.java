package fr.pag.rfid;

public class Protocol {
	
	private static final int PORT_HEART = 385;
	private static final String ACCESS_HEART = "9AOV4R7Q6D";

	//Reader
	public static final byte ASK_ROLE = 3;
	//Controller
	public static final byte GATE_OPEN = 0;
	public static final byte GATE_CLOSE = 1;
	public static final byte GATE_ROTATE_ON = 2;
	public static final byte GATE_ROTATE_OFF = 3;
	//Heart
	
	
}
