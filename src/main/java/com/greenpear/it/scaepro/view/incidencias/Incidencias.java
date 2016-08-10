package com.greenpear.it.scaepro.view.incidencias;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

public class Incidencias extends JInternalFrame {

	private JTextField txtNombre;
	private JTextField txtApePat;
	private JTextField txtApeMat;
	private JComboBox cmbArea;
	private JComboBox cmbTurno;
	private JButton btnConsultar;
	private JButton btnLimpiar;
	private JTable table;
	private JRadioButton rdbtnPorAsignacion;
	private JRadioButton rdbtnPorArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Incidencias frame = new Incidencias();
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
	public Incidencias() {
		setFrameIcon(new ImageIcon(Incidencias.class.getResource("/img/Logo1.png")));
		setClosable(true);
		setIconifiable(true);
		setTitle("Incidencias");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 965, 595);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pnlNombre = new JPanel();
		pnlNombre.setLayout(null);
		pnlNombre.setBorder(new TitledBorder(null, "Por nombre:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlNombre.setBackground(SystemColor.activeCaption);
		pnlNombre.setBounds(10, 21, 357, 179);
		contentPane.add(pnlNombre);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 56, 120, 16);
		pnlNombre.add(lblNombre);

		JLabel lblApePat = new JLabel("Apellido paterno:");
		lblApePat.setBounds(10, 96, 120, 16);
		pnlNombre.add(lblApePat);

		JLabel lblApeMat = new JLabel("Apellido materno:");
		lblApeMat.setBounds(10, 136, 120, 16);
		pnlNombre.add(lblApeMat);

		txtNombre = new JTextField();
		txtNombre.setEnabled(false);
		txtNombre.setColumns(10);
		txtNombre.setBounds(122, 51, 204, 26);
		pnlNombre.add(txtNombre);

		txtApePat = new JTextField();
		txtApePat.setEnabled(false);
		txtApePat.setColumns(10);
		txtApePat.setBounds(122, 91, 204, 26);
		pnlNombre.add(txtApePat);

		txtApeMat = new JTextField();
		txtApeMat.setEnabled(false);
		txtApeMat.setColumns(10);
		txtApeMat.setBounds(122, 131, 204, 26);
		pnlNombre.add(txtApeMat);
		
		rdbtnPorArea = new JRadioButton("Por area:");
		rdbtnPorArea.setBackground(SystemColor.activeCaption);
		rdbtnPorArea.setBounds(132, 21, 79, 23);
		pnlNombre.add(rdbtnPorArea);

		JPanel pnlArea = new JPanel();
		pnlArea.setLayout(null);
		pnlArea.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Por area:",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlArea.setBackground(SystemColor.activeCaption);
		pnlArea.setBounds(379, 21, 357, 179);
		contentPane.add(pnlArea);

		JLabel lblArea = new JLabel("Area:");
		lblArea.setBounds(10, 59, 61, 16);
		pnlArea.add(lblArea);

		JLabel lblTurno = new JLabel("Turno:");
		lblTurno.setBounds(10, 117, 61, 16);
		pnlArea.add(lblTurno);

		cmbArea = new JComboBox();
		cmbArea.setEnabled(false);
		cmbArea.setModel(new DefaultComboBoxModel(new String[] {"------Seleccione area------"}));
		cmbArea.setBounds(81, 54, 247, 27);
		pnlArea.add(cmbArea);

		cmbTurno = new JComboBox();
		cmbTurno.setEnabled(false);
		cmbTurno.setModel(new DefaultComboBoxModel(new String[] {"-----Seleccionar turno-----"}));
		cmbTurno.setBounds(81, 112, 247, 27);
		pnlArea.add(cmbTurno);
		
		rdbtnPorAsignacion = new JRadioButton("Por asignacion:");
		rdbtnPorAsignacion.setBackground(SystemColor.activeCaption);
		rdbtnPorAsignacion.setBounds(128, 18, 99, 23);
		pnlArea.add(rdbtnPorAsignacion);

		JScrollPane scrollConsulta = new JScrollPane((Component) null);
		scrollConsulta.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollConsulta.setBackground(SystemColor.activeCaption);
		scrollConsulta.setBounds(10, 211, 931, 330);
		contentPane.add(scrollConsulta);
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));
		scrollConsulta.setViewportView(table);

		btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(773, 50, 140, 30);
		contentPane.add(btnConsultar);

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(773, 120, 140, 30);
		contentPane.add(btnLimpiar);
		ButtonGroup radios= new ButtonGroup();
		radios.add(rdbtnPorArea);
		radios.add(rdbtnPorAsignacion);
	}
	// Setters and Getters

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JTextField getTxtApePat() {
		return txtApePat;
	}

	public void setTxtApePat(JTextField txtApePat) {
		this.txtApePat = txtApePat;
	}

	public JTextField getTxtApeMat() {
		return txtApeMat;
	}

	public void setTxtApeMat(JTextField txtApeMat) {
		this.txtApeMat = txtApeMat;
	}

	public JComboBox getCmbArea() {
		return cmbArea;
	}

	public void setCmbArea(JComboBox cmbArea) {
		this.cmbArea = cmbArea;
	}

	public JComboBox getCmbTurno() {
		return cmbTurno;
	}

	public void setCmbTurno(JComboBox cmbTurno) {
		this.cmbTurno = cmbTurno;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(JButton btnConsultar) {
		this.btnConsultar = btnConsultar;
	}

	public JButton getBtnLimpiar() {
		return btnLimpiar;
	}

	public void setBtnLimpiar(JButton btnLimpiar) {
		this.btnLimpiar = btnLimpiar;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JRadioButton getRdbtnPorAsignacion() {
		return rdbtnPorAsignacion;
	}

	public void setRdbtnPorAsignacion(JRadioButton rdbtnPorAsignacion) {
		this.rdbtnPorAsignacion = rdbtnPorAsignacion;
	}

	public JRadioButton getRdbtnPorArea() {
		return rdbtnPorArea;
	}

	public void setRdbtnPorArea(JRadioButton rdbtnPorArea) {
		this.rdbtnPorArea = rdbtnPorArea;
	}
	
}
