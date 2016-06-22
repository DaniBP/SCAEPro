/**
 * 
 */
package com.greenpear.it.scaepro.controller.administracionmovil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.beans.factory.annotation.Autowired;

import com.greenpear.it.scaepro.bo.administracionmovil.NoticiasExistentesBo;
import com.greenpear.it.scaepro.controller.government.GovernmentService;
import com.greenpear.it.scaepro.dao.administracionmovil.NoticiasExistentesDao;
import com.greenpear.it.scaepro.view.administracionmovil.NoticiasExistentesView;

/**
 * @author EDSONJOSUE
 *
 */
public class NoticiasExistentesController implements ActionListener {
	// ********ESTANCIAS***********************************
	@Autowired
	private GovernmentService government;

	@Autowired
	private NoticiasExistentesView vista;

	@Autowired
	private NoticiasExistentesBo bo;

	@Autowired
	private NoticiasExistentesDao dao;

	public GovernmentService getGovernment() {
		return government;
	}

	public NoticiasExistentesView getVista() {
		return vista;
	}

	public NoticiasExistentesBo getBo() {
		return bo;
	}

	public NoticiasExistentesDao getDao() {
		return dao;
	}

	// ***********FIN DE ESTANCIAS****************
	public void mostrarNoticiasExistentes() {
		getVista().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
