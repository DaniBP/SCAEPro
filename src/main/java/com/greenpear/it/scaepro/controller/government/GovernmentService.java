/**
 * 
 */
package com.greenpear.it.scaepro.controller.government;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.greenpear.it.scaepro.controller.accesosistema.*;
import com.greenpear.it.scaepro.controller.administracionmovil.AdminNoticiasController;
import com.greenpear.it.scaepro.controller.administracionmovil.EstatusPagoController;
import com.greenpear.it.scaepro.controller.administracionmovil.GenerarAvisoController;
import com.greenpear.it.scaepro.controller.administracionmovil.NoticiaImagenController;
import com.greenpear.it.scaepro.controller.administracionmovil.NoticiasExistentesController;
import com.greenpear.it.scaepro.controller.administracionmovil.NuevaNoticiaController;
import com.greenpear.it.scaepro.controller.configurarempleados.ConfigurarEmpleadosController;
import com.greenpear.it.scaepro.controller.configurarempleados.ConsultarEmpleadosController;
import com.greenpear.it.scaepro.controller.configurarscaepro.ConfigurarScaeProController;
import com.greenpear.it.scaepro.controller.controlacceso.ControlAccesoController;
import com.greenpear.it.scaepro.controller.gestionareas.AltaAreasController;
import com.greenpear.it.scaepro.controller.gestionareas.ConsultaAreasController;
import com.greenpear.it.scaepro.controller.gestionusuarios.AltaUsuariosController;
import com.greenpear.it.scaepro.controller.gestionusuarios.ConsultaUsuariosController;
import com.greenpear.it.scaepro.controller.solicitudreporte.SolicitudReporteController;;


/**
 * @author DanielBP
 *
 */
public class GovernmentService implements Government {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	/**
	 * Constructor
	 */
	public GovernmentService() {
		super();
	}
	
	//****************ESTANCIAS*****************
	@Autowired private InicioController inicioController;
	@Autowired private AccesoAlSistemaController loginController;
	@Autowired private PrincipalController principalController;
	@Autowired private ControlAccesoController controlAccesoController;
	@Autowired private AltaAreasController altaAreasController; 
	@Autowired private ConsultaAreasController consultaAreasController;
	@Autowired private AdminNoticiasController adminNoticiasController;
	@Autowired private NoticiasExistentesController noticiasExistentesController;
	@Autowired private NuevaNoticiaController nuevaNoticiaController;
	@Autowired private NoticiaImagenController noticiaImagenController;
	@Autowired private GenerarAvisoController generarAvisoController;
	@Autowired private EstatusPagoController estatusPagoController;
	@Autowired private SolicitudReporteController solicitudReporteController;
	@Autowired private ConfigurarScaeProController configurarScaeProController;
	@Autowired private ConfigurarEmpleadosController registrarEmpleadoController;
	@Autowired private AltaUsuariosController altaUsuariosController; 
	@Autowired private ConsultaUsuariosController consultaUsuariosController; 
	@Autowired private ConsultarEmpleadosController consultarEmpleadosController;


	public InicioController getInicioController() {
		return inicioController;
	}

	public AccesoAlSistemaController getLoginController() {
		return loginController;
	}
	
	public PrincipalController getPrincipalController() {
		return principalController;
	}
	
	public ControlAccesoController getControlAccesoController() {
		return controlAccesoController;
	}
	
	public AltaAreasController getAltaAreasController() {
		return altaAreasController;
	}
	
	public ConsultaAreasController getConsultaAreasController() {
		return consultaAreasController;
	}
	
	public AdminNoticiasController getAdminNoticiasController() {
		return adminNoticiasController;
	}
	
	public NoticiasExistentesController getNoticiasExistentesController() {
		return noticiasExistentesController;
	}
	
	public NuevaNoticiaController getNuevaNoticiaController() {
		return nuevaNoticiaController;
	}
	
	public NoticiaImagenController getNoticiaImagenController() {
		return noticiaImagenController;
	}
	
	public EstatusPagoController getEstatusPagoController() {
		return estatusPagoController;
	}
	
	public GenerarAvisoController getGenerarAvisoController() {
		return generarAvisoController;
	}
	
	public SolicitudReporteController getSolicitudReporteController() {
		return solicitudReporteController;
	}
	
	public ConfigurarScaeProController getConfigurarScaeProController() {
		return configurarScaeProController;
	}
	
	public ConfigurarEmpleadosController getRegistrarEmpleadoController() {
		return registrarEmpleadoController;
	}
	
	public AltaUsuariosController getAltaUsuariosController(){
		return altaUsuariosController;
	}
	
	public ConsultaUsuariosController getConsultaUsuariosController(){
		return consultaUsuariosController;
	}
	
	public ConsultarEmpleadosController getConsultarEmpleadosController() {
		return consultarEmpleadosController;
	}
	
	//*************FIN DE ESTANCIAS***************
	
	
	//*************CONTROL DE VENTANAS**************



	@Override
	public void mostrarInicio() {
		getInicioController().mostrarVistaInicio();
	}
	
	@Override
	public void mostrarLogin() {
		getLoginController().mostrarVistaLogin();
	}

	@Override
	public void mostrarVentanaPrincipal() {
		getPrincipalController().mostrarVistaPrincipal();
	}
	
	@Override
	public void mostrarControlAcceso(){
		getControlAccesoController().mostrarVistaControlAcceso();
	}

	@Override
	public void mostrarVistaRegistrarAreas(){
		getAltaAreasController().mostrarVistaRegistrarAreas();
	}
	
	@Override
	public void mostrarVistaConsultarAreas(){
		getConsultaAreasController().mostrarVistaConsultarAreas();
	}
	
	@Override
	public void mostrarVistaAdminNoticias(){
		getAdminNoticiasController().mostrarVistaAdminNoticias();
	}
	
	@Override
	public void mostrarNoticiasExistentes() {
		getNoticiasExistentesController().mostrarNoticiasExistentes();
	}
	
	@Override
	public void mostrarNuevaNoticia() {
		getNuevaNoticiaController().mostrarNuevaNoticia();
	}
	
	@Override
	public void mostrarImagenAmpliada() {
		getNoticiaImagenController().mostrarImagenAmpliada();
	}
	
	@Override
	public void mostrarVistaEstatusPago() {
		getEstatusPagoController().mostrarVistaEstatusPago();
	}
	
	@Override
	public void mostrarVistaGenerarAviso() {
		getGenerarAvisoController().mostrarVistaGenerarAviso();
	}

	@Override
	public void mostrarVistaSolicitudReporte() {
		getSolicitudReporteController().mostrarVistaSolicitudReporte();
		
	}

	@Override
	public void mostrarVistaConfigurarScaePro() {
		getConfigurarScaeProController().mostrarVistaConfigurarScaePro();
		
	}
	
	@Override
	public void mostrarVistaRegistro() {
		getRegistrarEmpleadoController().mostrarVistaRegistroEmpleado();
	}
	
	@Override
	public void mostrarVistaRegistrarUsuarios(){
		getAltaUsuariosController().mostrarVistaRegistrarUsuarios();
	}
	
	@Override
	public void mostrarVistaConsultarUsuarios(){
		getConsultaUsuariosController().mostrarVistaConsultarUsuarios();
	}

	@Override
	public void mostrarVistaConsultarEmpleados() {
		getConsultarEmpleadosController().mostrarVistaConsultaEmpleado();
	}
}
