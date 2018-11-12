package com.example.model;

// ----------------------------------
// Represents an item from a shop
//----------------------------------
public class ShopItem {
	
	String name;
	String price; 
	int quantity;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "ShopItem [name=" + name + ", price=" + price + ", quantity=" + quantity + "]";
	}
}
