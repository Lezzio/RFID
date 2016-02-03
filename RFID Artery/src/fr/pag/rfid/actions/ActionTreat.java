package fr.pag.rfid.actions;

import java.util.ArrayList;

import fr.pag.rfid.handler.RFIDAction;

public class ActionTreat implements RFIDAction {

	private String stringSplitter = new String();
	private String split;
	
	public ActionTreat(String split) {
		this.split = split;
	}

	@Override
	public void handle(String data) {

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

}
