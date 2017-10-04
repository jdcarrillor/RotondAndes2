package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class UsuarioGeneral extends Usuario {

	@JsonProperty(value="correo")
	private String correo;
	
	public UsuarioGeneral(Long id, String nombre, String rol, Pedido pedido, RotondAndes rotondAndes, @JsonProperty(value="correo")String correo) {
		super(id, nombre, rol, pedido, rotondAndes);
		this.correo=correo;
		// TODO Auto-generated constructor stub
	}

	private String getCorreo() {
		return correo;
	}

	private void setCorreo(String correo) {
		this.correo = correo;
	}


}
