package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class PedidoMesa 
{
	@JsonProperty(value="id")
	private Long id;
	
	@JsonProperty(value="idMesa")
	private Long idMesa;
	
	@JsonProperty(value="idProducto")
	private Long idProducto;
	
	@JsonProperty(value="idMenu")
	private Long idMenu;
	
	@JsonProperty(value="idCliente")
	private Long idCliente;
	
	

	public PedidoMesa(@JsonProperty(value="id")Long id,@JsonProperty(value="idMesa")Long idMesa,@JsonProperty(value="idProducto")Long idProducto ,@JsonProperty(value="idMenu")Long idMenu,@JsonProperty(value="idCliente")Long idCliente) 
	{
		this.id=id;
		this.idMesa = idMesa;
		this.idProducto=idProducto;
		this.idMenu=idMenu;
		this.idCliente=idCliente;

		
		// TODO Auto-generated constructor stub
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Long getIdMesa() {
		return idMesa;
	}



	public void setIdMesa(Long idMesa) {
		this.idMesa = idMesa;
	}



	public Long getIdProducto() {
		return idProducto;
	}



	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}



	public Long getIdMenu() {
		return idMenu;
	}



	public void setIdMenu(Long idMenu) {
		this.idMenu = idMenu;
	}



	public Long getIdCliente() {
		return idCliente;
	}



	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}





}
