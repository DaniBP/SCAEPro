package com.greenpear.it.scaepro.view.configurarempleados;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.JRadioButton;

public class ConsultarEmpleado extends JInternalFrame {
	private JTable table;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApePat;
	private JTextField txtApeMat;
	private JComboBox cmbArea;
	private JComboBox cmbTurno;
	private JComboBox cmbPuesto;
	private JButton btnConsultar;
	private JButton btnLimpiar;
	private JRadioButton rdbtnPorAsignacion;
	private JRadioButton rdbtnPorArea;
	/**
	 * Create the frame.
	 */
	public ConsultarEmpleado() {
		setFrameIcon(new ImageIcon(ConsultarEmpleado.class.getResource("/img/Logo1.png")));
		setIconifiable(true);
		setClosable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 970, 580);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 10, 357, 179);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(20, 67, 120, 16);
		panel.add(lblNewLabel);
		
		JLabel lblApellidoPaterno = new JLabel("Apellido paterno:");
		lblApellidoPaterno.setBounds(20, 107, 120, 16);
		panel.add(lblApellidoPaterno);
		
		JLabel lblApellidoMaterno = new JLabel("Apellido materno:");
		lblApellidoMaterno.setBounds(20, 147, 120, 16);
		panel.add(lblApellidoMaterno);
		
		txtNombre = new JTextField();
		txtNombre.setEnabled(false);
		txtNombre.setBounds(132, 62, 204, 26);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApePat = new JTextField();
		txtApePat.setEnabled(false);
		txtApePat.setColumns(10);
		txtApePat.setBounds(132, 102, 204, 26);
		panel.add(txtApePat);
		
		txtApeMat = new JTextField();
		txtApeMat.setEnabled(false);
		txtApeMat.setColumns(10);
		txtApeMat.setBounds(132, 142, 204, 26);
		panel.add(txtApeMat);
		
		rdbtnPorArea = new JRadioButton("Por area:");
		rdbtnPorArea.setBackground(SystemColor.activeCaption);
		rdbtnPorArea.setBounds(132, 22, 79, 23);
		panel.add(rdbtnPorArea);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(379, 10, 357, 179);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblArea = new JLabel("Area:");
		lblArea.setBounds(24, 65, 61, 16);
		panel_1.add(lblArea);
		
		JLabel lblTurno = new JLabel("Turno:");
		lblTurno.setBounds(24, 105, 61, 16);
		panel_1.add(lblTurno);
		
		JLabel lblPuesto = new JLabel("Puesto:");
		lblPuesto.setBounds(24, 145, 61, 16);
		panel_1.add(lblPuesto);
		
		cmbArea = new JComboBox();
		cmbArea.setEnabled(false);
		cmbArea.setModel(new DefaultComboBoxModel(new String[] {"------Seleccione area------"}));
		cmbArea.setBounds(93, 61, 247, 27);
		panel_1.add(cmbArea);
		
		cmbTurno = new JComboBox();
		cmbTurno.setEnabled(false);
		cmbTurno.setModel(new DefaultComboBoxModel(new String[] {"-----Seleccionar turno-----"}));
		cmbTurno.setBounds(93, 101, 247, 27);
		panel_1.add(cmbTurno);
		
		cmbPuesto = new JComboBox();
		cmbPuesto.setEnabled(false);
		cmbPuesto.setModel(new DefaultComboBoxModel(new String[] {"-----Seleccione puesto-----", "CEO", "Socio empresarial", "Gerente Comercial", ".....Gerente de Ventas", ".....Sub Gerente Comercial o de Ventas", ".....Jefe/ Supervisor de Ventas", ".....Vendedor(a)/ Representante Comercial", ".....Vendedor(a) de Salon", ".....Promotor(a)", ".....Administrativo Area Ventas", ".....Asistente Comercial", ".....Preventista", ".....Encargado/ Asistente de Escrituraciones", ".....Gerente Departamental/ Sucursal/ Unidad de Negocios", ".....Comercial de Unidad de Negocios", ".....Encargado(a) de Local", ".....Analista de Ventas", ".....Ejecutivo/ Oficial/ Asesor de Ventas/ Cuentas/ Creditos", ".....Ejecutivo/ Oficial/ Asesor de Ventas/ Cuentas/ Creditos Junior", ".....Desarrollador de Negocios (Business Developer)", ".....Gerente de Marketing", ".....Gerente Marketing por Unidad de Negocios", ".....Encargado/ Jefe/ Supervisor(a) de Marketing", ".....Ejecutivo(a) de Marketing", ".....Gerente/ Jefe de Producto\t", ".....Investigador de Mercado", ".....Analista de Trade Marketing", ".....Analista/ Asesor de Marketing", ".....Merchandiser - Encargado(a) de Stock Comercio Minorista", ".....Analista/ Asesor de Marketing Junior", ".....Asistente de Marketing", ".....Community Manager", ".....Brand Manager", ".....Channel Manager / Jefe o Encargado de Canal", ".....Supervisor(a) de Telemarketing\t", ".....Telemarketero(a)", "Director/ Gerente de Administracion y Finanzas", ".....Gerente Administrativo y/o de Operaciones", ".....Jefe/ Encargado/ Supervisor de Operaciones", ".....Jefe/ Supervisor/ Encargado Administrativo", ".....Auxiliar/ Secretario(a) Administrativo(a)/ de Operaciones\t", ".....Analista de Control de Gestion/ Auditor de Operaciones/ Controller", ".....Contador General / Gerente de Contabilidad", ".....Jefe/ Supervisor/ Encargado/ Analista de Contabilidad", ".....Auxiliar Contable", ".....Analista/ Encargado(a) de Impuestos\t", ".....Asistente/ Auxiliar de Impuestos\t", ".....Analista/ Asistente de Costos/ Presupuestos", ".....Encargado(a) de Cuentas a Pagar / Pagos a Proveedores", ".....Encargado(a)/ Auxiliar de Patrimonio", ".....Gerente de Finanzas y/o de Creditos", ".....Supervisor(a)/ Jefe(a)/ Coordinador(a) de Finanzas/ Creditos", ".....Analista/ Asesor de Finanzas/ Microfinanzas", ".....Auxiliar de Finanzas/ Analista de Finanzas Junior", ".....Encargado(a) de Facturacion y/o Cuentas Corrientes", ".....Auxiliar de Facturacion y/o Cuentas Corrientes", ".....Encargado(a) de Creditos y/o Cobranzas", ".....Auxiliar de Creditos y/o Cobranzas", ".....Analista de Creditos/ Riesgos Crediticios/ Operacionales", ".....Analista de Creditos/ Riesgos Crediticios Junior", ".....Tesorero(a)", ".....Asistente de Tesorera", ".....Supervisor de Cajas", ".....Cajero(a)", ".....Recontador de billetes", "Director/ Gerente General/ ", ".....Sub Gerente General", ".....Gerente Propietario", ".....Gerente/ Jefe de Planificacion", ".....Analista de Planificacion", ".....Asistente de Planificacion", ".....Secretaria(o) de Gerencia/ Directorio", ".....Gerente/ Jefe de Comunicacion Interinstitucional/ Relaciones Publicas", ".....Asistente de Comunicacion Interinstitucional/ Relaciones Publicas", ".....Auditor Senior", ".....Auditor", ".....Auditor Junior", "Gerente de Informatica y/o de Tecnologia", ".....Encargado de Informatica\t", ".....Asistente Informatico", ".....Gerente de Analisis y Diseeño de Sistemas", ".....Arquitecto(a) de Sistemas", ".....Analista de Sistemas Informaticos", ".....Analista de Sistemas Informaticos Junior", ".....Jefe de Organizacion y Metodos", ".....Analista de Organizacion y Metodos", ".....Gerente/ Jefe de Desarrollo de Sistemas", ".....Analista-Programador/ Desarrollador", ".....Programador/ Desarrollador Senior", ".....Programador", ".....Tester o Controlador de Calidad de Software", ".....Programador Junior", ".....Tecnico en Implementacion de Sistemas Informaticos", ".....Gerente de Infraestructura", ".....Encargado de Infraestructura/ Mantenimiento (Hardware y/o Software)", ".....Jefe de Procesamiento de Datos u Operaciones", ".....Administrador de Base de Datos", ".....Administrador de Redes/ Servidores", ".....Webmaster", ".....Digitador / Operador de Computador/ Sistemas", ".....Encargado(a)/ Asistente de Codificacion", ".....Gerente/ Jefe/ Encargado de Seguridad Informatica", ".....Auditor(a) Informatico", ".....Gerente/ Jefe/ Encargado(a) de Desarrollo Web", ".....Diseño/ Desarrollador Web", ".....Diseño/ Desarrollador Web Junior\t", ".....Gerente/ Encargado de Soporte a Usuarios", ".....Tecnico en Soporte a Usuarios / Help Desk", ".....Tecnico en Investigacion y Desarrollo Informaticos/ Tecnologicos", "Gerente de Produccion", ".....Supervisor/ Jefe/ Encargado de Produccion", ".....Gerente/ Ingeniero de Producto/ Investigacion y Desarrollo", ".....Asistente de Investigacion y Desarrollo", ".....Jefe de Laboratorio industrial/ quimico/ bioquimico", ".....Tecnico o Asistente de Laboratorio", ".....Proyectista/ Diseño Industrial", ".....Asistente de Proyectista / Diseño Industrial\t", ".....Gerente de Planta/ Gerente Tecnico Industrial", ".....Jefe de Planificacion de la Produccion", ".....Ingeniero/ Tecnico/ de Procesos Industriales", ".....Asistente de Procesos Industriales", ".....Supervisor/ Jefe de Planta/ Sector", ".....Asistente de Planta/ Produccion", ".....Jefe de Turno", ".....Operario de Industria (general) ", ".....Operador de Maquinas/ Equipamiento ", ".....Jefe/ Encargado de Mantenimiento Industrial (Mecanico/ Electrico)", ".....Tecnico en Mantenimiento Industrial (Mecanico/ Electrico)", ".....Gerente/ Encargado de Control de Calidad", ".....Tecnico en Control de Calidad", ".....Asistente de Control de Calidad", ".....Tecnico en Seguridad e Higiene Industrial", "Gerente de Recursos Humanos", ".....Jefe(a)/ Encargado(a) de Recursos Humanos", ".....Auxiliar de Recursos Humanos", ".....Encargado(a) de Capacitacion y/o Desarrollo Organizacional", ".....Encargado(a) de Reclutamiento y/o Seleccion de Personal", ".....Encargado(a) de Administracion del Personal", ".....Liquidador(a) de Sueldos y Jornales", "Gerente de Compras/ Adquisiciones", ".....Jefe o Gerente de Comercio Exterior", ".....Analista o Encargado de Comercio Exterior", ".....Auxiliar Comercio Exterior", ".....Jefe/ Supervisor de Compras o Abastecimiento", ".....Encargado de Compras/ Abastecimiento", ".....Asistente/ Auxiliar de Compras/ Abastecimiento", ".....Jefe/ Supervisor/ Encargado de Licitaciones", ".....Asistente o Auxiliar de Licitaciones", ".....Jefe/ Supervisor/ Encargado de Logistica/ Despacho/ Transporte", ".....Asistente de Logistica/ Despacho/Transporte// Empaquetador/ Repartidor", ".....Repositor(a)", ".....Jefe/ Supervisor/ Encargado de Deposito", ".....Auxiliar de Deposito", ".....Administrativo area Stock/ Deposito/ Logistica", "Gerente/ Jefe(a)/ Encargado(a) de Servicios Generales", ".....Asistente Servicios Generales\t", ".....Secretario(a) General - Secretario(a) Ejecutivo - Office Manager", ".....Secretario(a)", ".....Asistente de Secretaria", ".....Atencion al Cliente/ Recepcionista", ".....Chofer", ".....Cadete u Ordenanza", ".....Chofer-Cobrador", ".....Cobrador(a)", ".....Gestor(a)", ".....Encargado(a) de Limpieza ", ".....Operario de Limpieza ", ".....Supervisor de Call Center", ".....Operador de Call Center / Atencion Telefonica al Cliente/ Telefonista\t", ".....Jefe o Auxiliar de Suministros", ".....Encargado(a) de Archivo / Centro de Documentacion/ Biblioteca", ".....Asistente de Archivo / Biblioteca / Documentacion", ".....Coordinador/ Supervisor de Eventos", ".....Asistente de Eventos", ".....Agente de Servicio Post Venta"}));
		cmbPuesto.setBounds(93, 141, 247, 27);
		panel_1.add(cmbPuesto);
		
		rdbtnPorAsignacion = new JRadioButton("Por asignacion:");
		rdbtnPorAsignacion.setBackground(SystemColor.activeCaption);
		rdbtnPorAsignacion.setBounds(129, 18, 99, 23);
		panel_1.add(rdbtnPorAsignacion);
		
		JScrollPane ScrollConsulta = new JScrollPane(table);
		ScrollConsulta.setBackground(SystemColor.activeCaption);
		ScrollConsulta.setBounds(10, 201, 931, 330);
		contentPane.add(ScrollConsulta);
		ScrollConsulta.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		ScrollConsulta.setViewportView(table);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(770, 46, 140, 30);
		contentPane.add(btnConsultar);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(770, 126, 140, 30);
		contentPane.add(btnLimpiar);
		ButtonGroup radios= new ButtonGroup();
		radios.add(rdbtnPorArea);
		radios.add(rdbtnPorAsignacion);
		
	}
	//Setters and Getters
	public JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		this.table = table;
	}
//	public JPanel getContentPane() {
//		return contentPane;
//	}
//	public void setContentPane(JPanel contentPane) {
//		this.contentPane = contentPane;
//	}
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
	public JRadioButton getRdbtnPorAsignacion() {
		return rdbtnPorAsignacion;
	}
	public void setRdbtnPorAsignacion(JRadioButton rdbtnPorAsignacion) {
		this.rdbtnPorAsignacion = rdbtnPorAsignacion;
	}
	public JRadioButton getRdbtnPorArea() {
		return rdbtnPorArea;
	}
	public void setRdbtnPorArea(JRadioButton rdbtnPorArea) {
		this.rdbtnPorArea = rdbtnPorArea;
	}	
	
}
