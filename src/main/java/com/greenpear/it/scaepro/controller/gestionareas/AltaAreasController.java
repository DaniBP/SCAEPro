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
import com.greenpear.it.scaepro.model.gestionareas.ConsultaAreasModel;
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
	private ConsultaAreasModel modelo;
	
	@Autowired
	private AltaAreasView vista;
	
	@Autowired
	@Qualifier("altaAreasBo")
	private AltaAreasBo bo;
	
	public GovernmentService getGoverment(){
		return government;
	}
	
	private ConsultaAreasModel getModelo() {
		return modelo;
	}
	
	public AltaAreasView getVista() {
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
			getVista().toFront();
			editarArea();
	 }

	private void editarArea() {
		if(getModelo().getArea() != null){
			getVista().txtArea.setText(getModelo().getArea());
			getModelo().setAreaAnterior(getVista().txtArea.getText());
			getVista().txtDescArea.setText(getModelo().getDescripcionArea());
			getVista().btnRegistrar.setText("Editar");
			getVista().btnLimpiar.setText("Limpiar Descripción");
			getVista().btnLimpiar.setBounds(340, 240, 160, 23);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//------Limpiar editar----------
		if (e.getSource() == getVista().btnLimpiar && getVista().btnLimpiar.getText() == "Limpiar Descripción"){
			getVista().txtDescArea.setText("");
		}else if (e.getSource() == getVista().btnLimpiar && getVista().btnLimpiar.getText() == "Limpiar"){
				getVista().txtArea.setText("");
				getVista().txtDescArea.setText("");
			}
		//------Registrar--------
		else if (e.getSource() == getVista().btnRegistrar && getVista().btnRegistrar.getText() == "Registrar" ) {
			if(getVista().txtArea.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Ingrese un nombre de área","Registar Área",JOptionPane.WARNING_MESSAGE);
				return;
			}else if(getVista().txtDescArea.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Ingrese una descripción del área","Registar Área",JOptionPane.WARNING_MESSAGE);
				return;
			}
			getModelo().setArea(getVista().txtArea.getText());
			getModelo().setDescripcionArea(getVista().txtDescArea.getText());
			String acceso=null;
			try {
				acceso=getBo().insertar(getModelo());
			} catch (SQLException t) {
				JOptionPane.showMessageDialog(null, t.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
				JOptionPane.showMessageDialog(null, acceso,"Acceso",JOptionPane.INFORMATION_MESSAGE);
				return;
		//------Editar--------
		}else if (e.getSource() == getVista().btnRegistrar && getVista().btnRegistrar.getText() == "Editar" ) {
			if(getVista().txtArea.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Ingrese un nombre de área","Editar Área",JOptionPane.WARNING_MESSAGE);
				return;
			}else if(getVista().txtDescArea.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Ingrese una descripción del área","Editar Área",JOptionPane.WARNING_MESSAGE);
				return;
			}
			getModelo().setArea(getVista().txtArea.getText());
			getModelo().setDescripcionArea(getVista().txtDescArea.getText());
			String acceso=null;
			try {
				acceso=getBo().editar(getModelo());
			} catch (SQLException t) {
				JOptionPane.showMessageDialog(null, t.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
			
			if(acceso == "¡Ya Existe Un Área Con Éste Nombre!"){
				JOptionPane.showMessageDialog(null, acceso,"Acceso",JOptionPane.INFORMATION_MESSAGE);
				return;
			}else{
				JOptionPane.showMessageDialog(null, acceso,"Acceso",JOptionPane.INFORMATION_MESSAGE);
				getGoverment().getConsultaAreasController().mostrarVistaConsultarAreas();
				getVista().setVisible(false);
				return;
			}
		}
	}

}
