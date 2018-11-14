package io.altar.jseproject.model;

public class Shelf {
	private int id;
	private int capacity;
	private Product product;
	private int rentPrice;
	
	public Shelf(int id, int capacity, Product product, int rentPrice) {
		this.id = id;
		this.capacity = capacity;
		this.product = product;
		this.rentPrice = rentPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getRentPrice() {
		return rentPrice;
	}

	public void setRentPrice(int rentPrice) {
		this.rentPrice = rentPrice;
	}
	
	

}
