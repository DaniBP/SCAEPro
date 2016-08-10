/**
 * 
 */
package com.greenpear.it.scaepro.dao.administracionmovil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.greenpear.it.scaepro.model.administracionmovil.NoticiasModel;


/**
 * @author EDSONJOSUE
 *
 */
public class NoticiasExistentesDao {
	private static final Logger log = LoggerFactory.getLogger("SelectAllService");
	private JdbcTemplate jdbcTemplate;
		
		
		protected JdbcTemplate getJdbcTemplate() {
			return jdbcTemplate;
		}
		
		@Autowired
		@Qualifier("dataSource")
		public void setDataSource(DataSource ds) {
			jdbcTemplate = new JdbcTemplate(ds);
		}
		
		public DataSource getDataSource() {
			return jdbcTemplate.getDataSource();
		}

	public List<NoticiasModel> consultaGeneral() throws SQLException {
		List<NoticiasModel> listaAreas=new ArrayList<NoticiasModel>();
		String sql="SELECT * FROM c_noticia";
		
		try{
			listaAreas=getJdbcTemplate().query(sql, new RowMapper<NoticiasModel>(){
			
				public NoticiasModel mapRow(ResultSet rs, int columna) throws SQLException{
					NoticiasModel resultValue = new NoticiasModel();
					resultValue.setIdNoticia(rs.getInt("idNoticias"));
					resultValue.setTituloNoticia(rs.getString("titulo"));
					resultValue.setDescNoticia(rs.getString("noticia"));
					resultValue.setImagenNoticia(rs.getString("imagen"));
					return resultValue;
				}
				
			});
		}catch(Exception e){
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ",e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n"
					+ "No se pudo realizar la consulta!");
		}
		
		return listaAreas;
	}

	public NoticiasModel consultaEditar(String tituloNoticia) throws SQLException {
		NoticiasModel modelo = new NoticiasModel();
		String sql = "select * from c_noticia where titulo ='" + tituloNoticia + "'";

		try {
			modelo = getJdbcTemplate().query(sql, new ResultSetExtractor<NoticiasModel>() {
				public NoticiasModel extractData(ResultSet rs) throws SQLException {
					NoticiasModel resultValue = new NoticiasModel();
					if (rs.next()) {
						resultValue.setIdNoticia(rs.getInt("idNoticias"));
						resultValue.setDescNoticia(rs.getString("noticia"));
						resultValue.setTituloNoticia(rs.getString("titulo"));
						resultValue.setImagenNoticia(rs.getString("imagen"));
					}
					return resultValue;
				}
			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}
		return modelo;
	}
	
	public List<NoticiasModel> consultaFechas() throws SQLException {
		List<NoticiasModel> listaAreas = new ArrayList<NoticiasModel>();
		String sql = " SELECT fechaNoticia,idNoticias from c_noticia";
		try {
			listaAreas = getJdbcTemplate().query(sql, new RowMapper<NoticiasModel>() {

				public NoticiasModel mapRow(ResultSet rs, int columna) throws SQLException {
					NoticiasModel resultValue = new NoticiasModel();
					resultValue.setFechaNoticia(rs.getString("fechaNoticia"));
					resultValue.setIdNoticia(rs.getInt("idNoticias"));
					return resultValue;
				}
			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}
		return listaAreas;
	}

	public String eliminar(NoticiasModel fechasAvisosModel)throws SQLException {
		String sql = "DELETE FROM c_noticia WHERE idNoticias=?";

		try {
			getJdbcTemplate().update(sql, fechasAvisosModel.getIdNoticia());
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la eliminación!");
		}
		return "¡Aviso Eliminado!";
	}

}
