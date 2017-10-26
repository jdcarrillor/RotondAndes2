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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.RotondAndesTM;
import vos.Zona;

@Path("zonas")
public class ZonaServices 
{
	
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
	 * Metodo que expone servicio REST usando GET que da todos los videos de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos
	 * @return Json con todos los videos de la base de datos o json con 
     * el error que se produjo
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getZonas() {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<Zona> Zonas;
		try {
			Zonas = tm.darZonas();
		} catch (Exception e) {
	
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Zonas).build();
	}
	
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getZona( @PathParam( "id" ) Long id )
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			Zona Zona = tm.buscarZonaPorId( id );
			return Response.status( 200 ).entity( Zona ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Path( "GroupByEspacio" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getZonasGroupByEspacio()
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			List<Zona> Zona = tm.darZonasGroupByEspacio();
			return Response.status( 200 ).entity( Zona ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Path( "GroupByCapacidad" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getZonasGroupByCapacidad()
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			List<Zona> Zona = tm.darZonasGroupByCapacidad();
			return Response.status( 200 ).entity( Zona ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Path( "GroupByApto" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getZonasGroupByApto()
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			List<Zona> Zona = tm.darZonasGroupByApto();
			return Response.status( 200 ).entity( Zona ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Path( "GroupByCondiciones" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getZonasGroupByCondiciones()
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			List<Zona> Zona = tm.darZonasGroupByCondiciones();
			return Response.status( 200 ).entity( Zona ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	
	@GET
	@Path( "OrderByEspacio" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getZonasOrderByEspacio()
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			List<Zona> Zona = tm.darZonasOrderByEspacio();
			return Response.status( 200 ).entity( Zona ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Path( "OrderByCapacidad" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getZonasOrderByCapacidad()
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			List<Zona> Zona = tm.darZonasOrderByCapacidad();
			return Response.status( 200 ).entity( Zona ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Path( "OrderByApto" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getZonasOrderByApto()
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			List<Zona> Zona = tm.darZonasOrderByApto();
			return Response.status( 200 ).entity( Zona ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Path( "OrderByCondiciones" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getZonasOrderByCondiciones()
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			List<Zona> Zona = tm.darZonasOrderByCondiciones();
			return Response.status( 200 ).entity( Zona ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	/**
     * Metodo que expone servicio REST usando POST que agrega el video que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/video
     * @param video - video a agregar
     * @return Json con el video que agrego o Json con el error que se produjo
     */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addZona(Zona Zona) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.addZona(Zona);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Zona).build();
	}
	
	
	/**
     * Metodo que expone servicio REST usando POST que agrega los videos que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/varios
     * @param videos - videos a agregar. 
     * @return Json con el video que agrego o Json con el error que se produjo
     */
	@POST
	@Path("/varios")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addZona(List<Zona> Zonas) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.addZonas(Zonas);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Zonas).build();
	}
	
	 /**
     * Metodo que expone servicio REST usando PUT que actualiza el video que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos
     * @param video - video a actualizar. 
     * @return Json con el video que actualizo o Json con el error que se produjo
     */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateZona(Zona Zona) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.updateZona(Zona);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Zona).build();
	}
	
    /**
     * Metodo que expone servicio REST usando DELETE que elimina el video que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos
     * @param video - video a aliminar. 
     * @return Json con el video que elimino o Json con el error que se produjo
     */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteZona(Zona Zona) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.deleteZona(Zona);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Zona).build();
	}
}
