package fr.pag.rfid.handler.actions;

import java.io.IOException;
import java.io.OutputStream;

import fr.pag.rfid.board.Board;
import fr.pag.rfid.board.BoardRole;
import fr.pag.rfid.handler.BoardAction;

public class ActionRelay implements BoardAction {

	@Override
	public void handle(Board holder, String data) {
		
	}

	@Override
	public BoardRole getNeededRole() {
		return BoardRole.CONTROLLER;
	}
	
	@Override
	public boolean isAsync() {
		return true;
	}

	@Override
	public boolean execute(Board holder, int indication) {
		try {
			OutputStream outputStream = holder.getSerialPort().getOutputStream();
			outputStream.write(indication);
			outputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	

}
