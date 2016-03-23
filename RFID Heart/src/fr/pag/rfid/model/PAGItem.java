package fr.pag.rfid.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.pag.objects.Item;

import fr.pag.rfid.core.handler.BasketManager;

public class PAGItem {

	public static void saveItem(Item item, File directory) {
		saveItem(item, directory.getPath());
	}
	public static void saveItem(Item item, String directoryPath) {
		File itemFile = new File(directoryPath + "/" + item.getName() + ".pag-item");

		try {
			itemFile.createNewFile();

			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(itemFile));
			objectOutputStream.writeObject(item);
			objectOutputStream.flush();

			objectOutputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Item loadItem(File itemFile) {
		Item item = null;
		try {

			if (!itemFile.exists())
				return null;

			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(itemFile));
			item = (Item) objectInputStream.readObject();

			objectInputStream.close();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return item;
	}
	
	public static void removeItem(Item item, String directoryPath) {
		File itemFile = new File(directoryPath + "/" + item.getName() + ".pag-item");
		itemFile.delete();
	}

}
