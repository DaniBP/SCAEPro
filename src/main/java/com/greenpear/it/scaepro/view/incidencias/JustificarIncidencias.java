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
import javax.swing.SwingConstants;

public class JustificarIncidencias extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombreEmpleado;
	private JTextField txtArea;
	private JTextField txtTipoIncidencia;
	private JTextArea tatComentario;
	private JButton btnJustificar;
	private JLabel lblFotografia;
	private JTextField txtFecha;
	private JLabel lblFechaJustificane;
	private JTextField txtFechaJustificante;
	private JLabel lblJustificante;
	private JButton btnSubirImagen;
	private JButton btnAbrirImagen;
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
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 524, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreDelEmpleado = new JLabel("Nombre del empleado:");
		lblNombreDelEmpleado.setBounds(21, 36, 109, 14);
		contentPane.add(lblNombreDelEmpleado);
		
		JLabel lblTipoDeIncidencia = new JLabel("Tipo de incidencia:");
		lblTipoDeIncidencia.setBounds(277, 73, 93, 14);
		contentPane.add(lblTipoDeIncidencia);
		
		JLabel lblArea = new JLabel("Area:");
		lblArea.setBounds(21, 73, 35, 14);
		contentPane.add(lblArea);
		
		txtNombreEmpleado = new JTextField();
		txtNombreEmpleado.setEditable(false);
		txtNombreEmpleado.setBounds(140, 30, 345, 26);
		contentPane.add(txtNombreEmpleado);
		txtNombreEmpleado.setColumns(10);
		
		txtArea = new JTextField();
		txtArea.setEditable(false);
		txtArea.setColumns(10);
		txtArea.setBounds(54, 67, 213, 26);
		contentPane.add(txtArea);
		
		txtTipoIncidencia = new JTextField();
		txtTipoIncidencia.setEditable(false);
		txtTipoIncidencia.setColumns(10);
		txtTipoIncidencia.setBounds(380, 67, 105, 26);
		contentPane.add(txtTipoIncidencia);
		
		JLabel lblFechaDeIncidencia = new JLabel("Fecha incidencia:");
		lblFechaDeIncidencia.setBounds(21, 113, 86, 14);
		contentPane.add(lblFechaDeIncidencia);
		
		JLabel lblComentario = new JLabel("Comentario:");
		lblComentario.setBounds(21, 193, 67, 14);
		contentPane.add(lblComentario);
		
		tatComentario = new JTextArea(289, 136);
		tatComentario.setText("Sin comentario...");
		tatComentario.setBounds(21, 218, 289, 136);
		tatComentario.setLineWrap(true);
		tatComentario.setWrapStyleWord(true); 
		contentPane.add(tatComentario);
		
		lblJustificante = new JLabel("Justificante:");
		lblJustificante.setBounds(21, 155, 67, 14);
		contentPane.add(lblJustificante);
		
		btnJustificar = new JButton("Justificar");
		btnJustificar.setBounds(360, 317, 109, 23);
		contentPane.add(btnJustificar);
		
		lblFotografia = new JLabel("");
		lblFotografia.setBounds(340, 144, 145, 162);
		contentPane.add(lblFotografia);
		
		txtFecha = new JTextField();
		txtFecha.setEditable(false);
		txtFecha.setColumns(10);
		txtFecha.setBounds(110, 107, 136, 26);
		contentPane.add(txtFecha);
		
		lblFechaJustificane = new JLabel("Fecha justificante:");
		lblFechaJustificane.setBounds(256, 113, 98, 14);
		contentPane.add(lblFechaJustificane);
		
		txtFechaJustificante = new JTextField();
		txtFechaJustificante.setEditable(false);
		txtFechaJustificante.setColumns(10);
		txtFechaJustificante.setBounds(349, 107, 136, 26);
		contentPane.add(txtFechaJustificante);
		
		btnSubirImagen = new JButton("Seleccionar imagen...");
		btnSubirImagen.setHorizontalAlignment(SwingConstants.LEFT);
		btnSubirImagen.setBounds(93, 146, 135, 32);
		contentPane.add(btnSubirImagen);
		
		btnAbrirImagen = new JButton("Abrir");
		btnAbrirImagen.setHorizontalAlignment(SwingConstants.LEFT);
		btnAbrirImagen.setBounds(245, 146, 55, 32);
		contentPane.add(btnAbrirImagen);
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


	public JTextField getTxtFecha() {
		return txtFecha;
	}

	public void setTxtFecha(JTextField txtFecha) {
		this.txtFecha = txtFecha;
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

	public JLabel getLblFotografia() {
		return lblFotografia;
	}

	public void setLblFotografia(JLabel lblFotografia) {
		this.lblFotografia = lblFotografia;
	}

	public JTextField getTxtFechaJustificante() {
		return txtFechaJustificante;
	}

	public void setTxtFechaJustificante(JTextField txtFechaJustificante) {
		this.txtFechaJustificante = txtFechaJustificante;
	}

	public JLabel getLblFechaJustificane() {
		return lblFechaJustificane;
	}

	public void setLblFechaJustificane(JLabel lblFechaJustificane) {
		this.lblFechaJustificane = lblFechaJustificane;
	}

	public JLabel getLblJustificante() {
		return lblJustificante;
	}

	public void setLblJustificante(JLabel lblJustificante) {
		this.lblJustificante = lblJustificante;
	}

	public JButton getBtnSubirImagen() {
		return btnSubirImagen;
	}

	public void setBtnSubirImagen(JButton btnSubirImagen) {
		this.btnSubirImagen = btnSubirImagen;
	}

	public JButton getBtnAbrirImagen() {
		return btnAbrirImagen;
	}

	public void setBtnAbrirImagen(JButton btnAbrirImagen) {
		this.btnAbrirImagen = btnAbrirImagen;
	}
	
}
