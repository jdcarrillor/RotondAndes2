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
import vos.Producto;

@Path("productos")
public class ProductoServices {

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
	 * Metodo que expone servicio REST usando GET que da todos los Productos de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/ProductoAndes/rest/Productos
	 * @return Json con todos los Productos de la base de datos o json con 
     * el error que se produjo
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getProductos() {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<Producto> Productos;
		try {
			Productos = tm.darProductos();
		} catch (Exception e) {
	
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Productos).build();
	}

	 /**
     * Metodo que expone servicio REST usando GET que busca el Producto con el id que entra como parametro
     * <b>URL: </b> http://"ip o nombre de host":8080/ProductoAndes/rest/Productos/<<id>>" para la busqueda"
     * @param name - Nombre del Producto a buscar que entra en la URL como parametro 
     * @return Json con el/los Productos encontrados con el nombre que entra como parametro o json con 
     * el error que se produjo
     */
	
	@GET
	@Path( "fechas/{}" )
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getProductosFechas() {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<Producto> Productos;
		try {
			Productos = tm.darProductos();
		} catch (Exception e) {
	
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Productos).build();
	}
	
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getProducto( @PathParam( "id" ) Long id )
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			Producto v = tm.buscarProductoPorId( id );
			return Response.status( 200 ).entity( v ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Path( "OrderByNombre" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getProductosOrderByNombre()
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			List<Producto> v = tm.darProductosOrderByNombre();
			return Response.status( 200 ).entity( v ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	
	@GET
	@Path( "OrderByCosto" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getProductosOrderByCosto()
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			List<Producto> v = tm.darProductosOrderByCosto();
			return Response.status( 200 ).entity( v ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	
	@GET
	@Path( "OrderByCategoria" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getProductosOrderByCategoria()
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			List<Producto> v = tm.darProductosOrderByCategoria();
			return Response.status( 200 ).entity( v ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Path( "OrderByPrecio" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getProductosOrderByPrecio()
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			List<Producto> v = tm.darProductosOrderByPrecio();
			return Response.status( 200 ).entity( v ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	
	@GET
	@Path( "OrderByTiempo" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getProductosOrderByTiempo()
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			List<Producto> v = tm.darProductosOrderByTiempo();
			return Response.status( 200 ).entity( v ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	
	
	
	@GET
	@Path( "GroupByNombre" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getProductosGroupByNombre()
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			List<Producto> v = tm.darProductosGroupByNombre();
			return Response.status( 200 ).entity( v ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	
	@GET
	@Path( "GroupByCosto" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getProductosGroupByCosto()
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			List<Producto> v = tm.darProductosGroupByCosto();
			return Response.status( 200 ).entity( v ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	
	@GET
	@Path( "GroupByCategoria" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getProductosGroupByCategoria()
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			List<Producto> v = tm.darProductosGroupByCategoria();
			return Response.status( 200 ).entity( v ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Path( "GroupByPrecio" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getProductosGroupByPrecio()
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			List<Producto> v = tm.darProductosGroupByPrecio();
			return Response.status( 200 ).entity( v ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	
	@GET
	@Path( "GroupByTiempo" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getProductosGroupByTiempo()
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			List<Producto> v = tm.darProductosGroupByTiempo();
			return Response.status( 200 ).entity( v ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}

    /**
     * Metodo que expone servicio REST usando GET que busca el Producto con el nombre que entra como parametro
     * <b>URL: </b> http://"ip o nombre de host":8080/ProductoAndes/rest/Productos/nombre/nombre?nombre=<<nombre>>" para la busqueda"
     * @param name - Nombre del Producto a buscar que entra en la URL como parametro 
     * @return Json con el/los Productos encontrados con el nombre que entra como parametro o json con 
     * el error que se produjo
     */
	@GET
	@Path( "{nombre}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getProductoName( @PathParam("nombre") String name) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<Producto> Productos;
		try {
			if (name == null || name.length() == 0)
				throw new Exception("Nombre del Producto no valido");
			Productos = tm.buscarProductosPorName(name);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Productos).build();
	}


    /**
     * Metodo que expone servicio REST usando POST que agrega el Producto que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/ProductoAndes/rest/Productos/Producto
     * @param Producto - Producto a agregar
     * @return Json con el Producto que agrego o Json con el error que se produjo
     */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProducto(Producto Producto) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.addProducto(Producto);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Producto).build();
	}
	
    /**
     * Metodo que expone servicio REST usando POST que agrega los Productos que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/ProductoAndes/rest/Productos/varios
     * @param Productos - Productos a agregar. 
     * @return Json con el Producto que agrego o Json con el error que se produjo
     */
	@POST
	@Path("/varios")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProducto(List<Producto> Productos) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.addProductos(Productos);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Productos).build();
	}
	
    /**
     * Metodo que expone servicio REST usando PUT que actualiza el Producto que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/ProductoAndes/rest/Productos
     * @param Producto - Producto a actualizar. 
     * @return Json con el Producto que actualizo o Json con el error que se produjo
     */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateProducto(Producto Producto) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.updateProducto(Producto);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Producto).build();
	}
	
    /**
     * Metodo que expone servicio REST usando DELETE que elimina el Producto que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/ProductoAndes/rest/Productos
     * @param Producto - Producto a aliminar. 
     * @return Json con el Producto que elimino o Json con el error que se produjo
     */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteProducto(Producto Producto) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.deleteProducto(Producto);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Producto).build();
	}

}
