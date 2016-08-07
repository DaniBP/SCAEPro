package com.greenpear.it.scaepro.controller.justificarincidencias;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.greenpear.it.scaepro.bo.configurarempleados.ConfigurarEmpleadosBo;
import com.greenpear.it.scaepro.controller.government.GovernmentService;
import com.greenpear.it.scaepro.model.direccion.DireccionModelo;
import com.greenpear.it.scaepro.model.empleado.EmpleadoModel;
import com.greenpear.it.scaepro.model.gestionareas.ConsultaAreasModel;
import com.greenpear.it.scaepro.model.incidencia.IncidenciaModel;
import com.greenpear.it.scaepro.model.solicitudreporte.SolicitudReporteModel;
import com.greenpear.it.scaepro.model.turno.TurnoModel;
import com.greenpear.it.scaepro.view.incidencias.Incidencias;

@Controller
public class JustificarIncidenciasController implements ActionListener, ItemListener {

	public JustificarIncidenciasController() {
		super();
	}
	// Instancias de Clases a Utilizar

	// Government
	@Autowired
	private GovernmentService goverment;
	//

	// Controladores

	//

	// Modelos
	@Autowired
	private IncidenciaModel incidenciaModel;
	@Autowired
	private TurnoModel turnoModel;
	@Autowired
	private ConsultaAreasModel areasModel;
	@Autowired
	private EmpleadoModel configurarEmpleadosModel;
	@Autowired
	private SolicitudReporteModel solicitudReporteModel;
	//

	// Vistas
	@Autowired
	private Incidencias incidenciasView;

	//

	//
	@Autowired
	private ConfigurarEmpleadosBo empleadoBo;
	//
	
	//Otras instancias
	private String nombreInicial = "";

	public String getNombreInicial() {
		return nombreInicial;
	}
	
	//

	// Getters and Setters

	public GovernmentService getGoverment() {
		return goverment;
	}

	public void setGoverment(GovernmentService goverment) {
		this.goverment = goverment;
	}

	public IncidenciaModel getIncidenciaModel() {
		return incidenciaModel;
	}

	public void setIncidenciaModel(IncidenciaModel incidenciaModel) {
		this.incidenciaModel = incidenciaModel;
	}

	public Incidencias getIncidenciasView() {
		return incidenciasView;
	}

	public void setIncidenciasView(Incidencias incidenciasView) {
		this.incidenciasView = incidenciasView;
	}

	public ConfigurarEmpleadosBo getEmpleadoBo() {
		return empleadoBo;
	}

	public void setEmpleadoBo(ConfigurarEmpleadosBo empleadoBo) {
		this.empleadoBo = empleadoBo;
	}

	public TurnoModel getTurnoModel() {
		return turnoModel;
	}

	public void setTurnoModel(TurnoModel turnoModel) {
		this.turnoModel = turnoModel;
	}

	public ConsultaAreasModel getAreasModel() {
		return areasModel;
	}

	public void setAreasModel(ConsultaAreasModel areasModel) {
		this.areasModel = areasModel;
	}

	public EmpleadoModel getConfigurarEmpleadosModel() {
		return configurarEmpleadosModel;
	}

	public void setConfigurarEmpleadosModel(EmpleadoModel configurarEmpleadosModel) {
		this.configurarEmpleadosModel = configurarEmpleadosModel;
	}
	public SolicitudReporteModel getSolicitudReporteModel() {
		return solicitudReporteModel;
	}

	public void setSolicitudReporteModel(SolicitudReporteModel solicitudReporteModel) {
		this.solicitudReporteModel = solicitudReporteModel;
	}
	//

	// Se abre la ventanay



	public void abrirVentanaIncidencias() {
		if (getIncidenciasView().getBtnConsultar().getActionListeners().length == 0) {
			getIncidenciasView().getBtnConsultar().addActionListener(this);
			getIncidenciasView().getBtnLimpiar().addActionListener(this);
		}
		getIncidenciasView().setVisible(true);
		getIncidenciasView().toFront();
		openMethods();
	}
	//

	@Override
	public void itemStateChanged(ItemEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(getIncidenciasView().getBtnConsultar())) {
			JOptionPane.showMessageDialog(null, "Entro al boton de consulta");
		} else if (e.getSource().equals(getIncidenciasView().getBtnLimpiar())) {
			JOptionPane.showMessageDialog(null, "Entro a limpiar ventana");
		}
	}

	public void openMethods() {
		consultarAreas();
		consultaGeneral();
	}

	@SuppressWarnings({ "rawtypes", "serial" })
	private void consultaGeneral() {
		List<EmpleadoModel> listaEmpleados = new ArrayList<EmpleadoModel>();
		try {
			listaEmpleados = getEmpleadoBo().consultaGeneralEmpleados();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		final Iterator<EmpleadoModel> empleados = listaEmpleados.iterator();
		String[] columnNames = { "idEmpleado", "Nombre del empleado", "Area", "Turno", "Modificar", "Eliminar",
				"Reporte", "Mas" };

		final Class[] tiposColumnas = new Class[] { java.lang.String.class, java.lang.String.class,
				java.lang.String.class, java.lang.String.class, JButton.class, JButton.class, JButton.class,
				JButton.class };

		getIncidenciasView().getTable().setModel(new javax.swing.table.DefaultTableModel() {
			Class[] tipos = tiposColumnas;

			@SuppressWarnings("unchecked")
			public Class getColumnClass(int columnIndex) {
				return tipos[columnIndex];
			}

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});

		getIncidenciasView().getTable().setDefaultRenderer(JButton.class, new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado,
					boolean tieneElFoco, int fila, int columna) {
				return (Component) objeto;
			}
		});

		if (getIncidenciasView().getTable().getMouseListeners().length == 2) {
			getIncidenciasView().getTable().addMouseListener(new MouseAdapter() {
				String idEmpleado;
				String nombreEmpleado;
				String nombreArea;
				String turno;
				Object boton;
				private String deleteEmpleado;
				private String deleteDireccion;

				public void mouseClicked(MouseEvent e) {
					int fila = getIncidenciasView().getTable().rowAtPoint(e.getPoint());
					int columna = getIncidenciasView().getTable().columnAtPoint(e.getPoint());

					if (getIncidenciasView().getTable().getModel().getColumnClass(columna).equals(JButton.class)) {
						boton = getIncidenciasView().getTable().getModel().getValueAt(fila, columna);
						StringBuilder sb = new StringBuilder();
						for (int i = 0; i < getIncidenciasView().getTable().getModel().getColumnCount(); i++) {
							if (!getIncidenciasView().getTable().getModel().getColumnClass(i).equals(JButton.class)) {
								sb.append("\n").append(getIncidenciasView().getTable().getModel().getColumnName(i))
										.append(": ")
										.append(getIncidenciasView().getTable().getModel().getValueAt(fila, i));
								if (getIncidenciasView().getTable().getModel().getColumnName(i) == "idEmpleado") {
									idEmpleado = getIncidenciasView().getTable().getModel().getValueAt(fila, i)
											.toString();
								}
								if (getIncidenciasView().getTable().getModel()
										.getColumnName(i) == "Nombre del empleado") {
									nombreEmpleado = getIncidenciasView().getTable().getModel().getValueAt(fila, i)
											.toString();
								}
								if (getIncidenciasView().getTable().getModel().getColumnName(i) == "Area") {
									nombreArea = getIncidenciasView().getTable().getModel().getValueAt(fila, i)
											.toString();
								}
								if (getIncidenciasView().getTable().getModel().getColumnName(i) == "Turno") {
									turno = getIncidenciasView().getTable().getModel().getValueAt(fila, i).toString();
								}
							}
						}
						// Boton Modificar--------
						if (boton.toString().contains("Modificar") == true) {
							EmpleadoModel empleadoModel = new EmpleadoModel();
							try {
								empleadoModel = getEmpleadoBo().consultaModificarEmpleado(idEmpleado);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							abrirVentanaModificar(empleadoModel);

							return;
							// Boton Eliminar------
						} else if (boton.toString().contains("Eliminar") == true) {
							if (JOptionPane.showConfirmDialog(null,
									"Seguro de Eliminar el empleado :" + nombreEmpleado) == 0) {
								configurarEmpleadosModel.setIdEmpleado(Integer.parseInt(idEmpleado));
								try {
									configurarEmpleadosModel = getEmpleadoBo().consultaModificarEmpleado(idEmpleado);
									deleteEmpleado = getEmpleadoBo().eliminarEmpleado(getConfigurarEmpleadosModel());
									deleteDireccion = getEmpleadoBo().eliminarDireccion(configurarEmpleadosModel);
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
								if (deleteEmpleado.equals("El empleado fue eliminado correctamente!")) {
									JOptionPane.showMessageDialog(null, deleteEmpleado, "Eliminar empleado",
											JOptionPane.INFORMATION_MESSAGE);
								} else {
									JOptionPane.showMessageDialog(null, deleteEmpleado, "Eliminar empleado",
											JOptionPane.WARNING_MESSAGE);
								}
								consultaGeneral();
							}
							return;
						} else if (boton.toString().contains("Reporte") == true) {
							getSolicitudReporteModel().setIdEmpleado(Integer.parseInt(idEmpleado));
							getSolicitudReporteModel().setNombreEmpleado(nombreEmpleado);
							getSolicitudReporteModel().setNombreArea(nombreArea);
							getSolicitudReporteModel().setTurno(turno);
							getSolicitudReporteModel().setBandera("Empleados");
							getGoverment().mostrarVistaSolicitudReporte();
						} else if (boton.toString().contains("Mas") == true) {
							EmpleadoModel empleadoModel = new EmpleadoModel();
							try {
								empleadoModel = getEmpleadoBo().consultaModificarEmpleado(idEmpleado);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							abrirVentanaMas(empleadoModel);
							getGoverment().getRegistrarEmpleadoController().getConfigurarEmpleadosModel()
									.setIdEmpleado(Integer.parseInt(idEmpleado));
							return;
						}
					}
				}

				private void abrirVentanaModificar(EmpleadoModel empleadoModel) {
					getGoverment().mostrarVistaRegistro();
					String fecha = transformarFecha(empleadoModel.getFechaNacimiento());
					Date fechaInsert = convertirFecha(fecha);
					cargarvaloresDiaPago(empleadoModel);
					DireccionModelo direccionModelo = consultarDireccionEmpleado(
							empleadoModel.getIdDireccionEmpleado());
					TurnoModel turnoModel = consultarAreaTurnoEmpleado(empleadoModel.getIdTurno());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtNombres()
							.setText(empleadoModel.getNombreEmpleado());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtApePat()
							.setText(empleadoModel.getApePatEmpleado());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtApeMat()
							.setText(empleadoModel.getApeMatEmpleado());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtTelCel()
							.setText(empleadoModel.getTelCel());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtTelCasa()
							.setText(empleadoModel.getTelCasa());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbPuesto()
							.setSelectedItem(empleadoModel.getPuesto());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getFecha()
							.setDate(fechaInsert);
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbPeriodoNominal()
							.setSelectedItem(empleadoModel.getPeriodoNominal());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina()
							.setSelectedItem(Integer.toString(empleadoModel.getDiaDePago()));
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina()
							.setEnabled(true);
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtCp()
							.setText(direccionModelo.getCp());
					getGoverment().getRegistrarEmpleadoController().llenarDireccion(direccionModelo.getCp());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbColonia()
							.setSelectedItem(direccionModelo.getUniColonia());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtCalle()
							.setText(direccionModelo.getCalle());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtNumeroExt()
							.setText(direccionModelo.getNumEx());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtNumeroInt()
							.setText(direccionModelo.getNumIn());
					getGoverment().getRegistrarEmpleadoController().llenarComboArea();
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbArea()
							.setSelectedItem(turnoModel.getArea());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbTurno()
							.setSelectedItem(turnoModel.getNombreTurno());
					ImageIcon fotografia = new ImageIcon(
							"src/main/resources/img/fotosempleados/" + empleadoModel.getFotografia());
					Icon icono = new ImageIcon(fotografia.getImage().getScaledInstance(
							getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView()
									.getLblFotografia().getWidth(),
							getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView()
									.getLblFotografia().getHeight(),
							Image.SCALE_DEFAULT));
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getLblFotografia()
							.setIcon(icono);

					// Huella
					ImageIcon huella = new ImageIcon("src/main/resources/img/huella.png");
					Icon iconhuella = new ImageIcon(huella.getImage().getScaledInstance(
							getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView()
									.getLblHuellaDigital().getWidth(),
							getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView()
									.getLblHuellaDigital().getHeight(),
							Image.SCALE_DEFAULT));
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getLblHuellaDigital()
							.setIcon(iconhuella);

					//

					getGoverment().getRegistrarEmpleadoController().getConfigurarEmpleadosModel()
							.setFotografia(empleadoModel.getFotografia());
					getGoverment().getRegistrarEmpleadoController().getConfigurarEmpleadosModel()
							.setHuellaEmpleado(empleadoModel.getHuellaEmpleado());
					getGoverment().getRegistrarEmpleadoController().getConfigurarEmpleadosModel()
							.setIdEmpleado(empleadoModel.getIdEmpleado());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getBtnCapturarFoto()
							.setText("Cambiar Foto");
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getBtnRegistrar()
							.setText("Modificar");
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getBtnLeerHuella()
							.setText("Cambiar Huella");
					nombreInicial = getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView()
							.getTxtNombres().getText()
							+ " "
							+ getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtApePat()
									.getText()
							+ " " + getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView()
									.getTxtApeMat().getText();
				}

				public void abrirVentanaMas(EmpleadoModel empleadoModel) {
					getGoverment().mostrarVistaRegistro();
					String fecha = transformarFecha(empleadoModel.getFechaNacimiento());
					Date fechaInsert = convertirFecha(fecha);
					cargarvaloresDiaPago(empleadoModel);
					DireccionModelo direccionModelo = consultarDireccionEmpleado(
							empleadoModel.getIdDireccionEmpleado());
					TurnoModel turnoModel = consultarAreaTurnoEmpleado(empleadoModel.getIdTurno());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtNombres()
							.setText(empleadoModel.getNombreEmpleado());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtNombres()
							.setEditable(false);
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtApePat()
							.setText(empleadoModel.getApePatEmpleado());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtApePat()
							.setEditable(false);
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtApeMat()
							.setText(empleadoModel.getApeMatEmpleado());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtApeMat()
							.setEditable(false);
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtTelCel()
							.setText(empleadoModel.getTelCel());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtTelCel()
							.setEditable(false);
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtTelCasa()
							.setText(empleadoModel.getTelCasa());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtTelCasa()
							.setEditable(false);
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbPuesto()
							.setSelectedItem(empleadoModel.getPuesto());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbPuesto()
							.setEnabled(false);
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getFecha()
							.setDate(fechaInsert);
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getFecha()
							.setEnabled(false);
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbPeriodoNominal()
							.setSelectedItem(empleadoModel.getPeriodoNominal());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbPeriodoNominal()
							.setEnabled(false);
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina()
							.setEnabled(true);
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina()
							.setSelectedItem(Integer.toString(empleadoModel.getDiaDePago()));
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina()
							.setEnabled(false);
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtCp()
							.setText(direccionModelo.getCp());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtCp()
							.setEditable(false);
					getGoverment().getRegistrarEmpleadoController().llenarDireccion(direccionModelo.getCp());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbColonia()
							.setSelectedItem(direccionModelo.getUniColonia());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbColonia()
							.setEnabled(false);
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtCalle()
							.setText(direccionModelo.getCalle());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtCalle()
							.setEditable(false);
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtNumeroExt()
							.setText(direccionModelo.getNumEx());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtNumeroExt()
							.setEditable(false);
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtNumeroInt()
							.setText(direccionModelo.getNumIn());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtNumeroInt()
							.setEditable(false);
					getGoverment().getRegistrarEmpleadoController().llenarComboArea();
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbArea()
							.setSelectedItem(turnoModel.getArea());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbArea()
							.setEnabled(false);
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbTurno()
							.setSelectedItem(turnoModel.getNombreTurno());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbTurno()
							.setEnabled(false);
					ImageIcon fotografia = new ImageIcon(
							"src/main/resources/img/fotosempleados/" + empleadoModel.getFotografia());
					Icon icono = new ImageIcon(fotografia.getImage().getScaledInstance(
							getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView()
									.getLblFotografia().getWidth(),
							getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView()
									.getLblFotografia().getHeight(),
							Image.SCALE_DEFAULT));
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getLblFotografia()
							.setIcon(icono);

					// Huella
					ImageIcon huella = new ImageIcon("src/main/resources/img/huella.png");
					Icon iconhuella = new ImageIcon(huella.getImage().getScaledInstance(
							getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView()
									.getLblHuellaDigital().getWidth(),
							getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView()
									.getLblHuellaDigital().getHeight(),
							Image.SCALE_DEFAULT));
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getLblHuellaDigital()
							.setIcon(iconhuella);

					//

					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getBtnCapturarFoto()
							.setVisible(false);
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getBtnLeerHuella()
							.setVisible(true);
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getBtnLeerHuella()
							.setText("Recuperar user/pass");
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getBtnRegistrar()
							.setVisible(false);
				}

				private TurnoModel consultarAreaTurnoEmpleado(int idTurno) {
					TurnoModel turnomodel = new TurnoModel();
					try {
						turnomodel = getEmpleadoBo().consultarAreaEmpleado(idTurno);
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					return turnomodel;
				}

				private DireccionModelo consultarDireccionEmpleado(int idDireccionEmpleado) {
					DireccionModelo direccion = getGoverment().getRegistrarEmpleadoController().getDireccionModelo();
					try {
						direccion = getEmpleadoBo().consultarCpDireccionEmpleado(idDireccionEmpleado);
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					return direccion;
				}

				private void cargarvaloresDiaPago(EmpleadoModel empleadoModel) {
					if (empleadoModel.getPeriodoNominal().equals("Semanal")) {
						getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina()
								.addItem("1");
						getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina()
								.addItem("8");
						getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina()
								.addItem("15");
						getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina()
								.addItem("23");
						getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina()
								.addItem("30");
						getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina()
								.setEnabled(true);
						getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina()
								.setSelectedItem(Integer.toString(empleadoModel.getDiaDePago()));
					} else if (empleadoModel.getPeriodoNominal().equals("Quincenal")) {
						getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina()
								.addItem("1");
						getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina()
								.addItem("15");
						getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina()
								.addItem("30");
						getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina()
								.setEnabled(true);
						getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina()
								.setSelectedItem(Integer.toString(empleadoModel.getDiaDePago()));
					} else if (empleadoModel.getPeriodoNominal().equals("Mensual")) {
						getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina()
								.addItem("1");
						getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina()
								.addItem("30");
						getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina()
								.setEnabled(true);
						getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina()
								.setSelectedItem(Integer.toString(empleadoModel.getDiaDePago()));
					}

				}

				private Date convertirFecha(String fecha) {
					SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
					Date date = new Date();
					try {
						date = formato.parse(fecha);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					// System.out.println(date);
					// System.out.println(formato.format(date));
					return date;
				}

				private String transformarFecha(String fecha) {
					String ano, mes, dia;
					String fechaArray[] = fecha.split("-");
					ano = fechaArray[0];
					mes = fechaArray[1];
					dia = fechaArray[2];
					if (dia.length() == 1) {
						fecha = "0" + dia + "/" + mes + "/" + ano;
					} else {
						fecha = dia + "/" + mes + "/" + ano;
					}
					return fecha;
				}
			});
		}

		DefaultTableModel modelo = (DefaultTableModel) getIncidenciasView().getTable().getModel();
		modelo.setColumnIdentifiers(columnNames);
		Object[] fila = new Object[modelo.getColumnCount()];
		while (empleados.hasNext()) {
			EmpleadoModel empleado = empleados.next();
			JButton btn = new JButton("Modificar");
			JButton btn2 = new JButton("Eliminar");
			JButton btn3 = new JButton("Reporte");
			JButton btn4 = new JButton("Mas");
			fila[0] = empleado.getIdEmpleado();
			fila[1] = empleado.getNombreEmpleado() + " " + empleado.getApePatEmpleado() + " "
					+ empleado.getApeMatEmpleado();
			fila[2] = empleado.getArea();
			fila[3] = empleado.getNombreTurno();
			fila[4] = btn;
			fila[5] = btn2;
			fila[6] = btn3;
			fila[7] = btn4;
			modelo.addRow(fila);
		}
		getIncidenciasView().getTable().getColumnModel().getColumn(0).setWidth(0);
		getIncidenciasView().getTable().getColumnModel().getColumn(0).setMinWidth(0);
		getIncidenciasView().getTable().getColumnModel().getColumn(0).setMaxWidth(0);
		getIncidenciasView().getTable().getColumnModel().getColumn(1).setPreferredWidth(200);
		getIncidenciasView().getTable().getColumnModel().getColumn(2).setPreferredWidth(200);
		getIncidenciasView().getTable().getColumnModel().getColumn(3).setPreferredWidth(200);
		getIncidenciasView().getTable().getColumnModel().getColumn(4).setPreferredWidth(100);
		getIncidenciasView().getTable().getColumnModel().getColumn(5).setPreferredWidth(100);
		getIncidenciasView().getTable().getColumnModel().getColumn(6).setPreferredWidth(100);
		getIncidenciasView().getTable().getColumnModel().getColumn(7).setPreferredWidth(100);
	}

	private void limpiarArea() {
		int c = getIncidenciasView().getCmbArea().getItemCount();
		for (int x = 0; x < c; x++) {
			if (x != c - 1) {
				getIncidenciasView().getCmbArea().removeItemAt(1);
			}
		}

	}

	private void consultarAreas() {
		limpiarArea();
		List<EmpleadoModel> lista = new ArrayList<EmpleadoModel>();
		try {
			lista = getEmpleadoBo().consultaGeneral();
			Iterator<EmpleadoModel> listaIterator = lista.iterator();
			while (listaIterator.hasNext()) {

				EmpleadoModel listai = listaIterator.next();
				getIncidenciasView().getCmbArea().addItem(listai.getArea());
			}
		} catch (SQLException t) {
			JOptionPane.showMessageDialog(null, t.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void llenarTurnos() {
		limpiarTurnos();
		String area = (String) getIncidenciasView().getCmbArea().getSelectedItem();
		getTurnoModel().setArea(area);
		List<TurnoModel> listaTurno = new ArrayList<TurnoModel>();
		try {
			listaTurno = getEmpleadoBo().consultarTurno();
			Iterator<TurnoModel> listaIterator = listaTurno.iterator();
			while (listaIterator.hasNext()) {
				TurnoModel listai = listaIterator.next();
				getIncidenciasView().getCmbTurno().addItem(listai.getNombreTurno());
				// conocerIdArea();
			}
		} catch (SQLException t) {
			JOptionPane.showMessageDialog(null, t.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void limpiarTurnos() {
		getIncidenciasView().getCmbTurno().removeAllItems();
		getIncidenciasView().getCmbTurno().addItem("-----Seleccionar turno-----");
	}

}
