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
	public JLabel lblEmpleado;
	public JLabel lblArea;
	public JLabel lblEntrada;
	public JLabel lblSalidaComer;
	public JLabel lblEntradaComer;
	public JLabel lblSalida;
	public JLabel imgEstado;
	public JLabel imgFoto;

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
		
		lblEmpleado = new JLabel("Empleado:");
		lblEmpleado.setBounds(10, 28, 311, 14);
		panel.add(lblEmpleado);
		
		lblArea = new JLabel("\u00C1rea:");
		lblArea.setBounds(10, 53, 311, 14);
		panel.add(lblArea);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBorder(new TitledBorder(null, "Control de Acceso", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(25, 143, 331, 146);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		lblEntrada = new JLabel("Hora de Entrada:");
		lblEntrada.setBounds(10, 34, 311, 14);
		panel_1.add(lblEntrada);
		
		lblSalidaComer = new JLabel("Hora de Salida a Comer:");
		lblSalidaComer.setBounds(10, 60, 311, 14);
		panel_1.add(lblSalidaComer);
		
		lblEntradaComer = new JLabel("Hora de Entrada de Comer:");
		lblEntradaComer.setBounds(10, 85, 311, 14);
		panel_1.add(lblEntradaComer);
		
		lblSalida = new JLabel("Hora de Salida:");
		lblSalida.setBounds(10, 109, 311, 14);
		panel_1.add(lblSalida);
		
		JLabel lblFotografia = new JLabel("Fotografia");
		lblFotografia.setHorizontalAlignment(SwingConstants.CENTER);
		lblFotografia.setBounds(396, 138, 87, 14);
		contentPane.add(lblFotografia);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setBounds(396, 275, 87, 14);
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
		
		imgFoto = new JLabel("");
		imgFoto.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		imgFoto.setBounds(396, 42, 87, 96);
		contentPane.add(imgFoto);
		
		imgEstado = new JLabel("");
		imgEstado.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		imgEstado.setBounds(396, 177, 87, 96);
		contentPane.add(imgEstado);
	}
	
	public void limpiarVentana(){
		lblEmpleado.setText("Empleado:");
		lblArea.setText("Área:");
		lblEntrada.setText("Hora de Entrada:");
		lblSalidaComer.setText("Hora de Salida a Comer:");
		lblEntradaComer.setText("Hora de Entrada de Comer:");
		lblSalida.setText("Hora de Salida:");
		imgEstado.setIcon(null);
		imgFoto.setIcon(null);
	}
}
