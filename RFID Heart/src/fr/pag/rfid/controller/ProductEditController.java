package fr.pag.rfid.controller;

import com.pag.objects.Item;

import fr.pag.rfid.GUI;
import fr.pag.rfid.Heart;
import fr.pag.rfid.core.handler.BasketManager;
import fr.pag.rfid.model.PAGItem;
import fr.pag.rfid.model.ProductEditModel;
import fr.pag.rfid.utils.Validate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;

public class ProductEditController {

	@FXML
	private ImageView saveButton;
	@FXML
	private TextField nameField;
	@FXML
	private TextField priceField;
	@FXML
	private TextField codeField;
	@FXML
	private Label actionName;
	@FXML
	private ListView<Item> itemList;

	private ProductEditModel productEditModel;

	@FXML
	public void initialize() {
		productEditModel = new ProductEditModel();
		itemList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Item>() {
			public void changed(ObservableValue<? extends Item> observable, Item oldValue, Item newValue) {
				if (Validate.notNull(newValue)) {
					productEditModel.setEditItem(newValue);
					actionName.setText("Item editing");
					nameField.setText(newValue.getName());
					priceField.setText(Double.toString(newValue.getPrice()));
					codeField.setText(newValue.getCode());
				}
			}
		});
		updateList();
	}

	public void updateList() {
		ObservableList<Item> data = FXCollections.observableArrayList();
		data.addAll(BasketManager.getItems());
		itemList.setItems(data);
	}

	@FXML
	private void addItemClicked() {
		productEditModel.setEditItem(new Item());
		actionName.setText("Item creating");

		// Clear fields
		nameField.setText("");
		priceField.setText("");
		codeField.setText("");

		// "Ask" fields
		nameField.setPromptText("Example");
		priceField.setPromptText("1.99");
		codeField.setPromptText("A4G9C6");
	}

	@FXML
	private void saveClicked() {
		saveButton.setCursor(Cursor.WAIT);
		saveButton.setEffect(new DropShadow());
		// Save
		Item item = productEditModel.getEditItem();

		// Name different ?
		if (item.getName() != null && item.getName() != nameField.getText()) {
			PAGItem.removeItem(item, Heart.PATH);
		}

		item.setName(nameField.getText());
		item.setPrice(Double.parseDouble(priceField.getText()));
		item.setCode(codeField.getText());

		PAGItem.saveItem(item, Heart.PATH);
		BasketManager.updateCache();
		updateList();

		saveButton.setCursor(Cursor.HAND);
		saveButton.setEffect(null);

		nameField.setPromptText(null);
		priceField.setPromptText(null);
		codeField.setPromptText(null);
	}

	@FXML
	private void trashClicked() {
		Item item = productEditModel.getEditItem();
		if (Validate.notNull(item))
			PAGItem.removeItem(item, Heart.PATH);

		// Clear fields
		nameField.setText("");
		priceField.setText("");
		codeField.setText("");
		actionName.setText("Select an item");
		
		BasketManager.updateCache();
		updateList();
	}

	@FXML
	private void updateClicked() {
		BasketManager.updateCache();
		updateList();
	}

	@FXML
	private void statsClicked() {

	}

	@FXML
	private void settingsClicked() {

	}

	@FXML
	private void helpClicked() {

	}

	@FXML
	public void homeClicked() {
		GUI.setScene(GUI.dashboardView);
	}

}
