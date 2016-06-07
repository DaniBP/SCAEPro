package com.greenpear.it.scaepro.bo.controlacceso;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.greenpear.it.scaepro.dao.controlacceso.ControlAccesoDao;
import com.greenpear.it.scaepro.model.empleado.EmpleadoModel;
import com.greenpear.it.scaepro.services.SelectOneService;

/**
 * 
 * @author DanielBP
 *
 */
@Service("accesoBoService")
public class ControlAccesoBo implements SelectOneService<EmpleadoModel>{
	
	@Autowired
	@Qualifier("accesoDaoService")
	private ControlAccesoDao accesoDaoService;

	public ControlAccesoDao getAccesoDaoService() {
		return accesoDaoService;
	}

	@Override
	public EmpleadoModel consultaIndividual(int id) throws SQLException {
		EmpleadoModel empleado = new EmpleadoModel();
	
		try {
			empleado = getAccesoDaoService().consultaIndividual(id);
		} catch (Exception e) {
			throw new SQLException(e.getMessage());
		}
		
		
		return empleado;
	}

}
