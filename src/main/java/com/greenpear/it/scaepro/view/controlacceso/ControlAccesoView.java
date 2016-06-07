package com.greenpear.it.scaepro.view.controlacceso;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.util.Calendar;
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
import javax.swing.SwingConstants;

public class ControlAccesoView extends JFrame {

	private JPanel contentPane;
	public JButton btnChecar;
	public JButton btnSalir;
	
	public String hora,minutos,segundos,ampm;
	public String anio,mes,dia;
	public Calendar calendario;
	public Thread h1;
	public JLabel lblHora;
	public JLabel lblFecha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ControlAccesoView frame = new ControlAccesoView();
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
	public ControlAccesoView() {
		setTitle("Registro de Control de Acceso");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 521, 370);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblFecha = new JLabel("Fecha Actual:  ");
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFecha.setBounds(362, 11, 133, 14);
		contentPane.add(lblFecha);
		
		JPanel panel = new JPanel();
		
		panel.setBackground(SystemColor.activeCaption);
		panel.setBorder(new TitledBorder(null, "Datos del Empleado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(25, 42, 331, 90);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombreCompleto = new JLabel("Nombre Completo:");
		lblNombreCompleto.setBounds(10, 28, 105, 14);
		panel.add(lblNombreCompleto);
		
		JLabel lblrea = new JLabel("\u00C1rea:");
		lblrea.setBounds(10, 53, 46, 14);
		panel.add(lblrea);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBorder(new TitledBorder(null, "Control de Acceso", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(25, 143, 331, 146);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblHoraDeEntrada_1 = new JLabel("Hora de Entrada:");
		lblHoraDeEntrada_1.setBounds(10, 34, 116, 14);
		panel_1.add(lblHoraDeEntrada_1);
		
		JLabel lblHoraDeSalida = new JLabel("Hora de Salida a Comer:");
		lblHoraDeSalida.setBounds(10, 60, 142, 14);
		panel_1.add(lblHoraDeSalida);
		
		JLabel lblHoraDeEntrada = new JLabel("Hora de Entrada a Comer:");
		lblHoraDeEntrada.setBounds(9, 85, 151, 14);
		panel_1.add(lblHoraDeEntrada);
		
		JLabel lblHoraDeSalida_1 = new JLabel("Hora de Salida:");
		lblHoraDeSalida_1.setBounds(10, 109, 87, 14);
		panel_1.add(lblHoraDeSalida_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_2.setBackground(SystemColor.activeCaption);
		panel_2.setBounds(408, 42, 87, 90);
		contentPane.add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_3.setBackground(SystemColor.activeCaption);
		panel_3.setBounds(408, 164, 87, 90);
		contentPane.add(panel_3);
		
		JLabel lblFotografia = new JLabel("Fotografia");
		lblFotografia.setHorizontalAlignment(SwingConstants.CENTER);
		lblFotografia.setBounds(408, 131, 87, 14);
		contentPane.add(lblFotografia);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setBounds(408, 254, 87, 14);
		contentPane.add(lblEstado);
		
		lblHora = new JLabel("Hora:");
		lblHora.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHora.setBounds(362, 304, 133, 14);
		contentPane.add(lblHora);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(71, 300, 74, 23);
		contentPane.add(btnSalir);
		
		btnChecar = new JButton("Checar");
		btnChecar.setBounds(218, 300, 89, 23);
		contentPane.add(btnChecar);
	}
}
