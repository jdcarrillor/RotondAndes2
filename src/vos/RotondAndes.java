package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class RotondAndes {

	@JsonProperty(value="zonas")
	private Zona zonas;
	
	@JsonProperty(value="usuarios")
	private List<Usuario> usuarios;	

	
	
	
	
	
	
	
	





	public RotondAndes(@JsonProperty(value="zonas")Zona zonas,@JsonProperty(value="usuarios")List<Usuario> usuarios) {
		super();
		this.zonas=zonas;
		this.usuarios=usuarios;
		
		
		
	}

	private Zona getzonas() {
		return zonas;
	}



	private void setzonas(Zona zonas) {
		this.zonas = zonas;
	}



	

	private List<Usuario> getUsuario() {
		return usuarios;
	}

	private void setUsuario(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	

}
