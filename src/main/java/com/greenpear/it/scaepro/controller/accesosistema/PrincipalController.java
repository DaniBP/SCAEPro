package com.greenpear.it.scaepro.controller.accesosistema;

import org.springframework.beans.factory.annotation.Autowired;

import com.greenpear.it.scaepro.controller.government.GovernmentService;
import com.greenpear.it.scaepro.view.accesosistema.PrincipalView;

/**
 * 
 * @author DanielBP
 *
 */
public class PrincipalController {
	
	/**
	 * Constructor
	 */
	public PrincipalController() {
		super();
	}
	
	@Autowired
	private GovernmentService government;
	
	@Autowired
	private PrincipalView vistaPrincipal;

	public GovernmentService getGovernment() {
		return government;
	}

	public PrincipalView getVistaPrincipal() {
		return vistaPrincipal;
	}
	
	public void mostrarVistaPrincipal(){
		getVistaPrincipal().setVisible(true);
	}
}
