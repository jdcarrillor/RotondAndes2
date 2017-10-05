package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Ingrediente;
import vos.Video;

public class DAOTablaIngrediente {


	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;

	/**
	 * Metodo constructor que crea DAOIngrediente
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOTablaIngrediente() {
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
	 * Metodo que, usando la conexión a la base de datos, saca todos los Ingredientes de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM IngredienteS;
	 * @return Arraylist con los Ingredientes de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Ingrediente> darIngredientes() throws SQLException, Exception {
		ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();

		String sql = "SELECT * FROM INGREDIENTE";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long id = rs.getLong("ID");
			String nombre = rs.getString("NOMBRE");
			String descripcion = rs.getString("DESCRIPCION");
			String traduccion = rs.getString("TRADUCCION");
			ingredientes.add(new Ingrediente(id, nombre, descripcion, traduccion));
		}
		return ingredientes;
	}


	/**
	 * Metodo que busca el ingrediente con el id que entra como parametro.
	 * @param id - id del ingredientes a buscar
	 * @return ArrayList con los Ingredientes encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Ingrediente> buscarIngredientesPorNombre(String nombre) throws SQLException, Exception {
		ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();

		String sql = "SELECT * FROM INGREDIENTE WHERE NOMBRE ='" + nombre + "'";;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String nombre2 = rs.getString("NOMBRE");
			Long id = rs.getLong("ID");
			String descripcion = rs.getString("DESCRIPCION");
			String traduccion = rs.getString("TRADUCCION");
			ingredientes.add(new Ingrediente(id, nombre2, descripcion, traduccion));
		}

		return ingredientes;
	}
	
	/**
	 * Metodo que busca el Ingrediente con el id que entra como parametro.
	 * @param name - Id de el Ingrediente a buscar
	 * @return Ingrediente encontrado
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public Ingrediente buscarIngredientePorId(Long id) throws SQLException, Exception 
	{
		Ingrediente ingrediente = null;

		String sql = "SELECT * FROM INGREDIENTE WHERE ID =" + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			String nombre = rs.getString("NOMBRE");
			Long id2 = rs.getLong("ID");
			String descripcion = rs.getString("DESCRIPCION");
			String traduccion = rs.getString("TRADUCCION");
			ingrediente = new Ingrediente(id2, nombre, descripcion, traduccion);
		}

		return ingrediente;
	}

	/**
	 * Metodo que agrega el Ingrediente que entra como parametro a la base de datos.
	 * @param Ingrediente - el Ingrediente a agregar. Ingrediente !=  null
	 * <b> post: </b> se ha agregado el Ingrediente a la base de datos en la transaction actual. pendiente que el Ingrediente master
	 * haga commit para que el Ingrediente baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Ingrediente a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addIngrediente(Ingrediente ingrediente) throws SQLException, Exception {

		String sql = "INSERT INTO INGREDIENTE VALUES (";
		sql += ingrediente.getId() + ",";
		sql += ingrediente.getNombre() + ",";
		sql += ingrediente.getDescripcion() + ",";
		sql += ingrediente.getTraduccion() + ")";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	/**
	 * Metodo que actualiza el Ingrediente que entra como parametro en la base de datos.
	 * @param Ingrediente - el Ingrediente a actualizar. Ingrediente !=  null
	 * <b> post: </b> se ha actualizado el Ingrediente en la base de datos en la transaction actual. pendiente que el Ingrediente master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Ingrediente.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void updateIngrediente(Ingrediente ingrediente) throws SQLException, Exception {

		String sql = "UPDATE INGREDIENTE SET ";
		sql += "NOMBRE='" + ingrediente.getNombre() + ",";
		sql += "DESCRIPCION=" + ingrediente.getDescripcion()+ ",";
		sql += "TRADUCCION=" + ingrediente.getTraduccion();
		sql += " WHERE ID = " + ingrediente.getId();


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	/**
	 * Metodo que elimina el Ingrediente que entra como parametro en la base de datos.
	 * @param Ingrediente - el Ingrediente a borrar. Ingrediente !=  null
	 * <b> post: </b> se ha borrado el Ingrediente en la base de datos en la transaction actual. pendiente que el Ingrediente master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Ingrediente.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteIngrediente(Ingrediente ingrediente) throws SQLException, Exception {

		String sql = "DELETE FROM INGREDIENTE";
		sql += " WHERE ID = " + ingrediente.getId();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
}
