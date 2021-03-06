/**
 * 
 */
package com.greenpear.it.scaepro.dao.administracionmovil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.modelmbean.ModelMBeanOperationInfo;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.greenpear.it.scaepro.model.administracionmovil.GenerarAvisoModel;
import com.greenpear.it.scaepro.model.administracionmovil.NoticiasModel;
import com.greenpear.it.scaepro.model.gestionareas.ConsultaAreasModel;

/**
 * @author EDSONJOSUE
 *
 */
public class NuevaNoticiaDao {
	private static final Logger log = LoggerFactory.getLogger("InsertService");

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

	public String insertar(NoticiasModel modelo) throws SQLException {
		// Verificar si ya existe alguna noticia con �ste t�tulo
		String sql = "select * from c_noticia where titulo ='" + modelo.getTituloNoticia() + "'";

		NoticiasModel model = new NoticiasModel();
		try {
			model = getJdbcTemplate().query(sql, new ResultSetExtractor<NoticiasModel>() {
				public NoticiasModel extractData(ResultSet rs) throws SQLException {
					NoticiasModel resultValue = new NoticiasModel();
					if (rs.next()) {
						resultValue.setIdNoticia(rs.getInt("idNoticias"));
					}
					return resultValue;
				}
			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}

		if (model.getIdNoticia() != 0) {
			return "Ya Existe una Noticia con este T�tulo!";
		}

		// Verificar si ya existe alguna im�gen con �ste nombre
		String sqlImagen = "select * from c_noticia where imagen ='" + modelo.getImagenNoticia() + "'";

		NoticiasModel modelo2 = new NoticiasModel();
		try {
			modelo2 = getJdbcTemplate().query(sqlImagen, new ResultSetExtractor<NoticiasModel>() {
				public NoticiasModel extractData(ResultSet rs) throws SQLException {
					NoticiasModel resultValue = new NoticiasModel();
					if (rs.next()) {
						resultValue.setIdNoticia(rs.getInt("idNoticias"));
					}
					return resultValue;
				}
			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}

		if (modelo2.getIdNoticia() != 0) {
			return "Ya Existe una im�gen con este nombre!";
		}

		try {
			SimpleJdbcInsert insert = new SimpleJdbcInsert(getDataSource());

			insert.setTableName("c_noticia");
			insert.setGeneratedKeyName("idNoticias");

			Map<String, Object> parameters = new HashMap<String, Object>();

			parameters.put("titulo", modelo.getTituloNoticia());
			parameters.put("noticia", modelo.getDescNoticia());
			parameters.put("fechaNoticia", modelo.getFechaNoticia());

			modelo.setIdNoticia(insert.executeAndReturnKey(parameters).intValue());

		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo: {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la inserci�n!");
		}
		String sqlEdicion = "UPDATE c_noticia SET imagen=? WHERE idNoticias=?";
		try {
			getJdbcTemplate().update(sqlEdicion, "imagen"+modelo.getIdNoticia()+".jpg", modelo.getIdNoticia());
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo: {} ", e.getMessage());
			throw new SQLException(
					"Existe un problema con la base de datos\n" + "No se pudo realizar la actualizaci�n!");
		}
		
		return "!Registro De Noticia Exitoso!";
	}

	// Editar Noticia
	public String editar(NoticiasModel modelo) throws SQLException {
		String sqlid = "select * from c_noticia where idNoticias = " + modelo.getIdNoticia();
		NoticiasModel modelImagen = new NoticiasModel();
		modelImagen = getJdbcTemplate().query(sqlid, new ResultSetExtractor<NoticiasModel>() {
			public NoticiasModel extractData(ResultSet rs) throws SQLException {
				NoticiasModel resultValue = new NoticiasModel();
				if (rs.next()) {
					resultValue.setIdNoticia(rs.getInt("idNoticias"));
					resultValue.setTituloNoticia(rs.getString("titulo"));
					resultValue.setImagenNoticia(rs.getString("imagen"));
				}
				return resultValue;
			}
		});

		if (modelImagen.getTituloNoticia().equals(modelo.getTituloNoticia()) == false) {
			String sqltit = "select * from c_noticia where titulo = '" + modelo.getTituloNoticia() + "'";
			NoticiasModel modelTitulodif = new NoticiasModel();
			modelTitulodif = getJdbcTemplate().query(sqltit, new ResultSetExtractor<NoticiasModel>() {
				public NoticiasModel extractData(ResultSet rs) throws SQLException {
					NoticiasModel resultValue = new NoticiasModel();
					if (rs.next()) {
						resultValue.setIdNoticia(rs.getInt("idNoticias"));
					}
					return resultValue;
				}
			});
			if (modelTitulodif.getIdNoticia() != 0) {
				return "�Ya Existe Una Noticia Con �ste T�tulo!";
			}
		}

		String sqlEdicion = "UPDATE c_noticia SET titulo=?, noticia=?, imagen=?, fechaNoticia=? WHERE idNoticias=?";
		try {
			getJdbcTemplate().update(sqlEdicion, modelo.getTituloNoticia(), modelo.getDescNoticia(),
					modelo.getImagenNoticia(), modelo.getFechaNoticia() , modelo.getIdNoticia());
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo: {} ", e.getMessage());
			throw new SQLException(
					"Existe un problema con la base de datos\n" + "No se pudo realizar la actualizaci�n!");
		}
		if (modelImagen.getImagenNoticia().equals(modelo.getImagenNoticia()) == false) {
			return "�Im�gen Actualizada Correctamente!";
		}else{
			return "�Noticia Actualizada Correctamente!";
		}
	}

	public String eliminar(int idNoticia) throws SQLException {
		String sql = "DELETE FROM c_noticia WHERE idNoticias=?";

		try {
			getJdbcTemplate().update(sql, idNoticia);
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la eliminaci�n!");
		}
		return "�Noticia Eliminada Correctamente!";
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
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la eliminaci�n!");
		}
		return "�Aviso Eliminado!";
	}

}
