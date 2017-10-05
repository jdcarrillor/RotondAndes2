package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vos.Menu;
import vos.Pedido;
import vos.Producto;
import vos.Restaurante;
import vos.Video;

public class DAOTablaMenu 
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
		public DAOTablaMenu() {
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
		public  ArrayList<Menu> darMenus() throws SQLException, Exception {
			ArrayList<Menu> menus = new ArrayList<Menu>();

			String sql = "SELECT * FROM MENU";

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				Long id = rs.getLong("ID");
				double costo = rs.getDouble("COSTO");
				double precio = rs.getDouble("PRECIO");
				Long idRestaurante = rs.getLong("ID_RESTAURANTE");
				Long idPedido= rs.getLong("ID_PEDIDO");
				
				
				menus.add(new Menu(id, costo, precio, idRestaurante, idPedido));
				
            
			}
			return menus;
		}
		
		
		
		/**
		 * Metodo que busca el video con el id que entra como parametro.
		 * @param name - Id de el video a buscar
		 * @return Video encontrado
		 * @throws SQLException - Cualquier error que la base de datos arroje.
		 * @throws Exception - Cualquier error que no corresponda a la base de datos
		 */
		public Menu buscarMenuPorId(Long id) throws SQLException, Exception 
		{
			Menu menu= null;

			String sql = "SELECT * FROM MENU WHERE ID =" + id;

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			if(rs.next()) {
				Long id2 = rs.getLong("ID");
				double costo = rs.getDouble("COSTO");
				double precio = rs.getDouble("PRECIO");
				Long idRestaurante = rs.getLong("ID_RESTAURANTE");
				Long idPedido= rs.getLong("ID_PEDIDO");
				
				menu=(new Menu(id2, costo, precio, idRestaurante, idPedido));
				
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
		public void addMenu(Menu menu) throws SQLException, Exception {

			String sql = "INSERT INTO MENU VALUES (";
			sql += menu.getId() + ",";
			sql += menu.getcosto()+ ",";
			sql += menu.getprecio()+ ",";
			sql += menu.getId_restaurante()+ ","; 	
			sql += menu.getId_pedido() + ")";

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
		public void updateMenu(Menu menu) throws SQLException, Exception {

			String sql = "UPDATE MENU SET ";
			sql += "COSTO=" + menu.getcosto()+ ",";
			sql += "PRECIO=" + menu.getprecio()+ ",";
			sql += "ID_RESTAURANTE=" + menu.getId_restaurante()+ ",";
			sql += "ID_PEDIDO=" + menu.getId_pedido();
			sql += " WHERE ID = " + menu.getId();


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
		public void deleteMenu(Menu menu) throws SQLException, Exception {

			String sql = "DELETE FROM MENU";
			sql += " WHERE ID = " + menu	.getId();

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
		}
		
	
		
	
	
}
