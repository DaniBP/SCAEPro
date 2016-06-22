/**
 * 
 */
package com.greenpear.it.scaepro.controller.administracionmovil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.beans.factory.annotation.Autowired;

import com.greenpear.it.scaepro.bo.administracionmovil.NuevaNoticiaBo;
import com.greenpear.it.scaepro.controller.government.GovernmentService;
import com.greenpear.it.scaepro.dao.administracionmovil.NuevaNoticiaDao;
import com.greenpear.it.scaepro.view.administracionmovil.NuevaNoticiaView;

/**
 * @author EDSONJOSUE
 *
 */
public class NuevaNoticiaController implements ActionListener {
	// ********ESTANCIAS***********************************
	@Autowired
	private GovernmentService government;

	@Autowired
	private NuevaNoticiaView vista;

	@Autowired
	private NuevaNoticiaBo bo;

	@Autowired
	private NuevaNoticiaDao dao;

	public GovernmentService getGovernment() {
		return government;
	}

	public NuevaNoticiaView getVista() {
		return vista;
	}

	public NuevaNoticiaBo getBo() {
		return bo;
	}

	public NuevaNoticiaDao getDao() {
		return dao;
	}
	// ***********FIN DE ESTANCIAS****************

	public void mostrarNuevaNoticia() {
		if (getVista().btnRegistrar.getActionListeners().length == 0) {
			getVista().btnRegistrar.addActionListener(this);
			getVista().btnSubirFoto.addActionListener(this);
		}
		getVista().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
