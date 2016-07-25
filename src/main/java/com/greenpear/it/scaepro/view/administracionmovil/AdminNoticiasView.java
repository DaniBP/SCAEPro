package com.greenpear.it.scaepro.view.administracionmovil;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class AdminNoticiasView extends JInternalFrame {

	public JPanel contentPane;
	public JButton btnNoticiaNueva;
	public JButton btnNoticiasExistentes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminNoticiasView frame = new AdminNoticiasView();
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
	public AdminNoticiasView() {
		setFrameIcon(new ImageIcon(AdminNoticiasView.class.getResource("/img/Logo1.png")));
		setIconifiable(true);
		setClosable(true);
		setTitle("Administrar noticias");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 300, 250);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		btnNoticiaNueva = new JButton("Noticia nueva");
		btnNoticiaNueva.setBounds(57, 42, 180, 40);
		contentPane.add(btnNoticiaNueva);
		
		btnNoticiasExistentes = new JButton("Noticias existentes");
		btnNoticiasExistentes.setBounds(57, 106, 180, 40);
		contentPane.add(btnNoticiasExistentes);
	}
}
