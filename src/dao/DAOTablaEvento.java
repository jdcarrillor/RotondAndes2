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
				Long id = rs.getLong("IDEVENTO");
				Date fecha = rs.getDate("FECHAEVENTO");
				int numComen= rs.getInt("NUM_COMENSALES");
				Long idZona = rs.getLong("ID_ZONAEVENTO");
				Long idUsuarioCliente= rs.getLong("ID_USUARIOCLIENTE");
				String fechax = fecha.toString();
				
				
				eventos.add(new Evento(id, fechax, numComen, idZona, idUsuarioCliente));
				
            
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

			String sql = "SELECT * FROM EVENTO WHERE IDEVENTO =" + id;

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			if(rs.next()) {
				Long id2 = rs.getLong("IDEVENTO");
				Date fecha = rs.getDate("FECHAEVENTO");
				int numComen= rs.getInt("NUM_COMENSALES");
				Long idZona = rs.getLong("ID_ZONAEVENTO");
				Long idUsuarioCliente= rs.getLong("ID_USUARIOCLIENTE");
				String fechax = fecha.toString();
				
				
				Evento =(new Evento(id2, fechax, numComen, idZona, idUsuarioCliente));
				
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
			String[] fecha = Evento.getFecha().split("-");
			String dia = fecha[0];
			String mes = fecha[1];
			String anio = fecha[2];
			String sql = "INSERT INTO EVENTO VALUES (";
			sql +="'"+ anio+"-"+mes+"-"+dia+"'"+  ",";
			sql += Evento.getId() + ",";
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
			sql += "FECHAEVENTO='" + Evento.getFecha()+"'"+ ",";
			sql += "NUM_COMENSALES=" + Evento.getNum_comensales()+ ",";
			sql += "ID_ZONAEVENTO=" + Evento.getId_zona()+ ",";
			sql += "ID_USUARIOCLIENTE=" + Evento.getId_usuarioCliente();
			sql += " WHERE IDEVENTO = " + Evento.getId();


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
			sql += " WHERE IDEVENTO = " + Evento.getId();

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
		}
		
	
	

}
