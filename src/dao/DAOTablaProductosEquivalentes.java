package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Pedido;
import vos.ProductosEquivalentes;

public class DAOTablaProductosEquivalentes {


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
		public DAOTablaProductosEquivalentes() {
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
		 * Metodo que, usando la conexión a la base de datos, saca todos los Pedidos de la base de datos
		 * <b>SQL Statement:</b> SELECT * FROM PedidoS;
		 * @return Arraylist con los Pedidos de la base de datos.
		 * @throws SQLException - Cualquier error que la base de datos arroje.
		 * @throws Exception - Cualquier error que no corresponda a la base de datos
		 */
		public ArrayList<ProductosEquivalentes> darProductosEquivalentes() throws SQLException, Exception {
			ArrayList<ProductosEquivalentes> prodEqui = new ArrayList<ProductosEquivalentes>();

			String sql = "SELECT * FROM ProductosEquivalentes";

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				Long idProducto1 = rs.getLong("ID_PRODUCTO1");
				Long idProducto2 = rs.getLong("ID_PRODUCTO2");
				
				prodEqui.add(new ProductosEquivalentes(idProducto1, idProducto2));
			}
			return prodEqui;
		}
		
		
		public ArrayList<ProductosEquivalentes> darProductosEquivalentesDeUnProducto(Long idProducto) throws SQLException, Exception {
			ArrayList<ProductosEquivalentes> prodEqui = new ArrayList<ProductosEquivalentes>();

			String sql = "SELECT * FROM ProductosEquivalentes WHERE ID_PRODUCTO1="+ idProducto;

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				Long idProducto1 = rs.getLong("ID_PRODUCTO1");
				Long idProducto2 = rs.getLong("ID_PRODUCTO2");
				
				prodEqui.add(new ProductosEquivalentes(idProducto1, idProducto2));
			}
			return prodEqui;
		}

		
		/**
		 * Metodo que busca el Pedido con el id que entra como parametro.
		 * @param name - Id de el Pedido a buscar
		 * @return Pedido encontrado
		 * @throws SQLException - Cualquier error que la base de datos arroje.
		 * @throws Exception - Cualquier error que no corresponda a la base de datos
		 */
		public ProductosEquivalentes buscarProductoEquiPorId(String idx) throws SQLException, Exception 
		{
			ProductosEquivalentes pedido = null;
			String[] ids = idx.split("-"); 
			String id1S = ids[0];
			String id2S = ids[1];
			Long id1 =Long.parseLong(id1S);
			Long id2 =Long.parseLong(id2S);
			String sql = "SELECT * FROM ProductosEquivalentes WHERE ID_PRODUCTO1 =" + id1+ "AND ID_PRODUCTO2="+id2;

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			if(rs.next()) {
				Long idProducto1 = rs.getLong("ID_PRODUCTO1");
				Long idProducto2 = rs.getLong("ID_PRODUCTO2");
				pedido = new ProductosEquivalentes(idProducto1, idProducto2);
			}

			return pedido;
		}

		/**
		 * Metodo que agrega el Pedido que entra como parametro a la base de datos.
		 * @param Pedido - el Pedido a agregar. Pedido !=  null
		 * <b> post: </b> se ha agregado el Pedido a la base de datos en la transaction actual. pendiente que el Pedido master
		 * haga commit para que el Pedido baje  a la base de datos.
		 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Pedido a la base de datos
		 * @throws Exception - Cualquier error que no corresponda a la base de datos
		 */
		public void addProductoEquiv(ProductosEquivalentes pedido) throws SQLException, Exception {


			
			String sql = "INSERT INTO ProductosEquivalentes VALUES (";
			sql += pedido.getIdProducto1() + ",";
			sql += pedido.getIdProducto2() + ")";

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();

		}
		
		

		/**
		 * Metodo que elimina el Pedido que entra como parametro en la base de datos.
		 * @param Pedido - el Pedido a borrar. Pedido !=  null
		 * <b> post: </b> se ha borrado el Pedido en la base de datos en la transaction actual. pendiente que el Pedido master
		 * haga commit para que los cambios bajen a la base de datos.
		 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Pedido.
		 * @throws Exception - Cualquier error que no corresponda a la base de datos
		 */
		public void deleteProductoEquivalente(ProductosEquivalentes pedido) throws SQLException, Exception {

			String sql = "DELETE FROM ProductosEquivalentes ";
			sql += " WHERE IDPRODUCTO1 = "+ pedido.getIdProducto1()+ "AND IDPRODUCTO2=" +pedido.getIdProducto2();

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
		}

	
}
