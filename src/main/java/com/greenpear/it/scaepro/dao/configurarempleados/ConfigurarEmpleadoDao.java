package com.greenpear.it.scaepro.dao.configurarempleados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.swing.JOptionPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import com.greenpear.it.scaepro.model.direccion.DireccionModelo;
import com.greenpear.it.scaepro.model.empleado.EmpleadoModel;
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

	public List<DireccionModelo> consultarMunicipio(DireccionModelo direccionModelo) throws SQLException {
		List<DireccionModelo> listaEstados = new ArrayList<DireccionModelo>();
		String sql = "SELECT t_Municipio.nombre_municipio from t_Municipio INNER JOIN t_Estado on "
				+ "t_Municipio.Estado=t_Estado.id_Estado WHERE t_Estado.estado='" + direccionModelo.getEstado() + "'";
		try {
			listaEstados = getJdbcTemplate().query(sql, new RowMapper<DireccionModelo>() {

				public DireccionModelo mapRow(ResultSet rs, int columna) throws SQLException {
					DireccionModelo resultValue = new DireccionModelo();
					resultValue.setMunicipio(rs.getString("nombre_municipio"));
					return resultValue;
				}

			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}

		return listaEstados;
	}

	public List<DireccionModelo> consultarColonias(DireccionModelo direccionModelo) throws SQLException {
		List<DireccionModelo> listaMunicipios = new ArrayList<DireccionModelo>();
		String sql = "select DISTINCT t_Colonia.nombreColonia from t_Colonia INNER JOIN t_Municipio"
				+ " on t_Colonia.idMunicipio=t_Municipio.idEstado where t_Municipio.nombreMunicipio='"
				+ direccionModelo.getMunicipio() + "' ORDER BY t_Colonia.nombreColonia";
		JOptionPane.showMessageDialog(null, sql);
		try {
			listaMunicipios = getJdbcTemplate().query(sql, new RowMapper<DireccionModelo>() {

				public DireccionModelo mapRow(ResultSet rs, int columna) throws SQLException {
					DireccionModelo resultValue = new DireccionModelo();
					resultValue.setColonia(rs.getString("nombreColonia"));
					return resultValue;
				}

			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}

		return listaMunicipios;
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
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la inserci�n!");
		}

		return "La direccion con el cp" + direccionModelo2.getCp() + "\n" + "Fue registrado exitosamente!\n\n"
				+ "Su id de DireccionEmpleado es: " + direccionModelo2.getIdDireccion()+ " con la colonia"
						+ " " +getDireccionModelo().getUniColonia() ;
	}
}