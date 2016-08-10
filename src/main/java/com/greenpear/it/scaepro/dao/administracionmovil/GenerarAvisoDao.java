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

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.greenpear.it.scaepro.model.administracionmovil.EstatusPagoModel;
import com.greenpear.it.scaepro.model.administracionmovil.GenerarAvisoModel;
import com.greenpear.it.scaepro.model.administracionmovil.NoticiasModel;

/**
 * @author EDSONJOSUE
 *
 */
public class GenerarAvisoDao {
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

	public List<GenerarAvisoModel> consultaGeneral() throws SQLException {
		List<GenerarAvisoModel> listaEmpleados = new ArrayList<GenerarAvisoModel>();
		String sql = "SELECT * FROM t_empleado order by apePatEmpleado";

		try {
			listaEmpleados = getJdbcTemplate().query(sql, new RowMapper<GenerarAvisoModel>() {

				public GenerarAvisoModel mapRow(ResultSet rs, int columna) throws SQLException {
					GenerarAvisoModel resultValue = new GenerarAvisoModel();
					resultValue.setIdEmpleado(rs.getInt("idEmpleado"));
					resultValue.setNombreEmpleado(rs.getString("apePatEmpleado") +" "+ rs.getString("apeMatEmpleado")
					+" "+ rs.getString("nombreEmpleado"));
					return resultValue;
				}

			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}

		return listaEmpleados;
	}

	public List<GenerarAvisoModel> consultaAreas() throws SQLException{
		List<GenerarAvisoModel> listaAreas = new ArrayList<GenerarAvisoModel>();
		String sql = "SELECT * FROM c_area order by nombreArea";
		try {
			listaAreas = getJdbcTemplate().query(sql, new RowMapper<GenerarAvisoModel>() {

				public GenerarAvisoModel mapRow(ResultSet rs, int columna) throws SQLException {
					GenerarAvisoModel resultValue = new GenerarAvisoModel();
					resultValue.setIdArea(rs.getInt("idArea"));
					resultValue.setNombreArea(rs.getString("nombreArea"));
					return resultValue;
				}
			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}
		return listaAreas;
	}

	public GenerarAvisoModel consultaArea(String nombre) throws SQLException {
		GenerarAvisoModel modelo = new GenerarAvisoModel();
		String sql = "select * from c_area where nombreArea ='" + nombre + "'";
		try {
			modelo = getJdbcTemplate().query(sql, new ResultSetExtractor<GenerarAvisoModel>() {
				public GenerarAvisoModel extractData(ResultSet rs) throws SQLException {
					GenerarAvisoModel resultValue = new GenerarAvisoModel();
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
		return modelo;
	}

	public String insertarAvisoArea(GenerarAvisoModel modelo) throws SQLException {
		try {
			SimpleJdbcInsert insert = new SimpleJdbcInsert(getDataSource());

			insert.setTableName("c_aviso");
			insert.setGeneratedKeyName("idAviso");

			Map<String, Object> parameters = new HashMap<String, Object>();

			parameters.put("idArea", modelo.getIdArea());
			parameters.put("idEmpleado", modelo.getIdEmpleado());
			parameters.put("tipoAviso", modelo.getTipoAviso());
			parameters.put("aviso", modelo.getAviso());
			parameters.put("fechaAviso",modelo.getFechaAviso());
			
			insert.executeAndReturnKey(parameters).intValue();

		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo: {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la inserción!");
		}
		return "!Registro De Aviso Exitoso!";
	}

	public GenerarAvisoModel consultaEmpleado(String nombre)throws SQLException {
		GenerarAvisoModel modelo = new GenerarAvisoModel();
		String sql = "select * from t_empleado where CONCAT(apePatEmpleado,' ',apeMatEmpleado,' ',nombreEmpleado) ='" + nombre + "'";
		try {
			modelo = getJdbcTemplate().query(sql, new ResultSetExtractor<GenerarAvisoModel>() {
				public GenerarAvisoModel extractData(ResultSet rs) throws SQLException {
					GenerarAvisoModel resultValue = new GenerarAvisoModel();
					if (rs.next()) {
						resultValue.setIdEmpleado(rs.getInt("idEmpleado"));
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

	public List<GenerarAvisoModel> consultaFechas() throws SQLException {
		List<GenerarAvisoModel> listaAreas = new ArrayList<GenerarAvisoModel>();
		String sql = " SELECT fechaAviso,idAviso from c_aviso";
		try {
			listaAreas = getJdbcTemplate().query(sql, new RowMapper<GenerarAvisoModel>() {

				public GenerarAvisoModel mapRow(ResultSet rs, int columna) throws SQLException {
					GenerarAvisoModel resultValue = new GenerarAvisoModel();
					resultValue.setFechaAviso(rs.getString("fechaAviso"));
					resultValue.setIdAviso(rs.getInt("idAviso"));
					return resultValue;
				}
			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}
		return listaAreas;
	}

	public String eliminar(GenerarAvisoModel fechasAvisosModel)throws SQLException {
		String sql = "DELETE FROM c_aviso WHERE idAviso=?";

		try {
			getJdbcTemplate().update(sql, fechasAvisosModel.getIdAviso());
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la eliminación!");
		}
		return "¡Aviso Eliminado!";
	}

}
