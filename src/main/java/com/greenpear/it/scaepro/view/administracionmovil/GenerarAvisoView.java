package com.greenpear.it.scaepro.view.administracionmovil;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
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

public class GenerarAvisoView extends JFrame {

	public JPanel contentPane;
	public JTable tablaBusqueda;
	public JComboBox cmbTipoAviso;
	public JComboBox cmbArea;
	public JLabel lblArea;
	public JLabel lblTipoAviso;
	public JButton btnEnviar;
	public JTextPane txtAviso;
	public JScrollPane scrollPane;

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
		setTitle("Generar aviso");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 560);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTipoAviso = new JLabel("Tipo de aviso:");
		lblTipoAviso.setHorizontalAlignment(SwingConstants.LEFT);
		lblTipoAviso.setBounds(10, 36, 84, 14);
		contentPane.add(lblTipoAviso);
		
		cmbTipoAviso = new JComboBox();
		cmbTipoAviso.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar...", "General", "Personal", "\u00C1rea"}));
		cmbTipoAviso.setBounds(94, 33, 166, 20);
		contentPane.add(cmbTipoAviso);
		
		lblArea = new JLabel("\u00C1rea:");
		lblArea.setHorizontalAlignment(SwingConstants.RIGHT);
		lblArea.setBounds(317, 36, 33, 14);
		contentPane.add(lblArea);
		
		cmbArea = new JComboBox();
		cmbArea.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar..."}));
		cmbArea.setBounds(368, 33, 166, 20);
		contentPane.add(cmbArea);
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(SystemColor.activeCaption);
		scrollPane.setBounds(10, 106, 524, 226);
		contentPane.add(scrollPane);
		
		tablaBusqueda = new JTable();
		scrollPane.setViewportView(tablaBusqueda);
		tablaBusqueda.setEnabled(false);
		tablaBusqueda.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
			},
			new String[] {
				"Nombre", "Seleccionar"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		JLabel lblBusqueda = new JLabel("Empleado:");
		lblBusqueda.setBounds(10, 81, 59, 14);
		contentPane.add(lblBusqueda);
		
		txtAviso = new JTextPane();
		txtAviso.setBounds(10, 383, 524, 93);
		contentPane.add(txtAviso);
		
		JLabel lblAviso = new JLabel("Aviso:");
		lblAviso.setHorizontalAlignment(SwingConstants.LEFT);
		lblAviso.setBounds(10, 358, 84, 14);
		contentPane.add(lblAviso);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(445, 487, 89, 23);
		contentPane.add(btnEnviar);
		tablaBusqueda.getColumnModel().getColumn(0).setResizable(false);
		tablaBusqueda.getColumnModel().getColumn(1).setResizable(false);
	}
}
