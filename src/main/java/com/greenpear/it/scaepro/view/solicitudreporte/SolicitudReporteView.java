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
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;

public class SolicitudReporteView extends JFrame {

	public JPanel contentPane;
	public JRadioButton rdbtnReporteGeneral;
	public JRadioButton rdbtnReportePorArea;
	public JComboBox cmbAreas;
	public JDateChooser dataInicio;
	public JLabel lblArea;
	public JDateChooser dataFin;
	public JButton btnGenerarReporte;
	public JLabel lblFecha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SolicitudReporteView frame = new SolicitudReporteView();
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
	public SolicitudReporteView() {
		setTitle("Reportes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 670, 457);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Fecha Actual:");
		lblNewLabel.setBounds(379, 11, 79, 14);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(54, 42, 255, 90);
		
		panel.setBackground(SystemColor.activeCaption);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tipo de Reporte", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panel);
		panel.setLayout(null);
		
		rdbtnReporteGeneral = new JRadioButton("Reporte General");
		rdbtnReporteGeneral.setSelected(true);
		rdbtnReporteGeneral.setBackground(SystemColor.activeCaption);
		rdbtnReporteGeneral.setBounds(6, 20, 129, 23);
		panel.add(rdbtnReporteGeneral);
		
		rdbtnReportePorArea = new JRadioButton("Reporte por \u00C1rea");
		rdbtnReportePorArea.setBackground(SystemColor.activeCaption);
		rdbtnReportePorArea.setBounds(6, 46, 129, 23);
		panel.add(rdbtnReportePorArea);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(57, 143, 539, 207);
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Reporte Personalizado", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblHoraDeSalida = new JLabel("Fecha Inicio");
		lblHoraDeSalida.setBounds(78, 28, 74, 14);
		panel_1.add(lblHoraDeSalida);
		
		JLabel lblHoraDeEntrada = new JLabel("Fecha Fin");
		lblHoraDeEntrada.setBounds(372, 28, 64, 14);
		panel_1.add(lblHoraDeEntrada);
		
		dataInicio = new JDateChooser();
		dataInicio.setBounds(78, 65, 161, 20);
		panel_1.add(dataInicio);
		
		dataFin = new JDateChooser();
		dataFin.setBounds(321, 65, 161, 20);
		panel_1.add(dataFin);
		
		lblArea = new JLabel("\u00C1rea:");
		lblArea.setEnabled(false);
		lblArea.setBounds(331, 58, 38, 14);
		contentPane.add(lblArea);
		
		btnGenerarReporte = new JButton("Generar Reporte");
		btnGenerarReporte.setBounds(270, 361, 149, 23);
		contentPane.add(btnGenerarReporte);
		
		cmbAreas = new JComboBox();
		cmbAreas.setEnabled(false);
		cmbAreas.setModel(new DefaultComboBoxModel(new String[] {"Selecciona..."}));
		cmbAreas.setToolTipText("");
		cmbAreas.setForeground(new Color(0, 0, 0));
		cmbAreas.setBounds(379, 55, 217, 20);
		contentPane.add(cmbAreas);
		
		lblFecha = new JLabel("");
		lblFecha.setBounds(468, 11, 163, 14);
		contentPane.add(lblFecha);
		
		ButtonGroup radios = new ButtonGroup();
		radios.add(rdbtnReporteGeneral);
		radios.add(rdbtnReportePorArea);
	}
}
