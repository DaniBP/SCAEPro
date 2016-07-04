/**
 * 
 */
package com.greenpear.it.scaepro.controller.administracionmovil;

import java.awt.Image;

import javax.swing.ImageIcon;

import org.springframework.beans.factory.annotation.Autowired;

import com.greenpear.it.scaepro.controller.government.GovernmentService;
import com.greenpear.it.scaepro.model.administracionmovil.NoticiasModel;
import com.greenpear.it.scaepro.view.administracionmovil.NoticiaImagenView;

/**
 * @author EDSONJOSUE
 *
 */
public class NoticiaImagenController {
	// ********ESTANCIAS***********************************
		@Autowired
		private GovernmentService government;

		@Autowired
		private NoticiaImagenView vista;
		
		@Autowired
		private NoticiasModel modelo;
		
		public GovernmentService getGovernment() {
			return government;
		}

		public NoticiaImagenView getVista() {
			return vista;
		}
		
		public NoticiasModel getModelo() {
			return modelo;
		}
		
		public void mostrarImagenAmpliada() {
			getVista().setTitle(getModelo().getImagenNoticia());
			ImageIcon icono = new ImageIcon(
					"C:/Users/EDSONJOSUE/Documents/WorkSpaceSpringTools/SCAEPro/src/main/resources/img/imgsNoticias/"
							+ getModelo().getImagenNoticia());
			Image img = icono.getImage();
			Image nuevaImg = img.getScaledInstance(544, 321, java.awt.Image.SCALE_SMOOTH);
			ImageIcon newIcono = new ImageIcon(nuevaImg);
			getVista().lblImagen.setIcon(newIcono);
			getVista().lblImagen.setBounds(0, 0, 544, 321);
			getVista().setVisible(true);
		}
		
}
