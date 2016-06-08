package com.greenpear.it.scaepro.bo.controlacceso;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.greenpear.it.scaepro.dao.controlacceso.ControlAccesoDao;
import com.greenpear.it.scaepro.model.controlacceso.ControlAccesoModel;
import com.greenpear.it.scaepro.model.empleado.EmpleadoModel;
import com.greenpear.it.scaepro.services.InsertService;
import com.greenpear.it.scaepro.services.SelectOneService;

/**
 * 
 * @author DanielBP
 *
 */
@Service("accesoBoService")
public class ControlAccesoBo implements SelectOneService<EmpleadoModel>, InsertService<ControlAccesoModel>{
	
	@Autowired
	@Qualifier("accesoDaoService")
	private ControlAccesoDao accesoDaoService;

	public ControlAccesoDao getAccesoDaoService() {
		return accesoDaoService;
	}

	/**
	 * Método para consultar al empleado
	 * @param id nip del empleado
	 * @return modelo del empleado
	 * @throws SQLException excepcion SQL
	 */
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
	
	/**
	 * Método para consultar el control de acceso del empleado
	 * @param idEmpleado identificador del empleado
	 * @param fecha del control de acceso
	 * @return modelo del control de acceso
	 * @throws SQLException excepcion SQL
	 */
	public ControlAccesoModel consultarControlAcceso(int idEmpleado,String fecha) throws SQLException{
		ControlAccesoModel controlAcceso=new ControlAccesoModel();
		try {
			controlAcceso = getAccesoDaoService().consultarControlAcceso(idEmpleado, fecha);
		} catch (Exception e){
			throw new SQLException(e.getMessage());
		}
		
		return controlAcceso;
	}

	/**
	 * Método para insertar el control de acceso a la BD
	 * @param t modelo de Control de acceso
	 * @return resultado de la transacción
	 * @throws SQLException excepcion SQL
	 */
	@Override
	public String insertar(ControlAccesoModel t) throws SQLException {
		String horaControl="Hora de entrada";
		t.setHoraControl(horaControl);
		
		String resultado=null;
		try{
			resultado=accesoDaoService.insertar(t);
		}catch(Exception e){
			throw new SQLException(e.getMessage());
		}
		return resultado;
	}
}
