package com.greenpear.it.scaepro.view.configurarempleados;

import java.awt.BorderLayout;
import java.awt.EventQueue;

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
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	public RegistrarEmpleado() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 970, 580
				);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnlDatosPersonales = new JPanel();
		pnlDatosPersonales.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Datos personales:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
		
		JDateChooser dtchFechaNacimiento = new JDateChooser();
		dtchFechaNacimiento.setBounds(161, 164, 180, 26);
		pnlDatosPersonales.add(dtchFechaNacimiento);
		
		txtTelCel = new JTextField();
		txtTelCel.setBounds(196, 214, 145, 26);
		pnlDatosPersonales.add(txtTelCel);
		txtTelCel.setColumns(10);
		
		txtTelCasa = new JTextField();
		txtTelCasa.setColumns(10);
		txtTelCasa.setBounds(196, 264, 145, 26);
		pnlDatosPersonales.add(txtTelCasa);
		
		JPanel pnlDirección = new JPanel();
		pnlDirección.setBorder(new TitledBorder(null, "Direcci\u00F3n:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlDirección.setBounds(381, 10, 360, 307);
		contentPane.add(pnlDirección);
		pnlDirección.setLayout(null);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(17, 24, 80, 16);
		pnlDirección.add(lblEstado);
		
		JLabel lblMunicipio = new JLabel("Municipio:");
		lblMunicipio.setBounds(17, 69, 80, 16);
		pnlDirección.add(lblMunicipio);
		
		JLabel lblCalle = new JLabel("Calle:");
		lblCalle.setBounds(17, 119, 80, 16);
		pnlDirección.add(lblCalle);
		
		JLabel lblNumeroExt = new JLabel("Numero Ext:");
		lblNumeroExt.setBounds(17, 169, 87, 16);
		pnlDirección.add(lblNumeroExt);
		
		JLabel lblColonia = new JLabel("Colonia:");
		lblColonia.setBounds(17, 219, 80, 16);
		pnlDirección.add(lblColonia);
		
		JLabel lblCp = new JLabel("C.P:");
		lblCp.setBounds(17, 269, 80, 16);
		pnlDirección.add(lblCp);
		
		JLabel lblNumeroInt = new JLabel("Numero Int:");
		lblNumeroInt.setBounds(185, 169, 87, 16);
		pnlDirección.add(lblNumeroInt);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(93, 114, 243, 26);
		pnlDirección.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(93, 214, 243, 26);
		pnlDirección.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(93, 264, 243, 26);
		pnlDirección.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setBounds(101, 164, 72, 26);
		pnlDirección.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(264, 164, 72, 26);
		pnlDirección.add(textField_6);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"---Seleccione un estado---"}));
		comboBox.setBounds(95, 20, 241, 27);
		pnlDirección.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"--Selecciona un municipio--"}));
		comboBox_1.setBounds(95, 65, 241, 27);
		pnlDirección.add(comboBox_1);
		
		JPanel pnlNipFotografia = new JPanel();
		pnlNipFotografia.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Nip y fotograf\u00EDa", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlNipFotografia.setBounds(749, 10, 215, 525);
		contentPane.add(pnlNipFotografia);
		pnlNipFotografia.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(RegistrarEmpleado.class.getResource("/img/emplphoto.png")));
		lblNewLabel.setBounds(18, 22, 170, 162);
		pnlNipFotografia.add(lblNewLabel);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(RegistrarEmpleado.class.getResource("/img/huell.png")));
		label.setBounds(51, 265, 120, 170);
		pnlNipFotografia.add(label);
		
		JButton btnLeerHuella = new JButton("Detectar huella");
		btnLeerHuella.setBounds(51, 457, 120, 30);
		pnlNipFotografia.add(btnLeerHuella);
		
		JButton btnCapturarFoto = new JButton("Capturar foto");
		btnCapturarFoto.setBounds(51, 199, 120, 30);
		pnlNipFotografia.add(btnCapturarFoto);
		
		JPanel pnlAsignación = new JPanel();
		pnlAsignación.setBorder(new TitledBorder(null, "Asignaci\u00F3n:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlAsignación.setBounds(10, 334, 360, 201);
		contentPane.add(pnlAsignación);
		pnlAsignación.setLayout(null);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"--------Seleccione un área--------"}));
		comboBox_2.setBounds(20, 46, 311, 27);
		pnlAsignación.add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"-------Seleccione un puesto-------"}));
		comboBox_3.setBounds(20, 101, 311, 27);
		pnlAsignación.add(comboBox_3);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"--------Seleccione un turno--------"}));
		comboBox_4.setBounds(20, 154, 311, 27);
		pnlAsignación.add(comboBox_4);
		
		JPanel pnlNomina = new JPanel();
		pnlNomina.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Nomina:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlNomina.setBounds(381, 334, 360, 201);
		contentPane.add(pnlNomina);
		pnlNomina.setLayout(null);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setModel(new DefaultComboBoxModel(new String[] {"----Selecciona un periodo nominal----"}));
		comboBox_5.setBounds(22, 61, 311, 27);
		pnlNomina.add(comboBox_5);
		
		JComboBox comboBox_6 = new JComboBox();
		comboBox_6.setModel(new DefaultComboBoxModel(new String[] {"---------Selecciona un dia---------"}));
		comboBox_6.setBounds(22, 124, 311, 27);
		pnlNomina.add(comboBox_6);
	}
}
