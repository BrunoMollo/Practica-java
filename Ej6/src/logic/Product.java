package logic;

public class Product {
	Integer id;
	String name;
	String descripcion;
	double price;
	Integer stock;
	boolean shippingIncluded;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	public boolean isShippingIncluded() {
		return shippingIncluded;
	}
	public void setShippingIncluded(boolean shippingIncluded) {
		this.shippingIncluded = shippingIncluded;
	}
	
	
	@Override
	public String toString() {
		return "["+id+"-"+name+"-"+descripcion+"-$"+price+"-stock:"+stock+"-"+((shippingIncluded)?"Incluye envio":"NO incluye envio")+"]";
	}
	
}
