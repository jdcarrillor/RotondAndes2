package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Menu;
import vos.Preferencia;


public class DAOTablaPreferencia {

	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;

	/**
	 * Metodo constructor que crea DAOProducto
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOTablaPreferencia() {
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
	 * Metodo que, usando la conexión a la base de datos, saca todos los restaurantes de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM RESTAURANTE;
	 * @return Arraylist con los videos de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public  ArrayList<Preferencia> darPreferencias(Long idcliente) throws SQLException, Exception {
		ArrayList<Preferencia> Preferencias = new ArrayList<Preferencia>();

		String sql = "SELECT * FROM Preferencia WHERE ID_USUARIOCLIENTE= " +idcliente;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long id = rs.getLong("ID");
			double precio = rs.getDouble("PRECIO");
			String categoria = rs.getString("CATEGORIA");
			String zona = rs.getString("ZONA");
			Long idusuario= rs.getLong("ID_USUARIOCLIENTE");
			
			
			Preferencias.add(new Preferencia(id, precio, categoria, zona, idusuario));
			
        
		}
		return Preferencias;
	}
	
	
	
	/**
	 * Metodo que busca el video con el id que entra como parametro.
	 * @param name - Id de el video a buscar
	 * @return Video encontrado
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public Preferencia buscarPreferenciaPorId(Long id) throws SQLException, Exception 
	{
		Preferencia Preferencia= null;

		String sql = "SELECT * FROM Preferencia WHERE ID =" + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			Long id2 = rs.getLong("ID");
			double precio = rs.getDouble("PRECIO");
			String categoria = rs.getString("CATEGORIA");
			String zona = rs.getString("ZONA");
			Long idusuario= rs.getLong("ID_USUARIOCLIENTE");
			
			Preferencia=(new Preferencia(id2, precio, categoria, zona, idusuario));
			
		}

		return Preferencia;
	}
	
	
	/**
	 * Metodo que agrega el video que entra como parametro a la base de datos.
	 * @param video - el video a agregar. video !=  null
	 * <b> post: </b> se ha agregado el video a la base de datos en la transaction actual. pendiente que el video master
	 * haga commit para que el video baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el video a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addPreferencia(Preferencia Preferencia) throws SQLException, Exception {

		String sql = "INSERT INTO Preferencia VALUES (";
		sql += Preferencia.getId() + ",";
		sql += Preferencia.getPrecio()+ ",";
		sql += "'"+Preferencia.getCategoria()+ "',";
		sql += "'"+Preferencia.getZona()+ "',";
		sql += Preferencia.getId_usuariocliente() + ")";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	
	/**
	 * Metodo que actualiza el video que entra como parametro en la base de datos.
	 * @param video - el video a actualizar. video !=  null
	 * <b> post: </b> se ha actualizado el video en la base de datos en la transaction actual. pendiente que el video master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el video.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void updatePreferencia(Preferencia Preferencia) throws SQLException, Exception {

		String sql = "UPDATE Preferencia SET ";
		sql += "PRECIO=" + Preferencia.getPrecio()+ ",";
		sql += "CATEGORIA='" + Preferencia.getCategoria()+ "',";
		sql += "ZONA='" + Preferencia.getZona()+ "',";
		sql += "ID_USUARIOCLIENTE=" + Preferencia.getId_usuariocliente();
		sql += " WHERE ID = " + Preferencia.getId();


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	/**
	 * Metodo que elimina el video que entra como parametro en la base de datos.
	 * @param video - el video a borrar. video !=  null
	 * <b> post: </b> se ha borrado el video en la base de datos en la transaction actual. pendiente que el video master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el video.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deletePreferencia(Preferencia Preferencia) throws SQLException, Exception {

		String sql = "DELETE FROM Preferencia";
		sql += " WHERE ID = " + Preferencia	.getId();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	



}
