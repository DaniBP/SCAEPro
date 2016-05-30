package com.greenpear.it.scaepro.view.accesosistema;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.greenpear.it.scaepro.view.administracionmovil.AdminNoticiasView;

import java.awt.Frame;
import java.awt.Image;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import java.awt.Toolkit;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PrincipalView extends JFrame {

	private JPanel contentPane;
	/**
	 * @wbp.nonvisual location=588,-31
	 */
	private final JToggleButton toggleButton = new JToggleButton("New toggle button");
	private JDesktopPane desktopPane;

	private AdminNoticiasView adminNot;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalView frame = new PrincipalView();
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
	public PrincipalView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PrincipalView.class.getResource("/img/LogoGreenPearfin.png")));
		setTitle("Administraci\u00F3n SCAE Pro");
		setBounds(100, 100, 1280, 720);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnEstatusDeConexin = new JMenu("Archivo");
		menuBar.add(mnEstatusDeConexin);
		
		JMenu mnUsuarios = new JMenu("Usuarios");
		menuBar.add(mnUsuarios);
		
		JMenu mnreas = new JMenu("\u00C1reas");
		menuBar.add(mnreas);
		
		JMenu mnHorarios = new JMenu("Horarios");
		menuBar.add(mnHorarios);
		
		JMenu mnEmpleados = new JMenu("Empleados");
		menuBar.add(mnEmpleados);
		
		JMenu mnJustificacin = new JMenu("Justificaci\u00F3n");
		menuBar.add(mnJustificacin);
		
		JMenu mnReportes = new JMenu("Reportes");
		menuBar.add(mnReportes);
		
		JMenu mnAdministracinMvil = new JMenu("Administraci\u00F3n m\u00F3vil");
		menuBar.add(mnAdministracinMvil);
		
		JMenuItem mntmAdministrarNotcias = new JMenuItem("Administrar not\u00EDcias");
		mntmAdministrarNotcias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showAdminNoticias();
				
			}
		});
		mnAdministracinMvil.add(mntmAdministrarNotcias);
		
		JMenu mnEstatusDeConexion = new JMenu("Estatus de conexi\u00F3n");
		menuBar.add(mnEstatusDeConexion);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImageIcon fot = new ImageIcon(PrincipalView.class.getResource("/img/SCAEPro Logo.png"));
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(SystemColor.activeCaption);
		desktopPane.setBounds(0, 0, 980, 570);
		contentPane.add(desktopPane);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(230, 180, 800, 277);
		desktopPane.add(lblNewLabel);
		Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_DEFAULT));
		lblNewLabel.setIcon(icono);
		
//		this.adminNot = new AdminNoticiasView();
//		desktopPane.add(adminNot);
	}

	protected void showAdminNoticias() {
		if(adminNot.isShowing()){
			adminNot.toFront();
		}else{
			adminNot.show();
		}
	}
}
