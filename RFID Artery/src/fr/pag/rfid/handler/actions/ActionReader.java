package fr.pag.rfid.handler.actions;

import java.util.ArrayList;
import java.util.Arrays;

import com.pag.objects.Basket;
import com.pag.objects.Item;

import fr.pag.rfid.Debugger;
import fr.pag.rfid.bluetooth.BluetoothManager;
import fr.pag.rfid.board.Board;
import fr.pag.rfid.board.BoardRole;
import fr.pag.rfid.handler.BoardAction;

public class ActionReader implements BoardAction {

	private volatile String stringSplitter = new String();
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
	public void handle(Board board, String data) {
		stringSplitter += data;
		if(stringSplitter.contains(split)) {
			
			final String[] codes = stringSplitter.split(split);
			for(int i = 0 ; i < (codes.length > 1 ? codes.length - 1 : 1) ; i++) {
				retrieveProduct(codes[i].replace("0x", "").replace(" ", ""));
				stringSplitter = stringSplitter.substring(codes[i].length() + 1);
			}
		}
	}

	private volatile ArrayList<String> doneCode = new ArrayList<String>();
	public void retrieveProduct(String product) {
		if(!doneCode.contains(product)) {
			doneCode.add(product);
			System.out.println("Product caught: " + product);
			
			if(BluetoothManager.isFree()) {
				//BluetoothManager.writeToCustomer(basket.toString());
			}
		}
	}

	@Override
	public boolean execute(Board holder, int indication) {
		return false;
	}

}
