package com.pag.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Basket implements Serializable {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = 9084309445064824567L;

	private ArrayList<Item> items = new ArrayList<Item>();
	private String market;
	private String user;
	private Date date;
	private double totalPrice;

	public Basket() {}

	public Basket(ArrayList<Item> items) {
		this.items = items;
	}

	public Basket(ArrayList<Item> items, String market, String user, Date date, double totalPrice) {
		this.items = items;
		this.market = market;
		this.user = user;
		this.date = date;
		this.totalPrice = totalPrice;
	}

	public String getMarketName() {
		return market;
	}

	public String getUser() {
		return user;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public Date getDate() {
		return date;
	}

	public ArrayList<Item> getItems() {
		return items;
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
