package src;

import javax.persistence.*;

@Entity
@Table(name="detalles_cliente")
public class DetallesCliente {
	
	//campos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private Integer Id;
	
	@Column(name="Correo")
	private String Correo;
	
	@Column(name="Comentario")
	private String Comentario;
	
	@OneToOne(mappedBy = "detallesCliente", cascade = CascadeType.ALL)
	private Cliente cliente;
	
	//constructor
	public DetallesCliente() {
		
	}
	public DetallesCliente(String correo, String comentario) {
		Correo = correo;
		Comentario = comentario;
	}

	//metodos
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getCorreo() {
		return Correo;
	}
	public void setCorreo(String correo) {
		Correo = correo;
	}
	public String getComentarios() {
		return Comentario;
	}
	public void setComentarios(String comentarios) {
		Comentario = comentarios;
	}

	
	
}
