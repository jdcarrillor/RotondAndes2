package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ProductosEquivalentes 
{
	@JsonProperty(value="idProducto1")
	private Long idProducto1;
	@JsonProperty(value="idProducto2")
	private Long idProducto2;

	public ProductosEquivalentes(@JsonProperty(value="idProducto1")Long idProducto1, @JsonProperty(value="idProducto2")Long idProducto2) 
	{
		this.idProducto1 = idProducto1;
		this.idProducto2 = idProducto2;
		// TODO Auto-generated constructor stub
	}

	public Long getIdProducto1() {
		return idProducto1;
	}

	public void setIdProducto1(Long idProducto1) {
		this.idProducto1 = idProducto1;
	}

	public Long getIdProducto2() {
		return idProducto2;
	}

	public void setIdProducto2(Long idProducto2) {
		this.idProducto2 = idProducto2;
	}
	
	
	

}
