package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Surtir {


	@JsonProperty(value="idProducto")
	private Long idProducto;
	
	@JsonProperty(value="idRestaurante")
	private Long idRestaurante;
	
	@JsonProperty(value="maximoRest")
	private int maximoRest;
	


	public Surtir(@JsonProperty(value="idProducto")Long idProducto,@JsonProperty(value="idRestaurante")Long idRestaurante,@JsonProperty(value="maximoRest") int maximoRest) 
	{
		this.idProducto=idProducto;
		this.idRestaurante=idRestaurante;
		this.maximoRest=maximoRest;
	}



	public Long getIdProducto() {
		return idProducto;
	}



	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	


	public Long getIdRestaurante() {
		return idRestaurante;
	}



	public void setIdRestaurante(Long idRestaurante) {
		this.idRestaurante = idRestaurante;
	}



	public int getMaximoRest() {
		return maximoRest;
	}



	public void setMaximoRest(int maximoRest) {
		this.maximoRest = maximoRest;
	}
	
	
}
