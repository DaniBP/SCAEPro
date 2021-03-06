package com.greenpear.it.scaepro.view.administracionmovil;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.ScrollPane;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class NoticiasExistentesView extends JInternalFrame {

	public JPanel contentPane;
	public JTable tablaNoticias;
	public JScrollPane scrollPane;
	public JLabel lblFechaActual;
	public JLabel lblFecha;

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
		setFrameIcon(new ImageIcon(NoticiasExistentesView.class.getResource("/img/Logo1.png")));
		setIconifiable(true);
		setClosable(true);
		setTitle("Noticias Existentes");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 465, 444);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(SystemColor.activeCaption);
		scrollPane.setBounds(10, 30, 429, 364);
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
		
		lblFechaActual = new JLabel("Fecha Actual:");
		lblFechaActual.setBounds(259, 5, 71, 14);
		contentPane.add(lblFechaActual);
		
		lblFecha = new JLabel("");
		lblFecha.setBounds(340, 5, 99, 14);
		contentPane.add(lblFecha);
	}
}
