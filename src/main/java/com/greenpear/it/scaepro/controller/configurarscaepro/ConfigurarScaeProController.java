package com.greenpear.it.scaepro.controller.configurarscaepro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.greenpear.it.scaepro.bo.configurarscaepro.ConfigurarScaeProBo;
import com.greenpear.it.scaepro.controller.government.GovernmentService;
import com.greenpear.it.scaepro.model.gestionareas.ConsultaAreasModel;
import com.greenpear.it.scaepro.view.configurarscaepro.ConfigurarScaePro;

@Controller
public class ConfigurarScaeProController implements ActionListener,ItemListener{

	/**
	 * Constructor
	 */
	public ConfigurarScaeProController() {
		super();
	}

//**********************ESTANCIAS****************************
	@Autowired
	private GovernmentService government;
	
	@Autowired
	private ConfigurarScaePro configurarScaeProView;
	
	@Autowired
	@Qualifier("configurarScaeProBo")
	private ConfigurarScaeProBo configurarScaeProBo;

	public GovernmentService getGovernment() {
		return government;
	}

	public ConfigurarScaePro getConfigurarScaeProView() {
		return configurarScaeProView;
	}
	
	public ConfigurarScaeProBo getConfigurarScaeProBo() {
		return configurarScaeProBo;
	}
	
//**********************FIN DE ESTANCIAS***********************
	
	private List<ConsultaAreasModel> areas;

	

	public void mostrarVistaConfigurarScaePro(){
		if(getConfigurarScaeProView().getCmbArea().getItemListeners().length==1){
			getConfigurarScaeProView().getCmbArea().addItemListener(this);
		}
		
		getConfigurarScaeProView().setVisible(true);
		
		llenarComboAreas();
		
	}
	
	private void llenarComboAreas() {
		for (int i = 0; i < (getConfigurarScaeProView().getCmbArea().getItemCount()-1); i++) {
			getConfigurarScaeProView().getCmbArea().removeItemAt(1);
		}
		
		try {
			areas=getConfigurarScaeProBo().consultarAreas();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
		}
		if(!areas.isEmpty()){
			for (int i= 0; i < areas.size(); i++) {
				getConfigurarScaeProView().getCmbArea().addItem(areas.get(i).getArea());			
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			if(e.getSource() == getConfigurarScaeProView().getCmbArea()){
				if(getConfigurarScaeProView().getCmbArea().getSelectedItem().equals(
						"---------------Seleccione un \u00E1rea---------------")){
					getConfigurarScaeProView().limpiarVentana();
					return;
				}
				int idArea = areas.get(getConfigurarScaeProView().getCmbArea().getSelectedIndex()-1).getIdArea();
				
				
			}
		}
	}
}
