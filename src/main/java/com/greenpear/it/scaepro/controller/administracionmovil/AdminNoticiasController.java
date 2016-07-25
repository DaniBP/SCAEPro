/**
 * 
 */
package com.greenpear.it.scaepro.controller.administracionmovil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.beans.factory.annotation.Autowired;

import com.greenpear.it.scaepro.controller.government.GovernmentService;
import com.greenpear.it.scaepro.model.administracionmovil.NoticiasModel;
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
	
	@Autowired
	private NoticiasModel modelo;

	public GovernmentService getGovernment() {
		return government;
	}

	public AdminNoticiasView getVista() {
		return vista;
	}
	
	public NoticiasModel getModelo() {
		return modelo;
	}

	// ***********FIN DE ESTANCIAS****************
	public void mostrarVistaAdminNoticias() {
		if (getVista().btnNoticiaNueva.getActionListeners().length == 0) {
			getVista().btnNoticiaNueva.addActionListener(this);
			getVista().btnNoticiasExistentes.addActionListener(this);
		}
		getVista().setVisible(true);
		getVista().toFront();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Botón Noticia Nueva
		if (e.getSource() == getVista().btnNoticiaNueva) {
			getModelo().setNombreVentana("Nueva Noticia");
			getGovernment().mostrarNuevaNoticia();
		//	Botón Noticias Existentes
		} else if (e.getSource() == getVista().btnNoticiasExistentes) {
			getGovernment().mostrarNoticiasExistentes();
		}
	}

}
