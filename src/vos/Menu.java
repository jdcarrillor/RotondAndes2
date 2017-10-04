package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Menu 


{

	@JsonProperty(value="id")
	private Long id;
	
	@JsonProperty(value="costo")
	private double costo;
	
	@JsonProperty(value="precio")
	private double precio;
	
	@JsonProperty(value="restaurante")
	private Restaurante restaurante;
	
	@JsonProperty(value="productos")
	private List<Producto> productos;
	
	@JsonProperty(value="pedido")
	private Pedido pedido;
	
	//Peniente MenuDefinido




	public Menu(@JsonProperty(value="id")Long id,@JsonProperty(value="costo")double costo,@JsonProperty(value="precio")double precio,@JsonProperty(value="restaurante")Restaurante restaurante,@JsonProperty(value="pedido")Pedido pedido,@JsonProperty(value="productos")List<Producto>productos) {
		super();
		this.id=id;
		this.costo=costo;
		this.precio=precio;
		this.restaurante=restaurante;
		this.productos=productos;
		this.pedido=pedido;
		
		
	}

	private Long getId() {
		return id;
	}



	private void setId(Long id) {
		this.id = id;
	}




	private double getcosto() {
		return costo;
	}



	private void setcosto(double costo) {
		this.costo = costo;
	}



	private double getprecio() {
		return precio;
	}



	private void setprecio(double precio) {
		this.precio = precio;
	}



	private Restaurante getrestaurante() {
		return restaurante;
	}



	private void setrestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}



	private List<Producto> getProductos() {
		return productos;
	}



	private void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	private Pedido getPedido() {
		return pedido;
	}

	private void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
}
