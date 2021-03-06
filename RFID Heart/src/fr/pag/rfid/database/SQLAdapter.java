package fr.pag.rfid.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.pag.objects.Item;

import fr.pag.rfid.cypher.Decrypter;

public class SQLAdapter implements IDatabase {
	
	public final static String address = "149.202.55.170";
	private final static int DB_PORT = 5432;

	@Override
	public Connection connect(String address, String user, String password, String dbName) {
		try {
			
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://" + address + ":" + DB_PORT + "/" + dbName;
			Connection connection = DriverManager.getConnection(url, user, password);
			return connection;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean isValid(String user, String password, Object ... objects) {
		
		try {
			
			Connection connection = ((Connection) objects[0]);
			Statement state = connection.createStatement();
			
			ResultSet rs = state.executeQuery("SELECT crypt_pass FROM users WHERE pseudo = " + "'" + user + "'");
			rs.next();

			if(password.equals
					(Decrypter.decrypt(rs.getString(1)))) {
				return true;
			}
			
			state.close();
			connection.close();
			
		} catch (SQLException e) {
			//Exception comes from credentials error
			return false;
		}
		
		return false;
	}

	@Override
	public boolean close() {
		return false;
	}

	@Override
	public Item readItem(String code, Object... objects) {
		return null;
	}

}
