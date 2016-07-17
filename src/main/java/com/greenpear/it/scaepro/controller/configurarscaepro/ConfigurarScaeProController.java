package com.greenpear.it.scaepro.controller.configurarscaepro;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.greenpear.it.scaepro.bo.configurarscaepro.ConfigurarScaeProBo;
import com.greenpear.it.scaepro.controller.government.GovernmentService;
import com.greenpear.it.scaepro.model.gestionareas.ConsultaAreasModel;
import com.greenpear.it.scaepro.model.turno.TurnoModel;
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
	private List<TurnoModel> turnos;
	private ArrayList<JPanel> paneles;
	private int turnoSeleccionado;

	

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
				
				for (int i = 1; i < getConfigurarScaeProView().getTabbedPane().getTabCount(); i++) {
					Component[] componentes = paneles.get(i).getComponents();
					for (int j = 0; j < componentes.length; j++) {
						paneles.get(0).add(componentes[j]);
					}
				}
				
				paneles = new ArrayList<JPanel>();
				
				getConfigurarScaeProView().limpiarVentana();
				
				if(getConfigurarScaeProView().getCmbArea().getSelectedItem().equals(
						"---------------Seleccione un \u00E1rea---------------")){
					return;
				}
				
				int idArea = areas.get(getConfigurarScaeProView().getCmbArea().getSelectedIndex()-1).getIdArea();
				
				try {
					turnos = getConfigurarScaeProBo().consultarTunos(idArea);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				int noTurnos = turnos.size()/7;
				
				if(turnos.size()==0){
					getConfigurarScaeProView().limpiarVentana();
				}else{
					getConfigurarScaeProView().getTabbedPane().setVisible(true);
					paneles.add(getConfigurarScaeProView().getTjpnlHorarios());
					getConfigurarScaeProView().getTabbedPane().setTitleAt(0, turnos.get(0).getNombreTurno());
					for (int i = 1; i < noTurnos; i++) {
						paneles.add(new JPanel());
						paneles.get(i).setLayout(null);
						String nombreTurno = turnos.get((((i+1)*7)-1)).getNombreTurno();
						getConfigurarScaeProView().getTabbedPane().addTab(nombreTurno, paneles.get(i));
					}
					turnoSeleccionado = 0;
					llenarHorario();
				}
				
//				Component[] componentes = getConfigurarScaeProView().getTjpnlHorarios().getComponents();
//				for (int i = 0; i < componentes.length; i++) {
//					paneles.get(1).add(componentes[i]);
//				}
			}
		}
	}
	
	public void llenarHorario(){
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		Date now = null;
		
		int cantidad = ((turnoSeleccionado+1)*7);  
		int contador = 0;
		
		JSpinner controlHEntrada;
		JSpinner controlHSalida;
		JSpinner controlHSalidaC;
		JSpinner controlHEntradaC;
		
		for (int i = (((turnoSeleccionado+1)*7)-7); i < cantidad; i++) {
			switch (contador) {
			case 1:
//				control=getConfigurarScaeProView().getSpnH
				break;

			default:
				break;
			}
		}
		try {
			now = sdf.parse(turnos.get(0).getHoraSalida());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		getConfigurarScaeProView().getSpnHEntradaLunes().setValue(now);
		
		System.out.println(now);
	}
}
