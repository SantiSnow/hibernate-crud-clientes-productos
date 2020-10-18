package model;

import javax.persistence.*;

@Entity
@Table(name="producto")
public class Producto {

	//campos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id", unique=true, nullable = false)
	private Integer Id;
	
	@Column(name="Nombre")
	private String Nombre;
	
	@Column(name="Precio")
	private Integer Precio;
	
	@Column(name="Stock")
	private Integer Stock;
	
	@Column(name="Detalle")
	private String Detalle;
	
	@Column(name="Seccion")
	private String Seccion;

	//constructores
	public Producto(String nombre, Integer precio, Integer stock, String detalle, String seccion) {
		Nombre = nombre;
		Precio = precio;
		Stock = stock;
		Detalle = detalle;
		Seccion = seccion;
	}
	public Producto() {
		
	}
	
	//Getters y Setters
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public Integer getPrecio() {
		return Precio;
	}
	public void setPrecio(Integer precio) {
		Precio = precio;
	}
	public Integer getStock() {
		return Stock;
	}
	public void setStock(Integer stock) {
		Stock = stock;
	}
	public String getDetalle() {
		return Detalle;
	}
	public void setDetalle(String detalle) {
		Detalle = detalle;
	}
	public String getSeccion() {
		return Seccion;
	}
	public void setSeccion(String seccion) {
		Seccion = seccion;
	}
	
	//to string
	@Override
	public String toString() {
		return "Producto con Id=" + Id + ", Nombre=" + Nombre + ", Precio=" + Precio + ", Stock=" + Stock + ", Detalle="
				+ Detalle + ", Seccion=" + Seccion + ".";
	}
	
}
