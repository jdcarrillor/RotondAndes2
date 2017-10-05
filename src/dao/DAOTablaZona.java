package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Zona;

public class DAOTablaZona 
{
	
	/**
	 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la aplicación
	 * @author Monitores 2017-20
	 */
	

		/**
		 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
		 */
		private  ArrayList<Object> recursos;

		/**
		 * Atributo que genera la conexión a la base de datos
		 */
		private  Connection conn;

		/**
		 * Metodo constructor que crea DAOVideo
		 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
		 */
		public DAOTablaZona() {
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
		public  ArrayList<Zona> darZonas() throws SQLException, Exception {
			ArrayList<Zona> Zonas = new ArrayList<Zona>();

			String sql = "SELECT * FROM ZONA";

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				Long id = rs.getLong("ID");
				String espacio = rs.getString("ESPACIO");
				int capacidad = rs.getInt("CAPACIDAD");
				double apto = rs.getDouble("APTO");
				String condiciones = rs.getString("CONDICIONES");
				
				
				
				Zonas.add(new Zona(id, espacio, capacidad, apto, condiciones));
				
            
			}
			return Zonas;
		}
		
		
		
		/**
		 * Metodo que busca el video con el id que entra como parametro.
		 * @param name - Id de el video a buscar
		 * @return Video encontrado
		 * @throws SQLException - Cualquier error que la base de datos arroje.
		 * @throws Exception - Cualquier error que no corresponda a la base de datos
		 */
		public Zona buscarZonaPorId(Long id) throws SQLException, Exception 
		{
			Zona Zona= null;

			String sql = "SELECT * FROM ZONA WHERE ID =" + id;

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			if(rs.next()) {
				Long id2 = rs.getLong("ID");
				String espacio = rs.getString("ESPACIO");
				int capacidad = rs.getInt("CAPACIDAD");
				double apto = rs.getDouble("APTO");
				String condiciones = rs.getString("CONDICIONES");
				
				
				
				Zona=new Zona(id2, espacio, capacidad, apto, condiciones);
				
				
			}

			return Zona;
		}
		
		
		/**
		 * Metodo que agrega el video que entra como parametro a la base de datos.
		 * @param video - el video a agregar. video !=  null
		 * <b> post: </b> se ha agregado el video a la base de datos en la transaction actual. pendiente que el video master
		 * haga commit para que el video baje  a la base de datos.
		 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el video a la base de datos
		 * @throws Exception - Cualquier error que no corresponda a la base de datos
		 */
		public void addZona(Zona Zona) throws SQLException, Exception {

			String sql = "INSERT INTO ZONA VALUES (";
			sql += Zona.getId() + ",";
			sql += Zona.getespacio()+ ",";
			sql += Zona.getcapacidad()+ ",";
			sql += Zona.getnecesidadesEspeciales()+ ",";
			sql += Zona.getcondiciones() + ")";

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
		public void updateZona(Zona Zona) throws SQLException, Exception {

			String sql = "UPDATE ZONA SET ";
			sql += "ESPACIO=" + Zona.getespacio()+ ",";
			sql += "CAPACIDAD=" + Zona.getcapacidad()+ ",";
			sql += "APTO=" + Zona.getnecesidadesEspeciales()+ ",";
			sql += "CONDICIONES=" + Zona.getcondiciones();
			sql += " WHERE ID = " + Zona.getId();


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
		public void deleteZona(Zona Zona) throws SQLException, Exception {

			String sql = "DELETE FROM ZONA";
			sql += " WHERE ID = " + Zona	.getId();

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
		}

}
