package com.greenpear.it.scaepro.view.administracionmovil;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JSlider;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class NoticiaView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NoticiaView frame = new NoticiaView();
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
	public NoticiaView() {
		setTitle("Nueva Noticia");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 280, 360);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTtulo = new JLabel("T\u00EDtulo:");
		lblTtulo.setBounds(26, 11, 46, 14);
		contentPane.add(lblTtulo);
		
		textField = new JTextField();
		textField.setBounds(26, 36, 215, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNoticia = new JLabel("Noticia:");
		lblNoticia.setBounds(26, 67, 46, 14);
		contentPane.add(lblNoticia);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(24, 92, 217, 87);
		contentPane.add(textPane);
		
		JLabel lblImgen = new JLabel("Im\u00E1gen:");
		lblImgen.setBounds(26, 190, 46, 14);
		contentPane.add(lblImgen);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(82, 270, 90, 40);
		contentPane.add(btnRegistrar);
		
		JButton btnSubirFoto = new JButton("Seleccionar imagen...");
		btnSubirFoto.setHorizontalAlignment(SwingConstants.LEFT);
		btnSubirFoto.setIcon(new ImageIcon(NoticiaView.class.getResource("/javax/swing/plaf/basic/icons/image-delayed.png")));
		btnSubirFoto.setBounds(26, 212, 215, 32);
		contentPane.add(btnSubirFoto);
	}
}
