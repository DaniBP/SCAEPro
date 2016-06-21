package com.greenpear.it.scaepro.view.gestionareas;

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

public class ConsultaAreasView extends JFrame {
	public JTextField txtNombArea;
	public JButton btnBuscar;
	public JTable tablaAreas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaAreasView frame = new ConsultaAreasView();
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
	public ConsultaAreasView() {
		setBounds(100, 100, 789, 401);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Por Nombre:", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel.setBounds(22, 11, 551, 65);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNombreDelrea = new JLabel("Nombre del \u00C1rea:");
		lblNombreDelrea.setBounds(10, 27, 112, 14);
		panel.add(lblNombreDelrea);
		
		txtNombArea = new JTextField();
		txtNombArea.setBounds(124, 24, 263, 20);
		panel.add(txtNombArea);
		txtNombArea.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(425, 23, 89, 23);
		panel.add(btnBuscar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(22, 87, 729, 273);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 11, 729, 251);
		panel_1.add(scrollPane);
		
		tablaAreas = new JTable();
		tablaAreas.setModel(new DefaultTableModel(
			new Object[][] {
				{},
				{},
				{},
				{},
			},
			new String[] {
			}
		));
		scrollPane.setViewportView(tablaAreas);
			
	}
}
