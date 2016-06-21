/**
 * 
 */
package com.greenpear.it.scaepro.dao.gestionareas;

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
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.greenpear.it.scaepro.model.gestionareas.ConsultaAreasModel;
import com.greenpear.it.scaepro.services.DataSourceService;
import com.greenpear.it.scaepro.services.InsertService;

/**
 * @author EDSONJOSUE
 *
 */
@Repository("altaAreasDao")
public class AltaAreasDao extends DataSourceService implements InsertService<ConsultaAreasModel> {
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

	@Override
	public String insertar(ConsultaAreasModel t) throws SQLException {
		String sql = "select * from c_area where nombreArea ='" + t.getArea() + "'";

		ConsultaAreasModel model = new ConsultaAreasModel();
		try {
			model = getJdbcTemplate().query(sql, new ResultSetExtractor<ConsultaAreasModel>() {
				public ConsultaAreasModel extractData(ResultSet rs) throws SQLException {
					ConsultaAreasModel resultValue = new ConsultaAreasModel();
					if (rs.next()) {
						resultValue.setIdArea(rs.getInt("idArea"));
					}
					return resultValue;
				}
			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}

		if (model.getIdArea() != 0) {
			return "existe área!";
		}

		try {
			SimpleJdbcInsert insert = new SimpleJdbcInsert(getDataSource());

			insert.setTableName("c_area");
			insert.setGeneratedKeyName("idArea");

			Map<String, Object> parameters = new HashMap<String, Object>();

			parameters.put("nombreArea", t.getArea());
			parameters.put("descripcionArea", t.getDescripcionArea());

			t.setIdArea(insert.executeAndReturnKey(parameters).intValue());

		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo: {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la inserción!");
		}

		return "El área " + t.getArea() + "\n" + "Fue registrado exitosamente!\n\n" + "Su número de ID es: "
				+ t.getIdArea();
	}

	public String editar(ConsultaAreasModel modelo) throws SQLException {
		String sql = "select * from c_area where nombreArea ='" + modelo.getArea() + "'";

		ConsultaAreasModel model = new ConsultaAreasModel();
		try {
			model = getJdbcTemplate().query(sql, new ResultSetExtractor<ConsultaAreasModel>() {
				public ConsultaAreasModel extractData(ResultSet rs) throws SQLException {
					ConsultaAreasModel resultValue = new ConsultaAreasModel();
					if (rs.next()) {
						resultValue.setIdArea(rs.getInt("idArea"));
						resultValue.setDescripcionArea(rs.getString("descripcionArea"));
						resultValue.setArea(rs.getString("nombreArea"));
					}
					return resultValue;
				}
			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}

		if (model.getDescripcionArea().equals(modelo.getDescripcionArea())
				&& model.getArea().equals(modelo.getArea())) {
			return "No hubo cambios!";
			
		}

		String sqlArea = "UPDATE c_area SET nombreArea=?, descripcionArea=? WHERE nombreArea=?";

		try {
			getJdbcTemplate().update(sqlArea, modelo.getArea(), modelo.getDescripcionArea(), modelo.getAreaAnterior());

		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo: {} ", e.getMessage());
			throw new SQLException(
					"Existe un problema con la base de datos\n" + "No se pudo realizar la actualización!");
		}

		return "Área actualizada correctamente";
	}
}
