package fr.pag.rfid.handler.actions;

import java.util.ArrayList;

import fr.pag.rfid.board.Board;
import fr.pag.rfid.board.BoardRole;
import fr.pag.rfid.handler.BoardAction;

public class ActionReader implements BoardAction {

	private String stringSplitter = new String();
	private String split;
	
	public ActionReader(String split) {
		this.split = split;
	}
	
	@Override
	public BoardRole getNeededRole() {
		return BoardRole.READER;
	}
	
	@Override
	public boolean isAsync() {
		return true;
	}

	@Override
	public void handle(Board holder, String data) {
		stringSplitter += data;
		if(stringSplitter.contains(split)) {
			
			final String[] codes = stringSplitter.split(split);
			for(int i = 0 ; i < codes.length - 1 ; i++) {
				retrieveProduct(codes[i]);
			}
			
		}

	}

	private final ArrayList<String> doneCode = new ArrayList<String>();
	public void retrieveProduct(String product) {
		if(!doneCode.contains(product)) {
			doneCode.add(product);
			System.out.println("Product caught: " + product);
		}
	}

	@Override
	public boolean execute(Board holder, int indication) {
		return false;
	}

}
