/**
 * 
 */
package com.greenpear.it.scaepro.controller.government;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.greenpear.it.scaepro.controller.accesosistema.*;
import com.greenpear.it.scaepro.controller.controlacceso.ControlAccesoController;
import com.greenpear.it.scaepro.controller.gestionareas.AltaAreasController;
import com.greenpear.it.scaepro.controller.gestionareas.ConsultaAreasController;;


/**
 * @author DanielBP
 *
 */
public class GovernmentService implements Government {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	/**
	 * Constructor
	 */
	public GovernmentService() {
		super();
	}
	
	//****************ESTANCIAS*****************
	@Autowired private InicioController inicioController;
	@Autowired private AccesoAlSistemaController loginController;
	@Autowired private PrincipalController principalController;
	@Autowired private ControlAccesoController controlAccesoController;
	@Autowired private AltaAreasController altaAreasController; 
	@Autowired private ConsultaAreasController consultaAreasController;

	public InicioController getInicioController() {
		return inicioController;
	}

	public AccesoAlSistemaController getLoginController() {
		return loginController;
	}
	
	public PrincipalController getPrincipalController() {
		return principalController;
	}
	
	public ControlAccesoController getControlAccesoController() {
		return controlAccesoController;
	}
	
	public AltaAreasController getAltaAreasController() {
		return altaAreasController;
	}
	
	public ConsultaAreasController getConsultaAreasController() {
		return consultaAreasController;
	}
	//*************FIN DE ESTANCIAS***************
	
	
	//*************CONTROL DE VENTANAS**************


	@Override
	public void mostrarInicio() {
		getInicioController().mostrarVistaInicio();
	}
	
	@Override
	public void mostrarLogin() {
		getLoginController().mostrarVistaLogin();
	}

	@Override
	public void mostrarVentanaPrincipal() {
		getPrincipalController().mostrarVistaPrincipal();
	}
	
	@Override
	public void mostrarControlAcceso(){
		getControlAccesoController().mostrarVistaControlAcceso();
	}

	@Override
	public void mostrarVistaRegistrarAreas(){
		getAltaAreasController().mostrarVistaRegistrarAreas();
	}
	
	@Override
	public void mostrarVistaConsultarAreas(){
		getConsultaAreasController().mostrarVistaConsultarAreas();
	}
}
