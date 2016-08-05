package com.greenpear.it.scaepro.view.accesosistema;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class PrimerUsuarioView extends JFrame {
	public JTextField txtNombre;
	public JTextField txtPassword;
	public JTextField txtPassword2;
	public JTextField txtEmail;
	public JTextField txtEmail2;
	public JRadioButton rbtnActivo;
	public JRadioButton rbtnInactivo;
	public JCheckBox chbxNotificacion;
	public JButton btnRegistrar;
	public JButton btnLimpiar;
	public JButton btnSalir;
	private ButtonGroup bg= new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		PrimerUsuarioView alta=new PrimerUsuarioView();
		alta.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public PrimerUsuarioView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PrimerUsuarioView.class.getResource("/img/Logo1.png")));
		getContentPane().setBackground(SystemColor.activeCaption);
		setTitle("Registrar Usuario");
		setBounds(100, 100, 420, 414);
		getContentPane().setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBorder(new TitledBorder(null, "Datos del Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 384, 312);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre del Usuario:");
		lblNewLabel.setBounds(10, 40, 123, 14);
		panel.add(lblNewLabel);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(10, 80, 123, 14);
		panel.add(lblContrasea);
		
		JLabel lblRepetirContrasea = new JLabel("Repetir Contrase\u00F1a:");
		lblRepetirContrasea.setBounds(10, 120, 123, 14);
		panel.add(lblRepetirContrasea);
		
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setBounds(10, 160, 123, 14);
		panel.add(lblCorreo);
		
		JLabel lblRepetirCorreo = new JLabel("Repetir Correo:");
		lblRepetirCorreo.setBounds(10, 202, 123, 14);
		panel.add(lblRepetirCorreo);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(143, 37, 231, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(143, 77, 231, 20);
		panel.add(txtPassword);
		txtPassword.setColumns(10);
		
		
		txtPassword2 = new JPasswordField();
		txtPassword2.setBounds(143, 117, 231, 20);
		panel.add(txtPassword2);
		txtPassword2.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(143, 157, 231, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtEmail2 = new JTextField();
		txtEmail2.setBounds(143, 199, 231, 20);
		panel.add(txtEmail2);
		txtEmail2.setColumns(10);
		
		JLabel lblEstatus = new JLabel("Estatus:");
		lblEstatus.setBounds(10, 240, 123, 14);
		panel.add(lblEstatus);
		
		rbtnActivo = new JRadioButton("Activo");
		rbtnActivo.setEnabled(false);
		rbtnActivo.setSelected(true);
		rbtnActivo.setBackground(SystemColor.activeCaption);
		rbtnActivo.setBounds(143, 236, 92, 23);
		panel.add(rbtnActivo);
		
		rbtnInactivo = new JRadioButton("Inactivo");
		rbtnInactivo.setEnabled(false);
		rbtnInactivo.setBackground(SystemColor.activeCaption);
		rbtnInactivo.setBounds(282, 236, 92, 23);
		panel.add(rbtnInactivo);
		
		chbxNotificacion = new JCheckBox("Recibir notificaciones de empleados");
		chbxNotificacion.setBackground(SystemColor.activeCaption);
		chbxNotificacion.setBounds(143, 276, 231, 23);
		panel.add(chbxNotificacion);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(10, 346, 89, 23);
		getContentPane().add(btnRegistrar);
		
		btnLimpiar = new JButton("Limpiar");
//		btnLimpiar.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				txtNombre.setText(null);
//				txtPassword.setText(null);
//				txtPassword2.setText(null);
//				txtEmail.setText(null);
//				txtEmail2.setText(null);
//				rbtnActivo.setSelected(false);
//				rbtnInactivo.setSelected(false);
//				chbxNotificacion.setSelected(false);
//			}
//		});
		btnLimpiar.setBounds(160, 346, 89, 23);
		getContentPane().add(btnLimpiar);
		
		btnSalir = new JButton("Salir");
	
		btnSalir.setBounds(305, 346, 89, 23);
		getContentPane().add(btnSalir);
		
		bg.add(rbtnActivo);
		bg.add(rbtnInactivo);
	}
}
