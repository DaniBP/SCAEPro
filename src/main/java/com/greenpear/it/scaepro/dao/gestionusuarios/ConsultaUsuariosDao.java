/**
 * 
 */
package com.greenpear.it.scaepro.dao.gestionusuarios;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.greenpear.it.scaepro.model.gestionareas.ConsultaAreasModel;
import com.greenpear.it.scaepro.model.gestionusuarios.ConsultaUsuariosModel;
import com.greenpear.it.scaepro.services.DataSourceService;
import com.greenpear.it.scaepro.services.InsertService;
import com.greenpear.it.scaepro.services.SelectAllService;

/**
 * @author Alan Aguilar
 *
 */
@Repository("consultaUsuariosDao")
public class ConsultaUsuariosDao extends DataSourceService implements SelectAllService<ConsultaUsuariosModel> {
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

	@Override
	public List<ConsultaUsuariosModel> consultaGeneral() throws SQLException {
		List<ConsultaUsuariosModel> listaUsuarios = new ArrayList<ConsultaUsuariosModel>();
		String sql = "SELECT * FROM c_usuario";

		try {
			listaUsuarios = getJdbcTemplate().query(sql, new RowMapper<ConsultaUsuariosModel>() {

				public ConsultaUsuariosModel mapRow(ResultSet rs, int columna) throws SQLException {
					ConsultaUsuariosModel resultValue = new ConsultaUsuariosModel();
					resultValue.setId_usuario(rs.getInt("idUsuario"));
					resultValue.setNombreUsuario(rs.getString("nombreUsuario"));
					resultValue.setPasswordUsuario(rs.getString("passwordUsuario"));
					resultValue.setCorreoUsuario(rs.getString("correoUsuario"));
					resultValue.setEstatusUsuario(rs.getInt("estatusUsuario"));
					resultValue.setEstatusEnvio(rs.getInt("estatusEnvio"));
					return resultValue;
				}

			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}

		return listaUsuarios;
	}

	public String eliminarUsuario(ConsultaUsuariosModel modelo) throws SQLException {
		String sql = "DELETE FROM c_usuario WHERE nombreUsuario=?";

		try {
			getJdbcTemplate().update(sql, modelo.getNombreUsuario());
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la eliminación!");
		}
		return "El usuario fue eliminado correctamente!";
	}

	public ConsultaUsuariosModel consultaIndividual(ConsultaUsuariosModel usuario) throws SQLException {
		String sql = "SELECT * FROM c_usuario WHERE nombreUsuario='" + usuario.getNombreUsuario() + "'";

		try {
			usuario = getJdbcTemplate().query(sql, new ResultSetExtractor<ConsultaUsuariosModel>() {
				public ConsultaUsuariosModel extractData(ResultSet rs) throws SQLException {
					if (rs.next()) {
						ConsultaUsuariosModel resultValue = new ConsultaUsuariosModel();
						resultValue.setNombreUsuario(rs.getString("nombreUsuario"));
						resultValue.setPasswordUsuario(rs.getString("passwordUsuario"));
						resultValue.setCorreoUsuario(rs.getString("correoUsuario"));
						resultValue.setEstatusUsuario(rs.getInt("estatusUsuario"));
						resultValue.setEstatusEnvio(rs.getInt("estatusEnvio"));
						resultValue.setId_usuario(rs.getInt("idUsuario"));
						return resultValue;
					}
					return null;
				}
			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}
		return usuario;
	}

	public ConsultaUsuariosModel consultaEditar(String usuario) throws SQLException {
		ConsultaUsuariosModel usuarios = new ConsultaUsuariosModel();
		String sql = "SELECT * FROM c_usuario WHERE nombreUsuario='" + usuario + "'";

		try {
			usuarios = getJdbcTemplate().query(sql, new ResultSetExtractor<ConsultaUsuariosModel>() {
				public ConsultaUsuariosModel extractData(ResultSet rs) throws SQLException {
					if (rs.next()) {
						ConsultaUsuariosModel resultValue = new ConsultaUsuariosModel();
					
						//aquí se recuperan los valores de modificación
						resultValue.setId_usuario(rs.getInt("idUsuario"));
						resultValue.setNombreUsuario(rs.getString("nombreUsuario"));
						resultValue.setPasswordUsuario(rs.getString("passwordUsuario"));
						resultValue.setCorreoUsuario(rs.getString("correoUsuario"));
						resultValue.setEstatusUsuario(rs.getInt("estatusUsuario"));
						resultValue.setEstatusEnvio(rs.getInt("estatusEnvio"));
						
						return resultValue;
					}
					return null;
				}
			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}
		return usuarios;
	}
}
