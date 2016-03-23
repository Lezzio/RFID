package fr.pag.rfid.core.handler;

import java.io.File;
import java.io.FileFilter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;

import com.pag.objects.Basket;
import com.pag.objects.Item;

import fr.pag.rfid.Heart;
import fr.pag.rfid.database.IDatabase;
import fr.pag.rfid.database.SQLAdapter;
import fr.pag.rfid.model.PAGItem;
import fr.pag.rfid.utils.Validate;

public class BasketManager {

	private static ArrayList<Item> localCache = new ArrayList<Item>();
	
	public static Basket getBasket(String[] codes) {
		
		ArrayList<Item> items = new ArrayList<Item>();
		double totalPrice = 0.00D;
		
		//Match all items
		for(String code : codes) {
			
			Item item = getItem(code);
			if(Validate.notNull(item)) {
				totalPrice += item.getPrice(); //Compute total price
				items.add(item);
			}
			
		}
		return new Basket(items, Heart.MARKET_NAME, "", totalPrice);
	}
	
	public static Item getItem(String code) {
		return getItem(code, false);
	}
	
	public static Item getItem(String code, boolean forceServer) {
		//Local
		if(!forceServer) {
			Iterator<Item> itemIterator = localCache.iterator();
			while(itemIterator.hasNext()) {
				Item nextItem = itemIterator.next();
				if(code.equals(nextItem.getCode())) {
					System.out.println("Found " + nextItem.getName());
					return nextItem;
				} else continue;
			}
		}
		//Server
		String dbUser = "pag";
		String dbPassword = "131817pag&";
		String dbName = "pag1";
		
		final IDatabase sqlAdapter = Heart.sqlAdapter;
		Connection connection = (Connection) sqlAdapter.connect(SQLAdapter.address, dbUser, dbPassword, dbName);
		
		return sqlAdapter.readItem(code, connection);
	}
	
	public static void updateCache() {
		//Reset
		localCache.clear();
		
		//Reload all
		File directory = new File(Heart.PATH);
		directory.mkdirs();
		
		if(directory.isDirectory()) {
			for(File file : directory.listFiles(new FileFilter() {
				
				@Override
				public boolean accept(File pathname) {
					if(pathname.getName().endsWith("pag-item")) {
						return true;
					}
					return false;
				}
			})) {
				System.out.println("Loading " + file.getName());
				//Load it and put into cache
				Item item = PAGItem.loadItem(file);
				localCache.add(item);
				
			}
		}
		
	}
	
	public static ArrayList<Item> getItems() {
		return localCache;
	}
	
	

}
