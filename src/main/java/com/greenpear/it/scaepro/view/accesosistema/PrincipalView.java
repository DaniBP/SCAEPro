package com.greenpear.it.scaepro.view.accesosistema;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Image;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.UIManager;

import java.awt.Toolkit;
import javax.swing.JDesktopPane;

public class PrincipalView extends JFrame {

	private JPanel contentPane;

	private final JToggleButton toggleButton = new JToggleButton("New toggle button");
	private JDesktopPane panelEscritorio;

	private JMenuItem mntmSalir;
	private JMenuItem mntmRegistrarUsuario;
	private JMenuItem mntmConsultarUsuarios;
	private JMenuItem mntmRegistrarrea;
	private JMenuItem mntmConsultarreas;
	private JMenuItem mntmConfigurarScaePro;
	private JMenuItem mntmRegistrarEmpleado;
	private JMenuItem mntmConsultarEmpleados;
	private JMenuItem mntmJustificarIncidencias;
	private JMenuItem mntmGenerarReporte;
	private JMenuItem mntmAdministrarNotcias;
	private JMenuItem mntmAdministrarAvisos;
	private JMenuItem mntmVerificarEstatus;
	private JMenuItem mntmEstatusDePago;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(PrincipalView.class.getResource("/img/Logo1.png")));
		setTitle("Administraci\u00F3n SCAE Pro");
		setBounds(100, 100, 1280, 720);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnEstatusDeConexin = new JMenu("Archivo");
		menuBar.add(mnEstatusDeConexin);
		
		mntmSalir = new JMenuItem("Salir");
		mnEstatusDeConexin.add(mntmSalir);
		
		JMenu mnUsuarios = new JMenu("Usuarios");
		menuBar.add(mnUsuarios);
		
		mntmRegistrarUsuario = new JMenuItem("Registrar usuario");
		mnUsuarios.add(mntmRegistrarUsuario);
		
		mntmConsultarUsuarios = new JMenuItem("Consultar usuarios");
		mnUsuarios.add(mntmConsultarUsuarios);
		
		JMenu mnreas = new JMenu("\u00C1reas");
		menuBar.add(mnreas);
		
		mntmRegistrarrea = new JMenuItem("Registrar \u00E1rea");
		mnreas.add(mntmRegistrarrea);
		
		mntmConsultarreas = new JMenuItem("Consultar \u00E1reas");
		mnreas.add(mntmConsultarreas);
		
		JMenu mnHorarios = new JMenu("Horarios");
		menuBar.add(mnHorarios);
		
		mntmConfigurarScaePro = new JMenuItem("Configurar Scae Pro");
		mnHorarios.add(mntmConfigurarScaePro);
		
		JMenu mnEmpleados = new JMenu("Empleados");
		menuBar.add(mnEmpleados);
		
		mntmRegistrarEmpleado = new JMenuItem("Registrar empleado");
		mnEmpleados.add(mntmRegistrarEmpleado);
		
		mntmConsultarEmpleados = new JMenuItem("Consultar empleados");
		mnEmpleados.add(mntmConsultarEmpleados);
		
		JMenu mnJustificacin = new JMenu("Justificaci\u00F3n");
		menuBar.add(mnJustificacin);
		
		mntmJustificarIncidencias = new JMenuItem("Justificar incidencias");
		mnJustificacin.add(mntmJustificarIncidencias);
		
		JMenu mnReportes = new JMenu("Reportes");
		menuBar.add(mnReportes);
		
		mntmGenerarReporte = new JMenuItem("Generar reporte");
		mnReportes.add(mntmGenerarReporte);
		
		JMenu mnAdministracinMvil = new JMenu("Administraci\u00F3n m\u00F3vil");
		menuBar.add(mnAdministracinMvil);
		
		mntmAdministrarNotcias = new JMenuItem("Administrar noticias");

		mnAdministracinMvil.add(mntmAdministrarNotcias);
		
		mntmAdministrarAvisos = new JMenuItem("Generar aviso");
		mnAdministracinMvil.add(mntmAdministrarAvisos);
		
		mntmEstatusDePago = new JMenuItem("Estatus de pago");
		mnAdministracinMvil.add(mntmEstatusDePago);
		
		JMenu mnEstatusDeConexion = new JMenu("Estatus de conexi\u00F3n");
		menuBar.add(mnEstatusDeConexion);
		
		mntmVerificarEstatus = new JMenuItem("Verificar estatus");
		mnEstatusDeConexion.add(mntmVerificarEstatus);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImageIcon fot = new ImageIcon(PrincipalView.class.getResource("/img/SCAEPro Logo.png"));
		
		panelEscritorio = new JDesktopPane();
		panelEscritorio.setBackground(SystemColor.activeCaption);
		panelEscritorio.setBounds(0, 0, getWidth()-6, getHeight()-50);
		contentPane.add(panelEscritorio);
	
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(230, 180, 800, 277);
		panelEscritorio.add(lblNewLabel);
		Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_DEFAULT));
		lblNewLabel.setIcon(icono);
		
		try {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (Exception e) {
		JOptionPane.showMessageDialog(null, "Imposible modificar el tema visual", 
		"Lookandfeel inválido.",
		JOptionPane.ERROR_MESSAGE);
	}

	}

	public JDesktopPane getPanelEscritorio() {
		return panelEscritorio;
	}

	public JMenuItem getMntmSalir() {
		return mntmSalir;
	}

	public JMenuItem getMntmRegistrarUsuario() {
		return mntmRegistrarUsuario;
	}

	public JMenuItem getMntmConsultarUsuarios() {
		return mntmConsultarUsuarios;
	}

	public JMenuItem getMntmRegistrarrea() {
		return mntmRegistrarrea;
	}

	public JMenuItem getMntmConsultarreas() {
		return mntmConsultarreas;
	}

	public JMenuItem getMntmConfigurarScaePro() {
		return mntmConfigurarScaePro;
	}

	public JMenuItem getMntmRegistrarEmpleado() {
		return mntmRegistrarEmpleado;
	}

	public JMenuItem getMntmConsultarEmpleados() {
		return mntmConsultarEmpleados;
	}

	public JMenuItem getMntmJustificarIncidencias() {
		return mntmJustificarIncidencias;
	}

	public JMenuItem getMntmGenerarReporte() {
		return mntmGenerarReporte;
	}

	public JMenuItem getMntmAdministrarNotcias() {
		return mntmAdministrarNotcias;
	}

	public JMenuItem getMntmAdministrarAvisos() {
		return mntmAdministrarAvisos;
	}

	public JMenuItem getMntmVerificarEstatus() {
		return mntmVerificarEstatus;
	}

	public JMenuItem getMntmEstatusDePago() {
		return mntmEstatusDePago;
	}
	
	
}
