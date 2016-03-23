package fr.pag.rfid.utils;

public class Validate {

	public static boolean notNull(Object object) {
		if(object != null) {
			return true;
		}
		return false;
	}

}
