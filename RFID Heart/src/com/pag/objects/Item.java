package com.pag.objects;

import java.io.Serializable;

public class Item implements Serializable {
	
	/**
	 * Serial version
	 */
	private static final long serialVersionUID = -5227113464666972218L;
	
	private String name;
	private String code;
	private double price;
	
	public Item(String name, String code, double price) {
		this.setName(name);
		this.setCode(code);
		this.setPrice(price);
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return new String(name + "#" + code + "#" + price);
	}
	
	public static Item fromString(String item) {
		String[] array = item.split("#");
		return new Item(array[0], array[1], Double.parseDouble(array[2]));
	}

}