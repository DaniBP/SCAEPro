package com.greenpear.it.scaepro.dao.configurarempleados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.swing.JOptionPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.greenpear.it.scaepro.model.administracionmovil.EstatusPagoModel;
import com.greenpear.it.scaepro.model.direccion.DireccionModelo;
import com.greenpear.it.scaepro.model.empleado.EmpleadoModel;
import com.greenpear.it.scaepro.model.gestionareas.ConsultaAreasModel;
import com.greenpear.it.scaepro.model.turno.TurnoModel;
import com.greenpear.it.scaepro.services.DataSourceService;
import com.greenpear.it.scaepro.services.SelectAllService;

@Repository("empleadoDaoService")
public class ConfigurarEmpleadoDao extends DataSourceService implements SelectAllService<EmpleadoModel> {
	private static final Logger log = LoggerFactory.getLogger("empleadoDaoService");
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private DireccionModelo direccionModelo;
	@Autowired
	private TurnoModel turnoModelo;

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

	public DireccionModelo getDireccionModelo() {
		return direccionModelo;
	}

	public TurnoModel getTurnoModelo() {
		return turnoModelo;
	}

	public void setDireccionModelo(DireccionModelo direccionModelo) {
		this.direccionModelo = direccionModelo;
	}

	@Override
	public List<EmpleadoModel> consultaGeneral() throws SQLException {
		List<EmpleadoModel> listaAreas = new ArrayList<EmpleadoModel>();
		String sql = "SELECT * FROM c_area";

		try {
			listaAreas = getJdbcTemplate().query(sql, new RowMapper<EmpleadoModel>() {

				public EmpleadoModel mapRow(ResultSet rs, int columna) throws SQLException {
					EmpleadoModel resultValue = new EmpleadoModel();
					resultValue.setArea(rs.getString("nombreArea"));
					return resultValue;
				}

			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}

		return listaAreas;
	}

	public List<DireccionModelo> consultarDireccion() throws SQLException {
		List<DireccionModelo> listaDireccion = new ArrayList<DireccionModelo>();
		String sql = "SELECT CodigoPostal,Estado,Municipio,Colonia FROM t_direccionCp WHERE CodigoPostal =" + "'"
				+ getDireccionModelo().getCp() + "'";
		try {
			listaDireccion = getJdbcTemplate().query(sql, new RowMapper<DireccionModelo>() {

				public DireccionModelo mapRow(ResultSet rs, int columna) throws SQLException {
					DireccionModelo resultValue = new DireccionModelo();
					resultValue.setCp(rs.getString("CodigoPostal"));
					resultValue.setEstado(rs.getString("Estado"));
					resultValue.setMunicipio(rs.getString("Municipio"));
					resultValue.setColonia(rs.getString("Colonia"));
					return resultValue;
				}

			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}

		return listaDireccion;
	}

	public List<TurnoModel> consultarTurno() throws SQLException {
		List<TurnoModel> listaAreas = new ArrayList<TurnoModel>();
		String sql = "SELECT nombreTurno from c_turno INNER JOIN c_area on c_turno.idArea=c_area.idArea WHERE c_area.nombreArea='"
				+ getTurnoModelo().getArea() + "'";
		try {
			listaAreas = getJdbcTemplate().query(sql, new RowMapper<TurnoModel>() {

				public TurnoModel mapRow(ResultSet rs, int columna) throws SQLException {
					TurnoModel resultValue = new TurnoModel();
					resultValue.setNombreTurno(rs.getString("nombreTurno"));
					return resultValue;
				}

			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}

		return listaAreas;
	}

	public String insertarDireccionEmpleado(DireccionModelo direccionModelo2) throws SQLException {
		try {
			SimpleJdbcInsert insert = new SimpleJdbcInsert(getDataSource());

			insert.setTableName("t_direccionempleado");
			insert.setGeneratedKeyName("idDireccionEmpleado");

			Map<String, Object> parameters = new HashMap<String, Object>();

			parameters.put("codigoPostal", direccionModelo2.getCp());
			parameters.put("calle", direccionModelo2.getCalle());
			parameters.put("numIn", direccionModelo2.getNumIn());
			parameters.put("numEx", direccionModelo2.getNumEx());
			parameters.put("UniColonia", direccionModelo2.getUniColonia());

			direccionModelo2.setIdDireccion(insert.executeAndReturnKey(parameters).intValue());

		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo: {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la inserciï¿½n!");
		}

		return "La direccion con el cp" + direccionModelo2.getCp() + "\n" + "Fue registrado exitosamente!\n\n"
				+ "Su id de DireccionEmpleado es: " + direccionModelo2.getIdDireccion() + " con la colonia" + " "
				+ getDireccionModelo().getUniColonia();
	}

	public EmpleadoModel consultarIdEmpleado() throws SQLException {
		EmpleadoModel empleadoModel = new EmpleadoModel();
		String sql = "select idEmpleado from t_empleado ORDER BY IDEMPLEADO DESC LIMIT 1";
		try {
			empleadoModel = getJdbcTemplate().query(sql, new ResultSetExtractor<EmpleadoModel>() {
				public EmpleadoModel extractData(ResultSet rs) throws SQLException {
					EmpleadoModel empleado = new EmpleadoModel();
					if (rs.next()) {
						empleado.setIdEmpleado(rs.getInt("idEmpleado"));
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

	public TurnoModel consultarIdArea(TurnoModel turnoModelo2) throws SQLException {
		TurnoModel turnomodel = new TurnoModel();
		String sql = "select idArea from c_area where nombreArea='" + turnoModelo2.getArea() + "'";
		try {
			turnomodel = getJdbcTemplate().query(sql, new ResultSetExtractor<TurnoModel>() {
				public TurnoModel extractData(ResultSet rs) throws SQLException {
					TurnoModel turno = new TurnoModel();
					if (rs.next()) {
						turno.setIdArea(rs.getInt("idArea"));
					}
					return turno;
				}
			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo: {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la inserciï¿½n!");
		}
		return turnomodel;
	}

	public TurnoModel consultarIdTurno(TurnoModel turnoModelo3) throws SQLException {
		TurnoModel turnomodel = new TurnoModel();
		String sql = "select idTurno from c_turno inner join c_area on c_turno.idArea=c_area.idArea"
				+ " where nombreArea='" + turnoModelo3.getArea() + "' and nombreTurno='" + turnoModelo3.getNombreTurno()
				+ "'";
		try {
			turnomodel = getJdbcTemplate().query(sql, new ResultSetExtractor<TurnoModel>() {
				public TurnoModel extractData(ResultSet rs) throws SQLException {
					TurnoModel turno = new TurnoModel();
					if (rs.next()) {
						turno.setIdTurno(rs.getInt("idTurno"));
					}
					return turno;
				}
			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo: {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la inserciï¿½n!");
		}
		return turnomodel;
	}

	public String registrarEmpleado(EmpleadoModel configurarEmpleadosModel) throws SQLException {
		try {
			SimpleJdbcInsert insert = new SimpleJdbcInsert(getDataSource());

			insert.setTableName("t_empleado");
			insert.setGeneratedKeyName("idEmpleado");

			Map<String, Object> parameters = new HashMap<String, Object>();

			parameters.put("nombreEmpleado", configurarEmpleadosModel.getNombreEmpleado());
			parameters.put("apePatEmpleado", configurarEmpleadosModel.getApePatEmpleado());
			parameters.put("apeMatEmpleado", configurarEmpleadosModel.getApeMatEmpleado());
			parameters.put("fechaNacimiento", configurarEmpleadosModel.getFechaNacimiento());
			parameters.put("telCel", configurarEmpleadosModel.getTelCel());
			parameters.put("telCasa", configurarEmpleadosModel.getTelCasa());
			parameters.put("idDireccionEmpleado", configurarEmpleadosModel.getIdDireccionEmpleado());
			parameters.put("puesto", configurarEmpleadosModel.getPuesto());
			parameters.put("idTurno", configurarEmpleadosModel.getIdTurno());
			parameters.put("fotografia", configurarEmpleadosModel.getFotografia());
			parameters.put("periodoNominal", configurarEmpleadosModel.getPeriodoNominal());
			parameters.put("diaDePago", configurarEmpleadosModel.getDiaDePago());
			parameters.put("usuarioEmpleado", configurarEmpleadosModel.getNombreUsuario());
			parameters.put("passwordEmpleado", configurarEmpleadosModel.getPassword());
			configurarEmpleadosModel.setIdEmpleado(insert.executeAndReturnKey(parameters).intValue());

		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo: {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la insercion!");
		}

		registrarHuella(configurarEmpleadosModel);

		return "El empleado " + configurarEmpleadosModel.getNombreEmpleado() + " "
				+ configurarEmpleadosModel.getApePatEmpleado() + "" + " " + configurarEmpleadosModel.getApeMatEmpleado()
				+ "\n" + "Fue registrado exitosamente!\n\n" + "Su usuario para la app movil es: "
				+ configurarEmpleadosModel.getNombreUsuario() + "\n" + "Su contraseña " + "\n"
				+ configurarEmpleadosModel.getPassword() + "\n" + "\n" + "Recuerde cambiarla al iniciar sesion.";
	}

	public void registrarEstatusPago(EstatusPagoModel estatusPago) throws SQLException {
		try {
			SimpleJdbcInsert insert = new SimpleJdbcInsert(getDataSource());

			insert.setTableName("c_estatuspago");
			insert.setGeneratedKeyName("idEstatus");

			Map<String, Object> parameters = new HashMap<String, Object>();

			parameters.put("idEmpleado", estatusPago.getIdEmpleado());
			parameters.put("idEstatusPago", estatusPago.getIdEstatusPago());
			parameters.put("comentario", estatusPago.getComentario());
			parameters.put("fechaPago", estatusPago.getFechaPago());

			estatusPago.setIdStatusPago(insert.executeAndReturnKey(parameters).intValue());

		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo: {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n"
					+ "No se pudo realizar la insercion del estatus de pago!");
		}
	}

	public void registrarHuella(EmpleadoModel empleadoModel) {
		try {
			Connection c = conectar();
			PreparedStatement guardarHuella = c
					.prepareStatement("UPDATE t_empleado SET " + "huellaEmpleado = ? " + "WHERE " + "idEmpleado = ?");

			guardarHuella.setBinaryStream(1, empleadoModel.getDatosHuella(), empleadoModel.getTamanoHuella());
			guardarHuella.setInt(2, empleadoModel.getIdEmpleado());

			guardarHuella.execute();
			guardarHuella.close();
			desconectar();
		} catch (SQLException ex) {
			System.err.println("Error al guardar los datos de la huella.");
		} finally {
			desconectar();
		}
	}

	public List<EmpleadoModel> consultaGeneralEmpleados() throws SQLException {
		List<EmpleadoModel> listaEmpleados = new ArrayList<EmpleadoModel>();
		String sql = "select idEmpleado,nombreempleado,apepatempleado,apematempleado,"
				+ "c_turno.idArea,c_area.nombreArea,c_turno.idTurno, c_turno.nombreTurno from t_empleado "
				+ "inner join c_turno on t_empleado.idTurno=c_turno.idTurno "
				+ "inner join c_area on c_turno.idArea=c_area.idArea ORDER BY c_area.nombreArea";

		try {
			listaEmpleados = getJdbcTemplate().query(sql, new RowMapper<EmpleadoModel>() {

				public EmpleadoModel mapRow(ResultSet rs, int columna) throws SQLException {
					EmpleadoModel resultValue = new EmpleadoModel();
					resultValue.setIdEmpleado(rs.getInt("idEmpleado"));
					resultValue.setNombreEmpleado(rs.getString("nombreempleado"));
					resultValue.setApePatEmpleado(rs.getString("apepatempleado"));
					resultValue.setApeMatEmpleado(rs.getString("apematempleado"));
					resultValue.setIdArea(rs.getInt("idArea"));
					resultValue.setArea(rs.getString("nombreArea"));
					resultValue.setIdTurno(rs.getInt("idTurno"));
					resultValue.setNombreTurno(rs.getString("nombreTurno"));
					return resultValue;
				}

			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}

		return listaEmpleados;
	}

	public EmpleadoModel consultaModificarEmpleado(String idEmpleado) throws SQLException {
		EmpleadoModel empleados = new EmpleadoModel();
		String sql = "SELECT * FROM t_empleado WHERE idEmpleado=" + idEmpleado;
		try {
			empleados = getJdbcTemplate().query(sql, new ResultSetExtractor<EmpleadoModel>() {
				public EmpleadoModel extractData(ResultSet rs) throws SQLException {
					if (rs.next()) {
						EmpleadoModel resultValue = new EmpleadoModel();
						resultValue.setIdEmpleado(rs.getInt("idEmpleado"));
						resultValue.setNombreEmpleado(rs.getString("nombreEmpleado"));
						resultValue.setApePatEmpleado(rs.getString("apePatEmpleado"));
						resultValue.setApeMatEmpleado(rs.getString("apeMatEmpleado"));
						resultValue.setFechaNacimiento(rs.getString("fechaNacimiento"));
						resultValue.setTelCel(rs.getString("telCel"));
						resultValue.setTelCasa(rs.getString("telCasa"));
						resultValue.setIdDireccionEmpleado(rs.getInt("idDireccionEmpleado"));
						resultValue.setPuesto(rs.getString("puesto"));
						resultValue.setIdTurno(rs.getInt("idTurno"));
						resultValue.setFotografia(rs.getString("fotografia"));
						resultValue.setPeriodoNominal(rs.getString("periodoNominal"));
						resultValue.setDiaDePago(rs.getInt("diaDePago"));
						resultValue.setHuellaEmpleado(rs.getBytes("huellaEmpleado"));
						resultValue.setNombreUsuario(rs.getString("usuarioEmpleado"));
						resultValue.setPassword(rs.getString("passwordEmpleado"));
						return resultValue;
					}
					return null;
				}
			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}
		return empleados;
	}

	public DireccionModelo consultarCpDireccionEmpleado(int idDireccionEmpleado) throws SQLException {
		DireccionModelo direccionModelo = new DireccionModelo();
		String sql = "SELECT codigoPostal,uniColonia,calle,numEx,numIn FROM t_direccionEmpleado WHERE idDireccionEmpleado="
				+ idDireccionEmpleado;
		try {
			direccionModelo = getJdbcTemplate().query(sql, new ResultSetExtractor<DireccionModelo>() {
				public DireccionModelo extractData(ResultSet rs) throws SQLException {
					if (rs.next()) {
						DireccionModelo resultValue = new DireccionModelo();
						resultValue.setCp(rs.getString("codigoPostal"));
						resultValue.setUniColonia(rs.getString("uniColonia"));
						resultValue.setCalle(rs.getString("calle"));
						resultValue.setNumEx(rs.getString("numEx"));
						resultValue.setNumIn(rs.getString("numIn"));
						return resultValue;
					}
					return null;
				}
			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}
		return direccionModelo;
	}

	public TurnoModel consultarAreaEmpleado(int idTurno) throws SQLException {
		TurnoModel turnoModel = new TurnoModel();
		String sql = "SELECT c_area.nombreArea,c_turno.nombreTurno from c_area "
				+ "inner join c_turno on c_area.idArea=c_turno.idArea where idTurno=" + idTurno;
		try {
			turnoModel = getJdbcTemplate().query(sql, new ResultSetExtractor<TurnoModel>() {
				public TurnoModel extractData(ResultSet rs) throws SQLException {
					if (rs.next()) {
						TurnoModel resultValue = new TurnoModel();
						resultValue.setArea(rs.getString("nombreArea"));
						resultValue.setNombreTurno(rs.getString("nombreTurno"));
						return resultValue;
					}
					return null;
				}
			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}
		return turnoModel;
	}

	public String modificarEmpleado(EmpleadoModel configurarEmpleadosModel) throws SQLException {
		String sql = "UPDATE t_empleado SET nombreEmpleado=?, apePatEmpleado=?,apeMatEmpleado=?,"
				+ "fechaNacimiento=?,telCel=?,telCasa=?,idDireccionEmpleado=?,"
				+ "puesto=?,idTurno=?,fotografia=?,periodoNominal=?,diaDePago=? "
				+ "WHERE idEmpleado=?";
		try {

			getJdbcTemplate().update(sql,
		configurarEmpleadosModel.getNombreEmpleado(),
		configurarEmpleadosModel.getApePatEmpleado(),
		configurarEmpleadosModel.getApeMatEmpleado(),
		configurarEmpleadosModel.getFechaNacimiento(),
		configurarEmpleadosModel.getTelCel(),
		configurarEmpleadosModel.getTelCasa(),
		configurarEmpleadosModel.getIdDireccionEmpleado(),
		configurarEmpleadosModel.getPuesto(),
		configurarEmpleadosModel.getIdTurno(),
		configurarEmpleadosModel.getFotografia(),
		configurarEmpleadosModel.getPeriodoNominal(),
		configurarEmpleadosModel.getDiaDePago(),
		configurarEmpleadosModel.getIdEmpleado());

		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo: {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la inserion!");
		}

		if (configurarEmpleadosModel.getTamanoHuella() != 0) {
			registrarHuella(configurarEmpleadosModel);
		}

		return "El empleado " + configurarEmpleadosModel.getNombreEmpleado() + " "
				+ configurarEmpleadosModel.getApePatEmpleado() + "" + " " + configurarEmpleadosModel.getApeMatEmpleado()
				+ "\n" + "Fue actualizado exitosamente!\n\n"
				+ "Recuerde que puede cambiar su usuario y contraseña dentro la app movil";
	}

	public String eliminarEmpleado(EmpleadoModel configurarEmpleadosModel) throws SQLException {
		String sql = "DELETE FROM t_empleado WHERE idEmpleado=?";
		try {
			getJdbcTemplate().update(sql, configurarEmpleadosModel.getIdEmpleado());
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la eliminaci�n!");
		}
		return "El empleado fue eliminado correctamente!";
	}

	public EmpleadoModel validarEmpleado(String nombres, String apepat, String apemat) throws SQLException {
		EmpleadoModel empleadoModel = new EmpleadoModel();
		String sql = "select nombreEmpleado,apePatEmpleado,apeMatEmpleado from t_empleado where nombreEmpleado='"
				+ nombres + "' and apePatEmpleado='" + apepat + "' and apeMatEmpleado='" + apemat + "'";
		System.out.println(sql);
		try {
			empleadoModel = getJdbcTemplate().query(sql, new ResultSetExtractor<EmpleadoModel>() {

				public EmpleadoModel extractData(ResultSet rs) throws SQLException {
					if (rs.next()) {
						EmpleadoModel resultValue = new EmpleadoModel();
						resultValue.setNombreEmpleado(rs.getString("nombreEmpleado"));
						System.out.println(rs.getString("nombreEmpleado"));
						resultValue.setApePatEmpleado(rs.getString("apePatEmpleado"));
						System.out.println(rs.getString("nombreEmpleado"));
						resultValue.setApeMatEmpleado(rs.getString("apeMatEmpleado"));
						System.out.println(rs.getString("nombreEmpleado"));
						return resultValue;
					}
					return null;
				}
			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}
		return empleadoModel;
	}

	public String eliminarDireccion(EmpleadoModel configurarEmpleadosModel) throws SQLException {
		String sql = "DELETE FROM t_direccionempleado WHERE idDireccionEmpleado=?";
		try {
			getJdbcTemplate().update(sql, configurarEmpleadosModel.getIdDireccionEmpleado());
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la eliminaci�n!");
		}
		return "La direccion del empleado fue eliminada correctamente!";
	}

	public List<EmpleadoModel> consultaNombreCompletoEmpleado(EmpleadoModel configurarEmpleadosModel)
			throws SQLException {
		List<EmpleadoModel> listaEmpleados = new ArrayList<EmpleadoModel>();
		String sql = "select idEmpleado,nombreempleado,apepatempleado,apematempleado,"
				+ "c_turno.idArea,c_area.nombreArea,c_turno.idTurno, c_turno.nombreTurno from t_empleado "
				+ "inner join c_turno on t_empleado.idTurno=c_turno.idTurno "
				+ "inner join c_area on c_turno.idArea=c_area.idArea" + " where nombreEmpleado='"
				+ configurarEmpleadosModel.getNombreEmpleado() + "' " + "and apePatEmpleado='"
				+ configurarEmpleadosModel.getApePatEmpleado() + "' " + "and apeMatEmpleado='"
				+ configurarEmpleadosModel.getApeMatEmpleado() + "' " + "ORDER BY c_area.nombreArea";

		try {
			listaEmpleados = getJdbcTemplate().query(sql, new RowMapper<EmpleadoModel>() {

				public EmpleadoModel mapRow(ResultSet rs, int columna) throws SQLException {
					EmpleadoModel resultValue = new EmpleadoModel();
					resultValue.setIdEmpleado(rs.getInt("idEmpleado"));
					resultValue.setNombreEmpleado(rs.getString("nombreempleado"));
					resultValue.setApePatEmpleado(rs.getString("apepatempleado"));
					resultValue.setApeMatEmpleado(rs.getString("apematempleado"));
					resultValue.setIdArea(rs.getInt("idArea"));
					resultValue.setArea(rs.getString("nombreArea"));
					resultValue.setIdTurno(rs.getInt("idTurno"));
					resultValue.setNombreTurno(rs.getString("nombreTurno"));
					return resultValue;
				}

			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}

		return listaEmpleados;
	}

	public List<EmpleadoModel> consultaNombreEmpleado(EmpleadoModel configurarEmpleadosModel) throws SQLException {
		List<EmpleadoModel> listaEmpleados = new ArrayList<EmpleadoModel>();
		String sql = "select idEmpleado,nombreempleado,apepatempleado,apematempleado,"
				+ "c_turno.idArea,c_area.nombreArea,c_turno.idTurno, c_turno.nombreTurno from t_empleado "
				+ "inner join c_turno on t_empleado.idTurno=c_turno.idTurno "
				+ "inner join c_area on c_turno.idArea=c_area.idArea" + " where nombreEmpleado='"
				+ configurarEmpleadosModel.getNombreEmpleado() + "' " + "ORDER BY c_area.nombreArea";

		try {
			listaEmpleados = getJdbcTemplate().query(sql, new RowMapper<EmpleadoModel>() {

				public EmpleadoModel mapRow(ResultSet rs, int columna) throws SQLException {
					EmpleadoModel resultValue = new EmpleadoModel();
					resultValue.setIdEmpleado(rs.getInt("idEmpleado"));
					resultValue.setNombreEmpleado(rs.getString("nombreempleado"));
					resultValue.setApePatEmpleado(rs.getString("apepatempleado"));
					resultValue.setApeMatEmpleado(rs.getString("apematempleado"));
					resultValue.setIdArea(rs.getInt("idArea"));
					resultValue.setArea(rs.getString("nombreArea"));
					resultValue.setIdTurno(rs.getInt("idTurno"));
					resultValue.setNombreTurno(rs.getString("nombreTurno"));
					return resultValue;
				}

			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}

		return listaEmpleados;
	}

	public List<EmpleadoModel> consultaApePatEmpleado(EmpleadoModel configurarEmpleadosModel) throws SQLException {
		List<EmpleadoModel> listaEmpleados = new ArrayList<EmpleadoModel>();
		String sql = "select idEmpleado,nombreempleado,apepatempleado,apematempleado,"
				+ "c_turno.idArea,c_area.nombreArea,c_turno.idTurno, c_turno.nombreTurno from t_empleado "
				+ "inner join c_turno on t_empleado.idTurno=c_turno.idTurno "
				+ "inner join c_area on c_turno.idArea=c_area.idArea" + " where apePatEmpleado='"
				+ configurarEmpleadosModel.getApePatEmpleado() + "' " + "ORDER BY c_area.nombreArea";

		try {
			listaEmpleados = getJdbcTemplate().query(sql, new RowMapper<EmpleadoModel>() {

				public EmpleadoModel mapRow(ResultSet rs, int columna) throws SQLException {
					EmpleadoModel resultValue = new EmpleadoModel();
					resultValue.setIdEmpleado(rs.getInt("idEmpleado"));
					resultValue.setNombreEmpleado(rs.getString("nombreempleado"));
					resultValue.setApePatEmpleado(rs.getString("apepatempleado"));
					resultValue.setApeMatEmpleado(rs.getString("apematempleado"));
					resultValue.setIdArea(rs.getInt("idArea"));
					resultValue.setArea(rs.getString("nombreArea"));
					resultValue.setIdTurno(rs.getInt("idTurno"));
					resultValue.setNombreTurno(rs.getString("nombreTurno"));
					return resultValue;
				}

			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}

		return listaEmpleados;
	}

	public List<EmpleadoModel> consultaApeMatEmpleado(EmpleadoModel configurarEmpleadosModel) throws SQLException {
		List<EmpleadoModel> listaEmpleados = new ArrayList<EmpleadoModel>();
		String sql = "select idEmpleado,nombreempleado,apepatempleado,apematempleado,"
				+ "c_turno.idArea,c_area.nombreArea,c_turno.idTurno, c_turno.nombreTurno from t_empleado "
				+ "inner join c_turno on t_empleado.idTurno=c_turno.idTurno "
				+ "inner join c_area on c_turno.idArea=c_area.idArea" + " where apeMatEmpleado='"
				+ configurarEmpleadosModel.getApeMatEmpleado() + "' " + "ORDER BY c_area.nombreArea";

		try {
			listaEmpleados = getJdbcTemplate().query(sql, new RowMapper<EmpleadoModel>() {

				public EmpleadoModel mapRow(ResultSet rs, int columna) throws SQLException {
					EmpleadoModel resultValue = new EmpleadoModel();
					resultValue.setIdEmpleado(rs.getInt("idEmpleado"));
					resultValue.setNombreEmpleado(rs.getString("nombreempleado"));
					resultValue.setApePatEmpleado(rs.getString("apepatempleado"));
					resultValue.setApeMatEmpleado(rs.getString("apematempleado"));
					resultValue.setIdArea(rs.getInt("idArea"));
					resultValue.setArea(rs.getString("nombreArea"));
					resultValue.setIdTurno(rs.getInt("idTurno"));
					resultValue.setNombreTurno(rs.getString("nombreTurno"));
					return resultValue;
				}

			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}

		return listaEmpleados;
	}

	public List<EmpleadoModel> consultaNomApatEmpleado(EmpleadoModel configurarEmpleadosModel) throws SQLException {
		List<EmpleadoModel> listaEmpleados = new ArrayList<EmpleadoModel>();
		String sql = "select idEmpleado,nombreempleado,apepatempleado,apematempleado,"
				+ "c_turno.idArea,c_area.nombreArea,c_turno.idTurno, c_turno.nombreTurno from t_empleado "
				+ "inner join c_turno on t_empleado.idTurno=c_turno.idTurno "
				+ "inner join c_area on c_turno.idArea=c_area.idArea" + " where apePatEmpleado='"
				+ configurarEmpleadosModel.getApePatEmpleado() + "' " + "and nombreempleado='"
				+ configurarEmpleadosModel.getNombreEmpleado() + "' " + "ORDER BY c_area.nombreArea";

		try {
			listaEmpleados = getJdbcTemplate().query(sql, new RowMapper<EmpleadoModel>() {

				public EmpleadoModel mapRow(ResultSet rs, int columna) throws SQLException {
					EmpleadoModel resultValue = new EmpleadoModel();
					resultValue.setIdEmpleado(rs.getInt("idEmpleado"));
					resultValue.setNombreEmpleado(rs.getString("nombreempleado"));
					resultValue.setApePatEmpleado(rs.getString("apepatempleado"));
					resultValue.setApeMatEmpleado(rs.getString("apematempleado"));
					resultValue.setIdArea(rs.getInt("idArea"));
					resultValue.setArea(rs.getString("nombreArea"));
					resultValue.setIdTurno(rs.getInt("idTurno"));
					resultValue.setNombreTurno(rs.getString("nombreTurno"));
					return resultValue;
				}

			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}

		return listaEmpleados;
	}

	public List<EmpleadoModel> consultaNomAmatEmpleado(EmpleadoModel configurarEmpleadosModel) throws SQLException {
		List<EmpleadoModel> listaEmpleados = new ArrayList<EmpleadoModel>();
		String sql = "select idEmpleado,nombreempleado,apepatempleado,apematempleado,"
				+ "c_turno.idArea,c_area.nombreArea,c_turno.idTurno, c_turno.nombreTurno from t_empleado "
				+ "inner join c_turno on t_empleado.idTurno=c_turno.idTurno "
				+ "inner join c_area on c_turno.idArea=c_area.idArea" + " where apeMatEmpleado='"
				+ configurarEmpleadosModel.getApeMatEmpleado() + "' " + "and nombreempleado='"
				+ configurarEmpleadosModel.getNombreEmpleado() + "' " + "ORDER BY c_area.nombreArea";

		try {
			listaEmpleados = getJdbcTemplate().query(sql, new RowMapper<EmpleadoModel>() {

				public EmpleadoModel mapRow(ResultSet rs, int columna) throws SQLException {
					EmpleadoModel resultValue = new EmpleadoModel();
					resultValue.setIdEmpleado(rs.getInt("idEmpleado"));
					resultValue.setNombreEmpleado(rs.getString("nombreempleado"));
					resultValue.setApePatEmpleado(rs.getString("apepatempleado"));
					resultValue.setApeMatEmpleado(rs.getString("apematempleado"));
					resultValue.setIdArea(rs.getInt("idArea"));
					resultValue.setArea(rs.getString("nombreArea"));
					resultValue.setIdTurno(rs.getInt("idTurno"));
					resultValue.setNombreTurno(rs.getString("nombreTurno"));
					return resultValue;
				}

			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}

		return listaEmpleados;
	}

	public List<EmpleadoModel> consultaApellidos(EmpleadoModel configurarEmpleadosModel) throws SQLException {
		List<EmpleadoModel> listaEmpleados = new ArrayList<EmpleadoModel>();
		String sql = "select idEmpleado,nombreempleado,apepatempleado,apematempleado,"
				+ "c_turno.idArea,c_area.nombreArea,c_turno.idTurno, c_turno.nombreTurno from t_empleado "
				+ "inner join c_turno on t_empleado.idTurno=c_turno.idTurno "
				+ "inner join c_area on c_turno.idArea=c_area.idArea" + " where apePatEmpleado='"
				+ configurarEmpleadosModel.getApePatEmpleado() + "' " + "and apeMatEmpleado='"
				+ configurarEmpleadosModel.getApeMatEmpleado() + "' " + "ORDER BY c_area.nombreArea";

		try {
			listaEmpleados = getJdbcTemplate().query(sql, new RowMapper<EmpleadoModel>() {

				public EmpleadoModel mapRow(ResultSet rs, int columna) throws SQLException {
					EmpleadoModel resultValue = new EmpleadoModel();
					resultValue.setIdEmpleado(rs.getInt("idEmpleado"));
					resultValue.setNombreEmpleado(rs.getString("nombreempleado"));
					resultValue.setApePatEmpleado(rs.getString("apepatempleado"));
					resultValue.setApeMatEmpleado(rs.getString("apematempleado"));
					resultValue.setIdArea(rs.getInt("idArea"));
					resultValue.setArea(rs.getString("nombreArea"));
					resultValue.setIdTurno(rs.getInt("idTurno"));
					resultValue.setNombreTurno(rs.getString("nombreTurno"));
					return resultValue;
				}

			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}

		return listaEmpleados;
	}

	public List<EmpleadoModel> consultaAsignacion(EmpleadoModel configurarEmpleadosModel) throws SQLException {
		List<EmpleadoModel> listaEmpleados = new ArrayList<EmpleadoModel>();
		String sql = "select idEmpleado,nombreempleado,apepatempleado,apematempleado,"
				+ "c_turno.idArea,c_area.nombreArea,c_turno.idTurno, c_turno.nombreTurno from t_empleado "
				+ "inner join c_turno on t_empleado.idTurno=c_turno.idTurno "
				+ "inner join c_area on c_turno.idArea=c_area.idArea" + " where c_area.nombreArea='"
				+ configurarEmpleadosModel.getArea() + "' " + "and c_turno.nombreTurno='"
				+ configurarEmpleadosModel.getNombreTurno() + "' " + "and puesto='"
				+ configurarEmpleadosModel.getPuesto() + "' " + "ORDER BY c_area.nombreArea";

		try {
			listaEmpleados = getJdbcTemplate().query(sql, new RowMapper<EmpleadoModel>() {

				public EmpleadoModel mapRow(ResultSet rs, int columna) throws SQLException {
					EmpleadoModel resultValue = new EmpleadoModel();
					resultValue.setIdEmpleado(rs.getInt("idEmpleado"));
					resultValue.setNombreEmpleado(rs.getString("nombreempleado"));
					resultValue.setApePatEmpleado(rs.getString("apepatempleado"));
					resultValue.setApeMatEmpleado(rs.getString("apematempleado"));
					resultValue.setIdArea(rs.getInt("idArea"));
					resultValue.setArea(rs.getString("nombreArea"));
					resultValue.setIdTurno(rs.getInt("idTurno"));
					resultValue.setNombreTurno(rs.getString("nombreTurno"));
					return resultValue;
				}

			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}

		return listaEmpleados;
	}

	public List<EmpleadoModel> consultaArea(EmpleadoModel configurarEmpleadosModel) throws SQLException {
		List<EmpleadoModel> listaEmpleados = new ArrayList<EmpleadoModel>();
		String sql = "select idEmpleado,nombreempleado,apepatempleado,apematempleado,"
				+ "c_turno.idArea,c_area.nombreArea,c_turno.idTurno, c_turno.nombreTurno from t_empleado "
				+ "inner join c_turno on t_empleado.idTurno=c_turno.idTurno "
				+ "inner join c_area on c_turno.idArea=c_area.idArea" + " where c_area.nombreArea='"
				+ configurarEmpleadosModel.getArea() + "' " + "ORDER BY c_area.nombreArea";

		try {
			listaEmpleados = getJdbcTemplate().query(sql, new RowMapper<EmpleadoModel>() {

				public EmpleadoModel mapRow(ResultSet rs, int columna) throws SQLException {
					EmpleadoModel resultValue = new EmpleadoModel();
					resultValue.setIdEmpleado(rs.getInt("idEmpleado"));
					resultValue.setNombreEmpleado(rs.getString("nombreempleado"));
					resultValue.setApePatEmpleado(rs.getString("apepatempleado"));
					resultValue.setApeMatEmpleado(rs.getString("apematempleado"));
					resultValue.setIdArea(rs.getInt("idArea"));
					resultValue.setArea(rs.getString("nombreArea"));
					resultValue.setIdTurno(rs.getInt("idTurno"));
					resultValue.setNombreTurno(rs.getString("nombreTurno"));
					return resultValue;
				}

			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}

		return listaEmpleados;
	}

	public List<EmpleadoModel> consultaAreaTurno(EmpleadoModel configurarEmpleadosModel) throws SQLException {
		List<EmpleadoModel> listaEmpleados = new ArrayList<EmpleadoModel>();
		String sql = "select idEmpleado,nombreempleado,apepatempleado,apematempleado,"
				+ "c_turno.idArea,c_area.nombreArea,c_turno.idTurno, c_turno.nombreTurno from t_empleado "
				+ "inner join c_turno on t_empleado.idTurno=c_turno.idTurno "
				+ "inner join c_area on c_turno.idArea=c_area.idArea" + " where c_area.nombreArea='"
				+ configurarEmpleadosModel.getArea() + "' " + "and c_turno.nombreTurno='"
				+ configurarEmpleadosModel.getNombreTurno() + "' " + "ORDER BY c_area.nombreArea";

		try {
			listaEmpleados = getJdbcTemplate().query(sql, new RowMapper<EmpleadoModel>() {

				public EmpleadoModel mapRow(ResultSet rs, int columna) throws SQLException {
					EmpleadoModel resultValue = new EmpleadoModel();
					resultValue.setIdEmpleado(rs.getInt("idEmpleado"));
					resultValue.setNombreEmpleado(rs.getString("nombreempleado"));
					resultValue.setApePatEmpleado(rs.getString("apepatempleado"));
					resultValue.setApeMatEmpleado(rs.getString("apematempleado"));
					resultValue.setIdArea(rs.getInt("idArea"));
					resultValue.setArea(rs.getString("nombreArea"));
					resultValue.setIdTurno(rs.getInt("idTurno"));
					resultValue.setNombreTurno(rs.getString("nombreTurno"));
					return resultValue;
				}

			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}

		return listaEmpleados;
	}

	public List<EmpleadoModel> consultaAreaPuesto(EmpleadoModel configurarEmpleadosModel) throws SQLException {
		List<EmpleadoModel> listaEmpleados = new ArrayList<EmpleadoModel>();
		String sql = "select idEmpleado,nombreempleado,apepatempleado,apematempleado,"
				+ "c_turno.idArea,c_area.nombreArea,c_turno.idTurno, c_turno.nombreTurno from t_empleado "
				+ "inner join c_turno on t_empleado.idTurno=c_turno.idTurno "
				+ "inner join c_area on c_turno.idArea=c_area.idArea" + " where c_area.nombreArea='"
				+ configurarEmpleadosModel.getArea() + "' " + "and puesto='" + configurarEmpleadosModel.getPuesto()
				+ "' " + "ORDER BY c_area.nombreArea";

		try {
			listaEmpleados = getJdbcTemplate().query(sql, new RowMapper<EmpleadoModel>() {

				public EmpleadoModel mapRow(ResultSet rs, int columna) throws SQLException {
					EmpleadoModel resultValue = new EmpleadoModel();
					resultValue.setIdEmpleado(rs.getInt("idEmpleado"));
					resultValue.setNombreEmpleado(rs.getString("nombreempleado"));
					resultValue.setApePatEmpleado(rs.getString("apepatempleado"));
					resultValue.setApeMatEmpleado(rs.getString("apematempleado"));
					resultValue.setIdArea(rs.getInt("idArea"));
					resultValue.setArea(rs.getString("nombreArea"));
					resultValue.setIdTurno(rs.getInt("idTurno"));
					resultValue.setNombreTurno(rs.getString("nombreTurno"));
					return resultValue;
				}

			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}

		return listaEmpleados;
	}

	public List<EmpleadoModel> consultaPuesto(EmpleadoModel configurarEmpleadosModel) throws SQLException {
		List<EmpleadoModel> listaEmpleados = new ArrayList<EmpleadoModel>();
		String sql = "select idEmpleado,nombreempleado,apepatempleado,apematempleado,"
				+ "c_turno.idArea,c_area.nombreArea,c_turno.idTurno, c_turno.nombreTurno from t_empleado "
				+ "inner join c_turno on t_empleado.idTurno=c_turno.idTurno "
				+ "inner join c_area on c_turno.idArea=c_area.idArea" + " where puesto='"
				+ configurarEmpleadosModel.getPuesto() + "' " + "ORDER BY c_area.nombreArea";

		try {
			listaEmpleados = getJdbcTemplate().query(sql, new RowMapper<EmpleadoModel>() {

				public EmpleadoModel mapRow(ResultSet rs, int columna) throws SQLException {
					EmpleadoModel resultValue = new EmpleadoModel();
					resultValue.setIdEmpleado(rs.getInt("idEmpleado"));
					resultValue.setNombreEmpleado(rs.getString("nombreempleado"));
					resultValue.setApePatEmpleado(rs.getString("apepatempleado"));
					resultValue.setApeMatEmpleado(rs.getString("apematempleado"));
					resultValue.setIdArea(rs.getInt("idArea"));
					resultValue.setArea(rs.getString("nombreArea"));
					resultValue.setIdTurno(rs.getInt("idTurno"));
					resultValue.setNombreTurno(rs.getString("nombreTurno"));
					return resultValue;
				}

			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}
		return listaEmpleados;
	}

	public EmpleadoModel consultarUserAndPassowrd(EmpleadoModel configurarEmpleadosModel)throws SQLException{
		EmpleadoModel empleadoModel = new EmpleadoModel();
		String sql = "select usuarioEmpleado,passwordEmpleado from t_empleado " + "where idEmpleado="
				+ configurarEmpleadosModel.getIdEmpleado();
		try {
			empleadoModel = getJdbcTemplate().query(sql, new ResultSetExtractor<EmpleadoModel>() {
				public EmpleadoModel extractData(ResultSet rs) throws SQLException {
					EmpleadoModel empleado = new EmpleadoModel();
					if (rs.next()) {
						empleado.setNombreUsuario(rs.getString("usuarioEmpleado"));
						empleado.setPassword(rs.getString("passwordEmpleado"));
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

}
