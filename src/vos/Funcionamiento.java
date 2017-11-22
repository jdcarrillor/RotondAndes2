package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Funcionamiento 
{
	

	
	@JsonProperty(value="dia")
	private String dia;
	
	@JsonProperty(value="producto_mas_consumido")
	private Long producto_mas_consumido;
	
	@JsonProperty(value="producto_menos_consumido")
	private Long producto_menos_consumido;
	
	@JsonProperty(value="restaurante_mas_frecuentado")
	private Long restaurante_mas_frecuentado;
	
	@JsonProperty(value="restaurante_menos_frecuentado")
	private Long restaurante_menos_frecuentado;
	
	

	public Funcionamiento( @JsonProperty(value="dia") String dia,  @JsonProperty(value="producto_mas_consumido") Long producto_mas_consumido,@JsonProperty(value="producto_menos_consumido") Long producto_menos_consumido,@JsonProperty(value="restaurante_mas_frecuentado") Long restaurante_mas_frecuentado,@JsonProperty(value="restaurante_menos_frecuentado") Long restaurante_menos_frecuentado) {
		this.dia=dia;
		this.producto_mas_consumido=producto_mas_consumido;
		this.producto_menos_consumido=producto_menos_consumido;
		this.restaurante_mas_frecuentado=restaurante_mas_frecuentado;
		this.restaurante_menos_frecuentado=restaurante_menos_frecuentado;
	}



	public String getDia() {
		return dia;
	}



	public void setDia(String dia) {
		this.dia = dia;
	}



	public Long getProducto_mas_consumido() {
		return producto_mas_consumido;
	}



	public void setProducto_mas_consumido(Long producto_mas_consumido) {
		this.producto_mas_consumido = producto_mas_consumido;
	}



	public Long getProducto_menos_consumido() {
		return producto_menos_consumido;
	}



	public void setProducto_menos_consumido(Long producto_menos_consumido) {
		this.producto_menos_consumido = producto_menos_consumido;
	}



	public Long getRestaurante_mas_frecuentado() {
		return restaurante_mas_frecuentado;
	}



	public void setRestaurante_mas_frecuentado(Long restaurante_mas_frecuentado) {
		this.restaurante_mas_frecuentado = restaurante_mas_frecuentado;
	}



	public Long getRestaurante_menos_frecuentado() {
		return restaurante_menos_frecuentado;
	}



	public void setRestaurante_menos_frecuentado(Long restaurante_menos_frecuentado) {
		this.restaurante_menos_frecuentado = restaurante_menos_frecuentado;
	}

}
