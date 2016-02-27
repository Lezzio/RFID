package fr.pag.rfid.handler.actions;

import java.util.ArrayList;
import java.util.Arrays;

import fr.pag.rfid.Debugger;
import fr.pag.rfid.bluetooth.BluetoothManager;
import fr.pag.rfid.board.Board;
import fr.pag.rfid.board.BoardRole;
import fr.pag.rfid.handler.BoardAction;
import fr.pag.rfid.objects.Basket;
import fr.pag.rfid.objects.Item;

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
			product = product.replace("0x", "").replace(" ", "");
			System.out.println("Product caught: " + product);
			Item retrieved = new Item("Chocapic", product, 2.49);
			ArrayList<Item> items = new ArrayList<Item>();
			items.add(retrieved);
			Basket basket = new Basket(items);
			Debugger.log(basket.toString());
			
			if(BluetoothManager.isFree()) {
				BluetoothManager.writeToCustomer(basket.toString());
			}
		}
	}

	@Override
	public boolean execute(Board holder, int indication) {
		return false;
	}

}
