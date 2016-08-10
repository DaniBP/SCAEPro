package com.greenpear.it.scaepro.view.administracionmovil;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JSlider;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;

public class NuevaNoticiaView extends JInternalFrame {

	public JPanel contentPane;
	public JTextField txtTitulo;
	public JButton btnRegistrar;
	public JButton btnSubirFoto;
	public JTextPane txtDescNoticia;
	public JLabel lblImg;
	public JButton btnEliminar;
	public JLabel lblFecha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevaNoticiaView frame = new NuevaNoticiaView();
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
	public NuevaNoticiaView() {
		setFrameIcon(new ImageIcon(NuevaNoticiaView.class.getResource("/img/Logo1.png")));
		setIconifiable(true);
		setClosable(true);
		setTitle("Nueva Noticia");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 381, 481);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		JLabel lblTtulo = new JLabel("T\u00EDtulo:");
		lblTtulo.setBounds(26, 11, 46, 14);
		contentPane.add(lblTtulo);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(24, 36, 319, 20);
		contentPane.add(txtTitulo);
		txtTitulo.setColumns(10);
		
		JLabel lblNoticia = new JLabel("Noticia:");
		lblNoticia.setBounds(26, 67, 46, 14);
		contentPane.add(lblNoticia);
		
		txtDescNoticia = new JTextPane();
		txtDescNoticia.setBounds(24, 92, 319, 87);
		contentPane.add(txtDescNoticia);
		
		JLabel lblImgen = new JLabel("Im\u00E1gen:");
		lblImgen.setBounds(26, 190, 46, 14);
		contentPane.add(lblImgen);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(26, 388, 139, 32);
		contentPane.add(btnRegistrar);
		
		btnSubirFoto = new JButton("Seleccionar imagen...");
		btnSubirFoto.setHorizontalAlignment(SwingConstants.LEFT);
		btnSubirFoto.setIcon(new ImageIcon(NuevaNoticiaView.class.getResource("/javax/swing/plaf/basic/icons/image-delayed.png")));
		btnSubirFoto.setBounds(26, 323, 317, 32);
		contentPane.add(btnSubirFoto);
		
		lblImg = new JLabel("Im\u00E1gen");
		lblImg.setBackground(Color.WHITE);
		lblImg.setHorizontalAlignment(SwingConstants.CENTER);
		lblImg.setBounds(82, 210, 204, 102);
		lblImg.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		contentPane.add(lblImg);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setVisible(false);
		btnEliminar.setBounds(204, 388, 139, 32);
		contentPane.add(btnEliminar);
		
		JLabel lblFechaActual = new JLabel("Fecha Actual:");
		lblFechaActual.setBounds(174, 11, 75, 14);
		contentPane.add(lblFechaActual);
		
		lblFecha = new JLabel("");
		lblFecha.setBounds(254, 11, 89, 14);
		contentPane.add(lblFecha);
	}
}
