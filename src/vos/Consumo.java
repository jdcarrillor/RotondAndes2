package vos;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class Consumo {
	
	@JsonProperty(value = "correo")
	private String correo;
	
	@JsonProperty(value="producto")
	private String producto;
	
	@JsonProperty(value="tipo")
	private String tipo;
	
	@JsonProperty(value="restaurante")
	private String restaurante;
	
	@JsonProperty(value="fechaIn")
	private String fechaIn;
	
	@JsonProperty(value="fechaFin")
	private String fechaFin;
	
	public Consumo(@JsonProperty(value="correo")String correo, @JsonProperty(value="producto")String producto, @JsonProperty(value="tipo")String tipo, @JsonProperty(value="restaurante")String restaurante, @JsonProperty(value="fechaIn")String fechaIn, @JsonProperty(value="fechaFin")String fechaFin){
		super();
		this.correo = correo;
		this.producto = producto;
		this.tipo = tipo;
		this.restaurante = restaurante;
		this.fechaIn = fechaIn;
		this.fechaFin = fechaFin;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(String restaurante) {
		this.restaurante = restaurante;
	}

	public String getFechaIn() {
		return fechaIn;
	}

	public void setFechaIn(String fechaIn) {
		this.fechaIn = fechaIn;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	
	

}
