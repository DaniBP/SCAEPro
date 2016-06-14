package com.greenpear.it.scaepro.view.gestionusuarios;

import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.sun.prism.paint.Color;

public class ConsultaUsuarios extends JFrame {
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaUsuarios frame = new ConsultaUsuarios();
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
	public ConsultaUsuarios() {
		setTitle("Consultar Usuarios");
		setBounds(100, 100, 621, 429);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(null), "Buscar por:", TitledBorder.LEADING, TitledBorder.TOP, null, new java.awt.Color(0, 0, 0)));
		panel.setBounds(10, 11, 400, 90);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre de usuario:");
		lblNewLabel.setBounds(10, 30, 124, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Estatus:");
		lblNewLabel_1.setBounds(10, 60, 124, 14);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(144, 27, 240, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(144, 57, 240, 20);
		panel.add(comboBox);
		
		JButton btnNewButton = new JButton("Buscar Usuario");
		btnNewButton.setBounds(436, 47, 143, 23);
		getContentPane().add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 112, 585, 225);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 565, 203);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Nombre de usuario", "Correo electr\u00F3nico", "Estatus", "Modificar", "Eliminar"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_1 = new JButton("Salir");
		btnNewButton_1.setBounds(240, 348, 124, 23);
		getContentPane().add(btnNewButton_1);

	}
}
