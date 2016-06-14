/**
 * 
 */
package com.greenpear.it.scaepro.dao.gestionareas;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.greenpear.it.scaepro.model.gestionareas.AltaAreasModel;
import com.greenpear.it.scaepro.services.DataSourceService;
import com.greenpear.it.scaepro.services.InsertService;

/**
 * @author EDSONJOSUE
 *
 */
@Repository("altaAreasDao")
public class AltaAreasDao extends DataSourceService implements InsertService<AltaAreasModel>{
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
	public String insertar(AltaAreasModel t) throws SQLException {
		try{
			SimpleJdbcInsert insert = new SimpleJdbcInsert(getDataSource());
			
			insert.setTableName("c_area");
			insert.setGeneratedKeyName("idArea");
			
			Map<String, Object> parameters = new HashMap<String, Object>();
			
			parameters.put("nombreArea", t.getArea());
			parameters.put("descripcionArea", t.getDescripcion());
			
			t.setIdArea(insert.executeAndReturnKey(parameters).intValue());
			
		}catch(Exception e){
			log.error("\nSQL: Error al cargar los datos.\nMotivo: {} ",e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n"
					+ "No se pudo realizar la inserción!");
		}
		
		return "El área "+t.getArea()+"\n"
				+ "Fue registrado exitosamente!\n\n"
				+ "Su número de ID es: "+t.getIdArea();
	}  
}
