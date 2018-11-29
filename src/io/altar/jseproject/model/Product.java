package io.altar.jseproject.model;

import java.util.ArrayList;

public class Product extends Entity{
	private  ArrayList<Shelf> list = new ArrayList<Shelf>();
	private float price;
	private float discountValue;
	private float iva;
	private float pvp;
	

	public Product(float price, float discountValue, float iva, float pvp) {
		super();

		this.price = price;
		this.discountValue = discountValue;
		this.iva = iva;
		this.pvp = pvp;
	}
	
	public ArrayList<Shelf> getList (){
		return list;
		}
	public void addShelf(Shelf shelf) {
		list.add(shelf);
	}
	
	public void removeShelf(Shelf shelf) {
		list.remove(shelf);
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(float discountValue) {
		this.discountValue = discountValue;
	}

	public float getIva() {
		return iva;
	}

	public void setIva(float iva) {
		this.iva = iva;
	}

	public float getPvp() {
		return pvp;
	}

	public void setPvp(float pvp) {
		this.pvp = pvp;
	}

	private String productsToString(){
		String str = "";
		for (int i=0;i<list.size();i++){
			str+=list.get(i).getId();
			str+=", ";
		}
		return str;
	}
	@Override
	public String toString() {
		return "Product " + getId() + "[shelves="+ productsToString()+ "price=" + price + ", discountValue=" + discountValue
				+ ", iva=" + iva + ", pvp=" + pvp + "]";
	}

	public void removeListShelf() {
		list = new ArrayList<Shelf>();
		
	}

}