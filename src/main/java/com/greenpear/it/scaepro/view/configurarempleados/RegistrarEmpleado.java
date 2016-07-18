package com.greenpear.it.scaepro.view.configurarempleados;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.DateFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import com.toedter.calendar.JDateChooser;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class RegistrarEmpleado extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombres;
	private JTextField txtApePat;
	private JTextField txtApeMat;
	private JTextField txtTelCel;
	private JTextField txtTelCasa;
	private JTextField txtCalle;
	private JTextField txtNumeroExt;
	private JTextField txtNumeroInt;
	private JButton btnCapturarFoto;
	private JButton btnLeerHuella;
	private JButton btnRegistrar;
	private JComboBox cmbArea;
	private JComboBox cmbPeriodoNominal;
	private JComboBox cmbDiaNomina;
	private JComboBox cmbTurno;
	private JComboBox cmbColonia;
	private JDateChooser dtfechaNac;
	private JTextField txtCp;
	private JTextField txtEstado;
	private JTextField txtMunicipio;
	private JLabel lblPuesto;
	private JTextField txtPuesto;
	private JLabel lblFotografia;
	
	public RegistrarEmpleado() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 970, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pnlDatosPersonales = new JPanel();
		pnlDatosPersonales.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Datos personales:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlDatosPersonales.setBounds(10, 10, 360, 307);
		contentPane.add(pnlDatosPersonales);
		pnlDatosPersonales.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre (s):");
		lblNombre.setBounds(16, 24, 120, 16);
		pnlDatosPersonales.add(lblNombre);

		JLabel lblApellidoPaterno = new JLabel("Apellido paterno:");
		lblApellidoPaterno.setBounds(16, 69, 120, 16);
		pnlDatosPersonales.add(lblApellidoPaterno);

		JLabel lblApellidoManterno = new JLabel("Apellido materno:");
		lblApellidoManterno.setBounds(16, 119, 120, 16);
		pnlDatosPersonales.add(lblApellidoManterno);

		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento:");
		lblFechaDeNacimiento.setBounds(16, 169, 140, 16);
		pnlDatosPersonales.add(lblFechaDeNacimiento);

		JLabel lblTelefonoPersonal = new JLabel("Telefono personal (Celular):");
		lblTelefonoPersonal.setBounds(16, 219, 180, 16);
		pnlDatosPersonales.add(lblTelefonoPersonal);

		JLabel lblTelefonoPersonalcasa = new JLabel("Telefono personal (Casa):");
		lblTelefonoPersonalcasa.setBounds(16, 269, 180, 16);
		pnlDatosPersonales.add(lblTelefonoPersonalcasa);

		txtNombres = new JTextField();
		txtNombres.setBounds(129, 19, 212, 26);
		pnlDatosPersonales.add(txtNombres);
		txtNombres.setColumns(10);

		txtApePat = new JTextField();
		txtApePat.setColumns(10);
		txtApePat.setBounds(129, 66, 212, 26);
		pnlDatosPersonales.add(txtApePat);

		txtApeMat = new JTextField();
		txtApeMat.setColumns(10);
		txtApeMat.setBounds(129, 114, 212, 26);
		pnlDatosPersonales.add(txtApeMat);

		txtTelCel = new JTextField();
		txtTelCel.setBounds(196, 214, 145, 26);
		pnlDatosPersonales.add(txtTelCel);
		txtTelCel.setColumns(10);

		txtTelCasa = new JTextField();
		txtTelCasa.setColumns(10);
		txtTelCasa.setBounds(196, 264, 145, 26);
		pnlDatosPersonales.add(txtTelCasa);

		dtfechaNac = new JDateChooser();
		dtfechaNac.setBounds(196, 159, 145, 26);
		pnlDatosPersonales.add(dtfechaNac);

		JPanel pnlDireccion = new JPanel();
		pnlDireccion.setBorder(
				new TitledBorder(null, "Direcci\u00F3n:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlDireccion.setBounds(381, 10, 360, 307);
		contentPane.add(pnlDireccion);
		pnlDireccion.setLayout(null);

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(18, 70, 80, 16);
		pnlDireccion.add(lblEstado);

		JLabel lblMunicipio = new JLabel("Municipio:");
		lblMunicipio.setBounds(18, 115, 80, 16);
		pnlDireccion.add(lblMunicipio);

		JLabel lblCalle = new JLabel("Calle:");
		lblCalle.setBounds(18, 265, 80, 16);
		pnlDireccion.add(lblCalle);

		JLabel lblNumeroExt = new JLabel("Numero Ext:");
		lblNumeroExt.setBounds(18, 215, 87, 16);
		pnlDireccion.add(lblNumeroExt);

		JLabel lblColonia = new JLabel("Colonia:");
		lblColonia.setBounds(18, 165, 80, 16);
		pnlDireccion.add(lblColonia);

		JLabel lblNumeroInt = new JLabel("Numero Int:");
		lblNumeroInt.setBounds(181, 215, 87, 16);
		pnlDireccion.add(lblNumeroInt);

		txtCalle = new JTextField();
		txtCalle.setColumns(10);
		txtCalle.setBounds(94, 260, 243, 26);
		pnlDireccion.add(txtCalle);

		txtNumeroExt = new JTextField();
		txtNumeroExt.setBounds(97, 210, 72, 26);
		pnlDireccion.add(txtNumeroExt);
		txtNumeroExt.setColumns(10);

		txtNumeroInt = new JTextField();
		txtNumeroInt.setColumns(10);
		txtNumeroInt.setBounds(265, 210, 72, 26);
		pnlDireccion.add(txtNumeroInt);

		JLabel label = new JLabel("C.P:");
		label.setBounds(18, 33, 80, 16);
		pnlDireccion.add(label);

		txtCp = new JTextField();
		txtCp.setColumns(10);
		txtCp.setBounds(94, 28, 243, 26);
		pnlDireccion.add(txtCp);

		txtEstado = new JTextField();
		txtEstado.setEnabled(false);
		txtEstado.setBounds(94, 65, 243, 26);
		pnlDireccion.add(txtEstado);
		txtEstado.setColumns(10);

		txtMunicipio = new JTextField();
		txtMunicipio.setEnabled(false);
		txtMunicipio.setColumns(10);
		txtMunicipio.setBounds(94, 110, 243, 26);
		pnlDireccion.add(txtMunicipio);

		cmbColonia = new JComboBox();
		cmbColonia.setModel(new DefaultComboBoxModel(new String[] { "---Seleccione su colonia---" }));
		cmbColonia.setEnabled(false);
		cmbColonia.setBounds(94, 161, 243, 26);
		pnlDireccion.add(cmbColonia);

		JPanel pnlNipFotografia = new JPanel();
		pnlNipFotografia.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Nip y fotograf\u00EDa", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlNipFotografia.setBounds(749, 10, 215, 458);
		contentPane.add(pnlNipFotografia);
		pnlNipFotografia.setLayout(null);

		lblFotografia = new JLabel("");
		lblFotografia.setBounds(20, 22, 170, 162);
		pnlNipFotografia.add(lblFotografia);

		JLabel lblHuellaDigital = new JLabel("");
		lblHuellaDigital.setBounds(51, 228, 120, 170);
		pnlNipFotografia.add(lblHuellaDigital);

		btnLeerHuella = new JButton("Detectar huella");
		btnLeerHuella.setBounds(35, 410, 140, 30);
		pnlNipFotografia.add(btnLeerHuella);

		btnCapturarFoto = new JButton("Capturar foto");
		btnCapturarFoto.setBounds(35, 186, 140, 30);
		pnlNipFotografia.add(btnCapturarFoto);

		JPanel pnlAsignacion = new JPanel();
		pnlAsignacion.setBorder(
				new TitledBorder(null, "Asignaci\u00F3n:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlAsignacion.setBounds(10, 334, 360, 201);
		contentPane.add(pnlAsignacion);
		pnlAsignacion.setLayout(null);

		cmbArea = new JComboBox();
		cmbArea.setBounds(20, 101, 311, 27);
		pnlAsignacion.add(cmbArea);

		cmbTurno = new JComboBox();
		cmbTurno.setEnabled(false);
		cmbTurno.setModel(new DefaultComboBoxModel(new String[] {"--------Seleccione un turno--------", "a"}));
		cmbTurno.setBounds(20, 154, 311, 27);
		pnlAsignacion.add(cmbTurno);

		lblPuesto = new JLabel("Puesto :");
		lblPuesto.setBounds(20, 46, 61, 16);
		pnlAsignacion.add(lblPuesto);

		txtPuesto = new JTextField();
		txtPuesto.setBounds(78, 41, 253, 26);
		pnlAsignacion.add(txtPuesto);
		txtPuesto.setColumns(10);

		JPanel pnlNomina = new JPanel();
		pnlNomina.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Nomina:",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlNomina.setBounds(381, 334, 360, 201);
		contentPane.add(pnlNomina);
		pnlNomina.setLayout(null);

		cmbPeriodoNominal = new JComboBox();
		cmbPeriodoNominal.setModel(new DefaultComboBoxModel(new String[] {"----Selecciona un periodo nominal----", "Semanal", "Quincenal", "Mensual"}));
		cmbPeriodoNominal.setBounds(22, 61, 311, 27);
		pnlNomina.add(cmbPeriodoNominal);

		cmbDiaNomina = new JComboBox();
		cmbDiaNomina.setEnabled(false);
		cmbDiaNomina.setModel(new DefaultComboBoxModel(new String[] {"---------Selecciona un dia---------"}));
		cmbDiaNomina.setBounds(22, 124, 311, 27);
		pnlNomina.add(cmbDiaNomina);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(790, 480, 140, 30);
		contentPane.add(btnRegistrar);
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public JTextField getTxtNombres() {
		return txtNombres;
	}

	public JTextField getTxtApePat() {
		return txtApePat;
	}

	public JTextField getTxtApeMat() {
		return txtApeMat;
	}

	public JTextField getTxtTelCel() {
		return txtTelCel;
	}

	public JTextField getTxtTelCasa() {
		return txtTelCasa;
	}

	public JTextField getTxtCalle() {
		return txtCalle;
	}

	public JTextField getTxtNumeroExt() {
		return txtNumeroExt;
	}

	public JTextField getTxtNumeroInt() {
		return txtNumeroInt;
	}

	public JButton getBtnCapturarFoto() {
		return btnCapturarFoto;
	}

	public JButton getBtnLeerHuella() {
		return btnLeerHuella;
	}

	public JButton getBtnRegistrar() {
		return btnRegistrar;
	}

	public JComboBox getCmbArea() {
		return cmbArea;
	}

	public JComboBox getCmbPeriodoNominal() {
		return cmbPeriodoNominal;
	}

	public JComboBox getCmbDiaNomina() {
		return cmbDiaNomina;
	}

	public JDateChooser getFecha() {
		return dtfechaNac;
	}

	public JComboBox getCmbTurno() {
		return cmbTurno;
	}

	public JTextField getTxtCp() {
		return txtCp;
	}

	public void setTxtCp(JTextField txtCp) {
		this.txtCp = txtCp;
	}

	public JTextField getTxtEstado() {
		return txtEstado;
	}

	public void setTxtEstado(JTextField txtEstado) {
		this.txtEstado = txtEstado;
	}

	public JTextField getTxtMunicipio() {
		return txtMunicipio;
	}

	public void setTxtMunicipio(JTextField txtMunicipio) {
		this.txtMunicipio = txtMunicipio;
	}

	public JComboBox getCmbColonia() {
		return cmbColonia;
	}

	public void setCmbColonia(JComboBox cmbColonia) {
		this.cmbColonia = cmbColonia;
	}

	public JTextField getTxtPuesto() {
		return txtPuesto;
	}

	public void setTxtPuesto(JTextField txtPuesto) {
		this.txtPuesto = txtPuesto;
	}

	public JLabel getLblFotografia() {
		return lblFotografia;
	}
	
}