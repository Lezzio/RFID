package fr.pag.rfid;

public class Protocol {
	
	public static final int PORT_HEART = 385;
	public static final String ACCESS_HEART = "9AOV4R7Q6D";
	public static final String ACCESS_BLUETOOTH = "C4G9Q0H3M7";

	//Reader
	public static final byte ASK_ROLE = 3;
	//Controller
	public static final byte GATE_OPEN = 0;
	public static final byte GATE_CLOSE = 1;
	public static final byte GATE_ROTATE_ON = 4;
	public static final byte GATE_ROTATE_OFF = 5;
	//Phone
	public static final byte TRANSACTION_DONE = 6;
	public static final byte TRANSACTION_CANCEL = 7;
	//Heart
	
}
