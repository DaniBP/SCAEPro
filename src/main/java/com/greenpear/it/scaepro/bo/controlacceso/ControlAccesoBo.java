package com.greenpear.it.scaepro.bo.controlacceso;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.greenpear.it.scaepro.controller.controlacceso.ControlAccesoController;
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
	
	@Autowired
	private ControlAccesoController accesoController;

	public ControlAccesoDao getAccesoDaoService() {
		return accesoDaoService;
	}

	public ControlAccesoController getAccesoController() {
		return accesoController;
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
	 * @return Lista de modelos del control de acceso
	 * @throws SQLException excepcion SQL
	 */
	public List<ControlAccesoModel> consultarControlAcceso(int idEmpleado,String fecha) throws SQLException{
		List<ControlAccesoModel> listaControlAcceso = new ArrayList<ControlAccesoModel>();
		try {
			listaControlAcceso = getAccesoDaoService().consultarControlAcceso(idEmpleado, fecha);
		} catch (Exception e){
			throw new SQLException(e.getMessage());
		}
		
		return listaControlAcceso;
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
		
		
		switch (getAccesoController().getListaControlAcceso().size()-1) {
		case 0:
			horaControl="Hora de entrada";
			break;

		case 1:
			t.setIdControlAcceso(getAccesoController().getListaControlAcceso().get(0).getIdControlAcceso());
			horaControl="Hora de salida a comer";
			break;
			
		case 2:
			if(getAccesoController().getListaControlAcceso().get(1).getHoraControl().equals("Hora de salida")){
				return "Labores del día completados";
			}
			
			t.setIdControlAcceso(getAccesoController().getListaControlAcceso().get(0).getIdControlAcceso());
			horaControl="Hora de entrada de comer";
			break;
		case 3: 
			t.setIdControlAcceso(getAccesoController().getListaControlAcceso().get(0).getIdControlAcceso());
			horaControl="Hora de salida";
			break;
		case 4:
			return "Labores del día completados";
		}
		
		String resultado = validarHora(t);
		
		if(horaControl.equals("Hora de salida a comer") && (resultado.equals("Día no laboral") || resultado.equals("No comida"))){
			horaControl="Hora de salida";
		}
		
		if(horaControl.equals("Hora de salida")){
			int horaEntrada = Integer.parseInt(getAccesoController().getListaControlAcceso().get(0).getHoraRegistrada().substring(0, 2));
			System.out.println(horaEntrada);
			int horaSalida = Integer.parseInt(getAccesoController().getListaControlAcceso().get(getAccesoController().getListaControlAcceso().size()-1).getHoraRegistrada().substring(0, 2));
			System.out.println(horaSalida);
			int horasTrabajadas=horaSalida-horaEntrada;
			System.out.println(horasTrabajadas);
			t.setHorasTrabajadas(horasTrabajadas);
		}
		
		t.setHoraControl(horaControl);
		
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
	private String validarHora(ControlAccesoModel t) {
		String[] dia = new String[]{
				"Domingo",
				"Lunes",
				"Martes",
				"Miercoles",
				"Jueves",
				"Viernes",
				"Sabado"};
		
		Calendar now = Calendar.getInstance();
		
		TurnoModel turnoModel = getAccesoController().getTurnoModel();
		
		try {
			turnoModel = accesoDaoService.getHorario(t, dia[now.get(Calendar.DAY_OF_WEEK)-1]);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		if (turnoModel.getIdEstatus()==2){
			return "Día no laboral";
		}else if(turnoModel.getIdEstatusComida()==2){
			return "No comida";
		}
		return "correcto";
	}
}
