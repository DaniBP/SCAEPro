package com.greenpear.it.scaepro.controller.controlacceso;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.greenpear.it.scaepro.bo.controlacceso.ControlAccesoBo;
import com.greenpear.it.scaepro.controller.government.GovernmentService;
import com.greenpear.it.scaepro.model.controlacceso.ControlAccesoModel;
import com.greenpear.it.scaepro.model.empleado.EmpleadoModel;
import com.greenpear.it.scaepro.view.accesosistema.PrincipalView;
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
	private ControlAccesoView controlAccesoView;
	
	@Autowired
	private EmpleadoModel empleadoModel;
	
	@Autowired
	private ControlAccesoModel controlAccesoModel;
	
	@Autowired
	@Qualifier("accesoBoService")
	private ControlAccesoBo accesoBoService;

	public GovernmentService getGovernment() {
		return government;
	}

	public ControlAccesoView getControlAccesoView() {
		return controlAccesoView;
	}
	
	public ControlAccesoBo getAccesoBoService() {
		return accesoBoService;
	}
	
	public EmpleadoModel getEmpleadoModel(){
		return empleadoModel;
	}

	public ControlAccesoModel getControlAccesoModel() {
		return controlAccesoModel;
	}
	
//**********************FIN DE ESTANCIAS***********************

	/**
	 * Método para iniciar la vista de control de acceso
	 */
	public void mostrarVistaControlAcceso(){
		if(getControlAccesoView().btnSalir.getActionListeners().length == 0){
			getControlAccesoView().btnChecar.addActionListener(this);
			getControlAccesoView().btnSalir.addActionListener(this);
		}
		
		getControlAccesoView().h1 = new Thread(this);
		getControlAccesoView().h1.start();
		
		getControlAccesoView().setVisible(true);
	}
	
	/**
	 * Método para calcular la fecha y hora del sistema
	 */
	public void calculaFechaHora() {        
		Calendar calendario = new GregorianCalendar();
		Date fechaHoraActual = new Date();

		calendario.setTime(fechaHoraActual);

		getControlAccesoView().ampm = calendario.get(Calendar.AM_PM)==Calendar.AM?"AM":"PM";

		if(getControlAccesoView().ampm.equals("PM")){
		 int h = calendario.get(Calendar.HOUR_OF_DAY)-12;

		 getControlAccesoView().hora = h>9?""+h:"0"+h;

		}else{
			getControlAccesoView().hora = calendario.get(Calendar.HOUR_OF_DAY)>9?""+calendario.get(Calendar.HOUR_OF_DAY):"0"+calendario.get(Calendar.HOUR_OF_DAY);            
		}
		
		getControlAccesoView().minutos = calendario.get(Calendar.MINUTE)>9?""+calendario.get(Calendar.MINUTE):"0"+calendario.get(Calendar.MINUTE);
		getControlAccesoView().segundos = calendario.get(Calendar.SECOND)>9?""+calendario.get(Calendar.SECOND):"0"+calendario.get(Calendar.SECOND);
		
		getControlAccesoView().dia = calendario.get(Calendar.DAY_OF_MONTH)>9?""+calendario.get(Calendar.DAY_OF_MONTH):"0"+calendario.get(Calendar.DAY_OF_MONTH);
		getControlAccesoView().mes = calendario.get(Calendar.MONTH)>9?""+calendario.get(Calendar.MONTH):"0"+calendario.get(Calendar.MONTH);
		getControlAccesoView().anio = String.valueOf(calendario.get(Calendar.YEAR));
	}

	/**
	 * Método para inicializar el la fecha y hora
	 */
	@Override
	public void run() {
		Thread ct = Thread.currentThread();
		while(ct == getControlAccesoView().h1) {   
			calculaFechaHora();
	
			getControlAccesoView().lblHora.setText("Hora:  "+
			getControlAccesoView().hora + 
				":" + getControlAccesoView().minutos + 
				":" + getControlAccesoView().segundos + 
				" "+getControlAccesoView().ampm);
			
			getControlAccesoView().lblFecha.setText("Fecha:  "+
					getControlAccesoView().dia + 
						"-" + getControlAccesoView().mes + 
						"-" + getControlAccesoView().anio);
			
			try {	
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				JOptionPane.showMessageDialog(null, "Hay un problema con la hora del Sistema", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == getControlAccesoView().btnChecar){
			
			int nip = 252409999;
			
			try {
				
				empleadoModel = getAccesoBoService().consultaIndividual(nip);
				
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			if(getEmpleadoModel().getIdEmpleado() == 0){
				JOptionPane.showMessageDialog(null, "Empleado no registrado", "Atención",JOptionPane.WARNING_MESSAGE);
			}else{
				
				String horaActual;
				if(getControlAccesoView().ampm.equals("PM")){
					horaActual = (Integer.parseInt(controlAccesoView.hora)+12)+":"+controlAccesoView.minutos+":"+controlAccesoView.segundos;	
				}else{
					horaActual = controlAccesoView.hora+":"+controlAccesoView.minutos+":"+controlAccesoView.segundos;
				}
				String fechaActual = controlAccesoView.anio+"-"+controlAccesoView.mes+"-"+controlAccesoView.dia;
				
				try {
					controlAccesoModel = getAccesoBoService().consultarControlAcceso(getEmpleadoModel().getIdEmpleado(), fechaActual);
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				getControlAccesoModel().setIdEmpleado(getEmpleadoModel().getIdEmpleado());
				getControlAccesoModel().setFecha(fechaActual);
				getControlAccesoModel().setHoraRegistrada(horaActual);
				
				try {
					getAccesoBoService().insertar(getControlAccesoModel());
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				mostrarDatosEmpleado();
				
				getEmpleadoModel().limpiarModelo();
				getControlAccesoModel().limpiarModelo();
			}
		}else if(e.getSource()==getControlAccesoView().btnSalir){
			System.exit(0);
		}
	}
	
	public void mostrarDatosEmpleado(){
		getControlAccesoView().lblEmpleado.setText("Empleado: "+getEmpleadoModel().getNombreEmpleado()+" "+getEmpleadoModel().getApePatEmpleado()+" "+getEmpleadoModel().getApePatEmpleado());
		getControlAccesoView().lblArea.setText("Área:     "+getEmpleadoModel().getArea());
		getControlAccesoView().lblEntrada.setText("Hora de entrada             "+getControlAccesoModel().getHoraRegistrada());
		
		ImageIcon fot = new ImageIcon(PrincipalView.class.getResource("/img/fotosempleados/"+getEmpleadoModel().getFotografia()));
		Icon icono = new ImageIcon(fot.getImage().getScaledInstance(
				getControlAccesoView().imgFoto.getWidth(), getControlAccesoView().imgFoto.getHeight(), Image.SCALE_DEFAULT));
		
		getControlAccesoView().imgFoto.setIcon(icono);
		
			try {
				
				Thread.sleep (5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		getControlAccesoView().limpiarVentana();
	}
}
