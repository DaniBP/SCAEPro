package com.greenpear.it.scaepro.view.gestionareas;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class AltaAreasView extends JInternalFrame {
	
	public JTextField txtArea;
	public JButton btnRegistrar;
	public JTextArea txtDescArea;
	public JButton btnLimpiar;
	
	public static void main(String[] args) {
		AltaAreasView area = new AltaAreasView();
		area.setVisible(true);
	}


	public AltaAreasView() {
		setFrameIcon(new ImageIcon(AltaAreasView.class.getResource("/img/Logo1.png")));
		pack();
		setIconifiable(true);
		setClosable(true);
		getContentPane().setBackground(SystemColor.activeCaption);
		setTitle("Gesti\u00F3n de \u00C1reas");
		setBounds(100, 100, 546, 340);
		getContentPane().setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBorder(new TitledBorder(null, "Datos del \u00C1rea", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(399, 129, 89, 23);
		panel.add(btnLimpiar);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(399, 63, 89, 23);
		panel.add(btnRegistrar);

	}
}
