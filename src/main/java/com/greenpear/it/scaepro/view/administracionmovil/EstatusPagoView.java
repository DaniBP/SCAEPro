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
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class EstatusPagoView extends JInternalFrame {

	public JPanel contentPane;
	public JTable tabla;
	public JComboBox cmbAreas;
	public JButton btnConfirmar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EstatusPagoView frame = new EstatusPagoView();
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
	public EstatusPagoView() {
		setFrameIcon(new ImageIcon(EstatusPagoView.class.getResource("/img/Logo1.png")));
		setIconifiable(true);
		setClosable(true);
		setTitle("Estatus de pago");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 731, 461);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		JLabel lblrea = new JLabel("\u00C1rea:");
		lblrea.setBounds(10, 31, 46, 14);
		contentPane.add(lblrea);
		
		cmbAreas = new JComboBox();
		cmbAreas.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar..."}));
		cmbAreas.setBounds(66, 28, 208, 20);
		contentPane.add(cmbAreas);
		
		JLabel lblEmpleados = new JLabel("Empleados:");
		lblEmpleados.setBounds(10, 79, 90, 14);
		contentPane.add(lblEmpleados);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(SystemColor.activeCaption);
		scrollPane.setBounds(10, 114, 695, 247);
		contentPane.add(scrollPane);
		
		tabla = new JTable();
		tabla.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "Estatus de pago", "Comentario"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tabla.getColumnModel().getColumn(0).setResizable(false);
		tabla.getColumnModel().getColumn(0).setPreferredWidth(200);
		tabla.getColumnModel().getColumn(1).setResizable(false);
		tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
		tabla.getColumnModel().getColumn(2).setResizable(false);
		tabla.getColumnModel().getColumn(2).setPreferredWidth(195);
		scrollPane.setViewportView(tabla);
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(585, 388, 120, 23);
		contentPane.add(btnConfirmar);
	}
}
