package com.greenpear.it.scaepro.bo.controlacceso;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.greenpear.it.scaepro.dao.controlacceso.ControlAccesoDao;
import com.greenpear.it.scaepro.model.controlacceso.ControlAccesoModel;
import com.greenpear.it.scaepro.model.empleado.EmpleadoModel;
import com.greenpear.it.scaepro.model.turno.TurnoModel;
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
		EmpleadoModel empleado;
	
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
		ControlAccesoModel controlAcceso;
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
		String horaControl=null;
		if(t.getIdControlAcceso()==0){
			horaControl="Hora de entrada";
		}else {
			horaControl="Hora de entrada"; //Cambiar
		}
		t.setHoraControl(horaControl);
		String resultado=null;
		
		if(horaControl.equals("Hora de entrada")){
			resultado = validarHoraEntrada(t);
			System.out.println(resultado);
		}
		
		try{
			accesoDaoService.insertar(t);
		}catch(Exception e){
			throw new SQLException(e.getMessage());
		}
		return resultado;
	}

	/**
	 * Método para validar el registro de la hora de entrada
	 * @param t Modelo de control de acceso
	 * @return Resultado de la validación
	 */
	private String validarHoraEntrada(ControlAccesoModel t) {
		String[] dia = new String[]{
				"Domingo",
				"Lunes",
				"Martes",
				"Miercoles",
				"Jueves",
				"Viernes",
				"Sabado"};
		
		Calendar now = Calendar.getInstance();
		
		TurnoModel turnoModel= new TurnoModel();
		try {
			turnoModel = accesoDaoService.getHorario(t, dia[now.get(Calendar.DAY_OF_WEEK)-1]);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		if (turnoModel.getIdEstatus()==2){
			return "Día no laboral";
		}else{
			
		}
		return "correcto";
	}
}
