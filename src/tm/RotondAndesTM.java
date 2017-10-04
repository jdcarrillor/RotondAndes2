package tm;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import dao.DAOTablaIngrediente;
import dao.DAOTablaMenu;
import dao.DAOTablaPedido;
import dao.DAOTablaProducto;
import dao.DAOTablaRestaurante;
import dao.DAOTablaTipo;
import dao.DAOTablaUsuario;
import vos.Ingrediente;
import vos.Menu;
import vos.Pedido;
import vos.Producto;
import vos.Restaurante;
import vos.Tipo;
import vos.Usuario;


public class RotondAndesTM 
{
	/**
	 * Atributo estatico que contiene el path relativo del archivo que tiene los datos de la conexion
	 */
	private static final String CONNECTION_DATA_FILE_NAME_REMOTE = "/conexion.properties";
	/**
	 * Atributo estatico que contiene el path absoluto del archivo que tiene los datos de la conexion
	 */
	private  String connectionDataPath;

	/**
	 * Atributo que guarda el usuario que se va a usar para conectarse a la base de datos.
	 */
	private String user;

	/**
	 * Atributo que guarda la clave que se va a usar para conectarse a la base de datos.
	 */
	private String password;

	/**
	 * Atributo que guarda el URL que se va a usar para conectarse a la base de datos.
	 */
	private String url;

	/**
	 * Atributo que guarda el driver que se va a usar para conectarse a la base de datos.
	 */
	private String driver;
	
	/**
	 * conexion a la base de datos
	 */
	private Connection conn;
	

	/**
	 * Metodo constructor de la clase RotondAndesMaster, esta clase modela y contiene cada una de las 
	 * transacciones y la logica de negocios que estas conllevan.
	 * <b>post: </b> Se crea el objeto RotondAndesTM, se inicializa el path absoluto del archivo de conexion y se
	 * inicializa los atributos que se usan par la conexion a la base de datos.
	 * @param contextPathP - path absoluto en el servidor del contexto del deploy actual
	 */

	public RotondAndesTM(String contextPathP) {
		connectionDataPath = contextPathP + CONNECTION_DATA_FILE_NAME_REMOTE;
		initConnectionData();
	}
	
	
	/**
	 * Metodo que  inicializa los atributos que se usan para la conexion a la base de datos.
	 * <b>post: </b> Se han inicializado los atributos que se usan par la conexion a la base de datos.
	 */
	private void initConnectionData() {
		try {
			File arch = new File(this.connectionDataPath);
			Properties prop = new Properties();
			FileInputStream in = new FileInputStream(arch);
			prop.load(in);
			in.close();
			this.url = prop.getProperty("url");
			this.user = prop.getProperty("usuario");
			this.password = prop.getProperty("clave");
			this.driver = prop.getProperty("driver");
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que  retorna la conexion a la base de datos
	 * @return Connection - la conexion a la base de datos
	 * @throws SQLException - Cualquier error que se genere durante la conexion a la base de datos
	 */
	private Connection darConexion() throws SQLException {
		System.out.println("Connecting to: " + url + " With user: " + user);
		return DriverManager.getConnection(url, user, password);
	}

	////////////////////////////////////////
	///////Transacciones////////////////////
	////////////////////////////////////////
	
	/**
	 * Metodo que modela la transaccion que retorna todos los videos de la base de datos.
	 * @return ListaVideos - objeto que modela  un arreglo de videos. este arreglo contiene el resultado de la busqueda
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public List<Restaurante> darRestaurantes() throws Exception {
		List<Restaurante> restaurantes;
		DAOTablaRestaurante daoRestaurante = new DAOTablaRestaurante();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoRestaurante.setConn(conn);
			restaurantes = daoRestaurante.darRestaurantes();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoRestaurante.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return restaurantes;
	}
	
	/**
	 * Metodo que modela la transaccion que retorna todos los videos de la base de datos.
	 * @return ListaVideos - objeto que modela  un arreglo de videos. este arreglo contiene el resultado de la busqueda
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public List<Menu> darMenus() throws Exception {
		List<Menu> menus;
		DAOTablaMenu daoMenu = new DAOTablaMenu();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoMenu.setConn(conn);
			menus = daoMenu.darMenus();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoMenu.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return menus;
	}


	public List<Ingrediente> darIngredientes() throws Exception {
		List<Ingrediente> ingredientes;
		DAOTablaIngrediente daoIngrediente = new DAOTablaIngrediente();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoIngrediente.setConn(conn);
			ingredientes = daoIngrediente.darIngredientes();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoIngrediente.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return ingredientes;
	}


	public Ingrediente buscarIngredientePorId(Long id) throws Exception {
		Ingrediente ingrediente;
		DAOTablaIngrediente daoIngredientes = new DAOTablaIngrediente();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoIngredientes.setConn(conn);
			ingrediente = daoIngredientes.buscarIngredientePorId(id);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoIngredientes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return ingrediente;
	}


	public List<Ingrediente> buscarIngredientesPorName(String nombre) throws Exception {
		List<Ingrediente> ingrediente;
		DAOTablaIngrediente daoIngredientes = new DAOTablaIngrediente();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoIngredientes.setConn(conn);
			ingrediente = daoIngredientes.buscarIngredientesPorNombre(nombre);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoIngredientes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return ingrediente;
	}


	public void addIngrediente(Ingrediente ingrediente) throws Exception {
		DAOTablaIngrediente daoIngredientes = new DAOTablaIngrediente();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoIngredientes.setConn(conn);
			daoIngredientes.addIngrediente(ingrediente);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoIngredientes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}


	public void addIngredientes(List<Ingrediente> ingredientes) throws Exception {
		DAOTablaIngrediente daoIngredientes = new DAOTablaIngrediente();
		try 
		{
			//////transaccion - ACID Example
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoIngredientes.setConn(conn);
			Iterator<Ingrediente> it = ingredientes.iterator();
			while(it.hasNext())
			{
				daoIngredientes.addIngrediente(it.next());
			}
			
			conn.commit();
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			try {
				daoIngredientes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}


	public void updateIngrediente(Ingrediente ingrediente) throws Exception {
		DAOTablaIngrediente daoIngredientes = new DAOTablaIngrediente();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoIngredientes.setConn(conn);
			daoIngredientes.updateIngrediente(ingrediente);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoIngredientes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}


	public void deleteIngrediente(Ingrediente ingrediente) throws Exception {
		DAOTablaIngrediente daoIngredientes = new DAOTablaIngrediente();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoIngredientes.setConn(conn);
			daoIngredientes.deleteIngrediente(ingrediente);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoIngredientes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}



	public List<Pedido> darPedidos() throws Exception {
		List<Pedido> pedidos;
		DAOTablaPedido daoPedido = new DAOTablaPedido();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoPedido.setConn(conn);
			pedidos = daoPedido.darPedidos();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoPedido.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return pedidos;
	}


	public Pedido buscarPedidoPorId(Long id) throws Exception {
		Pedido pedido;
		DAOTablaPedido daoPedidos = new DAOTablaPedido();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoPedidos.setConn(conn);
			pedido = daoPedidos.buscarPedidoPorId(id);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoPedidos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return pedido;
	}


	public void addPedido(Pedido pedido) throws Exception {
		DAOTablaPedido daoPedidos = new DAOTablaPedido();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoPedidos.setConn(conn);
			daoPedidos.addPedido(pedido);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoPedidos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}


	public void addPedidos(List<Pedido> pedidos) throws Exception {
		DAOTablaPedido daoPedidos = new DAOTablaPedido();
		try 
		{
			//////transaccion - ACID Example
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoPedidos.setConn(conn);
			Iterator<Pedido> it = pedidos.iterator();
			while(it.hasNext())
			{
				daoPedidos.addPedido(it.next());
			}
			
			conn.commit();
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			try {
				daoPedidos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}


	public void updatePedido(Pedido pedido) throws Exception {
		DAOTablaPedido daoPedidos = new DAOTablaPedido();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoPedidos.setConn(conn);
			daoPedidos.updatePedido(pedido);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoPedidos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}


	public void deletePedido(Pedido pedido) throws Exception {
		DAOTablaPedido daoPedidos = new DAOTablaPedido();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoPedidos.setConn(conn);
			daoPedidos.deletePedido(pedido);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoPedidos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}


	public List<Producto> darProductos() throws Exception {
		List<Producto> productos;
		DAOTablaProducto daoProducto = new DAOTablaProducto();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProducto.setConn(conn);
			productos = daoProducto.darProductos();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoProducto.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return productos;
	}


	public Producto buscarProductoPorId(Long id) throws Exception {
		Producto producto;
		DAOTablaProducto daoProductos = new DAOTablaProducto();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProductos.setConn(conn);
			producto = daoProductos.buscarProductoPorId(id);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoProductos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return producto;
	}


	public List<Producto> buscarProductosPorName(String nombre) throws Exception {
		List<Producto> producto;
		DAOTablaProducto daoProductos = new DAOTablaProducto();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProductos.setConn(conn);
			producto = daoProductos.buscarProductosPorNombre(nombre);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoProductos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return producto;
	}


	public void addProducto(Producto producto) throws Exception {
		DAOTablaProducto daoProductos = new DAOTablaProducto();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProductos.setConn(conn);
			daoProductos.addProducto(producto);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoProductos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}


	public void addProductos(List<Producto> productos) throws Exception {
		DAOTablaProducto daoProductos = new DAOTablaProducto();
		try 
		{
			//////transaccion - ACID Example
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoProductos.setConn(conn);
			Iterator<Producto> it = productos.iterator();
			while(it.hasNext())
			{
				daoProductos.addProducto(it.next());
			}
			
			conn.commit();
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			try {
				daoProductos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}


	public void updateProducto(Producto producto) throws Exception {
		DAOTablaProducto daoProductos = new DAOTablaProducto();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProductos.setConn(conn);
			daoProductos.updateProducto(producto);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoProductos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}


	public void deleteProducto(Producto Producto) throws Exception {
		DAOTablaProducto daoProductos = new DAOTablaProducto();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProductos.setConn(conn);
			daoProductos.deleteProducto(Producto);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoProductos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	

	public List<Tipo> darTipos() throws Exception {
		List<Tipo> Tipos;
		DAOTablaTipo daoTipo = new DAOTablaTipo();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoTipo.setConn(conn);
			Tipos = daoTipo.darTipos();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoTipo.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Tipos;
	}


	public Tipo buscarTipoPorId(Long id) throws Exception {
		Tipo Tipo;
		DAOTablaTipo daoTipos = new DAOTablaTipo();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoTipos.setConn(conn);
			Tipo = daoTipos.buscarTipoPorId(id);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoTipos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Tipo;
	}


	public List<Tipo> buscarTiposPorName(String nombre) throws Exception {
		List<Tipo> Tipo;
		DAOTablaTipo daoTipos = new DAOTablaTipo();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoTipos.setConn(conn);
			Tipo = daoTipos.buscarTiposPorNombre(nombre);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoTipos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Tipo;
	}


	public void addTipo(Tipo Tipo) throws Exception {
		DAOTablaTipo daoTipos = new DAOTablaTipo();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoTipos.setConn(conn);
			daoTipos.addTipo(Tipo);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoTipos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}


	public void addTipos(List<Tipo> Tipos) throws Exception {
		DAOTablaTipo daoTipos = new DAOTablaTipo();
		try 
		{
			//////transaccion - ACID Example
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoTipos.setConn(conn);
			Iterator<Tipo> it = Tipos.iterator();
			while(it.hasNext())
			{
				daoTipos.addTipo(it.next());
			}
			
			conn.commit();
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			try {
				daoTipos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}


	public void updateTipo(Tipo Tipo) throws Exception {
		DAOTablaTipo daoTipos = new DAOTablaTipo();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoTipos.setConn(conn);
			daoTipos.updateTipo(Tipo);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoTipos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}


	public void deleteTipo(Tipo Tipo) throws Exception {
		DAOTablaTipo daoTipos = new DAOTablaTipo();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoTipos.setConn(conn);
			daoTipos.deleteTipo(Tipo);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoTipos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}


	public List<Usuario> darUsuarios() throws Exception {
		List<Usuario> Usuarios;
		DAOTablaUsuario daoUsuario = new DAOTablaUsuario();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoUsuario.setConn(conn);
			Usuarios = daoUsuario.darUsuarios();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoUsuario.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Usuarios;
	}


	public Usuario buscarUsuarioPorId(Long id) throws Exception {
		Usuario Usuario;
		DAOTablaUsuario daoUsuarios = new DAOTablaUsuario();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoUsuarios.setConn(conn);
			Usuario = daoUsuarios.buscarUsuarioPorId(id);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoUsuarios.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Usuario;
	}


	public List<Usuario> buscarUsuariosPorName(String nombre) throws Exception {
		List<Usuario> Usuario;
		DAOTablaUsuario daoUsuarios = new DAOTablaUsuario();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoUsuarios.setConn(conn);
			Usuario = daoUsuarios.buscarUsuariosPorNombre(nombre);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoUsuarios.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Usuario;
	}


	public void addUsuario(Usuario Usuario) throws Exception {
		DAOTablaUsuario daoUsuarios = new DAOTablaUsuario();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoUsuarios.setConn(conn);
			daoUsuarios.addUsuario(Usuario);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoUsuarios.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}


	public void addUsuarios(List<Usuario> Usuarios) throws Exception {
		DAOTablaUsuario daoUsuarios = new DAOTablaUsuario();
		try 
		{
			//////transaccion - ACID Example
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoUsuarios.setConn(conn);
			Iterator<Usuario> it = Usuarios.iterator();
			while(it.hasNext())
			{
				daoUsuarios.addUsuario(it.next());
			}
			
			conn.commit();
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			try {
				daoUsuarios.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}


	public void updateUsuario(Usuario Usuario) throws Exception {
		DAOTablaUsuario daoUsuarios = new DAOTablaUsuario();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoUsuarios.setConn(conn);
			daoUsuarios.updateUsuario(Usuario);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoUsuarios.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}


	public void deleteUsuario(Usuario Usuario) throws Exception {
		DAOTablaUsuario daoUsuarios = new DAOTablaUsuario();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoUsuarios.setConn(conn);
			daoUsuarios.deleteUsuario(Usuario);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoUsuarios.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	
}
