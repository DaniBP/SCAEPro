package com.greenpear.it.scaepro.view.gestionusuarios;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import javax.swing.border.TitledBorder;

public class AltaUsuarios extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		AltaUsuarios alta=new AltaUsuarios();
		alta.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public AltaUsuarios() {
		setTitle("Registrar Usuario");
		setBounds(100, 100, 420, 414);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Datos del Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 384, 312);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre del Usuario:");
		lblNewLabel.setBounds(10, 40, 123, 14);
		panel.add(lblNewLabel);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(10, 80, 123, 14);
		panel.add(lblContrasea);
		
		JLabel lblRepetirContrasea = new JLabel("Repetir Contrase\u00F1a:");
		lblRepetirContrasea.setBounds(10, 120, 123, 14);
		panel.add(lblRepetirContrasea);
		
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setBounds(10, 160, 123, 14);
		panel.add(lblCorreo);
		
		JLabel lblRepetirCorreo = new JLabel("Repetir Correo:");
		lblRepetirCorreo.setBounds(10, 202, 123, 14);
		panel.add(lblRepetirCorreo);
		
		textField = new JTextField();
		textField.setBounds(143, 37, 231, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(143, 77, 231, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(143, 117, 231, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(143, 157, 231, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(143, 199, 231, 20);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblEstatus = new JLabel("Estatus:");
		lblEstatus.setBounds(10, 240, 123, 14);
		panel.add(lblEstatus);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Activo");
		rdbtnNewRadioButton.setBounds(143, 236, 92, 23);
		panel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Inactivo");
		rdbtnNewRadioButton_1.setBounds(282, 236, 92, 23);
		panel.add(rdbtnNewRadioButton_1);
		
		JCheckBox chckbxRecibirNotificacionesDe = new JCheckBox("Recibir notificaciones de empleados");
		chckbxRecibirNotificacionesDe.setBounds(143, 276, 231, 23);
		panel.add(chckbxRecibirNotificacionesDe);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(10, 346, 89, 23);
		getContentPane().add(btnRegistrar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(160, 346, 89, 23);
		getContentPane().add(btnLimpiar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(305, 346, 89, 23);
		getContentPane().add(btnSalir);

	}
}
