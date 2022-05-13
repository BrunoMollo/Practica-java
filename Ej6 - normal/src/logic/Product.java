package logic;

import java.time.LocalDateTime;

import ui.Global;


public class Product {
	private Integer id;
	private String name;
	private String descripcion;
	private Double price;
	private Integer stock;
	private Boolean shippingIncluded;
	private LocalDateTime disableOn;
	
	
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
	
	public LocalDateTime  getDisableOn() {
		return disableOn;
	}
	public void setDisableOn(LocalDateTime  disableOn) {
		this.disableOn = disableOn;
	}
	
	
	public String printDisableOn(){
		if(disableOn==null) { return "<vacio>"; }
		return disableOn.format(Global.formatoFecha);
	}
	
	@Override
	public String toString() {
		return 	"\t|id: "+id+
				"\n\t|name: "+name+
				"\n\t|desc: "+descripcion+
				"\n\t|price: "+price+
				"\n\t|stock: "+stock+
				"\n\t|Incluye envio: "+((shippingIncluded)?"SI":"NO")+
				"\n\t|Fecha de deshabilitacion: "+printDisableOn()+"\n";	
	}
	
	
}
