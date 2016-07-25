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

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.springframework.beans.factory.annotation.Autowired;

import com.greenpear.it.scaepro.bo.administracionmovil.EstatusPagoBo;
import com.greenpear.it.scaepro.controller.government.GovernmentService;
import com.greenpear.it.scaepro.model.administracionmovil.EstatusPagoModel;
import com.greenpear.it.scaepro.model.administracionmovil.GenerarAvisoModel;
import com.greenpear.it.scaepro.view.administracionmovil.EstatusPagoView;

public class EstatusPagoController implements ActionListener, ItemListener {
	// ********ESTANCIAS***********************************
	@Autowired
	private GovernmentService government;

	@Autowired
	private EstatusPagoView vista;

	@Autowired
	private EstatusPagoModel modelo;

	@Autowired
	private EstatusPagoBo Bo;

	public GovernmentService getGovernment() {
		return government;
	}

	public EstatusPagoView getVista() {
		return vista;
	}

	public EstatusPagoModel getModelo() {
		return modelo;
	}

	public EstatusPagoBo getBo() {
		return Bo;
	}

	// ***********FIN DE ESTANCIAS****************
	public void mostrarVistaEstatusPago() {
		if (getVista().btnConfirmar.getActionListeners().length == 0) {
			getVista().btnConfirmar.addActionListener(this);
			getVista().cmbAreas.addItemListener(this);
		}
		llenarAreas();
		getVista().setVisible(true);
		getVista().toFront();
	}

	private void llenarAreas() {
		List<EstatusPagoModel> listaAreas = new ArrayList<EstatusPagoModel>();
		try {
			listaAreas = getBo().llenarAreas();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		Iterator<EstatusPagoModel> itrAreas = listaAreas.iterator();
		while (itrAreas.hasNext()) {
			EstatusPagoModel areas = itrAreas.next();
			getVista().cmbAreas.addItem(areas.getNombreArea());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(getVista().tabla.isEditing()){
			getVista().tabla.getCellEditor().stopCellEditing();
		}
		if(e.getSource() == getVista().btnConfirmar){
			String registroStatusPago = null;
			int columnas = getVista().tabla.getColumnCount();
			for(int x=0; x<columnas; x++){
				String empleado;
				String comentario;
				int idStatusPago = 0;
				int idEmpleado;
				empleado = getVista().tabla.getModel().getValueAt(x, 0).toString();
				EstatusPagoModel modeloConsulta = new EstatusPagoModel();
				try {
					modeloConsulta = getBo().consultaEmpleado(empleado);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				idEmpleado = modeloConsulta.getIdEmpleado();
				JRadioButton JRbutton = (JRadioButton)getVista().tabla.getModel().getValueAt(x, 1);
				if(JRbutton.isSelected() == true){
					idStatusPago = 1;
				}else if(JRbutton.isSelected() == false){
					idStatusPago = 2;
				}
				comentario = (String) getVista().tabla.getModel().getValueAt(x, 2);
				if(comentario == null){
					comentario = "Sin comentario";
				}
//				System.out.println("idEmpleado:"+idEmpleado+"; idStatus:"+idStatus+"; comentario:"+comentario);
				modeloConsulta.setIdStatusPago(idStatusPago);
				modeloConsulta.setComentario(comentario);
				try {
					registroStatusPago= getBo().insertarStatusPago(modeloConsulta);
				} catch (SQLException t) {
					JOptionPane.showMessageDialog(null, t.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			JOptionPane.showMessageDialog(null, registroStatusPago, "Registro Status Pago", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		String area = getVista().cmbAreas.getSelectedItem().toString();
		if (area.equals("Seleccionar...")) {
			limpiarTabla();
			return;
		} else {
			consultarArea(area);
		}
	}
	
	private void limpiarTabla() {
		getVista().tabla.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Nombre", "Estatus de pago", "Comentario"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		getVista().tabla.getColumnModel().getColumn(0).setResizable(false);
		getVista().tabla.getColumnModel().getColumn(0).setPreferredWidth(200);
		getVista().tabla.getColumnModel().getColumn(1).setResizable(false);
		getVista().tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
		getVista().tabla.getColumnModel().getColumn(2).setResizable(false);
		getVista().tabla.getColumnModel().getColumn(2).setPreferredWidth(195);
	}

	private void consultarArea(String area) {
		UIDefaults ui = UIManager.getLookAndFeel().getDefaults();
	    UIManager.put("RadioButton.focus", ui.getColor("control"));
		List<EstatusPagoModel> listaAreas = new ArrayList<EstatusPagoModel>();
		try {
			listaAreas = getBo().consultaAreas(area);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		Iterator<EstatusPagoModel> itrAreas = listaAreas.iterator();
		String[] columnNames = { "Nombre Empleados", "Estatus de pago", "Comentario" };

		final Class[] tiposColumnas = new Class[] { java.lang.String.class, JRadioButton.class,
				java.lang.String.class };

		getVista().tabla.setModel(new javax.swing.table.DefaultTableModel() {
			Class[] tipos = tiposColumnas;

			@SuppressWarnings("unchecked")
			public Class getColumnClass(int columnIndex) {
				return tipos[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
					false, true, true
				};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		getVista().tabla.setDefaultRenderer(JRadioButton.class, new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado,
					boolean tieneElFoco, int fila, int columna) {
				return (Component) objeto;
			}
		});

		DefaultTableModel modelo = (DefaultTableModel) getVista().tabla.getModel();
		modelo.setColumnIdentifiers(columnNames);
		Object[] fila = new Object[modelo.getColumnCount()];
		while (itrAreas.hasNext()) {
			EstatusPagoModel areas = itrAreas.next();
			JRadioButton jrb = new JRadioButton("No pagado");
			fila[0] = areas.getNombreEmpleado();
			fila[1] = jrb;
		    getVista().tabla.getColumn("Estatus de pago").setCellRenderer(
		        new RadioButtonRenderer());
		    getVista().tabla.getColumn("Estatus de pago").setCellEditor(
		        new RadioButtonEditor(new JCheckBox()));
			modelo.addRow(fila);
		}
		getVista().tabla.getColumnModel().getColumn(0).setResizable(false);
		getVista().tabla.getColumnModel().getColumn(0).setPreferredWidth(200);
		getVista().tabla.getColumnModel().getColumn(1).setResizable(false);
		getVista().tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
		getVista().tabla.getColumnModel().getColumn(2).setResizable(false);
		getVista().tabla.getColumnModel().getColumn(2).setPreferredWidth(195);
	}
	
	class RadioButtonRenderer implements TableCellRenderer {
		  public Component getTableCellRendererComponent(JTable table, Object value,
		      boolean isSelected, boolean hasFocus, int row, int column) {
		    if (value == null)
		      return null;
		    return (Component) value;
		  }
		}

		class RadioButtonEditor extends DefaultCellEditor implements ItemListener {
		  private JRadioButton button;

		  public RadioButtonEditor(JCheckBox checkBox) {
		    super(checkBox);
		  }

		  public Component getTableCellEditorComponent(JTable table, Object value,
		      boolean isSelected, int row, int column) {
		    if (value == null)
		      return null;
		    button = (JRadioButton) value;
		    button.addItemListener(this);
		    return (Component) value;
		  }

		  public Object getCellEditorValue() {
		    button.removeItemListener(this);
		    return button;
		  }

		  public void itemStateChanged(ItemEvent e) {
			  JRadioButton JRbutton = (JRadioButton) e.getSource();
			  if(JRbutton.isSelected() == true){
				  button.setText("Pagado");
				  return;
			  } else if(JRbutton.isSelected() == false){
				  button.setText("No pagado");
			  }
		    super.fireEditingStopped();
		  }
		}
}
