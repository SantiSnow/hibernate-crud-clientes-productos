package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="cliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id", unique=true, nullable = false)
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
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="Id", nullable=false)
	private DetallesCliente detallesCliente;
	
	@OneToMany(mappedBy = "cliente", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH} )
	private List<Pedido> pedidos;
	
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
	public DetallesCliente getDetallesCliente() {
		return detallesCliente;
	}
	public void setDetallesCliente(DetallesCliente detallesCliente) {
		this.detallesCliente = detallesCliente;
	}
	public List<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	//toString
	@Override
	public String toString() {
		return "Cliente: Id=" + Id + "\nNombre=" + Nombre + ", Apellido=" + Apellido + "\nDireccion=" + Direccion
				+ "\nTelefono=" + Telefono + "\nCompras=" + Compras + ".";
	}
	
	//metodos
	public static void recorrerLista(List<Cliente> lista) {
		for(Cliente i: lista) {
			System.out.println(i);
		}
	}
	
	public Boolean agregarPedido(Pedido ped) {
		
		if(pedidos == null) {
			pedidos = new ArrayList<Pedido>();
			
			pedidos.add(ped);
			
			ped.setCliente(this);
		}
		else {
			pedidos.add(ped);
			ped.setCliente(this);
		}
		
		return true;
	}
	
}
