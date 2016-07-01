package com.greenpear.it.scaepro.controller.administracionmovil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.beans.factory.annotation.Autowired;

import com.greenpear.it.scaepro.bo.administracionmovil.EstatusPagoBo;
import com.greenpear.it.scaepro.controller.government.GovernmentService;
import com.greenpear.it.scaepro.model.administracionmovil.EstatusPagoModel;
import com.greenpear.it.scaepro.view.administracionmovil.EstatusPagoView;

public class EstatusPagoController implements ActionListener{
	// ********ESTANCIAS***********************************
			@Autowired
			private GovernmentService government;

			@Autowired
			private EstatusPagoView vista;
			
			@Autowired
			private EstatusPagoModel modelo;
			
			@Autowired
			private EstatusPagoBo Bo;

			public GovernmentService getGovernment() {
				return government;
			}

			public EstatusPagoView getVista() {
				return vista;
			}
			
			public EstatusPagoModel getModelo() {
				return modelo;
			}
			
			public EstatusPagoBo getBo() {
				return Bo;
			}

			// ***********FIN DE ESTANCIAS****************
			public void mostrarVistaEstatusPago() {
//				if (getVista().btnEnviar.getActionListeners().length == 0) {
//					getVista().btnEnviar.addActionListener(this);
//				}
				getVista().setVisible(true);
			}

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
}
