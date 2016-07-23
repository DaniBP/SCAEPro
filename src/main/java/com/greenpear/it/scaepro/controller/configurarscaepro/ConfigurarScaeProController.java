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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.object.RdbmsOperation;
import org.springframework.stereotype.Controller;

import com.greenpear.it.scaepro.bo.configurarscaepro.ConfigurarScaeProBo;
import com.greenpear.it.scaepro.controller.government.GovernmentService;
import com.greenpear.it.scaepro.model.gestionareas.ConsultaAreasModel;
import com.greenpear.it.scaepro.model.turno.TurnoModel;
import com.greenpear.it.scaepro.view.configurarscaepro.ConfigurarScaePro;

/**
 * 
 * @author DanielBP
 *
 */
@Controller
public class ConfigurarScaeProController implements ActionListener,ItemListener,MouseListener,ChangeListener{

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
	private int idTurno;

	

	public void mostrarVistaConfigurarScaePro(){
		if(getConfigurarScaeProView().getCmbArea().getItemListeners().length==1){
			getConfigurarScaeProView().getCmbArea().addItemListener(this);
			getConfigurarScaeProView().getTabbedPane().addMouseListener(this);
			getConfigurarScaeProView().getChckbxLunes().addItemListener(this);
			getConfigurarScaeProView().getChkMartes().addItemListener(this);
			getConfigurarScaeProView().getChkMiercoles().addItemListener(this);
			getConfigurarScaeProView().getChkJueves().addItemListener(this);
			getConfigurarScaeProView().getChkViernes().addItemListener(this);
			getConfigurarScaeProView().getChkSabado().addItemListener(this);
			getConfigurarScaeProView().getChkDomingo().addItemListener(this);
			getConfigurarScaeProView().getChkComidaLunes().addItemListener(this);
			getConfigurarScaeProView().getChkComidaMartes().addItemListener(this);
			getConfigurarScaeProView().getChkComidaMiercoles().addItemListener(this);
			getConfigurarScaeProView().getChkComidaJueves().addItemListener(this);
			getConfigurarScaeProView().getChkComidaViernes().addItemListener(this);
			getConfigurarScaeProView().getChkComidaSabado().addItemListener(this);
			getConfigurarScaeProView().getChkComidaDomingo().addItemListener(this);
			getConfigurarScaeProView().getChkHorarioGeneral().addItemListener(this);
			getConfigurarScaeProView().getSpnHoraEntradaG().addChangeListener(this);
			getConfigurarScaeProView().getSpnHoraSalidaG().addChangeListener(this);
			getConfigurarScaeProView().getSpnHoraSalidaComidaG().addChangeListener(this);
			getConfigurarScaeProView().getSpnHoraEntradaComidaG().addChangeListener(this);
			
			getConfigurarScaeProView().getBtnNuevoTurno().addActionListener(this);
			getConfigurarScaeProView().getBtnEliminar().addActionListener(this);
			getConfigurarScaeProView().getBtnGuardarEditar().addActionListener(this);
			
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
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == getConfigurarScaeProView().getBtnNuevoTurno()){
			crearNuevoTurno();
		}else if(e.getSource() == getConfigurarScaeProView().getBtnGuardarEditar()){
			if(getConfigurarScaeProView().getTxtNombreTurno().getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Ingrese el nombre del turno", "Alerta", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			if(JOptionPane.showConfirmDialog(
					null, "Desea guardar los cambios realizados", "Confirmación",JOptionPane.YES_NO_OPTION)
					!=JOptionPane.YES_OPTION){
				return;
			}

			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			
			int idArea=0;
			
			for (int i = 0; i < areas.size(); i++) {
				if(areas.get(i).getArea().equals(getConfigurarScaeProView().getCmbArea().getSelectedItem())){
					idArea=areas.get(i).getIdArea();
				}
			}

			for (int i = 0; i < 7; i++) {
				turnos.add(new TurnoModel());
				turnos.get(i).setIdArea(idArea);
				turnos.get(i).setIdTurno(idTurno);
				turnos.get(i).setNombreTurno(getConfigurarScaeProView().getTxtNombreTurno().getText());
				turnos.get(i).setTiempoRetardo((Integer) getConfigurarScaeProView().getSpnMinutosRetardo().getValue());
				turnos.get(i).setTiempoFalta((Integer) getConfigurarScaeProView().getSpnMinutosFalta().getValue());
				switch (i) {
				case 0:
					turnos.get(i).setDia("Lunes");
					if(getConfigurarScaeProView().getChckbxLunes().isSelected()){						
						turnos.get(i).setIdEstatus(1);
					}else{
						turnos.get(i).setIdEstatus(2);
					}
					
					if(getConfigurarScaeProView().getChkComidaLunes().isSelected()){
						turnos.get(i).setIdEstatusComida(1);
					}else{
						turnos.get(i).setIdEstatusComida(2);
					}
					
					turnos.get(i).setHoraEntrada(sdf.format(getConfigurarScaeProView().getSpnHEntradaLunes().getValue()));
					turnos.get(i).setHoraSalida(sdf.format(getConfigurarScaeProView().getSpnHSalidaLunes().getValue()));
					turnos.get(i).setHoraSalidaComer(sdf.format(getConfigurarScaeProView().getSpnHSalidaComidaLunes().getValue()));
					turnos.get(i).setHoraEntradaComer(sdf.format(getConfigurarScaeProView().getSpnHEntradaComidaLunes().getValue()));
				break;
				case 1:
					turnos.get(i).setDia("Martes");
					if(getConfigurarScaeProView().getChkMartes().isSelected()){						
						turnos.get(i).setIdEstatus(1);
					}else{
						turnos.get(i).setIdEstatus(2);
					}
					
					if(getConfigurarScaeProView().getChkComidaMartes().isSelected()){
						turnos.get(i).setIdEstatusComida(1);
					}else{
						turnos.get(i).setIdEstatusComida(2);
					}
					
					turnos.get(i).setHoraEntrada(sdf.format(getConfigurarScaeProView().getSpnHEntradaMartes().getValue()));
					turnos.get(i).setHoraSalida(sdf.format(getConfigurarScaeProView().getSpnHSalidaMartes().getValue()));
					turnos.get(i).setHoraSalidaComer(sdf.format(getConfigurarScaeProView().getSpnHSalidaComidaMartes().getValue()));
					turnos.get(i).setHoraEntradaComer(sdf.format(getConfigurarScaeProView().getSpnHEntradaComidaMartes().getValue()));
				break;
				case 2:
					turnos.get(i).setDia("Miercoles");
					if(getConfigurarScaeProView().getChkMiercoles().isSelected()){						
						turnos.get(i).setIdEstatus(1);
					}else{
						turnos.get(i).setIdEstatus(2);
					}
					
					if(getConfigurarScaeProView().getChkComidaMiercoles().isSelected()){
						turnos.get(i).setIdEstatusComida(1);
					}else{
						turnos.get(i).setIdEstatusComida(2);
					}
					
					turnos.get(i).setHoraEntrada(sdf.format(getConfigurarScaeProView().getSpnHEntradaMiercoles().getValue()));
					turnos.get(i).setHoraSalida(sdf.format(getConfigurarScaeProView().getSpnHSalidaMiercoles().getValue()));
					turnos.get(i).setHoraSalidaComer(sdf.format(getConfigurarScaeProView().getSpnHSalidaComidaMiercoles().getValue()));
					turnos.get(i).setHoraEntradaComer(sdf.format(getConfigurarScaeProView().getSpnHEntradaComidaMiercoles().getValue()));
				break;
				case 3:
					turnos.get(i).setDia("Jueves");
					if(getConfigurarScaeProView().getChkJueves().isSelected()){						
						turnos.get(i).setIdEstatus(1);
					}else{
						turnos.get(i).setIdEstatus(2);
					}
					
					if(getConfigurarScaeProView().getChkComidaJueves().isSelected()){
						turnos.get(i).setIdEstatusComida(1);
					}else{
						turnos.get(i).setIdEstatusComida(2);
					}
					
					turnos.get(i).setHoraEntrada(sdf.format(getConfigurarScaeProView().getSpnHEntradaJueves().getValue()));
					turnos.get(i).setHoraSalida(sdf.format(getConfigurarScaeProView().getSpnHSalidaJueves().getValue()));
					turnos.get(i).setHoraSalidaComer(sdf.format(getConfigurarScaeProView().getSpnHSalidaComidaJueves().getValue()));
					turnos.get(i).setHoraEntradaComer(sdf.format(getConfigurarScaeProView().getSpnHEntradaComidaJueves().getValue()));
				break;
				case 4:
					turnos.get(i).setDia("Viernes");
					if(getConfigurarScaeProView().getChkViernes().isSelected()){						
						turnos.get(i).setIdEstatus(1);
					}else{
						turnos.get(i).setIdEstatus(2);
					}
					
					if(getConfigurarScaeProView().getChkComidaViernes().isSelected()){
						turnos.get(i).setIdEstatusComida(1);
					}else{
						turnos.get(i).setIdEstatusComida(2);
					}
					
					turnos.get(i).setHoraEntrada(sdf.format(getConfigurarScaeProView().getSpnHEntradaViernes().getValue()));
					turnos.get(i).setHoraSalida(sdf.format(getConfigurarScaeProView().getSpnHSalidaViernes().getValue()));
					turnos.get(i).setHoraSalidaComer(sdf.format(getConfigurarScaeProView().getSpnHSalidaComidaViernes().getValue()));
					turnos.get(i).setHoraEntradaComer(sdf.format(getConfigurarScaeProView().getSpnHEntradaComidaViernes().getValue()));
				break;
				case 5:
					turnos.get(i).setDia("Sabado");
					if(getConfigurarScaeProView().getChkSabado().isSelected()){						
						turnos.get(i).setIdEstatus(1);
					}else{
						turnos.get(i).setIdEstatus(2);
					}
					
					if(getConfigurarScaeProView().getChkComidaSabado().isSelected()){
						turnos.get(i).setIdEstatusComida(1);
					}else{
						turnos.get(i).setIdEstatusComida(2);
					}
					
					turnos.get(i).setHoraEntrada(sdf.format(getConfigurarScaeProView().getSpnHEntradaSabado().getValue()));
					turnos.get(i).setHoraSalida(sdf.format(getConfigurarScaeProView().getSpnHSalidaSabado().getValue()));
					turnos.get(i).setHoraSalidaComer(sdf.format(getConfigurarScaeProView().getSpnHSalidaComidaSabado().getValue()));
					turnos.get(i).setHoraEntradaComer(sdf.format(getConfigurarScaeProView().getSpnHEntradaComidaSabado().getValue()));
				break;
				case 6:
					turnos.get(i).setDia("Domingo");
					if(getConfigurarScaeProView().getChkDomingo().isSelected()){						
						turnos.get(i).setIdEstatus(1);
					}else{
						turnos.get(i).setIdEstatus(2);
					}
					
					if(getConfigurarScaeProView().getChkComidaDomingo().isSelected()){
						turnos.get(i).setIdEstatusComida(1);
					}else{
						turnos.get(i).setIdEstatusComida(2);
					}
					
					turnos.get(i).setHoraEntrada(sdf.format(getConfigurarScaeProView().getSpnHEntradaDomingo().getValue()));
					turnos.get(i).setHoraSalida(sdf.format(getConfigurarScaeProView().getSpnHSalidaDomingo().getValue()));
					turnos.get(i).setHoraSalidaComer(sdf.format(getConfigurarScaeProView().getSpnHSalidaComidaDomingo().getValue()));
					turnos.get(i).setHoraEntradaComer(sdf.format(getConfigurarScaeProView().getSpnHEntradaComidaDomingo().getValue()));
				break;
				}
			}
			
			String resultado=null;
			
			if(getConfigurarScaeProView().getTabbedPane().getTitleAt(turnoSeleccionado).equals("Nuevo Turno")){
				try {
					resultado = getConfigurarScaeProBo().registrarTurno(turnos);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}else{
				try{
					resultado = getConfigurarScaeProBo().editarTurno(turnos);
				}catch(SQLException e1){
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			if(resultado.equals("correcto")){
				JOptionPane.showMessageDialog(null, "Turno guardado correctamente");
				getConfigurarScaeProView().getCmbArea().setSelectedIndex(getConfigurarScaeProView().getCmbArea().getSelectedIndex()-1);
				getConfigurarScaeProView().getCmbArea().setSelectedIndex(getConfigurarScaeProView().getCmbArea().getSelectedIndex()+1);
			}else{
				JOptionPane.showMessageDialog(null, resultado, "Atención", JOptionPane.WARNING_MESSAGE);
			}
		}else if(e.getSource()==getConfigurarScaeProView().getBtnEliminar()){
			if(JOptionPane.showConfirmDialog(
					null, "¿Seguro que desea eliminar este turno?", "Confirmación",JOptionPane.YES_NO_OPTION)
					!=JOptionPane.YES_OPTION){
				return;
			}
			
			String resultado=null;
			
			try {
				resultado = getConfigurarScaeProBo().eliminarTurno(idTurno);
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			if(resultado.equals("correcto")){
				JOptionPane.showMessageDialog(null, "Turno eliminado correctamente");
				getConfigurarScaeProView().getCmbArea().setSelectedIndex(getConfigurarScaeProView().getCmbArea().getSelectedIndex()-1);
				getConfigurarScaeProView().getCmbArea().setSelectedIndex(getConfigurarScaeProView().getCmbArea().getSelectedIndex()+1);
			}else{
				JOptionPane.showMessageDialog(null, resultado, "Atención", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	public void crearNuevoTurno(){
		getConfigurarScaeProView().getTabbedPane().setVisible(true);
		if(paneles.size()==0){
			paneles.add(getConfigurarScaeProView().getTjpnlHorarios());
			getConfigurarScaeProView().getTabbedPane().setTitleAt(0, "Nuevo Turno");
			turnoSeleccionado = 0;
		}else{
			paneles.add(new JPanel());
			paneles.get(paneles.size()-1).setLayout(null);
			paneles.get(paneles.size()-1).setBackground(SystemColor.activeCaption);
			getConfigurarScaeProView().getTabbedPane().addTab("Nuevo Turno", paneles.get(paneles.size()-1));
			getConfigurarScaeProView().getTabbedPane().setSelectedIndex(paneles.size()-1);
			moverControles(getConfigurarScaeProView().getTabbedPane().getSelectedIndex());
		}
		getConfigurarScaeProView().reiniciarControles();
		getConfigurarScaeProView().getBtnNuevoTurno().setEnabled(false);
		getConfigurarScaeProView().getBtnGuardarEditar().setEnabled(true);
		getConfigurarScaeProView().getBtnEliminar().setEnabled(false);
		getConfigurarScaeProView().getTabbedPane().setSelectedIndex(paneles.size()-1);
		getConfigurarScaeProView().getChkHorarioGeneral().setEnabled(true);
		getConfigurarScaeProView().getTxtNombreTurno().setEnabled(true);
		getConfigurarScaeProView().getSpnMinutosRetardo().setEnabled(true);
		getConfigurarScaeProView().getSpnMinutosFalta().setEnabled(true);
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
					getConfigurarScaeProView().getBtnNuevoTurno().setEnabled(true);
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
		
		if(e.getSource()==getConfigurarScaeProView().getChckbxLunes()){
			if (e.getStateChange() == ItemEvent.SELECTED) {
				getConfigurarScaeProView().getSpnHEntradaLunes().setEnabled(true);
				getConfigurarScaeProView().getSpnHSalidaLunes().setEnabled(true);
				getConfigurarScaeProView().getChkComidaLunes().setEnabled(true);				
			}else{
				getConfigurarScaeProView().getSpnHSalidaComidaLunes().setEnabled(false);
				getConfigurarScaeProView().getSpnHEntradaComidaLunes().setEnabled(false);
				getConfigurarScaeProView().getSpnHEntradaLunes().setEnabled(false);
				getConfigurarScaeProView().getSpnHSalidaLunes().setEnabled(false);
				getConfigurarScaeProView().getChkComidaLunes().setSelected(false);
				getConfigurarScaeProView().getChkComidaLunes().setEnabled(false);
				
				getConfigurarScaeProView().getSpnHSalidaComidaLunes().setValue(getConfigurarScaeProView().now);
				getConfigurarScaeProView().getSpnHEntradaComidaLunes().setValue(getConfigurarScaeProView().now);
				getConfigurarScaeProView().getSpnHEntradaLunes().setValue(getConfigurarScaeProView().now);
				getConfigurarScaeProView().getSpnHSalidaLunes().setValue(getConfigurarScaeProView().now);
			}
		}else if(e.getSource()==getConfigurarScaeProView().getChkMartes()){
			if (e.getStateChange() == ItemEvent.SELECTED) {
				getConfigurarScaeProView().getSpnHEntradaMartes().setEnabled(true);
				getConfigurarScaeProView().getSpnHSalidaMartes().setEnabled(true);
				getConfigurarScaeProView().getChkComidaMartes().setEnabled(true);				
			}else{
				getConfigurarScaeProView().getSpnHSalidaComidaMartes().setEnabled(false);
				getConfigurarScaeProView().getSpnHEntradaComidaMartes().setEnabled(false);
				getConfigurarScaeProView().getSpnHEntradaMartes().setEnabled(false);
				getConfigurarScaeProView().getSpnHSalidaMartes().setEnabled(false);
				getConfigurarScaeProView().getChkComidaMartes().setSelected(false);	
				getConfigurarScaeProView().getChkComidaMartes().setEnabled(false);	
				
				getConfigurarScaeProView().getSpnHSalidaComidaMartes().setValue(getConfigurarScaeProView().now);
				getConfigurarScaeProView().getSpnHEntradaComidaMartes().setValue(getConfigurarScaeProView().now);
				getConfigurarScaeProView().getSpnHEntradaMartes().setValue(getConfigurarScaeProView().now);
				getConfigurarScaeProView().getSpnHSalidaMartes().setValue(getConfigurarScaeProView().now);
			}
		}else if(e.getSource()==getConfigurarScaeProView().getChkMiercoles()){
			if (e.getStateChange() == ItemEvent.SELECTED) {
				getConfigurarScaeProView().getSpnHEntradaMiercoles().setEnabled(true);
				getConfigurarScaeProView().getSpnHSalidaMiercoles().setEnabled(true);
				getConfigurarScaeProView().getChkComidaMiercoles().setEnabled(true);				
			}else{
				getConfigurarScaeProView().getSpnHSalidaComidaMiercoles().setEnabled(false);
				getConfigurarScaeProView().getSpnHEntradaComidaMiercoles().setEnabled(false);
				getConfigurarScaeProView().getSpnHEntradaMiercoles().setEnabled(false);
				getConfigurarScaeProView().getSpnHSalidaMiercoles().setEnabled(false);
				getConfigurarScaeProView().getChkComidaMiercoles().setSelected(false);	
				getConfigurarScaeProView().getChkComidaMiercoles().setEnabled(false);	
				
				getConfigurarScaeProView().getSpnHSalidaComidaMiercoles().setValue(getConfigurarScaeProView().now);
				getConfigurarScaeProView().getSpnHEntradaComidaMiercoles().setValue(getConfigurarScaeProView().now);
				getConfigurarScaeProView().getSpnHEntradaMiercoles().setValue(getConfigurarScaeProView().now);
				getConfigurarScaeProView().getSpnHSalidaMiercoles().setValue(getConfigurarScaeProView().now);
			}
		}else if(e.getSource()==getConfigurarScaeProView().getChkJueves()){
			if (e.getStateChange() == ItemEvent.SELECTED) {
				getConfigurarScaeProView().getSpnHEntradaJueves().setEnabled(true);
				getConfigurarScaeProView().getSpnHSalidaJueves().setEnabled(true);
				getConfigurarScaeProView().getChkComidaJueves().setEnabled(true);				
			}else{
				getConfigurarScaeProView().getSpnHSalidaComidaJueves().setEnabled(false);
				getConfigurarScaeProView().getSpnHEntradaComidaJueves().setEnabled(false);
				getConfigurarScaeProView().getSpnHEntradaJueves().setEnabled(false);
				getConfigurarScaeProView().getSpnHSalidaJueves().setEnabled(false);
				getConfigurarScaeProView().getChkComidaJueves().setSelected(false);	
				getConfigurarScaeProView().getChkComidaJueves().setEnabled(false);	
				
				getConfigurarScaeProView().getSpnHSalidaComidaJueves().setValue(getConfigurarScaeProView().now);
				getConfigurarScaeProView().getSpnHEntradaComidaJueves().setValue(getConfigurarScaeProView().now);
				getConfigurarScaeProView().getSpnHEntradaJueves().setValue(getConfigurarScaeProView().now);
				getConfigurarScaeProView().getSpnHSalidaJueves().setValue(getConfigurarScaeProView().now);
			}
		}else if(e.getSource()==getConfigurarScaeProView().getChkViernes()){
			if (e.getStateChange() == ItemEvent.SELECTED) {
				getConfigurarScaeProView().getSpnHEntradaViernes().setEnabled(true);
				getConfigurarScaeProView().getSpnHSalidaViernes().setEnabled(true);
				getConfigurarScaeProView().getChkComidaViernes().setEnabled(true);				
			}else{
				getConfigurarScaeProView().getSpnHSalidaComidaViernes().setEnabled(false);
				getConfigurarScaeProView().getSpnHEntradaComidaViernes().setEnabled(false);
				getConfigurarScaeProView().getSpnHEntradaViernes().setEnabled(false);
				getConfigurarScaeProView().getSpnHSalidaViernes().setEnabled(false);
				getConfigurarScaeProView().getChkComidaViernes().setSelected(false);	
				getConfigurarScaeProView().getChkComidaViernes().setEnabled(false);
				
				getConfigurarScaeProView().getSpnHSalidaComidaViernes().setValue(getConfigurarScaeProView().now);
				getConfigurarScaeProView().getSpnHEntradaComidaViernes().setValue(getConfigurarScaeProView().now);
				getConfigurarScaeProView().getSpnHEntradaViernes().setValue(getConfigurarScaeProView().now);
				getConfigurarScaeProView().getSpnHSalidaViernes().setValue(getConfigurarScaeProView().now);
			}
		}else if(e.getSource()==getConfigurarScaeProView().getChkSabado()){
			if (e.getStateChange() == ItemEvent.SELECTED) {
				getConfigurarScaeProView().getSpnHEntradaSabado().setEnabled(true);
				getConfigurarScaeProView().getSpnHSalidaSabado().setEnabled(true);
				getConfigurarScaeProView().getChkComidaSabado().setEnabled(true);				
			}else{
				getConfigurarScaeProView().getSpnHSalidaComidaSabado().setEnabled(false);
				getConfigurarScaeProView().getSpnHEntradaComidaSabado().setEnabled(false);
				getConfigurarScaeProView().getSpnHEntradaSabado().setEnabled(false);
				getConfigurarScaeProView().getSpnHSalidaSabado().setEnabled(false);
				getConfigurarScaeProView().getChkComidaSabado().setSelected(false);	
				getConfigurarScaeProView().getChkComidaSabado().setEnabled(false);	
				
				getConfigurarScaeProView().getSpnHSalidaComidaSabado().setValue(getConfigurarScaeProView().now);
				getConfigurarScaeProView().getSpnHEntradaComidaSabado().setValue(getConfigurarScaeProView().now);
				getConfigurarScaeProView().getSpnHEntradaSabado().setValue(getConfigurarScaeProView().now);
				getConfigurarScaeProView().getSpnHSalidaSabado().setValue(getConfigurarScaeProView().now);
			}
		}else if(e.getSource()==getConfigurarScaeProView().getChkDomingo()){
			if (e.getStateChange() == ItemEvent.SELECTED) {
				getConfigurarScaeProView().getSpnHEntradaDomingo().setEnabled(true);
				getConfigurarScaeProView().getSpnHSalidaDomingo().setEnabled(true);
				getConfigurarScaeProView().getChkComidaDomingo().setEnabled(true);				
			}else{
				getConfigurarScaeProView().getSpnHSalidaComidaDomingo().setEnabled(false);
				getConfigurarScaeProView().getSpnHEntradaComidaDomingo().setEnabled(false);
				getConfigurarScaeProView().getSpnHEntradaDomingo().setEnabled(false);
				getConfigurarScaeProView().getSpnHSalidaDomingo().setEnabled(false);
				getConfigurarScaeProView().getChkComidaDomingo().setSelected(false);	
				getConfigurarScaeProView().getChkComidaDomingo().setEnabled(false);	
				
				getConfigurarScaeProView().getSpnHSalidaComidaDomingo().setValue(getConfigurarScaeProView().now);
				getConfigurarScaeProView().getSpnHEntradaComidaDomingo().setValue(getConfigurarScaeProView().now);
				getConfigurarScaeProView().getSpnHEntradaDomingo().setValue(getConfigurarScaeProView().now);
				getConfigurarScaeProView().getSpnHSalidaDomingo().setValue(getConfigurarScaeProView().now);
			}
		}else if(e.getSource()==getConfigurarScaeProView().getChkComidaLunes()){
			if (e.getStateChange() == ItemEvent.SELECTED) {
				getConfigurarScaeProView().getSpnHEntradaComidaLunes().setEnabled(true);
				getConfigurarScaeProView().getSpnHSalidaComidaLunes().setEnabled(true);
			}else{
				getConfigurarScaeProView().getSpnHEntradaComidaLunes().setEnabled(false);
				getConfigurarScaeProView().getSpnHSalidaComidaLunes().setEnabled(false);
				
				getConfigurarScaeProView().getSpnHEntradaComidaLunes().setValue(getConfigurarScaeProView().now);
				getConfigurarScaeProView().getSpnHSalidaComidaLunes().setValue(getConfigurarScaeProView().now);
			}
		}else if(e.getSource()==getConfigurarScaeProView().getChkComidaMartes()){
			if (e.getStateChange() == ItemEvent.SELECTED) {
				getConfigurarScaeProView().getSpnHEntradaComidaMartes().setEnabled(true);
				getConfigurarScaeProView().getSpnHSalidaComidaMartes().setEnabled(true);
			}else{
				getConfigurarScaeProView().getSpnHEntradaComidaMartes().setEnabled(false);
				getConfigurarScaeProView().getSpnHSalidaComidaMartes().setEnabled(false);
				
				getConfigurarScaeProView().getSpnHEntradaComidaMartes().setValue(getConfigurarScaeProView().now);
				getConfigurarScaeProView().getSpnHSalidaComidaMartes().setValue(getConfigurarScaeProView().now);
			}
		}else if(e.getSource()==getConfigurarScaeProView().getChkComidaMiercoles()){
			if (e.getStateChange() == ItemEvent.SELECTED) {
				getConfigurarScaeProView().getSpnHEntradaComidaMiercoles().setEnabled(true);
				getConfigurarScaeProView().getSpnHSalidaComidaMiercoles().setEnabled(true);
			}else{
				getConfigurarScaeProView().getSpnHEntradaComidaMiercoles().setEnabled(false);
				getConfigurarScaeProView().getSpnHSalidaComidaMiercoles().setEnabled(false);
				
				getConfigurarScaeProView().getSpnHEntradaComidaMiercoles().setValue(getConfigurarScaeProView().now);
				getConfigurarScaeProView().getSpnHSalidaComidaMiercoles().setValue(getConfigurarScaeProView().now);
			}
		}else if(e.getSource()==getConfigurarScaeProView().getChkComidaJueves()){
			if (e.getStateChange() == ItemEvent.SELECTED) {
				getConfigurarScaeProView().getSpnHEntradaComidaJueves().setEnabled(true);
				getConfigurarScaeProView().getSpnHSalidaComidaJueves().setEnabled(true);
			}else{
				getConfigurarScaeProView().getSpnHEntradaComidaJueves().setEnabled(false);
				getConfigurarScaeProView().getSpnHSalidaComidaJueves().setEnabled(false);
				
				getConfigurarScaeProView().getSpnHEntradaComidaJueves().setValue(getConfigurarScaeProView().now);
				getConfigurarScaeProView().getSpnHSalidaComidaJueves().setValue(getConfigurarScaeProView().now);
			}
		}else if(e.getSource()==getConfigurarScaeProView().getChkComidaViernes()){
			if (e.getStateChange() == ItemEvent.SELECTED) {
				getConfigurarScaeProView().getSpnHEntradaComidaViernes().setEnabled(true);
				getConfigurarScaeProView().getSpnHSalidaComidaViernes().setEnabled(true);
			}else{
				getConfigurarScaeProView().getSpnHEntradaComidaViernes().setEnabled(false);
				getConfigurarScaeProView().getSpnHSalidaComidaViernes().setEnabled(false);
				
				getConfigurarScaeProView().getSpnHEntradaComidaViernes().setValue(getConfigurarScaeProView().now);
				getConfigurarScaeProView().getSpnHSalidaComidaViernes().setValue(getConfigurarScaeProView().now);
			}
		}else if(e.getSource()==getConfigurarScaeProView().getChkComidaSabado()){
			if (e.getStateChange() == ItemEvent.SELECTED) {
				getConfigurarScaeProView().getSpnHEntradaComidaSabado().setEnabled(true);
				getConfigurarScaeProView().getSpnHSalidaComidaSabado().setEnabled(true);
			}else{
				getConfigurarScaeProView().getSpnHEntradaComidaSabado().setEnabled(false);
				getConfigurarScaeProView().getSpnHSalidaComidaSabado().setEnabled(false);
				
				getConfigurarScaeProView().getSpnHEntradaComidaSabado().setValue(getConfigurarScaeProView().now);
				getConfigurarScaeProView().getSpnHSalidaComidaSabado().setValue(getConfigurarScaeProView().now);
			}
		}else if(e.getSource()==getConfigurarScaeProView().getChkComidaDomingo()){
			if (e.getStateChange() == ItemEvent.SELECTED) {
				getConfigurarScaeProView().getSpnHEntradaComidaDomingo().setEnabled(true);
				getConfigurarScaeProView().getSpnHSalidaComidaDomingo().setEnabled(true);
			}else{
				getConfigurarScaeProView().getSpnHEntradaComidaDomingo().setEnabled(false);
				getConfigurarScaeProView().getSpnHSalidaComidaDomingo().setEnabled(false);
				
				getConfigurarScaeProView().getSpnHEntradaComidaDomingo().setValue(getConfigurarScaeProView().now);
				getConfigurarScaeProView().getSpnHSalidaComidaDomingo().setValue(getConfigurarScaeProView().now);
			}
		}else if(e.getSource()==getConfigurarScaeProView().getChkHorarioGeneral()){
			if (e.getStateChange() == ItemEvent.SELECTED) {
				getConfigurarScaeProView().getChckbxLunes().setSelected(true);
				getConfigurarScaeProView().getChkMartes().setSelected(true);
				getConfigurarScaeProView().getChkMiercoles().setSelected(true);
				getConfigurarScaeProView().getChkJueves().setSelected(true);
				getConfigurarScaeProView().getChkViernes().setSelected(true);
				getConfigurarScaeProView().getChkSabado().setSelected(true);
				getConfigurarScaeProView().getChkDomingo().setSelected(true);
				
				getConfigurarScaeProView().getChkComidaLunes().setSelected(true);
				getConfigurarScaeProView().getChkComidaMartes().setSelected(true);
				getConfigurarScaeProView().getChkComidaMiercoles().setSelected(true);
				getConfigurarScaeProView().getChkComidaJueves().setSelected(true);
				getConfigurarScaeProView().getChkComidaViernes().setSelected(true);
				getConfigurarScaeProView().getChkComidaSabado().setSelected(true);
				getConfigurarScaeProView().getChkComidaDomingo().setSelected(true);
				
				getConfigurarScaeProView().getChkComidaLunes().setEnabled(true);
				getConfigurarScaeProView().getChkComidaMartes().setEnabled(true);
				getConfigurarScaeProView().getChkComidaMiercoles().setEnabled(true);
				getConfigurarScaeProView().getChkComidaJueves().setEnabled(true);
				getConfigurarScaeProView().getChkComidaViernes().setEnabled(true);
				getConfigurarScaeProView().getChkComidaSabado().setEnabled(true);
				getConfigurarScaeProView().getChkComidaDomingo().setEnabled(true);
				
				getConfigurarScaeProView().getSpnHEntradaLunes().setEnabled(true);
				getConfigurarScaeProView().getSpnHEntradaMartes().setEnabled(true);
				getConfigurarScaeProView().getSpnHEntradaMiercoles().setEnabled(true);
				getConfigurarScaeProView().getSpnHEntradaJueves().setEnabled(true);
				getConfigurarScaeProView().getSpnHEntradaViernes().setEnabled(true);
				getConfigurarScaeProView().getSpnHEntradaSabado().setEnabled(true);
				getConfigurarScaeProView().getSpnHEntradaDomingo().setEnabled(true);
				
				getConfigurarScaeProView().getSpnHSalidaLunes().setEnabled(true);
				getConfigurarScaeProView().getSpnHSalidaMartes().setEnabled(true);
				getConfigurarScaeProView().getSpnHSalidaMiercoles().setEnabled(true);
				getConfigurarScaeProView().getSpnHSalidaJueves().setEnabled(true);
				getConfigurarScaeProView().getSpnHSalidaViernes().setEnabled(true);
				getConfigurarScaeProView().getSpnHSalidaSabado().setEnabled(true);
				getConfigurarScaeProView().getSpnHSalidaDomingo().setEnabled(true);
				
				getConfigurarScaeProView().getSpnHSalidaComidaLunes().setEnabled(true);
				getConfigurarScaeProView().getSpnHSalidaComidaMartes().setEnabled(true);
				getConfigurarScaeProView().getSpnHSalidaComidaMiercoles().setEnabled(true);
				getConfigurarScaeProView().getSpnHSalidaComidaJueves().setEnabled(true);
				getConfigurarScaeProView().getSpnHSalidaComidaViernes().setEnabled(true);
				getConfigurarScaeProView().getSpnHSalidaComidaSabado().setEnabled(true);
				getConfigurarScaeProView().getSpnHSalidaComidaDomingo().setEnabled(true);
				
				getConfigurarScaeProView().getSpnHEntradaComidaLunes().setEnabled(true);
				getConfigurarScaeProView().getSpnHEntradaComidaMartes().setEnabled(true);
				getConfigurarScaeProView().getSpnHEntradaComidaMiercoles().setEnabled(true);
				getConfigurarScaeProView().getSpnHEntradaComidaJueves().setEnabled(true);
				getConfigurarScaeProView().getSpnHEntradaComidaViernes().setEnabled(true);
				getConfigurarScaeProView().getSpnHEntradaComidaSabado().setEnabled(true);
				getConfigurarScaeProView().getSpnHEntradaComidaDomingo().setEnabled(true);
				
				getConfigurarScaeProView().getSpnHoraEntradaG().setEnabled(true);
				getConfigurarScaeProView().getSpnHoraSalidaG().setEnabled(true);
				getConfigurarScaeProView().getSpnHoraSalidaComidaG().setEnabled(true);
				getConfigurarScaeProView().getSpnHoraEntradaComidaG().setEnabled(true);
				
				aplicarHorarioGeneral();				
			}else{
				getConfigurarScaeProView().getSpnHoraEntradaG().setEnabled(false);
				getConfigurarScaeProView().getSpnHoraSalidaG().setEnabled(false);
				getConfigurarScaeProView().getSpnHoraSalidaComidaG().setEnabled(false);
				getConfigurarScaeProView().getSpnHoraEntradaComidaG().setEnabled(false);
				if(!getConfigurarScaeProView().getTabbedPane().getTitleAt(turnoSeleccionado).equals("Nuevo Turno")){					
					llenarHorario();
				}else{
					getConfigurarScaeProView().reiniciarControles();
				}
			}
		}
	}
	
	public void aplicarHorarioGeneral(){
		getConfigurarScaeProView().getSpnHEntradaLunes().setValue(getConfigurarScaeProView().getSpnHoraEntradaG().getValue());
		getConfigurarScaeProView().getSpnHEntradaMartes().setValue(getConfigurarScaeProView().getSpnHoraEntradaG().getValue());
		getConfigurarScaeProView().getSpnHEntradaMiercoles().setValue(getConfigurarScaeProView().getSpnHoraEntradaG().getValue());
		getConfigurarScaeProView().getSpnHEntradaJueves().setValue(getConfigurarScaeProView().getSpnHoraEntradaG().getValue());
		getConfigurarScaeProView().getSpnHEntradaViernes().setValue(getConfigurarScaeProView().getSpnHoraEntradaG().getValue());
		getConfigurarScaeProView().getSpnHEntradaSabado().setValue(getConfigurarScaeProView().getSpnHoraEntradaG().getValue());
		getConfigurarScaeProView().getSpnHEntradaDomingo().setValue(getConfigurarScaeProView().getSpnHoraEntradaG().getValue());
		
		getConfigurarScaeProView().getSpnHSalidaLunes().setValue(getConfigurarScaeProView().getSpnHoraSalidaG().getValue());
		getConfigurarScaeProView().getSpnHSalidaMartes().setValue(getConfigurarScaeProView().getSpnHoraSalidaG().getValue());
		getConfigurarScaeProView().getSpnHSalidaMiercoles().setValue(getConfigurarScaeProView().getSpnHoraSalidaG().getValue());
		getConfigurarScaeProView().getSpnHSalidaJueves().setValue(getConfigurarScaeProView().getSpnHoraSalidaG().getValue());
		getConfigurarScaeProView().getSpnHSalidaViernes().setValue(getConfigurarScaeProView().getSpnHoraSalidaG().getValue());
		getConfigurarScaeProView().getSpnHSalidaSabado().setValue(getConfigurarScaeProView().getSpnHoraSalidaG().getValue());
		getConfigurarScaeProView().getSpnHSalidaDomingo().setValue(getConfigurarScaeProView().getSpnHoraSalidaG().getValue());
		
		getConfigurarScaeProView().getSpnHSalidaComidaLunes().setValue(getConfigurarScaeProView().getSpnHoraSalidaComidaG().getValue());
		getConfigurarScaeProView().getSpnHSalidaComidaMartes().setValue(getConfigurarScaeProView().getSpnHoraSalidaComidaG().getValue());
		getConfigurarScaeProView().getSpnHSalidaComidaMiercoles().setValue(getConfigurarScaeProView().getSpnHoraSalidaComidaG().getValue());
		getConfigurarScaeProView().getSpnHSalidaComidaJueves().setValue(getConfigurarScaeProView().getSpnHoraSalidaComidaG().getValue());
		getConfigurarScaeProView().getSpnHSalidaComidaViernes().setValue(getConfigurarScaeProView().getSpnHoraSalidaComidaG().getValue());
		getConfigurarScaeProView().getSpnHSalidaComidaSabado().setValue(getConfigurarScaeProView().getSpnHoraSalidaComidaG().getValue());
		getConfigurarScaeProView().getSpnHSalidaComidaDomingo().setValue(getConfigurarScaeProView().getSpnHoraSalidaComidaG().getValue());
		
		getConfigurarScaeProView().getSpnHEntradaComidaLunes().setValue(getConfigurarScaeProView().getSpnHoraEntradaComidaG().getValue());
		getConfigurarScaeProView().getSpnHEntradaComidaMartes().setValue(getConfigurarScaeProView().getSpnHoraEntradaComidaG().getValue());
		getConfigurarScaeProView().getSpnHEntradaComidaMiercoles().setValue(getConfigurarScaeProView().getSpnHoraEntradaComidaG().getValue());
		getConfigurarScaeProView().getSpnHEntradaComidaJueves().setValue(getConfigurarScaeProView().getSpnHoraEntradaComidaG().getValue());
		getConfigurarScaeProView().getSpnHEntradaComidaViernes().setValue(getConfigurarScaeProView().getSpnHoraEntradaComidaG().getValue());
		getConfigurarScaeProView().getSpnHEntradaComidaSabado().setValue(getConfigurarScaeProView().getSpnHoraEntradaComidaG().getValue());
		getConfigurarScaeProView().getSpnHEntradaComidaDomingo().setValue(getConfigurarScaeProView().getSpnHoraEntradaComidaG().getValue());
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
						}else{
							getConfigurarScaeProView().getChkComidaLunes().setSelected(false);
							getConfigurarScaeProView().getSpnHEntradaComidaLunes().setEnabled(false);
							getConfigurarScaeProView().getSpnHSalidaComidaLunes().setEnabled(false);
						}
					}else{
						getConfigurarScaeProView().getChckbxLunes().setSelected(false);
						getConfigurarScaeProView().getSpnHEntradaLunes().setEnabled(false);
						getConfigurarScaeProView().getSpnHSalidaLunes().setEnabled(false);
						getConfigurarScaeProView().getChkComidaLunes().setSelected(false);
						getConfigurarScaeProView().getChkComidaLunes().setEnabled(false);
						getConfigurarScaeProView().getSpnHEntradaComidaLunes().setEnabled(false);
						getConfigurarScaeProView().getSpnHSalidaComidaLunes().setEnabled(false);
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
						}else{
							getConfigurarScaeProView().getChkComidaMartes().setSelected(false);
							getConfigurarScaeProView().getSpnHEntradaComidaMartes().setEnabled(false);
							getConfigurarScaeProView().getSpnHSalidaComidaMartes().setEnabled(false);
						}
					}else{
						getConfigurarScaeProView().getChkMartes().setSelected(false);
						getConfigurarScaeProView().getSpnHEntradaMartes().setEnabled(false);
						getConfigurarScaeProView().getSpnHSalidaMartes().setEnabled(false);
						getConfigurarScaeProView().getChkComidaMartes().setSelected(false);
						getConfigurarScaeProView().getChkComidaMartes().setEnabled(false);
						getConfigurarScaeProView().getSpnHEntradaComidaMartes().setEnabled(false);
						getConfigurarScaeProView().getSpnHSalidaComidaMartes().setEnabled(false);
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
						}else{
							getConfigurarScaeProView().getChkComidaMiercoles().setSelected(false);
							getConfigurarScaeProView().getSpnHEntradaComidaMiercoles().setEnabled(false);
							getConfigurarScaeProView().getSpnHSalidaComidaMiercoles().setEnabled(false);
						}
					}else{
						getConfigurarScaeProView().getChkMiercoles().setSelected(false);
						getConfigurarScaeProView().getSpnHEntradaMiercoles().setEnabled(false);
						getConfigurarScaeProView().getSpnHSalidaMiercoles().setEnabled(false);
						getConfigurarScaeProView().getChkComidaMiercoles().setSelected(false);
						getConfigurarScaeProView().getChkComidaMiercoles().setEnabled(false);
						getConfigurarScaeProView().getSpnHEntradaComidaMiercoles().setEnabled(false);
						getConfigurarScaeProView().getSpnHSalidaComidaMiercoles().setEnabled(false);
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
						}else{
							getConfigurarScaeProView().getChkComidaJueves().setSelected(false);
							getConfigurarScaeProView().getSpnHEntradaComidaJueves().setEnabled(false);
							getConfigurarScaeProView().getSpnHSalidaComidaJueves().setEnabled(false);
						}
					}else{
						getConfigurarScaeProView().getChkJueves().setSelected(false);
						getConfigurarScaeProView().getSpnHEntradaJueves().setEnabled(false);
						getConfigurarScaeProView().getSpnHSalidaJueves().setEnabled(false);
						getConfigurarScaeProView().getChkComidaJueves().setSelected(false);
						getConfigurarScaeProView().getChkComidaJueves().setEnabled(false);
						getConfigurarScaeProView().getSpnHEntradaComidaJueves().setEnabled(false);
						getConfigurarScaeProView().getSpnHSalidaComidaJueves().setEnabled(false);
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
						}else{
							getConfigurarScaeProView().getChkComidaViernes().setSelected(false);
							getConfigurarScaeProView().getSpnHEntradaComidaViernes().setEnabled(false);
							getConfigurarScaeProView().getSpnHSalidaComidaViernes().setEnabled(false);
						}
					}else{
						getConfigurarScaeProView().getChkViernes().setSelected(false);
						getConfigurarScaeProView().getSpnHEntradaViernes().setEnabled(false);
						getConfigurarScaeProView().getSpnHSalidaViernes().setEnabled(false);
						getConfigurarScaeProView().getChkComidaViernes().setSelected(false);
						getConfigurarScaeProView().getChkComidaViernes().setEnabled(false);
						getConfigurarScaeProView().getSpnHEntradaComidaViernes().setEnabled(false);
						getConfigurarScaeProView().getSpnHSalidaComidaViernes().setEnabled(false);
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
						}else{
							getConfigurarScaeProView().getChkComidaSabado().setSelected(false);
							getConfigurarScaeProView().getSpnHEntradaComidaSabado().setEnabled(false);
							getConfigurarScaeProView().getSpnHSalidaComidaSabado().setEnabled(false);
						}
					}else{
						getConfigurarScaeProView().getChkSabado().setSelected(false);
						getConfigurarScaeProView().getSpnHEntradaSabado().setEnabled(false);
						getConfigurarScaeProView().getSpnHSalidaSabado().setEnabled(false);
						getConfigurarScaeProView().getChkComidaSabado().setSelected(false);
						getConfigurarScaeProView().getChkComidaSabado().setEnabled(false);
						getConfigurarScaeProView().getSpnHEntradaComidaSabado().setEnabled(false);
						getConfigurarScaeProView().getSpnHSalidaComidaSabado().setEnabled(false);
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
						}else{
							getConfigurarScaeProView().getChkComidaDomingo().setSelected(false);
							getConfigurarScaeProView().getSpnHEntradaComidaDomingo().setEnabled(false);
							getConfigurarScaeProView().getSpnHSalidaComidaDomingo().setEnabled(false);
						}
					}else{
						getConfigurarScaeProView().getChkDomingo().setSelected(false);
						getConfigurarScaeProView().getSpnHEntradaDomingo().setEnabled(false);
						getConfigurarScaeProView().getSpnHSalidaDomingo().setEnabled(false);
						getConfigurarScaeProView().getChkComidaDomingo().setSelected(false);
						getConfigurarScaeProView().getChkComidaDomingo().setEnabled(false);
						getConfigurarScaeProView().getSpnHEntradaComidaDomingo().setEnabled(false);
						getConfigurarScaeProView().getSpnHSalidaComidaDomingo().setEnabled(false);
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
			idTurno=turnos.get(i).getIdTurno();
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
			getConfigurarScaeProView().reiniciarControles();
			moverControles(getConfigurarScaeProView().getTabbedPane().getSelectedIndex());
			getConfigurarScaeProView().getTabbedPane().setSelectedIndex(turnoSeleccionado);
			if(!getConfigurarScaeProView().getTabbedPane().getTitleAt(turnoSeleccionado).equals("Nuevo Turno")){
				llenarHorario();
				if(getConfigurarScaeProView().getTabbedPane().getTitleAt(paneles.size()-1).equals("Nuevo Turno")){
					getConfigurarScaeProView().getBtnNuevoTurno().setEnabled(false);
					getConfigurarScaeProView().getBtnGuardarEditar().setEnabled(true);
					getConfigurarScaeProView().getBtnEliminar().setEnabled(true);
				}
			}else{
				getConfigurarScaeProView().getBtnNuevoTurno().setEnabled(false);
				getConfigurarScaeProView().getBtnGuardarEditar().setEnabled(true);
				getConfigurarScaeProView().getBtnEliminar().setEnabled(false);
			}
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

	@Override
	public void stateChanged(ChangeEvent e) {
		if(e.getSource()==getConfigurarScaeProView().getSpnHoraEntradaG() || 
				e.getSource()==getConfigurarScaeProView().getSpnHoraSalidaG() || 
				e.getSource()==getConfigurarScaeProView().getSpnHoraSalidaComidaG() ||
				e.getSource()==getConfigurarScaeProView().getSpnHoraEntradaComidaG()){
			aplicarHorarioGeneral();
		}
	}
}