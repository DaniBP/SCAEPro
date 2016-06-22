/**
 * 
 */
package com.greenpear.it.scaepro.controller.administracionmovil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;

import com.greenpear.it.scaepro.bo.administracionmovil.NuevaNoticiaBo;
import com.greenpear.it.scaepro.controller.government.GovernmentService;
import com.greenpear.it.scaepro.dao.administracionmovil.NuevaNoticiaDao;
import com.greenpear.it.scaepro.model.administracionmovil.NoticiasModel;
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
	private NoticiasModel modelo;

	public GovernmentService getGovernment() {
		return government;
	}

	public NuevaNoticiaView getVista() {
		return vista;
	}

	public NuevaNoticiaBo getBo() {
		return bo;
	}

	public NoticiasModel getModelo() {
		return modelo;
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
	//------Registrar--------
	if (e.getSource() == getVista().btnRegistrar) {
		if(getVista().txtTitulo.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Ingrese un título de la noticia","Nueva Noticia",JOptionPane.WARNING_MESSAGE);
			return;
		}else if(getVista().txtDescNoticia.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Ingrese una descripción de la noticia","Nueva Noticia",JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		getModelo().setTituloNoticia(getVista().txtTitulo.getText());
		getModelo().setDescNoticia(getVista().txtDescNoticia.getText());
		String registro=null;
		
		try {
			registro=getBo().insertar(getModelo());
		} catch (SQLException t) {
			JOptionPane.showMessageDialog(null, t.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
			JOptionPane.showMessageDialog(null, registro,"Acceso",JOptionPane.INFORMATION_MESSAGE);
			return;
	//------Editar--------
	}

	}

}
