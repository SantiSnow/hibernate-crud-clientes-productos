package src;

import javax.persistence.*;

@Entity
@Table(name="clientes")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private Integer Id;
	
	@Column(name="Nombre")
	private String Nombre;
	
	@Column(name="Apellido")
	private String Apellido;
	
	@Column(name="Direccion")
	private String Direccion;
	
	@Column(name="Telefono")
	private Integer Telefono;
	
	@Column(name="Compras")
	private Integer Compras;
	
	//constructores
	public Cliente() {
	}
	public Cliente(String nombre, String apellido, String direccion, Integer telefono, Integer compras) {
		Nombre = nombre;
		Apellido = apellido;
		Direccion = direccion;
		Telefono = telefono;
		Compras = compras;
	}
	
	//getters y setter
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
	public String getApellido() {
		return Apellido;
	}
	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	public String getDireccion() {
		return Direccion;
	}
	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
	public Integer getTelefono() {
		return Telefono;
	}
	public void setTelefono(Integer telefono) {
		Telefono = telefono;
	}
	public Integer getCompras() {
		return Compras;
	}
	public void setCompras(Integer compras) {
		Compras = compras;
	}
	//toString
	@Override
	public String toString() {
		return "Cliente: Id=" + Id + ", Nombre=" + Nombre + ", Apellido=" + Apellido + ", Direccion=" + Direccion
				+ ", Telefono=" + Telefono + ", Compras=" + Compras + ".";
	}
	
}
