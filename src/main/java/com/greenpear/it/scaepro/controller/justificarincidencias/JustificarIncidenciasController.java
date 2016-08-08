package com.greenpear.it.scaepro.controller.justificarincidencias;

import java.awt.Component;
import java.awt.Image;
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
		} else if (e.getSource().equals(getJustificarView().getBtnJustificar())) {
			if (getJustificarView().getBtnJustificar().getText().equals("Modificar")) {
				JOptionPane.showMessageDialog(null, "Entro a boton modificar");
			} else if (getJustificarView().getBtnJustificar().getText().equals("Justificar")) {
				System.out.println("El estatus de la incidencia sera Justificado");
				System.out.println("El id de la incidencia es "+getJustificanteIncidenciaModel().getIdIncidencia());
				System.out.println("El id del justificante a actualizar es "+getJustificanteIncidenciaModel().getIdJustificante());
				System.out.println("El comentario a subir es "+getJustificarView().getTatComentario().getText());
				System.out.println("Y la imagen a subir es " +nombreArchivo);
//				File source = new File(archive);
//				File dest = new File(
//						"src/main/resources/img/justificantes/"
//								+ nombreArchivo);
//				start = System.nanoTime();
//					try {
//						copyFileUsingJava7Files(source, dest);
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//					}
//				end = System.nanoTime();
			}
		} else if (e.getSource().equals(getJustificarView().getBtnAbrirImagen())) {
			JOptionPane.showMessageDialog(null, "Oprimio abrir imagen");
		} else if (e.getSource().equals(getJustificarView().getBtnSubirImagen())) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File("src/main/resources/img/justificantes/"));
			fileChooser.setFileFilter(filtro);
			fileChooser.setMultiSelectionEnabled(false);
			int opcion = fileChooser.showOpenDialog(getJustificarView().getContentPane());
			if (opcion == JFileChooser.APPROVE_OPTION) {
				archive = fileChooser.getSelectedFile().getPath();
				nombreArchivo = fileChooser.getSelectedFile().getName().toString();
				ImageIcon icono = new ImageIcon(archive);
				getJustificarView().getBtnSubirImagen().setText(nombreArchivo);
			}
		}
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
		String[] columnNames = { "idEmpleado", "Nombre del empleado", "Area", "Tipo", "Fecha y Hora", "Justificacion",
				"Revisar" };

		final Class[] tiposColumnas = new Class[] { java.lang.String.class, java.lang.String.class,
				java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
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
		getIncidenciasView().getTable().setDefaultRenderer(JCheckBox.class, new TableCellRenderer() {
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
									"Entro a modificar del empleado " + nombreEmpleado + " con el id " + idEmpleado);
							abrirVentanaJustificarIncidencia(idEmpleado, nombreEmpleado, nombreArea, tipo,
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
			fila[0] = empleado.getIdEmpleado();
			fila[1] = empleado.getNombreEmpleado() + " " + empleado.getApePatEmpleado() + " "
					+ empleado.getApeMatEmpleado();
			fila[2] = empleado.getArea();
			fila[3] = empleado.getTipo();
			fila[4] = empleado.getFechaIncidencia();
			fila[5] = empleado.getEstatusIncidencia();
			fila[6] = btn2;
			modelo.addRow(fila);
		}
		getIncidenciasView().getTable().getColumnModel().getColumn(0).setWidth(0);
		getIncidenciasView().getTable().getColumnModel().getColumn(0).setMinWidth(0);
		getIncidenciasView().getTable().getColumnModel().getColumn(0).setMaxWidth(0);
		getIncidenciasView().getTable().getColumnModel().getColumn(1).setPreferredWidth(200);
		getIncidenciasView().getTable().getColumnModel().getColumn(2).setPreferredWidth(150);
		getIncidenciasView().getTable().getColumnModel().getColumn(3).setPreferredWidth(50);
		getIncidenciasView().getTable().getColumnModel().getColumn(4).setPreferredWidth(100);
		getIncidenciasView().getTable().getColumnModel().getColumn(5).setPreferredWidth(100);
		getIncidenciasView().getTable().getColumnModel().getColumn(6).setPreferredWidth(100);
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

	private void abrirVentanaJustificarIncidencia(String idEmpleado, String nombreEmpleado, String nombreArea,
			String tipo, String justificacion, String fechaHora) {
		limpiarVentana();
		if (justificacion.equals("No Justificada")) {
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
			getJustificarView().getBtnAbrirImagen().setVisible(true);
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
