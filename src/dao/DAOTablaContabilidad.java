package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import vos.Contabilidad;
import vos.Evento;

public class DAOTablaContabilidad {

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
		public DAOTablaContabilidad() {
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
		public  ArrayList<Contabilidad> darContabilidad() throws SQLException, Exception {
			ArrayList<Contabilidad> eventos = new ArrayList<Contabilidad>();

			String sql = "SELECT * FROM CONTABILIDAD";

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				Long id = rs.getLong("ID");
				Long idRestaurante = rs.getLong("ID_RESTAURANTE");
				Long idProducto = rs.getLong("ID_PRODUCTO");
				Long idMenu = rs.getLong("ID_MENU");
				Long idCliente = rs.getLong("ID_CLIENTE");
				Long idUsuario = rs.getLong("ID_USUARIO");
				int unidadesVendidas = rs.getInt("UNIDADESVENDIDAS");
				int ingresosGenerados = rs.getInt("INGRESOSGENERADOS");
				
				
				eventos.add(new Contabilidad(id, idRestaurante, idProducto, idMenu, idCliente, idUsuario, unidadesVendidas, ingresosGenerados));
				
            
			}
			return eventos;
		}
		
		
		/**
		 * Metodo que, usando la conexión a la base de datos, saca todos los restaurantes de la base de datos
		 * <b>SQL Statement:</b> SELECT * FROM RESTAURANTE;
		 * @return Arraylist con los videos de la base de datos.
		 * @throws SQLException - Cualquier error que la base de datos arroje.
		 * @throws Exception - Cualquier error que no corresponda a la base de datos
		 */
		public  ArrayList<Contabilidad> darContabilidadPorRestaurante(Long idRestaurantex) throws SQLException, Exception {
			ArrayList<Contabilidad> eventos = new ArrayList<Contabilidad>();

			String sql = "SELECT * FROM CONTABILIDAD WHERE ID_RESTAURANTE ="+idRestaurantex;

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				Long id = rs.getLong("ID");
				Long idRestaurante = rs.getLong("ID_RESTAURANTE");
				Long idProducto = rs.getLong("ID_PRODUCTO");
				Long idMenu = rs.getLong("ID_MENU");
				Long idCliente = rs.getLong("ID_CLIENTE");
				Long idUsuario = rs.getLong("ID_USUARIO");
				int unidadesVendidas = rs.getInt("UNIDADESVENDIDAS");
				int ingresosGenerados = rs.getInt("INGRESOSGENERADOS");
				
				
				
				
				eventos.add(new Contabilidad(id, idRestaurante, idProducto, idMenu, idCliente, idUsuario, unidadesVendidas, ingresosGenerados));
				
            
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
		public Contabilidad buscarContabilidadPorId(Long id) throws SQLException, Exception 
		{
			Contabilidad Evento= null;

			String sql = "SELECT * FROM CONTABILIDAD WHERE IDEVENTO =" + id;

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			if(rs.next()) {
				Long id2 = rs.getLong("ID");
				Long idRestaurante = rs.getLong("ID_RESTAURANTE");
				Long idProducto = rs.getLong("ID_PRODUCTO");
				Long idMenu = rs.getLong("ID_MENU");
				Long idCliente = rs.getLong("ID_CLIENTE");
				Long idUsuario = rs.getLong("ID_USUARIO");
				int unidadesVendidas = rs.getInt("UNIDADESVENDIDAS");
				int ingresosGenerados = rs.getInt("INGRESOSGENERADOS");
				
				
				Evento =new Contabilidad(id, idRestaurante, idProducto, idMenu, idCliente, idUsuario, unidadesVendidas, ingresosGenerados);
				
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
		public void addContabilidad(Long id, Long idRestaurante , Long idProducto, Long idMenu, Long idCliente, Long idUsuario, int unidadesVendidas, int ingresos) throws SQLException, Exception {
			
			String sql = "INSERT INTO CONTABILIDAD VALUES (";
			sql += id + ",";
			sql += idRestaurante+ ",";
			sql += idProducto+ ",";
			sql += idMenu+",";
			sql += idCliente + ",";
			sql += idUsuario+ ",";
			sql += unidadesVendidas+ ",";
			sql += ingresos+ ")";
			

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
		public void updateContabilidad(Contabilidad Evento) throws SQLException, Exception {

			String sql = "UPDATE CONTABILIDAD SET ";
			sql += "ID_RESTAURANTE=" + Evento.getIdRestaurante()+ ",";
			sql += "ID_PRODUCTO=" + Evento.getIdProducto()+ ",";
			sql += "ID_MENU=" + Evento.getIdMenu()+ ",";
			sql += "ID_CLIENTE=" + Evento.getIdCliente()+ ",";
			sql += "ID_USUARIO=" + Evento.getIdUsuario()+ ",";
			sql += "UNIDADESVENDIDAS=" + Evento.getUnidadesVendidas()+ ",";
			sql += "INGRESOSGENERADOS=" + Evento.getIngresosGenerados();
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
		public void deleteContabilidad(Contabilidad Evento) throws SQLException, Exception {

			String sql = "DELETE FROM Contabilidad";
			sql += " WHERE ID = " + Evento.getId();

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
		}
		
	

}
