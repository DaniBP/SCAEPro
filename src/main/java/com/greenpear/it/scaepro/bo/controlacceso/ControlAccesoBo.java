package com.greenpear.it.scaepro.bo.controlacceso;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JOptionPane;

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
		
		if(horaControl.equals("Hora de entrada") && (!resultado.equals("Día no laboral"))){
			validarIncidencia(t);
		}
		
		
		if(horaControl.equals("Hora de salida a comer") && (resultado.equals("Día no laboral") || resultado.equals("No comida"))){
			horaControl="Hora de salida";
		}
		
		if(horaControl.equals("Hora de salida")){
			int horaEntrada = Integer.parseInt(getAccesoController().getListaControlAcceso().get(0).getHoraRegistrada().substring(0, 2));
			int horaSalida = Integer.parseInt(getAccesoController().getListaControlAcceso().get(getAccesoController().getListaControlAcceso().size()-1).getHoraRegistrada().substring(0, 2));
			int horasTrabajadas=horaSalida-horaEntrada;
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
		
		try {
			getAccesoController().setTurnoModel(accesoDaoService.getHorario(t, dia[now.get(Calendar.DAY_OF_WEEK)-1]));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		if (getAccesoController().getTurnoModel().getIdEstatus()==2){
			return "Día no laboral";
		}else if(getAccesoController().getTurnoModel().getIdEstatusComida()==2){
			return "No comida";
		}
		return "correcto";
	}
	
	private void validarIncidencia(ControlAccesoModel t){
		String horarioRegistrado = t.getHoraRegistrada();
		String horarioValido = getAccesoController().getTurnoModel().getHoraEntrada();
		int horaRegistrada = Integer.parseInt(horarioRegistrado.substring(0, 2));
		int horaValida = Integer.parseInt(horarioValido.substring(0, 2));
		int minutosRegistrados = Integer.parseInt(horarioRegistrado.substring(3, 5));
		int minutosValidos = Integer.parseInt(horarioValido.substring(3, 5));
		int toleranciaFalta = getAccesoController().getTurnoModel().getTiempoFalta();
		int toleranciaRetardo = getAccesoController().getTurnoModel().getTiempoRetardo();
		
		getAccesoController().getIncidenciaModel().setIdEmpleado(t.getIdEmpleado());
		getAccesoController().getIncidenciaModel().setIdEstatusIncidencia(2);
		getAccesoController().getIncidenciaModel().setFechaIncidencia(t.getFecha()+" "+t.getHoraRegistrada());
		
		if((horaRegistrada - horaValida)>0){
			JOptionPane.showMessageDialog(null, "Ha excedido la tolerancia por falta\nSe ha generado una incidencia", "INCIDENCIA", JOptionPane.WARNING_MESSAGE);
			getAccesoController().getIncidenciaModel().setIdTipoIncidencia(2);
			try {
				getAccesoDaoService().generarIncidencia(getAccesoController().getIncidenciaModel());
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}else if((minutosRegistrados-minutosValidos)>toleranciaFalta){
			JOptionPane.showMessageDialog(null, "Ha excedido la tolerancia por falta\nSe ha generado una incidencia", "INCIDENCIA", JOptionPane.WARNING_MESSAGE);
			getAccesoController().getIncidenciaModel().setIdTipoIncidencia(2);
			try {
				getAccesoDaoService().generarIncidencia(getAccesoController().getIncidenciaModel());
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}else if((minutosRegistrados-minutosValidos)>toleranciaRetardo){
			JOptionPane.showMessageDialog(null, "Ha excedido la tolerancia por retardo\nSe ha generado una incidencia", "INCIDENCIA", JOptionPane.WARNING_MESSAGE);
			getAccesoController().getIncidenciaModel().setIdTipoIncidencia(1);
			try {
				getAccesoDaoService().generarIncidencia(getAccesoController().getIncidenciaModel());
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		
	}
	
}
