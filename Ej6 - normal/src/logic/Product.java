package logic;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;

import Dao.DirectDataBaseMapping;
import Dao.identifiable;
import ui.Global;


public class Product implements identifiable<Integer>{
	
	@DirectDataBaseMapping Integer id;
	@DirectDataBaseMapping private String name;
	@DirectDataBaseMapping private String descripcion;
	@DirectDataBaseMapping private Double price;
	@DirectDataBaseMapping private Integer stock;
	@DirectDataBaseMapping private Boolean shippingIncluded;
	
	private LocalDate dateDisableOn;
	private LocalTime timeDisableOn;
	private ZonedDateTime dateTimeDisabelOn;

	
	public void setDateTimeDisabelOn(ZonedDateTime zonedDateTime) {
		if(zonedDateTime==null) return; 
		
		this.dateTimeDisabelOn=zonedDateTime;
		this.dateDisableOn=zonedDateTime.withZoneSameInstant(Global.usoHorarioCliente).toLocalDate();
		this.timeDisableOn=zonedDateTime.withZoneSameInstant(Global.usoHorarioCliente).toLocalTime();
	}
	
	
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
	
	
	public LocalDate getDateDisableOn() {
		return dateDisableOn;
	}


	public LocalTime getTimeDisableOn() {
		return timeDisableOn;
	}


	public ZonedDateTime getDateTimeDisabelOn() {
		return dateTimeDisabelOn;
	}


	
	
	public String printDateDisableOn(){
		if(dateDisableOn==null) { return " - "; }
		return dateDisableOn.format(Global.dateFormatter);
	}
	
	public String printTimeDisableOn(){
		if(timeDisableOn==null) { return " - "; }
		return timeDisableOn.format(Global.timeFormatter);
	}
	
	public Boolean isDisable() {
		return (dateTimeDisabelOn!=null);
	}
	
	@Override
	public String toString() {
		return 	"\t|id: "+id+
				"\n\t|name: "+name+
				"\n\t|desc: "+descripcion+
				"\n\t|price: "+price+
				"\n\t|stock: "+stock+
				"\n\t|Incluye envio: "+((shippingIncluded)?"SI":"NO")+
				"\n\t|Fecha de deshabilitacion: "+printDateDisableOn()+
				"\n\t|Hora de deshabilitacion: "+printTimeDisableOn()+"\n"
						+ "fecha y hora: "+dateTimeDisabelOn;	
		
	}


	@Override
	public Integer id() { return id; }


	@Override
	public String idFieldName() { return "id"; }
	
	
}
