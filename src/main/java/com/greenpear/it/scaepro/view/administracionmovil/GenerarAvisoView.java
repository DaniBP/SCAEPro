package com.greenpear.it.scaepro.view.administracionmovil;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class GenerarAvisoView extends JInternalFrame {

	public JPanel contentPane;
	public JTable tablaBusqueda;
	public JComboBox cmbTipoAviso;
	public JLabel lblTipoAviso;
	public JButton btnEnviar;
	public JTextPane txtAviso;
	public JScrollPane scrollPane;
	public JLabel lblBusqueda;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerarAvisoView frame = new GenerarAvisoView();
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
	public GenerarAvisoView() {
		setFrameIcon(new ImageIcon(GenerarAvisoView.class.getResource("/img/Logo1.png")));
		setIconifiable(true);
		setClosable(true);
		setTitle("Generar aviso");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 560, 560);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		lblTipoAviso = new JLabel("Tipo de aviso:");
		lblTipoAviso.setHorizontalAlignment(SwingConstants.LEFT);
		lblTipoAviso.setBounds(10, 36, 84, 14);
		contentPane.add(lblTipoAviso);
		
		cmbTipoAviso = new JComboBox();
		cmbTipoAviso.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar...", "General", "Personal", "Area"}));
		cmbTipoAviso.setBounds(94, 33, 166, 20);
		contentPane.add(cmbTipoAviso);
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(SystemColor.activeCaption);
		scrollPane.setBounds(10, 106, 524, 226);
		contentPane.add(scrollPane);
		
		tablaBusqueda = new JTable();
		tablaBusqueda.setEnabled(false);
		scrollPane.setViewportView(tablaBusqueda);
		tablaBusqueda.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		
		lblBusqueda = new JLabel("");
		lblBusqueda.setBounds(10, 81, 166, 14);
		contentPane.add(lblBusqueda);
		
		txtAviso = new JTextPane();
		txtAviso.setEnabled(false);
		txtAviso.setBounds(10, 383, 524, 93);
		contentPane.add(txtAviso);
		
		JLabel lblAviso = new JLabel("Aviso:");
		lblAviso.setHorizontalAlignment(SwingConstants.LEFT);
		lblAviso.setBounds(10, 358, 84, 14);
		contentPane.add(lblAviso);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.setEnabled(false);
		btnEnviar.setBounds(445, 487, 89, 23);
		contentPane.add(btnEnviar);
	}
}
