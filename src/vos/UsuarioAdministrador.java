package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class UsuarioAdministrador {

	@JsonProperty(value="id")
	private Long id;
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="rol")
	private String rol;
	
	
	@JsonProperty(value="correo")
	private String correo;
	
	
	
	public UsuarioAdministrador(@JsonProperty(value="id")Long id, @JsonProperty(value="nombre")String nombre,@JsonProperty(value="rol")String rol,@JsonProperty(value="correo")String correo)
	{
		super();
		this.id=(id);
		this.nombre=nombre;
		this.rol=rol;
		this.correo=correo;
		
	
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getRol() {
		return rol;
	}


	public void setRol(String rol) {
		this.rol = rol;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}

	

}
