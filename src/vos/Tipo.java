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


	private List<Producto> getProductos() {
		return productos;
	}


	private void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	
	
}
