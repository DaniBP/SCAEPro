package com.greenpear.it.scaepro.controller.justificarincidencias;

import java.awt.Component;
import java.awt.Image;
import java.awt.JobAttributes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.sql.ordering.antlr.GeneratedOrderByFragmentRendererTokenTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import com.greenpear.it.scaepro.bo.configurarempleados.ConfigurarEmpleadosBo;
import com.greenpear.it.scaepro.bo.justificarincidencias.JustificarIncidenciasBo;
import com.greenpear.it.scaepro.controller.government.GovernmentService;
import com.greenpear.it.scaepro.model.direccion.DireccionModelo;
import com.greenpear.it.scaepro.model.empleado.EmpleadoModel;
import com.greenpear.it.scaepro.model.gestionareas.ConsultaAreasModel;
import com.greenpear.it.scaepro.model.incidencia.IncidenciaModel;
import com.greenpear.it.scaepro.model.incidencia.JustificanteIncidenciaModel;
import com.greenpear.it.scaepro.model.solicitudreporte.SolicitudReporteModel;
import com.greenpear.it.scaepro.model.turno.TurnoModel;
import com.greenpear.it.scaepro.view.incidencias.Incidencias;
import com.greenpear.it.scaepro.view.incidencias.JustificarIncidencias;

@Controller
public class JustificarIncidenciasController implements ActionListener, ItemListener {

	public JustificarIncidenciasController() {
		super();
	}

	// Instancias de Clases a Utilizar
	private FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.JPG", "jpg");
	private long start;
	private long end;
	private String archive;
	private String nombreArchivo;
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
	@Autowired
	private JustificanteIncidenciaModel justificanteIncidenciaModel;
	//

	// Vistas
	@Autowired
	private Incidencias incidenciasView;
	@Autowired
	private JustificarIncidencias justificarView;

	//

	//
	@Autowired
	@Qualifier("empleadosBoService")
	private ConfigurarEmpleadosBo empleadoBo;

	@Autowired
	@Qualifier("consultarIncidenciasBo")
	private JustificarIncidenciasBo justificarIncidenciasBo;
	//

	// Otras instancias
	private String nombreInicial = "";

	public String getNombreInicial() {
		return nombreInicial;
	}

	//

	// Getters and Setters

	public GovernmentService getGoverment() {
		return goverment;
	}

	public IncidenciaModel getIncidenciaModel() {
		return incidenciaModel;
	}

	public Incidencias getIncidenciasView() {
		return incidenciasView;
	}

	public ConfigurarEmpleadosBo getEmpleadoBo() {
		return empleadoBo;
	}

	public TurnoModel getTurnoModel() {
		return turnoModel;
	}

	public ConsultaAreasModel getAreasModel() {
		return areasModel;
	}

	public EmpleadoModel getConfigurarEmpleadosModel() {
		return configurarEmpleadosModel;
	}

	public SolicitudReporteModel getSolicitudReporteModel() {
		return solicitudReporteModel;
	}

	public JustificarIncidenciasBo getJustificarIncidenciasBo() {
		return justificarIncidenciasBo;
	}

	public JustificarIncidencias getJustificarView() {
		return justificarView;
	}

	public JustificanteIncidenciaModel getJustificanteIncidenciaModel() {
		return justificanteIncidenciaModel;
	}

	//

	// Se abre la ventanay

	public void abrirVentanaIncidencias() {
		if (getIncidenciasView().getBtnConsultar().getActionListeners().length == 0) {
			getIncidenciasView().getBtnConsultar().addActionListener(this);
			getIncidenciasView().getBtnLimpiar().addActionListener(this);
			getJustificarView().getBtnAbrirImagen().addActionListener(this);
			getJustificarView().getBtnJustificar().addActionListener(this);
			getJustificarView().getBtnSubirImagen().addActionListener(this);
			getIncidenciasView().getCmbArea().addItemListener(this);
		}
		getIncidenciasView().setVisible(true);
		getIncidenciasView().toFront();
		openMethods();
	}
	//

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource().equals(getIncidenciasView().getCmbArea())) {
			if (!getIncidenciasView().getCmbArea().getSelectedItem().equals("------Seleccione area------")) {
				getIncidenciasView().getCmbTurno().setEnabled(true);
				llenarTurnos();
			} else {
				getIncidenciasView().getCmbTurno().setEnabled(false);
				getIncidenciasView().getCmbTurno().setSelectedIndex(0);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(getIncidenciasView().getBtnConsultar())) {
			if (getIncidenciasView().getTxtNombre().getText().isEmpty()
					&& getIncidenciasView().getTxtApeMat().getText().isEmpty()
					&& getIncidenciasView().getTxtApePat().getText().isEmpty()
					&& getIncidenciasView().getCmbArea().getSelectedItem().equals("------Seleccione area------")
					&& getIncidenciasView().getCmbPuesto().getSelectedItem().equals("-----Seleccione puesto-----")
					&& getIncidenciasView().getCmbTurno().getSelectedItem().equals("-----Seleccionar turno-----")) {
				JOptionPane.showMessageDialog(null, "Tienes que seleccionar un parametro de busqueda de empleado");
			} else if (!getIncidenciasView().getTxtNombre().getText().isEmpty()
					&& !getIncidenciasView().getTxtApePat().getText().isEmpty()
					&& !getIncidenciasView().getTxtApeMat().getText().isEmpty()) {
				incidenciaModel.setNombreEmpleado(getIncidenciasView().getTxtNombre().getText());
				incidenciaModel.setApePatEmpleado(getIncidenciasView().getTxtApePat().getText());
				incidenciaModel.setApeMatEmpleado(getIncidenciasView().getTxtApeMat().getText());
				List<IncidenciaModel> listaEmpleados = new ArrayList<IncidenciaModel>();
				try {
					listaEmpleados = getJustificarIncidenciasBo().consultaNombreCompletoEmpleado(incidenciaModel);
				} catch (SQLException ex) {
					System.out.println(ex.getMessage());
				}
				final Iterator<IncidenciaModel> empleados = listaEmpleados.iterator();
				String[] columnNames = { "idIncidencia", "idEmpleado", "Nombre del empleado", "Area", "Tipo", "Fecha y Hora",
						"Justificacion", "Revisar" };

				final Class[] tiposColumnas = new Class[] { java.lang.String.class, java.lang.String.class,
						java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
						java.lang.String.class, JButton.class };

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
						String idIncidencia;
						String idEmpleado;
						String nombreEmpleado;
						String nombreArea;
						String tipo;
						String fechaHora;
						String justificacion;
						Object boton;

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
										if (getIncidenciasView().getTable().getModel().getColumnName(i) == "idIncidencia") {
											idIncidencia = getIncidenciasView().getTable().getModel().getValueAt(fila, i)
													.toString();
										}
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
										if (getIncidenciasView().getTable().getModel().getColumnName(i) == "Tipo") {
											tipo = getIncidenciasView().getTable().getModel().getValueAt(fila, i).toString();
										}
										if (getIncidenciasView().getTable().getModel().getColumnName(i) == "Fecha y Hora") {
											fechaHora = getIncidenciasView().getTable().getModel().getValueAt(fila, i)
													.toString();
										}
										if (getIncidenciasView().getTable().getModel().getColumnName(i) == "Justificacion") {
											justificacion = getIncidenciasView().getTable().getModel().getValueAt(fila, i)
													.toString();
										}
									}
								}

								if (boton.toString().contains("Revisar") == true) {
									JOptionPane.showMessageDialog(null,
											"Entro a modificar del empleado " + nombreEmpleado + " con el id " + idIncidencia
													+ " " + idEmpleado + " " + nombreArea + " " + tipo + " " + justificacion
													+ " " + fechaHora);
									abrirVentanaJustificarIncidencia(idIncidencia, idEmpleado, nombreEmpleado, nombreArea, tipo,
											justificacion, fechaHora);
									return;
								}
							}
						}

					});
				}

				DefaultTableModel modelo = (DefaultTableModel) getIncidenciasView().getTable().getModel();
				modelo.setColumnIdentifiers(columnNames);
				Object[] fila = new Object[modelo.getColumnCount()];
				while (empleados.hasNext()) {
					IncidenciaModel empleado = empleados.next();
					JButton btn2 = new JButton("Revisar");
					fila[0] = empleado.getIdIncidencia();
					fila[1] = empleado.getIdEmpleado();
					fila[2] = empleado.getNombreEmpleado() + " " + empleado.getApePatEmpleado() + " "
							+ empleado.getApeMatEmpleado();
					fila[3] = empleado.getArea();
					fila[4] = empleado.getTipo();
					fila[5] = empleado.getFechaIncidencia();
					fila[6] = empleado.getEstatusIncidencia();
					fila[7] = btn2;
					modelo.addRow(fila);
				}
				getIncidenciasView().getTable().getColumnModel().getColumn(0).setWidth(0);
				getIncidenciasView().getTable().getColumnModel().getColumn(0).setMinWidth(0);
				getIncidenciasView().getTable().getColumnModel().getColumn(0).setMaxWidth(0);
				getIncidenciasView().getTable().getColumnModel().getColumn(1).setWidth(0);
				getIncidenciasView().getTable().getColumnModel().getColumn(1).setMinWidth(0);
				getIncidenciasView().getTable().getColumnModel().getColumn(1).setMaxWidth(0);
				getIncidenciasView().getTable().getColumnModel().getColumn(2).setPreferredWidth(150);
				getIncidenciasView().getTable().getColumnModel().getColumn(3).setPreferredWidth(50);
				getIncidenciasView().getTable().getColumnModel().getColumn(4).setPreferredWidth(100);
				getIncidenciasView().getTable().getColumnModel().getColumn(5).setPreferredWidth(100);
				getIncidenciasView().getTable().getColumnModel().getColumn(6).setPreferredWidth(100);
				getIncidenciasView().getTable().getColumnModel().getColumn(7).setPreferredWidth(100);	
			}

			// Aqui termina consulta
		} else if (e.getSource().equals(getIncidenciasView().getBtnLimpiar())) {
			getIncidenciasView().getBtnLimpiar().setText("Recargar");
			consultaGeneral();
		} else if (e.getSource().equals(getJustificarView().getBtnJustificar())) {
			if (getJustificarView().getBtnJustificar().getText().equals("Modificar")) {
				System.out.println("El id del justificante a actualizar es "
						+ getJustificanteIncidenciaModel().getIdJustificante());
				getJustificanteIncidenciaModel().setJustificante(getJustificarView().getBtnSubirImagen().getText());
				getJustificanteIncidenciaModel().setComentario(getJustificarView().getTatComentario().getText());
				try {
					String mensaje = "";
					mensaje = getJustificarIncidenciasBo().modificarEmpleado(getJustificanteIncidenciaModel());
					if (mensaje == "Correcto") {
						File source = new File(archive);
						File dest = new File("src/main/resources/img/justificantes/"
								+ getJustificanteIncidenciaModel().getJustificante());
						start = System.nanoTime();
						try {
							copyFileUsingJava7Files(source, dest);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
						}
						end = System.nanoTime();
						JOptionPane.showMessageDialog(null, "Se modifico correctamente la incidencia ");
						getJustificarView().setVisible(false);
					}
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}

			} else if (getJustificarView().getBtnJustificar().getText().equals("Justificar")) {
				if (getJustificarView().getTatComentario().getText().equals("Sin comentario...")) {
					JOptionPane.showMessageDialog(null, "Tienes que añadir un comentario");
				} else if (getJustificarView().getBtnSubirImagen().getText().equals("Seleccionar imagen...")) {
					JOptionPane.showMessageDialog(null, "Tienes que seleccionar una imagen");
				} else {
					JustificanteIncidenciaModel justificante = new JustificanteIncidenciaModel();
					Date date = new Date();
					SimpleDateFormat fechad = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String convertido = fechad.format(date);
					justificante.setComentario(getJustificarView().getTatComentario().getText());
					justificante.setFechaJustificacion(convertido);
					justificante.setIdIncidencia(getJustificanteIncidenciaModel().getIdIncidencia());
					justificante.setIdEmpleado(getJustificanteIncidenciaModel().getIdEmpleado());
					justificante.setJustificante(nombreArchivo);
					String mensaje = "";
					try {
						mensaje = getJustificarIncidenciasBo().justificarIncidencia(justificante);
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
					try {
						mensaje = getJustificarIncidenciasBo().justificarIncidencia2(justificante);
					} catch (SQLException ex) {

					}
					if (mensaje == "Correcto") {
						File source = new File(archive);
						File dest = new File("src/main/resources/img/justificantes/" + nombreArchivo);
						start = System.nanoTime();
						try {
							copyFileUsingJava7Files(source, dest);
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
						end = System.nanoTime();
						JOptionPane.showMessageDialog(null,
								"El empleado " + getJustificarView().getTxtNombreEmpleado().getText() + " "
										+ "ha justificado su " + getJustificarView().getTxtTipoIncidencia().getText()
										+ " " + "del dia " + getJustificarView().getTxtFecha().getText());
						getJustificarView().setVisible(false);
						consultaGeneral();

					}

				}

			}
		} else if (e.getSource().equals(getJustificarView().getBtnAbrirImagen())) {
			JOptionPane.showMessageDialog(null,
					"Oprimio abrir imagen " + getJustificanteIncidenciaModel().getJustificante());

		} else if (e.getSource().equals(getJustificarView().getBtnSubirImagen())) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File("src/main/resources/img/justificante/"));
			fileChooser.setFileFilter(filtro);
			fileChooser.setMultiSelectionEnabled(false);
			int opcion = fileChooser.showOpenDialog(getJustificarView().getContentPane());
			if (opcion == JFileChooser.APPROVE_OPTION) {
				archive = fileChooser.getSelectedFile().getPath();
				nombreArchivo = ConstruirNombreJustificante();
				ImageIcon icono = new ImageIcon(archive);
				getJustificarView().getBtnSubirImagen().setText(nombreArchivo);
			}
		}
	}

	private String ConstruirNombreJustificante() {
		String nombrearchivo = "";
		try {
			nombrearchivo = getJustificarIncidenciasBo().consultaridIncidencia();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		if (nombrearchivo == "") {
			nombrearchivo = "Justificante1.jpg";
		} else {
			nombrearchivo = Integer.toString((Integer.parseInt(nombrearchivo) + 1));
			nombrearchivo = "Justificante" + nombrearchivo + ".jpg";
		}
		return nombrearchivo;
	}

	public void openMethods() {
		consultarAreas();
		consultaGeneral();
	}

	@SuppressWarnings({ "rawtypes", "serial" })
	private void consultaGeneral() {
		List<IncidenciaModel> listaEmpleados = new ArrayList<IncidenciaModel>();
		try {
			listaEmpleados = getJustificarIncidenciasBo().consultaIncidenciasGeneral();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		final Iterator<IncidenciaModel> empleados = listaEmpleados.iterator();
		String[] columnNames = { "idIncidencia", "idEmpleado", "Nombre del empleado", "Area", "Tipo", "Fecha y Hora",
				"Justificacion", "Revisar" };

		final Class[] tiposColumnas = new Class[] { java.lang.String.class, java.lang.String.class,
				java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
				java.lang.String.class, JButton.class };

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
				String idIncidencia;
				String idEmpleado;
				String nombreEmpleado;
				String nombreArea;
				String tipo;
				String fechaHora;
				String justificacion;
				Object boton;

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
								if (getIncidenciasView().getTable().getModel().getColumnName(i) == "idIncidencia") {
									idIncidencia = getIncidenciasView().getTable().getModel().getValueAt(fila, i)
											.toString();
								}
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
								if (getIncidenciasView().getTable().getModel().getColumnName(i) == "Tipo") {
									tipo = getIncidenciasView().getTable().getModel().getValueAt(fila, i).toString();
								}
								if (getIncidenciasView().getTable().getModel().getColumnName(i) == "Fecha y Hora") {
									fechaHora = getIncidenciasView().getTable().getModel().getValueAt(fila, i)
											.toString();
								}
								if (getIncidenciasView().getTable().getModel().getColumnName(i) == "Justificacion") {
									justificacion = getIncidenciasView().getTable().getModel().getValueAt(fila, i)
											.toString();
								}
							}
						}

						if (boton.toString().contains("Revisar") == true) {
							JOptionPane.showMessageDialog(null,
									"Entro a modificar del empleado " + nombreEmpleado + " con el id " + idIncidencia
											+ " " + idEmpleado + " " + nombreArea + " " + tipo + " " + justificacion
											+ " " + fechaHora);
							abrirVentanaJustificarIncidencia(idIncidencia, idEmpleado, nombreEmpleado, nombreArea, tipo,
									justificacion, fechaHora);
							return;
						}
					}
				}

			});
		}

		DefaultTableModel modelo = (DefaultTableModel) getIncidenciasView().getTable().getModel();
		modelo.setColumnIdentifiers(columnNames);
		Object[] fila = new Object[modelo.getColumnCount()];
		while (empleados.hasNext()) {
			IncidenciaModel empleado = empleados.next();
			JButton btn2 = new JButton("Revisar");
			fila[0] = empleado.getIdIncidencia();
			fila[1] = empleado.getIdEmpleado();
			fila[2] = empleado.getNombreEmpleado() + " " + empleado.getApePatEmpleado() + " "
					+ empleado.getApeMatEmpleado();
			fila[3] = empleado.getArea();
			fila[4] = empleado.getTipo();
			fila[5] = empleado.getFechaIncidencia();
			fila[6] = empleado.getEstatusIncidencia();
			fila[7] = btn2;
			modelo.addRow(fila);
		}
		getIncidenciasView().getTable().getColumnModel().getColumn(0).setWidth(0);
		getIncidenciasView().getTable().getColumnModel().getColumn(0).setMinWidth(0);
		getIncidenciasView().getTable().getColumnModel().getColumn(0).setMaxWidth(0);
		getIncidenciasView().getTable().getColumnModel().getColumn(1).setWidth(0);
		getIncidenciasView().getTable().getColumnModel().getColumn(1).setMinWidth(0);
		getIncidenciasView().getTable().getColumnModel().getColumn(1).setMaxWidth(0);
		getIncidenciasView().getTable().getColumnModel().getColumn(2).setPreferredWidth(150);
		getIncidenciasView().getTable().getColumnModel().getColumn(3).setPreferredWidth(50);
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

	private void abrirVentanaJustificarIncidencia(String idIncidencia, String idEmpleado, String nombreEmpleado,
			String nombreArea, String tipo, String justificacion, String fechaHora) {
		limpiarVentana();
		if (justificacion.equals("No Justificada")) {
			getJustificanteIncidenciaModel().setIdIncidencia(Integer.parseInt(idIncidencia));
			getJustificanteIncidenciaModel().setIdEmpleado(Integer.parseInt(idEmpleado));
			getJustificarView().setVisible(true);
			getJustificarView().getTxtNombreEmpleado().setText(nombreEmpleado);
			getJustificarView().getTxtArea().setText(nombreArea);
			getJustificarView().getTxtTipoIncidencia().setText(tipo);
			getJustificarView().getTxtFecha().setText(fechaHora);
			String fotografia = consultarFotografia(idEmpleado);
			ImageIcon foto = new ImageIcon("src/main/resources/img/fotosempleados/" + fotografia);
			Icon icono = new ImageIcon(
					foto.getImage().getScaledInstance(getJustificarView().getLblFotografia().getWidth(),
							getJustificarView().getLblFotografia().getHeight(), Image.SCALE_DEFAULT));
			getJustificarView().getLblFotografia().setIcon(icono);
		} else {
			getJustificarView().setVisible(true);
			getJustificarView().getTxtNombreEmpleado().setText(nombreEmpleado);
			getJustificarView().getTxtArea().setText(nombreArea);
			getJustificarView().getTxtTipoIncidencia().setText(tipo);
			getJustificarView().getTxtFecha().setText(fechaHora);
			getJustificarView().getBtnJustificar().setText("Modificar");
			String fotografia = consultarFotografia(idEmpleado);
			ImageIcon foto = new ImageIcon("src/main/resources/img/fotosempleados/" + fotografia);
			Icon icono = new ImageIcon(
					foto.getImage().getScaledInstance(getJustificarView().getLblFotografia().getWidth(),
							getJustificarView().getLblFotografia().getHeight(), Image.SCALE_DEFAULT));
			getJustificarView().getLblFotografia().setIcon(icono);
			JustificanteIncidenciaModel justificante = consultarComentarioArchivo(idEmpleado);
			getJustificarView().getTatComentario().setText(justificante.getComentario());
			getJustificarView().getTxtFechaJustificante().setVisible(true);
			getJustificarView().getTxtFechaJustificante().setText(justificante.getFechaJustificacion());
			getJustificarView().getLblFechaJustificane().setVisible(true);
			getJustificarView().getBtnSubirImagen().setText(justificante.getJustificante());
			getJustificarView().getBtnAbrirImagen().setVisible(true);
			getJustificanteIncidenciaModel().setComentario(justificante.getComentario());
			getJustificanteIncidenciaModel().setFechaJustificacion(justificante.getFechaJustificacion());
			getJustificanteIncidenciaModel().setIdIncidencia(Integer.parseInt(idIncidencia));
			getJustificanteIncidenciaModel().setJustificante(justificante.getJustificante());

		}

	}

	private JustificanteIncidenciaModel consultarComentarioArchivo(String idEmpleado) {
		justificanteIncidenciaModel = getJustificarIncidenciasBo().consultarComentarioArchivo(idEmpleado);
		return justificanteIncidenciaModel;
	}

	private void limpiarVentana() {
		getJustificarView().getTxtArea().setText(null);
		getJustificarView().getTxtFecha().setText(null);
		getJustificarView().getTxtNombreEmpleado().setText(null);
		getJustificarView().getTxtTipoIncidencia().setText(null);
		getJustificarView().getLblFotografia().setIcon(null);
		getJustificarView().getTatComentario().setText("Sin comentario...");
		getJustificarView().getTxtFechaJustificante().setVisible(false);
		getJustificarView().getLblFechaJustificane().setVisible(false);
		getJustificarView().getBtnAbrirImagen().setVisible(false);
		getJustificarView().getBtnSubirImagen().setText("Seleccionar imagen...");
		getJustificarView().getBtnJustificar().setText("Justificar");
	}

	private String consultarFotografia(String idEmpleado) {
		String fotografia = "";
		configurarEmpleadosModel = getJustificarIncidenciasBo().consultarFoto(idEmpleado);
		fotografia = configurarEmpleadosModel.getFotografia();
		return fotografia;
	}

	@Autowired
	private static void copyFileUsingJava7Files(File source, File dest) throws IOException {
		Files.copy(source.toPath(), dest.toPath());
	}

}
