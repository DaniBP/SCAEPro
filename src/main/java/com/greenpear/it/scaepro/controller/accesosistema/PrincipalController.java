package com.greenpear.it.scaepro.controller.accesosistema;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JInternalFrame;

import org.springframework.beans.factory.annotation.Autowired;

import com.greenpear.it.scaepro.controller.government.GovernmentService;
import com.greenpear.it.scaepro.model.solicitudreporte.SolicitudReporteModel;
import com.greenpear.it.scaepro.view.accesosistema.PrincipalView;
/**
 * 
 * @author DanielBP
 *
 */
public class PrincipalController implements ActionListener{
	
	/**
	 * Constructor
	 */
	public PrincipalController() {
		super();
	}
	
	@Autowired
	private GovernmentService government;
	
	
	@Autowired
	private PrincipalView vistaPrincipal;

	public GovernmentService getGovernment() {
		return government;
	}

	public PrincipalView getVistaPrincipal() {
		return vistaPrincipal;
	}
	
	public void mostrarVistaPrincipal(){
		if(getVistaPrincipal().getMntmSalir().getActionListeners().length==0){
			getVistaPrincipal().addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e){
					regresarInicio();
				}
			});
			
			getVistaPrincipal().getMntmSalir().addActionListener(this);
			getVistaPrincipal().getMntmRegistrarUsuario().addActionListener(this);
			getVistaPrincipal().getMntmConsultarUsuarios().addActionListener(this);
			getVistaPrincipal().getMntmRegistrarrea().addActionListener(this);
			getVistaPrincipal().getMntmConsultarreas().addActionListener(this);
			getVistaPrincipal().getMntmConfigurarScaePro().addActionListener(this);
			getVistaPrincipal().getMntmRegistrarEmpleado().addActionListener(this);
			getVistaPrincipal().getMntmConsultarEmpleados().addActionListener(this);
			getVistaPrincipal().getMntmGenerarReporte().addActionListener(this);
			getVistaPrincipal().getMntmAdministrarAvisos().addActionListener(this);
			getVistaPrincipal().getMntmAdministrarNotcias().addActionListener(this);
			getVistaPrincipal().getMntmEstatusDePago().addActionListener(this);
			getVistaPrincipal().getMntmJustificarIncidencias().addActionListener(this);
			
			getVistaPrincipal().getPanelEscritorio().add(getGovernment().getAltaUsuariosController().getVista());
			getVistaPrincipal().getPanelEscritorio().add(getGovernment().getConsultaUsuariosController().getVista());
			getVistaPrincipal().getPanelEscritorio().add(getGovernment().getAltaAreasController().getVista());
			getVistaPrincipal().getPanelEscritorio().add(getGovernment().getConsultaAreasController().getVista());
			getVistaPrincipal().getPanelEscritorio().add(getGovernment().getConfigurarScaeProController().getConfigurarScaeProView());
			getVistaPrincipal().getPanelEscritorio().add(getGovernment().getRegistrarEmpleadoController().getRegistrarEmpleadoView());
			getVistaPrincipal().getPanelEscritorio().add(getGovernment().getConsultarEmpleadosController().getConsultaEmpleadoView());
			getVistaPrincipal().getPanelEscritorio().add(getGovernment().getSolicitudReporteController().getVista());
			getVistaPrincipal().getPanelEscritorio().add(getGovernment().getAdminNoticiasController().getVista());
			getVistaPrincipal().getPanelEscritorio().add(getGovernment().getNuevaNoticiaController().getVista());
			getVistaPrincipal().getPanelEscritorio().add(getGovernment().getNoticiasExistentesController().getVista());
			getVistaPrincipal().getPanelEscritorio().add(getGovernment().getGenerarAvisoController().getVista());
			getVistaPrincipal().getPanelEscritorio().add(getGovernment().getEstatusPagoController().getVista());
			getVistaPrincipal().getPanelEscritorio().add(getGovernment().getJustificarIncidenciasController().getIncidenciasView());
			getVistaPrincipal().getPanelEscritorio().add(getGovernment().getJustificarIncidenciasController().getJustificarView());
		}
		limpiarVentanaPrincipal();
		getVistaPrincipal().setVisible(true);
	}

	private void limpiarVentanaPrincipal() {
		JInternalFrame[] internals = getVistaPrincipal().getPanelEscritorio().getAllFrames();
		for (int i = 0; i < internals.length; i++) {			
			internals[i].setVisible(false);
		}
	}
	
	private void regresarInicio(){
		getVistaPrincipal().setVisible(false);
		getGovernment().mostrarInicio();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == getVistaPrincipal().getMntmSalir()){
			regresarInicio();
		}else if(e.getSource() == getVistaPrincipal().getMntmRegistrarUsuario()){
			getGovernment().mostrarVistaRegistrarUsuarios();
		}else if(e.getSource() == getVistaPrincipal().getMntmConsultarUsuarios()){
			getGovernment().mostrarVistaConsultarUsuarios();
		}else if(e.getSource() == getVistaPrincipal().getMntmRegistrarrea()){
			getGovernment().mostrarVistaRegistrarAreas();
		}else if(e.getSource() == getVistaPrincipal().getMntmConsultarreas()){
			getGovernment().mostrarVistaConsultarAreas();
		}else if(e.getSource() == getVistaPrincipal().getMntmConfigurarScaePro()){
			getGovernment().mostrarVistaConfigurarScaePro();
		}else if(e.getSource() == getVistaPrincipal().getMntmRegistrarEmpleado()){
			getGovernment().mostrarVistaRegistro();
		}else if(e.getSource() == getVistaPrincipal().getMntmConsultarEmpleados()){
			getGovernment().mostrarVistaConsultarEmpleados();
		}else if(e.getSource() == getVistaPrincipal().getMntmGenerarReporte()){
			getGovernment().mostrarVistaSolicitudReporte();
		}else if(e.getSource() == getVistaPrincipal().getMntmAdministrarNotcias()){
			getGovernment().mostrarVistaAdminNoticias();
		}else if(e.getSource() == getVistaPrincipal().getMntmAdministrarAvisos()){
			getGovernment().mostrarVistaGenerarAviso();
		}else if(e.getSource() == getVistaPrincipal().getMntmEstatusDePago()){
			getGovernment().mostrarVistaEstatusPago();
		}else if(e.getSource() == getVistaPrincipal().getMntmJustificarIncidencias()){
			getGovernment().mostrarIncidencias();
		}
	}
}
