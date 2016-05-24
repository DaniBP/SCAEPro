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
	
	@Autowired
	private AccesoAlSistemaController loginController;
	
	public GovernmentService() {
		super();
	}
	
	@Override
	public void doShowLogin() {
		getLoginController().mostrarVista();
	}

	public AccesoAlSistemaController getLoginController() {
		return loginController;
	}

	@Override
	public void doShowVentanaPrincipal() {
		// TODO Auto-generated method stub
		
	}
}
