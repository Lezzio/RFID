package com.pag.objects;

import java.util.ArrayList;
import java.util.Date;

public class Basket {

	private ArrayList<Item> items = new ArrayList<Item>();
	private String pseudo;
	private int totalPrice;
	private Date date;
	
	public Basket() {}
	
	public Basket(ArrayList<Item> items) {
		this.items = items;
	}
	
	public void addItem(Item item) {
		items.add(item);
	}
	public void removeItem(Item item) {
		items.remove(item);
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(Item item : items) {
			builder.append(item.toString() + "@");
		}
		return builder.toString();
	}
	public static Basket fromString(String itemsString) {
		ArrayList<Item> items = new ArrayList<Item>();
		for(String item : itemsString.split("@")) {
			items.add(Item.fromString(item));
		}
		return new Basket(items);
	}
	
}
