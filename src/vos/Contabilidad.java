package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Contabilidad {

	@JsonProperty(value="id")
	private Long id;
	
	@JsonProperty(value="idRestaurante")
	private Long idRestaurante;
	
	@JsonProperty(value="idProducto")
	private Long idProducto;
	
	@JsonProperty(value="idMenu")
	private Long idMenu;
	
	@JsonProperty(value="idCliente")
	private Long idCliente;

	@JsonProperty(value="idUsuario")
	private Long idUsuario;
	
	@JsonProperty(value="unidadesVendidas")
	private int unidadesVendidas;
	
	@JsonProperty(value="ingresosGenerados")
	private int ingresosGenerados;
	

	
	
	public Contabilidad(@JsonProperty(value="id")Long id, @JsonProperty(value="idRestaurante")Long idRestaurante,@JsonProperty(value="idProducto")Long idProducto,@JsonProperty(value="idMenu")Long idMenu,@JsonProperty(value="idCliente")Long idCliente,@JsonProperty(value="idUsuario")Long idUsuario,@JsonProperty(value="unidadesVendidas")int unidadesVendidas,@JsonProperty(value="ingresosGenerados")int ingresosGenerados) 
	{
		super();
		this.id=id;
		this.idRestaurante=idRestaurante;
		this.idProducto=idProducto;
		this.idMenu=idMenu;
		this.idCliente=idCliente;
		this.idUsuario=idUsuario;
		this.unidadesVendidas=unidadesVendidas;
		this.ingresosGenerados=ingresosGenerados;
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public Long getIdRestaurante() {
		return idRestaurante;
	}




	public void setIdRestaurante(Long idRestaurante) {
		this.idRestaurante = idRestaurante;
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




	public Long getIdUsuario() {
		return idUsuario;
	}




	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}




	public int getUnidadesVendidas() {
		return unidadesVendidas;
	}




	public void setUnidadesVendidas(int unidadesVendidas) {
		this.unidadesVendidas = unidadesVendidas;
	}




	public int getIngresosGenerados() {
		return ingresosGenerados;
	}




	public void setIngresosGenerados(int ingresosGenerados) {
		this.ingresosGenerados = ingresosGenerados;
	}
	
	


}
