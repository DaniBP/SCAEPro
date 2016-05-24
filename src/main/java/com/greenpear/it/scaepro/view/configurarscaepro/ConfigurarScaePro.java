package com.greenpear.it.scaepro.view.configurarscaepro;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Frame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import com.toedter.calendar.JDateChooser;
import java.awt.Rectangle;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class ConfigurarScaePro extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombreEmpleado;
	private JTextField txtApePat;
	private JTextField txtApeMat;
	private JTextField txtTelPer;
	private JTextField txtTelCasa;
	private JTextField txtCalle;
	private JTextField txtNoInt;
	private JTextField textField;
	private JTextField txtColonia;
	private JTextField txtCp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfigurarScaePro frame = new ConfigurarScaePro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConfigurarScaePro() {
		setTitle("Administraci\u00F3n SCAE Pro");
		setBounds(100, 100, 980, 620);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnEstatusDeConexin = new JMenu("Archivo");
		menuBar.add(mnEstatusDeConexin);
		
		JMenu mnUsuarios = new JMenu("Usuarios");
		menuBar.add(mnUsuarios);
		
		JMenu mnreas = new JMenu("\u00C1reas");
		menuBar.add(mnreas);
		
		JMenu mnHorarios = new JMenu("Horarios");
		menuBar.add(mnHorarios);
		
		JMenu mnEmpleados = new JMenu("Empleados");
		menuBar.add(mnEmpleados);
		
		JMenu mnJustificacin = new JMenu("Justificaci\u00F3n");
		menuBar.add(mnJustificacin);
		
		JMenu mnReportes = new JMenu("Reportes");
		menuBar.add(mnReportes);
		
		JMenu mnAdministracinMvil = new JMenu("Administraci\u00F3n m\u00F3vil");
		menuBar.add(mnAdministracinMvil);
		
		JMenu mnEstatusDeConexion = new JMenu("Estatus de conexi\u00F3n");
		menuBar.add(mnEstatusDeConexion);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Datos Personales", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel.setBounds(6, 6, 428, 301);
		contentPane.add(panel);
		
		JLabel lblNombre = new JLabel("Nombre:");
		
		txtNombreEmpleado = new JTextField();
		txtNombreEmpleado.setColumns(10);
		
		JLabel lblApellidoPaterno = new JLabel("Apellido Paterno:");
		
		JLabel lblApellidoMaterno = new JLabel("Apellido Materno:");
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento:");
		
		JLabel lblTelefonoPersonal = new JLabel("Telefono personal:");
		
		JLabel lblTelefonoCasa = new JLabel("Telefono casa:");
		
		JDateChooser dtFechaNacimiento = new JDateChooser();
		
		txtApePat = new JTextField();
		txtApePat.setColumns(10);
		
		txtApeMat = new JTextField();
		txtApeMat.setColumns(10);
		
		txtTelPer = new JTextField();
		txtTelPer.setColumns(10);
		
		txtTelCasa = new JTextField();
		txtTelCasa.setColumns(10);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("14px"),
				ColumnSpec.decode("126px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("3px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("266px"),},
			new RowSpec[] {
				RowSpec.decode("42px"),
				RowSpec.decode("28px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("30px"),
				FormSpecs.LABEL_COMPONENT_GAP_ROWSPEC,
				RowSpec.decode("28px"),
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("33px"),
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("29px"),
				FormSpecs.PARAGRAPH_GAP_ROWSPEC,
				RowSpec.decode("34px"),}));
		panel.add(lblNombre, "2, 2, left, fill");
		panel.add(txtNombreEmpleado, "4, 2, 3, 3, fill, top");
		panel.add(lblApellidoPaterno, "2, 4, fill, fill");
		panel.add(txtApePat, "4, 4, 3, 1, fill, top");
		panel.add(lblApellidoMaterno, "2, 6, fill, fill");
		panel.add(txtApeMat, "4, 6, 3, 1, fill, fill");
		panel.add(lblFechaDeNacimiento, "2, 8, 3, 1, left, fill");
		panel.add(dtFechaNacimiento, "6, 8, fill, top");
		panel.add(lblTelefonoPersonal, "2, 10, 3, 1, fill, fill");
		panel.add(txtTelPer, "6, 10, fill, fill");
		panel.add(lblTelefonoCasa, "2, 12, 3, 1, fill, fill");
		panel.add(txtTelCasa, "6, 12, fill, top");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Direcci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel_1.setBounds(427, 6, 306, 300);
		contentPane.add(panel_1);
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("14px"),
				ColumnSpec.decode("71px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("71px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("61px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("71px"),},
			new RowSpec[] {
				RowSpec.decode("72px"),
				RowSpec.decode("27px"),
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("27px"),
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("26px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("27px"),
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("26px"),
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("26px"),}));
		
		JLabel lblEstado = new JLabel("Estado:");
		panel_1.add(lblEstado, "2, 2, fill, center");
		
		JLabel lblMunicipio = new JLabel("Municipio:");
		panel_1.add(lblMunicipio, "2, 4, fill, center");
		
		JLabel lblCalle = new JLabel("Calle:");
		panel_1.add(lblCalle, "2, 6, fill, center");
		
		JLabel lblNoInt = new JLabel("No. Int:");
		panel_1.add(lblNoInt, "2, 8, fill, center");
		
		JLabel lblColonia = new JLabel("Colonia:");
		panel_1.add(lblColonia, "2, 10, left, center");
		
		JLabel lblCp = new JLabel("CP:");
		panel_1.add(lblCp, "2, 12, fill, center");
		
		JLabel lblNoExt = new JLabel("No. Ext:");
		panel_1.add(lblNoExt, "6, 8, fill, center");
		
		JComboBox cmbEstado = new JComboBox();
		cmbEstado.setModel(new DefaultComboBoxModel(new String[] {"-Seleccione un estado-"}));
		panel_1.add(cmbEstado, "4, 2, 5, 1, fill, top");
		
		JComboBox cmbMunicipio = new JComboBox();
		cmbMunicipio.setModel(new DefaultComboBoxModel(new String[] {"-Selecciona un municipio-"}));
		panel_1.add(cmbMunicipio, "4, 4, 5, 1, fill, top");
		
		txtCalle = new JTextField();
		panel_1.add(txtCalle, "4, 6, 5, 1, fill, top");
		txtCalle.setColumns(10);
		
		txtNoInt = new JTextField();
		panel_1.add(txtNoInt, "4, 8, fill, top");
		txtNoInt.setColumns(10);
		
		textField = new JTextField();
		textField.setColumns(10);
		panel_1.add(textField, "8, 8, fill, bottom");
		
		txtColonia = new JTextField();
		panel_1.add(txtColonia, "4, 10, 5, 1, fill, top");
		txtColonia.setColumns(10);
		
		txtCp = new JTextField();
		txtCp.setColumns(10);
		panel_1.add(txtCp, "4, 12, 5, 1, fill, top");
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Nip y fotograf\u00EDa", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(738, 6, 224, 544);
		contentPane.add(panel_2);
		panel_2.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("20px"),
				ColumnSpec.decode("174px"),},
			new RowSpec[] {
				RowSpec.decode("38px"),
				RowSpec.decode("186px"),
				FormSpecs.PARAGRAPH_GAP_ROWSPEC,
				RowSpec.decode("39px"),
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("186px"),
				FormSpecs.LABEL_COMPONENT_GAP_ROWSPEC,
				RowSpec.decode("39px"),}));
		
		JLabel lblNewLabel = new JLabel("");
		panel_2.add(lblNewLabel, "2, 2, fill, fill");
		
		JButton btnFoto = new JButton("Tomar fotografia");
		panel_2.add(btnFoto, "2, 4, fill, fill");
		
		JLabel label = new JLabel("");
		panel_2.add(label, "2, 6, fill, fill");
		
		JButton btnDispHuella = new JButton("Escanear Disp/Huella");
		panel_2.add(btnDispHuella, "2, 8, fill, fill");
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Asignaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(8, 310, 424, 234);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Selecciona área"}));
		comboBox_2.setBounds(69, 50, 258, 27);
		panel_3.add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Selecciona puesto"}));
		comboBox_3.setEnabled(false);
		comboBox_3.setBounds(69, 115, 258, 27);
		panel_3.add(comboBox_3);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"Selecciona turno"}));
		comboBox_4.setEnabled(false);
		comboBox_4.setBounds(69, 172, 258, 27);
		panel_3.add(comboBox_4);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Nomina", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(437, 312, 295, 234);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Periodo nominal: ");
		lblNewLabel_1.setBounds(17, 40, 120, 16);
		panel_4.add(lblNewLabel_1);
		
		JLabel lblDiaDeInicio = new JLabel("Dia de inicio:");
		lblDiaDeInicio.setBounds(17, 116, 120, 16);
		panel_4.add(lblDiaDeInicio);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Selecciona un periodo nominal"}));
		comboBox.setBounds(17, 68, 258, 27);
		panel_4.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setEnabled(false);
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Selecciona dia de inicio"}));
		comboBox_1.setBounds(17, 162, 258, 27);
		panel_4.add(comboBox_1);
	}
}
