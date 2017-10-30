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
	
	@JsonProperty(value="tipocomida")
	private String tipocomida;
	
	@JsonProperty(value="idzona")
	private Long idzona;
	
	@JsonProperty(value="capacidad")
	private int capacidad;
	
	@JsonProperty(value="max_produc")
	private int maxProduc;
	
	@JsonProperty(value="ingresos")
	private Long ingresos;
	
	
	public Restaurante(@JsonProperty(value="id")Long id, @JsonProperty(value="nombre")String nombre,@JsonProperty(value="representante")String representante,@JsonProperty(value="tipocomida")String tipo_comida,@JsonProperty(value="idzona")Long id_zona, @JsonProperty(value="capacidad") int capacidad, @JsonProperty(value="max_produc") int maxProduc, @JsonProperty(value="ingresos") Long ingresos ) {
		super();
		this.id=id;
		this.nombre=nombre;
		this.representante=representante;
		this.tipocomida=tipo_comida;
		this.idzona= id_zona;
		this.capacidad = capacidad;
		this.maxProduc=maxProduc;
		this.ingresos=ingresos;
		
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
	return tipocomida;
}



public void settipoComida(String tipoComida) {
	this.tipocomida = tipoComida;
}





public Long getIdZona() {
	return idzona;
}

public void setIdZona(Long idZona) {
	this.idzona = idZona;
}
public int getCapacidad() {
	return capacidad;
}
public void setCapacidad(int capacidad) {
	this.capacidad = capacidad;
}
public int getMaxProduc() {
	return maxProduc;
}
public void setMaxProduc(int maxProduc) {
	this.maxProduc = maxProduc;
}
public Long getIngresos() {
	return ingresos;
}
public void setIngresos(Long ingresos) {
	this.ingresos = ingresos;
}




	
}
