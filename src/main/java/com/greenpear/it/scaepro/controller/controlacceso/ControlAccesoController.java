package com.greenpear.it.scaepro.controller.controlacceso;

import org.springframework.beans.factory.annotation.Autowired;

import com.greenpear.it.scaepro.controller.government.GovernmentService;
import com.greenpear.it.scaepro.view.controlacceso.ControlAccesoView;

public class ControlAccesoController {
	
	/**
	 * Constructor
	 */
	public ControlAccesoController() {
		super();
	}
	
	@Autowired
	private GovernmentService government;
	
	@Autowired
	private ControlAccesoView vistaControlAcceso;

	public GovernmentService getGovernment() {
		return government;
	}

	public ControlAccesoView getVistaControlAcceso() {
		return vistaControlAcceso;
	}
	
	public void mostrarVistaControlAcceso(){
		getVistaControlAcceso().setVisible(true);
	}
}
