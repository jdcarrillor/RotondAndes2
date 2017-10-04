package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Producto 
{
	@JsonProperty(value="id")
	private Long id;
	
	@JsonProperty(value="nombreProducto")
	private String nombreProducto;
	
	@JsonProperty(value="costoProduccion")
	private double costoProduccion;
	
	@JsonProperty(value="precio")
	private double precio;
	
	@JsonProperty(value="descripcion")
	private String descripcion;
	
	@JsonProperty(value="traduccion")
	private String traduccion;
	
	@JsonProperty(value="tiempo")
	private double tiempo;
	
	@JsonProperty(value="categoria")
	private String categoria;
	
	@JsonProperty(value="disponibles")
	private int disponibles;
	
	@JsonProperty(value="menu")
	private Long menu;
	

	
	
	
	
	
	
	
	
	//Peniente MenuDefinido




	public Producto(@JsonProperty(value="id")Long id, @JsonProperty(value="nombre")String nombreProducto,@JsonProperty(value="costoProduccion")double costoProduccion,@JsonProperty(value="precio")double precio,@JsonProperty(value="tiempo")double tiempo,@JsonProperty(value="descripcion")String descripcion,@JsonProperty(value="traduccion")String traduccion,@JsonProperty(value="categoria")String categoria,@JsonProperty(value="disponibles")int disponibles, @JsonProperty(value="id_menu")Long menu) {
		super();
		this.id=id;
		this.nombreProducto=nombreProducto;
		this.costoProduccion=costoProduccion;
		this.precio=precio;
		this.descripcion = descripcion;
		this.traduccion = traduccion;
		this.tiempo = tiempo;
		this.categoria = categoria;
		this.disponibles = disponibles;
		this.menu = menu;
		
		
	}

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNombreProducto() {
		return nombreProducto;
	}



	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}



	public double getCostoProduccion() {
		return costoProduccion;
	}



	public void setCostoProduccion(double costoProduccion) {
		this.costoProduccion = costoProduccion;
	}



	public double getPrecio() {
		return precio;
	}



	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTraduccion() {
		return traduccion;
	}

	public void setTraduccion(String traduccion) {
		this.traduccion = traduccion;
	}

	public double getTiempo() {
		return tiempo;
	}

	public void setTiempo(double tiempo) {
		this.tiempo = tiempo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getDisponibles() {
		return disponibles;
	}

	public void setDisponibles(int disponibles) {
		this.disponibles = disponibles;
	}

	public Long getMenu() {
		return menu;
	}

	public void setMenu(Long menu) {
		this.menu = menu;
	}



	


}
