/**
 * 
 */
package com.greenpear.it.scaepro.dao.gestionareas;

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
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.greenpear.it.scaepro.model.gestionareas.AltaAreasModel;
import com.greenpear.it.scaepro.model.gestionareas.ConsultaAreasModel;
import com.greenpear.it.scaepro.services.DataSourceService;
import com.greenpear.it.scaepro.services.InsertService;
import com.greenpear.it.scaepro.services.SelectAllService;

/**
 * @author EDSONJOSUE
 *
 */
@Repository("consultaAreasDao")
public class ConsultaAreasDao extends DataSourceService implements SelectAllService<ConsultaAreasModel>{
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
	
	private ConsultaAreasDao SelectAllServiceDao;
	
	public ConsultaAreasDao getSelectAllServiceDao() {
		return SelectAllServiceDao;
	}
	@Override
	public List<ConsultaAreasModel> consultaGeneral() throws SQLException {
		List<ConsultaAreasModel> listaAreas=new ArrayList<ConsultaAreasModel>();
		String sql="SELECT * FROM c_area";
		
		try{
			listaAreas=getJdbcTemplate().query(sql, new RowMapper<ConsultaAreasModel>(){
			
				public ConsultaAreasModel mapRow(ResultSet rs, int columna) throws SQLException{
					ConsultaAreasModel resultValue = new ConsultaAreasModel();
					resultValue.setId_area(rs.getInt("idArea"));
					resultValue.setArea(rs.getString("nombreArea"));
					resultValue.setDescripcionArea(rs.getString("descripcionArea"));
					return resultValue;
				}
				
			});
		}catch(Exception e){
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ",e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n"
					+ "No se pudo realizar la consulta!");
		}
		
		return listaAreas;
	}
}