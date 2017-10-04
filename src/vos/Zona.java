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
	private boolean necesidadesEspeciales;
	
	@JsonProperty(value="evento")
	private Evento evento;
	
	@JsonProperty(value="restaurantes")
	private List<Restaurante> restaurantes;
	
	@JsonProperty(value="condiciones")
	private String condiciones;
	
	@JsonProperty(value="rotondAndes")
	private RotondAndes rotondAndes;
	
	
	
	
	
	
	
	
	





	public Zona(@JsonProperty(value="id")Long id, @JsonProperty(value="espacio")String espacio,@JsonProperty(value="capacidad")int capacidad,@JsonProperty(value="necesidadesEspeciales")boolean necesidadesEspeciales,@JsonProperty(value="evento")Evento evento,@JsonProperty(value="restaurantes")List<Restaurante> restaurantes,@JsonProperty(value="condiciones")String condiciones,@JsonProperty(value="rotondAndes")RotondAndes rotondAndes) {
		super();
		this.id=id;
		this.espacio=espacio;
		this.capacidad=capacidad;
		this.necesidadesEspeciales=necesidadesEspeciales;
		this.evento =evento;
		this.restaurantes=restaurantes;
		this.condiciones=condiciones;
		this.rotondAndes=rotondAndes;
	
		
		
	}

	private Long getId() {
		return id;
	}



	private void setId(Long id) {
		this.id = id;
	}



	private String getespacio() {
		return espacio;
	}



	private void setespacio(String espacio) {
		this.espacio = espacio;
	}



	private int getcapacidad() {
		return capacidad;
	}



	private void setcapacidad(int capacidad) {
		this.capacidad = capacidad;
	}



	private boolean getnecesidadesEspeciales() {
		return necesidadesEspeciales;
	}



	private void setnecesidadesEspeciales(boolean necesidadesEspeciales) {
		this.necesidadesEspeciales = necesidadesEspeciales;
	}

	private Evento getEvento() {
		return evento;
	}

	private void setEvento(Evento evento) {
		this.evento = evento;
	}

	private List<Restaurante> getRestaurante() {
		return restaurantes;
	}

	private void setRestaurante(List<Restaurante> restaurantes) {
		this.restaurantes = restaurantes;
	}

	private String getcondiciones() {
		return condiciones;
	}

	private void setcondiciones(String condiciones) {
		this.condiciones = condiciones;
	}
	
	private RotondAndes getRotondAndes() {
		return rotondAndes;
	}

	private void setRotondAndes(RotondAndes rotondAndes) {
		this.rotondAndes = rotondAndes;
	}
	
}
