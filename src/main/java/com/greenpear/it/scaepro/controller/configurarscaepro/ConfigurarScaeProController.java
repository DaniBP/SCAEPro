package com.greenpear.it.scaepro.controller.configurarscaepro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.greenpear.it.scaepro.controller.government.GovernmentService;
import com.greenpear.it.scaepro.view.configurarscaepro.ConfigurarScaePro;

@Controller
public class ConfigurarScaeProController implements ActionListener{

	/**
	 * Constructor
	 */
	public ConfigurarScaeProController() {
		super();
	}

//**********************ESTANCIAS****************************
	@Autowired
	private GovernmentService government;
	
	@Autowired
	private ConfigurarScaePro configurarScaeProView;

	public GovernmentService getGovernment() {
		return government;
	}

	public ConfigurarScaePro getConfigurarScaeProView() {
		return configurarScaeProView;
	}
	
//**********************FIN DE ESTANCIAS***********************
	
	public void mostrarVistaConfigurarScaePro(){
		getConfigurarScaeProView().setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
	}
}
