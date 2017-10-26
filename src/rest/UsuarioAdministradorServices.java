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
import vos.UsuarioAdministrador;
import vos.UsuarioCliente;

@Path("usuariosAdmin")
public class UsuarioAdministradorServices {

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
	 * Metodo que expone servicio REST usando GET que da todos los Usuarios de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/UsuarioAndes/rest/Usuarios
	 * @return Json con todos los Usuarios de la base de datos o json con 
     * el error que se produjo
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getUsuarios() {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<UsuarioAdministrador> Usuarios;
		try {
			Usuarios = tm.darUsuariosAdmin();
		} catch (Exception e) {
	
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Usuarios).build();
	}

	 /**
     * Metodo que expone servicio REST usando GET que busca el Usuario con el id que entra como parametro
     * <b>URL: </b> http://"ip o nombre de host":8080/UsuarioAndes/rest/Usuarios/<<id>>" para la busqueda"
     * @param name - Nombre del Usuario a buscar que entra en la URL como parametro 
     * @return Json con el/los Usuarios encontrados con el nombre que entra como parametro o json con 
     * el error que se produjo
     */
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getUsuario( @PathParam( "id" ) Long id )
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			UsuarioAdministrador v = tm.buscarUsuarioAdminPorId( id );
			return Response.status( 200 ).entity( v ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}

    /**
     * Metodo que expone servicio REST usando GET que busca el Usuario con el nombre que entra como parametro
     * <b>URL: </b> http://"ip o nombre de host":8080/UsuarioAndes/rest/Usuarios/nombre/nombre?nombre=<<nombre>>" para la busqueda"
     * @param name - Nombre del Usuario a buscar que entra en la URL como parametro 
     * @return Json con el/los Usuarios encontrados con el nombre que entra como parametro o json con 
     * el error que se produjo
     */
	@GET
	@Path( "{nombre}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getUsuarioName( @PathParam("nombre") String name) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<UsuarioAdministrador> Usuarios;
		try {
			if (name == null || name.length() == 0)
				throw new Exception("Nombre del Usuario no valido");
			Usuarios = tm.buscarUsuariosAdminPorName(name);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Usuarios).build();
	}


    /**
     * Metodo que expone servicio REST usando POST que agrega el Usuario que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/UsuarioAndes/rest/Usuarios/Usuario
     * @param Usuario - Usuario a agregar
     * @return Json con el Usuario que agrego o Json con el error que se produjo
     */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUsuario(UsuarioAdministrador Usuario) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.addUsuarioAdministrador(Usuario);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Usuario).build();
	}
	
    /**
     * Metodo que expone servicio REST usando POST que agrega los Usuarios que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/UsuarioAndes/rest/Usuarios/varios
     * @param Usuarios - Usuarios a agregar. 
     * @return Json con el Usuario que agrego o Json con el error que se produjo
     */
	@POST
	@Path("/varios")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUsuario(List<UsuarioAdministrador> Usuarios) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.addUsuariosAdministrador(Usuarios);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Usuarios).build();
	}
	
    /**
     * Metodo que expone servicio REST usando PUT que actualiza el Usuario que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/UsuarioAndes/rest/Usuarios
     * @param Usuario - Usuario a actualizar. 
     * @return Json con el Usuario que actualizo o Json con el error que se produjo
     */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUsuario(UsuarioAdministrador Usuario) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.updateUsuarioAdministrador(Usuario);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Usuario).build();
	}
	
    /**
     * Metodo que expone servicio REST usando DELETE que elimina el Usuario que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/UsuarioAndes/rest/Usuarios
     * @param Usuario - Usuario a aliminar. 
     * @return Json con el Usuario que elimino o Json con el error que se produjo
     */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUsuario(UsuarioAdministrador Usuario) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.deleteUsuarioAdministrador(Usuario);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Usuario).build();
	}
}
