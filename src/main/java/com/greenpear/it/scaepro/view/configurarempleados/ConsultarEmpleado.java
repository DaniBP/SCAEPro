package com.greenpear.it.scaepro.view.configurarempleados;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ConsultarEmpleado extends JFrame {
	private JTable table;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApePat;
	private JTextField txtApeMat;
	/**
	 * Create the frame.
	 */
	public ConsultarEmpleado() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 970, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Por nombre:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 10, 357, 179);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(18, 40, 120, 16);
		panel.add(lblNewLabel);
		
		JLabel lblApellidoPaterno = new JLabel("Apellido paterno:");
		lblApellidoPaterno.setBounds(18, 80, 120, 16);
		panel.add(lblApellidoPaterno);
		
		JLabel lblApellidoMaterno = new JLabel("Apellido materno:");
		lblApellidoMaterno.setBounds(18, 120, 120, 16);
		panel.add(lblApellidoMaterno);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(130, 35, 204, 26);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApePat = new JTextField();
		txtApePat.setColumns(10);
		txtApePat.setBounds(130, 75, 204, 26);
		panel.add(txtApePat);
		
		txtApeMat = new JTextField();
		txtApeMat.setColumns(10);
		txtApeMat.setBounds(130, 115, 204, 26);
		panel.add(txtApeMat);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Por area:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(379, 10, 357, 179);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblArea = new JLabel("Area:");
		lblArea.setBounds(16, 40, 61, 16);
		panel_1.add(lblArea);
		
		JLabel lblTurno = new JLabel("Turno:");
		lblTurno.setBounds(16, 80, 61, 16);
		panel_1.add(lblTurno);
		
		JLabel lblPuesto = new JLabel("Puesto:");
		lblPuesto.setBounds(16, 120, 61, 16);
		panel_1.add(lblPuesto);
		
		JComboBox btnArea = new JComboBox();
		btnArea.setModel(new DefaultComboBoxModel(new String[] {"------Seleccione area------"}));
		btnArea.setBounds(89, 36, 247, 27);
		panel_1.add(btnArea);
		
		JComboBox btnTurno = new JComboBox();
		btnTurno.setModel(new DefaultComboBoxModel(new String[] {"-----Seleccionar turno-----"}));
		btnTurno.setBounds(89, 76, 247, 27);
		panel_1.add(btnTurno);
		
		JComboBox btnPuesto = new JComboBox();
		btnPuesto.setModel(new DefaultComboBoxModel(new String[] {"-----Seleccione puesto-----"}));
		btnPuesto.setBounds(89, 116, 247, 27);
		panel_1.add(btnPuesto);
		
		JScrollPane ScrollConsulta = new JScrollPane(table);
		ScrollConsulta.setBounds(10, 201, 931, 330);
		contentPane.add(ScrollConsulta);
		ScrollConsulta.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
				
			new Object[][] {
			},
			new String[] {
				"Nombre completo","Area","Turno","Correo electronico","Modificar","Eliminar","Reporte","Mas"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(255);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(255);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(75);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setPreferredWidth(75);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(6).setPreferredWidth(75);
		table.getColumnModel().getColumn(6).setResizable(false);
		table.getColumnModel().getColumn(7).setPreferredWidth(75);
		table.getColumnModel().getColumn(7).setResizable(false);
		ScrollConsulta.setViewportView(table);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(770, 46, 140, 30);
		contentPane.add(btnConsultar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(770, 126, 140, 30);
		contentPane.add(btnLimpiar);
		
		
	}
}
