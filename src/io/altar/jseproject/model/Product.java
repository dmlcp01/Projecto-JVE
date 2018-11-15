package io.altar.jseproject.model;

public class Product {
	private int id=0;
	private Shelf shelves[];
	private int price;
	private int iva;
	private int pvp;
	public Product (Shelf[] shelves, int price, int iva, int pvp){
		this.shelves=shelves;
		this.price=price;
		this.iva=iva;
		this.pvp=pvp;
	}
	public Product() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Shelf[] getShelves() {
		return shelves;
	}
	public void setShelves(Shelf[] shelves) {
		this.shelves = shelves;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getIva() {
		return iva;
	}
	public void setIva(int iva) {
		this.iva = iva;
	}
	public int getPvp() {
		return pvp;
	}
	public void setPvp(int pvp) {
		this.pvp = pvp;
	}
	

}
