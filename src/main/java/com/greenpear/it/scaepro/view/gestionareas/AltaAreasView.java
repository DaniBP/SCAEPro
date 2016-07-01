package com.greenpear.it.scaepro.view.gestionareas;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFrame;

public class AltaAreasView extends JFrame {
	public JTextField txtArea;
	public JButton btnRegistrar;
	public JTextArea txtDescArea;
	public JButton btnLimpiar;


	public AltaAreasView() {
		setResizable(true);
		setTitle("Gesti\u00F3n de \u00C1reas");
		setBounds(100, 100, 546, 340);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos del \u00C1rea", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setForeground(Color.WHITE);
		panel.setToolTipText("");
		panel.setBounds(10, 11, 510, 288);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNombreDelrea = new JLabel("Nombre del \u00C1rea:");
		lblNombreDelrea.setBounds(10, 67, 104, 14);
		panel.add(lblNombreDelrea);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setBounds(10, 133, 104, 14);
		panel.add(lblDescripcin);
		
		txtArea = new JTextField();
		txtArea.setBounds(124, 64, 246, 20);
		panel.add(txtArea);
		txtArea.setColumns(10);
		
		txtDescArea = new JTextArea();
		txtDescArea.setBounds(124, 128, 246, 90);
		panel.add(txtDescArea);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(399, 63, 89, 23);
		panel.add(btnRegistrar);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(399, 129, 89, 23);
		panel.add(btnLimpiar);

	}
}
