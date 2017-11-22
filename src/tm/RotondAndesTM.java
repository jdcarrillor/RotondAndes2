package tm;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import dao.DAOTablaAdministrador;
import dao.DAOTablaCliente;
import dao.DAOTablaContabilidad;
import dao.DAOTablaEvento;
import dao.DAOTablaIngrediente;

import dao.DAOTablaMenu;
import dao.DAOTablaMesa;
import dao.DAOTablaPedido;
import dao.DAOTablaPedidoMesa;
import dao.DAOTablaPedidoProducto;
import dao.DAOTablaPreferencia;
import dao.DAOTablaProducto;
import dao.DAOTablaProductosEquivalentes;
import dao.DAOTablaRestaurante;

import dao.DAOTablaZona;
import dao.DAOTablaTipo;
import vos.Contabilidad;
import vos.Evento;
import vos.Funcionamiento;
import dao.DAOTablaUsuario;

import vos.Ingrediente;
import vos.Menu;
import vos.Mesa;
import vos.Pedido;
import vos.PedidoMesa;
import vos.PedidoProducto;
import vos.PedidoRestaurante;
import vos.Preferencia;
import vos.Producto;
import vos.ProductosEquivalentes;
import vos.Restaurante;

import vos.Video;
import vos.Zona;
import vos.Tipo;
import vos.Usuario;
import vos.UsuarioAdministrador;
import vos.UsuarioCliente;



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
	
	
	
	public List<PedidoProducto> darProductosMasPedidos() throws Exception {
		List<PedidoProducto> restaurantes;
		DAOTablaPedidoProducto daoRestaurante = new DAOTablaPedidoProducto();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoRestaurante.setConn(conn);
			restaurantes = daoRestaurante.darProductosMasPedido();

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
	public List<Restaurante> darRestaurantes() throws Exception {
		List<Restaurante> restaurantes;
		DAOTablaRestaurante daoRestaurante = new DAOTablaRestaurante();
		try 
		{
			
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
	 * Metodo que modela la transaccion que busca el video en la base de datos con el id que entra como parametro.
	 * @param name - Id del video a buscar. name != null
	 * @return Video - Resultado de la busqueda
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public Restaurante buscarRestaurantePorId(Long id) throws Exception {
		Restaurante Restaurante;
		DAOTablaRestaurante daoRestaurantes = new DAOTablaRestaurante();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoRestaurantes.setConn(conn);
			Restaurante = daoRestaurantes.buscarRestaurantePorId(id);

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
				daoRestaurantes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Restaurante;
	}
	
	/**
	 * Metodo que modela la transaccion que agrega un solo video a la base de datos.
	 * <b> post: </b> se ha agregado el video que entra como parametro
	 * @param video - el video a agregar. video != null
	 * @throws Exception - cualquier error que se genere agregando el video
	 */
	public void addRestaurante(Restaurante Restaurante) throws Exception {
		DAOTablaRestaurante daoRestaurantes = new DAOTablaRestaurante();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoRestaurantes.setConn(conn);
			daoRestaurantes.addRestaurante(Restaurante);
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
				daoRestaurantes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	
	/**
	 * Metodo que modela la transaccion que agrega los videos que entran como parametro a la base de datos.
	 * <b> post: </b> se han agregado los videos que entran como parametro
	 * @param videos - objeto que modela una lista de videos y se estos se pretenden agregar. videos != null
	 * @throws Exception - cualquier error que se genera agregando los videos
	 */
	public void addRestaurantes(List<Restaurante> Restaurantes) throws Exception {
		DAOTablaRestaurante daoRestaurantes = new DAOTablaRestaurante();
		try 
		{
			//////transaccion - ACID Example
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoRestaurantes.setConn(conn);
			Iterator<Restaurante> it = Restaurantes.iterator();
			while(it.hasNext())
			{
				daoRestaurantes.addRestaurante(it.next());
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
				daoRestaurantes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	
	/**
	 * Metodo que modela la transaccion que actualiza el video que entra como parametro a la base de datos.
	 * <b> post: </b> se ha actualizado el video que entra como parametro
	 * @param video - Video a actualizar. video != null
	 * @throws Exception - cualquier error que se genera actualizando los videos
	 */
	public void updateRestaurante(Restaurante Restaurante) throws Exception {
		DAOTablaRestaurante daoRestaurantes= new DAOTablaRestaurante();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoRestaurantes.setConn(conn);
			daoRestaurantes.updateRestaurante(Restaurante);

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
				daoRestaurantes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * Metodo que modela la transaccion que elimina el video que entra como parametro a la base de datos.
	 * <b> post: </b> se ha eliminado el video que entra como parametro
	 * @param video - Video a eliminar. video != null
	 * @throws Exception - cualquier error que se genera actualizando los videos
	 */
	public void deleteRestaurante(Restaurante Restaurante) throws Exception {
		DAOTablaRestaurante daoRestaurantes = new DAOTablaRestaurante();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoRestaurantes.setConn(conn);
			daoRestaurantes.deleteRestaurante(Restaurante);

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
				daoRestaurantes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	
	
	public List<PedidoProducto> darPedidoProducto() throws Exception {
		List<PedidoProducto> menus;
		DAOTablaPedidoProducto daoMenu = new DAOTablaPedidoProducto();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoMenu.setConn(conn);
			menus = daoMenu.darProductosPedido();

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
	
	
////////////////////////////////////////
///////MENU////////////////////
////////////////////////////////////////
	
	
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
	
	/**
	 * Metodo que modela la transaccion que busca el video en la base de datos con el id que entra como parametro.
	 * @param name - Id del video a buscar. name != null
	 * @return Video - Resultado de la busqueda
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public Menu buscarMenuPorId(Long id) throws Exception {
		Menu menu;
		DAOTablaMenu daoMenus = new DAOTablaMenu();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoMenus.setConn(conn);
			menu = daoMenus.buscarMenuPorId(id);

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
				daoMenus.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return menu;
	}
	
	/**
	 * Metodo que modela la transaccion que agrega un solo video a la base de datos.
	 * <b> post: </b> se ha agregado el video que entra como parametro
	 * @param video - el video a agregar. video != null
	 * @throws Exception - cualquier error que se genere agregando el video
	 */
	public void addMenu(Menu menu) throws Exception {
		DAOTablaMenu daoMenus = new DAOTablaMenu();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoMenus.setConn(conn);
			daoMenus.addMenu(menu);
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
				daoMenus.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	
	/**
	 * Metodo que modela la transaccion que agrega los videos que entran como parametro a la base de datos.
	 * <b> post: </b> se han agregado los videos que entran como parametro
	 * @param videos - objeto que modela una lista de videos y se estos se pretenden agregar. videos != null
	 * @throws Exception - cualquier error que se genera agregando los videos
	 */
	public void addMenus(List<Menu> menus) throws Exception {
		DAOTablaMenu daoMenus = new DAOTablaMenu();
		try 
		{
			//////transaccion - ACID Example
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoMenus.setConn(conn);
			Iterator<Menu> it = menus.iterator();
			while(it.hasNext())
			{
				daoMenus.addMenu(it.next());
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
				daoMenus.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	
	/**
	 * Metodo que modela la transaccion que actualiza el video que entra como parametro a la base de datos.
	 * <b> post: </b> se ha actualizado el video que entra como parametro
	 * @param video - Video a actualizar. video != null
	 * @throws Exception - cualquier error que se genera actualizando los videos
	 */
	public void updateMenu(Menu menu) throws Exception {
		DAOTablaMenu daoMenus= new DAOTablaMenu();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoMenus.setConn(conn);
			daoMenus.updateMenu(menu);

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
				daoMenus.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	public void updateDisponilbes(Menu menu) throws Exception {
		DAOTablaMenu daoMenus= new DAOTablaMenu();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoMenus.setConn(conn);
			daoMenus.updateDisponilbes(menu);

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
				daoMenus.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * Metodo que modela la transaccion que elimina el video que entra como parametro a la base de datos.
	 * <b> post: </b> se ha eliminado el video que entra como parametro
	 * @param video - Video a eliminar. video != null
	 * @throws Exception - cualquier error que se genera actualizando los videos
	 */
	public void deleteMenu(Menu menu) throws Exception {
		DAOTablaMenu daoMenus = new DAOTablaMenu();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoMenus.setConn(conn);
			daoMenus.deleteMenu(menu);

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
				daoMenus.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	
////////////////////////////////////////
///////EVENTO////////////////////
////////////////////////////////////////

	/**
	 * Metodo que modela la transaccion que retorna todos los videos de la base de datos.
	 * @return ListaVideos - objeto que modela  un arreglo de videos. este arreglo contiene el resultado de la busqueda
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public List<Evento> darEventos() throws Exception {
		List<Evento> Eventos;
		DAOTablaEvento daoEvento = new DAOTablaEvento();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoEvento.setConn(conn);
			Eventos = daoEvento.dareventos();

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
				daoEvento.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Eventos;
	}
	
	/**
	 * Metodo que modela la transaccion que busca el video en la base de datos con el id que entra como parametro.
	 * @param name - Id del video a buscar. name != null
	 * @return Video - Resultado de la busqueda
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public Evento buscarEventoPorId(Long id) throws Exception {
		Evento Evento;
		DAOTablaEvento daoEventos = new DAOTablaEvento();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoEventos.setConn(conn);
			Evento = daoEventos.buscarEventoPorId(id);

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
				daoEventos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Evento;
	}
	
	/**
	 * Metodo que modela la transaccion que agrega un solo video a la base de datos.
	 * <b> post: </b> se ha agregado el video que entra como parametro
	 * @param video - el video a agregar. video != null
	 * @throws Exception - cualquier error que se genere agregando el video
	 */
	public void addEvento(Evento Evento) throws Exception {
		DAOTablaEvento daoEventos = new DAOTablaEvento();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoEventos.setConn(conn);
			daoEventos.addEvento(Evento);
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
				daoEventos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	
	/**
	 * Metodo que modela la transaccion que agrega los videos que entran como parametro a la base de datos.
	 * <b> post: </b> se han agregado los videos que entran como parametro
	 * @param videos - objeto que modela una lista de videos y se estos se pretenden agregar. videos != null
	 * @throws Exception - cualquier error que se genera agregando los videos
	 */
	public void addEventos(List<Evento> Eventos) throws Exception {
		DAOTablaEvento daoEventos = new DAOTablaEvento();
		try 
		{
			//////transaccion - ACID Example
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoEventos.setConn(conn);
			Iterator<Evento> it = Eventos.iterator();
			while(it.hasNext())
			{
				daoEventos.addEvento(it.next());
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
				daoEventos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	
	/**
	 * Metodo que modela la transaccion que actualiza el video que entra como parametro a la base de datos.
	 * <b> post: </b> se ha actualizado el video que entra como parametro
	 * @param video - Video a actualizar. video != null
	 * @throws Exception - cualquier error que se genera actualizando los videos
	 */
	public void updateEvento(Evento Evento) throws Exception {
		DAOTablaEvento daoEventos= new DAOTablaEvento();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoEventos.setConn(conn);
			daoEventos.updateEvento(Evento);

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
				daoEventos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * Metodo que modela la transaccion que elimina el video que entra como parametro a la base de datos.
	 * <b> post: </b> se ha eliminado el video que entra como parametro
	 * @param video - Video a eliminar. video != null
	 * @throws Exception - cualquier error que se genera actualizando los videos
	 */
	public void deleteEvento(Evento Evento) throws Exception {
		DAOTablaEvento daoEventos = new DAOTablaEvento();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoEventos.setConn(conn);
			daoEventos.deleteEvento(Evento);

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
				daoEventos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}	
	
	
	////////////7
	

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
		DAOTablaMenu daoMenu = new DAOTablaMenu();
		DAOTablaProducto daoProducto= new DAOTablaProducto();
		DAOTablaPedidoProducto daoPedidoProduc = new DAOTablaPedidoProducto();
		try 
		{
			System.out.println("------------------------------------SYS2");
			//////transaccion
			this.conn = darConexion();
			daoPedidoProduc.setConn(conn);
			daoPedidos.setConn(conn);
			daoMenu.setConn(conn);
			daoProducto.setConn(conn);
			Long idmenupedido =pedido.getIdMenu();
			Long idPedido = pedido.getId();
			System.out.println("------------------------------------IDMENU" +pedido.getIdMenu());
			System.out.println("------------------------------------IDPROD" +pedido.getIdProducto());
			
			Long idProductopedido = pedido.getIdProducto();
			Menu menu = daoMenu.buscarMenuPorId(idmenupedido);
			Producto produc = daoProducto.buscarProductoPorId(idProductopedido);
			System.out.println("------------------------------------MENU" +menu);
			System.out.println("------------------------------------PROD" +produc);
			
			System.out.println("------------------------------------SYS3");
			if(menu!=null && produc==null)
			{
				System.out.println("------------------------------------SYSX");
				int disponibles = menu.getDisponibles();
				if(disponibles>0)
				{
					updateDisponilbes(menu);
					daoPedidos.addPedido(pedido);
					daoPedidoProduc.addPedidoProduc(idPedido, idProductopedido);
					conn.commit();
					
				}
			}
			else if(produc!=null && menu==null)
			{
				System.out.println("------------------------------------SYSU");
				int disponibles = produc.getDisponibles();
				if(disponibles>0)
				{
					updateDisponiblesProd(produc);
					daoPedidos.addPedido(pedido);
					daoPedidoProduc.addPedidoProduc(idPedido, idProductopedido);
					conn.commit();
					
				}
				
			}
			
			else if(produc!=null && menu!=null)
			{
				System.out.println("------------------------------------SYST");
				
				int disponiblesProd = produc.getDisponibles();
				int disponiblesMenu = menu.getDisponibles();
				if(disponiblesProd>0 && disponiblesMenu>0)
				{
					System.out.println("------------------------------------SYSASDASDASD");
					updateDisponiblesProd(produc);
					updateDisponilbes(menu);
					daoPedidos.addPedido(pedido);
					daoPedidoProduc.addPedidoProduc(idPedido, idProductopedido);
					
					conn.commit();
					
				}
				
			}
			
			

		}
		
		catch (SQLException e) {
			System.out.println("------------------------------------ERROR1");
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.out.println("------------------------------------ERROR2");
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				
				System.out.println("------------------------------------SYS4");
				daoPedidos.cerrarRecursos();
				daoMenu.cerrarRecursos();
				daoProducto.cerrarRecursos();
				daoPedidoProduc.cerrarRecursos();
				if(this.conn!=null)
					System.out.println("------------------------------------SYS5");
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}
	
	
	public void addPedidoProductoEquivalente(Pedido pedido) throws Exception {
		DAOTablaPedido daoPedidos = new DAOTablaPedido();
		DAOTablaMenu daoMenu = new DAOTablaMenu();
		DAOTablaProducto daoProducto= new DAOTablaProducto();
		DAOTablaPedidoProducto daoPedidoProduc = new DAOTablaPedidoProducto();
		DAOTablaProductosEquivalentes daoProductosEquivalentes = new DAOTablaProductosEquivalentes();
		try 
		{
			System.out.println("------------------------------------SYS2");
			//////transaccion
			this.conn = darConexion();
			daoPedidoProduc.setConn(conn);
			daoPedidos.setConn(conn);
			daoMenu.setConn(conn);
			daoProducto.setConn(conn);
			daoProductosEquivalentes.setConn(conn);
			Long idmenupedido =pedido.getIdMenu();
			Long idPedido = pedido.getId();
			System.out.println("------------------------------------IDMENU" +pedido.getIdMenu());
			System.out.println("------------------------------------IDPROD" +pedido.getIdProducto());
			
			Long idProductopedido = pedido.getIdProducto();
			Menu menu = daoMenu.buscarMenuPorId(idmenupedido);
			Producto produc = daoProducto.buscarProductoPorId(idProductopedido);
			ArrayList<ProductosEquivalentes> lista = daoProductosEquivalentes.darProductosEquivalentesDeUnProducto(idProductopedido);
			System.out.println("------------------------------------MENU" +menu);
			System.out.println("------------------------------------PROD" +produc);
			
			System.out.println("------------------------------------SYS3");
			if(menu!=null && produc==null)
			{
				System.out.println("------------------------------------SYSX");
				int disponibles = menu.getDisponibles();
				if(disponibles>0)
				{
					updateDisponilbes(menu);
					daoPedidos.addPedido(pedido);
					for (int i = 0; i < lista.size(); i++)
					{
						ProductosEquivalentes actual = lista.get(i);
						Long idProd = actual.getIdProducto2();
						daoPedidoProduc.addPedidoProduc(idPedido, idProd);
					}
					
					conn.commit();
					
				}
			}
			else if(produc!=null && menu==null)
			{
				System.out.println("------------------------------------SYSU");
				int disponibles = produc.getDisponibles();
				if(disponibles>0)
				{
					updateDisponiblesProd(produc);
					daoPedidos.addPedido(pedido);
					for (int i = 0; i < lista.size(); i++)
					{
						ProductosEquivalentes actual = lista.get(i);
						Long idProd = actual.getIdProducto2();
						daoPedidoProduc.addPedidoProduc(idPedido, idProd);
					}
					conn.commit();
					
				}
				
			}
			
			else if(produc!=null && menu!=null)
			{
				System.out.println("------------------------------------SYST");
				
				int disponiblesProd = produc.getDisponibles();
				int disponiblesMenu = menu.getDisponibles();
				if(disponiblesProd>0 && disponiblesMenu>0)
				{
					System.out.println("------------------------------------SYSASDASDASD");
					updateDisponiblesProd(produc);
					updateDisponilbes(menu);
					daoPedidos.addPedido(pedido);
					for (int i = 0; i < lista.size(); i++)
					{
						ProductosEquivalentes actual = lista.get(i);
						Long idProd = actual.getIdProducto2();
						daoPedidoProduc.addPedidoProduc(idPedido, idProd);
					}
					
					conn.commit();
					
				}
				
			}
			
			
	
		}
		
		catch (SQLException e) {
			System.out.println("------------------------------------ERROR1");
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.out.println("------------------------------------ERROR2");
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				
				System.out.println("------------------------------------SYS4");
				daoPedidos.cerrarRecursos();
				daoMenu.cerrarRecursos();
				daoProducto.cerrarRecursos();
				daoPedidoProduc.cerrarRecursos();
				daoProductosEquivalentes.cerrarRecursos();
				
				
				if(this.conn!=null)
					System.out.println("------------------------------------SYS5");
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
	
	
	public List<Producto> darProductosGroupByNombre() throws Exception {
		List<Producto> productos;
		DAOTablaProducto daoProducto = new DAOTablaProducto();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProducto.setConn(conn);
			productos = daoProducto.darProductosoGroupByNombre();

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
	
	
	public List<Producto> darProductosGroupByCosto() throws Exception {
		List<Producto> productos;
		DAOTablaProducto daoProducto = new DAOTablaProducto();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProducto.setConn(conn);
			productos = daoProducto.darProductosoGroupByCosto();

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
	
	
	public List<Producto> darProductosGroupByCategoria() throws Exception {
		List<Producto> productos;
		DAOTablaProducto daoProducto = new DAOTablaProducto();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProducto.setConn(conn);
			productos = daoProducto.darProductosoGroupByCategoria();

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
	
	
	public List<Producto> darProductosGroupByPrecio() throws Exception {
		List<Producto> productos;
		DAOTablaProducto daoProducto = new DAOTablaProducto();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProducto.setConn(conn);
			productos = daoProducto.darProductosoGroupByPrecio();

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
	
	public List<Producto> darProductosGroupByTiempo() throws Exception {
		List<Producto> productos;
		DAOTablaProducto daoProducto = new DAOTablaProducto();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProducto.setConn(conn);
			productos = daoProducto.darProductosoGroupByTiempo();

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
	
	
	
	public List<Producto> darProductosOrderByNombre() throws Exception {
		List<Producto> productos;
		DAOTablaProducto daoProducto = new DAOTablaProducto();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProducto.setConn(conn);
			productos = daoProducto.darProductosoOrderByNombre();

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
	
	
	public List<Producto> darProductosOrderByCosto() throws Exception {
		List<Producto> productos;
		DAOTablaProducto daoProducto = new DAOTablaProducto();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProducto.setConn(conn);
			productos = daoProducto.darProductosoOrderByCosto();

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
	
	
	public List<Producto> darProductosOrderByCategoria() throws Exception {
		List<Producto> productos;
		DAOTablaProducto daoProducto = new DAOTablaProducto();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProducto.setConn(conn);
			productos = daoProducto.darProductosoOrderByCategoria();

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
	
	
	public List<Producto> darProductosOrderByPrecio() throws Exception {
		List<Producto> productos;
		DAOTablaProducto daoProducto = new DAOTablaProducto();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProducto.setConn(conn);
			productos = daoProducto.darProductosoOrderByPrecio();

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
	
	public List<Producto> darProductosOrderByTiempo() throws Exception {
		List<Producto> productos;
		DAOTablaProducto daoProducto = new DAOTablaProducto();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProducto.setConn(conn);
			productos = daoProducto.darProductosoOrderByTiempo();

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
	
	
	public void updateDisponiblesProd(Producto producto) throws Exception {
		DAOTablaProducto daoProductos = new DAOTablaProducto();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProductos.setConn(conn);
			daoProductos.updateDisponiblesProd(producto);

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
	
	
	
	
	
////////////////////////////////////////
///////TIPO////////////////////
////////////////////////////////////////

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
	
////////////////////////////////////////
///////ZONA////////////////////
////////////////////////////////////////
	
	
	/**
	 * Metodo que modela la transaccion que retorna todos los videos de la base de datos.
	 * @return ListaVideos - objeto que modela  un arreglo de videos. este arreglo contiene el resultado de la busqueda
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public List<Zona> darZonas() throws Exception {
		List<Zona> Zonas;
		DAOTablaZona daoZona = new DAOTablaZona();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZona.setConn(conn);
			Zonas = daoZona.darZonas();

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
				daoZona.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Zonas;
	}
	
	public List<Zona> darZonasGroupByEspacio() throws Exception {
		List<Zona> Zonas;
		DAOTablaZona daoZona = new DAOTablaZona();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZona.setConn(conn);
			Zonas = daoZona.darZonasGroupByEspacio();

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
				daoZona.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Zonas;
	}
	
	public List<Zona> darZonasGroupByApto() throws Exception {
		List<Zona> Zonas;
		DAOTablaZona daoZona = new DAOTablaZona();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZona.setConn(conn);
			Zonas = daoZona.darZonasGroupByApto();

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
				daoZona.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Zonas;
	}
	
	public List<Zona> darZonasGroupByCapacidad() throws Exception {
		List<Zona> Zonas;
		DAOTablaZona daoZona = new DAOTablaZona();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZona.setConn(conn);
			Zonas = daoZona.darZonasGroupByCapacidad();

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
				daoZona.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Zonas;
	}
	
	public List<Zona> darZonasGroupByCondiciones() throws Exception {
		List<Zona> Zonas;
		DAOTablaZona daoZona = new DAOTablaZona();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZona.setConn(conn);
			Zonas = daoZona.darZonasGroupByCondiciones();

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
				daoZona.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Zonas;
	}
	
	
	public List<Zona> darZonasOrderByEspacio() throws Exception {
		List<Zona> Zonas;
		DAOTablaZona daoZona = new DAOTablaZona();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZona.setConn(conn);
			Zonas = daoZona.darZonasOrderByEspacio();

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
				daoZona.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Zonas;
	}
	
	public List<Zona> darZonasOrderByApto() throws Exception {
		List<Zona> Zonas;
		DAOTablaZona daoZona = new DAOTablaZona();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZona.setConn(conn);
			Zonas = daoZona.darZonasOrderByApto();

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
				daoZona.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Zonas;
	}
	
	public List<Zona> darZonasOrderByCapacidad() throws Exception {
		List<Zona> Zonas;
		DAOTablaZona daoZona = new DAOTablaZona();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZona.setConn(conn);
			Zonas = daoZona.darZonasOrderByCapacidad();

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
				daoZona.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Zonas;
	}
	
	public List<Zona> darZonasOrderByCondiciones() throws Exception {
		List<Zona> Zonas;
		DAOTablaZona daoZona = new DAOTablaZona();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZona.setConn(conn);
			Zonas = daoZona.darZonasOrderByCondiciones();

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
				daoZona.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Zonas;
	}
	
	
	
	
	/**
	 * Metodo que modela la transaccion que busca el video en la base de datos con el id que entra como parametro.
	 * @param name - Id del video a buscar. name != null
	 * @return Video - Resultado de la busqueda
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public Zona buscarZonaPorId(Long id) throws Exception {
		Zona Zona;
		DAOTablaZona daoZonas = new DAOTablaZona();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			Zona = daoZonas.buscarZonaPorId(id);

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
				daoZonas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Zona;
	}
	
	/**
	 * Metodo que modela la transaccion que agrega un solo video a la base de datos.
	 * <b> post: </b> se ha agregado el video que entra como parametro
	 * @param video - el video a agregar. video != null
	 * @throws Exception - cualquier error que se genere agregando el video
	 */
	public void addZona(Zona Zona) throws Exception {
		DAOTablaZona daoZonas = new DAOTablaZona();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			daoZonas.addZona(Zona);
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
				daoZonas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	
	/**
	 * Metodo que modela la transaccion que agrega los videos que entran como parametro a la base de datos.
	 * <b> post: </b> se han agregado los videos que entran como parametro
	 * @param videos - objeto que modela una lista de videos y se estos se pretenden agregar. videos != null
	 * @throws Exception - cualquier error que se genera agregando los videos
	 */
	public void addZonas(List<Zona> Zonas) throws Exception {
		DAOTablaZona daoZonas = new DAOTablaZona();
		try 
		{
			//////transaccion - ACID Example
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoZonas.setConn(conn);
			Iterator<Zona> it = Zonas.iterator();
			while(it.hasNext())
			{
				daoZonas.addZona(it.next());
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
				daoZonas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	
	/**
	 * Metodo que modela la transaccion que actualiza el video que entra como parametro a la base de datos.
	 * <b> post: </b> se ha actualizado el video que entra como parametro
	 * @param video - Video a actualizar. video != null
	 * @throws Exception - cualquier error que se genera actualizando los videos
	 */
	public void updateZona(Zona Zona) throws Exception {
		DAOTablaZona daoZonas= new DAOTablaZona();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			daoZonas.updateZona(Zona);

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
				daoZonas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * Metodo que modela la transaccion que elimina el video que entra como parametro a la base de datos.
	 * <b> post: </b> se ha eliminado el video que entra como parametro
	 * @param video - Video a eliminar. video != null
	 * @throws Exception - cualquier error que se genera actualizando los videos
	 */
	public void deleteZona(Zona Zona) throws Exception {
		DAOTablaZona daoZonas = new DAOTablaZona();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			daoZonas.deleteZona(Zona);

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
				daoZonas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}


///////////////////////////////////////////////////////////////////////
///////////////////////////USUARIO/////////////////////////////////////

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
	
///////////////////////////////////////////////////////////////////////
///////////////////////////USUARIOCLIENTE/////////////////////////////////////
	public List<UsuarioCliente> darUsuariosCliente() throws Exception {
		List<UsuarioCliente> Usuarios;
		DAOTablaCliente daoUsuario = new DAOTablaCliente();
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


	public UsuarioCliente buscarUsuarioClientePorId(Long id) throws Exception {
		UsuarioCliente Usuario;
		DAOTablaCliente daoUsuarios = new DAOTablaCliente();
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


	public List<UsuarioCliente> buscarUsuariosClientePorName(String nombre) throws Exception {
		List<UsuarioCliente> Usuario;
		DAOTablaCliente daoUsuarios = new DAOTablaCliente();
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


	public void addUsuarioCliente(UsuarioCliente Usuario) throws Exception {
		DAOTablaCliente daoUsuarios = new DAOTablaCliente();
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


	public void addUsuariosCliente(List<UsuarioCliente> Usuarios) throws Exception {
		DAOTablaCliente daoUsuarios = new DAOTablaCliente();
		try 
		{
			//////transaccion - ACID Example
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoUsuarios.setConn(conn);
			Iterator<UsuarioCliente> it = Usuarios.iterator();
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


	public void updateUsuarioCliente(UsuarioCliente Usuario) throws Exception {
		DAOTablaCliente daoUsuarios = new DAOTablaCliente();
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


	public void deleteUsuarioCliente(UsuarioCliente Usuario) throws Exception {
		DAOTablaCliente daoUsuarios = new DAOTablaCliente();
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
///////////////////////////////////////////////////////////////////////
///////////////////////////USUARIOADMIN/////////////////////////////////////
	public List<UsuarioAdministrador> darUsuariosAdmin() throws Exception {
		List<UsuarioAdministrador> Usuarios;
		DAOTablaAdministrador daoUsuario = new DAOTablaAdministrador();
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


	public UsuarioAdministrador buscarUsuarioAdminPorId(Long id) throws Exception {
		UsuarioAdministrador Usuario;
		DAOTablaAdministrador daoUsuarios = new DAOTablaAdministrador();
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


	public List<UsuarioAdministrador> buscarUsuariosAdminPorName(String nombre) throws Exception {
		List<UsuarioAdministrador> Usuario;
		DAOTablaAdministrador daoUsuarios = new DAOTablaAdministrador();
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


	public void addUsuarioAdministrador(UsuarioAdministrador Usuario) throws Exception {
		DAOTablaAdministrador daoUsuarios = new DAOTablaAdministrador();
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


	public void addUsuariosAdministrador(List<UsuarioAdministrador> Usuarios) throws Exception {
		DAOTablaAdministrador daoUsuarios = new DAOTablaAdministrador();
		try 
		{
			//////transaccion - ACID Example
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoUsuarios.setConn(conn);
			Iterator<UsuarioAdministrador> it = Usuarios.iterator();
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


	public void updateUsuarioAdministrador(UsuarioAdministrador Usuario) throws Exception {
		DAOTablaAdministrador daoUsuarios = new DAOTablaAdministrador();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoUsuarios.setConn(conn);
			daoUsuarios.UpdateUsuarioAdministrador(Usuario);

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


	public void deleteUsuarioAdministrador(UsuarioAdministrador Usuario) throws Exception {
		DAOTablaAdministrador daoUsuarios = new DAOTablaAdministrador();
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
	
///////////////////////////////////////////////////////////////////////
///////////////////////////Preferencias/////////////////////////////////////
	public List<Preferencia> darPreferencias(Long idcliente) throws Exception {
		List<Preferencia> preferencia;
		DAOTablaPreferencia daoUsuario = new DAOTablaPreferencia();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoUsuario.setConn(conn);
			preferencia = daoUsuario.darPreferencias(idcliente);
	
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
		return preferencia;
	}


	public Preferencia buscarPreferenciaPorId(Long id) throws Exception {
		Preferencia preferencia;
		DAOTablaPreferencia daoPreferencia = new DAOTablaPreferencia();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoPreferencia.setConn(conn);
			preferencia = daoPreferencia.buscarPreferenciaPorId(id);

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
				daoPreferencia.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return preferencia;
	}


	


	public void addPreferencia(Preferencia Usuario) throws Exception {
		DAOTablaPreferencia daoUsuarios = new DAOTablaPreferencia();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoUsuarios.setConn(conn);
			daoUsuarios.addPreferencia(Usuario);
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


	public void addPreferencias(List<Preferencia> Usuarios) throws Exception {
		DAOTablaPreferencia daoUsuarios = new DAOTablaPreferencia();
		try 
		{
			//////transaccion - ACID Example
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoUsuarios.setConn(conn);
			Iterator<Preferencia> it = Usuarios.iterator();
			while(it.hasNext())
			{
				daoUsuarios.addPreferencia(it.next());
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


	public void updatePreferencia(Preferencia Usuario) throws Exception {
		DAOTablaPreferencia daoUsuarios = new DAOTablaPreferencia();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoUsuarios.setConn(conn);
			daoUsuarios.updatePreferencia(Usuario);

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


	public void deletePreferencia(Preferencia Usuario) throws Exception {
		DAOTablaPreferencia daoUsuarios = new DAOTablaPreferencia();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoUsuarios.setConn(conn);
			daoUsuarios.deletePreferencia(Usuario);

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


	public void addIngredienteEquivalente(Long id1, Ingrediente ing2) throws Exception {
		DAOTablaIngrediente daoIngredientes = new DAOTablaIngrediente();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoIngredientes.setConn(conn);
			daoIngredientes.addIngredienteEquivalente(id1, ing2);;
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
	
	
	/////////////////////////////////////////PRODUCTOSEQUIVALENTES///////////////////////////
	
	public List<ProductosEquivalentes> darProducEqui() throws Exception {
		List<ProductosEquivalentes> preferencia;
		DAOTablaProductosEquivalentes daoUsuario = new DAOTablaProductosEquivalentes();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoUsuario.setConn(conn);
			preferencia = daoUsuario.darProductosEquivalentes();
	
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
		return preferencia;
	}


	public ProductosEquivalentes buscarProducEquiPorId(String id) throws Exception {
		ProductosEquivalentes preferencia;
		DAOTablaProductosEquivalentes daoPreferencia = new DAOTablaProductosEquivalentes();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoPreferencia.setConn(conn);
			preferencia = daoPreferencia.buscarProductoEquiPorId(id);

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
				daoPreferencia.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return preferencia;
	}


	


	public void addProduEquivalentes(ProductosEquivalentes Usuario) throws Exception {
		DAOTablaProductosEquivalentes daoUsuarios = new DAOTablaProductosEquivalentes();
		DAOTablaProducto daoProducto = new DAOTablaProducto();
		try 
		{
			
			this.conn =darConexion();
			daoUsuarios.setConn(conn);
			daoProducto.setConn(conn);
			Long idProd1 = Usuario.getIdProducto1();
			Long idProd2 = Usuario.getIdProducto2();
			System.out.println("---------------------------------------"+idProd1);
			System.out.println("---------------------------------------"+idProd2);
			Producto prod1 = daoProducto.buscarProductoPorId(idProd1);
			Producto prod2 = daoProducto.buscarProductoPorId(idProd2);
			System.out.println("---------------------------------------pro1"+prod1);
			System.out.println("---------------------------------------prod2"+prod2);
			this.conn = darConexion();
			
			System.out.println("---------------------------------------SYS1");
			//////transaccion
			if(prod1.getCategoria().equals(prod2.getCategoria()))
			{
				System.out.println("---------------------------------------SYS2");
			daoUsuarios.addProductoEquiv(Usuario);
			conn.commit();
			}
			else
			{
				throw new Exception("Los productos no son de la misma categoria");
			}
			System.out.println("---------------------------------------SYS3");

		} catch (SQLException e) 
		{
			System.out.println("---------------------------------------SYS4");
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) 
		{
			System.out.println("---------------------------------------SYS5");
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoUsuarios.cerrarRecursos();
				daoProducto.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}
	
	public void deleteProductoEquivalente(ProductosEquivalentes Usuario) throws Exception {
		DAOTablaProductosEquivalentes daoUsuarios = new DAOTablaProductosEquivalentes();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoUsuarios.setConn(conn);
			daoUsuarios.deleteProductoEquivalente(Usuario);

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

	
	///////////////////////////////////////////MESA/////////////////////////////////////////
	public List<Mesa> darMesas() throws Exception {
		List<Mesa> preferencia;
		DAOTablaMesa daoUsuario = new DAOTablaMesa();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoUsuario.setConn(conn);
			preferencia = daoUsuario.darMesas();
	
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
		return preferencia;
	}


	public Mesa buscarMesaPorId(Long id) throws Exception {
		Mesa preferencia;
		DAOTablaMesa daoPreferencia = new DAOTablaMesa();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoPreferencia.setConn(conn);
			preferencia = daoPreferencia.buscarMesaPorId(id);

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
				daoPreferencia.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return preferencia;
	}


	


	public void addMesa(Mesa Usuario) throws Exception {
		DAOTablaMesa daoUsuarios = new DAOTablaMesa();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoUsuarios.setConn(conn);
			daoUsuarios.addMesa(Usuario);
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


	public void addMesas(List<Mesa> Usuarios) throws Exception {
		DAOTablaMesa daoUsuarios = new DAOTablaMesa();
		try 
		{
			//////transaccion - ACID Example
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoUsuarios.setConn(conn);
			Iterator<Mesa> it = Usuarios.iterator();
			while(it.hasNext())
			{
				daoUsuarios.addMesa(it.next());
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


	


	public void deleteMesa(Mesa Usuario) throws Exception {
		DAOTablaMesa daoUsuarios = new DAOTablaMesa();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoUsuarios.setConn(conn);
			daoUsuarios.deleteMesa(Usuario);

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
	
	
	///////////////////////////////////PEDIDOMESA//////////////////////////////////////////
	
	public List<PedidoMesa> darPedidoMesas() throws Exception {
		List<PedidoMesa> preferencia;
		DAOTablaPedidoMesa daoUsuario = new DAOTablaPedidoMesa();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoUsuario.setConn(conn);
			preferencia = daoUsuario.darPedidoMesas();
	
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
		return preferencia;
	}


	public PedidoMesa buscarPedidoMesaPorId(Long id) throws Exception {
		PedidoMesa preferencia;
		DAOTablaPedidoMesa daoPreferencia = new DAOTablaPedidoMesa();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoPreferencia.setConn(conn);
			preferencia = daoPreferencia.buscarPedidoMesaPorId(id);

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
				daoPreferencia.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return preferencia;
	}


	


	public void addPedidoMesa(PedidoMesa pedidoMesa) throws Exception {
		DAOTablaPedidoMesa daoPedidoMesa = new DAOTablaPedidoMesa();
		DAOTablaProducto daoProducto = new DAOTablaProducto();
		DAOTablaMenu daoMenu = new DAOTablaMenu();
		DAOTablaPedidoProducto daoPedidoProduc = new DAOTablaPedidoProducto();
		
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoPedidoMesa.setConn(conn);
			daoMenu.setConn(conn);
			daoProducto.setConn(conn);
			daoPedidoProduc.setConn(conn);
			Long idProducto = pedidoMesa.getIdProducto();
			Long idMenu = pedidoMesa.getIdMenu();
			Long idPedido = pedidoMesa.getId();
			Producto produc = daoProducto.buscarProductoPorId(idProducto);
			Menu menu = daoMenu.buscarMenuPorId(idMenu);
			if(menu!=null && produc==null)
			{
				System.out.println("------------------------------------SYSX");
				int disponibles = menu.getDisponibles();
				if(disponibles>0)
				{
					updateDisponilbes(menu);
					daoPedidoMesa.addPedidosMesa(pedidoMesa);
					daoPedidoProduc.addPedidoProduc(idPedido, idProducto);
					conn.commit();
					
				}
			}
			else if(produc!=null && menu==null)
			{
				System.out.println("------------------------------------SYSU");
				int disponibles = produc.getDisponibles();
				if(disponibles>0)
				{
					updateDisponiblesProd(produc);
					daoPedidoMesa.addPedidosMesa(pedidoMesa);
					daoPedidoProduc.addPedidoProduc(idPedido, idProducto);
					conn.commit();
					
				}
				
			}
			
			else if(produc!=null && menu!=null)
			{
				System.out.println("------------------------------------SYST");
				
				int disponiblesProd = produc.getDisponibles();
				int disponiblesMenu = menu.getDisponibles();
				if(disponiblesProd>0 && disponiblesMenu>0)
				{
					System.out.println("------------------------------------SYSASDASDASD");
					updateDisponiblesProd(produc);
					updateDisponilbes(menu);
					daoPedidoMesa.addPedidosMesa(pedidoMesa);
					daoPedidoProduc.addPedidoProduc(idPedido, idProducto);
					conn.commit();
					
				}
				
			}
		

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
				daoPedidoMesa.cerrarRecursos();
				daoMenu.cerrarRecursos();
				daoProducto.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}


	public void addPedidoMesa(List<PedidoMesa> Usuarios) throws Exception {
		DAOTablaPedidoMesa daoUsuarios = new DAOTablaPedidoMesa();
		try 
		{
			//////transaccion - ACID Example
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoUsuarios.setConn(conn);
			Iterator<PedidoMesa> it = Usuarios.iterator();
			while(it.hasNext())
			{
				daoUsuarios.addPedidosMesa(it.next());
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


	


	public void deletePedidoMesa(PedidoMesa Usuario) throws Exception {
		DAOTablaPedidoMesa daoUsuarios = new DAOTablaPedidoMesa();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoUsuarios.setConn(conn);
			daoUsuarios.deletePedidoMesa(Usuario);

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
	
	
	
	//////////////////////////////////////////////////////CONTABILIDAD///////////////////////////////////////////////////////
	
	/**
	 * Metodo que modela la transaccion que retorna todos los videos de la base de datos.
	 * @return ListaVideos - objeto que modela  un arreglo de videos. este arreglo contiene el resultado de la busqueda
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public List<Contabilidad> darContabilidad() throws Exception {
		List<Contabilidad> Eventos;
		DAOTablaContabilidad daoEvento = new DAOTablaContabilidad();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoEvento.setConn(conn);
			Eventos = daoEvento.darContabilidad();

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
				daoEvento.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Eventos;
	}
	
	/**
	 * Metodo que modela la transaccion que busca el video en la base de datos con el id que entra como parametro.
	 * @param name - Id del video a buscar. name != null
	 * @return Video - Resultado de la busqueda
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public Contabilidad buscarContabilidadPorId(Long id) throws Exception {
		Contabilidad Evento;
		DAOTablaContabilidad daoEventos = new DAOTablaContabilidad();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoEventos.setConn(conn);
			Evento = daoEventos.buscarContabilidadPorId(id);

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
				daoEventos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Evento;
	}
	
	/**
	 * Metodo que modela la transaccion que agrega un solo video a la base de datos.
	 * <b> post: </b> se ha agregado el video que entra como parametro
	 * @param video - el video a agregar. video != null
	 * @throws Exception - cualquier error que se genere agregando el video
	 */
	public void addContabilidad(Contabilidad contabilidad) throws Exception {
		DAOTablaContabilidad daoContabilidad = new DAOTablaContabilidad();
		DAOTablaProducto daoProducto = new DAOTablaProducto();
		DAOTablaMenu daoMenu = new DAOTablaMenu();
		DAOTablaRestaurante daoRestaurante = new DAOTablaRestaurante();
		DAOTablaPedidoProducto daoPedidoProducto = new DAOTablaPedidoProducto();
		DAOTablaPedido daoPedido= new DAOTablaPedido();
		
		try 
		{
			
			//////transaccion
			this.conn = darConexion();
			daoContabilidad.setConn(conn);
			daoProducto.setConn(conn);
			daoMenu.setConn(conn);
			daoRestaurante.setConn(conn);
			daoPedidoProducto.setConn(conn);
			daoPedido.setConn(conn);
			Long idRes = contabilidad.getIdRestaurante();
			Long idProd = contabilidad.getIdProducto();
			Long idMenu = contabilidad.getIdMenu();
			Restaurante rest = daoRestaurante.buscarRestaurantePorId(idRes);
			Producto prod = daoProducto.buscarProductoPorId(idProd);
			Menu menu = daoMenu.buscarMenuPorId(idMenu);
			int cantidadVendidas = 0;
			double ingresos =0;
			Long idUsuario = null;
			ArrayList<PedidoProducto> productosVendidos = daoPedidoProducto.darProductosVendidos(idProd);
			for (int i = 0; i < productosVendidos.size(); i++)
			{
				
				PedidoProducto actual = productosVendidos.get(i);
				Long idProductoActual = actual.getIdProducto();
				Producto productoActual = daoProducto.buscarProductoPorId(idProductoActual);
				Pedido pedidoActual = daoPedido.buscarPedidoPorId(actual.getIdPedido());
				idUsuario = pedidoActual.getIdUsuario();
				if(productoActual.getIdRestaurante().equals(idRes))
				{
					cantidadVendidas++;
					ingresos+=productoActual.getPrecio();
				}
			}
			contabilidad.setIngresosGenerados(ingresos);
			contabilidad.setUnidadesVendidas(cantidadVendidas);
			contabilidad.setIdCliente(idUsuario);
			contabilidad.setIdUsuario(idUsuario);
				
			daoContabilidad.addContabilidad(contabilidad);
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
				daoContabilidad.cerrarRecursos();
				daoProducto.cerrarRecursos();
				daoMenu.cerrarRecursos();
				daoRestaurante.cerrarRecursos();
				daoPedido.cerrarRecursos();
				daoPedidoProducto.cerrarRecursos();
				
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	
	/**
	 * Metodo que modela la transaccion que agrega los videos que entran como parametro a la base de datos.
	 * <b> post: </b> se han agregado los videos que entran como parametro
	 * @param videos - objeto que modela una lista de videos y se estos se pretenden agregar. videos != null
	 * @throws Exception - cualquier error que se genera agregando los videos
	 */
	public void addContabilidad(List<Contabilidad> Eventos) throws Exception {
		DAOTablaContabilidad daoEventos = new DAOTablaContabilidad();
		try 
		{
			//////transaccion - ACID Example
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoEventos.setConn(conn);
			Iterator<Contabilidad> it = Eventos.iterator();
			while(it.hasNext())
			{
				daoEventos.addContabilidad(it.next());
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
				daoEventos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	
	/**
	 * Metodo que modela la transaccion que actualiza el video que entra como parametro a la base de datos.
	 * <b> post: </b> se ha actualizado el video que entra como parametro
	 * @param video - Video a actualizar. video != null
	 * @throws Exception - cualquier error que se genera actualizando los videos
	 */
	public void updateContabilidad(Contabilidad Evento) throws Exception {
		DAOTablaContabilidad daoEventos= new DAOTablaContabilidad();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoEventos.setConn(conn);
			daoEventos.updateContabilidad(Evento);

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
				daoEventos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	


	
	


	/**
	 * Metodo que modela la transaccion que elimina el video que entra como parametro a la base de datos.
	 * <b> post: </b> se ha eliminado el video que entra como parametro
	 * @param video - Video a eliminar. video != null
	 * @throws Exception - cualquier error que se genera actualizando los videos
	 */
	public void deleteContabilidad(Contabilidad Evento) throws Exception {
		DAOTablaContabilidad daoEventos = new DAOTablaContabilidad();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoEventos.setConn(conn);
			daoEventos.deleteContabilidad(Evento);

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
				daoEventos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	
	//////////////////////RNF11////////////////////////////
	
	public List<Funcionamiento> darFuncionamiento() throws Exception {
		List<Pedido> pedidos;
		List<Funcionamiento> funcionamiento;
		List<PedidoRestaurante> pedidosRestaurante;
		
		DAOTablaPedidoProducto daoPedidoProducto = new DAOTablaPedidoProducto();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoPedidoProducto.setConn(conn);
			pedidos = daoPedidoProducto.darProductosPedidoFecha();
			pedidosRestaurante = daoPedidoProducto.darRestaurantesPedidoFecha();
			int domingo =0;
			int lunes =1;
			int martes =2;
			int miercoles =3;
			int jueves =4;
			int viernes =5;
			int sabado =6;
			List<Pedido> pedidosD =new ArrayList<Pedido>();
			List<Pedido> pedidosL =new ArrayList<Pedido>();
			List<Pedido> pedidosM =new ArrayList<Pedido>();
			List<Pedido> pedidosMM =new ArrayList<Pedido>();
			List<Pedido> pedidosJ =new ArrayList<Pedido>();
			List<Pedido> pedidosV =new ArrayList<Pedido>();
			List<Pedido> pedidosS =new ArrayList<Pedido>();
			
			List<PedidoRestaurante> resD =new ArrayList<PedidoRestaurante>();
			List<PedidoRestaurante> resL =new ArrayList<PedidoRestaurante>();
			List<PedidoRestaurante> resM =new ArrayList<PedidoRestaurante>();
			List<PedidoRestaurante> resMM =new ArrayList<PedidoRestaurante>();
			List<PedidoRestaurante> resJ =new ArrayList<PedidoRestaurante>();
			List<PedidoRestaurante> resV =new ArrayList<PedidoRestaurante>();
			List<PedidoRestaurante> resS =new ArrayList<PedidoRestaurante>();
			
			for (int i = 0; i < pedidos.size(); i++)
			{
				System.out.println("///////////////////////////////SYS1");
				Pedido act = pedidos.get(i);
				String fechaActual = act.getfecha();
				System.out.println("///////////////////////////////"+ fechaActual);
				Date fechax = new Date();
				String[]arr = fechaActual.split("-");
				String anioF = arr[0];
				String mesF = arr[1];
				String diaF = arr[2];
				int aniox = Integer.parseInt(anioF);
				int mesx = Integer.parseInt(mesF);
				int diax = Integer.parseInt(diaF);
				fechax.setDate(diax);
				fechax.setMonth(mesx);
				fechax.setYear(aniox);
				
				int dia =fechax.getDay();
				if (dia==domingo) 
				{
					pedidosD.add(act);
				}
				else if(dia==lunes)
				{
					pedidosL.add(act);
				}
				else if(dia==martes)
				{
					pedidosM.add(act);
				}
				else if(dia==miercoles)
				{
					pedidosMM.add(act);
				}
				else if(dia==jueves)
				{
					pedidosJ.add(act);	
				}
				else if(dia==viernes)
				{
					pedidosV.add(act);
				}
				else if(dia==sabado)
				{
					pedidosS.add(act);
				}
				
				
				
				
			}
			
			for (int i = 0; i < pedidosRestaurante.size(); i++)
			{
				PedidoRestaurante act = pedidosRestaurante.get(i);
				String fechaActual = act.getFecha();
				Date fechax = new Date();
				int dia =fechax.getDay();
				String[]arr = fechaActual.split("-");
				String anioF = arr[0];
				String mesF = arr[1];
				String diaF = arr[2];
				int aniox = Integer.parseInt(anioF);
				int mesx = Integer.parseInt(mesF);
				int diax = Integer.parseInt(diaF);
				fechax.setDate(diax);
				fechax.setMonth(mesx);
				fechax.setYear(aniox);
				if (dia==domingo) 
				{
					resD.add(act);
				}
				else if(dia==lunes)
				{
					resL.add(act);
				}
				else if(dia==martes)
				{
					resM.add(act);
				}
				else if(dia==miercoles)
				{
					resMM.add(act);
				}
				else if(dia==jueves)
				{
					resJ.add(act);	
				}
				else if(dia==viernes)
				{
					resV.add(act);
				}
				else if(dia==sabado)
				{
					resS.add(act);
				}
				
				
				
				
			}
			
			System.out.println("/////////////////////////////////////SYS2");
			
		/////////////////////Productos/////////////////////////////////////
			//Domingo
			daoPedidoProducto.createTabla1(pedidosD);
			System.out.println("/////////////////////////////////////SYS4");
			daoPedidoProducto.insertTabla1(pedidosD);
			System.out.println("/////////////////////////////////////SYS7");
			List<Pedido>masPedidosDomingo =daoPedidoProducto.maxTabla1();
			System.out.println("/////////////////////////////////////SYS10");
			List<Pedido>menosPedidosDomingo =daoPedidoProducto.minTabla1();
			System.out.println("/////////////////////////////////////SYS13");
			daoPedidoProducto.dropTabla1();
			
			
			//Lunes
			daoPedidoProducto.createTabla1(pedidosL);
			daoPedidoProducto.insertTabla1(pedidosL);
			List<Pedido>masPedidosLunes =daoPedidoProducto.maxTabla1();
			List<Pedido>menosPedidosLunes =daoPedidoProducto.minTabla1();
			daoPedidoProducto.dropTabla1();
			//Martes
			daoPedidoProducto.createTabla1(pedidosM);
			daoPedidoProducto.insertTabla1(pedidosM);
			List<Pedido>masPedidosMartes =daoPedidoProducto.maxTabla1();
			List<Pedido>menosPedidosMartes=daoPedidoProducto.minTabla1();
			daoPedidoProducto.dropTabla1();
			//Miercoles
			daoPedidoProducto.createTabla1(pedidosMM);
			daoPedidoProducto.insertTabla1(pedidosMM);
			List<Pedido>masPedidosMiercoles =daoPedidoProducto.maxTabla1();
			List<Pedido>menosPedidosMiercoles =daoPedidoProducto.minTabla1();
			daoPedidoProducto.dropTabla1();
			//Jueves
			daoPedidoProducto.createTabla1(pedidosJ);
			daoPedidoProducto.insertTabla1(pedidosJ);
			List<Pedido>masPedidosJueves =daoPedidoProducto.maxTabla1();
			List<Pedido>menosPedidosJueves =daoPedidoProducto.minTabla1();
			daoPedidoProducto.dropTabla1();
			//Viernes
			daoPedidoProducto.createTabla1(pedidosV);
			daoPedidoProducto.insertTabla1(pedidosV);
			List<Pedido>masPedidosViernes =daoPedidoProducto.maxTabla1();
			List<Pedido>menosPedidosVierenes =daoPedidoProducto.minTabla1();
			daoPedidoProducto.dropTabla1();
			//Sabado
			daoPedidoProducto.createTabla1(pedidosS);
			daoPedidoProducto.insertTabla1(pedidosS);
			List<Pedido>masPedidosSabado =daoPedidoProducto.maxTabla1();
			List<Pedido>menosPedidosSabado =daoPedidoProducto.minTabla1();
			daoPedidoProducto.dropTabla1();
			
		/////////////////////Restaurantes/////////////////////////////////////
			//Domingo
			daoPedidoProducto.createTabla2(resD);
			daoPedidoProducto.insertTabla2(resD);
			List<PedidoRestaurante>masFrecDomingo =daoPedidoProducto.maxTabla2();
			List<PedidoRestaurante>menosFrecDomingo =daoPedidoProducto.minTabla2();
			daoPedidoProducto.dropTabla2();
			//Lunes
			daoPedidoProducto.createTabla2(resL);
			daoPedidoProducto.insertTabla2(resL);
			List<PedidoRestaurante>masFrecLunes =daoPedidoProducto.maxTabla2();
			List<PedidoRestaurante>menosFrecLunes =daoPedidoProducto.minTabla2();
			daoPedidoProducto.dropTabla2();
			//Martes
			daoPedidoProducto.createTabla2(resM);
			daoPedidoProducto.insertTabla2(resM);
			List<PedidoRestaurante>masFrecMartes =daoPedidoProducto.maxTabla2();
			List<PedidoRestaurante>menosFrecMartes =daoPedidoProducto.minTabla2();
			daoPedidoProducto.dropTabla2();
			//Miercoles
			daoPedidoProducto.createTabla2(resMM);
			daoPedidoProducto.insertTabla2(resMM);
			List<PedidoRestaurante>masFrecMiercoles =daoPedidoProducto.maxTabla2();
			List<PedidoRestaurante>menosFrecMiercoles =daoPedidoProducto.minTabla2();
			daoPedidoProducto.dropTabla2();
			//Jueves
			daoPedidoProducto.createTabla2(resJ);
			daoPedidoProducto.insertTabla2(resJ);
			List<PedidoRestaurante>masFrecJueves =daoPedidoProducto.maxTabla2();
			List<PedidoRestaurante>menosFrecJueves=daoPedidoProducto.minTabla2();
			daoPedidoProducto.dropTabla2();
			//Viernes
			daoPedidoProducto.createTabla2(resV);
			daoPedidoProducto.insertTabla2(resV);
			List<PedidoRestaurante>masFrecViernes =daoPedidoProducto.maxTabla2();
			List<PedidoRestaurante>menosFrecViernes=daoPedidoProducto.minTabla2();
			daoPedidoProducto.dropTabla2();
			//Sabado
			daoPedidoProducto.createTabla2(resS);
			daoPedidoProducto.insertTabla2(resS);
			List<PedidoRestaurante>masFrecSabado =daoPedidoProducto.maxTabla2();
			List<PedidoRestaurante>menosFrecSabado=daoPedidoProducto.minTabla2();
			daoPedidoProducto.dropTabla2();
			
			Pedido productoMasPedidoDomingo = null;
			Pedido productoMenosPedidoDomingo= null;
			Pedido productoMasPedidoLunes= null;
			Pedido productoMenosPedidoLunes= null;
			Pedido productoMasPedidoMartes= null;
			Pedido productoMenosPedidoMartes= null;
			Pedido productoMasPedidoMiercoles= null;
			Pedido productoMenosPedidoMiercoles= null;
			Pedido productoMasPedidoJueves= null;
			Pedido productoMenosPedidoJueves= null;
			Pedido productoMasPedidoViernes= null;
			Pedido productoMenosPedidoViernes= null;
			Pedido productoMasPedidoSabado = null;
			Pedido productoMenosPedidoSabado= null;
			
			
			PedidoRestaurante restauranteMasFrecDom= null;
			PedidoRestaurante restauranteMenosFrecDom= null;
			PedidoRestaurante restauranteMasFrecLun= null;
			PedidoRestaurante restauranteMenosFrecLun= null;
			PedidoRestaurante restauranteMasFrecMar= null;
			PedidoRestaurante restauranteMenosFrecMar= null;
			PedidoRestaurante restauranteMasFrecMier= null;
			PedidoRestaurante restauranteMenosFrecMier= null;
			PedidoRestaurante restauranteMasFrecJue= null;
			PedidoRestaurante restauranteMenosFrecJue= null;
			PedidoRestaurante restauranteMasFrecVie= null;
			PedidoRestaurante restauranteMenosFrecVie= null;
			PedidoRestaurante restauranteMasFrecSab= null;
			PedidoRestaurante restauranteMenosFrecSab= null;
			
			
			

			
			if (masPedidosDomingo.size()>0)
			{
				 productoMasPedidoDomingo = masPedidosDomingo.get(0);
			}
			
			if (menosPedidosDomingo.size()>0)
			{
				 productoMenosPedidoDomingo = menosPedidosDomingo.get(0);
			}
			
			if (masPedidosLunes.size()>0)
			{
				 productoMasPedidoLunes = masPedidosLunes.get(0);	
			}
			if (menosPedidosLunes.size()>0)
			{
				 productoMenosPedidoLunes= menosPedidosLunes.get(0);
			}
			if (masPedidosMartes.size()>0)
			{
				 productoMasPedidoMartes = masPedidosMartes.get(0);
			}
			
			if (menosPedidosMartes.size()>0)
			{
				 productoMenosPedidoMartes = menosPedidosMartes.get(0);
			}
			if (masPedidosMiercoles.size()>0)
			{
				 productoMasPedidoMiercoles = masPedidosMiercoles.get(0);
			}
			if (menosPedidosMiercoles.size()>0)
			{
				 productoMenosPedidoMiercoles= menosPedidosMiercoles.get(0);
			}
			if (masPedidosJueves.size()>0)
			{
				 productoMasPedidoJueves = masPedidosJueves.get(0);
			}
			if (menosPedidosJueves.size()>0)
			{
				 productoMenosPedidoJueves= menosPedidosJueves.get(0);
			}
			if (masPedidosViernes.size()>0)
			{
				 productoMasPedidoViernes = masPedidosViernes.get(0);
			}
			if (menosPedidosVierenes.size()>0)
			{
				 productoMenosPedidoViernes = menosPedidosVierenes.get(0);
			}
			if (masPedidosSabado.size()>0)
			{
				 productoMasPedidoSabado = masPedidosSabado.get(0);
			}
			if (menosPedidosSabado.size()>0)
			{
				 productoMenosPedidoSabado= menosPedidosSabado.get(0);
			}
			if (masFrecDomingo.size()>0)
			{
				 restauranteMasFrecDom = masFrecDomingo.get(0);
			}
			if (menosFrecDomingo.size()>0)
			{
				 restauranteMenosFrecDom = menosFrecDomingo.get(0);
			}
			if (masFrecLunes.size()>0)
			{
				 restauranteMasFrecLun = masFrecLunes.get(0);
			}
			if (menosFrecLunes.size()>0)
			{
				 restauranteMenosFrecLun = menosFrecLunes.get(0);
			}
			if (masFrecMartes.size()>0)
			{
				 restauranteMasFrecMar = masFrecMartes.get(0);
			}
			if (menosFrecMartes.size()>0)
			{
				 restauranteMenosFrecMar = menosFrecMartes.get(0);
			}
			if (masFrecMiercoles.size()>0)
			{
				 restauranteMasFrecMier = masFrecMiercoles.get(0);
			}
			if (menosFrecMiercoles.size()>0)
			{
				 restauranteMenosFrecMier = menosFrecMiercoles.get(0);
			}
			if (masFrecJueves.size()>0)
			{
				 restauranteMasFrecJue = masFrecJueves.get(0);
			}
			if (menosFrecJueves.size()>0)
			{
				 restauranteMenosFrecJue = menosFrecJueves.get(0);
			}
			if (masFrecViernes.size()>0)
			{
				 restauranteMasFrecVie = masFrecViernes.get(0);
			}
			if (menosFrecViernes.size()>0)
			{
				 restauranteMenosFrecVie = menosFrecViernes.get(0);
			}
			if (masFrecSabado.size()>0)
			{
				 restauranteMasFrecSab = masFrecSabado.get(0);
			}
			if (menosFrecSabado.size()>0)
			{
				 restauranteMenosFrecSab = menosFrecSabado.get(0);

			}
			funcionamiento = new ArrayList<>();

			if(productoMasPedidoDomingo!=null&&productoMenosPedidoDomingo!=null&&restauranteMasFrecDom!=null&&restauranteMenosFrecDom!=null)
			{
				Funcionamiento funcDomingo = new Funcionamiento("Domingo", productoMasPedidoDomingo.getId(), productoMenosPedidoDomingo.getId(), restauranteMasFrecDom.getIdRestaurante(), restauranteMenosFrecDom.getIdRestaurante());
				funcionamiento.add(funcDomingo);
			}
			
			if(productoMasPedidoLunes!=null&&productoMenosPedidoLunes!=null&&restauranteMasFrecLun!=null&&restauranteMenosFrecLun!=null)
			{
				Funcionamiento funcLunes = new Funcionamiento("Lunes", productoMasPedidoLunes.getId(), productoMenosPedidoLunes.getId(), restauranteMasFrecLun.getIdRestaurante(), restauranteMenosFrecLun.getIdRestaurante());
				funcionamiento.add(funcLunes);
			}
			
			if(productoMasPedidoMartes!=null&&productoMenosPedidoMartes!=null&&restauranteMasFrecMar!=null&&restauranteMenosFrecMar!=null)
			{
				Funcionamiento funcMartes = new Funcionamiento("Martes", productoMasPedidoMartes.getId(), productoMenosPedidoMartes.getId(), restauranteMasFrecMar.getIdRestaurante(), restauranteMenosFrecMar.getIdRestaurante());
				funcionamiento.add(funcMartes);
			}
			if(productoMasPedidoMiercoles!=null&&productoMenosPedidoMiercoles!=null&&restauranteMasFrecMier!=null&&restauranteMenosFrecMier!=null)
			{
				Funcionamiento funcMiercoles = new Funcionamiento("Miercoles", productoMasPedidoMiercoles.getId(), productoMenosPedidoMiercoles.getId(), restauranteMasFrecMier.getIdRestaurante(), restauranteMenosFrecMier.getIdRestaurante());
				funcionamiento.add(funcMiercoles);
			}
			if(productoMasPedidoJueves!=null&&productoMenosPedidoJueves!=null&&restauranteMasFrecJue!=null&&restauranteMenosFrecJue!=null)
			{
				Funcionamiento funcJueves = new Funcionamiento("Jueves", productoMasPedidoJueves.getId(), productoMenosPedidoJueves.getId(), restauranteMasFrecJue.getIdRestaurante(), restauranteMenosFrecJue.getIdRestaurante());
				funcionamiento.add(funcJueves);
				
			}
			if(productoMasPedidoViernes!=null&&productoMenosPedidoViernes!=null&&restauranteMasFrecVie!=null&&restauranteMenosFrecVie!=null)
			{
				Funcionamiento funcViernes = new Funcionamiento("Viernes", productoMasPedidoViernes.getId(), productoMenosPedidoViernes.getId(), restauranteMasFrecVie.getIdRestaurante(), restauranteMenosFrecVie.getIdRestaurante());
				funcionamiento.add(funcViernes);
			}
			if(productoMasPedidoSabado!=null&&productoMenosPedidoSabado!=null&&restauranteMasFrecSab!=null&&restauranteMenosFrecSab!=null)
			{
				Funcionamiento funcSabado= new Funcionamiento("Sabado", productoMasPedidoSabado.getId(), productoMenosPedidoSabado.getId(), restauranteMasFrecSab.getIdRestaurante(), restauranteMenosFrecSab.getIdRestaurante());
				funcionamiento.add(funcSabado);
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
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
				daoPedidoProducto.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return funcionamiento;
	}

	
	
	
	
	
}
