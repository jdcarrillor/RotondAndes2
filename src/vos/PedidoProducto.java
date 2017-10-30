package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class PedidoProducto {
	

	@JsonProperty(value="id_producto")
	private Long idProducto;
	
	@JsonProperty(value="id_pedido")
	private Long idPedido;



	public PedidoProducto(@JsonProperty(value="id_producto")Long idProducto, @JsonProperty(value="id_pedido")Long idPedido) {
		super();
		this.idProducto=idProducto;
		this.idPedido=idPedido;
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
