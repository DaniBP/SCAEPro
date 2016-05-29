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

	private JPanel contentPane;
	private JTable table;

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
		setBounds(100, 100, 450, 330);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTipoAviso = new JLabel("Tipo de aviso:");
		lblTipoAviso.setHorizontalAlignment(SwingConstants.LEFT);
		lblTipoAviso.setBounds(10, 36, 84, 14);
		contentPane.add(lblTipoAviso);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar...", "General", "Por \u00E1rea", "Personal"}));
		comboBox.setBounds(94, 33, 120, 20);
		contentPane.add(comboBox);
		
		JLabel lblArea = new JLabel("\u00C1rea:");
		lblArea.setHorizontalAlignment(SwingConstants.RIGHT);
		lblArea.setEnabled(false);
		lblArea.setBounds(224, 36, 33, 14);
		contentPane.add(lblArea);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setEnabled(false);
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar..."}));
		comboBox_1.setBounds(267, 33, 120, 20);
		contentPane.add(comboBox_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(SystemColor.activeCaption);
		scrollPane.setBounds(10, 88, 414, 60);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setEnabled(false);
		table.setModel(new DefaultTableModel(
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
		
		JLabel lblEmpleado = new JLabel("Empleado:");
		lblEmpleado.setEnabled(false);
		lblEmpleado.setBounds(10, 63, 59, 14);
		contentPane.add(lblEmpleado);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(10, 174, 414, 60);
		contentPane.add(textPane);
		
		JLabel lblAviso = new JLabel("Aviso:");
		lblAviso.setHorizontalAlignment(SwingConstants.LEFT);
		lblAviso.setBounds(10, 159, 84, 14);
		contentPane.add(lblAviso);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(335, 234, 89, 23);
		contentPane.add(btnEnviar);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
	}
}
