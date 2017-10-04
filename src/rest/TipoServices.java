package rest;


import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import tm.RotondAndesTM;
import vos.Ingrediente;
import vos.Tipo;

@Path("tipos")
public class TipoServices {
	

	/**
	 * Atributo que usa la anotacion @Context para tener el ServletContext de la conexion actual.
	 */
	@Context
	private ServletContext context;

	/**
	 * Metodo que retorna el path de la carpeta WEB-INF/ConnectionData en el deploy actual dentro del servidor.
	 * @return path de la carpeta WEB-INF/ConnectionData en el deploy actual.
	 */
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}
	
	
	private String doErrorMessage(Exception e){
		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	}
	

	/**
	 * Metodo que expone servicio REST usando GET que da todos los Tipos de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/TipoAndes/rest/Tipos
	 * @return Json con todos los Tipos de la base de datos o json con 
     * el error que se produjo
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getTipos() {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<Tipo> Tipos;
		try {
			Tipos = tm.darTipos();
		} catch (Exception e) {
	
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Tipos).build();
	}

	 /**
     * Metodo que expone servicio REST usando GET que busca el Tipo con el id que entra como parametro
     * <b>URL: </b> http://"ip o nombre de host":8080/TipoAndes/rest/Tipos/<<id>>" para la busqueda"
     * @param name - Nombre del Tipo a buscar que entra en la URL como parametro 
     * @return Json con el/los Tipos encontrados con el nombre que entra como parametro o json con 
     * el error que se produjo
     */
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getTipo( @PathParam( "id" ) Long id )
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			Tipo v = tm.buscarTipoPorId( id );
			return Response.status( 200 ).entity( v ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}

    /**
     * Metodo que expone servicio REST usando GET que busca el Tipo con el nombre que entra como parametro
     * <b>URL: </b> http://"ip o nombre de host":8080/TipoAndes/rest/Tipos/nombre/nombre?nombre=<<nombre>>" para la busqueda"
     * @param name - Nombre del Tipo a buscar que entra en la URL como parametro 
     * @return Json con el/los Tipos encontrados con el nombre que entra como parametro o json con 
     * el error que se produjo
     */
	@GET
	@Path( "{nombre}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getTipoName( @QueryParam("nombre") String name) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<Tipo> Tipos;
		try {
			if (name == null || name.length() == 0)
				throw new Exception("Nombre del Tipo no valido");
			Tipos = tm.buscarTiposPorName(name);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Tipos).build();
	}


    /**
     * Metodo que expone servicio REST usando POST que agrega el Tipo que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/TipoAndes/rest/Tipos/Tipo
     * @param Tipo - Tipo a agregar
     * @return Json con el Tipo que agrego o Json con el error que se produjo
     */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addTipo(Tipo Tipo) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.addTipo(Tipo);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Tipo).build();
	}
	
    /**
     * Metodo que expone servicio REST usando POST que agrega los Tipos que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/TipoAndes/rest/Tipos/varios
     * @param Tipos - Tipos a agregar. 
     * @return Json con el Tipo que agrego o Json con el error que se produjo
     */
	@POST
	@Path("/varios")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addTipo(List<Tipo> Tipos) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.addTipos(Tipos);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Tipos).build();
	}
	
    /**
     * Metodo que expone servicio REST usando PUT que actualiza el Tipo que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/TipoAndes/rest/Tipos
     * @param Tipo - Tipo a actualizar. 
     * @return Json con el Tipo que actualizo o Json con el error que se produjo
     */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateTipo(Tipo Tipo) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.updateTipo(Tipo);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Tipo).build();
	}
	
    /**
     * Metodo que expone servicio REST usando DELETE que elimina el Tipo que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/TipoAndes/rest/Tipos
     * @param Tipo - Tipo a aliminar. 
     * @return Json con el Tipo que elimino o Json con el error que se produjo
     */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteTipo(Tipo Tipo) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.deleteTipo(Tipo);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Tipo).build();
	}

	
}
