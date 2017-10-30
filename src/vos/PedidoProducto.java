package vos;

import org.codehaus.jackson.annotate.JsonProperty;

<<<<<<< HEAD
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


=======
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
>>>>>>> d4994593116cea66631c0950e0a94a33cbe30aa0

	public Long getIdProducto() {
		return idProducto;
	}

<<<<<<< HEAD


=======
>>>>>>> d4994593116cea66631c0950e0a94a33cbe30aa0
	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

<<<<<<< HEAD


=======
>>>>>>> d4994593116cea66631c0950e0a94a33cbe30aa0
	public Long getIdPedido() {
		return idPedido;
	}

<<<<<<< HEAD


=======
>>>>>>> d4994593116cea66631c0950e0a94a33cbe30aa0
	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}
	
	
<<<<<<< HEAD
=======
	
>>>>>>> d4994593116cea66631c0950e0a94a33cbe30aa0

}
