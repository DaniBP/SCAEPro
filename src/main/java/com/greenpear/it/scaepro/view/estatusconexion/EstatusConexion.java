package com.greenpear.it.scaepro.view.estatusconexion;



import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;
import java.awt.Panel;

import javax.swing.JInternalFrame;
import java.awt.Label;
import javax.swing.border.TitledBorder;

import com.greenpear.it.scaepro.view.accesosistema.PrincipalView;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.Image;

public class EstatusConexion extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EstatusConexion frame = new EstatusConexion();
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
	public EstatusConexion() {
		setTitle("Estatus de Conexi\u00F3n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 380, 287);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreCompleto = new JLabel("Conexi\u00F3n a Internet");
		lblNombreCompleto.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombreCompleto.setBounds(124, 55, 170, 26);
		contentPane.add(lblNombreCompleto);
		
		JLabel lblrea = new JLabel("Conexi\u00F3n a Web Service");
		lblrea.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblrea.setBounds(124, 146, 196, 19);
		contentPane.add(lblrea);
		
		JLabel label = new JLabel("");
		label.setBounds(10, 37, 79, 69);
		ImageIcon fot = new ImageIcon(PrincipalView.class.getResource("/img/verde.png"));
		Icon icono = new ImageIcon(fot.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
		label.setIcon(icono);
		contentPane.add(label);
		
		JLabel label2 = new JLabel("");
		label2.setBounds(10, 128, 79, 69);
		ImageIcon foti = new ImageIcon(PrincipalView.class.getResource("/img/rojo.png"));
		Icon icono2 = new ImageIcon(foti.getImage().getScaledInstance(label2.getWidth(), label2.getHeight(), Image.SCALE_DEFAULT));
		label2.setIcon(icono2);
		contentPane.add(label2);
	}
}
