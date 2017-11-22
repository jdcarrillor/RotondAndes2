package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sun.glass.ui.Pixels.Format;

import vos.Pedido;
import vos.PedidoProducto;
import vos.PedidoRestaurante;
import vos.Producto;

public class DAOTablaPedidoProducto {



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
	public DAOTablaPedidoProducto() {
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
	public ArrayList<PedidoProducto> darProductosPedido() throws SQLException, Exception {
		ArrayList<PedidoProducto> productos = new ArrayList<PedidoProducto>();

		String sql = "SELECT * FROM PEDIDOPRODUCTO";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long idProducto = rs.getLong("ID_PRODUCTO");
			Long idPedido = rs.getLong("ID_PEDIDO");
			productos.add(new PedidoProducto(idProducto, idPedido));
		}
		return productos;
	}

	public ArrayList<PedidoProducto> darProductosVendidos(Long idProducto) throws SQLException, Exception {
		ArrayList<PedidoProducto> productos = new ArrayList<PedidoProducto>();

		String sql = "SELECT * FROM PEDIDOPRODUCTO WHERE ID_PRODUCTO="+idProducto;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long idProductox = rs.getLong("ID_PRODUCTO");
			Long idPedido = rs.getLong("ID_PEDIDO");
			productos.add(new PedidoProducto(idProductox, idPedido));
		}
		return productos;
	}



	public ArrayList<PedidoProducto> darProductosMasPedido() throws SQLException, Exception {
		ArrayList<PedidoProducto> productos = new ArrayList<PedidoProducto>();

		String sql = "SELECT numeroRepetidas, ID_PRODUCTO FROM( SELECT MAX (NUMERO) AS numeroRepetidas, ID_PRODUCTO FROM( SELECT COUNT(*) AS NUMERO, ID_PRODUCTO FROM PEDIDOPRODUCTO group by ID_PRODUCTO) group by ID_PRODUCTO) WHERE numeroRepetidas = (SELECT MAX (NUMERO) AS numeroRepetidas FROM( SELECT COUNT(*) AS NUMERO, ID_PRODUCTO FROM PEDIDOPRODUCTO group by ID_PRODUCTO) )";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long idProducto = rs.getLong("ID_PRODUCTO");
			int numeroPedidos = rs.getInt("numeroRepetidas");
			productos.add(new PedidoProducto(idProducto, numeroPedidos));
		}
		return productos;
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


	public void addPedidoProduc(Long idPedido, Long idProducto) throws SQLException, Exception {

		String sql = "INSERT INTO PEDIDOPRODUCTO VALUES (";
		sql += idProducto + ",";
		sql += idPedido + ")";

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

	/**
	 * Metodo que, usando la conexión a la base de datos, saca todos los Pedidos de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM PedidoS;
	 * @return Arraylist con los Pedidos de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Pedido> darProductosPedidoFecha() throws SQLException, Exception {
		ArrayList<Pedido> productos = new ArrayList<Pedido>();

		String sql = "SELECT * FROM (PEDIDOPRODUCTO INNER JOIN PEDIDO ON PEDIDOPRODUCTO.ID_PEDIDO=PEDIDO.IDPEDIDO)";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long idProducto = rs.getLong("ID_PRODUCTO");
			Long idPedido = rs.getLong("ID_PEDIDO");
			String fecha = "" + rs.getDate("FECHA");
			productos.add(new Pedido(idPedido,fecha,null ,idProducto,null));
		}
		return productos;
	}

	public ArrayList<PedidoRestaurante> darRestaurantesPedidoFecha() throws SQLException, Exception {
		ArrayList<PedidoRestaurante> productos = new ArrayList<PedidoRestaurante>();

		String sql = "SELECT *FROM((SELECT * FROM (PRODUCTO INNER JOIN PEDIDOPRODUCTO ON PRODUCTO.IDPRODUCTO=PEDIDOPRODUCTO.ID_PRODUCTO))s INNER JOIN PEDIDO ON s.ID_PEDIDO=PEDIDO.IDPEDIDO)";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String fecha = "" + rs.getDate("FECHA");
			Long idRestaurante = rs.getLong("ID_RESTAURANTE");
			productos.add(new PedidoRestaurante(fecha,idRestaurante));
		}
		return productos;
	}
	
	





			/**
			 * Metodo que agrega el Pedido que entra como parametro a la base de datos.
			 * @param Pedido - el Pedido a agregar. Pedido !=  null
			 * <b> post: </b> se ha agregado el Pedido a la base de datos en la transaction actual. pendiente que el Pedido master
			 * haga commit para que el Pedido baje  a la base de datos.
			 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Pedido a la base de datos
			 * @throws Exception - Cualquier error que no corresponda a la base de datos
			 */
			public void createTabla1(List<Pedido> listax) throws SQLException, Exception {

				String sql = "CREATE TABLE TABLE1(IDPEDIDO NUMBER(*, 0) NOT NULL , ID_PRODUCTO NUMBER)";
				System.out.println("/////////////////////////////////////SYS3");

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();
				

			}


			/**
			 * Metodo que agrega el Pedido que entra como parametro a la base de datos.
			 * @param Pedido - el Pedido a agregar. Pedido !=  null
			 * <b> post: </b> se ha agregado el Pedido a la base de datos en la transaction actual. pendiente que el Pedido master
			 * haga commit para que el Pedido baje  a la base de datos.
			 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Pedido a la base de datos
			 * @throws Exception - Cualquier error que no corresponda a la base de datos
			 */
			public void insertTabla1(List<Pedido> listax) throws SQLException, Exception {

				for (int i = 0; i < listax.size(); i++) {

					Pedido actual = listax.get(i);
					
					System.out.println("/////////////////////////////////////FECHA"+ actual.getfecha());
					System.out.println("/////////////////////////////////////SYS5");
					String sql = "INSERT INTO TABLE1 VALUES (";
					sql += actual.getId() + ",";
					sql += actual.getIdProducto() + ")";
					System.out.println("/////////////////////////////////////SYS6");
					
					System.out.println("/////////////////////////////////////"+actual.getId());
					System.out.println("/////////////////////////////////////"+actual.getIdUsuario());
					System.out.println("/////////////////////////////////////"+actual.getIdMenu());
					System.out.println("/////////////////////////////////////"+actual.getIdProducto());

					PreparedStatement prepStmt = conn.prepareStatement(sql);
					recursos.add(prepStmt);
					prepStmt.executeQuery();

				}
				

			}


			/**
			 * Metodo que agrega el Pedido que entra como parametro a la base de datos.
			 * @param Pedido - el Pedido a agregar. Pedido !=  null
			 * <b> post: </b> se ha agregado el Pedido a la base de datos en la transaction actual. pendiente que el Pedido master
			 * haga commit para que el Pedido baje  a la base de datos.
			 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Pedido a la base de datos
			 * @throws Exception - Cualquier error que no corresponda a la base de datos
			 */
			public List<Pedido> maxTabla1() throws SQLException, Exception {
				List<Pedido> masPedidos = new ArrayList<Pedido>();
				System.out.println("/////////////////////////////////////SYS8");
				String sql = "SELECT numeroRepetidas, ID_PRODUCTO FROM( SELECT MAX (NUMERO) AS numeroRepetidas, ID_PRODUCTO FROM( SELECT COUNT(*) AS NUMERO, ID_PRODUCTO FROM TABLE1 group by ID_PRODUCTO) group by ID_PRODUCTO) WHERE numeroRepetidas = (SELECT MAX (NUMERO) AS numeroRepetidas FROM( SELECT COUNT(*) AS NUMERO, ID_PRODUCTO FROM TABLE1 group by ID_PRODUCTO))";


				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				while (rs.next()) {
					Long idProducto = rs.getLong("ID_PRODUCTO");
					int numeroPedidos = rs.getInt("numeroRepetidas");
					masPedidos.add(new Pedido(idProducto, numeroPedidos));
				}
				System.out.println("/////////////////////////////////////SYS9");
				return masPedidos;

			}

			/**
			 * Metodo que agrega el Pedido que entra como parametro a la base de datos.
			 * @param Pedido - el Pedido a agregar. Pedido !=  null
			 * <b> post: </b> se ha agregado el Pedido a la base de datos en la transaction actual. pendiente que el Pedido master
			 * haga commit para que el Pedido baje  a la base de datos.
			 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Pedido a la base de datos
			 * @throws Exception - Cualquier error que no corresponda a la base de datos
			 */
			public List<Pedido>  minTabla1() throws SQLException, Exception {

				List<Pedido> masPedidos = new ArrayList<Pedido>();
				System.out.println("/////////////////////////////////////SYS11");
				String sql = "SELECT numeroRepetidas, ID_PRODUCTO FROM( SELECT MIN (NUMERO) AS numeroRepetidas, ID_PRODUCTO FROM( SELECT COUNT(*) AS NUMERO, ID_PRODUCTO FROM TABLE1 group by ID_PRODUCTO) group by ID_PRODUCTO) WHERE numeroRepetidas = (SELECT MIN (NUMERO) AS numeroRepetidas FROM( SELECT COUNT(*) AS NUMERO, ID_PRODUCTO FROM TABLE1 group by ID_PRODUCTO))";


				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				while (rs.next()) {
					Long idProducto = rs.getLong("ID_PRODUCTO");
					int numeroPedidos = rs.getInt("numeroRepetidas");
					masPedidos.add(new Pedido(idProducto, numeroPedidos));
				}
				System.out.println("/////////////////////////////////////SYS12");
				return masPedidos;
				


			}


			/**
			 * Metodo que agrega el Pedido que entra como parametro a la base de datos.
			 * @param Pedido - el Pedido a agregar. Pedido !=  null
			 * <b> post: </b> se ha agregado el Pedido a la base de datos en la transaction actual. pendiente que el Pedido master
			 * haga commit para que el Pedido baje  a la base de datos.
			 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Pedido a la base de datos
			 * @throws Exception - Cualquier error que no corresponda a la base de datos
			 */
			public void dropTabla1() throws SQLException, Exception {

				String sql = "drop table ISIS2304B011720.TABLE1";
				System.out.println("/////////////////////////////////////SYS14");

				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();
				System.out.println("/////////////////////////////////////SYS15");

			}
			
			
			
			/**
			 * Metodo que agrega el Pedido que entra como parametro a la base de datos.
			 * @param Pedido - el Pedido a agregar. Pedido !=  null
			 * <b> post: </b> se ha agregado el Pedido a la base de datos en la transaction actual. pendiente que el Pedido master
			 * haga commit para que el Pedido baje  a la base de datos.
			 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Pedido a la base de datos
			 * @throws Exception - Cualquier error que no corresponda a la base de datos
			 */
			public void createTabla2(List<PedidoRestaurante> listax) throws SQLException, Exception {

				String sql = "CREATE TABLE TABLE2(FECHA DATE, ID_RESTAURANTE NUMBER )";


				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();
				insertTabla2(listax);

			}


			/**
			 * Metodo que agrega el Pedido que entra como parametro a la base de datos.
			 * @param Pedido - el Pedido a agregar. Pedido !=  null
			 * <b> post: </b> se ha agregado el Pedido a la base de datos en la transaction actual. pendiente que el Pedido master
			 * haga commit para que el Pedido baje  a la base de datos.
			 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Pedido a la base de datos
			 * @throws Exception - Cualquier error que no corresponda a la base de datos
			 */
			public void insertTabla2(List<PedidoRestaurante> listax) throws SQLException, Exception {

				for (int i = 0; i < listax.size(); i++) {

					PedidoRestaurante actual = listax.get(i);
					String[] fecha = actual.getFecha().split("-");
					String dia = fecha[0];
					String mes = fecha[1];
					String anio = fecha[2];
					String sql = "INSERT INTO TABLE2 VALUES (";
					sql +="'"+ anio+"-"+mes+"-"+dia+"'"+  ",";
					sql += actual.getIdRestaurante() + ")";

					PreparedStatement prepStmt = conn.prepareStatement(sql);
					recursos.add(prepStmt);
					prepStmt.executeQuery();

				}

			}


			/**
			 * Metodo que agrega el Pedido que entra como parametro a la base de datos.
			 * @param Pedido - el Pedido a agregar. Pedido !=  null
			 * <b> post: </b> se ha agregado el Pedido a la base de datos en la transaction actual. pendiente que el Pedido master
			 * haga commit para que el Pedido baje  a la base de datos.
			 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Pedido a la base de datos
			 * @throws Exception - Cualquier error que no corresponda a la base de datos
			 */
			public List<PedidoRestaurante> maxTabla2() throws SQLException, Exception {
				List<PedidoRestaurante> masPedidos = new ArrayList<PedidoRestaurante>();
				String sql = "SELECT numeroRepetidas, ID_RESTAURANTE FROM( SELECT MAX (NUMERO) AS numeroRepetidas, ID_RESTAURANTE FROM( SELECT COUNT(*) AS NUMERO, ID_RESTAURANTE FROM TABLE2 group by ID_RESTAURANTE) group by ID_RESTAURANTE) WHERE numeroRepetidas = (SELECT MAX (NUMERO) AS numeroRepetidas FROM( SELECT COUNT(*) AS NUMERO, ID_RESTAURANTE FROM TABLE2 group by ID_RESTAURANTE))";


				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				while (rs.next()) {
					Long idProducto = rs.getLong("ID_RESTAURANTE");
					int numeroPedidos = rs.getInt("numeroRepetidas");
					masPedidos.add(new PedidoRestaurante(idProducto, numeroPedidos));
				}
				return masPedidos;

			}

			/**
			 * Metodo que agrega el Pedido que entra como parametro a la base de datos.
			 * @param Pedido - el Pedido a agregar. Pedido !=  null
			 * <b> post: </b> se ha agregado el Pedido a la base de datos en la transaction actual. pendiente que el Pedido master
			 * haga commit para que el Pedido baje  a la base de datos.
			 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Pedido a la base de datos
			 * @throws Exception - Cualquier error que no corresponda a la base de datos
			 */
			public List<PedidoRestaurante>  minTabla2() throws SQLException, Exception {

				List<PedidoRestaurante> masPedidos = new ArrayList<PedidoRestaurante>();
				String sql = "SELECT numeroRepetidas, ID_RESTAURANTE FROM( SELECT MIN (NUMERO) AS numeroRepetidas, ID_RESTAURANTE FROM( SELECT COUNT(*) AS NUMERO, ID_RESTAURANTE FROM TABLE2 group by ID_RESTAURANTE) group by ID_RESTAURANTE) WHERE numeroRepetidas = (SELECT MIN (NUMERO) AS numeroRepetidas FROM( SELECT COUNT(*) AS NUMERO, ID_RESTAURANTE FROM TABLE2 group by ID_RESTAURANTE))";


				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();

				while (rs.next()) {
					Long idProducto = rs.getLong("ID_RESTAURANTE");
					int numeroPedidos = rs.getInt("numeroRepetidas");
					masPedidos.add(new PedidoRestaurante(idProducto, numeroPedidos));
				}
				return masPedidos;


			}


			/**
			 * Metodo que agrega el Pedido que entra como parametro a la base de datos.
			 * @param Pedido - el Pedido a agregar. Pedido !=  null
			 * <b> post: </b> se ha agregado el Pedido a la base de datos en la transaction actual. pendiente que el Pedido master
			 * haga commit para que el Pedido baje  a la base de datos.
			 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Pedido a la base de datos
			 * @throws Exception - Cualquier error que no corresponda a la base de datos
			 */
			public void dropTabla2() throws SQLException, Exception {

				String sql = "drop table ISIS2304B011720.TABLE2";


				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();

			}


			public static void main(String[] argv) throws Exception
			{
				Date fechap = new Date();
				String fechaS= "2010-10-10";
				String[]arr = fechaS.split("-");
				String anios = arr[0];
				String mess = arr[1];
				String dias = arr[2];
				int anioxx = Integer.parseInt(anios);
				int mesxx = Integer.parseInt(mess);
				int diaxx = Integer.parseInt(dias);
				System.out.println(anioxx);
				System.out.println(mesxx);
				System.out.println(diaxx);
				fechap.setDate(diaxx);
				fechap.setMonth(mesxx);
				fechap.setYear(anioxx);
				
				System.out.println(fechap.getDate());
				SimpleDateFormat cc = new SimpleDateFormat("YY/MM/dd");
				String fechaB= "2010/10/10";
				Date fechatt = cc.parse(fechaB);
				System.out.println(fechap);
				System.out.println(fechap.getYear());
				System.out.println(fechap.getMonth());
				System.out.println(fechap.getDate());
				System.out.println("////////////////////////////////////////");
				System.out.println(fechatt);
				SimpleDateFormat ff=new SimpleDateFormat("MM/dd/YY");
				String fecha =ff.format(fechap);
				String[] fechax= fecha.split("/");
				String dia =fechax[0];
				int diax= Integer.parseInt(dia);
				String mes =fechax[1];
				String anio =fechax[2];
				int mesx= Integer.parseInt(mes);
				int aniox= Integer.parseInt(anio);
				Date fechadef = new Date();
				fechadef.setDate(diax);
				fechadef.setMonth(mesx);;
				fechadef.setYear(aniox);

				SimpleDateFormat formatter = new SimpleDateFormat("EEEE"); 
				String s = formatter.format(fechap);
				System.out.println(s);
				System.out.println(fechap);
				System.out.println(fecha);
				System.out.println(fechadef);
				System.out.println(dia);
				System.out.println(mes);
				System.out.println(anio);

			}
}
		