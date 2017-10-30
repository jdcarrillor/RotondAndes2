package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import vos.Restaurante;


public class DAOTablaRestaurante 
{
	/**
	 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la aplicación
	 * @author Monitores 2017-20
	 */
	

		/**
		 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
		 */
		private ArrayList<Object> recursos;

		/**
		 * Atributo que genera la conexión a la base de datos
		 */
		private Connection conn;

		/**
		 * Metodo constructor que crea DAOVideo
		 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
		 */
		public DAOTablaRestaurante() {
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
		public ArrayList<Restaurante> darRestaurantes() throws SQLException, Exception {
			ArrayList<Restaurante> restaruantes = new ArrayList<Restaurante>();

			String sql = "SELECT * FROM RESTAURANTE";

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				String name = rs.getString("NOMBRERESTAURANTE");
				Long id = rs.getLong("IDRESTAURANTE");
				String representante= rs.getString("REPRESENTANTE");
				String tipo_Comida= rs.getString("TIPO_COMIDA");
				Long id_Zona=rs.getLong("ID_ZONA");
				Integer capacidad = rs.getInt("CAPACIDAD");
				Integer maxprod= rs.getInt("MAX_PRODUC");
				Long ingresos = rs.getLong("INGRESOS");
				restaruantes.add(new Restaurante(id, name, representante, tipo_Comida, id_Zona,capacidad, maxprod, ingresos));
				
            
			}
			return restaruantes;
		}
		
		


		/**
		 * Metodo que busca el video con el id que entra como parametro.
		 * @param name - Id de el video a buscar
		 * @return Restaurante encontrado
		 * @throws SQLException - Cualquier error que la base de datos arroje.
		 * @throws Exception - Cualquier error que no corresponda a la base de datos
		 */
	
		public Restaurante buscarRestaurantePorId(Long id) throws SQLException, Exception 
		{
			Restaurante restaurante = null;

			String sql = "SELECT * FROM RESTAURANTE WHERE IDRESTAURANTE =" + id;

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();
			if(rs!=null){

			if(rs.next()) {
				Long id2 = rs.getLong("ID");
				String name = rs.getString("NOMBRE");
				String representante = rs.getString("REPRESENTANTE");
				String tipo_comida = rs.getString("TIPO_COMIDA");
				Long id_zona = rs.getLong("ID_ZONA");
				Integer capacidad = rs.getInt("CAPACIDAD");
				Integer maxprod= rs.getInt("MAX_PRODUC");
				Long ingresos = rs.getLong("INGRESOS");
				
				restaurante = new Restaurante(id2, name, representante, tipo_comida, id_zona, capacidad, maxprod, ingresos);
				
				
				
			}
			}

			return restaurante;
		}

		/**
		 * Metodo que agrega el video que entra como parametro a la base de datos.
		 * @param video - el video a agregar. video !=  null
		 * <b> post: </b> se ha agregado el video a la base de datos en la transaction actual. pendiente que el video master
		 * haga commit para que el video baje  a la base de datos.
		 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el video a la base de datos
		 * @throws Exception - Cualquier error que no corresponda a la base de datos
		 */
		public void addRestaurante(Restaurante restaurante) throws SQLException, Exception {

			String sql = "INSERT INTO Restaurante VALUES (";
			sql += restaurante.getId() + ",";
			sql += "'"+restaurante.getnombre()+"'" + ",";
			sql += "'"+restaurante.getrepresentante()+"'" + ",";
			sql += "'"+restaurante.gettipoComida()+"'" + ",";
			sql += restaurante.getIdZona() +  ",";
			sql += restaurante.getCapacidad() + ",";
			sql += restaurante.getMaxProduc() + ",";
			sql += restaurante.getIngresos() + ")";

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
		public void updateRestaurante(Restaurante restaurante) throws SQLException, Exception {

			String sql = "UPDATE RESTAURANTE SET ";
			sql += "NOMBRE='" + restaurante.getnombre() + "',";
			sql += "REPRESENTANTE='" + restaurante.getrepresentante()+ "',";
			sql += "TIPO_COMIDA='" + restaurante.gettipoComida()+ "',";
			sql += "ID_ZONA=" + restaurante.getIdZona() + ",";
			sql += "CAPACIDAD" + restaurante.getCapacidad() + ",";
			sql += "MAX_PRODUC" + restaurante.getMaxProduc() + ",";
			sql += "INGRESOS" + restaurante.getIngresos();
			sql += " WHERE IDRESTAURANTE = " + restaurante.getId();


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
		public void deleteRestaurante(Restaurante restaurante) throws SQLException, Exception {

			String sql = "DELETE FROM RESTAURANTE";
			sql += " WHERE IDRESTAURANTE = " + restaurante.getId();

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
		}
		
		public void surtirRestaurante()throws SQLException, Exception{
			
		}

	}



