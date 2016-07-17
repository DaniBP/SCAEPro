package com.greenpear.it.scaepro.controller.configurarempleados;

import java.awt.TextField;
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
	@Autowired
	@Qualifier("empleadosBoService")
	private ConfigurarEmpleadosBo empleadosBo;

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

	public void mostrarVistaRegistroEmpleado() {
		if (getRegistrarEmpleadoView().getBtnRegistrar().getActionListeners().length == 0) {
			getRegistrarEmpleadoView().getBtnRegistrar().addActionListener(this);
			getRegistrarEmpleadoView().getCmbArea().addItemListener(this);
			getRegistrarEmpleadoView().addWindowListener(this);
			getRegistrarEmpleadoView().getCmbPeriodoNominal().addItemListener(this);
		}
		getRegistrarEmpleadoView().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(getRegistrarEmpleadoView().getBtnRegistrar())) {
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
					|| getRegistrarEmpleadoView().getTxtNumeroExt().getText().isEmpty() || getRegistrarEmpleadoView()
							.getCmbColonia().getSelectedItem().equals("---Seleccione su colonia---")) {
				JOptionPane.showMessageDialog(null, "Completa todos los valores solicitados de 'Direccion'");
				// Validar panel de asignacion
			} else if (getRegistrarEmpleadoView().getTxtPuesto().getText().isEmpty()
					|| getRegistrarEmpleadoView().getCmbArea().getSelectedItem()
							.equals("--------Seleccione un área--------")
					|| getRegistrarEmpleadoView().getCmbTurno().getSelectedItem()
							.equals("--------Seleccione un turno--------")) {
				JOptionPane.showMessageDialog(null, "Completa todos los campos de  'Asignación'");
				// Validar campos de nomina
			} else if (getRegistrarEmpleadoView().getCmbPeriodoNominal().getSelectedItem()
					.equals("----Selecciona un periodo nominal----")
					|| getRegistrarEmpleadoView().getCmbDiaNomina().getSelectedItem()
							.equals("---------Selecciona un dia---------")) {
				JOptionPane.showMessageDialog(null, "Completa todos los campos de 'Nomina'");
			} else {
				insertarDireccionEmpleado();

				// limpiarVentanas();
			}
		}

	}

	private void limpiarVentanas() {
		getRegistrarEmpleadoView().getTxtNombres().setText(null);
		getRegistrarEmpleadoView().getTxtApePat().setText(null);
		getRegistrarEmpleadoView().getTxtApeMat().setText(null);
		getRegistrarEmpleadoView().getTxtCalle().setText(null);
		getRegistrarEmpleadoView().getTxtCp().setText(null);
		getRegistrarEmpleadoView().getTxtNumeroExt().setText(null);
		getRegistrarEmpleadoView().getTxtNumeroInt().setText(null);
		getRegistrarEmpleadoView().getTxtPuesto().setText(null);
		getRegistrarEmpleadoView().getTxtMunicipio().setText(null);
		getRegistrarEmpleadoView().getTxtEstado().setText(null);
		getRegistrarEmpleadoView().getTxtTelCasa().setText(null);
		getRegistrarEmpleadoView().getTxtTelCel().setText(null);
		getRegistrarEmpleadoView().getFecha().setDate(null);
		limpiarCombos();
		limpiarDireccion();
	}

	private void limpiarCombos() {
		final int tempArea = getRegistrarEmpleadoView().getCmbArea().getItemCount();
		final int tempNomina = getRegistrarEmpleadoView().getCmbPeriodoNominal().getItemCount();
		for (int x = 0; x < tempArea; x++) {
			getRegistrarEmpleadoView().getCmbArea().removeItemAt(0);
			if (getRegistrarEmpleadoView().getCmbArea().getItemCount() == 1) {
				llenarComboArea();
			}
		}
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

	private void insertarDireccionEmpleado() {
		
		direccionModelo.setCp(getRegistrarEmpleadoView().getTxtCp().getText());
		direccionModelo.setNumIn(getRegistrarEmpleadoView().getTxtNumeroInt().getText());
		direccionModelo.setNumEx(getRegistrarEmpleadoView().getTxtNumeroExt().getText());
		direccionModelo.setCalle(getRegistrarEmpleadoView().getTxtCalle().getText());
		direccionModelo.setUniColonia(getRegistrarEmpleadoView().getCmbColonia().getSelectedItem().toString());
		String acceso=null;
		
		try {
			acceso=getEmpleadosBo().insertarDirEmpl(getDireccionModelo());
		} catch (SQLException t) {
			JOptionPane.showMessageDialog(null, t.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// Escucha el cambio del area y llena los turno dependiendo el area
		if (e.getSource().equals(getRegistrarEmpleadoView().getCmbArea())) {
			if (!getRegistrarEmpleadoView().getCmbArea().getSelectedItem()
					.equals("--------Seleccione un área--------")) {
				getRegistrarEmpleadoView().getCmbTurno().setEnabled(true);
				llenarTurnos();
			} else {
				getRegistrarEmpleadoView().getCmbTurno().setEnabled(false);
			}
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
				getRegistrarEmpleadoView().getTxtApeMat(), getRegistrarEmpleadoView().getTxtPuesto());
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

	private void soloLetras(JTextField txtNombres, JTextField txtApePat, JTextField txtApeMat, JTextField txtPuesto) {
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
		txtPuesto.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isDigit(c)) {
					Toolkit.getDefaultToolkit().beep();
					e.consume();
				}
			}
		});
	}

	protected void llenarDireccion(String cp) {
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

	private void llenarComboArea() {
		// getRegistrarEmpleadoView().getCmbArea().removeAllItems();
		getRegistrarEmpleadoView().getCmbArea().addItem("--------Seleccione un área--------");
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

	private void llenarTurnos() {
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
			}
		} catch (SQLException t) {
			JOptionPane.showMessageDialog(null, t.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
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
