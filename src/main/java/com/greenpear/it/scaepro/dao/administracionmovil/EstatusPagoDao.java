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

/**
 * @author EDSONJOSUE
 *
 */
public class EstatusPagoDao {
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

	public List<EstatusPagoModel> consultaAreas(String area, String estatusPago) throws SQLException {
		List<EstatusPagoModel> listaAreas = new ArrayList<EstatusPagoModel>();
		String sql = "SELECT * FROM t_empleado inner join c_turno on c_turno.idTurno = "
				+ "t_empleado.idTurno inner join c_area on c_turno.idArea = c_area.idArea"
				+ " inner join c_estatuspago on t_empleado.idEmpleado = c_estatuspago.idEmpleado"
				+ " inner join t_estatuspago on c_estatuspago.idEstatusPago = t_estatuspago.idEstatusPago"
				+ " where estatus = '" + estatusPago + "' and nombreArea ='" + area + "'";
		try {
			listaAreas = getJdbcTemplate().query(sql, new RowMapper<EstatusPagoModel>() {

				public EstatusPagoModel mapRow(ResultSet rs, int columna) throws SQLException {
					EstatusPagoModel resultValue = new EstatusPagoModel();
					resultValue.setIdEstatusPago(rs.getInt("idEstatus"));
					resultValue.setNombreEmpleado(rs.getString("apePatEmpleado") + " " + rs.getString("apeMatEmpleado")
							+ " " + rs.getString("nombreEmpleado"));
					resultValue.setDiaDePago(rs.getInt("diaDePago"));
					resultValue.setFechaPago(rs.getString("fechaPago"));
					resultValue.setStatusPago(rs.getString("estatus"));
					resultValue.setPeriodoNominal(rs.getString("periodoNominal"));
					resultValue.setComentario(rs.getString("comentario"));
					return resultValue;
				}
			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}
		return listaAreas;
	}

	public List<EstatusPagoModel> llenarAreas() throws SQLException {
		List<EstatusPagoModel> listaAreas = new ArrayList<EstatusPagoModel>();
		String sql = "SELECT * FROM c_area order by nombreArea";
		try {
			listaAreas = getJdbcTemplate().query(sql, new RowMapper<EstatusPagoModel>() {

				public EstatusPagoModel mapRow(ResultSet rs, int columna) throws SQLException {
					EstatusPagoModel resultValue = new EstatusPagoModel();
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

	public EstatusPagoModel consultaArea(String empleado) throws SQLException {
		EstatusPagoModel modeloConsulta = new EstatusPagoModel();
		String sql = "select * from t_empleado where CONCAT(apePatEmpleado,' ',apeMatEmpleado,' ',nombreEmpleado) ='"
				+ empleado + "'";
		try {
			modeloConsulta = getJdbcTemplate().query(sql, new ResultSetExtractor<EstatusPagoModel>() {
				public EstatusPagoModel extractData(ResultSet rs) throws SQLException {
					EstatusPagoModel resultValue = new EstatusPagoModel();
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
		return modeloConsulta;
	}
	//Actualización de STATUS PAGO
	public String insertarStatusPago(EstatusPagoModel modeloConsulta) throws SQLException {
		String sqlEdicion = "UPDATE c_estatuspago SET idEstatusPago=?,comentario=?,fechaPago=? WHERE idEstatus=?";
		try {
			System.out.println(getJdbcTemplate().update(sqlEdicion, modeloConsulta.getIdStatusPago(),
					modeloConsulta.getComentario(),modeloConsulta.getFechaActual(), modeloConsulta.getIdEstatusPago()));
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo: {} ", e.getMessage());
			throw new SQLException(
					"Existe un problema con la base de datos\n" + "No se pudo realizar la actualización!");
		}
		return "!Registros De Status De Pago Exitosos!";
	}

	public List<EstatusPagoModel> consultaFechas() throws SQLException {
		List<EstatusPagoModel> listaAreas = new ArrayList<EstatusPagoModel>();
		String sql = " SELECT c_estatuspago.idEstatus,periodoNominal,estatus,fechaPago "
				+ "FROM t_empleado inner join c_turno on c_turno.idTurno = "
				+ "t_empleado.idTurno inner join c_area on c_turno.idArea = c_area.idArea "
				+ "inner join c_estatuspago on t_empleado.idEmpleado = c_estatuspago.idEmpleado "
				+ "inner join t_estatuspago on c_estatuspago.idEstatusPago = t_estatuspago.idEstatusPago "
				+ "where estatus = 'Pagado'";
		try {
			listaAreas = getJdbcTemplate().query(sql, new RowMapper<EstatusPagoModel>() {

				public EstatusPagoModel mapRow(ResultSet rs, int columna) throws SQLException {
					EstatusPagoModel resultValue = new EstatusPagoModel();
					resultValue.setIdEstatusPago(rs.getInt("idEstatus"));
					resultValue.setFechaPago(rs.getString("fechaPago"));
					resultValue.setStatusPago(rs.getString("estatus"));
					resultValue.setPeriodoNominal(rs.getString("periodoNominal"));
					return resultValue;
				}
			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}
		return listaAreas;
	}

	public String editar(EstatusPagoModel fechasPagosModel) throws SQLException {
		String sqlEdicion = "UPDATE c_estatuspago SET idEstatusPago=? WHERE idEstatus=?";
		try {
			System.out.println(getJdbcTemplate().update(sqlEdicion, 2, fechasPagosModel.getIdEstatusPago()));
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo: {} ", e.getMessage());
			throw new SQLException(
					"Existe un problema con la base de datos\n" + "No se pudo realizar la actualización!");
		}
			return "Actualizado";
	}

}
