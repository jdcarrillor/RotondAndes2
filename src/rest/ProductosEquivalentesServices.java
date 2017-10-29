package rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.RotondAndesTM;
import vos.Pedido;
import vos.Producto;
import vos.ProductosEquivalentes;

@Path("productosEquivalentes")
public class ProductosEquivalentesServices 
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
	 * Metodo que expone servicio REST usando GET que da todos los Pedidos de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/PedidoAndes/rest/Pedidos
	 * @return Json con todos los Pedidos de la base de datos o json con 
     * el error que se produjo
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getPedidos() {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<ProductosEquivalentes> pedidos;
		try {
			pedidos = tm.darProducEqui();
		} catch (Exception e) {
	
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(pedidos).build();
	}
	
	

	 /**
     * Metodo que expone servicio REST usando GET que busca el Pedido con el id que entra como parametro
     * <b>URL: </b> http://"ip o nombre de host":8080/PedidoAndes/rest/Pedidos/<<id>>" para la busqueda"
     * @param name - Nombre del Pedido a buscar que entra en la URL como parametro 
     * @return Json con el/los Pedidos encontrados con el nombre que entra como parametro o json con 
     * el error que se produjo
     */
	@GET
	@Path( "{id}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getPedido( @PathParam( "id" ) String id )
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			ProductosEquivalentes v = tm.buscarProducEquiPorId(id);
			return Response.status( 200 ).entity( v ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProducto(ProductosEquivalentes Producto) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.addProduEquivalentes(Producto);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Producto).build();
	}
	
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteProducto(ProductosEquivalentes Producto) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.deleteProductoEquivalente(Producto);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Producto).build();
	}

	

}
