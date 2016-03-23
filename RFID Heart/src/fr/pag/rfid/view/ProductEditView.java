package fr.pag.rfid.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class ProductEditView extends Scene {

	public ProductEditView() throws IOException {
		super(FXMLLoader.load(ProductEditView.class.getResource("ProductEditor-scene.fxml")));
	}
	
}
