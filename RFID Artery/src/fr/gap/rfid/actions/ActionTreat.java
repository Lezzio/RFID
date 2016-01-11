package fr.gap.rfid.actions;

import fr.gap.rfid.handler.RFIDAction;

public class ActionTreat implements RFIDAction {

	private String stringSplitter = new String();
	private CharSequence split;
	
	public ActionTreat(CharSequence split) {
		this.split = split;
	}

	@Override
	public void handle(String data) {

		stringSplitter += data;
		if(stringSplitter.contains(split)) {
			
			for(String code : stringSplitter.split("*")) {
				retrieveProduct(code);
			}
			
		}

	}

	public void retrieveProduct(String product) {
		System.out.println("Product caught: " + product);
	}

}
