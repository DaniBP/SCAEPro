package com.greenpear.it.scaepro.controller.configurarempleados;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import com.greenpear.it.scaepro.bo.configurarempleados.ConfigurarEmpleadosBo;
import com.greenpear.it.scaepro.controller.government.GovernmentService;
import com.greenpear.it.scaepro.model.direccion.DireccionModelo;
import com.greenpear.it.scaepro.model.empleado.EmpleadoModel;
import com.greenpear.it.scaepro.model.turno.TurnoModel;
import com.greenpear.it.scaepro.view.configurarempleados.RegistrarEmpleado;
import jdk.nashorn.internal.scripts.JO;

@Controller
public class ConfigurarEmpleadosController implements ActionListener, ItemListener, WindowListener {

	public ConfigurarEmpleadosController() {
		super();
	}
	// Estancias

	// Government
	@Autowired
	private GovernmentService goverment;

	// Modelos
	@Autowired
	private EmpleadoModel configurarEmpleadosModel;
	@Autowired
	private DireccionModelo direccionModelo;
	@Autowired
	private TurnoModel turnoModelo;

	// Vistas
	@Autowired
	private RegistrarEmpleado registrarEmpleadoView;

	// Bo
	@Autowired
	@Qualifier("empleadosBoService")
	private ConfigurarEmpleadosBo empleadosBo;

	// Controladores
	@Autowired
	private TomarFotoController fotoController;
	@Autowired
	private CapturaHuellaController huellaController;
	@Autowired
	private ConsultarEmpleadosController consultaController;

	// Getters de clases privadas antes declaradas
	public EmpleadoModel getConfigurarEmpleadosModel() {
		return configurarEmpleadosModel;
	}

	public DireccionModelo getDireccionModelo() {
		return direccionModelo;
	}

	public TurnoModel getTurnoModelo() {
		return turnoModelo;
	}

	public RegistrarEmpleado getRegistrarEmpleadoView() {
		return registrarEmpleadoView;
	}

	public ConfigurarEmpleadosBo getEmpleadosBo() {
		return empleadosBo;
	}

	public GovernmentService getGoverment() {
		return goverment;
	}

	public TomarFotoController getFotoController() {
		return fotoController;
	}

	public CapturaHuellaController getHuellaController() {
		return huellaController;
	}

	public ConsultarEmpleadosController getConsultaController() {
		return consultaController;
	}

	public void mostrarVistaRegistroEmpleado() {
		if (getRegistrarEmpleadoView().getBtnRegistrar().getActionListeners().length == 0) {
			getRegistrarEmpleadoView().getBtnRegistrar().addActionListener(this);
			getRegistrarEmpleadoView().getCmbArea().addItemListener(this);
			getRegistrarEmpleadoView().getCmbTurno().addItemListener(this);
			// getRegistrarEmpleadoView().addWindowListener(this);
			getRegistrarEmpleadoView().getCmbPeriodoNominal().addItemListener(this);
			getRegistrarEmpleadoView().getBtnCapturarFoto().addActionListener(this);
			getRegistrarEmpleadoView().getBtnLeerHuella().addActionListener(this);
		}
		getRegistrarEmpleadoView().setVisible(true);
		getRegistrarEmpleadoView().toFront();

		limpiarVentanas();
		ventanaOpen();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(getRegistrarEmpleadoView().getBtnRegistrar())) {
			if (getRegistrarEmpleadoView().getBtnRegistrar().getText() == "Registrar") {
				String fecha = "";
				try {
					DateFormat df = DateFormat.getDateInstance();
					fecha = df.format(registrarEmpleadoView.getFecha().getDate());
				} catch (Exception exe) {
					fecha = "vacio";
				}

				// Validar panel datos personales
				if (getRegistrarEmpleadoView().getTxtNombres().getText().isEmpty()
						|| getRegistrarEmpleadoView().getTxtApePat().getText().isEmpty()
						|| getRegistrarEmpleadoView().getTxtApeMat().getText().isEmpty()
						|| getRegistrarEmpleadoView().getTxtTelCasa().getText().isEmpty()
						|| getRegistrarEmpleadoView().getTxtTelCel().getText().isEmpty() || fecha.equals("vacio")) {
					JOptionPane.showMessageDialog(null, "Completa todos los valores solicitados de 'Datos personales'");
					// Validar panel de direccion
				} else if (getRegistrarEmpleadoView().getTxtCp().getText().isEmpty()
						|| getRegistrarEmpleadoView().getTxtCalle().getText().isEmpty()
						|| getRegistrarEmpleadoView().getTxtNumeroInt().getText().isEmpty()
						|| getRegistrarEmpleadoView().getTxtNumeroExt().getText().isEmpty()
						|| getRegistrarEmpleadoView().getCmbColonia().getSelectedItem()
								.equals("---Seleccione su colonia---")) {
					JOptionPane.showMessageDialog(null, "Completa todos los valores solicitados de 'Direccion'");
					// Validar panel de asignacion
				} else if (getRegistrarEmpleadoView().getCmbPuesto().getSelectedItem()
						.equals("-----Seleccione puesto-----")
						|| getRegistrarEmpleadoView().getCmbArea().getSelectedItem()
								.equals("--------Seleccione un área--------")
						|| getRegistrarEmpleadoView().getCmbTurno().getSelectedItem()
								.equals("--------Seleccione un turno--------")) {
					JOptionPane.showMessageDialog(null, "Completa todos los campos de  'Asignacion'");
					// Validar campos de nomina
				} else if (getRegistrarEmpleadoView().getCmbPeriodoNominal().getSelectedItem()
						.equals("----Selecciona un periodo nominal----")
						|| getRegistrarEmpleadoView().getCmbDiaNomina().getSelectedItem()
								.equals("---------Selecciona un dia---------")) {
					JOptionPane.showMessageDialog(null, "Completa todos los campos de 'Nomina'");
				} else if (getConfigurarEmpleadosModel().getFotografia() == null) {
					JOptionPane.showMessageDialog(null, "Por favor capture una fotografia");
				} else if (getConfigurarEmpleadosModel().getDatosHuella() == null) {
					JOptionPane.showMessageDialog(null, "Por favor capture la huella digital");
				} else {
					insertarDireccionEmpleado();
					conocerIdTurno();
					String empleado = validarEmpleado();
					if (!empleado.equals(getRegistrarEmpleadoView().getTxtNombres().getText() + " "
							+ getRegistrarEmpleadoView().getTxtApePat().getText() + " "
							+ getRegistrarEmpleadoView().getTxtApeMat().getText())) {
						insertarEmpleado();
						limpiarVentanas();
					} else {
						JOptionPane.showMessageDialog(null, "El empleado " + empleado + " ya esta registrado");
					}
				}
			} else if (getRegistrarEmpleadoView().getBtnRegistrar().getText() == "Modificar") {
				String fecha = "";
				try {
					DateFormat df = DateFormat.getDateInstance();
					fecha = df.format(registrarEmpleadoView.getFecha().getDate());
				} catch (Exception exe) {
					fecha = "vacio";
				}

				// Validar panel datos personales
				if (getRegistrarEmpleadoView().getTxtNombres().getText().isEmpty()
						|| getRegistrarEmpleadoView().getTxtApePat().getText().isEmpty()
						|| getRegistrarEmpleadoView().getTxtApeMat().getText().isEmpty()
						|| getRegistrarEmpleadoView().getTxtTelCasa().getText().isEmpty()
						|| getRegistrarEmpleadoView().getTxtTelCel().getText().isEmpty() || fecha.equals("vacio")) {
					JOptionPane.showMessageDialog(null, "Completa todos los valores solicitados de 'Datos personales'");
					// Validar panel de direccion
				} else if (getRegistrarEmpleadoView().getTxtCp().getText().isEmpty()
						|| getRegistrarEmpleadoView().getTxtCalle().getText().isEmpty()
						|| getRegistrarEmpleadoView().getTxtNumeroInt().getText().isEmpty()
						|| getRegistrarEmpleadoView().getTxtNumeroExt().getText().isEmpty()
						|| getRegistrarEmpleadoView().getCmbColonia().getSelectedItem()
								.equals("---Seleccione su colonia---")) {
					JOptionPane.showMessageDialog(null, "Completa todos los valores solicitados de 'Direccion'");
					// Validar panel de asignacion
				} else if (getRegistrarEmpleadoView().getCmbPuesto().getSelectedItem()
						.equals("-----Seleccione puesto-----")
						|| getRegistrarEmpleadoView().getCmbArea().getSelectedItem()
								.equals("--------Seleccione un área--------")
						|| getRegistrarEmpleadoView().getCmbTurno().getSelectedItem()
								.equals("--------Seleccione un turno--------")) {
					JOptionPane.showMessageDialog(null, "Completa todos los campos de  'Asignacion'");
					// Validar campos de nomina
				} else if (getRegistrarEmpleadoView().getCmbPeriodoNominal().getSelectedItem()
						.equals("----Selecciona un periodo nominal----")
						|| getRegistrarEmpleadoView().getCmbDiaNomina().getSelectedItem()
								.equals("---------Selecciona un dia---------")) {
					JOptionPane.showMessageDialog(null, "Completa todos los campos de 'Nomina'");
				} else if (getConfigurarEmpleadosModel().getFotografia() == null) {
					JOptionPane.showMessageDialog(null, "Por favor capture una fotografia");
				} else {

					insertarDireccionEmpleado();
					conocerIdTurno();
					String empleado = validarEmpleado();
					if (getConsultaController().getNombreInicial()
							.equals(getRegistrarEmpleadoView().getTxtNombres().getText() + " "
									+ getRegistrarEmpleadoView().getTxtApePat().getText() + " "
									+ getRegistrarEmpleadoView().getTxtApeMat().getText())) {
						modificarEmpleado();
						limpiarVentanas();
						getRegistrarEmpleadoView().setVisible(false);

					} else {
						if (empleado.equals(getRegistrarEmpleadoView().getTxtNombres().getText() + " "
								+ getRegistrarEmpleadoView().getTxtApePat().getText() + " "
								+ getRegistrarEmpleadoView().getTxtApeMat().getText())) {
							JOptionPane.showMessageDialog(null, "El empleado " + empleado + " ya esta registrado");
						} else {
							modificarEmpleado();
							limpiarVentanas();
							getRegistrarEmpleadoView().setVisible(false);
						}
					}
				}
			}

		} else if (e.getSource().equals(getRegistrarEmpleadoView().getBtnCapturarFoto())) {
			// fotoController.iniciar();
			SwingUtilities.invokeLater(fotoController);
			// consultarIdEmpleado();
		} else if (e.getSource().equals(getRegistrarEmpleadoView().getBtnLeerHuella())) {
			if (getRegistrarEmpleadoView().getBtnLeerHuella().getText()=="Capturar Huella" 
					|| getRegistrarEmpleadoView().getBtnLeerHuella().getText()=="Cambiar Huella") {
				getHuellaController().setVisible(true);
				getHuellaController().start();
			} else {
					 configurarEmpleadosModel= getEmpleadosBo().consultarUserAndPassword(getConfigurarEmpleadosModel());
					 JOptionPane.showMessageDialog(null, "El nombre de usuario es "+configurarEmpleadosModel.getNombreUsuario()
					 +" y la contraseña es "+configurarEmpleadosModel.getPassword());
			}
		}
	}

	private String validarEmpleado() {
		String empleado = "";
		EmpleadoModel empleadoModel = new EmpleadoModel();
		try {
			empleadoModel = getEmpleadosBo().validarEmpleado(getRegistrarEmpleadoView().getTxtNombres().getText(),
					getRegistrarEmpleadoView().getTxtApePat().getText(),
					getRegistrarEmpleadoView().getTxtApeMat().getText());
			if (empleadoModel == null) {
				empleado = "vacio";
			} else {
				empleado = empleadoModel.getNombreEmpleado() + " " + empleadoModel.getApePatEmpleado() + " "
						+ empleadoModel.getApeMatEmpleado();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return empleado;
	}

	private void modificarEmpleado() {
		DateFormat df = DateFormat.getDateInstance();
		String fecha = df.format(registrarEmpleadoView.getFecha().getDate());
		fecha = construirFecha(fecha);
		String nombreUsuario = construirNombreUsuario();
		getConfigurarEmpleadosModel().setNombreEmpleado(getRegistrarEmpleadoView().getTxtNombres().getText());
		getConfigurarEmpleadosModel().setApePatEmpleado(getRegistrarEmpleadoView().getTxtApePat().getText());
		getConfigurarEmpleadosModel().setApeMatEmpleado(getRegistrarEmpleadoView().getTxtApeMat().getText());
		getConfigurarEmpleadosModel().setFechaNacimiento(fecha);
		getConfigurarEmpleadosModel().setTelCasa(getRegistrarEmpleadoView().getTxtTelCasa().getText());
		getConfigurarEmpleadosModel().setTelCel(getRegistrarEmpleadoView().getTxtTelCel().getText());
		getConfigurarEmpleadosModel().setIdDireccionEmpleado(getDireccionModelo().getIdDireccion());
		getConfigurarEmpleadosModel().setPuesto(getRegistrarEmpleadoView().getCmbPuesto().getSelectedItem().toString());
		getConfigurarEmpleadosModel().setIdTurno(turnoModelo.getIdTurno());
		getConfigurarEmpleadosModel().setFotografia(configurarEmpleadosModel.getFotografia());
		getConfigurarEmpleadosModel()
				.setPeriodoNominal(getRegistrarEmpleadoView().getCmbPeriodoNominal().getSelectedItem().toString());
		getConfigurarEmpleadosModel().setDiaDePago(
				Integer.parseInt(getRegistrarEmpleadoView().getCmbDiaNomina().getSelectedItem().toString()));
		getConfigurarEmpleadosModel().setNombreUsuario(nombreUsuario);
		getConfigurarEmpleadosModel().setPassword("123456789");
		// System.out.println(getConfigurarEmpleadosModel().getNombreEmpleado());
		// System.out.println(getConfigurarEmpleadosModel().getApeMatEmpleado());
		// System.out.println(getConfigurarEmpleadosModel().getFechaNacimiento());
		// System.out.println(getConfigurarEmpleadosModel().getTelCasa());
		// System.out.println(getConfigurarEmpleadosModel().getTelCel());
		// System.out.println(getConfigurarEmpleadosModel().getIdDireccionEmpleado());
		// System.out.println(getConfigurarEmpleadosModel().getPuesto());
		// System.out.println(getConfigurarEmpleadosModel().getIdTurno());
		// System.out.println(getConfigurarEmpleadosModel().getFotografia());
		// System.out.println(getConfigurarEmpleadosModel().getPeriodoNominal());
		// System.out.println(getConfigurarEmpleadosModel().getDiaDePago());
		// System.out.println(getConfigurarEmpleadosModel().getNombreUsuario());
		// System.out.println(getConfigurarEmpleadosModel().getPassword());

		String mensaje = null;
		try {
			mensaje = getEmpleadosBo().ModificarEmpleado(configurarEmpleadosModel);
			JOptionPane.showMessageDialog(null, mensaje);
		} catch (SQLException t) {
			JOptionPane.showMessageDialog(null, t.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void insertarEmpleado() {
		DateFormat df = DateFormat.getDateInstance();
		String fecha = df.format(registrarEmpleadoView.getFecha().getDate());
		fecha = construirFecha(fecha);
		String nombreUsuario = construirNombreUsuario();
		getConfigurarEmpleadosModel().setNombreEmpleado(getRegistrarEmpleadoView().getTxtNombres().getText());
		getConfigurarEmpleadosModel().setApePatEmpleado(getRegistrarEmpleadoView().getTxtApePat().getText());
		getConfigurarEmpleadosModel().setApeMatEmpleado(getRegistrarEmpleadoView().getTxtApeMat().getText());
		getConfigurarEmpleadosModel().setFechaNacimiento(fecha);
		getConfigurarEmpleadosModel().setTelCasa(getRegistrarEmpleadoView().getTxtTelCasa().getText());
		getConfigurarEmpleadosModel().setTelCel(getRegistrarEmpleadoView().getTxtTelCel().getText());
		getConfigurarEmpleadosModel().setIdDireccionEmpleado(getDireccionModelo().getIdDireccion());
		getConfigurarEmpleadosModel().setPuesto(getRegistrarEmpleadoView().getCmbPuesto().getSelectedItem().toString());
		getConfigurarEmpleadosModel().setIdTurno(turnoModelo.getIdTurno());
		getConfigurarEmpleadosModel().setFotografia(configurarEmpleadosModel.getFotografia());
		getConfigurarEmpleadosModel()
				.setPeriodoNominal(getRegistrarEmpleadoView().getCmbPeriodoNominal().getSelectedItem().toString());
		getConfigurarEmpleadosModel().setDiaDePago(
				Integer.parseInt(getRegistrarEmpleadoView().getCmbDiaNomina().getSelectedItem().toString()));
		getConfigurarEmpleadosModel().setNombreUsuario(nombreUsuario);
		getConfigurarEmpleadosModel().setPassword("123456789");
		String mensaje = null;
		try {
			mensaje = getEmpleadosBo().registrarEmpleado(configurarEmpleadosModel);
			JOptionPane.showMessageDialog(null, mensaje);
		} catch (SQLException t) {
			JOptionPane.showMessageDialog(null, t.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	private String construirFecha(String fecha) {
		String ano, mes, dia;
		String fechaArray[] = fecha.split("/");
		dia = fechaArray[0];
		mes = fechaArray[1];
		ano = fechaArray[2];
		if (dia.length() == 1) {
			fecha = ano + "/" + mes + "/" + "0" + dia;
		} else {
			fecha = ano + "/" + mes + "/" + dia;
		}
		return fecha;
	}

	private String construirNombreUsuario() {
		String idUsuario, nombreEmpleado, apellido, nomFinal;
		idUsuario = Integer.toString(configurarEmpleadosModel.getIdEmpleado() + 1);
		nombreEmpleado = (getRegistrarEmpleadoView().getTxtNombres().getText()).substring(0, 3);
		apellido = (getRegistrarEmpleadoView().getTxtApePat().getText()).substring(0, 3);
		nomFinal = idUsuario + nombreEmpleado + apellido;
		return nomFinal;
	}

	private void limpiarVentanas() {
		getRegistrarEmpleadoView().getTxtNombres().setText(null);
		getRegistrarEmpleadoView().getTxtNombres().setEditable(true);
		getRegistrarEmpleadoView().getTxtApePat().setText(null);
		getRegistrarEmpleadoView().getTxtApePat().setEditable(true);
		getRegistrarEmpleadoView().getTxtApeMat().setText(null);
		getRegistrarEmpleadoView().getTxtApeMat().setEditable(true);
		getRegistrarEmpleadoView().getTxtCalle().setText(null);
		getRegistrarEmpleadoView().getTxtCalle().setEditable(true);
		getRegistrarEmpleadoView().getTxtCp().setText(null);
		getRegistrarEmpleadoView().getTxtCp().setEditable(true);
		getRegistrarEmpleadoView().getTxtNumeroExt().setText(null);
		getRegistrarEmpleadoView().getTxtNumeroExt().setEditable(true);
		getRegistrarEmpleadoView().getTxtNumeroInt().setText(null);
		getRegistrarEmpleadoView().getTxtNumeroInt().setEditable(true);
		getRegistrarEmpleadoView().getTxtMunicipio().setText(null);
		getRegistrarEmpleadoView().getTxtMunicipio().setEditable(true);
		getRegistrarEmpleadoView().getTxtEstado().setText(null);
		getRegistrarEmpleadoView().getTxtEstado().setEditable(true);
		getRegistrarEmpleadoView().getTxtTelCasa().setText(null);
		getRegistrarEmpleadoView().getTxtTelCasa().setEditable(true);
		getRegistrarEmpleadoView().getTxtTelCel().setText(null);
		getRegistrarEmpleadoView().getTxtTelCel().setEditable(true);
		getRegistrarEmpleadoView().getFecha().setDate(null);
		getRegistrarEmpleadoView().getFecha().setEnabled(true);
		getRegistrarEmpleadoView().getBtnCapturarFoto().setText("Capturar Foto");
		getRegistrarEmpleadoView().getBtnCapturarFoto().setVisible(true);
		getRegistrarEmpleadoView().getBtnLeerHuella().setText("Capturar Huella");
		getRegistrarEmpleadoView().getBtnLeerHuella().setVisible(true);
		getRegistrarEmpleadoView().getBtnRegistrar().setText("Registrar");
		getRegistrarEmpleadoView().getBtnRegistrar().setVisible(true);
		getRegistrarEmpleadoView().getLblFotografia().setIcon(null);
		getRegistrarEmpleadoView().getLblHuellaDigital().setIcon(null);
		limpiarCombos();
		limpiarDireccion();
		limpiarModeloEmpleado();
	}

	private void limpiarModeloEmpleado() {
		getConfigurarEmpleadosModel().setFotografia(null);
		getConfigurarEmpleadosModel().setNipEmpleado(null);
		getConfigurarEmpleadosModel().setApeMatEmpleado(null);
		getConfigurarEmpleadosModel().setApePatEmpleado(null);
		getConfigurarEmpleadosModel().setArea(null);
		getConfigurarEmpleadosModel().setDiaDePago(0);
		getConfigurarEmpleadosModel().setIdDireccionEmpleado(0);
		getConfigurarEmpleadosModel().setEstado(null);
		getConfigurarEmpleadosModel().setIdEmpleado(0);
		getConfigurarEmpleadosModel().setHuellaEmpleado(null);
		getConfigurarEmpleadosModel().setIdTurno(0);
		getConfigurarEmpleadosModel().setPeriodoNominal(null);
		getConfigurarEmpleadosModel().setTelCasa(null);
		getConfigurarEmpleadosModel().setTelCel(null);
		getConfigurarEmpleadosModel().setTamanoHuella(0);
		getConfigurarEmpleadosModel().setDatosHuella(null);
		getConfigurarEmpleadosModel().setNombreUsuario(null);
		getConfigurarEmpleadosModel().setPassword(null);
		getConfigurarEmpleadosModel().setIdEmpleado(0);
	}

	public void limpiarCombos() {
		final int tempArea = getRegistrarEmpleadoView().getCmbArea().getItemCount();
		final int tempNomina = getRegistrarEmpleadoView().getCmbPeriodoNominal().getItemCount();
		getRegistrarEmpleadoView().getCmbPuesto().setEnabled(true);
		getRegistrarEmpleadoView().getCmbArea().setEnabled(true);
		getRegistrarEmpleadoView().getCmbPeriodoNominal().setEnabled(true);
		for (int x = 0; x < tempArea; x++) {
			if (getRegistrarEmpleadoView().getCmbArea().getItemCount() != 1) {
				getRegistrarEmpleadoView().getCmbArea().removeItemAt(1);
			}
		}
		getRegistrarEmpleadoView().getCmbPuesto().setSelectedItem("-----Seleccione puesto-----");
		for (int x = 0; x < tempNomina; x++) {
			getRegistrarEmpleadoView().getCmbPeriodoNominal().removeItemAt(0);
			if (getRegistrarEmpleadoView().getCmbPeriodoNominal().getItemCount() == 1) {
				getRegistrarEmpleadoView().getCmbPeriodoNominal().addItem("----Selecciona un periodo nominal----");
				getRegistrarEmpleadoView().getCmbPeriodoNominal().addItem("Semanal");
				getRegistrarEmpleadoView().getCmbPeriodoNominal().addItem("Quincenal");
				getRegistrarEmpleadoView().getCmbPeriodoNominal().addItem("Mensual");
			}
		}

	}

	public void limpiarArea() {
		final int tempArea = getRegistrarEmpleadoView().getCmbArea().getItemCount();
		for (int x = 0; x < tempArea; x++) {
			getRegistrarEmpleadoView().getCmbArea().removeItemAt(1);
			if (getRegistrarEmpleadoView().getCmbArea().getItemCount() == 1) {
				llenarComboArea();
			}
		}
	}

	private void insertarDireccionEmpleado() {

		direccionModelo.setCp(getRegistrarEmpleadoView().getTxtCp().getText());
		direccionModelo.setNumIn(getRegistrarEmpleadoView().getTxtNumeroInt().getText());
		direccionModelo.setNumEx(getRegistrarEmpleadoView().getTxtNumeroExt().getText());
		direccionModelo.setCalle(getRegistrarEmpleadoView().getTxtCalle().getText());
		direccionModelo.setUniColonia(getRegistrarEmpleadoView().getCmbColonia().getSelectedItem().toString());
		String acceso = null;

		try {
			acceso = getEmpleadosBo().insertarDirEmpl(getDireccionModelo());
			// JOptionPane.showMessageDialog(null,getDireccionModelo().getIdDireccion());
		} catch (SQLException t) {
			JOptionPane.showMessageDialog(null, t.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// Escucha el cambio del area y llena los turno dependiendo el area
		if (e.getSource().equals(getRegistrarEmpleadoView().getCmbArea())) {
			if (!getRegistrarEmpleadoView().getCmbArea().getSelectedItem()
					.equals("--------Seleccione un area--------")) {
				getRegistrarEmpleadoView().getCmbTurno().setEnabled(true);
				llenarTurnos();
				// conocerIdTurno();
			} else {
				getRegistrarEmpleadoView().getCmbTurno().setEnabled(false);
			}
		} else if (e.getSource().equals(getRegistrarEmpleadoView().getCmbTurno())) {
			// System.out.println(getRegistrarEmpleadoView().getCmbTurno().getSelectedItem());
			// System.out.println(getRegistrarEmpleadoView().getCmbTurno().getSelectedItem().toString());
			// if(getRegistrarEmpleadoView().getCmbTurno().getSelectedItem()!="--------Seleccione
			// un turno--------"){
			// JOptionPane.showMessageDialog(null, "Entro");
			// }
		}
		// Escucha el cambio del combo periodo nominal y realiza las
		// asignaciones de dia
		// dependiendo el periodo escogido
		else if (e.getSource().equals(getRegistrarEmpleadoView().getCmbPeriodoNominal())) {
			getRegistrarEmpleadoView().getCmbDiaNomina().removeAllItems();
			getRegistrarEmpleadoView().getCmbDiaNomina().addItem("---------Selecciona un dia---------");
			if (!getRegistrarEmpleadoView().getCmbPeriodoNominal().getSelectedItem()
					.equals("----Selecciona un periodo nominal----")) {
				if (getRegistrarEmpleadoView().getCmbPeriodoNominal().getSelectedItem().equals("Semanal")) {
					getRegistrarEmpleadoView().getCmbDiaNomina().addItem("1");
					getRegistrarEmpleadoView().getCmbDiaNomina().addItem("8");
					getRegistrarEmpleadoView().getCmbDiaNomina().addItem("15");
					getRegistrarEmpleadoView().getCmbDiaNomina().addItem("23");
					getRegistrarEmpleadoView().getCmbDiaNomina().addItem("30");
					getRegistrarEmpleadoView().getCmbDiaNomina().setEnabled(true);
				} else if (getRegistrarEmpleadoView().getCmbPeriodoNominal().getSelectedItem().equals("Quincenal")) {
					getRegistrarEmpleadoView().getCmbDiaNomina().addItem("1");
					getRegistrarEmpleadoView().getCmbDiaNomina().addItem("15");
					getRegistrarEmpleadoView().getCmbDiaNomina().addItem("30");
					getRegistrarEmpleadoView().getCmbDiaNomina().setEnabled(true);
				} else if (getRegistrarEmpleadoView().getCmbPeriodoNominal().getSelectedItem().equals("Mensual")) {
					getRegistrarEmpleadoView().getCmbDiaNomina().addItem("1");
					getRegistrarEmpleadoView().getCmbDiaNomina().addItem("30");
					getRegistrarEmpleadoView().getCmbDiaNomina().setEnabled(true);
				}
			} else {
				getRegistrarEmpleadoView().getCmbDiaNomina().setEnabled(false);
			}

		}
	}

	private void conocerIdTurno() {
		turnoModelo.setArea(getRegistrarEmpleadoView().getCmbArea().getSelectedItem().toString());
		turnoModelo.setNombreTurno(getRegistrarEmpleadoView().getCmbTurno().getSelectedItem().toString());
		turnoModelo = getEmpleadosBo().consultarIdTurno(turnoModelo);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// El siguiente evento limita la entrada de letras en los campos de tipo
		// numerico
		soloNumeros(getRegistrarEmpleadoView().getTxtCp(), getRegistrarEmpleadoView().getTxtNumeroExt(),
				getRegistrarEmpleadoView().getTxtNumeroInt(), getRegistrarEmpleadoView().getTxtTelCasa(),
				getRegistrarEmpleadoView().getTxtTelCel());
		// El siguiente evento limita la entrada de numeros en los campos de
		// tipo texto
		soloLetras(getRegistrarEmpleadoView().getTxtNombres(), getRegistrarEmpleadoView().getTxtApePat(),
				getRegistrarEmpleadoView().getTxtApeMat());
		// El siguiente evento limita la entrada de caracteres de Codigo Postal
		// a 5
		limitadorDeCaracteres();

		// El siguiente evento escucha los cambios de las entradas cobre el
		// Codigo Postal
		getRegistrarEmpleadoView().getTxtCp().getDocument()
				.addDocumentListener(new javax.swing.event.DocumentListener() {
					public void insertUpdate(javax.swing.event.DocumentEvent evt) {
						if (getRegistrarEmpleadoView().getTxtCp().getText().length() == 5) {
							String cp = getRegistrarEmpleadoView().getTxtCp().getText();
							llenarDireccion(cp);
						}
					}

					public void removeUpdate(javax.swing.event.DocumentEvent evt) {
					}

					public void changedUpdate(javax.swing.event.DocumentEvent evt) {

					}
				});
		llenarComboArea();
	}

	public void ventanaOpen() {
		// El siguiente evento limita la entrada de letras en los campos de tipo
		// numerico
		soloNumeros(getRegistrarEmpleadoView().getTxtCp(), getRegistrarEmpleadoView().getTxtNumeroExt(),
				getRegistrarEmpleadoView().getTxtNumeroInt(), getRegistrarEmpleadoView().getTxtTelCasa(),
				getRegistrarEmpleadoView().getTxtTelCel());
		// El siguiente evento limita la entrada de numeros en los campos de
		// tipo texto
		soloLetras(getRegistrarEmpleadoView().getTxtNombres(), getRegistrarEmpleadoView().getTxtApePat(),
				getRegistrarEmpleadoView().getTxtApeMat());
		// El siguiente evento limita la entrada de caracteres de Codigo Postal
		// a 5
		limitadorDeCaracteres();

		// El siguiente evento escucha los cambios de las entradas cobre el
		// Codigo Postal
		getRegistrarEmpleadoView().getTxtCp().getDocument()
				.addDocumentListener(new javax.swing.event.DocumentListener() {
					public void insertUpdate(javax.swing.event.DocumentEvent evt) {
						if (getRegistrarEmpleadoView().getTxtCp().getText().length() == 5) {
							String cp = getRegistrarEmpleadoView().getTxtCp().getText();
							llenarDireccion(cp);
						}
					}

					public void removeUpdate(javax.swing.event.DocumentEvent evt) {
					}

					public void changedUpdate(javax.swing.event.DocumentEvent evt) {

					}
				});
		llenarComboArea();
	}

	private void limitadorDeCaracteres() {
		final int limiteCp = 5;
		final int limiteNumeros = 10;
		getRegistrarEmpleadoView().getTxtCp().addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e)

			{
				if (getRegistrarEmpleadoView().getTxtCp().getText().length() == limiteCp) {
					Toolkit.getDefaultToolkit().beep();
					e.consume();
				} else if (getRegistrarEmpleadoView().getTxtCp().getText().length() < limiteCp
						|| getRegistrarEmpleadoView().getTxtCp().getText().equals(null)) {
					limpiarDireccion();
				}
			}

			public void keyPressed(KeyEvent arg0) {
			}

			public void keyReleased(KeyEvent arg0) {
			}
		});
	}

	private void soloNumeros(JTextField txtCp, JTextField txtNumeroExt, JTextField txtNumeroInt, JTextField txtTelCasa,
			JTextField txtTelCel) {
		txtCp.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isLetter(c)) {
					Toolkit.getDefaultToolkit().beep();
					e.consume();
				}
			}
		});
		txtNumeroExt.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isLetter(c)) {
					Toolkit.getDefaultToolkit().beep();
					e.consume();
				}
			}
		});
		txtNumeroInt.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isLetter(c)) {
					Toolkit.getDefaultToolkit().beep();
					e.consume();
				}
			}
		});
		txtTelCasa.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isLetter(c)) {
					Toolkit.getDefaultToolkit().beep();
					e.consume();
				}
			}
		});
		txtTelCel.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isLetter(c)) {
					Toolkit.getDefaultToolkit().beep();
					e.consume();
				}
			}
		});
	}

	private void soloLetras(JTextField txtNombres, JTextField txtApePat, JTextField txtApeMat) {
		txtNombres.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isDigit(c)) {
					Toolkit.getDefaultToolkit().beep();
					e.consume();
				}
			}
		});
		txtApePat.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isDigit(c)) {
					Toolkit.getDefaultToolkit().beep();
					e.consume();
				}
			}
		});
		txtApeMat.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isDigit(c)) {
					Toolkit.getDefaultToolkit().beep();
					e.consume();
				}
			}
		});
	}

	public void llenarDireccion(String cp) {
		limpiarDireccion();
		getDireccionModelo().setCp(cp);
		List<DireccionModelo> listaDireccion = new ArrayList<DireccionModelo>();
		try {
			listaDireccion = getEmpleadosBo().consultaDireccion();
			Iterator<DireccionModelo> listaIterator = listaDireccion.iterator();
			getRegistrarEmpleadoView().getCmbColonia().setEnabled(true);
			if (!listaIterator.hasNext()) {
				JOptionPane.showMessageDialog(null, "No existe tal direccion con ese " + "Codigo Postal");
			}
			while (listaIterator.hasNext()) {
				DireccionModelo listai = listaIterator.next();
				getRegistrarEmpleadoView().getTxtEstado().setText(listai.getEstado());
				getRegistrarEmpleadoView().getTxtMunicipio().setText(listai.getMunicipio());
				String colonias = listai.getColonia();
				String[] ListaColonias = colonias.split(";");
				for (int x = 0; x < ListaColonias.length; x++) {
					getRegistrarEmpleadoView().getCmbColonia().addItem(ListaColonias[x]);
				}
			}
		} catch (SQLException t) {
			JOptionPane.showMessageDialog(null, t.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void limpiarDireccion() {
		getRegistrarEmpleadoView().getTxtEstado().setText("");
		getRegistrarEmpleadoView().getTxtMunicipio().setText("");
		getRegistrarEmpleadoView().getCmbColonia().setEnabled(false);
		getRegistrarEmpleadoView().getCmbColonia().removeAllItems();
		getRegistrarEmpleadoView().getCmbColonia().addItem("---Seleccione su colonia---");
	}

	public void llenarComboArea() {
		// getRegistrarEmpleadoView().getCmbArea().removeAllItems();
		List<EmpleadoModel> lista = new ArrayList<EmpleadoModel>();
		try {
			lista = getEmpleadosBo().consultaGeneral();
			Iterator<EmpleadoModel> listaIterator = lista.iterator();
			while (listaIterator.hasNext()) {

				EmpleadoModel listai = listaIterator.next();
				getRegistrarEmpleadoView().getCmbArea().addItem(listai.getArea());
			}
		} catch (SQLException t) {
			JOptionPane.showMessageDialog(null, t.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void llenarTurnos() {
		limpiarTurnos();
		String area = (String) getRegistrarEmpleadoView().getCmbArea().getSelectedItem();
		getTurnoModelo().setArea(area);
		List<TurnoModel> listaTurno = new ArrayList<TurnoModel>();
		try {
			listaTurno = getEmpleadosBo().consultarTurno();
			Iterator<TurnoModel> listaIterator = listaTurno.iterator();
			while (listaIterator.hasNext()) {
				TurnoModel listai = listaIterator.next();
				getRegistrarEmpleadoView().getCmbTurno().addItem(listai.getNombreTurno());
				// conocerIdArea();
			}
		} catch (SQLException t) {
			JOptionPane.showMessageDialog(null, t.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public int consultarIdEmpleado() {
		configurarEmpleadosModel = getEmpleadosBo().consultarIdEmpleado();
		return configurarEmpleadosModel.getIdEmpleado();
	}

	private void limpiarTurnos() {
		getRegistrarEmpleadoView().getCmbTurno().removeAllItems();
		getRegistrarEmpleadoView().getCmbTurno().addItem("--------Seleccione un turno--------");
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
