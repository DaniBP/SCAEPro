package com.greenpear.it.scaepro.controller.controlacceso;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.greenpear.it.scaepro.bo.controlacceso.ControlAccesoBo;
import com.greenpear.it.scaepro.controller.government.GovernmentService;
import com.greenpear.it.scaepro.model.empleado.EmpleadoModel;
import com.greenpear.it.scaepro.view.controlacceso.ControlAccesoView;

/**
 * 
 * @author DanielBP
 *
 */
@Controller
public class ControlAccesoController implements ActionListener, Runnable{
	
	/**
	 * Constructor
	 */
	public ControlAccesoController() {
		super();
	}
	
//**********************ESTANCIAS****************************
	@Autowired
	private GovernmentService government;
	
	@Autowired
	private ControlAccesoView vistaControlAcceso;
	
	@Autowired
	private EmpleadoModel empleadoModel;
	
	@Autowired
	@Qualifier("accesoBoService")
	private ControlAccesoBo accesoBoService;

	public GovernmentService getGovernment() {
		return government;
	}

	public ControlAccesoView getVistaControlAcceso() {
		return vistaControlAcceso;
	}
	
	public ControlAccesoBo getAccesoBoService() {
		return accesoBoService;
	}
	
	public EmpleadoModel getEmpleadoModel(){
		return empleadoModel;
	}
	
//**********************FIN DE ESTANCIAS***********************

	/**
	 * Método para iniciar la vista de control de acceso
	 */
	public void mostrarVistaControlAcceso(){
		if(getVistaControlAcceso().btnSalir.getActionListeners().length == 0){
			getVistaControlAcceso().btnChecar.addActionListener(this);
			getVistaControlAcceso().btnSalir.addActionListener(this);
		}
		
		getVistaControlAcceso().h1 = new Thread(this);
		getVistaControlAcceso().h1.start();
		
		getVistaControlAcceso().setVisible(true);
	}
	
	/**
	 * Método para calcular la fecha y hora del sistema
	 */
	public void calculaFechaHora() {        
		Calendar calendario = new GregorianCalendar();
		Date fechaHoraActual = new Date();

		calendario.setTime(fechaHoraActual);

		getVistaControlAcceso().ampm = calendario.get(Calendar.AM_PM)==Calendar.AM?"AM":"PM";

		if(getVistaControlAcceso().ampm.equals("PM")){
		 int h = calendario.get(Calendar.HOUR_OF_DAY)-12;

		 getVistaControlAcceso().hora = h>9?""+h:"0"+h;

		}else{
			getVistaControlAcceso().hora = calendario.get(Calendar.HOUR_OF_DAY)>9?""+calendario.get(Calendar.HOUR_OF_DAY):"0"+calendario.get(Calendar.HOUR_OF_DAY);            
		}
		
		getVistaControlAcceso().minutos = calendario.get(Calendar.MINUTE)>9?""+calendario.get(Calendar.MINUTE):"0"+calendario.get(Calendar.MINUTE);
		getVistaControlAcceso().segundos = calendario.get(Calendar.SECOND)>9?""+calendario.get(Calendar.SECOND):"0"+calendario.get(Calendar.SECOND);
		
		getVistaControlAcceso().dia = calendario.get(Calendar.DAY_OF_MONTH)>9?""+calendario.get(Calendar.DAY_OF_MONTH):"0"+calendario.get(Calendar.DAY_OF_MONTH);
		getVistaControlAcceso().mes = calendario.get(Calendar.MONTH)>9?""+calendario.get(Calendar.MONTH):"0"+calendario.get(Calendar.MONTH);
		getVistaControlAcceso().anio = String.valueOf(calendario.get(Calendar.YEAR));
	}

	/**
	 * Método para inicializar el la fecha y hora
	 */
	@Override
	public void run() {
		Thread ct = Thread.currentThread();
		while(ct == getVistaControlAcceso().h1) {   
			calculaFechaHora();
	
			getVistaControlAcceso().lblHora.setText("Hora:  "+
			getVistaControlAcceso().hora + 
				":" + getVistaControlAcceso().minutos + 
				":" + getVistaControlAcceso().segundos + 
				" "+getVistaControlAcceso().ampm);
			
			getVistaControlAcceso().lblFecha.setText("Fecha:  "+
					getVistaControlAcceso().dia + 
						"-" + getVistaControlAcceso().mes + 
						"-" + getVistaControlAcceso().anio);
			
			try {	
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				JOptionPane.showMessageDialog(null, "Hay un problema con la hora del Sistema", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == vistaControlAcceso.btnChecar){
			
			int nip = 252409999;
			
			try {
				
				empleadoModel = getAccesoBoService().consultaIndividual(nip);
				
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			if(getEmpleadoModel().getIdEmpleado() == 0){
				JOptionPane.showMessageDialog(null, "Empleado no registrado", "Atención",JOptionPane.WARNING_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null, getEmpleadoModel().getIdEmpleado());
			}
		}
	}
}
