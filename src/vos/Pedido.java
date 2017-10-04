package vos;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class Pedido 
{
	@JsonProperty(value="id")
	private Long id;
	
	@JsonProperty(value="fecha")
	private Date fecha;
	

	@JsonProperty(value="menu")
	private Menu menu;
	



	public Pedido(@JsonProperty(value="id")Long id, @JsonProperty(value="fecha")Date fecha, @JsonProperty(value="menu")Menu menu) {
		super();
		this.id=id;
		this.fecha=fecha;
		this.setMenu(menu);
		
		
	}

	private Long getId() {
		return id;
	}



	private void setId(Long id) {
		this.id = id;
	}



	private Date getfecha() {
		return fecha;
	}



	private void setfecha(Date fecha) {
		this.fecha = fecha;
	}

	private Menu getMenu() {
		return menu;
	}

	private void setMenu(Menu menu) {
		this.menu = menu;
	}



	
}
