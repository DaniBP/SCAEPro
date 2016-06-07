package com.greenpear.it.scaepro.view.solicitudreporte;



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
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;

public class SolicitudReporte extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SolicitudReporte frame = new SolicitudReporte();
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
	public SolicitudReporte() {
		setTitle("Reportes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 591, 414);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Fecha Actual:");
		lblNewLabel.setBounds(178, 11, 105, 14);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(25, 42, 255, 90);
		
		panel.setBackground(SystemColor.activeCaption);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tipo de Reporte", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JRadioButton rdbtnReporteGeneral = new JRadioButton("Reporte General");
		rdbtnReporteGeneral.setBackground(SystemColor.activeCaption);
		rdbtnReporteGeneral.setBounds(6, 20, 129, 23);
		panel.add(rdbtnReporteGeneral);
		
		JRadioButton rdbtnReportePorrea = new JRadioButton("Reporte por \u00C1rea");
		rdbtnReportePorrea.setBackground(SystemColor.activeCaption);
		rdbtnReportePorrea.setBounds(6, 46, 129, 23);
		panel.add(rdbtnReportePorrea);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(25, 143, 381, 200);
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Reporte Personalizado", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblHoraDeSalida = new JLabel("Fecha Inicio");
		lblHoraDeSalida.setBounds(78, 28, 74, 14);
		panel_1.add(lblHoraDeSalida);
		
		JLabel lblHoraDeEntrada = new JLabel("Fecha Fin");
		lblHoraDeEntrada.setBounds(279, 28, 64, 14);
		panel_1.add(lblHoraDeEntrada);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(78, 65, 95, 20);
		panel_1.add(dateChooser);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(248, 65, 95, 20);
		panel_1.add(dateChooser_1);
		
		JLabel lblHora = new JLabel("\u00C1rea:");
		lblHora.setBounds(332, 42, 38, 14);
		contentPane.add(lblHora);
		
		JButton btnSalir = new JButton("Generar Reporte");
		btnSalir.setBounds(416, 341, 149, 23);
		contentPane.add(btnSalir);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Selecciona \u00C1rea"}));
		comboBox.setToolTipText("");
		comboBox.setForeground(new Color(0, 0, 0));
		comboBox.setBounds(380, 39, 141, 20);
		contentPane.add(comboBox);
	}
}
