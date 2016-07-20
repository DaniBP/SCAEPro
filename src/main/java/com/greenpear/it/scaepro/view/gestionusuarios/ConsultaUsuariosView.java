package com.greenpear.it.scaepro.view.gestionusuarios;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.ScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.UIManager;

public class ConsultaUsuariosView extends JFrame {
	public JTextField txtNombreUsuario;
	public JButton btnBuscar;
	public JTable tablaUsuarios;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaUsuariosView frame = new ConsultaUsuariosView();
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
	public ConsultaUsuariosView() {
		setBounds(100, 100, 615, 401);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Buscar por Nombre:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(27, 11, 546, 65);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Nombre del Usuario:");
		lblUsuario.setBounds(10, 27, 112, 14);
		panel.add(lblUsuario);
		
		txtNombreUsuario = new JTextField();
		txtNombreUsuario.setBounds(124, 24, 263, 20);
		panel.add(txtNombreUsuario);
		txtNombreUsuario.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(425, 23, 89, 23);
		panel.add(btnBuscar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(27, 87, 546, 273);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 526, 251);
		panel_1.add(scrollPane);
		
		tablaUsuarios = new JTable();
		tablaUsuarios.setModel(new DefaultTableModel(
			new Object[][] {
				{},
				{},
				{},
				{},
				{},
				{},
				{},
			},
			new String[] {
			}
		));
		scrollPane.setViewportView(tablaUsuarios);
			
	}
}
