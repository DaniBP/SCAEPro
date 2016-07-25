/**
 * 
 */
package com.greenpear.it.scaepro.controller.gestionareas;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import org.aspectj.weaver.tools.cache.AsynchronousFileCacheBacking.RemoveCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jca.work.jboss.JBossWorkManagerTaskExecutor;

import com.greenpear.it.scaepro.bo.gestionareas.ConsultaAreasBo;
import com.greenpear.it.scaepro.controller.government.GovernmentService;
import com.greenpear.it.scaepro.model.administracionmovil.NoticiasModel;
import com.greenpear.it.scaepro.model.gestionareas.ConsultaAreasModel;
import com.greenpear.it.scaepro.view.accesosistema.PrincipalView;
import com.greenpear.it.scaepro.view.gestionareas.ConsultaAreasView;

/**
 * @author EDSONJOSUE
 *
 */
public class ConsultaAreasController implements ActionListener {
	// ********ESTANCIAS***********************************

	@Autowired
	private GovernmentService government;

	@Autowired
	private ConsultaAreasModel modelo;

	@Autowired
	@Qualifier("consultaAreasBo")
	private ConsultaAreasBo Bo;

	@Autowired
	private ConsultaAreasView vista;

	public GovernmentService getGovernment() {
		return government;
	}

	public ConsultaAreasModel getModelo() {
		return modelo;
	}

	public ConsultaAreasView getVista() {
		return vista;
	}

	public ConsultaAreasBo getBo() {
		return Bo;
	}

	// ***********FIN DE ESTANCIAS****************
	public void mostrarVistaConsultarAreas() {
		if (getVista().btnBuscar.getActionListeners().length == 0) {
			getVista().btnBuscar.addActionListener(this);
			 
		 }

		getVista().setVisible(true);
		getVista().toFront();
		 
		tabla();
		getVista().setVisible(true);
	}

	@SuppressWarnings({ "rawtypes", "serial" })
	private void tabla() {
		List<ConsultaAreasModel> listaUsuarios = new ArrayList<ConsultaAreasModel>();
		try {
			listaUsuarios = getBo().consultaGeneral();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		Iterator<ConsultaAreasModel> itrPartidos = listaUsuarios.iterator();
		String[] columnNames = { "Nombre del Área", "Descripción", "Editar", "Eliminar" };

		final Class[] tiposColumnas = new Class[] { java.lang.String.class, java.lang.String.class, JButton.class,
				JButton.class };

		vista.tablaAreas.setModel(new javax.swing.table.DefaultTableModel() {
			Class[] tipos = tiposColumnas;

			@SuppressWarnings("unchecked")
			public Class getColumnClass(int columnIndex) {
				return tipos[columnIndex];
			}

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});

		vista.tablaAreas.setDefaultRenderer(JButton.class, new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado,
					boolean tieneElFoco, int fila, int columna) {
				return (Component) objeto;
			}
		});
		if (vista.tablaAreas.getMouseListeners().length == 2) {

			vista.tablaAreas.addMouseListener(new MouseAdapter() {
				String area;
				String descripcionArea;
				Object boton;
				private String delete;

				public void mouseClicked(MouseEvent e) {
					int fila = vista.tablaAreas.rowAtPoint(e.getPoint());
					int columna = vista.tablaAreas.columnAtPoint(e.getPoint());

					if (vista.tablaAreas.getModel().getColumnClass(columna).equals(JButton.class)) {
						boton = vista.tablaAreas.getModel().getValueAt(fila, columna);
						StringBuilder sb = new StringBuilder();
						for (int i = 0; i < vista.tablaAreas.getModel().getColumnCount(); i++) {
							if (!vista.tablaAreas.getModel().getColumnClass(i).equals(JButton.class)) {
								sb.append("\n").append(vista.tablaAreas.getModel().getColumnName(i)).append(": ")
										.append(vista.tablaAreas.getModel().getValueAt(fila, i));
								if (vista.tablaAreas.getModel().getColumnName(i) == "Nombre del Área") {
									area = vista.tablaAreas.getModel().getValueAt(fila, i).toString();
								}
								if (vista.tablaAreas.getModel().getColumnName(i) == "Descripción") {
									descripcionArea = vista.tablaAreas.getModel().getValueAt(fila, i).toString();
								}
							}
						}
						// Botón Editar--------
						if (boton.toString().contains("Editar") == true) {
							modelo.setArea(area);
							ConsultaAreasModel modeloConsulta= new ConsultaAreasModel();
							try {
								modeloConsulta = getBo().consultaEditar(area);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							getModelo().setArea(modeloConsulta.getArea());
							getModelo().setDescripcionArea(modeloConsulta.getDescripcionArea());
							getModelo().setIdArea(modeloConsulta.getIdArea());
//							getModelo().setNombreVentana("Detalle Noticia");
							getGovernment().mostrarVistaRegistrarAreas();

							return;
							// Botón Eliminar------
						} else if (boton.toString().contains("Eliminar") == true) {
							if (JOptionPane.showConfirmDialog(null, "Seguro de Eliminar el Àrea :" + area) == 0) {
								modelo.setArea(area);
								try {
									delete = getBo().eliminarArea(getModelo());
								} catch (SQLException e1) {
									JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
								}
								if (delete.equals("El àrea fue eliminada correctamente!")) {
									JOptionPane.showMessageDialog(null, delete, "Eliminar área",
											JOptionPane.INFORMATION_MESSAGE);
								} else {
									JOptionPane.showMessageDialog(null, delete, "Eliminar área",
											JOptionPane.WARNING_MESSAGE);
								}
								tabla();
							}
							return;
						}
					}
				}
			});
		}

		DefaultTableModel modelo = (DefaultTableModel) vista.tablaAreas.getModel();
		modelo.setColumnIdentifiers(columnNames);
		Object[] fila = new Object[modelo.getColumnCount()];
		while (itrPartidos.hasNext()) {
			ConsultaAreasModel partido = itrPartidos.next();
			JButton btn = new JButton("Editar");
			JButton btn2 = new JButton("Eliminar");
			fila[0] = partido.getArea();
			fila[1] = partido.getDescripcionArea();
			fila[2] = btn;
			fila[3] = btn2;
			modelo.addRow(fila);
		}
		vista.tablaAreas.getColumnModel().getColumn(0).setPreferredWidth(150);
		vista.tablaAreas.getColumnModel().getColumn(1).setPreferredWidth(475);
		vista.tablaAreas.getColumnModel().getColumn(2).setPreferredWidth(100);
		vista.tablaAreas.getColumnModel().getColumn(3).setPreferredWidth(100);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == getVista().btnBuscar) {
			if (getVista().txtNombArea.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Ingrese el nombre del área", "Buscar Área",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			getModelo().setArea(getVista().txtNombArea.getText());
			ConsultaAreasModel area = getModelo();
			try {
				area=getBo().consultaIndividual(area);
			} catch (SQLException e1) {
				System.out.print(e1.getMessage());
			}
			String[] columnNames = { "Nombre del Área", "Descripción", "Editar", "Eliminar" };

			final Class[] tiposColumnas = new Class[] { java.lang.String.class, java.lang.String.class, JButton.class,
					JButton.class };

			vista.tablaAreas.setModel(new javax.swing.table.DefaultTableModel() {
				Class[] tipos = tiposColumnas;

				@SuppressWarnings("unchecked")
				public Class getColumnClass(int columnIndex) {
					return tipos[columnIndex];
				}

				public boolean isCellEditable(int row, int column) {
					return false;
				}
			});

			vista.tablaAreas.setDefaultRenderer(JButton.class, new TableCellRenderer() {
				@Override
				public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado,
						boolean tieneElFoco, int fila, int columna) {
					return (Component) objeto;
				}
			});
			if (vista.tablaAreas.getMouseListeners().length == 2) {

				vista.tablaAreas.addMouseListener(new MouseAdapter() {
					String area;
					String descripcionArea;
					Object boton;
					private String delete;

					public void mouseClicked(MouseEvent e) {
						int fila = vista.tablaAreas.rowAtPoint(e.getPoint());
						int columna = vista.tablaAreas.columnAtPoint(e.getPoint());

						if (vista.tablaAreas.getModel().getColumnClass(columna).equals(JButton.class)) {
							boton = vista.tablaAreas.getModel().getValueAt(fila, columna);
							StringBuilder sb = new StringBuilder();
							for (int i = 0; i < vista.tablaAreas.getModel().getColumnCount(); i++) {
								if (!vista.tablaAreas.getModel().getColumnClass(i).equals(JButton.class)) {
									sb.append("\n").append(vista.tablaAreas.getModel().getColumnName(i)).append(": ")
											.append(vista.tablaAreas.getModel().getValueAt(fila, i));
									if (vista.tablaAreas.getModel().getColumnName(i) == "Nombre del Área") {
										area = vista.tablaAreas.getModel().getValueAt(fila, i).toString();
									}
									if (vista.tablaAreas.getModel().getColumnName(i) == "Descripción") {
										descripcionArea = vista.tablaAreas.getModel().getValueAt(fila, i).toString();
									}
								}
							}
							// Botón Editar--------
							if (boton.toString().contains("Editar") == true) {
								modelo.setArea(area);
								modelo.setDescripcionArea(descripcionArea);
								getGovernment().mostrarVistaRegistrarAreas();

								return;
								// Botón Eliminar------
							} else if (boton.toString().contains("Eliminar") == true) {
								if (JOptionPane.showConfirmDialog(null, "Seguro de Eliminar el Àrea :" + area) == 0) {
									modelo.setArea(area);
									try {
										delete = getBo().eliminarArea(getModelo());
									} catch (SQLException e1) {
										JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
									}
									if (delete.equals("El àrea fue eliminada correctamente!")) {
										JOptionPane.showMessageDialog(null, delete, "Eliminar área",
												JOptionPane.INFORMATION_MESSAGE);
									} else {
										JOptionPane.showMessageDialog(null, delete, "Eliminar área",
												JOptionPane.WARNING_MESSAGE);
									}
									tabla();
								}
								return;
							}
						}
					}
				});
			}

			DefaultTableModel modelo = (DefaultTableModel) vista.tablaAreas.getModel();
			modelo.setColumnIdentifiers(columnNames);
			Object[] fila = new Object[modelo.getColumnCount()];
				JButton btn = new JButton("Editar");
				JButton btn2 = new JButton("Eliminar");
				fila[0] = area.getArea();
				fila[1] = area.getDescripcionArea();
				fila[2] = btn;
				fila[3] = btn2;
				modelo.addRow(fila);
				
			vista.tablaAreas.getColumnModel().getColumn(0).setPreferredWidth(150);
			vista.tablaAreas.getColumnModel().getColumn(1).setPreferredWidth(475);
			vista.tablaAreas.getColumnModel().getColumn(2).setPreferredWidth(100);
			vista.tablaAreas.getColumnModel().getColumn(3).setPreferredWidth(100);
		}
	}
}
