package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class PedidoRestaurante
{
	@JsonProperty(value="fecha")
	private String fecha;
	
	@JsonProperty(value="idRestaurante")
	private Long idRestaurante;
	
	@JsonProperty(value="numRepetidas")
	private int numRepetidas;
	
	public PedidoRestaurante(@JsonProperty(value="fecha") String fecha, @JsonProperty(value="idRestaurante") Long idRestaurante) 
	{
		this.fecha=fecha;
		this.idRestaurante=idRestaurante;
		
		// TODO Auto-generated constructor stub
	}

	public PedidoRestaurante( @JsonProperty(value="idRestaurante") Long idRestaurante, @JsonProperty(value="numeroRepetidas") int numeroRepetidas) 
	{
		this.idRestaurante=idRestaurante;
		this.numRepetidas=numeroRepetidas;
		// TODO Auto-generated constructor stub
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Long getIdRestaurante() {
		return idRestaurante;
	}

	public void setIdRestaurante(Long idRestaurante) {
		this.idRestaurante = idRestaurante;
	}

	public int getNumRepetidas() {
		return numRepetidas;
	}

	public void setNumRepetidas(int numRepetidas) {
		this.numRepetidas = numRepetidas;
	}

}
