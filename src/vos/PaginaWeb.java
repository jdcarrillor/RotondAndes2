package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class PaginaWeb {

	@JsonProperty(value="id")
	private Long id;
	
	@JsonProperty(value="trayectoria")
	private String trayectoria;
	
	@JsonProperty(value="logros")
	private List<String>logros;
	
	@JsonProperty(value="restaurante")
	private Restaurante restaurante;
	
	public PaginaWeb(@JsonProperty(value="id")Long id, @JsonProperty(value="trayectoria")String trayectoria,@JsonProperty(value="logors")List<String> logros,@JsonProperty(value="restaurante")Restaurante restaurante){
		super();
		this.setId(id);
		this.setTrayectoria(trayectoria);
		this.setLogros(logros);
		this.setRestaurante(restaurante);
	
	}

	private Long getId() {
		return id;
	}

	private void setId(Long id) {
		this.id = id;
	}

	private String getTrayectoria() {
		return trayectoria;
	}

	private void setTrayectoria(String trayectoria) {
		this.trayectoria = trayectoria;
	}

	private List<String> getLogros() {
		return logros;
	}

	private void setLogros(List<String> logros) {
		this.logros = logros;
	}

	private Restaurante getRestaurante() {
		return restaurante;
	}

	private void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}
	
	

}
