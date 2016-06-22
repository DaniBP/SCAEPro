package com.greenpear.it.scaepro.view.administracionmovil;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.ScrollPane;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.SystemColor;

public class NoticiasExistentesView extends JFrame {

	public JPanel contentPane;
	public JTable tablaNoticias;
	public JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NoticiasExistentesView frame = new NoticiasExistentesView();
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
	public NoticiasExistentesView() {
		setTitle("Noticias Existentes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 444);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(SystemColor.activeCaption);
		scrollPane.setBounds(10, 11, 429, 383);
		contentPane.add(scrollPane);
		
		tablaNoticias = new JTable();
		scrollPane.setViewportView(tablaNoticias);
		tablaNoticias.setModel(new DefaultTableModel(
			new Object[][] {
				{},
				{},
			},
			new String[] {
			}
		));
	}
}
