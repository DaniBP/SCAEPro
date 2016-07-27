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
import javax.swing.ImageIcon;
import javax.swing.JTextField;

public class SolicitudReporteView extends JInternalFrame {

	public JPanel contentPane;
	public JRadioButton rdbtnReporteGeneral;
	public JRadioButton rdbtnReportePorArea;
	public JComboBox cmbAreas;
	public JDateChooser dataInicio;
	public JLabel lblArea;
	public JDateChooser dataFin;
	public JButton btnGenerarReporte;
	public JLabel lblFecha;
	public JTextField txtEmpleado;
	public JLabel lblEmpleado;
	public JPanel panelTipoReporte;
	public JTextField txtarea;
	public JLabel lblarea2;
	public JTextField txtTurno;
	public JLabel lblTurno;
	public JPanel panel;

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
		setFrameIcon(new ImageIcon(SolicitudReporteView.class.getResource("/img/Logo1.png")));
		setClosable(true);
		setIconifiable(true);
		setTitle("Reportes");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 670, 457);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		JLabel lblNewLabel = new JLabel("Fecha Actual:");
		lblNewLabel.setBounds(379, 11, 79, 14);
		contentPane.add(lblNewLabel);
		
		panelTipoReporte = new JPanel();
		panelTipoReporte.setBounds(54, 42, 255, 90);
		
		panelTipoReporte.setBackground(SystemColor.activeCaption);
		panelTipoReporte.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tipo de Reporte", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panelTipoReporte);
		panelTipoReporte.setLayout(null);
		
		rdbtnReporteGeneral = new JRadioButton("Reporte General");
		rdbtnReporteGeneral.setSelected(true);
		rdbtnReporteGeneral.setBackground(SystemColor.activeCaption);
		rdbtnReporteGeneral.setBounds(6, 20, 129, 23);
		panelTipoReporte.add(rdbtnReporteGeneral);
		
		rdbtnReportePorArea = new JRadioButton("Reporte por \u00C1rea");
		rdbtnReportePorArea.setBackground(SystemColor.activeCaption);
		rdbtnReportePorArea.setBounds(6, 46, 129, 23);
		panelTipoReporte.add(rdbtnReportePorArea);
		
		panel = new JPanel();
		panel.setBounds(57, 143, 539, 207);
		panel.setBackground(SystemColor.activeCaption);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Reporte Personalizado", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblHoraDeSalida = new JLabel("Fecha Inicio");
		lblHoraDeSalida.setBounds(78, 28, 74, 14);
		panel.add(lblHoraDeSalida);
		
		JLabel lblHoraDeEntrada = new JLabel("Fecha Fin");
		lblHoraDeEntrada.setBounds(372, 28, 64, 14);
		panel.add(lblHoraDeEntrada);
		
		dataInicio = new JDateChooser();
		dataInicio.setBounds(78, 65, 161, 20);
		panel.add(dataInicio);
		
		dataFin = new JDateChooser();
		dataFin.setBounds(321, 65, 161, 20);
		panel.add(dataFin);
		
		lblArea = new JLabel("\u00C1rea:");
		lblArea.setEnabled(false);
		lblArea.setBounds(331, 58, 38, 14);
		contentPane.add(lblArea);
		
		btnGenerarReporte = new JButton("Generar Reporte General");
		btnGenerarReporte.setBounds(236, 362, 188, 23);
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
		
		txtEmpleado = new JTextField();
		txtEmpleado.setVisible(false);
		txtEmpleado.setBounds(388, 76, 228, 20);
		contentPane.add(txtEmpleado);
		txtEmpleado.setColumns(10);
		
		lblEmpleado = new JLabel("Empleado:");
		lblEmpleado.setVisible(false);
		lblEmpleado.setBounds(315, 80, 72, 14);
		contentPane.add(lblEmpleado);
		
		txtarea = new JTextField();
		txtarea.setVisible(false);
		txtarea.setBounds(388, 94, 228, 20);
		contentPane.add(txtarea);
		txtarea.setColumns(10);
		
		lblarea2 = new JLabel("\u00C1rea:");
		lblarea2.setVisible(false);
		lblarea2.setBounds(318, 97, 46, 14);
		contentPane.add(lblarea2);
		
		txtTurno = new JTextField();
		txtTurno.setVisible(false);
		txtTurno.setBounds(388, 113, 228, 20);
		contentPane.add(txtTurno);
		txtTurno.setColumns(10);
		
		lblTurno = new JLabel("Turno:");
		lblTurno.setVisible(false);
		lblTurno.setBounds(315, 115, 46, 14);
		contentPane.add(lblTurno);
	}
}
