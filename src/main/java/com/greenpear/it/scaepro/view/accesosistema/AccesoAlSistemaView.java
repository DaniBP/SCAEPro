package com.greenpear.it.scaepro.view.accesosistema;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class AccesoAlSistemaView extends JFrame {

	public JPanel contentPane;
	public JTextField txtUsuario;
	public JButton btnAcceder;
	public JPasswordField txtContrase;
	private JLabel label;
	public JButton btnRegresar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccesoAlSistemaView frame = new AccesoAlSistemaView();
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
	public AccesoAlSistemaView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AccesoAlSistemaView.class.getResource("/img/Logo1.png")));
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 320, 475);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Nombre de usuario:");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setBounds(97, 135, 111, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasea.setBounds(110, 211, 84, 14);
		contentPane.add(lblContrasea);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(54, 160, 190, 40);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		btnAcceder = new JButton("Acceder");
		btnAcceder.setBounds(54, 313, 90, 40);
		contentPane.add(btnAcceder);
		
		txtContrase = new JPasswordField();
		txtContrase.setBounds(54, 236, 190, 40);
		contentPane.add(txtContrase);
		
		label = new JLabel("");
		label.setBounds(86, 11, 140, 102);
		ImageIcon fot = new ImageIcon(PrincipalView.class.getResource("/img/Login Icono.png"));
		Icon icono = new ImageIcon(fot.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
		label.setIcon(icono);
		contentPane.add(label);
		
		btnRegresar = new JButton("Regresar");
		btnRegresar.setBounds(154, 313, 90, 40);
		contentPane.add(btnRegresar);
	}
}
