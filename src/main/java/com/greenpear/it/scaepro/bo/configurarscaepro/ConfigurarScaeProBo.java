package com.greenpear.it.scaepro.bo.configurarscaepro;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.greenpear.it.scaepro.dao.configurarscaepro.ConfigurarScaeProDao;
import com.greenpear.it.scaepro.model.gestionareas.ConsultaAreasModel;
import com.greenpear.it.scaepro.model.turno.TurnoModel;

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
	
	public List<TurnoModel> consultarTunos(int idArea) throws SQLException{
		List<TurnoModel> turnos = new ArrayList<TurnoModel>();
		try{
			turnos=getConfigurarScaeProDao().consultarTurnos(idArea);
		}catch(SQLException e){
			throw new SQLException(e.getMessage());
		}
		return turnos;
	}
	
	public String registrarTurno(List<TurnoModel> turno) throws SQLException{
		String resultado=null;
		try{
			resultado=getConfigurarScaeProDao().registrarTurno(turno);
		}catch(SQLException e){
			throw new SQLException(e.getMessage());
		}
		return resultado;
	}
}
