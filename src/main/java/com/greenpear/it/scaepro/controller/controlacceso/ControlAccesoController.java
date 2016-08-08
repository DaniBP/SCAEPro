package com.greenpear.it.scaepro.controller.controlacceso;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.greenpear.it.scaepro.bo.controlacceso.ControlAccesoBo;
import com.greenpear.it.scaepro.controller.government.GovernmentService;
import com.greenpear.it.scaepro.model.controlacceso.ControlAccesoModel;
import com.greenpear.it.scaepro.model.empleado.EmpleadoModel;
import com.greenpear.it.scaepro.model.incidencia.IncidenciaModel;
import com.greenpear.it.scaepro.model.turno.TurnoModel;
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
	private LectorHuellasController lectorHuellasController;
	
	@Autowired
	private EmpleadoModel empleadoModel;
	
	@Autowired
	private ControlAccesoModel controlAccesoModel;
	
	@Autowired
	private TurnoModel turnoModel;
	
	@Autowired
	private IncidenciaModel incidenciaModel;
	
	@Autowired
	@Qualifier("accesoBoService")
	private ControlAccesoBo accesoBoService;
	
	private List<ControlAccesoModel> listaControlAcceso;
	
	private int contadorMovil;
	
	private String estatusCheck;

	public GovernmentService getGovernment() {
		return government;
	}

	public ControlAccesoView getControlAccesoView() {
		return controlAccesoView;
	}

	public LectorHuellasController getLectorHuellasController() {
		return lectorHuellasController;
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
	
	public TurnoModel getTurnoModel() {
		return turnoModel;
	}
	
	public void setTurnoModel(TurnoModel turnoModel){
		this.turnoModel=turnoModel;
	}
	
	public IncidenciaModel getIncidenciaModel() {
		return incidenciaModel;
	}

	public List<ControlAccesoModel> getListaControlAcceso() {
		return listaControlAcceso;
	}
	
	public void setEstatusCheck(String estatusCheck) {
		this.estatusCheck = estatusCheck;
	}
	
//**********************FIN DE ESTANCIAS***********************

	/**
	 * Método para iniciar la vista de control de acceso
	 */
	public void mostrarVistaControlAcceso(){
		if(getControlAccesoView().btnSalir.getActionListeners().length == 0){
			getControlAccesoView().addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e){
					regresarInicio();
				}
			});
			
			getControlAccesoView().btnSalir.addActionListener(this);
			getLectorHuellasController().Iniciar();
		}
		
		activarLecturaWiFi();
		estatusCheck="libre";
		
		try {
			contadorMovil=getAccesoBoService().consultarRegistroMovil().size();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		getLectorHuellasController().start();
		getControlAccesoView().setVisible(true);
		
		getControlAccesoView().h1 = new Thread(this);
		getControlAccesoView().h1.start();
		
		
	}
	
	private void activarLecturaWiFi(){
		ImageIcon identificando = new ImageIcon("src/main/resources/img/Identificando.gif");
		Icon iconoIde = new ImageIcon(identificando.getImage().getScaledInstance(
				getControlAccesoView().imgEstado.getWidth(), getControlAccesoView().imgEstado.getHeight(), Image.SCALE_DEFAULT));
		
		getControlAccesoView().imgEstado.setIcon(iconoIde);
	}
	
	private void verificarContadorMovil() {
		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				try {
					int temp = contadorMovil;
					List<EmpleadoModel> empleados = new ArrayList<EmpleadoModel>();
					try{
						empleados = getAccesoBoService().consultarRegistroMovil();
					}catch(SQLException e){
						JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
					contadorMovil=empleados.size();
					
					for (int i = temp; i < (contadorMovil); i++) {
						estatusCheck="checando";
						chequeo(empleados.get(i).getIdEmpleado(), "boton");
						Thread.sleep (5200);
						estatusCheck="libre";
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		Thread h2 = new Thread(r2);
		h2.start();
		
	}

	/**
	 * Método para regresar al inicio
	 */
	private void regresarInicio(){
		getControlAccesoView().setVisible(false);
		getGovernment().mostrarInicio();
		getLectorHuellasController().stop();
		getControlAccesoView().h1.stop();
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
		getControlAccesoView().mes = (calendario.get(Calendar.MONTH)+1)>9?""+(calendario.get(Calendar.MONTH)+1):"0"+(calendario.get(Calendar.MONTH)+1);
		getControlAccesoView().anio = String.valueOf(calendario.get(Calendar.YEAR));
	}

	/**
	 * Método para inicializar el la fecha y hora
	 */
	@Override
	public void run() {
		Thread ct = Thread.currentThread();
		while(ct == getControlAccesoView().h1) {  
			if(estatusCheck.equals("libre")){				
				verificarContadorMovil();
			}
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
		if(e.getSource()==getControlAccesoView().btnSalir){
			regresarInicio();
		}
	}
	
	public List<EmpleadoModel> verificarHuella()throws SQLException{
		
		List<EmpleadoModel> empleados = new ArrayList<EmpleadoModel>();
		
		try {
			empleados=getAccesoBoService().consultaHuella();
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
		return empleados;
	}
	
	public void chequeo(final int id,final String tipoCheck){
		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				
				try {
					
					empleadoModel = getAccesoBoService().consultaIndividual(id);
					
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
		
		
				if(getEmpleadoModel().getIdEmpleado() == 0){
					ImageIcon error = new ImageIcon("src/main/resources/img/Error.png");
					Icon iconoError = new ImageIcon(error.getImage().getScaledInstance(
							getControlAccesoView().imgEstado.getWidth(), getControlAccesoView().imgEstado.getHeight(), Image.SCALE_DEFAULT));
					
					getControlAccesoView().imgEstado.setIcon(iconoError);
					
					esperar();
				}else{
					
					String horaActual;
					if(getControlAccesoView().ampm.equals("PM")){
						horaActual = (Integer.parseInt(controlAccesoView.hora)+12)+":"+controlAccesoView.minutos+":"+controlAccesoView.segundos;	
					}else{
						horaActual = controlAccesoView.hora+":"+controlAccesoView.minutos+":"+controlAccesoView.segundos;
					}
					String fechaActual = controlAccesoView.anio+"-"+controlAccesoView.mes+"-"+controlAccesoView.dia;
					
					listaControlAcceso = new ArrayList<ControlAccesoModel>();
					
					try {
						listaControlAcceso = getAccesoBoService().consultarControlAcceso(getEmpleadoModel().getIdEmpleado(), fechaActual);
					} catch (SQLException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
					
					listaControlAcceso.add(getControlAccesoModel());
					
					listaControlAcceso.get(listaControlAcceso.size()-1).setIdEmpleado(getEmpleadoModel().getIdEmpleado());
					listaControlAcceso.get(listaControlAcceso.size()-1).setFecha(fechaActual);
					listaControlAcceso.get(listaControlAcceso.size()-1).setHoraRegistrada(horaActual);
					
					try {
						String resultado=getAccesoBoService().insertar(listaControlAcceso.get(listaControlAcceso.size()-1));
						
						mostrarDatosEmpleado(tipoCheck);
						
						if(!resultado.equals("correcto") && !resultado.equals("No comida")){
							getControlAccesoView().lblAlerta.setText(resultado);
						}
					} catch (SQLException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
					
					esperar();
					
					getEmpleadoModel().limpiarModelo();
					getListaControlAcceso().clear();
					getControlAccesoModel().limpiarModelo();
					getTurnoModel().limpiarModelo();
					getIncidenciaModel().limpiarModelo();
				}
			}
		};
		
		Thread h2 = new Thread(r2);
		h2.start();
	}
	
	/**
	 * Método para mostrar los datos del empleado en pantalla
	 */
	public void mostrarDatosEmpleado(String tipoCheck){
		getControlAccesoView().lblEmpleado.setText("Empleado:            "+getEmpleadoModel().getNombreEmpleado()+" "+getEmpleadoModel().getApePatEmpleado()+" "+getEmpleadoModel().getApePatEmpleado());
		getControlAccesoView().lblArea.setText("Área:                      "+getEmpleadoModel().getArea());
		for (int i = 0; i < listaControlAcceso.size(); i++) {
			String hora = listaControlAcceso.get(i).getHoraRegistrada().substring(0, 2);
			String ampm;
			
			if(Integer.parseInt(hora)>12){
				if((Integer.parseInt(hora)-12)<10){
					hora = "0"+(Integer.parseInt(hora)-12);
				}else{
					hora = Integer.toString((Integer.parseInt(hora)-12));
				}
				ampm="PM";
			}else if(Integer.parseInt(hora)==12){
				hora="12";
				ampm="PM";
			}else if(Integer.parseInt(hora)==0){
				hora="12";
				ampm="AM";
			}else{
				ampm="AM";
			}
			
			switch (i) {

			case 0:
				getControlAccesoView().lblEntrada.setText("Hora de entrada                               "
						+ ""+hora+listaControlAcceso.get(i).getHoraRegistrada().substring(2)+" "+ampm);
				getControlAccesoView().lblEntrada.setVisible(true);
				break;

			case 1:
				if(listaControlAcceso.get(i).getHoraControl().equals("Hora de salida")){
					getControlAccesoView().lblSalidaComer.setText("Hora de salida a comer                           "
							+ "---");
					getControlAccesoView().lblSalidaComer.setVisible(true);
					getControlAccesoView().lblSalidaComer.setEnabled(false);
					getControlAccesoView().lblEntradaComer.setText("Hora de entrada de comer                     "
							+ "---");
					getControlAccesoView().lblEntradaComer.setVisible(true);
					getControlAccesoView().lblEntradaComer.setEnabled(false);
					getControlAccesoView().lblSalida.setText("Hora de salida                                  "
							+ ""+hora+listaControlAcceso.get(i).getHoraRegistrada().substring(2)+" "+ampm);
					getControlAccesoView().lblSalida.setVisible(true);
					getControlAccesoView().lblHorasTrabajadas.setText("Horas trabajadas                                      "
							+ ""+listaControlAcceso.get(i).getHorasTrabajadas());
					getControlAccesoView().lblHorasTrabajadas.setVisible(true);
					i=4;
				}else{
					getControlAccesoView().lblSalidaComer.setText("Hora de salida a comer                  "
							+ ""+hora+listaControlAcceso.get(i).getHoraRegistrada().substring(2)+" "+ampm);
					getControlAccesoView().lblSalidaComer.setVisible(true);
				}
				break;
				
			case 2:
				getControlAccesoView().lblEntradaComer.setText("Hora de entrada de comer            "
						+ ""+hora+listaControlAcceso.get(i).getHoraRegistrada().substring(2)+" "+ampm);
				getControlAccesoView().lblEntradaComer.setVisible(true);
				break;
			case 3: 
				getControlAccesoView().lblSalida.setText("Hora de salida                                  "
						+ ""+hora+listaControlAcceso.get(i).getHoraRegistrada().substring(2)+" "+ampm);
				getControlAccesoView().lblSalida.setVisible(true);
				getControlAccesoView().lblHorasTrabajadas.setText("Horas trabajadas                                      "
						+ ""+listaControlAcceso.get(i).getHorasTrabajadas());
				getControlAccesoView().lblHorasTrabajadas.setVisible(true);
				break;
			}
		}
		
		ImageIcon foto = new ImageIcon("src/main/resources/img/fotosempleados/"+getEmpleadoModel().getFotografia());
		Icon iconoFoto = new ImageIcon(foto.getImage().getScaledInstance(
				getControlAccesoView().imgFoto.getWidth(), getControlAccesoView().imgFoto.getHeight(), Image.SCALE_DEFAULT));
		
		ImageIcon exito = new ImageIcon("src/main/resources/img/Éxito.png");
		Icon iconoExito = new ImageIcon(exito.getImage().getScaledInstance(
				getControlAccesoView().imgEstado.getWidth(), getControlAccesoView().imgEstado.getHeight(), Image.SCALE_DEFAULT));
		
		getControlAccesoView().imgFoto.setIcon(iconoFoto);
		if(tipoCheck.equals("boton")){
			getControlAccesoView().imgEstado.setIcon(iconoExito);
		}
	}
	
	/**
	 * Método para esperar 5 segundos con los datos en pantalla
	 */
	public void esperar(){
		Runnable r2 = new Runnable() {
			
			@Override
			public void run() {
				try {
					getLectorHuellasController().stop();
					Thread.sleep (5000);
					getControlAccesoView().limpiarVentana();
					activarLecturaWiFi();
					getLectorHuellasController().start();
					setEstatusCheck("libre");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		Thread h2 = new Thread(r2);
		h2.start();
	}
}