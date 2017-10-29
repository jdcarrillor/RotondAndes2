package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Menu;
import vos.Mesa;
import vos.PedidoMesa;

public class DAOTablaPedidoMesa {

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
		public DAOTablaPedidoMesa() {
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
		public  ArrayList<PedidoMesa> darPedidoMesas() throws SQLException, Exception {
			ArrayList<PedidoMesa> mesas= new ArrayList<PedidoMesa>();

			String sql = "SELECT * FROM PedidosMesa";

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				Long id = rs.getLong("ID");
				Long idMesa = rs.getLong("ID_MESA");
				Long idProducto = rs.getLong("ID_PRODUCTO");
				Long idMenu  = rs.getLong("ID_MENU");
				Long idCliente= rs.getLong("ID_CLIENTE");
				
				
				
				mesas.add(new PedidoMesa(id, idMesa, idProducto, idMenu, idCliente));
				
            
			}
			return mesas;
		}
		
		
		
		/**
		 * Metodo que busca el video con el id que entra como parametro.
		 * @param name - Id de el video a buscar
		 * @return Video encontrado
		 * @throws SQLException - Cualquier error que la base de datos arroje.
		 * @throws Exception - Cualquier error que no corresponda a la base de datos
		 */
		public PedidoMesa buscarPedidoMesaPorId(Long id) throws SQLException, Exception 
		{
			PedidoMesa menu= null;

			String sql = "SELECT * FROM PEDIDOSMESA WHERE ID=" + id;

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			if(rs.next()) {
				Long id2 = rs.getLong("ID");
				Long idMesa = rs.getLong("ID_MESA");
				Long idProducto = rs.getLong("ID_PRODUCTO");
				Long idMenu  = rs.getLong("ID_MENU");
				Long idCliente= rs.getLong("ID_CLIENTE");
				
				menu=(new PedidoMesa(id2, idMesa, idProducto, idMenu, idCliente));
				
			}

			return menu;
		}
		
		
		/**
		 * Metodo que agrega el video que entra como parametro a la base de datos.
		 * @param video - el video a agregar. video !=  null
		 * <b> post: </b> se ha agregado el video a la base de datos en la transaction actual. pendiente que el video master
		 * haga commit para que el video baje  a la base de datos.
		 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el video a la base de datos
		 * @throws Exception - Cualquier error que no corresponda a la base de datos
		 */
		public void addPedidosMesa(PedidoMesa menu) throws SQLException, Exception {

			String sql = "INSERT INTO PedidosMesa VALUES (";
			sql += menu.getId() + ",";
			sql += menu.getIdMesa()+ ",";
			sql += menu.getIdProducto()+ ",";
			sql += menu.getIdMenu()+ ","; 
			sql += menu.getIdCliente() + ")";

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
		public void deletePedidoMesa(PedidoMesa pedidoMesa) throws SQLException, Exception {

			String sql = "DELETE FROM PedidosMesa";
			sql += " WHERE ID = " + pedidoMesa.getId();

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
		}
		
		
}
