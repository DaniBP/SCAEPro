package com.greenpear.it.scaepro.bo.configurarscaepro;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.greenpear.it.scaepro.dao.configurarscaepro.ConfigurarScaeProDao;
import com.greenpear.it.scaepro.model.gestionareas.ConsultaAreasModel;

/**
 * 
 * @author DanielBP
 *
 */
@Service("configurarScaeProBo")
public class ConfigurarScaeProBo {
	
	@Autowired
	@Qualifier("configurarScaeProDao")
	private ConfigurarScaeProDao configurarScaeProDao;

	public ConfigurarScaeProDao getConfigurarScaeProDao() {
		return configurarScaeProDao;
	}
	
	public List<ConsultaAreasModel> consultarAreas() throws SQLException{
		List<ConsultaAreasModel> areas = new ArrayList<ConsultaAreasModel>();
		try{
			areas = getConfigurarScaeProDao().consultarAreas();
		}catch(SQLException e){
			throw new SQLException(e.getMessage());
		}
		return areas;
	}
}
