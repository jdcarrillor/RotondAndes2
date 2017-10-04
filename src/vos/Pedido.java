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
	

	@JsonProperty(value="menus")
	private List<Menu> menus;
	
	@JsonProperty(value="usuario")
	private Usuario usuario;
	
	@JsonProperty(value="productos")
	private List<Producto> productos;
	
	@JsonProperty(value="idUsuario")
	private Long idUsuario;
	




	public Pedido(@JsonProperty(value="id")Long id, @JsonProperty(value="fecha")Date fecha, @JsonProperty(value="menus")List<Menu> menus, @JsonProperty(value="usuario")Usuario usuario, @JsonProperty(value="productos")List<Producto> productos) {
		super();
		this.id=id;
		this.fecha=fecha;
		this.menus = menus;
		this.usuario=usuario;
		this.productos = productos;
	}
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


	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	
	
	
	public Long getIdUsuarioX()
	{
		if(usuario!=null)
		{
			return usuario.getId();
		}
		return null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) 
	{
		this.idUsuario = idUsuario;

	}



	
}
