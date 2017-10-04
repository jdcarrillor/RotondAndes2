package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Restaurante
{

	@JsonProperty(value="id")
	private Long id;
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="representante")
	private String representante;
	
	@JsonProperty(value="tipo_comida")
	private String tipo_comida;
	
	@JsonProperty(value="id_zona")
	private Long id_zona;
	
	@JsonProperty(value="id_paginaweb")
	private Long id_paginaweb;
	
	public Restaurante(@JsonProperty(value="id")Long id, @JsonProperty(value="nombre")String nombre,@JsonProperty(value="representante")String representante,@JsonProperty(value="tipo_comida")String tipo_comida,@JsonProperty(value="id_Zona")Long id_zona, @JsonProperty(value="id_paginaWeb")Long id_paginaweb ) {
		super();
		this.id=id;
		this.nombre=nombre;
		this.representante=representante;
		this.tipo_comida=tipo_comida;
		this.id_paginaweb=id_paginaweb;
		this.id_zona= id_zona;
	
		
		
	}
	public Restaurante()
	{
		
	}


	public Long getId() {
	return id;
}



public void setId(Long id) {
	this.id = id;
}



public String getnombre() {
	return nombre;
}



public void setnombre(String nombre) {
	this.nombre = nombre;
}



public String getrepresentante() {
	return representante;
}



public void setrepresentante(String representante) {
	this.representante = representante;
}



public String gettipoComida() {
	return tipo_comida;
}



public void settipoComida(String tipoComida) {
	this.tipo_comida = tipoComida;
}





public Long getIdZona() {
	return id_zona;
}

public void setIdZona(Long idZona) {
	this.id_zona = idZona;
}

public Long getIdPaginaWeb() {
	return id_paginaweb;
}

public void setIdPaginaWeb(Long idPaginaWeb) {
	this.id_paginaweb = idPaginaWeb;
}

	
}
