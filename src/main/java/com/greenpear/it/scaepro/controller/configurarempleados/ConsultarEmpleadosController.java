package com.greenpear.it.scaepro.controller.configurarempleados;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.greenpear.it.scaepro.bo.configurarempleados.ConfigurarEmpleadosBo;
import com.greenpear.it.scaepro.controller.government.GovernmentService;
import com.greenpear.it.scaepro.model.direccion.DireccionModelo;
import com.greenpear.it.scaepro.model.empleado.EmpleadoModel;
import com.greenpear.it.scaepro.model.gestionareas.ConsultaAreasModel;
import com.greenpear.it.scaepro.model.solicitudreporte.SolicitudReporteModel;
import com.greenpear.it.scaepro.model.turno.TurnoModel;
import com.greenpear.it.scaepro.view.configurarempleados.ConsultarEmpleado;

import jdk.nashorn.internal.scripts.JO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

@Controller

public class ConsultarEmpleadosController implements ActionListener, WindowListener, ItemListener {

	public ConsultarEmpleadosController() {
		super();
	}

	// Government
	@Autowired
	private GovernmentService goverment;
	
	// Modelos
	@Autowired
	private EmpleadoModel configurarEmpleadosModel;
	
	@Autowired
	private TurnoModel turnoModelo;
	
	@Autowired
	private SolicitudReporteModel solicitudReporteModel;

	// Vistas
	@Autowired
	ConsultarEmpleado ConsultaEmpleadoView;
	// Bo
	@Autowired
	@Qualifier("empleadosBoService")
	private ConfigurarEmpleadosBo empleadosBo;

	// Setter and Getters
	public ConsultarEmpleado getConsultaEmpleadoView() {
		return ConsultaEmpleadoView;
	}

	public GovernmentService getGoverment() {
		return goverment;
	}

	public ConfigurarEmpleadosBo getEmpleadosBo() {
		return empleadosBo;
	}

	public EmpleadoModel getConfigurarEmpleadosModel() {
		return configurarEmpleadosModel;
	}

	public void setConfigurarEmpleadosModel(EmpleadoModel configurarEmpleadosModel) {
		this.configurarEmpleadosModel = configurarEmpleadosModel;
	}

	public TurnoModel getTurnoModelo() {
		return turnoModelo;
	}

	public void setTurnoModelo(TurnoModel turnoModelo) {
		this.turnoModelo = turnoModelo;
	}
	
	public SolicitudReporteModel getSolicitudReporteModel() {
		return solicitudReporteModel;
	}

	// End Setter and Getters

	public void mostrarVistaConsultaEmpleado() {
		if (getConsultaEmpleadoView().getBtnConsultar().getActionListeners().length == 0) {
			getConsultaEmpleadoView().getBtnConsultar().addActionListener(this);
			getConsultaEmpleadoView().getBtnLimpiar().addActionListener(this);
			getConsultaEmpleadoView().getCmbArea().addItemListener(this);
			getConsultaEmpleadoView().getCmbPuesto().addItemListener(this);
			getConsultaEmpleadoView().getCmbTurno().addItemListener(this);
//			getConsultaEmpleadoView().addWindowListener(this);
		}
		
		getConsultaEmpleadoView().setVisible(true);
		getConsultaEmpleadoView().toFront();
		ventanaOpen();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource().equals(getConsultaEmpleadoView().getCmbArea())) {
			if (!getConsultaEmpleadoView().getCmbArea().getSelectedItem().equals("------Seleccione area------")) {
				getConsultaEmpleadoView().getCmbTurno().setEnabled(true);
				llenarTurnos();
			} else {
				getConsultaEmpleadoView().getCmbTurno().setEnabled(false);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(getConsultaEmpleadoView().getBtnConsultar())) {
			JOptionPane.showMessageDialog(null, "Que empiece lo cabron");
		}

	}

	private void consultarAreas() {
		getConsultaEmpleadoView().getCmbArea().addItem("------Seleccione area------");
		List<EmpleadoModel> lista = new ArrayList<EmpleadoModel>();
		try {
			lista = getEmpleadosBo().consultaGeneral();
			Iterator<EmpleadoModel> listaIterator = lista.iterator();
			while (listaIterator.hasNext()) {

				EmpleadoModel listai = listaIterator.next();
				getConsultaEmpleadoView().getCmbArea().addItem(listai.getArea());
			}
		} catch (SQLException t) {
			JOptionPane.showMessageDialog(null, t.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void llenarTurnos() {
		limpiarTurnos();
		String area = (String) getConsultaEmpleadoView().getCmbArea().getSelectedItem();
		getTurnoModelo().setArea(area);
		List<TurnoModel> listaTurno = new ArrayList<TurnoModel>();
		try {
			listaTurno = getEmpleadosBo().consultarTurno();
			Iterator<TurnoModel> listaIterator = listaTurno.iterator();
			while (listaIterator.hasNext()) {
				TurnoModel listai = listaIterator.next();
				getConsultaEmpleadoView().getCmbTurno().addItem(listai.getNombreTurno());
				// conocerIdArea();
			}
		} catch (SQLException t) {
			JOptionPane.showMessageDialog(null, t.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void limpiarTurnos() {

		getConsultaEmpleadoView().getCmbTurno().removeAllItems();
		getConsultaEmpleadoView().getCmbTurno().addItem("--------Seleccione un turno--------");
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		consultarAreas();
		consultaGeneral();
	}
	
	public void ventanaOpen(){
		consultarAreas();
		consultaGeneral();
	}

	@SuppressWarnings({ "rawtypes", "serial" })
	private void consultaGeneral() {
		List<EmpleadoModel> listaEmpleados = new ArrayList<EmpleadoModel>();
		try {
			listaEmpleados = getEmpleadosBo().consultaGeneralEmpleados();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		final Iterator<EmpleadoModel> empleados = listaEmpleados.iterator();
		String[] columnNames = { "idEmpleado","Nombre del empleado", "Area","Turno","Modificar", "Eliminar","Reporte","Mas" };

		final Class[] tiposColumnas = new Class[] {java.lang.String.class, java.lang.String.class, java.lang.String.class,java.lang.String.class, JButton.class,
				JButton.class,JButton.class,JButton.class };

		getConsultaEmpleadoView().getTable().setModel(new javax.swing.table.DefaultTableModel() {
			Class[] tipos = tiposColumnas;

			@SuppressWarnings("unchecked")
			public Class getColumnClass(int columnIndex) {
				return tipos[columnIndex];
			}

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});

		getConsultaEmpleadoView().getTable().setDefaultRenderer(JButton.class, new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado,
					boolean tieneElFoco, int fila, int columna) {
				return (Component) objeto;
			}
		});

		if (getConsultaEmpleadoView().getTable().getMouseListeners().length == 2) {
			getConsultaEmpleadoView().getTable().addMouseListener(new MouseAdapter() {
				String idEmpleado;
				String nombreEmpleado;
				String nombreArea;
				String turno;
				Object boton;
				private String delete;

				public void mouseClicked(MouseEvent e) {
					int fila = getConsultaEmpleadoView().getTable().rowAtPoint(e.getPoint());
					int columna = getConsultaEmpleadoView().getTable().columnAtPoint(e.getPoint());

					if (getConsultaEmpleadoView().getTable().getModel().getColumnClass(columna).equals(JButton.class)) {
						boton = getConsultaEmpleadoView().getTable().getModel().getValueAt(fila, columna);
						StringBuilder sb = new StringBuilder();
						for (int i = 0; i < getConsultaEmpleadoView().getTable().getModel().getColumnCount(); i++) {
							if (!getConsultaEmpleadoView().getTable().getModel().getColumnClass(i).equals(JButton.class)) {
								sb.append("\n").append(getConsultaEmpleadoView().getTable().getModel().getColumnName(i)).append(": ")
										.append(getConsultaEmpleadoView().getTable().getModel().getValueAt(fila, i));
								if (getConsultaEmpleadoView().getTable().getModel().getColumnName(i) == "idEmpleado") {
									idEmpleado = getConsultaEmpleadoView().getTable().getModel().getValueAt(fila, i).toString();
								}
								if (getConsultaEmpleadoView().getTable().getModel().getColumnName(i) == "Nombre del empleado") {
									nombreEmpleado = getConsultaEmpleadoView().getTable().getModel().getValueAt(fila, i).toString();
								}
								if (getConsultaEmpleadoView().getTable().getModel().getColumnName(i) == "Area") {
									nombreArea = getConsultaEmpleadoView().getTable().getModel().getValueAt(fila, i).toString();
								}
								if (getConsultaEmpleadoView().getTable().getModel().getColumnName(i) == "Turno") {
									turno = getConsultaEmpleadoView().getTable().getModel().getValueAt(fila, i).toString();
								}
							}
						}
						// Boton Modificar--------
						if (boton.toString().contains("Modificar") == true) {
							EmpleadoModel empleadoModel = new EmpleadoModel();
							try {
								empleadoModel = getEmpleadosBo().consultaModificarEmpleado(idEmpleado);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							abrirVentanaModificar(empleadoModel);
							
							return;
							// Boton Eliminar------
						} else if (boton.toString().contains("Eliminar") == true) {
							if (JOptionPane.showConfirmDialog(null, "Seguro de Eliminar el empleado :" + nombreEmpleado) == 0) {
								configurarEmpleadosModel.setIdEmpleado(Integer.parseInt(idEmpleado));
								try {
									delete = getEmpleadosBo().eliminarEmpleado(getConfigurarEmpleadosModel());
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
								if (delete.equals("El empleado fue eliminado correctamente!")) {
									JOptionPane.showMessageDialog(null, delete, "Eliminar empleado",
											JOptionPane.INFORMATION_MESSAGE);
								} else {
									JOptionPane.showMessageDialog(null, delete, "Eliminar empleado",
											JOptionPane.WARNING_MESSAGE);
								}
								consultaGeneral();
							}
							return;
						}
						else if (boton.toString().contains("Reporte") == true) {
							getSolicitudReporteModel().setIdEmpleado(Integer.parseInt(idEmpleado));
							getSolicitudReporteModel().setNombreEmpleado(nombreEmpleado);
							getSolicitudReporteModel().setNombreArea(nombreArea);
							getSolicitudReporteModel().setTurno(turno);
							getSolicitudReporteModel().setBandera("Empleados");
							getGoverment().mostrarVistaSolicitudReporte();
						}
						else if (boton.toString().contains("Mas") == true) {
							EmpleadoModel empleadoModel = new EmpleadoModel();
							try {
								empleadoModel = getEmpleadosBo().consultaModificarEmpleado(idEmpleado);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							abrirVentanaMas(empleadoModel);
							return;						
						}
					}
				}

				private void abrirVentanaModificar(EmpleadoModel empleadoModel) {
					getGoverment().mostrarVistaRegistro();
					String fecha=transformarFecha(empleadoModel.getFechaNacimiento());
					Date fechaInsert=convertirFecha(fecha);
					cargarvaloresDiaPago(empleadoModel);
					DireccionModelo direccionModelo=consultarDireccionEmpleado(empleadoModel.getIdDireccionEmpleado());
					TurnoModel turnoModel=consultarAreaTurnoEmpleado(empleadoModel.getIdTurno());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtNombres().setText(empleadoModel.getNombreEmpleado());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtApePat().setText(empleadoModel.getApePatEmpleado());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtApeMat().setText(empleadoModel.getApeMatEmpleado());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtTelCel().setText(empleadoModel.getTelCel());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtTelCasa().setText(empleadoModel.getTelCasa());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbPuesto().setSelectedItem(empleadoModel.getPuesto());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getFecha().setDate(fechaInsert);
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbPeriodoNominal().setSelectedItem(empleadoModel.getPeriodoNominal());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina().setSelectedItem(Integer.toString(empleadoModel.getDiaDePago()));
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina().setEnabled(true);
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtCp().setText(direccionModelo.getCp());
					getGoverment().getRegistrarEmpleadoController().llenarDireccion(direccionModelo.getCp());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbColonia().setSelectedItem(direccionModelo.getUniColonia());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtCalle().setText(direccionModelo.getCalle());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtNumeroExt().setText(direccionModelo.getNumEx());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtNumeroInt().setText(direccionModelo.getNumIn());
					getGoverment().getRegistrarEmpleadoController().llenarComboArea();
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbArea().setSelectedItem(turnoModel.getArea());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbTurno().setSelectedItem(turnoModel.getNombreTurno());
					ImageIcon fotografia=new ImageIcon("src/main/resources/img/fotosempleados/"+empleadoModel.getFotografia());
					Icon icono=new ImageIcon(fotografia.getImage().getScaledInstance(getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getLblFotografia().getWidth(),getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getLblFotografia().getHeight() , Image.SCALE_DEFAULT));
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getLblFotografia().setIcon(icono);
					
					//Huella
					ImageIcon huella=new ImageIcon("src/main/resources/img/huella.png");
					Icon iconhuella=new ImageIcon(huella.getImage().getScaledInstance(getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getLblHuellaDigital().getWidth(),getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getLblHuellaDigital().getHeight() , Image.SCALE_DEFAULT));
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getLblHuellaDigital().setIcon(iconhuella);

					//
					
					getGoverment().getRegistrarEmpleadoController().getConfigurarEmpleadosModel().setFotografia(empleadoModel.getFotografia());
					getGoverment().getRegistrarEmpleadoController().getConfigurarEmpleadosModel().setHuellaEmpleado(empleadoModel.getHuellaEmpleado());
					getGoverment().getRegistrarEmpleadoController().getConfigurarEmpleadosModel().setIdEmpleado(empleadoModel.getIdEmpleado());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getBtnCapturarFoto().setText("Cambiar Foto");
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getBtnRegistrar().setText("Modificar");
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getBtnLeerHuella().setText("Cambiar Huella");
				}
				public void abrirVentanaMas(EmpleadoModel empleadoModel) {
					getGoverment().mostrarVistaRegistro();
					String fecha=transformarFecha(empleadoModel.getFechaNacimiento());
					Date fechaInsert=convertirFecha(fecha);
					cargarvaloresDiaPago(empleadoModel);
					DireccionModelo direccionModelo=consultarDireccionEmpleado(empleadoModel.getIdDireccionEmpleado());
					TurnoModel turnoModel=consultarAreaTurnoEmpleado(empleadoModel.getIdTurno());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtNombres().setText(empleadoModel.getNombreEmpleado());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtNombres().setEditable(false);
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtApePat().setText(empleadoModel.getApePatEmpleado());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtApePat().setEditable(false);
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtApeMat().setText(empleadoModel.getApeMatEmpleado());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtApeMat().setEditable(false);
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtTelCel().setText(empleadoModel.getTelCel());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtTelCel().setEditable(false);
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtTelCasa().setText(empleadoModel.getTelCasa());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtTelCasa().setEditable(false);
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbPuesto().setSelectedItem(empleadoModel.getPuesto());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbPuesto().setEditable(false);
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getFecha().setDate(fechaInsert);
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getFecha().setEnabled(false);
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbPeriodoNominal().setSelectedItem(empleadoModel.getPeriodoNominal());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbPeriodoNominal().setEnabled(false);
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina().setEnabled(true);
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina().setSelectedItem(Integer.toString(empleadoModel.getDiaDePago()));
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina().setEnabled(false);
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtCp().setText(direccionModelo.getCp());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtCp().setEditable(false);
					getGoverment().getRegistrarEmpleadoController().llenarDireccion(direccionModelo.getCp());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbColonia().setSelectedItem(direccionModelo.getUniColonia());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbColonia().setEnabled(false);
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtCalle().setText(direccionModelo.getCalle());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtCalle().setEditable(false);
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtNumeroExt().setText(direccionModelo.getNumEx());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtNumeroExt().setEditable(false);
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtNumeroInt().setText(direccionModelo.getNumIn());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getTxtNumeroInt().setEditable(false);
					getGoverment().getRegistrarEmpleadoController().llenarComboArea();
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbArea().setSelectedItem(turnoModel.getArea());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbArea().setEnabled(false);
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbTurno().setSelectedItem(turnoModel.getNombreTurno());
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbTurno().setEnabled(false);
					ImageIcon fotografia=new ImageIcon("src/main/resources/img/fotosempleados/"+empleadoModel.getFotografia());
					Icon icono=new ImageIcon(fotografia.getImage().getScaledInstance(getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getLblFotografia().getWidth(),getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getLblFotografia().getHeight() , Image.SCALE_DEFAULT));
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getLblFotografia().setIcon(icono);
					
					//Huella
					ImageIcon huella=new ImageIcon("src/main/resources/img/huella.png");
					Icon iconhuella=new ImageIcon(huella.getImage().getScaledInstance(getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getLblHuellaDigital().getWidth(),getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getLblHuellaDigital().getHeight() , Image.SCALE_DEFAULT));
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getLblHuellaDigital().setIcon(iconhuella);

					//
					
					
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getBtnCapturarFoto().setVisible(false);
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getBtnLeerHuella().setVisible(false);
					getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getBtnRegistrar().setVisible(false);
				}

				private TurnoModel consultarAreaTurnoEmpleado(int idTurno) {
					TurnoModel turnomodel=new TurnoModel();
					try{
						turnomodel=getEmpleadosBo().consultarAreaEmpleado(idTurno);
					}catch(SQLException e){
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					return turnomodel;
				}

				private DireccionModelo consultarDireccionEmpleado(int idDireccionEmpleado) {
					DireccionModelo direccion=getGoverment().getRegistrarEmpleadoController().getDireccionModelo();
					try{
						direccion=getEmpleadosBo().consultarCpDireccionEmpleado(idDireccionEmpleado);
					}catch(SQLException e){
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					return direccion;
				}

				private void cargarvaloresDiaPago(EmpleadoModel empleadoModel) {
					if (empleadoModel.getPeriodoNominal().equals("Semanal")) {
						getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina().addItem("1");
						getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina().addItem("8");
						getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina().addItem("15");
						getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina().addItem("23");
						getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina().addItem("30");
						getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina().setEnabled(true);
						getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina().setSelectedItem(Integer.toString(empleadoModel.getDiaDePago()));
					} else if (empleadoModel.getPeriodoNominal().equals("Quincenal")) {
						getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina().addItem("1");
						getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina().addItem("15");
						getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina().addItem("30");
						getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina().setEnabled(true);
						getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina().setSelectedItem(Integer.toString(empleadoModel.getDiaDePago()));
					} else if (empleadoModel.getPeriodoNominal().equals("Mensual")) {
						getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina().addItem("1");
						getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina().addItem("30");
						getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina().setEnabled(true);
						getGoverment().getRegistrarEmpleadoController().getRegistrarEmpleadoView().getCmbDiaNomina().setSelectedItem(Integer.toString(empleadoModel.getDiaDePago()));
					}
					
				}

				private Date convertirFecha(String fecha) {
					SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
					Date date=new Date();
					try {
						date = formato.parse(fecha);
					} catch (ParseException e) {
						e.printStackTrace();
					}
//					System.out.println(date);
//					System.out.println(formato.format(date));
					return date;
				}

				private String transformarFecha(String fecha) {
					String ano,mes,dia;
					String fechaArray[]=fecha.split("-");
					ano=fechaArray[0];
					mes=fechaArray[1];
					dia=fechaArray[2];
					if(dia.length()==1){
						fecha="0"+dia+"/"+mes+"/"+ano;
					}else {
						fecha=dia+"/"+mes+"/"+ano;
					}
					return fecha;
				}
			});
		}

		DefaultTableModel modelo = (DefaultTableModel) getConsultaEmpleadoView().getTable().getModel();
		modelo.setColumnIdentifiers(columnNames);
		Object[] fila = new Object[modelo.getColumnCount()];
		while (empleados.hasNext()) {
			EmpleadoModel empleado = empleados.next();
			JButton btn = new JButton("Modificar");
			JButton btn2 = new JButton("Eliminar");
			JButton btn3 = new JButton("Reporte");
			JButton btn4 = new JButton("Mas");
			fila[0] = empleado.getIdEmpleado();
			fila[1] = empleado.getNombreEmpleado()+" "+empleado.getApePatEmpleado()+" "+empleado.getApeMatEmpleado();
			fila[2] = empleado.getArea();
			fila[3] = empleado.getNombreTurno();
			fila[4] = btn;		
			fila[5] = btn2;			
			fila[6] = btn3;			
			fila[7] = btn4;			
			modelo.addRow(fila);
		}
		getConsultaEmpleadoView().getTable().getColumnModel().getColumn(0).setWidth(0);
		getConsultaEmpleadoView().getTable().getColumnModel().getColumn(0).setMinWidth(0);
		getConsultaEmpleadoView().getTable().getColumnModel().getColumn(0).setMaxWidth(0);
		getConsultaEmpleadoView().getTable().getColumnModel().getColumn(1).setPreferredWidth(200);
		getConsultaEmpleadoView().getTable().getColumnModel().getColumn(2).setPreferredWidth(200);
		getConsultaEmpleadoView().getTable().getColumnModel().getColumn(3).setPreferredWidth(200);
		getConsultaEmpleadoView().getTable().getColumnModel().getColumn(4).setPreferredWidth(100);
		getConsultaEmpleadoView().getTable().getColumnModel().getColumn(5).setPreferredWidth(100);
		getConsultaEmpleadoView().getTable().getColumnModel().getColumn(6).setPreferredWidth(100);
		getConsultaEmpleadoView().getTable().getColumnModel().getColumn(7).setPreferredWidth(100);

	}

	
	}