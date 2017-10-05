package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Tipo;

public class DAOTablaTipo {

	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;

	/**
	 * Metodo constructor que crea DAOTipo
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOTablaTipo() {
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
	 * Metodo que, usando la conexión a la base de datos, saca todos los Tipos de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM TipoS;
	 * @return Arraylist con los Tipos de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Tipo> darTipos() throws SQLException, Exception {
		ArrayList<Tipo> tipos = new ArrayList<Tipo>();

		String sql = "SELECT * FROM Tipo";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long id = rs.getLong("ID");
			String nombre = rs.getString("NOMBRE");
			tipos.add(new Tipo(id, nombre));
		}
		return tipos;
	}


	/**
	 * Metodo que busca el Tipo con el id que entra como parametro.
	 * @param id - id del Tipos a buscar
	 * @return ArrayList con los Tipos encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Tipo> buscarTiposPorNombre(String nombre) throws SQLException, Exception {
		ArrayList<Tipo> tipos = new ArrayList<Tipo>();

		String sql = "SELECT * FROM Tipo WHERE NOMBRE ='" + nombre + "'";;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String nombre2 = rs.getString("NOMBRE");
			Long id = rs.getLong("ID");
			tipos.add(new Tipo(id, nombre2));
		}

		return tipos;
	}
	
	/**
	 * Metodo que busca el Tipo con el id que entra como parametro.
	 * @param name - Id de el Tipo a buscar
	 * @return Tipo encontrado
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public Tipo buscarTipoPorId(Long id) throws SQLException, Exception 
	{
		Tipo tipo = null;

		String sql = "SELECT * FROM Tipo WHERE ID =" + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			String nombre = rs.getString("NOMBRE");
			Long id2 = rs.getLong("ID");
			tipo = new Tipo(id2, nombre);
		}

		return tipo;
	}

	/**
	 * Metodo que agrega el Tipo que entra como parametro a la base de datos.
	 * @param Tipo - el Tipo a agregar. Tipo !=  null
	 * <b> post: </b> se ha agregado el Tipo a la base de datos en la transaction actual. pendiente que el Tipo master
	 * haga commit para que el Tipo baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Tipo a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addTipo(Tipo Tipo) throws SQLException, Exception {

		String sql = "INSERT INTO TIPO VALUES (";
		sql += Tipo.getId() + ",";
		sql += Tipo.getNombre() + ")";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	/**
	 * Metodo que actualiza el Tipo que entra como parametro en la base de datos.
	 * @param Tipo - el Tipo a actualizar. Tipo !=  null
	 * <b> post: </b> se ha actualizado el Tipo en la base de datos en la transaction actual. pendiente que el Tipo master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Tipo.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void updateTipo(Tipo Tipo) throws SQLException, Exception {

		String sql = "UPDATE Tipo SET ";
		sql += "NOMBRE=" + Tipo.getNombre();
		sql += " WHERE ID = " + Tipo.getId();


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	/**
	 * Metodo que elimina el Tipo que entra como parametro en la base de datos.
	 * @param Tipo - el Tipo a borrar. Tipo !=  null
	 * <b> post: </b> se ha borrado el Tipo en la base de datos en la transaction actual. pendiente que el Tipo master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Tipo.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteTipo(Tipo tipo) throws SQLException, Exception {

		String sql = "DELETE FROM Tipo";
		sql += " WHERE ID = " + tipo.getId();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
}
