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


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getRol() {
		return rol;
	}


	public void setRol(String rol) {
		this.rol = rol;
	}


	public Pedido getPedido() {
		return pedido;
	}


	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}


	public RotondAndes getRotondAndes() {
		return rotondAndes;
	}


	public void setRotondAndes(RotondAndes rotondAndes) {
		this.rotondAndes = rotondAndes;
	}
	
	
}
