package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Producto;

public class DAOTablaProducto {


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
	public DAOTablaProducto() {
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
	public ArrayList<Producto> darProductos() throws SQLException, Exception {
		ArrayList<Producto> productos = new ArrayList<Producto>();

		String sql = "SELECT * FROM PRODUCTO";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long id = rs.getLong("ID");
			String nombre = rs.getString("NOMBRE");
			Double costoProduccion = rs.getDouble("COSTOPRODUCCION");
			Double precio = rs.getDouble("PRECIO");
			String descripcion = rs.getString("DESCRIPCION");
			String traduccion = rs.getString("TRADUCCION");
			Double tiempo = rs.getDouble("TIEMPO");
			String categoria = rs.getString("CATEGORIA");
			Integer disponibles = rs.getInt("DISPONIBLES");
			Long menu = rs.getLong("ID_MENU");
			productos.add(new Producto(id, nombre, costoProduccion, precio, tiempo, descripcion, traduccion, categoria, disponibles, menu));
		}
		return productos;
	}


	/**
	 * Metodo que busca el Pedido con el id que entra como parametro.
	 * @param id - id del Pedidos a buscar
	 * @return ArrayList con los Pedidos encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Producto> buscarProductosPorNombre(String nombre) throws SQLException, Exception {
		ArrayList<Producto> productos = new ArrayList<Producto>();

		String sql = "SELECT * FROM PRODUCTO WHERE NOMBRE ='" + nombre + "'";;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String nombre2 = rs.getString("NOMBRE");
			Long id = rs.getLong("ID");
			Double costoProduccion = rs.getDouble("COSTOPRODUCCION");
			Double precio = rs.getDouble("PRECIO");
			String descripcion = rs.getString("DESCRIPCION");
			String traduccion = rs.getString("TRADUCCION");
			Double tiempo = rs.getDouble("TIEMPO");
			String categoria = rs.getString("CATEGORIA");
			Integer disponibles = rs.getInt("DISPONIBLES");
			Long menu = rs.getLong("ID_MENU");
			productos.add(new Producto(id, nombre2, costoProduccion, precio, tiempo, descripcion, traduccion, categoria, disponibles, menu));
	
		}

		return productos;
	}
	
	/**
	 * Metodo que busca el Pedido con el id que entra como parametro.
	 * @param name - Id de el Pedido a buscar
	 * @return Pedido encontrado
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public Producto buscarProductoPorId(Long id) throws SQLException, Exception 
	{
		Producto producto = null;

		String sql = "SELECT * FROM PRODUCTO WHERE ID =" + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			String nombre = rs.getString("NOMBRE");
			Long id2 = rs.getLong("ID");
			Double costoProduccion = rs.getDouble("COSTOPRODUCCION");
			Double precio = rs.getDouble("PRECIO");
			String descripcion = rs.getString("DESCRIPCION");
			String traduccion = rs.getString("TRADUCCION");
			Double tiempo = rs.getDouble("TIEMPO");
			String categoria = rs.getString("CATEGORIA");
			Integer disponibles = rs.getInt("DISPONIBLES");
			Long menu = rs.getLong("ID_MENU");
			producto = new Producto(id2, nombre, costoProduccion, precio, tiempo, descripcion, traduccion, categoria, disponibles, menu);
	
		}

		return producto;
	}

	/**
	 * Metodo que agrega el Pedido que entra como parametro a la base de datos.
	 * @param Pedido - el Pedido a agregar. Pedido !=  null
	 * <b> post: </b> se ha agregado el Pedido a la base de datos en la transaction actual. pendiente que el Pedido master
	 * haga commit para que el Pedido baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Pedido a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addProducto(Producto producto) throws SQLException, Exception {

		String sql = "INSERT INTO PRODUCTO VALUES (";
		sql += producto.getId() + ",";
		sql += "'"+producto.getNombreProducto() + "',";
		sql += producto.getCostoProduccion() + ",";
		sql += "'"+producto.getDescripcion() + "',";
		sql += "'"+producto.getTraduccion() + "',";
		sql += producto.getTiempo() + ",";
		sql += producto.getPrecio() + ",";
		sql += "'"+producto.getCategoria() + "',";
		sql += producto.getDisponibles() + ",";
		sql += producto.getMenu() + ")";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	/**
	 * Metodo que actualiza el Pedido que entra como parametro en la base de datos.
	 * @param Pedido - el Pedido a actualizar. Pedido !=  null
	 * <b> post: </b> se ha actualizado el Pedido en la base de datos en la transaction actual. pendiente que el Pedido master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Pedido.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void updateProducto(Producto producto) throws SQLException, Exception {

		String sql = "UPDATE PRODUCTO SET ";
		sql += "NOMBRE='" + producto.getNombreProducto() + "',";
		sql += "COSTOPRODUCCION=" + producto.getCostoProduccion() + ",";
		sql += "DESCRIPCION='" + producto.getDescripcion() + "',";
		sql += "TRADUCCION='" + producto.getTraduccion() + "',";
		sql += "TIEMPO=" + producto.getTiempo() + ",";
		sql += "PRECIO=" + producto.getPrecio() + ",";
		sql += "CATEGORIA='" + producto.getCategoria() + "',";
		sql += "DISPONIBLES=" + producto.getDisponibles() + ",";
		sql += "ID_MENU=" + producto.getMenu();
		sql += " WHERE ID = " + producto.getId();


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
	public void deleteProducto(Producto producto) throws SQLException, Exception {

		String sql = "DELETE FROM PRODUCTO";
		sql += " WHERE ID = " + producto.getId();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
}
