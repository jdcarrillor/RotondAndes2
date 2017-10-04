package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Producto 
{
	@JsonProperty(value="id")
	private Long id;
	
	@JsonProperty(value="nombreProducto")
	private String nombreProducto;
	
	@JsonProperty(value="costoProduccion")
	private double costoProduccion;
	
	@JsonProperty(value="precio")
	private double precio;
	
	@JsonProperty(value="descripcion")
	private String descripcion;
	
	@JsonProperty(value="pedidos")
	private List<Pedido> pedidos;
	
	@JsonProperty(value="traduccion")
	private String traduccion;
	
	@JsonProperty(value="tiempoPreparacion")
	private double tiempoPreparacion;
	
	@JsonProperty(value="categoria")
	private String categoria;
	
	@JsonProperty(value="disponibles")
	private int disponibles;
	
	@JsonProperty(value="equivalentes")
	private List<Producto> equivalentes;
	
	@JsonProperty(value="tipo")
	private Tipo tipo;
	
	@JsonProperty(value="ingredientes")
	private List<Ingrediente> ingredientes;
	
	@JsonProperty(value="menu")
	private Menu menu;
	

	
	
	
	
	
	
	
	
	//Peniente MenuDefinido




	public Producto(@JsonProperty(value="id")Long id, @JsonProperty(value="nombreProducto")String nombreProducto,@JsonProperty(value="costoProduccion")double costoProduccion,@JsonProperty(value="precio")double precio,@JsonProperty(value="tiempoPreparacion")double tiempoPreparacion,@JsonProperty(value="descripcion")String descripcion,@JsonProperty(value="pedidos")List<Pedido>pedidos,@JsonProperty(value="traduccion")String traduccion,@JsonProperty(value="categoria")String categoria,@JsonProperty(value="disponibles")int disponibles,@JsonProperty(value="equivalentes")List<Producto>equivalentes) {
		super();
		this.id=id;
		this.nombreProducto=nombreProducto;
		this.costoProduccion=costoProduccion;
		this.precio=precio;
		this.setDescripcion(descripcion);
		this.setPedidos(pedidos);
		this.setTraduccion(traduccion);
		this.setTiempoPreparacion(tiempoPreparacion);
		this.setCategoria(categoria);
		this.setDisponibles(disponibles);
		this.setEquivalentes(equivalentes);
		
		
	}

	private Long getId() {
		return id;
	}



	private void setId(Long id) {
		this.id = id;
	}



	private String getnombreProducto() {
		return nombreProducto;
	}



	private void setnombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}



	private double getcostoProduccion() {
		return costoProduccion;
	}



	private void setcostoProduccion(double costoProduccion) {
		this.costoProduccion = costoProduccion;
	}



	private double getprecio() {
		return precio;
	}



	private void setprecio(double precio) {
		this.precio = precio;
	}

	private String getDescripcion() {
		return descripcion;
	}

	private void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	private List<Pedido> getPedidos() {
		return pedidos;
	}

	private void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	private String getTraduccion() {
		return traduccion;
	}

	private void setTraduccion(String traduccion) {
		this.traduccion = traduccion;
	}

	private double getTiempoPreparacion() {
		return tiempoPreparacion;
	}

	private void setTiempoPreparacion(double tiempoPreparacion) {
		this.tiempoPreparacion = tiempoPreparacion;
	}

	private String getCategoria() {
		return categoria;
	}

	private void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	private int getDisponibles() {
		return disponibles;
	}

	private void setDisponibles(int disponibles) {
		this.disponibles = disponibles;
	}

	private List<Producto> getEquivalentes() {
		return equivalentes;
	}

	private void setEquivalentes(List<Producto> equivalentes) {
		this.equivalentes = equivalentes;
	}

	private Tipo getTipo() {
		return tipo;
	}

	private void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	private List<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	private void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

	private Menu getMenu() {
		return menu;
	}

	private void setMenu(Menu menu) {
		this.menu = menu;
	}



	


}
