/**
 * 
 */
package com.greenpear.it.scaepro.controller.government;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.greenpear.it.scaepro.controller.accesosistema.*;;


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

	public InicioController getInicioController() {
		return inicioController;
	}

	public AccesoAlSistemaController getLoginController() {
		return loginController;
	}
	
	public PrincipalController getPrincipalController() {
		return principalController;
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
}
