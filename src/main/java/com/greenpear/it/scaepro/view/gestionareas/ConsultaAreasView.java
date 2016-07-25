package com.greenpear.it.scaepro.view.gestionareas;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class ConsultaAreasView extends JInternalFrame {
	public JTextField txtNombArea;
	public JButton btnBuscar;
	public JTable tablaAreas;

	/**
	 * Create the frame.
	 */
	public ConsultaAreasView() {
		setFrameIcon(new ImageIcon(ConsultaAreasView.class.getResource("/img/Logo1.png")));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Consultar \u00E1reas");
		getContentPane().setBackground(SystemColor.activeCaption);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 789, 401);
		getContentPane().setLayout(null);
		setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBorder(new TitledBorder(null, "Por nombre: ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(22, 87, 729, 273);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(SystemColor.activeCaption);
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
