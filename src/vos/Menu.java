	package vos;

import java.sql.SQLException;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import dao.DAOTablaRestaurante;

public class Menu 


{
	
	

	@JsonProperty(value="id")
	private Long id;
	
	@JsonProperty(value="costo")
	private double costo;
	
	@JsonProperty(value="precio")
	private double precio;
	
	@JsonProperty(value="id_restaurante")
	private Long id_restaurante;
	
	@JsonProperty(value="id_pedido")
	private Long id_pedido;
	
	
	
	//Peniente MenuDefinido




	public Menu(@JsonProperty(value="id")Long id,@JsonProperty(value="costo")double costo,@JsonProperty(value="precio")double precio,@JsonProperty(value="id_restaurante")Long id_restaurante,@JsonProperty(value="id_pedido")Long id_pedido) {
		super();
		this.id=id;
		this.costo=costo;
		this.precio=precio;
		this.id_restaurante=id_restaurante;
		this.id_pedido=id_pedido;
		
		
	}

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}




	public double getcosto() {
		return costo;
	}



	public void setcosto(double costo) {
		this.costo = costo;
	}



	public double getprecio() {
		return precio;
	}



	public void setprecio(double precio) {
		this.precio = precio;
	}

	public Long getId_restaurante() {
		return id_restaurante;
	}

	public void setId_restaurante(Long id_restaurante) {
		this.id_restaurante = id_restaurante;
	}

	public Long getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(Long id_pedido) {
		this.id_pedido = id_pedido;
	}



	
	
}
