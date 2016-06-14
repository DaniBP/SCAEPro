package com.greenpear.it.scaepro.controller.gestionareas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.greenpear.it.scaepro.bo.gestionareas.AltaAreasBo;
import com.greenpear.it.scaepro.controller.government.GovernmentService;
import com.greenpear.it.scaepro.model.gestionareas.AltaAreasModel;
import com.greenpear.it.scaepro.view.gestionareas.AltaAreasView;
/**
 * @author EDSONJOSUE
 *
 */
@Controller
public class AltaAreasController implements ActionListener{
	//********ESTANCIAS***********************************
	@Autowired
	private GovernmentService government;
	
	@Autowired
	private AltaAreasModel modelo;
	
	@Autowired
	private AltaAreasView vista;
	
	@Autowired
	@Qualifier("altaAreasBo")
	private AltaAreasBo bo;
	
	public GovernmentService getGoverment(){
		return government;
	}
	
	private AltaAreasModel getModelo() {
		return modelo;
	}
	
	private AltaAreasView getVista() {
		return vista;
	}
	
	private AltaAreasBo getBo() {
		return bo;
	}

	//***********FIN DE ESTANCIAS****************
	 public void mostrarVistaRegistrarAreas(){
		 if(getVista().btnRegistrar.getActionListeners().length == 0){
		 getVista().btnRegistrar.addActionListener(this);
		 getVista().btnLimpiar.addActionListener(this);
		 
		 }
			
			getVista().setVisible(true);
	 }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == getVista().btnRegistrar) {
			if(getVista().txtArea.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Ingrese el nombre del área","Registar Área",JOptionPane.WARNING_MESSAGE);
				return;
			}else if(getVista().txtDescArea.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Ingrese la descripción del área","Registar Área",JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			getModelo().setArea(getVista().txtArea.getText());
			getModelo().setDescripcion(getVista().txtDescArea.getText());
			String acceso=null;
			
			try {
				acceso=getBo().insertar(getModelo());
			} catch (SQLException t) {
				JOptionPane.showMessageDialog(null, t.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
			
//			if(acceso.equals("Registro exitoso!")){
//				JOptionPane.showMessageDialog(null, acceso,"Acceso",JOptionPane.INFORMATION_MESSAGE);
////				getGovernment().mostrarVentanaPrincipal();
//			}else{
//				JOptionPane.showMessageDialog(null, acceso,"Acceso",JOptionPane.WARNING_MESSAGE);
//			}
		}
		
	}

}
