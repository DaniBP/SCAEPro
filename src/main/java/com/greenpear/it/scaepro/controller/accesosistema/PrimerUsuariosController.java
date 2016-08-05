package com.greenpear.it.scaepro.controller.accesosistema;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.greenpear.it.scaepro.bo.gestionusuarios.AltaUsuariosBo;
import com.greenpear.it.scaepro.controller.accesosistema.PrincipalController;
import com.greenpear.it.scaepro.controller.government.GovernmentService;
import com.greenpear.it.scaepro.model.gestionusuarios.ConsultaUsuariosModel;
import com.greenpear.it.scaepro.view.accesosistema.PrimerUsuarioView;
import com.greenpear.it.scaepro.view.gestionusuarios.AltaUsuariosView;
/**
 * @author Alan Aguilar
 *
 */
@Controller
public class PrimerUsuariosController implements ActionListener{
	//********ESTANCIAS***********************************
	@Autowired
	private GovernmentService government;
	
	@Autowired
	private ConsultaUsuariosModel modelo;
	
	@Autowired
	private PrimerUsuarioView vista;
	
	@Autowired
	@Qualifier("altaUsuariosBo")
	private AltaUsuariosBo bo;
	
	public GovernmentService getGoverment(){
		return government;
	}
	
	private ConsultaUsuariosModel getModelo() {
		return modelo;
	}
	
	public PrimerUsuarioView getVista() {
		return vista;
	}
	
	private AltaUsuariosBo getBo() {
		return bo;
	}


	//***********FIN DE ESTANCIAS****************
	 public void mostrarVistaRegistrarUsuarios(){
		 if(getVista().btnRegistrar.getActionListeners().length == 0){
			 getVista().btnRegistrar.addActionListener(this);
			 getVista().btnLimpiar.addActionListener(this);		 
		 }

		 	getVista().setVisible(true);
			editarUsuario();
	 }

	private void editarUsuario() {
		//aqui se recuperan los demas datos
		if(getModelo().getNombreUsuario() != null){
			getVista().txtNombre.setText(getModelo().getNombreUsuario());
			getModelo().setUsuarioAnterior(getVista().txtNombre.getText());
			getVista().txtPassword.setText(getModelo().getPasswordUsuario());
			getVista().txtPassword2.setText(getModelo().getPasswordUsuario());
			getVista().txtEmail.setText(getModelo().getCorreoUsuario());
			getVista().txtEmail2.setText(getModelo().getCorreoUsuario());
			

			if(getModelo().getEstatusUsuario() == 1 ){
				getVista().rbtnActivo.setSelected(true);
				
			}else if(getModelo().getEstatusUsuario() == 2){
				getVista().rbtnInactivo.setSelected(true);
					
			}
			if(getModelo().getEstatusEnvio() == 1){
				getVista().chbxNotificacion.setSelected(true);
				
			}else if(getModelo().getEstatusEnvio() ==2){
				getVista().chbxNotificacion.setSelected(false);
			}
			
			
			getVista().btnRegistrar.setText("Editar");
			getVista().btnLimpiar.setText("Limpiar Todo");
			getVista().btnLimpiar.setBounds(160, 346, 89, 23);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//------Limpiar editar----------
		if (e.getSource() == getVista().btnLimpiar && getVista().btnLimpiar.getText() == "Limpiar Todo"){
			getVista().txtNombre.setText("");
			getVista().txtPassword.setText("");
			getVista().txtPassword2.setText("");
			getVista().txtEmail.setText("");
			getVista().txtEmail2.setText("");
			//getVista().rbtnActivo.isSelected();
			
		}else if (e.getSource() == getVista().btnLimpiar && getVista().btnLimpiar.getText() == "Limpiar"){
				getVista().txtNombre.setText("");
				getVista().txtPassword.setText("");
				getVista().txtPassword2.setText("");
				getVista().txtEmail.setText("");
				getVista().txtEmail2.setText("");
//				if (getVista().rbtnActivo.isSelected()) {
//					getModelo().setEstatusUsuario(1);
//			
//				}else if (getVista().rbtnInactivo.isSelected()){
//					getModelo().setEstatusUsuario(2);
//				}
//				
//				if (getVista().chbxNotificacion.isSelected()){
//					getModelo().setEstatusEnvio(1);
//					
//				}else if (!getVista().chbxNotificacion.isSelected()){
//					getModelo().setEstatusEnvio(2);
//				}
				
			}
		//------Registrar--------
		else if (e.getSource() == getVista().btnRegistrar && getVista().btnRegistrar.getText() == "Registrar" ) {
			
			if(getVista().txtNombre.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Ingrese el nombre de usuario","Registar Usuario",JOptionPane.WARNING_MESSAGE);
				return;
				
			}else if(getVista().txtPassword.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Ingrese el password del usuario","Registar Usuario",JOptionPane.WARNING_MESSAGE);
				return;
				
			}else if(getVista().txtPassword2.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Ingrese el password2 del usuario","Registar Usuario",JOptionPane.WARNING_MESSAGE);
				return;	
				
			}else if(getVista().txtEmail.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Ingrese el correo del usuario","Registar Usuario",JOptionPane.WARNING_MESSAGE);
				return;
				
			}else if(getVista().txtEmail2.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Ingrese el correo2 del usuario","Registar Usuario",JOptionPane.WARNING_MESSAGE);
				return;	
				
			}else if(getVista().rbtnActivo.isSelected() == false && getVista().rbtnInactivo.isSelected() == false){
				JOptionPane.showMessageDialog(null, "Ingrese el Estatus del usuario","Registar Usuario",JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			getModelo().setNombreUsuario(getVista().txtNombre.getText());
			getModelo().setPasswordUsuario(getVista().txtPassword.getText());
			getModelo().setPasswordUsuario2(getVista().txtPassword2.getText());
			getModelo().setCorreoUsuario(getVista().txtEmail.getText());
			getModelo().setCorreoUsuario2(getVista().txtEmail2.getText());
			
			if (getVista().rbtnActivo.isSelected()) {
				getModelo().setEstatusUsuario(1);
		
			}else if (getVista().rbtnInactivo.isSelected()){
				getModelo().setEstatusUsuario(2);
			}
			
			if (getVista().chbxNotificacion.isSelected()){
				getModelo().setEstatusEnvio(1);
				
			}else if (!getVista().chbxNotificacion.isSelected()){
				getModelo().setEstatusEnvio(2);
			}
			
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
			
			if(getVista().txtNombre.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Ingrese el nombre de usuario","Registar Usuario",JOptionPane.WARNING_MESSAGE);
				return;
			}else if(getVista().txtPassword.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Ingrese el password del usuario","Registar Usuario",JOptionPane.WARNING_MESSAGE);
				return;
				
			}else if(getVista().txtPassword2.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Ingrese el password2 del usuario","Registar Usuario",JOptionPane.WARNING_MESSAGE);
				return;	
				
			}else if(getVista().txtEmail.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Ingrese el correo del usuario","Registar Usuario",JOptionPane.WARNING_MESSAGE);
				return;
				
			}else if(getVista().txtEmail2.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Ingrese el correo2 del usuario","Registar Usuario",JOptionPane.WARNING_MESSAGE);
				return;	
				
			}else if(getVista().rbtnActivo.isSelected() == false && getVista().rbtnInactivo.isSelected() == false){
				JOptionPane.showMessageDialog(null, "Ingrese el Estatus del usuario","Registar Usuario",JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			
			getModelo().setNombreUsuario(getVista().txtNombre.getText());
			getModelo().setPasswordUsuario(getVista().txtPassword.getText());
			getModelo().setPasswordUsuario2(getVista().txtPassword2.getText());
			getModelo().setCorreoUsuario(getVista().txtEmail.getText());
			getModelo().setCorreoUsuario2(getVista().txtEmail2.getText());
			
			if (getVista().rbtnActivo.isSelected()) {
				getModelo().setEstatusUsuario(1);
		
			}else if (getVista().rbtnInactivo.isSelected()){
				getModelo().setEstatusUsuario(2);
			}
			
			if (getVista().chbxNotificacion.isSelected()){
				getModelo().setEstatusEnvio(1);
				
			}else if (!getVista().chbxNotificacion.isSelected()){
				getModelo().setEstatusEnvio(2);
			}
			
			String acceso=null;
			
			try {
				acceso=getBo().editar(getModelo());
			} catch (SQLException t) {
				JOptionPane.showMessageDialog(null, t.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
			
			if(acceso == "¡Ya Existe Un Usuario Con Éste Nombre!"){
				JOptionPane.showMessageDialog(null, acceso,"Acceso",JOptionPane.INFORMATION_MESSAGE);
				return;
			}else{
				JOptionPane.showMessageDialog(null, acceso,"Acceso",JOptionPane.INFORMATION_MESSAGE);
				getGoverment().getConsultaUsuariosController().mostrarVistaConsultarUsuarios();
				getVista().setVisible(false);
				return;
			}
		}
	}
}
