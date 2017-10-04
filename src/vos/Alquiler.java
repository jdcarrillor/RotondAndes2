package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Alquiler
{
	
////Atributos

	/**
	 * Id del video
	 */
	@JsonProperty(value="id")
	private Long id;
	
	/**
	 * Id del video
	 */
	@JsonProperty(value="idVideo")
	private Long idVideo;

	/**
	 * Nombre del video
	 */
	@JsonProperty(value="name")
	private String name;



	/**
	 * Metodo constructor de la clase video
	 * <b>post: </b> Crea el video con los valores que entran como parametro
	 * @param id - Id del video.
	 * @param name - Nombre del video. name != null
	 * @param duration - Duracion en minutos del video.
	 */
	public Alquiler(@JsonProperty(value="id")Long id, @JsonProperty(value="idVideo")Long idVideo,@JsonProperty(value="name")String name) {
		super();
		this.id = id;
		this.idVideo = idVideo;
		this.name = name;
	}
	
	public Long getIdVideo() {
		return idVideo;
	}
	
	public void setIdVideo(Long idVideo) {
		this.idVideo = idVideo;
	}
	
	

	/**
	 * Metodo getter del atributo id
	 * @return id del video
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Metodo setter del atributo id <b>post: </b> El id del video ha sido
	 * cambiado con el valor que entra como parametro
	 * @param id - Id del video
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Metodo getter del atributo name
	 * @return nombre del video
	 */
	public String getName() {
		return name;
	}

	/**
	 * Metodo setter del atributo name <b>post: </b> El nombre del video ha sido
	 * cambiado con el valor que entra como parametro
	 * @param name - Id del video
	 */
	public void setName(String name) {
		this.name = name;
	}

}
