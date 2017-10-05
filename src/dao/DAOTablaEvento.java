package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import vos.Evento;
import vos.Menu;

public class DAOTablaEvento
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
		public DAOTablaEvento() {
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
		public  ArrayList<Evento> dareventos() throws SQLException, Exception {
			ArrayList<Evento> eventos = new ArrayList<Evento>();

			String sql = "SELECT * FROM EVENTO";

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				Long id = rs.getLong("ID");
				Date fecha = rs.getDate("FECHA");
				int numComen= rs.getInt("NUM_COMENSALES");
				Long idZona = rs.getLong("ID_ZONA");
				Long idUsuarioCliente= rs.getLong("ID_USUARIOCLIENTE");
				
				
				eventos.add(new Evento(id, fecha, numComen, idZona, idUsuarioCliente));
				
            
			}
			return eventos;
		}
		
		
		
		/**
		 * Metodo que busca el video con el id que entra como parametro.
		 * @param name - Id de el video a buscar
		 * @return Video encontrado
		 * @throws SQLException - Cualquier error que la base de datos arroje.
		 * @throws Exception - Cualquier error que no corresponda a la base de datos
		 */
		public Evento buscarEventoPorId(Long id) throws SQLException, Exception 
		{
			Evento Evento= null;

			String sql = "SELECT * FROM EVENTO WHERE ID =" + id;

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			if(rs.next()) {
				Long id2 = rs.getLong("ID");
				Date fecha = rs.getDate("FECHA");
				int numComen= rs.getInt("NUM_COMENSALES");
				Long idZona = rs.getLong("ID_ZONA");
				Long idUsuarioCliente= rs.getLong("ID_USUARIOCLIENTE");
				
				
				Evento =(new Evento(id2, fecha, numComen, idZona, idUsuarioCliente));
				
			}

			return Evento;
		}
		
		
		/**
		 * Metodo que agrega el video que entra como parametro a la base de datos.
		 * @param video - el video a agregar. video !=  null
		 * <b> post: </b> se ha agregado el video a la base de datos en la transaction actual. pendiente que el video master
		 * haga commit para que el video baje  a la base de datos.
		 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el video a la base de datos
		 * @throws Exception - Cualquier error que no corresponda a la base de datos
		 */
		public void addEvento(Evento Evento) throws SQLException, Exception {

			String sql = "INSERT INTO EVENTO VALUES (";
			sql += Evento.getId() + ",";
			sql += Evento.getFecha()+ ",";
			sql += Evento.getNum_comensales()+ ",";
			sql += Evento.getId_zona()+ ",";
			sql += Evento.getId_usuarioCliente() + ")";

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
		public void updateEvento(Evento Evento) throws SQLException, Exception {

			String sql = "UPDATE EVENTO SET ";
			sql += "FECHA=" + Evento.getFecha()+ ",";
			sql += "NUM_COMENSALES=" + Evento.getNum_comensales()+ ",";
			sql += "ID_ZONA=" + Evento.getId_zona()+ ",";
			sql += "ID_USUARIOCLIENTE=" + Evento.getId_usuarioCliente();
			sql += " WHERE ID = " + Evento.getId();


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
		public void deleteEvento(Evento Evento) throws SQLException, Exception {

			String sql = "DELETE FROM EVENTO";
			sql += " WHERE ID = " + Evento.getId();

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
		}
		
	
	

}
