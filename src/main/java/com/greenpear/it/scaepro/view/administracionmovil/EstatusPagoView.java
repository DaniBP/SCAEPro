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
import java.awt.Color;
import java.awt.Font;

public class EstatusPagoView extends JInternalFrame {

	public JPanel contentPane;
	public JTable tabla;
	public JComboBox cmbAreas;
	public JButton btnConfirmar;
	public JLabel lblFecha;
	public JLabel lblTituloTabla;
	public JComboBox cmbEstatusPago;

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
		setBounds(100, 100, 850, 600);
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
		cmbAreas.setBounds(66, 28, 227, 20);
		contentPane.add(cmbAreas);
		
		lblTituloTabla = new JLabel("Empleados Sin Pago:");
		lblTituloTabla.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTituloTabla.setBounds(10, 111, 144, 14);
		contentPane.add(lblTituloTabla);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(SystemColor.activeCaption);
		scrollPane.setBounds(10, 136, 814, 373);
		contentPane.add(scrollPane);
		
		tabla = new JTable();
		tabla.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre Empleados", "Estatus de pago", "Comentario"
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
		
		btnConfirmar = new JButton("Confirmar Pago");
		btnConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnConfirmar.setBounds(628, 528, 163, 31);
		contentPane.add(btnConfirmar);
		
		JLabel lblFechaActual = new JLabel("Fecha Actual:");
		lblFechaActual.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFechaActual.setForeground(new Color(0, 0, 0));
		lblFechaActual.setBounds(586, 31, 96, 20);
		contentPane.add(lblFechaActual);
		
		lblFecha = new JLabel("");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFecha.setBounds(681, 31, 143, 20);
		contentPane.add(lblFecha);
		
		JLabel lblEstatusDePago = new JLabel("Estatus de Pago:");
		lblEstatusDePago.setBounds(10, 70, 96, 14);
		contentPane.add(lblEstatusDePago);
		
		cmbEstatusPago = new JComboBox();
		cmbEstatusPago.setEnabled(false);
		cmbEstatusPago.setModel(new DefaultComboBoxModel(new String[] {"No pagado", "Pagado"}));
		cmbEstatusPago.setBounds(103, 67, 190, 20);
		contentPane.add(cmbEstatusPago);
	}
}
