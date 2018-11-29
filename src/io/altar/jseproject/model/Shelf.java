package io.altar.jseproject.model;

public class Shelf extends Entity{

	private float capacity;
	private Product product=null;
	private float rentPrice;
	public Object removeProduct;
	public Shelf(float capacity,float rentPrice) {
		super();
		this.capacity = capacity;

		this.rentPrice = rentPrice;
	}
	public float getCapacity() {
		return capacity;
	}
	public void setCapacity(float capacity) {
		this.capacity = capacity;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public void removeProduct (){
		this.product = null;
	}
	public float getRentPrice() {
		return rentPrice;
	}
	public void setRentPrice(float rentPrice) {
		this.rentPrice = rentPrice;
	}
	@Override
	public String toString() {
		if(product!=null)
			return "Shelf [capacity=" + capacity + ", product=" + product.getId() + ", rentPrice=" + rentPrice + "]";
		else
			return "Shelf [capacity=" + capacity + ", product=" +" nada"+ ", rentPrice=" + rentPrice + "]";
	}	
}
