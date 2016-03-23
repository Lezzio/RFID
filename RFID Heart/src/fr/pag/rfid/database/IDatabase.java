package fr.pag.rfid.database;

import com.pag.objects.Item;

public interface IDatabase {
	
	public Item readItem(String code, Object ... objects);
	public Object connect(String address, String user, String password, String dbname);
	public boolean isValid(String user, String password, Object ... objects);
	public boolean close();

}
