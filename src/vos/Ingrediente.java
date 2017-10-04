package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Ingrediente 
{


	@JsonProperty(value="id")
	private Long id;
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="descripcion")
	private String descripcion;
	
	@JsonProperty(value="traduccion")
	private String traduccion;
	
	@JsonProperty(value="equivalentes")
	private List<Ingrediente> equivalentes;
	
	@JsonProperty(value="productos")
	private List<Producto> productos;



	public Ingrediente(@JsonProperty(value="id")Long id, @JsonProperty(value="nombre")String nombre,@JsonProperty(value="descripcion")String descripcion,@JsonProperty(value="traduccion")String traduccion,@JsonProperty(value="equivalentes")List<Ingrediente> equivalentes,@JsonProperty(value="productos")List<Producto>productos) {
		super();
		this.id=id;
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.traduccion=traduccion;
		this.equivalentes=equivalentes;
		this.productos=productos;
		
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



	private String getDescripcion() {
		return descripcion;
	}



	private void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	private String getTraduccion() {
		return traduccion;
	}



	private void setTraduccion(String traduccion) {
		this.traduccion = traduccion;
	}



	private List<Ingrediente> getEquivalentes() {
		return equivalentes;
	}



	private void setEquivalentes(List<Ingrediente> equivalentes) {
		this.equivalentes = equivalentes;
	}



	private List<Producto> getProductos() {
		return productos;
	}



	private void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
}

	



	
