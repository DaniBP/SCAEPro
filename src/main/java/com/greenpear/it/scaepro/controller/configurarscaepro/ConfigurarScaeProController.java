package com.greenpear.it.scaepro.controller.configurarscaepro;

import java.awt.Component;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
public class ConfigurarScaeProController implements ActionListener,ItemListener,MouseListener{

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
			getConfigurarScaeProView().getTabbedPane().addMouseListener(this);
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
						paneles.get(i).setBackground(SystemColor.activeCaption);
						String nombreTurno = turnos.get((((i+1)*7)-1)).getNombreTurno();
						getConfigurarScaeProView().getTabbedPane().addTab(nombreTurno, paneles.get(i));
					}
					turnoSeleccionado = 0;
					llenarHorario();
				}
			}
		}
	}
	
	public void llenarHorario(){
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		Date now = null;
		int cantidad = ((turnoSeleccionado+1)*7);  
		int contador = 0;
		
		for (int i = (((turnoSeleccionado+1)*7)-7); i < cantidad; i++) {
			contador++;
			switch (contador) {
			case 1:
				try {
					if(turnos.get(i).getIdEstatus()==1){
						now = sdf.parse(turnos.get(i).getHoraEntrada());
						getConfigurarScaeProView().getLblHEntradaLunes().setVisible(true);
						getConfigurarScaeProView().getSpnHEntradaLunes().setVisible(true);
						getConfigurarScaeProView().getSpnHEntradaLunes().setEnabled(true);
						getConfigurarScaeProView().getSpnHEntradaLunes().setValue(now);
						now = sdf.parse(turnos.get(i).getHoraSalida());
						getConfigurarScaeProView().getLblHSalidaLunes().setVisible(true);
						getConfigurarScaeProView().getSpnHSalidaLunes().setVisible(true);
						getConfigurarScaeProView().getSpnHSalidaLunes().setEnabled(true);
						getConfigurarScaeProView().getSpnHSalidaLunes().setValue(now);
						getConfigurarScaeProView().getChckbxLunes().setSelected(true);
						getConfigurarScaeProView().getChckbxLunes().setEnabled(true);
						getConfigurarScaeProView().getChkComidaLunes().setVisible(true);
						if(turnos.get(i).getIdEstatusComida()==1){
							getConfigurarScaeProView().getChkComidaLunes().setSelected(true);
							getConfigurarScaeProView().getChkComidaLunes().setEnabled(true);
							now = sdf.parse(turnos.get(i).getHoraSalidaComer());
							getConfigurarScaeProView().getLblHoraSalidaComerLunes().setVisible(true);
							getConfigurarScaeProView().getSpnHSalidaComidaLunes().setVisible(true);
							getConfigurarScaeProView().getSpnHSalidaComidaLunes().setEnabled(true);
							getConfigurarScaeProView().getSpnHSalidaComidaLunes().setValue(now);
							now = sdf.parse(turnos.get(i).getHoraEntradaComer());
							getConfigurarScaeProView().getLblHEntradaComidaLunes().setVisible(true);
							getConfigurarScaeProView().getSpnHEntradaComidaLunes().setVisible(true);
							getConfigurarScaeProView().getSpnHEntradaComidaLunes().setEnabled(true);
							getConfigurarScaeProView().getSpnHEntradaComidaLunes().setValue(now);
						}
					}					
				} catch (ParseException e) {
					e.printStackTrace();
				}
			break;
			case 2:
				try {
					if(turnos.get(i).getIdEstatus()==1){
						now = sdf.parse(turnos.get(i).getHoraEntrada());
						getConfigurarScaeProView().getLblHEntradaMartes().setVisible(true);
						getConfigurarScaeProView().getSpnHEntradaMartes().setVisible(true);
						getConfigurarScaeProView().getSpnHEntradaMartes().setEnabled(true);
						getConfigurarScaeProView().getSpnHEntradaMartes().setValue(now);
						now = sdf.parse(turnos.get(i).getHoraSalida());
						getConfigurarScaeProView().getLblHSalidaMartes().setVisible(true);
						getConfigurarScaeProView().getSpnHSalidaMartes().setVisible(true);
						getConfigurarScaeProView().getSpnHSalidaMartes().setEnabled(true);
						getConfigurarScaeProView().getSpnHSalidaMartes().setValue(now);
						getConfigurarScaeProView().getChkMartes().setSelected(true);
						getConfigurarScaeProView().getChkMartes().setEnabled(true);
						getConfigurarScaeProView().getChkComidaMartes().setVisible(true);
						if(turnos.get(i).getIdEstatusComida()==1){
							getConfigurarScaeProView().getChkComidaMartes().setSelected(true);
							getConfigurarScaeProView().getChkComidaMartes().setEnabled(true);
							now = sdf.parse(turnos.get(i).getHoraSalidaComer());
							getConfigurarScaeProView().getLblHoraSalidaComerMartes().setVisible(true);
							getConfigurarScaeProView().getSpnHSalidaComidaMartes().setVisible(true);
							getConfigurarScaeProView().getSpnHSalidaComidaMartes().setEnabled(true);
							getConfigurarScaeProView().getSpnHSalidaComidaMartes().setValue(now);
							now = sdf.parse(turnos.get(i).getHoraEntradaComer());
							getConfigurarScaeProView().getLblHEntradaComidaMartes().setVisible(true);
							getConfigurarScaeProView().getSpnHEntradaComidaMartes().setVisible(true);
							getConfigurarScaeProView().getSpnHEntradaComidaMartes().setEnabled(true);
							getConfigurarScaeProView().getSpnHEntradaComidaMartes().setValue(now);
						}
					}										
				} catch (ParseException e) {
					e.printStackTrace();
				}
			break;
			case 3:
				try {
					if(turnos.get(i).getIdEstatus()==1){
						now = sdf.parse(turnos.get(i).getHoraEntrada());
						getConfigurarScaeProView().getLblHEntradaMiercoles().setVisible(true);
						getConfigurarScaeProView().getSpnHEntradaMiercoles().setVisible(true);
						getConfigurarScaeProView().getSpnHEntradaMiercoles().setEnabled(true);
						getConfigurarScaeProView().getSpnHEntradaMiercoles().setValue(now);
						now = sdf.parse(turnos.get(i).getHoraSalida());
						getConfigurarScaeProView().getLblHSalidaMiercoles().setVisible(true);
						getConfigurarScaeProView().getSpnHSalidaMiercoles().setVisible(true);
						getConfigurarScaeProView().getSpnHSalidaMiercoles().setEnabled(true);
						getConfigurarScaeProView().getSpnHSalidaMiercoles().setValue(now);
						getConfigurarScaeProView().getChkMiercoles().setSelected(true);
						getConfigurarScaeProView().getChkMiercoles().setEnabled(true);
						getConfigurarScaeProView().getChkComidaMiercoles().setVisible(true);
						if(turnos.get(i).getIdEstatusComida()==1){
							getConfigurarScaeProView().getChkComidaMiercoles().setSelected(true);
							getConfigurarScaeProView().getChkComidaMiercoles().setEnabled(true);
							now = sdf.parse(turnos.get(i).getHoraSalidaComer());
							getConfigurarScaeProView().getLblHoraSalidaComerMiercoles().setVisible(true);
							getConfigurarScaeProView().getSpnHSalidaComidaMiercoles().setVisible(true);
							getConfigurarScaeProView().getSpnHSalidaComidaMiercoles().setEnabled(true);
							getConfigurarScaeProView().getSpnHSalidaComidaMiercoles().setValue(now);
							now = sdf.parse(turnos.get(i).getHoraEntradaComer());
							getConfigurarScaeProView().getLblHEntradaComidaMiercoles().setVisible(true);
							getConfigurarScaeProView().getSpnHEntradaComidaMiercoles().setVisible(true);
							getConfigurarScaeProView().getSpnHEntradaComidaMiercoles().setEnabled(true);
							getConfigurarScaeProView().getSpnHEntradaComidaMiercoles().setValue(now);
						}
					}															
				} catch (ParseException e) {
					e.printStackTrace();
				}
			break;
			case 4:
				try {
					if(turnos.get(i).getIdEstatus()==1){
						now = sdf.parse(turnos.get(i).getHoraEntrada());
						getConfigurarScaeProView().getLblHEntradaJueves().setVisible(true);
						getConfigurarScaeProView().getSpnHEntradaJueves().setVisible(true);
						getConfigurarScaeProView().getSpnHEntradaJueves().setEnabled(true);
						getConfigurarScaeProView().getSpnHEntradaJueves().setValue(now);
						now = sdf.parse(turnos.get(i).getHoraSalida());
						getConfigurarScaeProView().getLblHSalidaJueves().setVisible(true);
						getConfigurarScaeProView().getSpnHSalidaJueves().setVisible(true);
						getConfigurarScaeProView().getSpnHSalidaJueves().setEnabled(true);
						getConfigurarScaeProView().getSpnHSalidaJueves().setValue(now);
						getConfigurarScaeProView().getChkJueves().setSelected(true);
						getConfigurarScaeProView().getChkJueves().setEnabled(true);
						getConfigurarScaeProView().getChkComidaJueves().setVisible(true);
						if(turnos.get(i).getIdEstatusComida()==1){
							getConfigurarScaeProView().getChkComidaJueves().setSelected(true);
							getConfigurarScaeProView().getChkComidaJueves().setEnabled(true);
							now = sdf.parse(turnos.get(i).getHoraSalidaComer());
							getConfigurarScaeProView().getLblHoraSalidaComerJueves().setVisible(true);
							getConfigurarScaeProView().getSpnHSalidaComidaJueves().setVisible(true);
							getConfigurarScaeProView().getSpnHSalidaComidaJueves().setEnabled(true);
							getConfigurarScaeProView().getSpnHSalidaComidaJueves().setValue(now);
							now = sdf.parse(turnos.get(i).getHoraEntradaComer());
							getConfigurarScaeProView().getLblHEntradaComidaJueves().setVisible(true);
							getConfigurarScaeProView().getSpnHEntradaComidaJueves().setVisible(true);
							getConfigurarScaeProView().getSpnHEntradaComidaJueves().setEnabled(true);
							getConfigurarScaeProView().getSpnHEntradaComidaJueves().setValue(now);
						}
					}															
				} catch (ParseException e) {
					e.printStackTrace();
				}
			break;
			case 5:
				try {
					if(turnos.get(i).getIdEstatus()==1){
						now = sdf.parse(turnos.get(i).getHoraEntrada());
						getConfigurarScaeProView().getLblHEntradaViernes().setVisible(true);
						getConfigurarScaeProView().getSpnHEntradaViernes().setVisible(true);
						getConfigurarScaeProView().getSpnHEntradaViernes().setEnabled(true);
						getConfigurarScaeProView().getSpnHEntradaViernes().setValue(now);
						now = sdf.parse(turnos.get(i).getHoraSalida());
						getConfigurarScaeProView().getLblHSalidaViernes().setVisible(true);
						getConfigurarScaeProView().getSpnHSalidaViernes().setVisible(true);
						getConfigurarScaeProView().getSpnHSalidaViernes().setEnabled(true);
						getConfigurarScaeProView().getSpnHSalidaViernes().setValue(now);
						getConfigurarScaeProView().getChkViernes().setSelected(true);
						getConfigurarScaeProView().getChkViernes().setEnabled(true);
						getConfigurarScaeProView().getChkComidaViernes().setVisible(true);
						if(turnos.get(i).getIdEstatusComida()==1){
							getConfigurarScaeProView().getChkComidaViernes().setSelected(true);
							getConfigurarScaeProView().getChkComidaViernes().setEnabled(true);
							now = sdf.parse(turnos.get(i).getHoraSalidaComer());
							getConfigurarScaeProView().getLblHoraSalidaComerViernes().setVisible(true);
							getConfigurarScaeProView().getSpnHSalidaComidaViernes().setVisible(true);
							getConfigurarScaeProView().getSpnHSalidaComidaViernes().setEnabled(true);
							getConfigurarScaeProView().getSpnHSalidaComidaViernes().setValue(now);
							now = sdf.parse(turnos.get(i).getHoraEntradaComer());
							getConfigurarScaeProView().getLblHEntradaComidaViernes().setVisible(true);
							getConfigurarScaeProView().getSpnHEntradaComidaViernes().setVisible(true);
							getConfigurarScaeProView().getSpnHEntradaComidaViernes().setEnabled(true);
							getConfigurarScaeProView().getSpnHEntradaComidaViernes().setValue(now);
						}
					}															
				} catch (ParseException e) {
					e.printStackTrace();
				}
			break;
			case 6:
				try {
					if(turnos.get(i).getIdEstatus()==1){
						now = sdf.parse(turnos.get(i).getHoraEntrada());
						getConfigurarScaeProView().getLblHEntradaSabado().setVisible(true);
						getConfigurarScaeProView().getSpnHEntradaSabado().setVisible(true);
						getConfigurarScaeProView().getSpnHEntradaSabado().setEnabled(true);
						getConfigurarScaeProView().getSpnHEntradaSabado().setValue(now);
						now = sdf.parse(turnos.get(i).getHoraSalida());
						getConfigurarScaeProView().getLblHSalidaSabado().setVisible(true);
						getConfigurarScaeProView().getSpnHSalidaSabado().setVisible(true);
						getConfigurarScaeProView().getSpnHSalidaSabado().setEnabled(true);
						getConfigurarScaeProView().getSpnHSalidaSabado().setValue(now);
						getConfigurarScaeProView().getChkSabado().setSelected(true);
						getConfigurarScaeProView().getChkSabado().setEnabled(true);
						getConfigurarScaeProView().getChkComidaSabado().setVisible(true);
						if(turnos.get(i).getIdEstatusComida()==1){
							getConfigurarScaeProView().getChkComidaSabado().setSelected(true);
							getConfigurarScaeProView().getChkComidaSabado().setEnabled(true);
							now = sdf.parse(turnos.get(i).getHoraSalidaComer());
							getConfigurarScaeProView().getLblHoraSalidaComerSabado().setVisible(true);
							getConfigurarScaeProView().getSpnHSalidaComidaSabado().setVisible(true);
							getConfigurarScaeProView().getSpnHSalidaComidaSabado().setEnabled(true);
							getConfigurarScaeProView().getSpnHSalidaComidaSabado().setValue(now);
							now = sdf.parse(turnos.get(i).getHoraEntradaComer());
							getConfigurarScaeProView().getLblHEntradaComidaSabado().setVisible(true);
							getConfigurarScaeProView().getSpnHEntradaComidaSabado().setVisible(true);
							getConfigurarScaeProView().getSpnHEntradaComidaSabado().setEnabled(true);
							getConfigurarScaeProView().getSpnHEntradaComidaSabado().setValue(now);
						}
					}										
				} catch (ParseException e) {
					e.printStackTrace();
				}
			break;
			case 7:
				try {
					if(turnos.get(i).getIdEstatus()==1){
						now = sdf.parse(turnos.get(i).getHoraEntrada());
						getConfigurarScaeProView().getLblHEntradaDomingo().setVisible(true);
						getConfigurarScaeProView().getSpnHEntradaDomingo().setVisible(true);
						getConfigurarScaeProView().getSpnHEntradaDomingo().setEnabled(true);
						getConfigurarScaeProView().getSpnHEntradaDomingo().setValue(now);
						now = sdf.parse(turnos.get(i).getHoraSalida());
						getConfigurarScaeProView().getLblHSalidaDomingo().setVisible(true);
						getConfigurarScaeProView().getSpnHSalidaDomingo().setVisible(true);
						getConfigurarScaeProView().getSpnHSalidaDomingo().setEnabled(true);
						getConfigurarScaeProView().getSpnHSalidaDomingo().setValue(now);
						getConfigurarScaeProView().getChkDomingo().setSelected(true);
						getConfigurarScaeProView().getChkDomingo().setEnabled(true);
						getConfigurarScaeProView().getChkComidaDomingo().setVisible(true);
						if(turnos.get(i).getIdEstatusComida()==1){
							getConfigurarScaeProView().getChkComidaDomingo().setSelected(true);
							getConfigurarScaeProView().getChkComidaDomingo().setEnabled(true);
							now = sdf.parse(turnos.get(i).getHoraSalidaComer());
							getConfigurarScaeProView().getLblHoraSalidaComerDomingo().setVisible(true);
							getConfigurarScaeProView().getSpnHSalidaComidaDomingo().setVisible(true);
							getConfigurarScaeProView().getSpnHSalidaComidaDomingo().setEnabled(true);
							getConfigurarScaeProView().getSpnHSalidaComidaDomingo().setValue(now);
							now = sdf.parse(turnos.get(i).getHoraEntradaComer());
							getConfigurarScaeProView().getLblHEntradaComidaDomingo().setVisible(true);
							getConfigurarScaeProView().getSpnHEntradaComidaDomingo().setVisible(true);
							getConfigurarScaeProView().getSpnHEntradaComidaDomingo().setEnabled(true);
							getConfigurarScaeProView().getSpnHEntradaComidaDomingo().setValue(now);
						}
					}										
				} catch (ParseException e) {
					e.printStackTrace();
				}
			break;
			}
			getConfigurarScaeProView().getSpnMinutosRetardo().setValue(turnos.get(i).getTiempoRetardo());
			getConfigurarScaeProView().getSpnMinutosRetardo().setEnabled(true);;
			getConfigurarScaeProView().getSpnMinutosFalta().setValue(turnos.get(i).getTiempoFalta());
			getConfigurarScaeProView().getSpnMinutosFalta().setEnabled(true);
			getConfigurarScaeProView().getTxtNombreTurno().setText(turnos.get(i).getNombreTurno());
			getConfigurarScaeProView().getTxtNombreTurno().setEnabled(true);
		}
		getConfigurarScaeProView().getChkHorarioGeneral().setEnabled(true);
		getConfigurarScaeProView().getBtnNuevoTurno().setEnabled(true);
		getConfigurarScaeProView().getBtnGuardarEditar().setEnabled(true);
		getConfigurarScaeProView().getBtnEliminar().setEnabled(true);
	}
	
	public void moverControles(int pestanaSeleccionada){
		getConfigurarScaeProView().getTabbedPane().setSelectedIndex(turnoSeleccionado);
		Component[] componentes = paneles.get(turnoSeleccionado).getComponents();
		for (int i = 0; i < componentes.length; i++) {
			paneles.get(pestanaSeleccionada).add(componentes[i]);
		}
		turnoSeleccionado=pestanaSeleccionada;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(getConfigurarScaeProView().getTabbedPane().getSelectedIndex()!=turnoSeleccionado){
			moverControles(getConfigurarScaeProView().getTabbedPane().getSelectedIndex());
			getConfigurarScaeProView().getTabbedPane().setSelectedIndex(turnoSeleccionado);
			getConfigurarScaeProView().reiniciarControles();
			llenarHorario();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}