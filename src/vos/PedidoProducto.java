package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class PedidoProducto 
{
	@JsonProperty(value="idProducto")
	private Long idProducto;
	
	@JsonProperty(value="idPedido")
	private Long idPedido;
	
	@JsonProperty(value="numeroRepetidas")
	private int numeroRepetidas;

	public PedidoProducto(@JsonProperty(value="idProducto") Long idProducto, @JsonProperty(value="idPedido") Long idPedido) {
		// TODO Auto-generated constructor stub
		
		this.idProducto = idProducto;
		
		this.idPedido = idPedido;
	}
	
	public PedidoProducto(@JsonProperty(value="idProducto") Long idProducto, @JsonProperty(value="numeroRepetidas") int numeroRepetidas) {
		// TODO Auto-generated constructor stub
		
		this.idProducto = idProducto;
		
		this.numeroRepetidas = numeroRepetidas;
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}
	
	
	

}
