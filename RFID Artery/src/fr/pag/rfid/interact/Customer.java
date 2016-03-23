package fr.pag.rfid.interact;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import fr.pag.rfid.RFID;

public class Customer {

	private String name;
	
	private InputStream inStream;
	private OutputStream outStream;
	private int distanceIndicator;
	
	public Customer(String name, InputStream inStream, OutputStream outStream, int distanceIndicator) {
		this.inStream = inStream;
		this.outStream = outStream;
		this.distanceIndicator = distanceIndicator;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean writeString(String jsonMsg) {
		
		 PrintStream printStream = new PrintStream(outStream);
		 printStream.println(jsonMsg);
		 printStream.flush();
		 
		return true;
	}
	public boolean writeObject(Object object) {
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);
			objectOutputStream.writeObject(object);
			objectOutputStream.flush();
		} catch(IOException e) {
			e.printStackTrace(RFID.printWriter);
			return false;
		}
		return true;
	}
	
	public InputStream getInputStream() {
		return inStream;
	}
	
	public OutputStream getOutputStream() {
		return outStream;
	}
	
	public void setDistanceIndicator(int distanceIndicator) {
		this.distanceIndicator = distanceIndicator;
	}
	
	public int getDistanceIndicator() {
		return distanceIndicator;
	}

}
