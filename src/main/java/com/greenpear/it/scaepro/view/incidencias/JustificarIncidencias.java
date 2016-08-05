package com.greenpear.it.scaepro.view.incidencias;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class JustificarIncidencias extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombreEmpleado;
	private JTextField txtArea;
	private JTextField txtTipoIncidencia;
	private JDateChooser dtFechaIncidencia;
	private JTextArea tatComentario;
	private JButton btnJustificar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JustificarIncidencias frame = new JustificarIncidencias();
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
	public JustificarIncidencias() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 499, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreDelEmpleado = new JLabel("Nombre del empleado:");
		lblNombreDelEmpleado.setBounds(21, 36, 109, 14);
		contentPane.add(lblNombreDelEmpleado);
		
		JLabel lblTipoDeIncidencia = new JLabel("Tipo de incidencia:");
		lblTipoDeIncidencia.setBounds(21, 113, 93, 14);
		contentPane.add(lblTipoDeIncidencia);
		
		JLabel lblArea = new JLabel("Area:");
		lblArea.setBounds(21, 73, 35, 14);
		contentPane.add(lblArea);
		
		txtNombreEmpleado = new JTextField();
		txtNombreEmpleado.setBounds(134, 30, 317, 26);
		contentPane.add(txtNombreEmpleado);
		txtNombreEmpleado.setColumns(10);
		
		txtArea = new JTextField();
		txtArea.setColumns(10);
		txtArea.setBounds(118, 67, 333, 26);
		contentPane.add(txtArea);
		
		txtTipoIncidencia = new JTextField();
		txtTipoIncidencia.setColumns(10);
		txtTipoIncidencia.setBounds(118, 107, 109, 26);
		contentPane.add(txtTipoIncidencia);
		
		JLabel lblFechaDeIncidencia = new JLabel("Fecha:");
		lblFechaDeIncidencia.setBounds(237, 113, 52, 14);
		contentPane.add(lblFechaDeIncidencia);
		
		JLabel lblComentario = new JLabel("Comentario:");
		lblComentario.setBounds(21, 193, 67, 14);
		contentPane.add(lblComentario);
		
		dtFechaIncidencia = new JDateChooser();
		dtFechaIncidencia.setBounds(279, 107, 172, 26);
		contentPane.add(dtFechaIncidencia);
		
		tatComentario = new JTextArea();
		tatComentario.setBounds(21, 218, 297, 136);
		contentPane.add(tatComentario);
		
		JLabel lblJustificante = new JLabel("Justificante:");
		lblJustificante.setBounds(21, 155, 67, 14);
		contentPane.add(lblJustificante);
		
		btnJustificar = new JButton("Justificar");
		btnJustificar.setBounds(342, 276, 109, 23);
		contentPane.add(btnJustificar);
	}

	public JTextField getTxtNombreEmpleado() {
		return txtNombreEmpleado;
	}

	public void setTxtNombreEmpleado(JTextField txtNombreEmpleado) {
		this.txtNombreEmpleado = txtNombreEmpleado;
	}

	public JTextField getTxtArea() {
		return txtArea;
	}

	public void setTxtArea(JTextField txtArea) {
		this.txtArea = txtArea;
	}

	public JTextField getTxtTipoIncidencia() {
		return txtTipoIncidencia;
	}

	public void setTxtTipoIncidencia(JTextField txtTipoIncidencia) {
		this.txtTipoIncidencia = txtTipoIncidencia;
	}

	public JDateChooser getDtFechaIncidencia() {
		return dtFechaIncidencia;
	}

	public void setDtFechaIncidencia(JDateChooser dtFechaIncidencia) {
		this.dtFechaIncidencia = dtFechaIncidencia;
	}

	public JTextArea getTatComentario() {
		return tatComentario;
	}

	public void setTatComentario(JTextArea tatComentario) {
		this.tatComentario = tatComentario;
	}

	public JButton getBtnJustificar() {
		return btnJustificar;
	}

	public void setBtnJustificar(JButton btnJustificar) {
		this.btnJustificar = btnJustificar;
	}
	
}
