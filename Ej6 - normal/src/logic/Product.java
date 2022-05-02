package logic;

public class Product {
	private Integer id;
	private String name;
	private String descripcion;
	private double price;
	private Integer stock;
	private boolean shippingIncluded;
	private boolean esNull=true;
	
	public void marcarComoCargado() { esNull=false; }
	public  boolean esNull() {return esNull;}
	
	
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
	
	public String toCard() {
		return 	"\t|id: "+id+
				"\n\t|name: "+name+
				"\n\t|desc: "+descripcion+
				"\n\t|price: "+price+
				"\n\t|stock: "+stock+
				"\n\t|Incluye envio: "+((shippingIncluded)?"SI":"NO")+"\n";		
	}
	
}
