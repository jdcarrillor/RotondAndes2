package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Surtir {


	@JsonProperty(value="idProducto")
	private Long idProducto;
	
	@JsonProperty(value="maximoRest")
	private int maximoRest;
	


	public Surtir(@JsonProperty(value="idProducto")Long idProducto,@JsonProperty(value="idPedidoMesa") int maximoRest) 
	{
		this.idProducto=idProducto;
		this.maximoRest=maximoRest;
	}



	public Long getIdProducto() {
		return idProducto;
	}



	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}



	public int getMaximoRest() {
		return maximoRest;
	}



	public void setMaximoRest(int maximoRest) {
		this.maximoRest = maximoRest;
	}
	
	
}
