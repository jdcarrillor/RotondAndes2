package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Mesa 
{

	@JsonProperty(value="id")
	private Long id;
	
	@JsonProperty(value="idPedidoMesa")
	private Long idPedidoMesa;
	
	

	public Mesa(@JsonProperty(value="id")Long id,@JsonProperty(value="idPedidoMesa")Long idPedidoMesa) 
	{
		this.id=id;
		this.idPedidoMesa=idPedidoMesa;
		
		// TODO Auto-generated constructor stub
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Long getIdPedidoMesa() {
		return idPedidoMesa;
	}



	public void setIdPedidoMesa(Long idPedidoMesa) {
		this.idPedidoMesa = idPedidoMesa;
	}

}
