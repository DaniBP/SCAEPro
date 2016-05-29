package com.greenpear.it.scaepro.view.administracionmovil;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.SystemColor;

public class AdminNoticiasView extends JFrame {

	private JPanel contentPane;

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
		setTitle("Administrar noticias");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 250);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNoticiaNueva = new JButton("Noticia nueva");
		btnNoticiaNueva.setBounds(57, 42, 180, 40);
		contentPane.add(btnNoticiaNueva);
		
		JButton btnNoticiasExistentes = new JButton("Noticias existentes");
		btnNoticiasExistentes.setBounds(57, 106, 180, 40);
		contentPane.add(btnNoticiasExistentes);
	}
}
