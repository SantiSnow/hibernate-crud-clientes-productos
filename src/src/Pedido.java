package src;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="pedido")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id", unique=true, nullable = false)
	private Integer Id;
	
	@Column(name="Fecha")
	private Date Fecha;
	
	@Column(name="FormaPago")
	private String FormaPago;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH} )
	@JoinColumn(name = "CLIENTE_Id")
	private Cliente cliente;

	public Pedido() {	}
	
	public Pedido(Date fecha, String formaPago, Cliente cliente) {
		super();
		Fecha = fecha;
		FormaPago = formaPago;
		this.cliente = cliente;
	}
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public Date getFecha() {
		return Fecha;
	}
	public void setFecha(Date fecha) {
		Fecha = fecha;
	}
	public String getFormaPago() {
		return FormaPago;
	}
	public void setFormaPago(String formaPago) {
		FormaPago = formaPago;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Pedido: Id=" + Id + "\nFecha=" + Fecha + "\nFormaPago=" + FormaPago + ".";
	}
	
	
}
