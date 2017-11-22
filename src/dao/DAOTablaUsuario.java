package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Consumo;
import vos.Usuario;

public class DAOTablaUsuario {
	

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
	public DAOTablaUsuario() {
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
	public ArrayList<Usuario> darUsuarios() throws SQLException, Exception {
		ArrayList<Usuario> Usuarios = new ArrayList<Usuario>();

		String sql = "SELECT * FROM Usuario";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long id = rs.getLong("IDUSUARIO");
			String nombre = rs.getString("NOMBREUSUARIO");
			String rol = rs.getString("ROL");
			String correo = rs.getString("CORREO");
			Usuarios.add(new Usuario(id, nombre, rol,correo ));
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
	public ArrayList<Usuario> buscarUsuariosPorNombre(String nombre) throws SQLException, Exception {
		ArrayList<Usuario> Usuarios = new ArrayList<Usuario>();

		String sql = "SELECT * FROM Usuario WHERE NOMBRE ='" + nombre + "'";;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String nombre2 = rs.getString("NOMBREUSUARIO");
			Long id = rs.getLong("IDUSUARIO");
			String rol = rs.getString("ROL");
			String correo = rs.getString("CORREO");
			Usuarios.add(new Usuario(id, nombre2, rol, correo));
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
	public Usuario buscarUsuarioPorId(Long id) throws SQLException, Exception 
	{
		Usuario Usuario = null;

		String sql = "SELECT * FROM Usuario WHERE IDUSUARIO =" + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			String nombre = rs.getString("NOMBREUSUARIO");
			Long id2 = rs.getLong("IDUSUARIO");
			String rol = rs.getString("ROL");
			String correo = rs.getString("CORREO");
			Usuario = new Usuario(id2, nombre, rol, correo);
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
	public void addUsuario(Usuario Usuario) throws SQLException, Exception {

		String sql = "INSERT INTO Usuario VALUES (";
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
	public void updateUsuario(Usuario Usuario) throws SQLException, Exception {

		String sql = "UPDATE Usuario SET ";
		sql += "NOMBREUSUARIO='" + Usuario.getNombre()+"'"  + ",";
		sql += "ROL='" + Usuario.getRol()+"'" ;
		sql += " WHERE IDUSUARIO = " + Usuario.getId();


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
	public void deleteUsuario(Usuario Usuario) throws SQLException, Exception {

		String sql = "DELETE FROM Usuario";
		sql += " WHERE IDUSUARIO = " + Usuario.getId();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public void consultarConsumo(Consumo consumo) throws SQLException, Exception{
		String sql = "SELECT IDUSUARIO, NOMBREUSUARIO, ROL, CORREO FROM (WITH AAA AS (SELECT * FROM USUARIO NATURAL JOIN"
				+ " (SELECT ID_USUARIO AS IDUSUARIO, ID_PRODUCTO AS IDPRODUCTO, FECHA FROM PEDIDO))"
				+ " SELECT DISTINCT * FROM AAA NATURAL JOIN (SELECT IDPRODUCTO,NOMBRE AS NOMBREPRODUCTO, ID_RESTAURANTE "
				+ "FROM PRODUCTO) NATURAL JOIN (SELECT ID_PRODUCTOTIPO AS IDPRODUCTO,ID_TIPOPROD FROM TIPOPRODUCTO) "
				+ "NATURAL JOIN (SELECT IDTIPO AS ID_TIPOPRODUCTO, NOMBRETIPO FROM TIPO) NATURAL JOIN"
				+ " (SELECT IDRESTAURANTE AS ID_RESTAURANTE, NOMBRERESTAURANTE FROM RESTAURANTE)"
				+ " WHERE CORREO = '" + consumo.getCorreo() + "' AND NOMBREPRODUCTO = '" + consumo.getProducto() 
				+ "' AND NOMBRETIPO = '" + consumo.getTipo() +"' AND NOMBRERESTAURANTE = '" + consumo.getRestaurante()
				+ "' AND FECHA >= '" + consumo.getFechaIn() + "' AND FECHA <= '" + consumo.getFechaFin() + "')";
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public void consultarNoConsumo(Consumo consumo)throws SQLException, Exception{
		String sql = "SELECT * FROM USUARIO MINUS" + "(SELECT IDUSUARIO, NOMBREUSUARIO, ROL, CORREO FROM (WITH AAA AS (SELECT * FROM USUARIO NATURAL JOIN"
				+ " (SELECT ID_USUARIO AS IDUSUARIO, ID_PRODUCTO AS IDPRODUCTO, FECHA FROM PEDIDO))"
				+ " SELECT DISTINCT * FROM AAA NATURAL JOIN (SELECT IDPRODUCTO,NOMBRE AS NOMBREPRODUCTO, ID_RESTAURANTE "
				+ "FROM PRODUCTO) NATURAL JOIN (SELECT ID_PRODUCTOTIPO AS IDPRODUCTO,ID_TIPOPROD FROM TIPOPRODUCTO) "
				+ "NATURAL JOIN (SELECT IDTIPO AS ID_TIPOPRODUCTO, NOMBRETIPO FROM TIPO) NATURAL JOIN"
				+ " (SELECT IDRESTAURANTE AS ID_RESTAURANTE, NOMBRERESTAURANTE FROM RESTAURANTE)"
				+ " WHERE CORREO = '" + consumo.getCorreo() + "' AND NOMBREPRODUCTO = '" + consumo.getProducto() 
				+ "' AND NOMBRETIPO = '" + consumo.getTipo() +"' AND NOMBRERESTAURANTE = '" + consumo.getRestaurante()
				+ "' AND FECHA >= '" + consumo.getFechaIn() + "' AND FECHA <= '" + consumo.getFechaFin() + "'))";
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	/**
	 * Metodo que busca el Usuario con el id que entra como parametro.
	 * @param id - id del Usuarios a buscar
	 * @return ArrayList con los Usuarios encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Usuario> buscarUsuariosFieles() throws SQLException, Exception {
		ArrayList<Usuario> Usuarios = new ArrayList<Usuario>();

		String sql = "SELECT *FROM((SELECT * FROM((SELECT IDPEDIDO, FECHA, ID_USUARIO,PEDIDOPRODUCTO.ID_PRODUCTO,ID_MENU,ID_PEDIDO FROM PEDIDO INNER JOIN PEDIDOPRODUCTO ON PEDIDO.IDPEDIDO= PEDIDOPRODUCTO.ID_PEDIDO)k INNER JOIN USUARIO ON k.ID_USUARIO=USUARIO.IDUSUARIO)WHERE ROL = 'Cliente')j INNER JOIN PRODUCTO ON PRODUCTO.IDPRODUCTO=j.ID_PRODUCTO) WHERE CATEGORIA ='Plato fuerte'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String nombre2 = rs.getString("NOMBREUSUARIO");
			Long id = rs.getLong("IDUSUARIO");
			String rol = rs.getString("ROL");
			String correo = rs.getString("CORREO");
			Usuarios.add(new Usuario(id, nombre2, rol, correo));
		}

		return Usuarios;
	}
	
	
	
	
	

}
