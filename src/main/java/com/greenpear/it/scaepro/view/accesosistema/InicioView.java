package com.greenpear.it.scaepro.view.accesosistema;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class InicioView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicioView frame = new InicioView();
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
	public InicioView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(InicioView.class.getResource("/img/LogoGreenPearfin.png")));
		setResizable(false);
		setTitle("Inicio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAdministracin = new JButton("Administraci\u00F3n");
		btnAdministracin.setBounds(67, 172, 140, 40);
		contentPane.add(btnAdministracin);
		
		JButton btnControlDeAcceso = new JButton("Control de \r\nA\r\ncceso");
		btnControlDeAcceso.setBounds(243, 172, 140, 40);
		contentPane.add(btnControlDeAcceso);
		
		JLabel label = new JLabel("");
		label.setBounds(78, 41, 120, 120);
		ImageIcon fot = new ImageIcon(PrincipalView.class.getResource("/img/Admin Icono.png"));
		Icon icono = new ImageIcon(fot.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
		label.setIcon(icono);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(252, 41, 120, 120);
		ImageIcon fot2 = new ImageIcon(PrincipalView.class.getResource("/img/Acceso Icono.png"));
		Icon icono2 = new ImageIcon(fot2.getImage().getScaledInstance(label_1.getWidth(), label_1.getHeight(), Image.SCALE_DEFAULT));
		label_1.setIcon(icono2);
		contentPane.add(label_1);
	}
}
