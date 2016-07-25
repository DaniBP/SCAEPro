/**
 * 
 */
package com.greenpear.it.scaepro.controller.administracionmovil;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.spi.RegisterableService;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.support.RegistrationPolicy;

import com.greenpear.it.scaepro.bo.administracionmovil.GenerarAvisoBo;
import com.greenpear.it.scaepro.controller.government.GovernmentService;
import com.greenpear.it.scaepro.model.administracionmovil.GenerarAvisoModel;
import com.greenpear.it.scaepro.model.administracionmovil.NoticiasModel;
import com.greenpear.it.scaepro.view.administracionmovil.GenerarAvisoView;

/**
 * @author EDSONJOSUE
 *
 */
public class GenerarAvisoController implements ActionListener, ItemListener {
	// ********ESTANCIAS***********************************
	@Autowired
	private GovernmentService government;

	@Autowired
	private GenerarAvisoView vista;

	@Autowired
	private GenerarAvisoModel modelo;

	@Autowired
	private GenerarAvisoBo Bo;

	public GovernmentService getGovernment() {
		return government;
	}

	public GenerarAvisoView getVista() {
		return vista;
	}

	public GenerarAvisoModel getModelo() {
		return modelo;
	}

	public GenerarAvisoBo getBo() {
		return Bo;
	}

	// ***********FIN DE ESTANCIAS****************
	public void mostrarVistaGenerarAviso() {
		if (getVista().btnEnviar.getActionListeners().length == 0) {
			getVista().btnEnviar.addActionListener(this);
			getVista().cmbTipoAviso.addItemListener(this);
		}
		getVista().setVisible(true);
		getVista().toFront();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		int tipoAviso = getVista().cmbTipoAviso.getSelectedIndex();
		if (tipoAviso == 0) {
			limpiarTabla();
			getVista().tablaBusqueda.setEnabled(false);
			getVista().txtAviso.setEnabled(false);
			getVista().btnEnviar.setEnabled(false);
			getVista().lblBusqueda.setText("");
			return;
		} else {
			getVista().tablaBusqueda.setEnabled(false);
			getVista().txtAviso.setEnabled(true);
			getVista().btnEnviar.setEnabled(true);
			if (tipoAviso == 1) {
				getVista().lblBusqueda.setText("");
				getVista().tablaBusqueda.setEnabled(false);
				limpiarTabla();
			} else if (tipoAviso == 2) {
				cargarEmpleados();
				getVista().tablaBusqueda.setEnabled(true);
				getVista().lblBusqueda.setText("Empleados:");
			} else if (tipoAviso == 3) {
				cargarAreas();
				getVista().tablaBusqueda.setEnabled(true);
				getVista().lblBusqueda.setText("Áreas:");
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(getVista().txtAviso.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Ingrese Un Aviso", "Nuevo Aviso",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		String typeAviso = getVista().cmbTipoAviso.getSelectedItem().toString();
		String aviso = getVista().txtAviso.getText();

		int tipoAviso = getVista().cmbTipoAviso.getSelectedIndex();

		if (e.getSource() == getVista().btnEnviar) {
			JTable tabla = getVista().tablaBusqueda;
			int filas = getVista().tablaBusqueda.getModel().getRowCount();
			if (tipoAviso == 1) {
				String registroAviso = null;
				GenerarAvisoModel modeloConsulta = new GenerarAvisoModel();
				modeloConsulta.setIdArea(0);
				modeloConsulta.setIdEmpleado(0);
				modeloConsulta.setTipoAviso(typeAviso);
				modeloConsulta.setAviso(aviso);
				try {
					registroAviso = getBo().insertarAvisoArea(modeloConsulta);
				} catch (SQLException t) {
					JOptionPane.showMessageDialog(null, t.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			JOptionPane.showMessageDialog(null, registroAviso, "Registro", JOptionPane.INFORMATION_MESSAGE);
			return;
		} else if (tipoAviso == 2) {
			String registroAviso = null;
			for (int x = 0; x < filas; x++) {
				if (Boolean.parseBoolean(tabla.getModel().getValueAt(x, 1).toString()) == true) {
					String nombre = tabla.getModel().getValueAt(x, 0).toString();
					GenerarAvisoModel modeloConsulta = new GenerarAvisoModel();
					try {
						modeloConsulta = getBo().consultaEmpleado(nombre);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					modeloConsulta.setIdArea(0);
					modeloConsulta.setTipoAviso(typeAviso);
					modeloConsulta.setAviso(aviso);
					try {
						registroAviso = getBo().insertarAvisoArea(modeloConsulta);
					} catch (SQLException t) {
						JOptionPane.showMessageDialog(null, t.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			}
			JOptionPane.showMessageDialog(null, registroAviso, "Registro", JOptionPane.INFORMATION_MESSAGE);
			return;
		} else if (tipoAviso == 3) {
			String registroAviso = null;
			for (int x = 0; x < filas; x++) {
				if (Boolean.parseBoolean(tabla.getModel().getValueAt(x, 1).toString()) == true) {
					String nombre = tabla.getModel().getValueAt(x, 0).toString();
					GenerarAvisoModel modeloConsulta = new GenerarAvisoModel();
					try {
						modeloConsulta = getBo().consultaArea(nombre);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					modeloConsulta.setIdEmpleado(0);
					modeloConsulta.setTipoAviso(typeAviso);
					modeloConsulta.setAviso(aviso);
					try {
						registroAviso = getBo().insertarAvisoArea(modeloConsulta);
					} catch (SQLException t) {
						JOptionPane.showMessageDialog(null, t.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			}
			JOptionPane.showMessageDialog(null, registroAviso, "Registro", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
	}

	}

	private void limpiarTabla() {
		String[] columnNames = { "Nombre Empleados", "Seleccionar" };

		final Class[] tiposColumnas = new Class[] { java.lang.String.class, java.lang.Boolean.class };

		getVista().tablaBusqueda.setModel(new javax.swing.table.DefaultTableModel() {
			Class[] tipos = tiposColumnas;

			@SuppressWarnings("unchecked")
			public Class getColumnClass(int columnIndex) {
				return tipos[columnIndex];
			}

			public boolean isCellEditable(int row, int column) {
				return true;
			}
		});
	}

	private void cargarAreas() {
		List<GenerarAvisoModel> listaAreas = new ArrayList<GenerarAvisoModel>();
		try {
			listaAreas = getBo().consultaAreas();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		Iterator<GenerarAvisoModel> itrAreas = listaAreas.iterator();
		String[] columnNames = { "Nombre Empleados", "Seleccionar" };

		final Class[] tiposColumnas = new Class[] { java.lang.String.class, java.lang.Boolean.class };

		getVista().tablaBusqueda.setModel(new javax.swing.table.DefaultTableModel() {
			Class[] tipos = tiposColumnas;

			@SuppressWarnings("unchecked")
			public Class getColumnClass(int columnIndex) {
				return tipos[columnIndex];
			}

			public boolean isCellEditable(int row, int column) {
				return true;
			}
		});

		getVista().tablaBusqueda.setDefaultRenderer(JButton.class, new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado,
					boolean tieneElFoco, int fila, int columna) {
				return (Component) objeto;
			}
		});

		DefaultTableModel modelo = (DefaultTableModel) getVista().tablaBusqueda.getModel();
		modelo.setColumnIdentifiers(columnNames);
		Object[] fila = new Object[modelo.getColumnCount()];
		while (itrAreas.hasNext()) {
			GenerarAvisoModel areas = itrAreas.next();
			Boolean checkBox = false;
			fila[0] = areas.getNombreArea();
			fila[1] = checkBox;

			modelo.addRow(fila);
		}
		getVista().tablaBusqueda.getColumnModel().getColumn(0).setResizable(false);
		getVista().tablaBusqueda.getColumnModel().getColumn(0).setPreferredWidth(320);
		getVista().tablaBusqueda.getColumnModel().getColumn(1).setResizable(false);
		getVista().tablaBusqueda.getColumnModel().getColumn(1).setPreferredWidth(40);
	}

	private void cargarEmpleados() {
		List<GenerarAvisoModel> listaNoticias = new ArrayList<GenerarAvisoModel>();
		try {
			listaNoticias = getBo().consultaGeneral();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		Iterator<GenerarAvisoModel> itrempleados = listaNoticias.iterator();
		String[] columnNames = { "Nombre Empleados", "Seleccionar" };

		final Class[] tiposColumnas = new Class[] { java.lang.String.class, java.lang.Boolean.class };

		getVista().tablaBusqueda.setModel(new javax.swing.table.DefaultTableModel() {
			Class[] tipos = tiposColumnas;

			@SuppressWarnings("unchecked")
			public Class getColumnClass(int columnIndex) {
				return tipos[columnIndex];
			}

			public boolean isCellEditable(int row, int column) {
				return true;
			}
		});

		getVista().tablaBusqueda.setDefaultRenderer(JButton.class, new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado,
					boolean tieneElFoco, int fila, int columna) {
				return (Component) objeto;
			}
		});

		DefaultTableModel modelo = (DefaultTableModel) getVista().tablaBusqueda.getModel();
		modelo.setColumnIdentifiers(columnNames);
		Object[] fila = new Object[modelo.getColumnCount()];
		while (itrempleados.hasNext()) {
			GenerarAvisoModel empleados = itrempleados.next();
			Boolean checkBox = false;
			fila[0] = empleados.getNombreEmpleado();
			fila[1] = checkBox;

			modelo.addRow(fila);
		}
		getVista().tablaBusqueda.getColumnModel().getColumn(0).setResizable(false);
		getVista().tablaBusqueda.getColumnModel().getColumn(0).setPreferredWidth(320);
		getVista().tablaBusqueda.getColumnModel().getColumn(1).setResizable(false);
		getVista().tablaBusqueda.getColumnModel().getColumn(1).setPreferredWidth(40);
	}
}
