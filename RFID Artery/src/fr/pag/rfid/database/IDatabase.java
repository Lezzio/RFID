package fr.pag.rfid.database;

public interface IDatabase {
	
	public Object connect(String address, String user, String password, String dbname);
	public boolean close();
	public boolean isValid(String user, String password, Object ... objects);

}
