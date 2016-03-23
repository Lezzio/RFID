package fr.pag.rfid.handler.actions;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

import com.pag.objects.Basket;

import fr.pag.rfid.RFID;
import fr.pag.rfid.bluetooth.BluetoothManager;
import fr.pag.rfid.board.Board;
import fr.pag.rfid.board.BoardRole;
import fr.pag.rfid.handler.BoardAction;
import fr.pag.rfid.interact.Customer;
import fr.pag.rfid.socket.SocketManager;
import fr.pag.rfid.utils.Debugger;

public class ActionReader implements BoardAction {
	
	private static volatile TimerTask task;
	
	private volatile Timer timer;
	private volatile ArrayList<String> itemQueue = new ArrayList<String>();

	private volatile String stringSplitter = new String();
	private String split;
	
	public ActionReader(String split) {
		this.split = split;
		timer = new Timer();
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

	public void retrieveProduct(String product) {
		Debugger.log("Product caught: " + product);
		itemQueue.add(product);

		//Cancel previous
		if(task != null) task.cancel();
		
		task = new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("Ongoing treatment...");
				try {
					//Get basket
					Basket basket = SocketManager.getBasket(itemQueue).get();
					
					Customer customer = BluetoothManager.getCustomer();
					basket.setUser(customer.getName());
					basket.setDate(new Date());
					System.out.println(basket);
					
					//Push items
					customer.writeObject(basket);
					System.out.println("Basket sent!");
					
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
		};
		
		//Send again
		timer.schedule(task, 1000 * 3);
		
	}

	@Override
	public boolean execute(Board holder, int indication) {
		return false;
	}

}
