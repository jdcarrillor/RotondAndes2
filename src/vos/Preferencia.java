package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Preferencia 
{
	
	@JsonProperty(value="id")
	private Long id;
	
	@JsonProperty(value="categoria")
	private String categoria;
	
	@JsonProperty(value="precio")
	private double precio;
	
	@JsonProperty(value="zona")
	private String zona;
	
	@JsonProperty(value="id_usuariocliente")
	private Long id_usuariocliente;

	public Preferencia(@JsonProperty(value="id")Long id,@JsonProperty(value="precio")double precio,@JsonProperty(value="categorria")String categoria,@JsonProperty(value="zona")String zona,@JsonProperty(value="id_usuariocliente")Long id_usuariocliente) {
		super();
		this.id=id;
		this.precio=precio;
		this.categoria=categoria;
		this.zona=zona;
		this.id_usuariocliente=id_usuariocliente;

		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public Long getId_usuariocliente() {
		return id_usuariocliente;
	}

	public void setId_usuariocliente(Long id_usuariocliente) {
		this.id_usuariocliente = id_usuariocliente;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

}
