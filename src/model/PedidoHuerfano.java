package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PedidoHuerfano")
public class PedidoHuerfano {
	//campos de clase
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id", unique=true, nullable = false)
	private Integer Id;
	
	@Column(name="Fecha")
	private Date Fecha;
	
	@Column(name="FormaPago")
	private String FormaPago;
	//constructor
	public PedidoHuerfano(Date fecha, String formaPago) {
		Fecha = fecha;
		FormaPago = formaPago;
	}
	public PedidoHuerfano() {
		
	}
	//getters, setters y tostring
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
	@Override
	public String toString() {
		return "PedidoHuerfano [Id=" + Id + ", Fecha=" + Fecha + ", FormaPago=" + FormaPago + "]";
	}
	
}
