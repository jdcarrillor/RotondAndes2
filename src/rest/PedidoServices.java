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
import vos.Pedido;

@Path("pedidos")
public class PedidoServices {


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
		List<Pedido> pedidos;
		try {
			pedidos = tm.darPedidos();
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
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getPedido( @PathParam( "id" ) Long id )
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			Pedido v = tm.buscarPedidoPorId( id );
			return Response.status( 200 ).entity( v ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}


    /**
     * Metodo que expone servicio REST usando POST que agrega el Pedido que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/PedidoAndes/rest/Pedidos/Pedido
     * @param Pedido - Pedido a agregar
     * @return Json con el Pedido que agrego o Json con el error que se produjo
     */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPedido(Pedido Pedido) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.addPedido(Pedido);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Pedido).build();
	}
	
    /**
     * Metodo que expone servicio REST usando POST que agrega los Pedidos que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/PedidoAndes/rest/Pedidos/varios
     * @param Pedidos - Pedidos a agregar. 
     * @return Json con el Pedido que agrego o Json con el error que se produjo
     */
	@POST
	@Path("/varios")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPedido(List<Pedido> Pedidos) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.addPedidos(Pedidos);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Pedidos).build();
	}
	
    /**
     * Metodo que expone servicio REST usando PUT que actualiza el Pedido que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/PedidoAndes/rest/Pedidos
     * @param Pedido - Pedido a actualizar. 
     * @return Json con el Pedido que actualizo o Json con el error que se produjo
     */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePedido(Pedido Pedido) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.updatePedido(Pedido);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Pedido).build();
	}
	
    /**
     * Metodo que expone servicio REST usando DELETE que elimina el Pedido que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/PedidoAndes/rest/Pedidos
     * @param Pedido - Pedido a aliminar. 
     * @return Json con el Pedido que elimino o Json con el error que se produjo
     */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePedido(Pedido Pedido) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.deletePedido(Pedido);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Pedido).build();
	}
}
