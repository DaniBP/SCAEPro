/**
 * 
 */
package com.greenpear.it.scaepro.dao.administracionmovil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

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
//		Verificar si ya existe alguna noticia con éste título 
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
			return "Ya Existe una Noticia con este Título!";
		}
		
//		Verificar si ya existe alguna imágen con éste nombre 
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
			return "Ya Existe una imágen con este nombre!";
		}

		try {
			SimpleJdbcInsert insert = new SimpleJdbcInsert(getDataSource());

			insert.setTableName("c_noticia");
			insert.setGeneratedKeyName("idNoticias");

			Map<String, Object> parameters = new HashMap<String, Object>();

			parameters.put("titulo", modelo.getTituloNoticia());
			parameters.put("noticia", modelo.getDescNoticia());
			parameters.put("imagen", modelo.getImagenNoticia());

			modelo.setIdNoticia(insert.executeAndReturnKey(parameters).intValue());

		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo: {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la inserción!");
		}

		return "Registro de Noticia Exitoso!";
	}

	public String editar(NoticiasModel modelo) throws SQLException {
		String sql = "select * from c_area where idNoticias =" + modelo.getIdNoticia();

		NoticiasModel model = new NoticiasModel();
		try {
			model = getJdbcTemplate().query(sql, new ResultSetExtractor<NoticiasModel>() {
				public NoticiasModel extractData(ResultSet rs) throws SQLException {
					NoticiasModel resultValue = new NoticiasModel();
					if (rs.next()) {
						resultValue.setIdNoticia(rs.getInt("idArea"));
						resultValue.setDescNoticia(rs.getString("descripcionArea"));
						resultValue.setTituloNoticia(rs.getString("nombreArea"));
					}
					return resultValue;
				}
			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}

//		if (model.getDescripcionArea().equals(modelo.getDescripcionArea())
//				&& model.getArea().equals(modelo.getArea())) {
//			return "No hubo cambios!";
//		}

		String sqlArea = "UPDATE c_area SET nombreArea=?, descripcionArea=? WHERE nombreArea=?";

		try {
			getJdbcTemplate().update(sqlArea, modelo.getTituloNoticia(), modelo.getDescNoticia(), modelo.getImagenNoticia());

		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo: {} ", e.getMessage());
			throw new SQLException(
					"Existe un problema con la base de datos\n" + "No se pudo realizar la actualización!");
		}

		return "Área actualizada correctamente";
	}

}
