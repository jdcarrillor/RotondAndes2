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
	
<<<<<<< HEAD


	
	
=======
>>>>>>> 9770101ea6e6b965aa8c30a80f61b6eae7bd6522
	@JsonProperty(value="maxproductos")
	private int maxproductos;
	
	@JsonProperty(value="ingresos")
	private double ingresos;
	
		
	
	public Restaurante(@JsonProperty(value="id")Long id, @JsonProperty(value="nombre")String nombre,@JsonProperty(value="representante")String representante,@JsonProperty(value="tipocomida")String tipo_comida,@JsonProperty(value="idzona")Long id_zona,@JsonProperty(value="capacidad")int capacidad,@JsonProperty(value="maxproductos")int maxproductos,@JsonProperty(value="ingresos")double ingresos) {
<<<<<<< HEAD

=======
>>>>>>> 9770101ea6e6b965aa8c30a80f61b6eae7bd6522
		super();
		this.id=id;
		this.nombre=nombre;
		this.representante=representante;
		this.tipocomida=tipo_comida;
		this.idzona= id_zona;
<<<<<<< HEAD
;		
		this.capacidad=capacidad;
		this.maxproductos=maxproductos;
		this.ingresos=ingresos;
				

		
=======
		this.capacidad=capacidad;
		this.maxproductos=maxproductos;
		this.ingresos=ingresos;

>>>>>>> 9770101ea6e6b965aa8c30a80f61b6eae7bd6522
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
<<<<<<< HEAD
 public  int getMaxproductos() {
=======
public int getMaxproductos() {
>>>>>>> 9770101ea6e6b965aa8c30a80f61b6eae7bd6522
	return maxproductos;
}
public void setMaxproductos(int maxproductos) {
	this.maxproductos = maxproductos;
}
public double getIngresos() {
	return ingresos;
}
public void setIngresos(double ingresos) {
	this.ingresos = ingresos;
}
<<<<<<< HEAD


=======
>>>>>>> 9770101ea6e6b965aa8c30a80f61b6eae7bd6522

	
}
