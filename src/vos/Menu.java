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



	public Restaurante getrestaurante() {
		return restaurante;
	}



	public void setrestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}



	public List<Producto> getProductos() {
		return productos;
	}



	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public Long getIdRestaruarnte()
	{
		if(restaurante!=null)
		{
		return this.restaurante.getId();
		}
		return null;
	}
	
	public Long getIdPedido()
	{
		if(pedido!=null)
		{
		return pedido.getId();
		}
		return null;
	}
	
	
}
