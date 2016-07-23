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
			return areas;
		}catch(SQLException e){
			throw new SQLException(e.getMessage());
		}
	}
	
	public List<TurnoModel> consultarTunos(int idArea) throws SQLException{
		List<TurnoModel> turnos = new ArrayList<TurnoModel>();
		try{
			turnos=getConfigurarScaeProDao().consultarTurnos(idArea);
			return turnos;
		}catch(SQLException e){
			throw new SQLException(e.getMessage());
		}
	}
	
	public String registrarTurno(List<TurnoModel> turno) throws SQLException{
		String resultado=null;
		try{
			resultado=getConfigurarScaeProDao().registrarTurno(turno);
			return resultado;
		}catch(SQLException e){
			throw new SQLException(e.getMessage());
		}
	}
	
	public String editarTurno(List<TurnoModel> turno) throws SQLException{
		String resultado = null;
		try{
			resultado=getConfigurarScaeProDao().editarTurno(turno);
			return resultado;
		}catch(SQLException e){
			throw new SQLException(e.getMessage());
		}
	}

	public String eliminarTurno(int idTurno) throws SQLException{
		String resultado = null;
		try{
			resultado = getConfigurarScaeProDao().verificarEmpleados(idTurno);
			
			if(!resultado.equals("Sin empleados en el turno")){
				return resultado;
			}
		}catch(SQLException e){
			throw new SQLException(e.getMessage());
		}		
		
		try{
			resultado=getConfigurarScaeProDao().eliminarTurno(idTurno);
			return resultado;
		}catch(SQLException e){
			throw new SQLException(e.getMessage());
		}
	}	
}
