package com.greenpear.it.scaepro.view.controlacceso;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.util.Calendar;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;

public class ControlAccesoView extends JFrame {
	
	private JPanel contentPane;
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
	public JLabel lblHorasTrabajadas;
	public JLabel lblAlerta;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(ControlAccesoView.class.getResource("/img/Logo1.png")));
		setTitle("Registro de Control de Acceso");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		lblFecha = new JLabel("Fecha Actual:");
		lblFecha.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 25));
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFecha.setBounds(999, 24, 260, 23);
		contentPane.add(lblFecha);
		
		JPanel panel = new JPanel();
		
		panel.setBackground(SystemColor.activeCaption);
		panel.setBorder(new TitledBorder(null, "Datos del Empleado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(25, 70, 974, 96);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblEmpleado = new JLabel("Empleado:");
		lblEmpleado.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		lblEmpleado.setBounds(10, 23, 954, 20);
		panel.add(lblEmpleado);
		
		lblArea = new JLabel("\u00C1rea:");
		lblArea.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		lblArea.setBounds(10, 62, 954, 20);
		panel.add(lblArea);
		
		JPanel panel_1 = new JPanel();
		panel_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBorder(new TitledBorder(null, "Control de Acceso", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(25, 168, 974, 445);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		lblEntrada = new JLabel("Hora de Entrada:");
		lblEntrada.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		lblEntrada.setVisible(false);
		lblEntrada.setBounds(10, 75, 954, 20);
		panel_1.add(lblEntrada);
		
		lblSalidaComer = new JLabel("Hora de Salida a Comer:");
		lblSalidaComer.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		lblSalidaComer.setVisible(false);
		lblSalidaComer.setBounds(10, 141, 954, 20);
		panel_1.add(lblSalidaComer);
		
		lblEntradaComer = new JLabel("Hora de Entrada de Comer:");
		lblEntradaComer.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		lblEntradaComer.setVisible(false);
		lblEntradaComer.setBounds(10, 217, 954, 20);
		panel_1.add(lblEntradaComer);
		
		lblSalida = new JLabel("Hora de Salida:");
		lblSalida.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		lblSalida.setVisible(false);
		lblSalida.setBounds(10, 296, 954, 20);
		panel_1.add(lblSalida);
		
		lblHorasTrabajadas = new JLabel("Horas trabajadas: ");
		lblHorasTrabajadas.setVisible(false);
		lblHorasTrabajadas.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
		lblHorasTrabajadas.setBounds(10, 382, 954, 20);
		panel_1.add(lblHorasTrabajadas);
		
		JLabel lblFotografia = new JLabel("Fotografia");
		lblFotografia.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		lblFotografia.setHorizontalAlignment(SwingConstants.CENTER);
		lblFotografia.setBounds(1069, 331, 133, 26);
		contentPane.add(lblFotografia);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setBounds(1095, 598, 87, 23);
		contentPane.add(lblEstado);
		
		lblHora = new JLabel("Hora:");
		lblHora.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 25));
		lblHora.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHora.setBounds(1028, 652, 231, 23);
		contentPane.add(lblHora);
		
		btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		btnSalir.setBounds(25, 624, 210, 42);
		contentPane.add(btnSalir);
		
		imgFoto = new JLabel("");
		imgFoto.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		imgFoto.setBounds(1009, 70, 250, 250);
		contentPane.add(imgFoto);
		
		imgEstado = new JLabel("");
		imgEstado.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		imgEstado.setBounds(1038, 387, 200, 200);
		contentPane.add(imgEstado);
		
		lblAlerta = new JLabel("");
		lblAlerta.setForeground(Color.RED);
		lblAlerta.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
		lblAlerta.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlerta.setBounds(321, 624, 592, 42);
		contentPane.add(lblAlerta);
	}
	
	/**
	 * Método para limpiar la ventana de control de acceso
	 */
	public void limpiarVentana(){
		lblEmpleado.setText("Empleado:");
		lblArea.setText("Área:");
		lblEntrada.setText("Hora de Entrada:");
		lblSalidaComer.setText("Hora de Salida a Comer:");
		lblEntradaComer.setText("Hora de Entrada de Comer:");
		lblSalida.setText("Hora de Salida:");
		lblHorasTrabajadas.setText("Horas trabajadas:");
		lblEntrada.setVisible(false);
		lblSalidaComer.setVisible(false);
		lblSalidaComer.setEnabled(true);
		lblEntradaComer.setVisible(false);
		lblEntradaComer.setEnabled(true);
		lblSalida.setVisible(false);
		lblHorasTrabajadas.setVisible(false);
		imgEstado.setIcon(null);
		imgFoto.setIcon(null);
		lblAlerta.setText(null);
	}
}
