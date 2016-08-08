package com.greenpear.it.scaepro.view.incidencias;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class Incidencias extends JInternalFrame {

	private JTextField txtNombre;
	private JTextField txtApePat;
	private JTextField txtApeMat;
	private JComboBox cmbArea;
	private JComboBox cmbTurno;
	private JComboBox cmbPuesto;
	private JButton btnConsultar;
	private JButton btnLimpiar;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Incidencias frame = new Incidencias();
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
	public Incidencias() {
		setFrameIcon(new ImageIcon(Incidencias.class.getResource("/img/Logo1.png")));
		setClosable(true);
		setIconifiable(true);
		setTitle("Incidencias");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 965, 595);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pnlNombre = new JPanel();
		pnlNombre.setLayout(null);
		pnlNombre.setBorder(new TitledBorder(null, "Por nombre:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlNombre.setBackground(SystemColor.activeCaption);
		pnlNombre.setBounds(10, 21, 357, 179);
		contentPane.add(pnlNombre);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(18, 40, 120, 16);
		pnlNombre.add(lblNombre);

		JLabel lblApePat = new JLabel("Apellido paterno:");
		lblApePat.setBounds(18, 80, 120, 16);
		pnlNombre.add(lblApePat);

		JLabel lblApeMat = new JLabel("Apellido materno:");
		lblApeMat.setBounds(18, 120, 120, 16);
		pnlNombre.add(lblApeMat);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(130, 35, 204, 26);
		pnlNombre.add(txtNombre);

		txtApePat = new JTextField();
		txtApePat.setColumns(10);
		txtApePat.setBounds(130, 75, 204, 26);
		pnlNombre.add(txtApePat);

		txtApeMat = new JTextField();
		txtApeMat.setColumns(10);
		txtApeMat.setBounds(130, 115, 204, 26);
		pnlNombre.add(txtApeMat);

		JPanel pnlArea = new JPanel();
		pnlArea.setLayout(null);
		pnlArea.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Por area:",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlArea.setBackground(SystemColor.activeCaption);
		pnlArea.setBounds(379, 21, 357, 179);
		contentPane.add(pnlArea);

		JLabel lblArea = new JLabel("Area:");
		lblArea.setBounds(16, 40, 61, 16);
		pnlArea.add(lblArea);

		JLabel lblTurno = new JLabel("Turno:");
		lblTurno.setBounds(16, 80, 61, 16);
		pnlArea.add(lblTurno);

		JLabel label_5 = new JLabel("Puesto:");
		label_5.setBounds(16, 120, 61, 16);
		pnlArea.add(label_5);

		cmbArea = new JComboBox();
		cmbArea.setModel(new DefaultComboBoxModel(new String[] {"------Seleccione area------"}));
		cmbArea.setBounds(89, 36, 247, 27);
		pnlArea.add(cmbArea);

		cmbTurno = new JComboBox();
		cmbTurno.setEnabled(false);
		cmbTurno.setModel(new DefaultComboBoxModel(new String[] {"-----Seleccionar turno-----"}));
		cmbTurno.setBounds(89, 76, 247, 27);
		pnlArea.add(cmbTurno);

		cmbPuesto = new JComboBox();
		cmbPuesto.setModel(new DefaultComboBoxModel(new String[] {"-----Seleccione puesto-----", "CEO", "Socio empresarial", "Gerente Comercial", ".....Gerente de Ventas", ".....Sub Gerente Comercial o de Ventas", ".....Jefe/ Supervisor de Ventas", ".....Vendedor(a)/ Representante Comercial", ".....Vendedor(a) de Salon", ".....Promotor(a)", ".....Administrativo Area Ventas", ".....Asistente Comercial", ".....Preventista", ".....Encargado/ Asistente de Escrituraciones", ".....Gerente Departamental/ Sucursal/ Unidad de Negocios", ".....Comercial de Unidad de Negocios", ".....Encargado(a) de Local", ".....Analista de Ventas", ".....Ejecutivo/ Oficial/ Asesor de Ventas/ Cuentas/ Creditos", ".....Ejecutivo/ Oficial/ Asesor de Ventas/ Cuentas/ Creditos Junior", ".....Desarrollador de Negocios (Business Developer)", ".....Gerente de Marketing", ".....Gerente Marketing por Unidad de Negocios", ".....Encargado/ Jefe/ Supervisor(a) de Marketing", ".....Ejecutivo(a) de Marketing", ".....Gerente/ Jefe de Producto\t", ".....Investigador de Mercado", ".....Analista de Trade Marketing", ".....Analista/ Asesor de Marketing", ".....Merchandiser - Encargado(a) de Stock Comercio Minorista", ".....Analista/ Asesor de Marketing Junior", ".....Asistente de Marketing", ".....Community Manager", ".....Brand Manager", ".....Channel Manager / Jefe o Encargado de Canal", ".....Supervisor(a) de Telemarketing\t", ".....Telemarketero(a)", "Director/ Gerente de Administracion y Finanzas", ".....Gerente Administrativo y/o de Operaciones", ".....Jefe/ Encargado/ Supervisor de Operaciones", ".....Jefe/ Supervisor/ Encargado Administrativo", ".....Auxiliar/ Secretario(a) Administrativo(a)/ de Operaciones\t", ".....Analista de Control de Gestion/ Auditor de Operaciones/ Controller", ".....Contador General / Gerente de Contabilidad", ".....Jefe/ Supervisor/ Encargado/ Analista de Contabilidad", ".....Auxiliar Contable", ".....Analista/ Encargado(a) de Impuestos\t", ".....Asistente/ Auxiliar de Impuestos\t", ".....Analista/ Asistente de Costos/ Presupuestos", ".....Encargado(a) de Cuentas a Pagar / Pagos a Proveedores", ".....Encargado(a)/ Auxiliar de Patrimonio", ".....Gerente de Finanzas y/o de Creditos", ".....Supervisor(a)/ Jefe(a)/ Coordinador(a) de Finanzas/ Creditos", ".....Analista/ Asesor de Finanzas/ Microfinanzas", ".....Auxiliar de Finanzas/ Analista de Finanzas Junior", ".....Encargado(a) de Facturacion y/o Cuentas Corrientes", ".....Auxiliar de Facturacion y/o Cuentas Corrientes", ".....Encargado(a) de Creditos y/o Cobranzas", ".....Auxiliar de Creditos y/o Cobranzas", ".....Analista de Creditos/ Riesgos Crediticios/ Operacionales", ".....Analista de Creditos/ Riesgos Crediticios Junior", ".....Tesorero(a)", ".....Asistente de Tesorera", ".....Supervisor de Cajas", ".....Cajero(a)", ".....Recontador de billetes", "Director/ Gerente General/ ", ".....Sub Gerente General", ".....Gerente Propietario", ".....Gerente/ Jefe de Planificacion", ".....Analista de Planificacion", ".....Asistente de Planificacion", ".....Secretaria(o) de Gerencia/ Directorio", ".....Gerente/ Jefe de Comunicacion Interinstitucional/ Relaciones Publicas", ".....Asistente de Comunicacion Interinstitucional/ Relaciones Publicas", ".....Auditor Senior", ".....Auditor", ".....Auditor Junior", "Gerente de Informatica y/o de Tecnologia", ".....Encargado de Informatica\t", ".....Asistente Informatico", ".....Gerente de Analisis y Diseeño de Sistemas", ".....Arquitecto(a) de Sistemas", ".....Analista de Sistemas Informaticos", ".....Analista de Sistemas Informaticos Junior", ".....Jefe de Organizacion y Metodos", ".....Analista de Organizacion y Metodos", ".....Gerente/ Jefe de Desarrollo de Sistemas", ".....Analista-Programador/ Desarrollador", ".....Programador/ Desarrollador Senior", ".....Programador", ".....Tester o Controlador de Calidad de Software", ".....Programador Junior", ".....Tecnico en Implementacion de Sistemas Informaticos", ".....Gerente de Infraestructura", ".....Encargado de Infraestructura/ Mantenimiento (Hardware y/o Software)", ".....Jefe de Procesamiento de Datos u Operaciones", ".....Administrador de Base de Datos", ".....Administrador de Redes/ Servidores", ".....Webmaster", ".....Digitador / Operador de Computador/ Sistemas", ".....Encargado(a)/ Asistente de Codificacion", ".....Gerente/ Jefe/ Encargado de Seguridad Informatica", ".....Auditor(a) Informatico", ".....Gerente/ Jefe/ Encargado(a) de Desarrollo Web", ".....Diseño/ Desarrollador Web", ".....Diseño/ Desarrollador Web Junior\t", ".....Gerente/ Encargado de Soporte a Usuarios", ".....Tecnico en Soporte a Usuarios / Help Desk", ".....Tecnico en Investigacion y Desarrollo Informaticos/ Tecnologicos", "Gerente de Produccion", ".....Supervisor/ Jefe/ Encargado de Produccion", ".....Gerente/ Ingeniero de Producto/ Investigacion y Desarrollo", ".....Asistente de Investigacion y Desarrollo", ".....Jefe de Laboratorio industrial/ quimico/ bioquimico", ".....Tecnico o Asistente de Laboratorio", ".....Proyectista/ Diseño Industrial", ".....Asistente de Proyectista / Diseño Industrial\t", ".....Gerente de Planta/ Gerente Tecnico Industrial", ".....Jefe de Planificacion de la Produccion", ".....Ingeniero/ Tecnico/ de Procesos Industriales", ".....Asistente de Procesos Industriales", ".....Supervisor/ Jefe de Planta/ Sector", ".....Asistente de Planta/ Produccion", ".....Jefe de Turno", ".....Operario de Industria (general) ", ".....Operador de Maquinas/ Equipamiento ", ".....Jefe/ Encargado de Mantenimiento Industrial (Mecanico/ Electrico)", ".....Tecnico en Mantenimiento Industrial (Mecanico/ Electrico)", ".....Gerente/ Encargado de Control de Calidad", ".....Tecnico en Control de Calidad", ".....Asistente de Control de Calidad", ".....Tecnico en Seguridad e Higiene Industrial", "Gerente de Recursos Humanos", ".....Jefe(a)/ Encargado(a) de Recursos Humanos", ".....Auxiliar de Recursos Humanos", ".....Encargado(a) de Capacitacion y/o Desarrollo Organizacional", ".....Encargado(a) de Reclutamiento y/o Seleccion de Personal", ".....Encargado(a) de Administracion del Personal", ".....Liquidador(a) de Sueldos y Jornales", "Gerente de Compras/ Adquisiciones", ".....Jefe o Gerente de Comercio Exterior", ".....Analista o Encargado de Comercio Exterior", ".....Auxiliar Comercio Exterior", ".....Jefe/ Supervisor de Compras o Abastecimiento", ".....Encargado de Compras/ Abastecimiento", ".....Asistente/ Auxiliar de Compras/ Abastecimiento", ".....Jefe/ Supervisor/ Encargado de Licitaciones", ".....Asistente o Auxiliar de Licitaciones", ".....Jefe/ Supervisor/ Encargado de Logistica/ Despacho/ Transporte", ".....Asistente de Logistica/ Despacho/Transporte// Empaquetador/ Repartidor", ".....Repositor(a)", ".....Jefe/ Supervisor/ Encargado de Deposito", ".....Auxiliar de Deposito", ".....Administrativo area Stock/ Deposito/ Logistica", "Gerente/ Jefe(a)/ Encargado(a) de Servicios Generales", ".....Asistente Servicios Generales\t", ".....Secretario(a) General - Secretario(a) Ejecutivo - Office Manager", ".....Secretario(a)", ".....Asistente de Secretaria", ".....Atencion al Cliente/ Recepcionista", ".....Chofer", ".....Cadete u Ordenanza", ".....Chofer-Cobrador", ".....Cobrador(a)", ".....Gestor(a)", ".....Encargado(a) de Limpieza ", ".....Operario de Limpieza ", ".....Supervisor de Call Center", ".....Operador de Call Center / Atencion Telefonica al Cliente/ Telefonista\t", ".....Jefe o Auxiliar de Suministros", ".....Encargado(a) de Archivo / Centro de Documentacion/ Biblioteca", ".....Asistente de Archivo / Biblioteca / Documentacion", ".....Coordinador/ Supervisor de Eventos", ".....Asistente de Eventos", ".....Agente de Servicio Post Venta"}));
		cmbPuesto.setBounds(89, 116, 247, 27);
		pnlArea.add(cmbPuesto);

		JScrollPane scrollConsulta = new JScrollPane((Component) null);
		scrollConsulta.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollConsulta.setBackground(SystemColor.activeCaption);
		scrollConsulta.setBounds(10, 211, 931, 330);
		contentPane.add(scrollConsulta);
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));
		scrollConsulta.setViewportView(table);

		btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(773, 50, 140, 30);
		contentPane.add(btnConsultar);

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(773, 120, 140, 30);
		contentPane.add(btnLimpiar);
	}
	// Setters and Getters

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JTextField getTxtApePat() {
		return txtApePat;
	}

	public void setTxtApePat(JTextField txtApePat) {
		this.txtApePat = txtApePat;
	}

	public JTextField getTxtApeMat() {
		return txtApeMat;
	}

	public void setTxtApeMat(JTextField txtApeMat) {
		this.txtApeMat = txtApeMat;
	}

	public JComboBox getCmbArea() {
		return cmbArea;
	}

	public void setCmbArea(JComboBox cmbArea) {
		this.cmbArea = cmbArea;
	}

	public JComboBox getCmbTurno() {
		return cmbTurno;
	}

	public void setCmbTurno(JComboBox cmbTurno) {
		this.cmbTurno = cmbTurno;
	}

	public JComboBox getCmbPuesto() {
		return cmbPuesto;
	}

	public void setCmbPuesto(JComboBox cmbPuesto) {
		this.cmbPuesto = cmbPuesto;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(JButton btnConsultar) {
		this.btnConsultar = btnConsultar;
	}

	public JButton getBtnLimpiar() {
		return btnLimpiar;
	}

	public void setBtnLimpiar(JButton btnLimpiar) {
		this.btnLimpiar = btnLimpiar;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

}
