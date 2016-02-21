package fr.pag.rfid.board;

import com.fazecast.jSerialComm.SerialPort;

import fr.pag.rfid.handler.BoardHandler;

public class Board extends PortConnection {
	
	private String name;
	private BoardHandler handler;
	private BoardRole role;
	
	private boolean listening;
	
	/**
	 * Build from builder
	 */
	public Board(SerialPort serialPort, int baudRate) {
			super(serialPort, baudRate);
	}
	
	/*
	 * Empty constructor
	 */
	public Board() {}
	
	public void setHandler(BoardHandler handler) {
		this.handler = handler;
	}
	
	public BoardHandler getHandler() {
		return handler;
	}
	
	public void setRole(BoardRole role) {
		this.role = role;
	}
	
	public BoardRole getRole() {
		return role;
	}
	
	public boolean isListening() {
		return listening;
	}
	
	public void setListening(boolean listening) {
		//Is different ?
		if(this.listening != listening) {
			this.listening = listening;
			
			if(listening) {
				handler.run();
			} else {
				handler.stop();
			}
			
		}
	}
	
	public void execute(int indication) {
		handler.execute(indication);
	}
	
	
}
