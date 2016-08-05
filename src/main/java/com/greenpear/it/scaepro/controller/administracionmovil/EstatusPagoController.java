package com.greenpear.it.scaepro.controller.administracionmovil;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
			getVista().cmbEstatusPago.addItemListener(this);
		}
		llenarAreas();
		obtenerFechaActual();
		actualizarPagos();
		getVista().setVisible(true);
		getVista().toFront();
	}

	private void obtenerFechaActual() {
		Calendar calendar = new GregorianCalendar();
		String dia = Integer.toString(calendar.get(Calendar.DATE));
		String mes = Integer.toString(calendar.get(Calendar.MONTH) + 1);
		String annio = Integer.toString(calendar.get(Calendar.YEAR));
		getVista().lblFecha.setText(annio + "/" + mes + "/" + dia);
	}

	private void actualizarPagos() {
		List<EstatusPagoModel> fechasPagos = new ArrayList<EstatusPagoModel>();
		try {
			fechasPagos = getBo().consultaFechas();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		Iterator<EstatusPagoModel> itrFechasPagos = fechasPagos.iterator();
		while (itrFechasPagos.hasNext()) {
			EstatusPagoModel fechasPagosModel = itrFechasPagos.next();
			// Dar formato a la fecha pagada consultada
			String formato = "yyyy-MM-dd";
			SimpleDateFormat formatoFecha = new SimpleDateFormat(formato);
			String strFecha = fechasPagosModel.getFechaPago();
			Date fechaDate = null;
			try {
				fechaDate = formatoFecha.parse(strFecha);
			} catch (ParseException ex) {
				ex.printStackTrace();
			}
			String fechaPago = new SimpleDateFormat("dd/MM/yyyy").format(fechaDate);
			System.out.println("fechaPago:" + fechaPago);
			// Dar formato a la fecha actual
			String formato2 = "yyyy/MM/dd";
			SimpleDateFormat formatoFecha2 = new SimpleDateFormat(formato2);
			String strFechaActual = getVista().lblFecha.getText();
			Date fechaDate2 = null;
			try {
				fechaDate2 = formatoFecha2.parse(strFechaActual);
			} catch (ParseException ex) {
				ex.printStackTrace();
			}
			String fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(fechaDate2);
			System.out.println("fechaActual:" + fechaActual);

			String periodoNominal = fechasPagosModel.getPeriodoNominal();

			System.out.println(restar_fecha(fechaPago, periodoNominal));
			System.out.println(fechasPagosModel.getIdEstatusPago());
			
			if(restar_fecha(fechaPago, periodoNominal).equals("Si")){
				String edicion = null;
				try {
					System.out.println(edicion = getBo().editar(fechasPagosModel));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public String restar_fecha(String fechaPago, String periodoNominal) {
		String fechaInicio = fechaPago;
		String actualiza = null;
		String[] aFechaIng = fechaInicio.split("/");
		
		Integer diaPago = Integer.parseInt(aFechaIng[0]);
		Integer mesPago = Integer.parseInt(aFechaIng[1]);
		Integer anioPago = Integer.parseInt(aFechaIng[2]);

		int dias = 0;
		
		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al d�a 
		java.util.Date hoy = new Date(); //Fecha de hoy 
		
		int anio = anioPago; int mes = mesPago; int dia = diaPago; //Fecha anterior 
		Calendar calendar = new GregorianCalendar(anio, mes-1, dia); 
		java.sql.Date fecha = new java.sql.Date(calendar.getTimeInMillis());

		long diferencia = ( hoy.getTime() - fecha.getTime() )/MILLSECS_PER_DAY; 
		System.out.println("D�as transcurridos: "+diferencia); 
		
		dias = (int) (diferencia);

		if (periodoNominal.equals("Semanal")) {
			if (dias >= 7) {
				actualiza = "Si";
			}else{
				actualiza = "No";
			}
		} else if (periodoNominal.equals("Quincenal")) {
			if (dias >= 15) {
				actualiza = "Si";
			}else{
				actualiza = "No";
			}
		} else if (periodoNominal.equals("Mensual")) {
			if (dias >= 30) {
				actualiza = "Si";
			}else{
				actualiza = "No";
			}
		}
		return actualiza;
	}

	private void llenarAreas() {
		List<EstatusPagoModel> listaAreas = new ArrayList<EstatusPagoModel>();
		int c = getVista().cmbAreas.getItemCount();
		for (int i = 0; i < c; i++) {
			if(i!=c-1){				
				getVista().cmbAreas.removeItemAt(1);
			}
		}
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
		if (getVista().tabla.isEditing()) {
			getVista().tabla.getCellEditor().stopCellEditing();
		}
		if (e.getSource() == getVista().btnConfirmar) {
			String registroStatusPago = null;
			int filas = getVista().tabla.getRowCount();
			for (int x = 0; x < filas; x++) {
				String comentario;
				int idStatusPago = 0;
				int idEstatus;
				EstatusPagoModel modeloConsulta = new EstatusPagoModel();
				idEstatus = (Integer) getVista().tabla.getModel().getValueAt(x, 0);
				JRadioButton JRbutton = (JRadioButton) getVista().tabla.getModel().getValueAt(x, 4);
				if (JRbutton.isSelected() == true) {
					idStatusPago = 1;
				} else if (JRbutton.isSelected() == false) {
					idStatusPago = 2;
				}
				comentario = (String) getVista().tabla.getModel().getValueAt(x, 5);
				if (comentario == null) {
					comentario = "Sin comentario";
				}
				modeloConsulta.setIdEstatusPago(idEstatus);
				modeloConsulta.setIdStatusPago(idStatusPago);
				modeloConsulta.setComentario(comentario);

				String formato = "y/MM/dd";
				SimpleDateFormat formatoFecha = new SimpleDateFormat(formato);
				String strFecha = getVista().lblFecha.getText();
				Date fechaDate = null;
				try {
					fechaDate = formatoFecha.parse(strFecha);
				} catch (ParseException ex) {
					ex.printStackTrace();
				}
				String fechaActual = new SimpleDateFormat("yyyy-MM-dd").format(fechaDate);
				modeloConsulta.setFechaActual(fechaActual);

				try {
					registroStatusPago = getBo().insertarStatusPago(modeloConsulta);
				} catch (SQLException t) {
					JOptionPane.showMessageDialog(null, t.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			String area = getVista().cmbAreas.getSelectedItem().toString();
			String estatusPago = getVista().cmbEstatusPago.getSelectedItem().toString();
			consultarArea(area, estatusPago);
			JOptionPane.showMessageDialog(null, registroStatusPago, "Registro Status Pago",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		String area = getVista().cmbAreas.getSelectedItem().toString();
		String estatusPago = getVista().cmbEstatusPago.getSelectedItem().toString();
		if (area.equals("Seleccionar...")) {
			limpiarTabla();
			getVista().cmbEstatusPago.setEnabled(false);
			return;
		} else {
			getVista().cmbEstatusPago.setEnabled(true);
			if (estatusPago == "No pagado") {
				getVista().lblTituloTabla.setText("Empleados Sin Pago:");
				getVista().btnConfirmar.setEnabled(true);
				getVista().tabla.setEnabled(true);
				consultarArea(area, estatusPago);
				for(int ed=0;ed<getVista().tabla.getRowCount();ed ++){
					getVista().tabla.setValueAt(null, ed, 5);
				}
			} else if (estatusPago == "Pagado") {
				getVista().btnConfirmar.setEnabled(false);
				getVista().tabla.setEnabled(false);
				getVista().lblTituloTabla.setText("Empleados Con Pago:");
				consultarArea(area, estatusPago);
			}
		}
	}

	private void limpiarTabla() {
		getVista().tabla.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "idEstatus",
				"Nombre Empleados", "Fecha �ltima de pago", "Periodo", "Estatus de pago", "Comentario" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		getVista().tabla.getColumnModel().getColumn(0).setWidth(0);
		getVista().tabla.getColumnModel().getColumn(0).setMinWidth(0);
		getVista().tabla.getColumnModel().getColumn(0).setMaxWidth(0);
		getVista().tabla.getColumnModel().getColumn(1).setResizable(false);
		getVista().tabla.getColumnModel().getColumn(1).setPreferredWidth(200);
		getVista().tabla.getColumnModel().getColumn(2).setResizable(false);
		getVista().tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
		getVista().tabla.getColumnModel().getColumn(3).setResizable(false);
		getVista().tabla.getColumnModel().getColumn(3).setPreferredWidth(75);
		getVista().tabla.getColumnModel().getColumn(4).setResizable(false);
		getVista().tabla.getColumnModel().getColumn(4).setPreferredWidth(75);
		getVista().tabla.getColumnModel().getColumn(5).setResizable(false);
		getVista().tabla.getColumnModel().getColumn(5).setPreferredWidth(250);
	}

	private void consultarArea(String area, String estatusPago) {
		UIDefaults ui = UIManager.getLookAndFeel().getDefaults();
		UIManager.put("RadioButton.focus", ui.getColor("control"));
		List<EstatusPagoModel> listaAreas = new ArrayList<EstatusPagoModel>();
		try {
			listaAreas = getBo().consultaAreas(area, estatusPago);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		Iterator<EstatusPagoModel> itrAreas = listaAreas.iterator();
		String[] columnNames = { "idEstatus", "Nombre Empleados", "Fecha �ltima de pago", "Periodo", "Estatus de pago",
				"Comentario" };

		final Class[] tiposColumnas = new Class[] { java.lang.Integer.class, java.lang.String.class,
				java.lang.String.class, java.lang.String.class, JRadioButton.class, java.lang.String.class };

		getVista().tabla.setModel(new javax.swing.table.DefaultTableModel() {
			Class[] tipos = tiposColumnas;

			@SuppressWarnings("unchecked")
			public Class getColumnClass(int columnIndex) {
				return tipos[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false, false, true, true };

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
			String estatusP = areas.getStatusPago();
			JRadioButton jrb = new JRadioButton(estatusP);
			if (estatusP.equals("No pagado")) {
				jrb.setSelected(false);
			} else if (estatusP.equals("Pagado")) {
				jrb.setSelected(true);
			}
			fila[0] = areas.getIdEstatusPago();
			fila[1] = areas.getNombreEmpleado().toUpperCase();
			fila[2] = areas.getFechaPago();
			fila[3] = areas.getPeriodoNominal();
			fila[4] = jrb;
			fila[5] = areas.getComentario();
			getVista().tabla.getColumn("Estatus de pago").setCellRenderer(new RadioButtonRenderer());
			getVista().tabla.getColumn("Estatus de pago").setCellEditor(new RadioButtonEditor(new JCheckBox()));
			modelo.addRow(fila);
		}
		getVista().tabla.getColumnModel().getColumn(0).setWidth(0);
		getVista().tabla.getColumnModel().getColumn(0).setMinWidth(0);
		getVista().tabla.getColumnModel().getColumn(0).setMaxWidth(0);
		getVista().tabla.getColumnModel().getColumn(1).setResizable(false);
		getVista().tabla.getColumnModel().getColumn(1).setPreferredWidth(200);
		getVista().tabla.getColumnModel().getColumn(2).setResizable(false);
		getVista().tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
		getVista().tabla.getColumnModel().getColumn(3).setResizable(false);
		getVista().tabla.getColumnModel().getColumn(3).setPreferredWidth(75);
		getVista().tabla.getColumnModel().getColumn(4).setResizable(false);
		getVista().tabla.getColumnModel().getColumn(4).setPreferredWidth(75);
		getVista().tabla.getColumnModel().getColumn(5).setResizable(false);
		getVista().tabla.getColumnModel().getColumn(5).setPreferredWidth(250);
	}

	class RadioButtonRenderer implements TableCellRenderer {
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
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

		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
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
			if (JRbutton.isSelected() == true) {
				button.setText("Pagado");
				return;
			} else if (JRbutton.isSelected() == false) {
				button.setText("No pagado");
			}
			super.fireEditingStopped();
		}
	}
}
