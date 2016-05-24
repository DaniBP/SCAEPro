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
import com.greenpear.it.scaepro.model.accesosistema.AccesoAlSistemaModel;
import com.greenpear.it.scaepro.view.accesosistema.AccesoAlSistemaView;

/**
 * 
 * @author DanielBP
 *
 */
@Controller
public class AccesoAlSistemaController implements ActionListener{

//**********************DECLARACI�N DE ESTANCIAS****************************
	
	@Autowired
	private AccesoAlSistemaView loginView;
	
	@Autowired
	private AccesoAlSistemaModel loginModel;
	
	@Autowired
	@Qualifier("loginBoService")
	private AccesoAlSistemaBo loginBO;
	
	
	private AccesoAlSistemaView getLoginView() {
		return loginView;
	}
	
	private AccesoAlSistemaModel getLoginModel() {
		return loginModel;
	}
	
	private AccesoAlSistemaBo getLoginBoService(){
		return loginBO;
	}
//**********************FIN DE ESTANCIAS****************************
	
	public void mostrarVista(){
		getLoginView().setVisible(true);
		getLoginView().btnAcceder.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == getLoginView().btnAcceder) {
			System.out.println("Bonton Prensado");
			if(getLoginView().txtUsuario.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Ingrese el nombre de usuario","Acceso",JOptionPane.WARNING_MESSAGE);
				return;
			}else if(getLoginView().txtContrase.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Ingrese la contrase�a","Acceso",JOptionPane.WARNING_MESSAGE);
				JOptionPane.showMessageDialog(null, "Hola");
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
			}else{
				JOptionPane.showMessageDialog(null, acceso,"Acceso",JOptionPane.WARNING_MESSAGE);
			}		
		}
	}
}