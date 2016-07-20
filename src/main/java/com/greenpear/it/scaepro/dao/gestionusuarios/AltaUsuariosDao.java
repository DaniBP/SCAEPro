/**
 * 
 */
package com.greenpear.it.scaepro.dao.gestionusuarios;

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
import com.greenpear.it.scaepro.model.gestionusuarios.ConsultaUsuariosModel;
import com.greenpear.it.scaepro.services.DataSourceService;
import com.greenpear.it.scaepro.services.InsertService;

/**
 * @author Alan Aguilar
 *
 */
@Repository("altaUsuariosDao")
public class AltaUsuariosDao extends DataSourceService implements InsertService<ConsultaUsuariosModel> {
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
	public String insertar(ConsultaUsuariosModel t) throws SQLException {
		String sql = "select * from c_usuario where nombreUsuario ='" + t.getNombreUsuario() + "'";

		ConsultaUsuariosModel model = new ConsultaUsuariosModel();
		try {
			model = getJdbcTemplate().query(sql, new ResultSetExtractor<ConsultaUsuariosModel>() {
				public ConsultaUsuariosModel extractData(ResultSet rs) throws SQLException {
					ConsultaUsuariosModel resultValue = new ConsultaUsuariosModel();
					if (rs.next()) {
						resultValue.setId_usuario(rs.getInt("idUsuario"));
					}
					return resultValue;
				}
			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}

		if (model.getId_usuario() != 0) {
			return "existe usuario!";
		}

		try {
			SimpleJdbcInsert insert = new SimpleJdbcInsert(getDataSource());

			insert.setTableName("c_usuario");
			insert.setGeneratedKeyName("idUsuario");

			Map<String, Object> parameters = new HashMap<String, Object>();

			parameters.put("nombreUsuario", t.getNombreUsuario());
			parameters.put("passwordUsuario", t.getPasswordUsuario());
			parameters.put("passwordUsuario2", t.getPasswordUsuario2());
			parameters.put("correoUsuario", t.getCorreoUsuario());
			parameters.put("correoUsuario2", t.getCorreoUsuario2());
			parameters.put("estatusUsuario", t.getEstatusUsuario());
			parameters.put("estatusEnvio", t.getEstatusEnvio());

			t.setId_usuario(insert.executeAndReturnKey(parameters).intValue());

		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo: {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la inserción!");
		}

		return "El usuario " + t.getNombreUsuario() + "\n" + "Fue registrado exitosamente!\n\n" + "Su número de ID es: "
				+ t.getId_usuario();
	}

	public String editar(ConsultaUsuariosModel modelo) throws SQLException {
		String sql = "select * from c_usuario where nombreUsuario ='" + modelo.getNombreUsuario() + "'";

		ConsultaUsuariosModel model = new ConsultaUsuariosModel();
		try {
			model = getJdbcTemplate().query(sql, new ResultSetExtractor<ConsultaUsuariosModel>() {
				public ConsultaUsuariosModel extractData(ResultSet rs) throws SQLException {
					ConsultaUsuariosModel resultValue = new ConsultaUsuariosModel();
					if (rs.next()) {
						resultValue.setId_usuario(rs.getInt("idUsuario"));
						resultValue.setNombreUsuario(rs.getString("nombreUsuario"));
						resultValue.setPasswordUsuario(rs.getString("passwordUsuario"));
					//	resultValue.setPasswordUsuario2(rs.getString("passwordUsuario2"));
						resultValue.setCorreoUsuario(rs.getString("correoUsuario"));
					//	resultValue.setCorreoUsuario2(rs.getString("correoUsuario2"));
						resultValue.setEstatusUsuario(rs.getInt("estatusUsuario"));
						resultValue.setEstatusEnvio(rs.getInt("estatusEnvio"));
					}
					return resultValue;
				}
			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}
		//System.out.println(modelo.getId_usuario());
		//System.out.println(model.getId_usuario());
		if (model.getId_usuario() != modelo.getId_usuario()) {
			if (model.getId_usuario() != 0) {
				return "¡Ya Existe Un Usuario Con Éste Nombre!";
			}
		}

		String sqlUsuario = "UPDATE c_usuario SET nombreUsuario=?, passwordUsuario=?, correoUsuario=?, estatusUsuario=?, estatusEnvio=? WHERE idUsuario=?";

		try {
			getJdbcTemplate().update(sqlUsuario, modelo.getNombreUsuario(), modelo.getPasswordUsuario(), modelo.getCorreoUsuario(), modelo.getEstatusUsuario(), modelo.getEstatusEnvio(), modelo.getId_usuario());

		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo: {} ", e.getMessage());
			throw new SQLException(
					"Existe un problema con la base de datos\n" + "No se pudo realizar la actualización!");
		}

		return "Usuario actualizado correctamente";
	}
}
