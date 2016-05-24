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

public class NuevaNoticiaView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevaNoticiaView frame = new NuevaNoticiaView();
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
	public NuevaNoticiaView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTtulo = new JLabel("T\u00EDtulo:");
		lblTtulo.setBounds(78, 52, 46, 14);
		contentPane.add(lblTtulo);
		
		textField = new JTextField();
		textField.setBounds(134, 49, 120, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNoticia = new JLabel("Noticia:");
		lblNoticia.setBounds(78, 116, 46, 14);
		contentPane.add(lblNoticia);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(76, 141, 217, 87);
		contentPane.add(textPane);
		
		JLabel lblImgen = new JLabel("Im\u00E1gen:");
		lblImgen.setBounds(78, 272, 46, 14);
		contentPane.add(lblImgen);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(134, 304, 90, 40);
		contentPane.add(btnRegistrar);
	}
}
