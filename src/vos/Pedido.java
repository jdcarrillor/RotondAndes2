package vos;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Pedido 
{
	@JsonProperty(value="id")
	private Long id;
	
	
	@JsonProperty(value="fecha")
	private String fecha;
	
	@JsonProperty(value="idUsuario")
	private Long idUsuario;
	
	@JsonProperty(value="idProducto")
	private Long idProducto;
	
	@JsonProperty(value="idMenu")
	private Long idMenu;
	
	@JsonProperty(value="numeroRepetidas")
	private int numeroRepetidas;
	





	public Pedido(@JsonProperty(value="id")Long id, @JsonProperty(value="fecha")String fecha, @JsonProperty(value="idUsuario")Long idUsuario,  @JsonProperty(value="idProducto")Long idProducto,  @JsonProperty(value="idMenu")Long idMenu) {
		super();
		this.id=id;
		this.fecha=fecha;
		this.idUsuario =idUsuario;
		this.idProducto=idProducto;
		this.idMenu= idMenu;
	}
		
	public Pedido( @JsonProperty(value="idProducto")Long idProducto,  @JsonProperty(value="numeroRepetidas") int numeroRepetidas) {
		super();
		
		this.idProducto=idProducto;
		this.numeroRepetidas= numeroRepetidas;
	}
		
	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getfecha() {
		return fecha;
	}



	public void setfecha(String fecha) {
		this.fecha = fecha;
	}


	
	
	

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) 
	{
		this.idUsuario = idUsuario;

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



	
}
