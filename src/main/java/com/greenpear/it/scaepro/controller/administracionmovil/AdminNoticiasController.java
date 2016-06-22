/**
 * 
 */
package com.greenpear.it.scaepro.controller.administracionmovil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.beans.factory.annotation.Autowired;

import com.greenpear.it.scaepro.controller.government.GovernmentService;
import com.greenpear.it.scaepro.view.administracionmovil.AdminNoticiasView;

/**
 * @author EDSONJOSUE
 *
 */
public class AdminNoticiasController implements ActionListener {
	// ********ESTANCIAS***********************************
	@Autowired
	private GovernmentService government;

	@Autowired
	private AdminNoticiasView vista;

	public GovernmentService getGovernment() {
		return government;
	}

	public AdminNoticiasView getVista() {
		return vista;
	}

	// ***********FIN DE ESTANCIAS****************
	public void mostrarVistaAdminNoticias() {
		if (getVista().btnNoticiaNueva.getActionListeners().length == 0) {
			getVista().btnNoticiaNueva.addActionListener(this);
			getVista().btnNoticiasExistentes.addActionListener(this);
		}
		getVista().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Bot�n Noticia Nueva
		if (e.getSource() == getVista().btnNoticiaNueva) {
		//	Bot�n Noticias Existentes
		} else if (e.getSource() == getVista().btnNoticiasExistentes) {

		}
	}

}
