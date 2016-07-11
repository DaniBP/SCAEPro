/**
 * 
 */
package com.greenpear.it.scaepro.controller.solicitudreporte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.beans.factory.annotation.Autowired;

import com.greenpear.it.scaepro.bo.solicitudreporte.SolicitudReporteBo;
import com.greenpear.it.scaepro.controller.government.GovernmentService;
import com.greenpear.it.scaepro.model.solicitudreporte.SolicitudReporteModel;
import com.greenpear.it.scaepro.view.solicitudreporte.SolicitudReporteView;

/**
 * @author EDSONJOSUE
 *
 */
public class SolicitudReporteController implements ActionListener {
	// ********ESTANCIAS***********************************
		@Autowired
		private GovernmentService government;

		@Autowired
		private SolicitudReporteView vista;
		
		@Autowired
		private SolicitudReporteModel modelo;
		
		@Autowired
		private SolicitudReporteBo bo;

		public GovernmentService getGovernment() {
			return government;
		}

		public SolicitudReporteView getVista() {
			return vista;
		}
		
		public SolicitudReporteModel getModelo() {
			return modelo;
		}
		public SolicitudReporteBo getBo() {
			return bo;
		}

		// ***********FIN DE ESTANCIAS****************
		public void mostrarVistaSolicitudReporte() {
//			if (getVista().btnNoticiaNueva.getActionListeners().length == 0) {
//				getVista().btnNoticiaNueva.addActionListener(this);
//				getVista().btnNoticiasExistentes.addActionListener(this);
//			}
			getVista().setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}

}
