package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Usuario;
import vos.UsuarioCliente;

public class DAOTablaCliente {
	

	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;

	/**
	 * Metodo constructor que crea DAOUsuario
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOTablaCliente() {
		recursos = new ArrayList<Object>();
	}

	/**
	 * Metodo que cierra todos los recursos que estan enel arreglo de recursos
	 * <b>post: </b> Todos los recurso del arreglo de recursos han sido cerrados
	 */
	public void cerrarRecursos() {
		for(Object ob : recursos){
			if(ob instanceof PreparedStatement)
				try {
					((PreparedStatement) ob).close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
	}

	/**
	 * Metodo que inicializa la connection del DAO a la base de datos con la conexión que entra como parametro.
	 * @param con  - connection a la base de datos
	 */
	public void setConn(Connection con){
		this.conn = con;
	}


	/**
	 * Metodo que, usando la conexión a la base de datos, saca todos los Usuarios de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM UsuarioS;
	 * @return Arraylist con los Usuarios de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<UsuarioCliente> darUsuarios() throws SQLException, Exception {
		ArrayList<UsuarioCliente> Usuarios = new ArrayList<UsuarioCliente>();

		String sql = "SELECT * FROM UsuarioCliente";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long id = rs.getLong("IDUSUARIOCLIENTE");
			String nombre = rs.getString("NOMBREUSUARIOC");
			String rol = rs.getString("ROL");
			String correo = rs.getString("CORREOCLIENTE");
			Usuarios.add(new UsuarioCliente(id, nombre, rol,correo ));
		}
		return Usuarios;
	}


	/**
	 * Metodo que busca el Usuario con el id que entra como parametro.
	 * @param id - id del Usuarios a buscar
	 * @return ArrayList con los Usuarios encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<UsuarioCliente> buscarUsuariosPorNombre(String nombre) throws SQLException, Exception {
		ArrayList<UsuarioCliente> Usuarios = new ArrayList<UsuarioCliente>();

		String sql = "SELECT * FROM UsuarioCliente WHERE NOMBREUSUARIOC ='" + nombre + "'";;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String nombre2 = rs.getString("NOMBREUSUARIOC");
			Long id = rs.getLong("IDUSUARIOCLIENTE");
			String rol = rs.getString("ROL");
			String correo = rs.getString("CORREOCLIENTE");
			Usuarios.add(new UsuarioCliente(id, nombre2, rol, correo));
		}

		return Usuarios;
	}
	
	/**
	 * Metodo que busca el Usuario con el id que entra como parametro.
	 * @param name - Id de el Usuario a buscar
	 * @return Usuario encontrado
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public UsuarioCliente buscarUsuarioPorId(Long id) throws SQLException, Exception 
	{
		UsuarioCliente Usuario = null;

		String sql = "SELECT * FROM UsuarioCliente WHERE IDUSUARIOCLIENTE =" + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			String nombre = rs.getString("NOMBREUSUARIOC");
			Long id2 = rs.getLong("IDUSUARIOCLIENTE");
			String rol = rs.getString("ROL");
			String correo = rs.getString("CORREOCLIENTE");
			Usuario = new UsuarioCliente(id2, nombre, rol, correo);
		}

		return Usuario;
	}

	/**
	 * Metodo que agrega el Usuario que entra como parametro a la base de datos.
	 * @param Usuario - el Usuario a agregar. Usuario !=  null
	 * <b> post: </b> se ha agregado el Usuario a la base de datos en la transaction actual. pendiente que el Usuario master
	 * haga commit para que el Usuario baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Usuario a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addUsuario(UsuarioCliente Usuario) throws SQLException, Exception {

		String sql = "INSERT INTO UsuarioCliente VALUES (";
		sql += Usuario.getId() + ",";
		sql += "'"+ Usuario.getNombre()+"'" + ",";
		sql += "'"+ Usuario.getRol()+"'" + ",";
		sql += "'"+ Usuario.getCorreo()+"'" + ")";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	/**
	 * Metodo que actualiza el Usuario que entra como parametro en la base de datos.
	 * @param Usuario - el Usuario a actualizar. Usuario !=  null
	 * <b> post: </b> se ha actualizado el Usuario en la base de datos en la transaction actual. pendiente que el Usuario master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Usuario.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void updateUsuario(UsuarioCliente Usuario) throws SQLException, Exception {

		String sql = "UPDATE UsuarioCliente SET ";
		sql += "NOMBREUSUARIOC='" + Usuario.getNombre()+"'"  + ",";
		sql += "ROL='" + Usuario.getRol()+"'"  + ",";
		sql += "CORREOCLIENTE='" + Usuario.getCorreo()+"'" ;
		sql += " WHERE IDUSUARIOCLIENTE = " + Usuario.getId();


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	/**
	 * Metodo que elimina el Usuario que entra como parametro en la base de datos.
	 * @param Usuario - el Usuario a borrar. Usuario !=  null
	 * <b> post: </b> se ha borrado el Usuario en la base de datos en la transaction actual. pendiente que el Usuario master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Usuario.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteUsuario(UsuarioCliente Usuario) throws SQLException, Exception {

		String sql = "DELETE FROM UsuarioCliente";
		sql += " WHERE IDUSUARIOCLIENTE = " + Usuario.getId();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

}
