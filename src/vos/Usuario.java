package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Usuario {

	@JsonProperty(value="id")
	private Long id;
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="rol")
	private String rol;
	
	@JsonProperty(value="pedido")
	private Pedido pedido;
	
	@JsonProperty(value="rotondAndes")
	private RotondAndes rotondAndes;
	
	
	public Usuario(@JsonProperty(value="id")Long id, @JsonProperty(value="nombre")String nombre,@JsonProperty(value="rol")String rol,@JsonProperty(value="pedido")Pedido pedido,@JsonProperty(value="rotondAndes")RotondAndes rotondAndes)
	{
		super();
		this.id=(id);
		this.nombre=nombre;
		this.rol=rol;
		this.pedido=pedido;
		this.rotondAndes=rotondAndes;
	
	}


	private Long getId() {
		return id;
	}


	private void setId(Long id) {
		this.id = id;
	}


	private String getNombre() {
		return nombre;
	}


	private void setNombre(String nombre) {
		this.nombre = nombre;
	}


	private String getRol() {
		return rol;
	}


	private void setRol(String rol) {
		this.rol = rol;
	}


	private Pedido getPedido() {
		return pedido;
	}


	private void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}


	private RotondAndes getRotondAndes() {
		return rotondAndes;
	}


	private void setRotondAndes(RotondAndes rotondAndes) {
		this.rotondAndes = rotondAndes;
	}
	
	
}
