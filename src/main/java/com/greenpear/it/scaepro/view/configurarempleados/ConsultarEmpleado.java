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
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

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
		panel.setBorder(new TitledBorder(null, "Por nombre:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 10, 357, 179);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(18, 40, 120, 16);
		panel.add(lblNewLabel);
		
		JLabel lblApellidoPaterno = new JLabel("Apellido paterno:");
		lblApellidoPaterno.setBounds(18, 80, 120, 16);
		panel.add(lblApellidoPaterno);
		
		JLabel lblApellidoMaterno = new JLabel("Apellido materno:");
		lblApellidoMaterno.setBounds(18, 120, 120, 16);
		panel.add(lblApellidoMaterno);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(130, 35, 204, 26);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApePat = new JTextField();
		txtApePat.setColumns(10);
		txtApePat.setBounds(130, 75, 204, 26);
		panel.add(txtApePat);
		
		txtApeMat = new JTextField();
		txtApeMat.setColumns(10);
		txtApeMat.setBounds(130, 115, 204, 26);
		panel.add(txtApeMat);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Por area:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(379, 10, 357, 179);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblArea = new JLabel("Area:");
		lblArea.setBounds(16, 40, 61, 16);
		panel_1.add(lblArea);
		
		JLabel lblTurno = new JLabel("Turno:");
		lblTurno.setBounds(16, 80, 61, 16);
		panel_1.add(lblTurno);
		
		JLabel lblPuesto = new JLabel("Puesto:");
		lblPuesto.setBounds(16, 120, 61, 16);
		panel_1.add(lblPuesto);
		
		cmbArea = new JComboBox();
		cmbArea.setBounds(89, 36, 247, 27);
		panel_1.add(cmbArea);
		
		cmbTurno = new JComboBox();
		cmbTurno.setModel(new DefaultComboBoxModel(new String[] {"-----Seleccionar turno-----"}));
		cmbTurno.setBounds(89, 76, 247, 27);
		panel_1.add(cmbTurno);
		
		cmbPuesto = new JComboBox();
		cmbPuesto.setModel(new DefaultComboBoxModel(new String[] {"-----Seleccione puesto-----", "Gerente Comercial", ".....Gerente de Ventas", ".....Sub Gerente Comercial o de Ventas", ".....Jefe/ Supervisor de Ventas", ".....Vendedor(a)/ Representante Comercial", ".....Vendedor(a) de SalÃ³n", ".....Promotor(a)", ".....Administrativo Area Ventas", ".....Asistente Comercial", ".....Preventista", ".....Encargado/ Asistente de Escrituraciones", ".....Gerente Departamental/ Sucursal/ Unidad de Negocios", ".....Comercial de Unidad de Negocios", ".....Encargado(a) de Local", ".....Analista de Ventas", ".....Ejecutivo/ Oficial/ Asesor de Ventas/ Cuentas/ CrÃ©ditos", ".....Ejecutivo/ Oficial/ Asesor de Ventas/ Cuentas/ CrÃ©ditos Junior", ".....Desarrollador de Negocios (Business Developer)", ".....Gerente de Marketing", ".....Gerente Marketing por Unidad de Negocios", ".....Encargado/ Jefe/ Supervisor(a) de Marketing", ".....Ejecutivo(a) de Marketing", ".....Gerente/ Jefe de Producto\t", ".....Investigador de Mercado", ".....Analista de Trade Marketing", ".....Analista/ Asesor de Marketing", ".....Merchandiser - Encargado(a) de Stock Comercio Minorista", ".....Analista/ Asesor de Marketing Junior", ".....Asistente de Marketing", ".....Community Manager", ".....Brand Manager", ".....Channel Manager / Jefe o Encargado de Canal", ".....Supervisor(a) de Telemarketing\t", ".....Telemarketero(a)", "Director/ Gerente de AdministraciÃ³n y Finanzas", ".....Gerente Administrativo y/o de Operaciones", ".....Jefe/ Encargado/ Supervisor de Operaciones", ".....Jefe/ Supervisor/ Encargado Administrativo", ".....Auxiliar/ Secretario(a) Administrativo(a)/ de Operaciones\t", ".....Analista de Control de GestiÃ³n/ Auditor de Operaciones/ Controller", ".....Contador General / Gerente de Contabilidad", ".....Jefe/ Supervisor/ Encargado/ Analista de Contabilidad", ".....Auxiliar Contable", ".....Analista/ Encargado(a) de Impuestos\t", ".....Asistente/ Auxiliar de Impuestos\t", ".....Analista/ Asistente de Costos/ Presupuestos", ".....Encargado(a) de Cuentas a Pagar / Pagos a Proveedores", ".....Encargado(a)/ Auxiliar de Patrimonio", ".....Gerente de Finanzas y/o de CrÃ©ditos", ".....Supervisor(a)/ Jefe(a)/ Coordinador(a) de Finanzas/ CrÃ©ditos", ".....Analista/ Asesor de Finanzas/ Microfinanzas", ".....Auxiliar de Finanzas/ Analista de Finanzas Junior", ".....Encargado(a) de FacturaciÃ³n y/o Cuentas Corrientes", ".....Auxiliar de FacturaciÃ³n y/o Cuentas Corrientes", ".....Encargado(a) de CrÃ©ditos y/o Cobranzas", ".....Auxiliar de CrÃ©ditos y/o Cobranzas", ".....Analista de CrÃ©ditos/ Riesgos Crediticios/ Operacionales", ".....Analista de CrÃ©ditos/ Riesgos Crediticios Junior", ".....Tesorero(a)", ".....Asistente de TesorerÃ­a", ".....Supervisor de Cajas", ".....Cajero(a)", ".....Recontador de billetes", "Director/ Gerente General/ ", ".....Sub Gerente General", ".....Gerente Propietario", ".....Gerente/ Jefe de PlanificaciÃ³n", ".....Analista de PlanificaciÃ³n", ".....Asistente de PlanificaciÃ³n", ".....Secretaria(o) de Gerencia/ Directorio", ".....Gerente/ Jefe de ComunicaciÃ³n Interinstitucional/ Relaciones PÃºblicas", ".....Asistente de ComunicaciÃ³n Interinstitucional/ Relaciones PÃºblicas", ".....Auditor Senior", ".....Auditor", ".....Auditor Junior", "Gerente de InformÃ¡tica y/o de TecnologÃ­a", ".....Encargado de InformÃ¡tica\t", ".....Asistente InformÃ¡tico", ".....Gerente de Analisis y DiseÃ±o de Sistemas", ".....Arquitecto(a) de Sistemas", ".....Analista de Sistemas InformÃ¡ticos", ".....Analista de Sistemas InformÃ¡ticos Junior", ".....Jefe de OrganizaciÃ³n y MÃ©todos", ".....Analista de OrganizaciÃ³n y MÃ©todos", ".....Gerente/ Jefe de Desarrollo de Sistemas", ".....Analista-Programador/ Desarrollador", ".....Programador/ Desarrollador Senior", ".....Programador", ".....Tester o Controlador de Calidad de Software", ".....Programador Junior", ".....TÃ©cnico en ImplementaciÃ³n de Sistemas InformÃ¡ticos", ".....Gerente de Infraestructura", ".....Encargado de Infraestructura/ Mantenimiento (Hardware y/o Software)", ".....Jefe de Procesamiento de Datos u Operaciones", ".....Administrador de Base de Datos", ".....Administrador de Redes/ Servidores", ".....Webmaster", ".....Digitador / Operador de Computador/ Sistemas", ".....Encargado(a)/ Asistente de CodificaciÃ³n", ".....Gerente/ Jefe/ Encargado de Seguridad InformÃ¡tica", ".....Auditor(a) InformÃ¡tico", ".....Gerente/ Jefe/ Encargado(a) de Desarrollo Web", ".....DiseÃ±ador/ Desarrollador Web", ".....DiseÃ±ador/ Desarrollador Web Junior\t", ".....Gerente/ Encargado de Soporte a Usuarios", ".....TÃ©cnico en Soporte a Usuarios / Help Desk", ".....TÃ©cnico en InvestigaciÃ³n y Desarrollo InformÃ¡ticos/ TecnolÃ³gicos", "Gerente de ProducciÃ³n", ".....Supervisor/ Jefe/ Encargado de ProducciÃ³n", ".....Gerente/ Ingeniero de Producto/ InvestigaciÃ³n y Desarrollo", ".....Asistente de InvestigaciÃ³n y Desarrollo", ".....Jefe de Laboratorio industrial/ quÃ­mico/ bioquÃ­mico", ".....TÃ©cnico o Asistente de Laboratorio", ".....Proyectista/ DiseÃ±ista Industrial", ".....Asistente de Proyectista / DiseÃ±ista Industrial\t", ".....Gerente de Planta/ Gerente TÃ©cnico Industrial", ".....Jefe de PlanificaciÃ³n de la ProducciÃ³n", ".....Ingeniero/ TÃ©cnico/ de Procesos Industriales", ".....Asistente de Procesos Industriales", ".....Supervisor/ Jefe de Planta/ Sector", ".....Asistente de Planta/ ProducciÃ³n", ".....Jefe de Turno", ".....Operario de Industria (general) ", ".....Operador de MÃ¡quinas/ Equipamiento ", ".....Jefe/ Encargado de Mantenimiento Industrial (MecÃ¡nico/ ElÃ©ctrico)", ".....TÃ©cnico en Mantenimiento Industrial (MecÃ¡nico/ ElÃ©ctrico)", ".....Gerente/ Encargado de Control de Calidad", ".....TÃ©cnico en Control de Calidad", ".....Asistente de Control de Calidad", ".....TÃ©cnico en Seguridad e Higiene Industrial", "Gerente de Recursos Humanos", ".....Jefe(a)/ Encargado(a) de Recursos Humanos", ".....Auxiliar de Recursos Humanos", ".....Encargado(a) de CapacitaciÃ³n y/o Desarrollo Organizacional", ".....Encargado(a) de Reclutamiento y/o SelecciÃ³n de Personal", ".....Encargado(a) de AdministraciÃ³n del Personal", ".....Liquidador(a) de Sueldos y Jornales", "Gerente de Compras/ Adquisiciones", ".....Jefe o Gerente de Comercio Exterior", ".....Analista o Encargado de Comercio Exterior", ".....Auxiliar Comercio Exterior", ".....Jefe/ Supervisor de Compras o Abastecimiento", ".....Encargado de Compras/ Abastecimiento", ".....Asistente/ Auxiliar de Compras/ Abastecimiento", ".....Jefe/ Supervisor/ Encargado de Licitaciones", ".....Asistente o Auxiliar de Licitaciones", ".....Jefe/ Supervisor/ Encargado de LogÃ­stica/ Despacho/ Transporte", ".....Asistente de LogÃ­stica/ Despacho/Transporte// Empaquetador/ Repartidor", ".....Repositor(a)", ".....Jefe/ Supervisor/ Encargado de DepÃ³sito", ".....Auxiliar de DepÃ³sito", ".....Administrativo Ã�rea Stock/ DepÃ³sito/ LogÃ­stica", "Gerente/ Jefe(a)/ Encargado(a) de Servicios Generales\t260", ".....Asistente Servicios Generales\t1130", ".....Secretario(a) General - Secretario(a) Ejecutivo - Office Manager\t850", ".....Secretario(a)\t3740", ".....Asistente de SecretarÃ­a\t809", ".....AtenciÃ³n al Cliente/ Recepcionista\t3743", ".....Chofer\t669", ".....Cadete u Ordenanza\t570", ".....Chofer-Cobrador\t213", ".....Cobrador(a)\t333", ".....Gestor(a)\t763", ".....Encargado(a) de Limpieza *\t108", ".....Operario de Limpieza *\t138", ".....Supervisor de Call Center\t212", ".....Operador de Call Center / AtenciÃ³n TelefÃ³nica al Cliente/ Telefonista\t4477", ".....Jefe o Auxiliar de Suministros\t85", ".....Encargado(a) de Archivo / Centro de DocumentaciÃ³n/ Biblioteca\t178", ".....Asistente de Archivo / Biblioteca / DocumentaciÃ³n\t276", ".....Coordinador/ Supervisor de Eventos\t210", ".....Asistente de Eventos\t136", ".....Agente de Servicio Post Venta"}));
		cmbPuesto.setBounds(89, 116, 247, 27);
		panel_1.add(cmbPuesto);
		
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

}
