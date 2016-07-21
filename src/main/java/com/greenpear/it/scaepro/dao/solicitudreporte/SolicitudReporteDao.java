/**
 * 
 */
package com.greenpear.it.scaepro.dao.solicitudreporte;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.greenpear.it.scaepro.model.administracionmovil.EstatusPagoModel;
import com.greenpear.it.scaepro.model.solicitudreporte.SolicitudReporteModel;

/**
 * @author EDSONJOSUE
 *
 */
public class SolicitudReporteDao {
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

	public List<SolicitudReporteModel> llenarAreas() throws SQLException {
		List<SolicitudReporteModel> listaAreas = new ArrayList<SolicitudReporteModel>();
		String sql = "SELECT * FROM c_area order by nombreArea";
		try {
			listaAreas = getJdbcTemplate().query(sql, new RowMapper<SolicitudReporteModel>() {

				public SolicitudReporteModel mapRow(ResultSet rs, int columna) throws SQLException {
					SolicitudReporteModel resultValue = new SolicitudReporteModel();
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

}
