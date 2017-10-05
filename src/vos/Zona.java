package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Zona {

	@JsonProperty(value="id")
	private Long id;
	
	@JsonProperty(value="espacio")
	private String espacio;
	
	@JsonProperty(value="capacidad")
	private int capacidad;
	
	@JsonProperty(value="necesidadesEspeciales")
	private double necesidadesEspeciales;
	
	@JsonProperty(value="condiciones")
	private String condiciones;
	
	
	
	
	
	
	
	
	
	
	





	public Zona(@JsonProperty(value="id")Long id, @JsonProperty(value="espacio")String espacio,@JsonProperty(value="capacidad")int capacidad,@JsonProperty(value="necesidadesEspeciales")double necesidadesEspeciales,@JsonProperty(value="condiciones")String condiciones) {
		super();
		this.id=id;
		this.espacio=espacio;
		this.capacidad=capacidad;
		this.necesidadesEspeciales=necesidadesEspeciales;
		this.condiciones=condiciones;
		
	
		
		
	}

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getespacio() {
		return espacio;
	}



	public void setespacio(String espacio) {
		this.espacio = espacio;
	}



	public int getcapacidad() {
		return capacidad;
	}



	public void setcapacidad(int capacidad) {
		this.capacidad = capacidad;
	}



	public double getnecesidadesEspeciales() {
		return necesidadesEspeciales;
	}



	public void setnecesidadesEspeciales(double necesidadesEspeciales) {
		this.necesidadesEspeciales = necesidadesEspeciales;
	}

	

	

	public String getcondiciones() {
		return condiciones;
	}

	public void setcondiciones(String condiciones) {
		this.condiciones = condiciones;
	}
	
	
	
}
