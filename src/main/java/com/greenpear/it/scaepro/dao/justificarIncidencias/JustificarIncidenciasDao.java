package com.greenpear.it.scaepro.dao.justificarIncidencias;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.greenpear.it.scaepro.model.empleado.EmpleadoModel;
import com.greenpear.it.scaepro.model.incidencia.IncidenciaModel;
import com.greenpear.it.scaepro.model.incidencia.JustificanteIncidenciaModel;

public class JustificarIncidenciasDao {
	private static final Logger log = LoggerFactory.getLogger("empleadoDaoService");
	private JdbcTemplate jdbcTemplate;

	@Autowired
	@Qualifier("dataSource")
	public void setDataSource(DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	// Setters and Getters

	//
	public List<IncidenciaModel> consultaGeneralEmpleados() throws SQLException {
		List<IncidenciaModel> listaEmpleados = new ArrayList<IncidenciaModel>();
		String sql = "select t_empleado.idEmpleado,nombreEmpleado, apepatEmpleado,apematEmpleado,nombrearea,tipoincidencia,"
				+ "fechaIncidencia,estatus from c_area inner join c_turno on c_area.idArea=c_turno.idArea "
				+ "inner join t_empleado on c_turno.idTurno=t_empleado.idTurno "
				+ "inner join t_incidencia on t_empleado.idEmpleado=t_incidencia.idEmpleado "
				+ "inner join c_tipo_incidencia on c_tipo_incidencia.idtipoincidencia=t_incidencia.idtipoincidencia "
				+ "inner join t_estatusincidencia on t_incidencia.idestatusincidencia=t_estatusincidencia.idestatusincidencia";

		try {
			listaEmpleados = getJdbcTemplate().query(sql, new RowMapper<IncidenciaModel>() {

				public IncidenciaModel mapRow(ResultSet rs, int columna) throws SQLException {
					IncidenciaModel resultValue = new IncidenciaModel();
					resultValue.setIdEmpleado(rs.getInt("idEmpleado"));
					resultValue.setNombreEmpleado(rs.getString("nombreempleado"));
					resultValue.setApePatEmpleado(rs.getString("apepatempleado"));
					resultValue.setApeMatEmpleado(rs.getString("apematempleado"));
					resultValue.setArea(rs.getString("nombrearea"));
					resultValue.setTipo(rs.getString("tipoincidencia"));
					resultValue.setFechaIncidencia(rs.getString("fechaIncidencia"));
					resultValue.setEstatusIncidencia(rs.getString("estatus"));
					return resultValue;
				}

			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}
		System.out.println(listaEmpleados.size());
		return listaEmpleados;
	}

	public EmpleadoModel consultarFotografia(String idEmpleado)throws SQLException {
		EmpleadoModel empleadoModel = new EmpleadoModel();
		String sql = "select fotografia from t_empleado where idEmpleado="+idEmpleado;
		try {
			empleadoModel = getJdbcTemplate().query(sql, new ResultSetExtractor<EmpleadoModel>() {
				public EmpleadoModel extractData(ResultSet rs) throws SQLException {
					EmpleadoModel empleado = new EmpleadoModel();
					if (rs.next()) {
						empleado.setFotografia(rs.getString("fotografia"));
					}
					return empleado;
				}
			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo: {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la inserciï¿½n!");
		}
		return empleadoModel;
	}

	public JustificanteIncidenciaModel consultarComentarioArchivo(String idEmpleado)throws SQLException {
		JustificanteIncidenciaModel justificante=new JustificanteIncidenciaModel();
		String sql="select imagen,comentario,fecha from t_justificarincidencia "
				+ "inner join t_incidencia on t_justificarincidencia.idIncidencia=t_incidencia.idIncidencia "
				+ "inner join t_empleado on t_incidencia.idEmpleado=t_empleado.idEmpleado "
				+ "where t_empleado.idEmpleado="+idEmpleado;
		try {
			justificante = getJdbcTemplate().query(sql, new ResultSetExtractor<JustificanteIncidenciaModel>() {
				public JustificanteIncidenciaModel extractData(ResultSet rs) throws SQLException {
					JustificanteIncidenciaModel justificante = new JustificanteIncidenciaModel();
					if (rs.next()) {
						justificante.setJustificante(rs.getString("imagen"));
						justificante.setComentario(rs.getString("comentario"));
						justificante.setFechaJustificacion(rs.getString("fecha"));
					}
					return justificante;
				}
			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo: {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la inserciï¿½n!");
		}
		return justificante;
	}

}
