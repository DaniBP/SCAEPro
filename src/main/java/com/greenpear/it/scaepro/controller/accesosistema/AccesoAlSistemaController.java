package com.greenpear.it.scaepro.controller.accesosistema;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.greenpear.it.scaepro.bo.accesosistema.AccesoAlSistemaBo;
import com.greenpear.it.scaepro.controller.government.GovernmentService;
import com.greenpear.it.scaepro.model.accesosistema.AccesoAlSistemaModel;
import com.greenpear.it.scaepro.view.accesosistema.AccesoAlSistemaView;

/**
 * 
 * @author DanielBP
 *
 */
@Controller
public class AccesoAlSistemaController implements ActionListener{

//**********************ESTANCIAS****************************
	@Autowired 
	private GovernmentService government;
	
	@Autowired
	private AccesoAlSistemaView loginView;
	
	@Autowired
	private AccesoAlSistemaModel loginModel;
	
	@Autowired
	@Qualifier("loginBoService")
	private AccesoAlSistemaBo loginBO;
	
	
	public GovernmentService getGovernment() {
		return government;
	}

	private AccesoAlSistemaView getLoginView() {
		return loginView;
	}
	
	private AccesoAlSistemaModel getLoginModel() {
		return loginModel;
	}
	
	private AccesoAlSistemaBo getLoginBoService(){
		return loginBO;
	}
//**********************FIN DE ESTANCIAS***********************
	
	/**
	 * M�todo para mostrar la ventana de acceso al sistema
	 */
	public void mostrarVistaLogin(){		
		
		if(getLoginView().btnAcceder.getActionListeners().length == 0){
			getLoginView().btnAcceder.addActionListener(this);
			getLoginView().btnRegresar.addActionListener(this);
		}
		
		getLoginView().setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == getLoginView().btnAcceder) {
			if(getLoginView().txtUsuario.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Ingrese el nombre de usuario","Acceso",JOptionPane.WARNING_MESSAGE);
				return;
			}else if(getLoginView().txtContrase.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Ingrese la contrase�a","Acceso",JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			getLoginModel().setUsuario(getLoginView().txtUsuario.getText());
			getLoginModel().setPassword(getLoginView().txtContrase.getText());
			String acceso=null;
			
			try {
				acceso=getLoginBoService().consultarUsuario(getLoginModel());
			} catch (SQLException t) {
				JOptionPane.showMessageDialog(null, t.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
			
			if(acceso.equals("Acceso concedido!")){
				JOptionPane.showMessageDialog(null, acceso,"Acceso",JOptionPane.INFORMATION_MESSAGE);
				getGovernment().mostrarVentanaPrincipal();
			}else{
				JOptionPane.showMessageDialog(null, acceso,"Acceso",JOptionPane.WARNING_MESSAGE);
			}
		} else if(e.getSource() == getLoginView().btnRegresar){
			getLoginView().setVisible(false);
			getGovernment().mostrarInicio();
		}
	}
}