package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Tipo {

	@JsonProperty(value="id")
	private Long id;
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="productos")
	private List<Producto> productos;
	
	
	public Tipo(@JsonProperty(value="id")Long id, @JsonProperty(value="nombre")String nombre,@JsonProperty(value="productos")List<Producto> productos)
	{
		super();
		this.id=(id);
		this.nombre=nombre;
		this.productos=productos;
		
	
		
		
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


	public List<Producto> getProductos() {
		return productos;
	}


	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	
	
}
