package fr.gap.rfid.actions;

import fr.gap.rfid.handler.RFIDAction;

public class ActionTreat implements RFIDAction {
	
	StringBuilder stringBuilder = new StringBuilder();
	
	@Override
	public void handle(String data) {
		stringBuilder.append(data);
		//Sort with hexadecimal value
		String[] hexaData = stringBuilder.toString().split("0x");
		
		//Identify each product
		if(hexaData.length >= 4) {
			String productString = "";

			//TODO End it
		 for(String hexa : hexaData) {
			 
			 int a = 0;
			 System.out.println(hexa);
			 productString += hexa;
			 
			 if(a++ >= 4) {
				 a = 0;
				 retrieveProduct(productString);
				 continue;
			 }
			 
		 }
			//Release memory
			stringBuilder.delete(0, stringBuilder.length());
		 
		} 
	}
	
	public void retrieveProduct(String product){
		System.out.println("Product caught: " + product);
	}
	
	

}
