package fr.pag.rfid.database;

public class MongoAdapter implements IDatabase {

	@Override
	public Object connect(String address, String user, String password, String dbname) {
		return null;
	}

	@Override
	public boolean close() {
		return false;
	}

	@Override
	public boolean isValid(String user, String password, Object... objects) {
		return false;
	}

}
