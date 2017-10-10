package vos;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Pedido 
{
	@JsonProperty(value="id")
	private Long id;
	
	@JsonProperty(value="fecha")
	private Date fecha;
	
	@JsonProperty(value="idUsuario")
	private Long idUsuario;
	





	public Pedido(@JsonProperty(value="id")Long id, @JsonProperty(value="fecha")Date fecha, @JsonProperty(value="idUsuario")Long idUsuario) {
		super();
		this.id=id;
		this.fecha=fecha;
		this.idUsuario =idUsuario;
	}
		
		
	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Date getfecha() {
		return fecha;
	}



	public void setfecha(Date fecha) {
		this.fecha = fecha;
	}


	
	
	

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) 
	{
		this.idUsuario = idUsuario;

	}



	
}
