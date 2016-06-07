package com.greenpear.it.scaepro.controller.accesosistema;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.beans.factory.annotation.Autowired;

import com.greenpear.it.scaepro.controller.government.GovernmentService;
import com.greenpear.it.scaepro.view.accesosistema.InicioView;

/**
 * 
 * @author DanielBP
 *
 */
public class InicioController implements ActionListener{

	/**
	 * Constructor
	 */
	public InicioController() {
		super();
	}


	@Autowired
	private GovernmentService governmet;
	
	@Autowired
	private InicioView vistaInicio;

	public GovernmentService getGovernmet() {
		return governmet;
	}

	public InicioView getVistaInicio() {
		return vistaInicio;
	}
	
	public void mostrarVistaInicio(){
		if(getVistaInicio().btnAdministracion.getActionListeners().length == 0){
			getVistaInicio().btnAdministracion.addActionListener(this);
			getVistaInicio().btnControlDeAcceso.addActionListener(this);
		}
		getVistaInicio().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == getVistaInicio().btnAdministracion){
			getVistaInicio().setVisible(false);
			getGovernmet().mostrarLogin();
		}else if(ae.getSource() == getVistaInicio().btnControlDeAcceso){
			getVistaInicio().setVisible(false);
			getGovernmet().mostrarControlAcceso();
		}
	}
}
