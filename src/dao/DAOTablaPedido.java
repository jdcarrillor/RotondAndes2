package dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import vos.Pedido;

public class DAOTablaPedido {
	

	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;

	/**
	 * Metodo constructor que crea DAOPedido
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOTablaPedido() {
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
	public ArrayList<Pedido> darPedidos() throws SQLException, Exception {
		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();

		String sql = "SELECT * FROM PEDIDO";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long id = rs.getLong("IDPEDIDO");
			String fecha = "" + rs.getDate("FECHA");
			Long idUsuario = rs.getLong("ID_USUARIO");
			Long idProducto = rs.getLong("ID_PRODUCTO");
			Long idMenu = rs.getLong("ID_MENU");
			pedidos.add(new Pedido(id, fecha, idUsuario, idProducto, idMenu));
		}
		return pedidos;
	}

	
	/**
	 * Metodo que busca el Pedido con el id que entra como parametro.
	 * @param name - Id de el Pedido a buscar
	 * @return Pedido encontrado
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public Pedido buscarPedidoPorId(Long id) throws SQLException, Exception 
	{
		Pedido pedido = null;

		String sql = "SELECT * FROM PEDIDO WHERE IDPEDIDO =" + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			Long id2 = rs.getLong("IDPEDIDO");
			String fecha = "" + rs.getDate("FECHA");
			Long idUsuario = rs.getLong("ID_USUARIO");
			Long idProducto = rs.getLong("ID_PRODUCTO");
			Long idMenu = rs.getLong("ID_MENU");
			pedido = new Pedido(id2, fecha, idUsuario, idProducto, idMenu);
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
	public void addPedido(Pedido pedido) throws SQLException, Exception {


		String[] fecha = pedido.getfecha().split("-");
		String dia = fecha[0];
		String mes = fecha[1];
		String anio = fecha[2];
		String sql = "INSERT INTO PEDIDO VALUES (";
		sql += pedido.getId() + ",";
		sql +="'"+ anio+"-"+mes+"-"+dia+"'"+  ",";
		sql += pedido.getIdUsuario() + ",";
		sql += pedido.getIdProducto() + ",";
		sql += pedido.getIdMenu() + ")";

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
	public void updatePedido(Pedido pedido) throws SQLException, Exception {
		String[] fecha = pedido.getfecha().split("-");
		String dia = fecha[0];
		String mes = fecha[1];
		String anio = fecha[2];
		
		String sql = "UPDATE PEDIDO SET ";
		sql += "FECHA='" + anio+"-"+mes+"-"+dia+"'"+",";
		sql += "ID_USUARIO="+ pedido.getIdUsuario()+",";
		sql += "ID_PRODUCTO=" + pedido.getIdProducto()+",";
		sql += "ID_MENU=" + pedido.getIdMenu();
		sql += " WHERE IDPEDIDO = " + pedido.getId();


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
	public void deletePedido(Pedido pedido) throws SQLException, Exception {

		String sql = "DELETE FROM PEDIDO";
		sql += " WHERE IDPEDIDO = " + pedido.getId();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

}
