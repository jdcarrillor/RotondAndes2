package vos;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class Evento
{
	@JsonProperty(value="id")
	private Long id;
	
	@JsonProperty(value="fecha")
	private String fecha;
	
	@JsonProperty(value="num_comensales")
	private int num_comensales;
	
	@JsonProperty(value="id_zona")
	private Long id_zona;
	
	@JsonProperty(value="id_usuarioCliente")
	private Long id_usuarioCliente;
	
	
	public Evento(@JsonProperty(value="id")Long id, @JsonProperty(value="fecha")String fecha,@JsonProperty(value="num_comensales")int num_comensales,@JsonProperty(value="id_zona")Long id_zona,@JsonProperty(value="id_usuarioCliente")Long id_usuarioCliente) 
	{
		super();
		this.id=id;
		this.fecha=fecha;
		this.num_comensales=num_comensales;
		this.id_zona=id_zona;
		this.id_usuarioCliente=id_usuarioCliente;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public int getNum_comensales() {
		return num_comensales;
	}


	public void setNum_comensales(int num_comensales) {
		this.num_comensales = num_comensales;
	}


	public Long getId_zona() {
		return id_zona;
	}


	public void setId_zona(Long id_zona) {
		this.id_zona = id_zona;
	}


	public Long getId_usuarioCliente() {
		return id_usuarioCliente;
	}


	public void setId_usuarioCliente(Long id_usuarioCliente) {
		this.id_usuarioCliente = id_usuarioCliente;
	}
	
	
}
