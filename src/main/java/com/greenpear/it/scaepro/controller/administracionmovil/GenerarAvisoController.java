/**
 * 
 */
package com.greenpear.it.scaepro.controller.administracionmovil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import org.springframework.beans.factory.annotation.Autowired;

import com.greenpear.it.scaepro.bo.administracionmovil.GenerarAvisoBo;
import com.greenpear.it.scaepro.controller.government.GovernmentService;
import com.greenpear.it.scaepro.model.administracionmovil.GenerarAvisoModel;
import com.greenpear.it.scaepro.view.administracionmovil.GenerarAvisoView;

/**
 * @author EDSONJOSUE
 *
 */
public class GenerarAvisoController implements ActionListener, ItemListener{
	// ********ESTANCIAS***********************************
		@Autowired
		private GovernmentService government;

		@Autowired
		private GenerarAvisoView vista;
		
		@Autowired
		private GenerarAvisoModel modelo;
		
		@Autowired
		private GenerarAvisoBo Bo;

		public GovernmentService getGovernment() {
			return government;
		}

		public GenerarAvisoView getVista() {
			return vista;
		}
		
		public GenerarAvisoModel getModelo() {
			return modelo;
		}
		
		public GenerarAvisoBo getBo() {
			return Bo;
		}

		// ***********FIN DE ESTANCIAS****************
		public void mostrarVistaGenerarAviso() {
			if (getVista().btnEnviar.getActionListeners().length == 0) {
				getVista().btnEnviar.addActionListener(this);
				getVista().cmbTipoAviso.addItemListener(this);
			}
			getVista().setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			String tipoAviso = getVista().cmbTipoAviso.getSelectedItem().toString();
			if(tipoAviso.equals("General")){
				
			}
		}
}
