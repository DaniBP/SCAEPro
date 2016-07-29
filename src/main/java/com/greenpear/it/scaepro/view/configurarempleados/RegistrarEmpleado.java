package com.greenpear.it.scaepro.view.configurarempleados;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.DateFormat;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import com.toedter.calendar.JDateChooser;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;

public class RegistrarEmpleado extends JInternalFrame {

	private JPanel contentPane;
	private JTextField txtNombres;
	private JTextField txtApePat;
	private JTextField txtApeMat;
	private JTextField txtTelCel;
	private JTextField txtTelCasa;
	private JTextField txtCalle;
	private JTextField txtNumeroExt;
	private JTextField txtNumeroInt;
	private JButton btnCapturarFoto;
	private JButton btnLeerHuella;
	private JButton btnRegistrar;
	private JComboBox cmbArea;
	private JComboBox cmbPeriodoNominal;
	private JComboBox cmbDiaNomina;
	private JComboBox cmbTurno;
	private JComboBox cmbColonia;
	private JComboBox cmbPuesto;
	private JDateChooser dtfechaNac;
	private JTextField txtCp;
	private JTextField txtEstado;
	private JTextField txtMunicipio;
	private JLabel lblFotografia;
	private JLabel lblHuellaDigital; 
	
	public RegistrarEmpleado() {
		setFrameIcon(new ImageIcon(RegistrarEmpleado.class.getResource("/img/Logo1.png")));
		setIconifiable(true);
		setClosable(true);
		setTitle("Registrar empleado");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 970, 580);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);

		JPanel pnlDatosPersonales = new JPanel();
		pnlDatosPersonales.setBackground(SystemColor.activeCaption);
		pnlDatosPersonales.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Datos personales:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlDatosPersonales.setBounds(10, 10, 360, 307);
		contentPane.add(pnlDatosPersonales);
		pnlDatosPersonales.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre (s):");
		lblNombre.setBounds(16, 24, 120, 16);
		pnlDatosPersonales.add(lblNombre);

		JLabel lblApellidoPaterno = new JLabel("Apellido paterno:");
		lblApellidoPaterno.setBounds(16, 69, 120, 16);
		pnlDatosPersonales.add(lblApellidoPaterno);

		JLabel lblApellidoManterno = new JLabel("Apellido materno:");
		lblApellidoManterno.setBounds(16, 119, 120, 16);
		pnlDatosPersonales.add(lblApellidoManterno);

		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento:");
		lblFechaDeNacimiento.setBounds(16, 169, 140, 16);
		pnlDatosPersonales.add(lblFechaDeNacimiento);

		JLabel lblTelefonoPersonal = new JLabel("Telefono personal (Celular):");
		lblTelefonoPersonal.setBounds(16, 219, 180, 16);
		pnlDatosPersonales.add(lblTelefonoPersonal);

		JLabel lblTelefonoPersonalcasa = new JLabel("Telefono personal (Casa):");
		lblTelefonoPersonalcasa.setBounds(16, 269, 180, 16);
		pnlDatosPersonales.add(lblTelefonoPersonalcasa);

		txtNombres = new JTextField();
		txtNombres.setBounds(129, 19, 212, 26);
		pnlDatosPersonales.add(txtNombres);
		txtNombres.setColumns(10);

		txtApePat = new JTextField();
		txtApePat.setColumns(10);
		txtApePat.setBounds(129, 66, 212, 26);
		pnlDatosPersonales.add(txtApePat);

		txtApeMat = new JTextField();
		txtApeMat.setColumns(10);
		txtApeMat.setBounds(129, 114, 212, 26);
		pnlDatosPersonales.add(txtApeMat);

		txtTelCel = new JTextField();
		txtTelCel.setBounds(196, 214, 145, 26);
		pnlDatosPersonales.add(txtTelCel);
		txtTelCel.setColumns(10);

		txtTelCasa = new JTextField();
		txtTelCasa.setColumns(10);
		txtTelCasa.setBounds(196, 264, 145, 26);
		pnlDatosPersonales.add(txtTelCasa);

		dtfechaNac = new JDateChooser();
		dtfechaNac.setBounds(196, 159, 145, 26);
		pnlDatosPersonales.add(dtfechaNac);

		JPanel pnlDireccion = new JPanel();
		pnlDireccion.setBackground(SystemColor.activeCaption);
		pnlDireccion.setBorder(
				new TitledBorder(null, "Direcci\u00F3n:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlDireccion.setBounds(381, 10, 360, 307);
		contentPane.add(pnlDireccion);
		pnlDireccion.setLayout(null);

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(18, 70, 80, 16);
		pnlDireccion.add(lblEstado);

		JLabel lblMunicipio = new JLabel("Municipio:");
		lblMunicipio.setBounds(18, 115, 80, 16);
		pnlDireccion.add(lblMunicipio);

		JLabel lblCalle = new JLabel("Calle:");
		lblCalle.setBounds(18, 265, 80, 16);
		pnlDireccion.add(lblCalle);

		JLabel lblNumeroExt = new JLabel("Numero Ext:");
		lblNumeroExt.setBounds(18, 215, 87, 16);
		pnlDireccion.add(lblNumeroExt);

		JLabel lblColonia = new JLabel("Colonia:");
		lblColonia.setBounds(18, 165, 80, 16);
		pnlDireccion.add(lblColonia);

		JLabel lblNumeroInt = new JLabel("Numero Int:");
		lblNumeroInt.setBounds(181, 215, 87, 16);
		pnlDireccion.add(lblNumeroInt);

		txtCalle = new JTextField();
		txtCalle.setColumns(10);
		txtCalle.setBounds(94, 260, 243, 26);
		pnlDireccion.add(txtCalle);

		txtNumeroExt = new JTextField();
		txtNumeroExt.setBounds(97, 210, 72, 26);
		pnlDireccion.add(txtNumeroExt);
		txtNumeroExt.setColumns(10);

		txtNumeroInt = new JTextField();
		txtNumeroInt.setColumns(10);
		txtNumeroInt.setBounds(265, 210, 72, 26);
		pnlDireccion.add(txtNumeroInt);

		JLabel label = new JLabel("C.P:");
		label.setBounds(18, 33, 80, 16);
		pnlDireccion.add(label);

		txtCp = new JTextField();
		txtCp.setColumns(10);
		txtCp.setBounds(94, 28, 243, 26);
		pnlDireccion.add(txtCp);

		txtEstado = new JTextField();
		txtEstado.setEnabled(false);
		txtEstado.setBounds(94, 65, 243, 26);
		pnlDireccion.add(txtEstado);
		txtEstado.setColumns(10);

		txtMunicipio = new JTextField();
		txtMunicipio.setEnabled(false);
		txtMunicipio.setColumns(10);
		txtMunicipio.setBounds(94, 110, 243, 26);
		pnlDireccion.add(txtMunicipio);

		cmbColonia = new JComboBox();
		cmbColonia.setModel(new DefaultComboBoxModel(new String[] { "---Seleccione su colonia---" }));
		cmbColonia.setEnabled(false);
		cmbColonia.setBounds(94, 161, 243, 26);
		pnlDireccion.add(cmbColonia);

		JPanel pnlNipFotografia = new JPanel();
		pnlNipFotografia.setBackground(SystemColor.activeCaption);
		pnlNipFotografia.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Nip y fotograf\u00EDa", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlNipFotografia.setBounds(749, 10, 215, 458);
		contentPane.add(pnlNipFotografia);
		pnlNipFotografia.setLayout(null);

		lblFotografia = new JLabel("");
		lblFotografia.setBounds(20, 22, 170, 162);
		pnlNipFotografia.add(lblFotografia);

		lblHuellaDigital = new JLabel("");
		lblHuellaDigital.setBounds(51, 228, 120, 170);
		pnlNipFotografia.add(lblHuellaDigital);

		btnLeerHuella = new JButton("Detectar huella");
		btnLeerHuella.setBounds(35, 410, 140, 30);
		pnlNipFotografia.add(btnLeerHuella);

		btnCapturarFoto = new JButton("Capturar foto");
		btnCapturarFoto.setBounds(35, 186, 140, 30);
		pnlNipFotografia.add(btnCapturarFoto);

		JPanel pnlAsignacion = new JPanel();
		pnlAsignacion.setBackground(SystemColor.activeCaption);
		pnlAsignacion.setBorder(
				new TitledBorder(null, "Asignaci\u00F3n:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlAsignacion.setBounds(10, 334, 360, 201);
		contentPane.add(pnlAsignacion);
		pnlAsignacion.setLayout(null);

		cmbArea = new JComboBox();
		cmbArea.setModel(new DefaultComboBoxModel(new String[] {"--------Seleccione un \u00E1rea--------"}));
		cmbArea.setBounds(20, 101, 311, 27);
		pnlAsignacion.add(cmbArea);

		cmbTurno = new JComboBox();
		cmbTurno.setEnabled(false);
		cmbTurno.setModel(new DefaultComboBoxModel(new String[] {"--------Seleccione un turno--------", "a"}));
		cmbTurno.setBounds(20, 154, 311, 27);
		pnlAsignacion.add(cmbTurno);
		
		cmbPuesto = new JComboBox();
		cmbPuesto.setModel(new DefaultComboBoxModel(new String[] {"-----Seleccione puesto-----", "CEO", "Socio empresarial", "Gerente Comercial", ".....Gerente de Ventas", ".....Sub Gerente Comercial o de Ventas", ".....Jefe/ Supervisor de Ventas", ".....Vendedor(a)/ Representante Comercial", ".....Vendedor(a) de Salon", ".....Promotor(a)", ".....Administrativo Area Ventas", ".....Asistente Comercial", ".....Preventista", ".....Encargado/ Asistente de Escrituraciones", ".....Gerente Departamental/ Sucursal/ Unidad de Negocios", ".....Comercial de Unidad de Negocios", ".....Encargado(a) de Local", ".....Analista de Ventas", ".....Ejecutivo/ Oficial/ Asesor de Ventas/ Cuentas/ Creditos", ".....Ejecutivo/ Oficial/ Asesor de Ventas/ Cuentas/ Creditos Junior", ".....Desarrollador de Negocios (Business Developer)", ".....Gerente de Marketing", ".....Gerente Marketing por Unidad de Negocios", ".....Encargado/ Jefe/ Supervisor(a) de Marketing", ".....Ejecutivo(a) de Marketing", ".....Gerente/ Jefe de Producto\t", ".....Investigador de Mercado", ".....Analista de Trade Marketing", ".....Analista/ Asesor de Marketing", ".....Merchandiser - Encargado(a) de Stock Comercio Minorista", ".....Analista/ Asesor de Marketing Junior", ".....Asistente de Marketing", ".....Community Manager", ".....Brand Manager", ".....Channel Manager / Jefe o Encargado de Canal", ".....Supervisor(a) de Telemarketing\t", ".....Telemarketero(a)", "Director/ Gerente de Administracion y Finanzas", ".....Gerente Administrativo y/o de Operaciones", ".....Jefe/ Encargado/ Supervisor de Operaciones", ".....Jefe/ Supervisor/ Encargado Administrativo", ".....Auxiliar/ Secretario(a) Administrativo(a)/ de Operaciones\t", ".....Analista de Control de Gestion/ Auditor de Operaciones/ Controller", ".....Contador General / Gerente de Contabilidad", ".....Jefe/ Supervisor/ Encargado/ Analista de Contabilidad", ".....Auxiliar Contable", ".....Analista/ Encargado(a) de Impuestos\t", ".....Asistente/ Auxiliar de Impuestos\t", ".....Analista/ Asistente de Costos/ Presupuestos", ".....Encargado(a) de Cuentas a Pagar / Pagos a Proveedores", ".....Encargado(a)/ Auxiliar de Patrimonio", ".....Gerente de Finanzas y/o de Creditos", ".....Supervisor(a)/ Jefe(a)/ Coordinador(a) de Finanzas/ Creditos", ".....Analista/ Asesor de Finanzas/ Microfinanzas", ".....Auxiliar de Finanzas/ Analista de Finanzas Junior", ".....Encargado(a) de Facturacion y/o Cuentas Corrientes", ".....Auxiliar de Facturacion y/o Cuentas Corrientes", ".....Encargado(a) de Creditos y/o Cobranzas", ".....Auxiliar de Creditos y/o Cobranzas", ".....Analista de Creditos/ Riesgos Crediticios/ Operacionales", ".....Analista de Creditos/ Riesgos Crediticios Junior", ".....Tesorero(a)", ".....Asistente de Tesorera", ".....Supervisor de Cajas", ".....Cajero(a)", ".....Recontador de billetes", "Director/ Gerente General/ ", ".....Sub Gerente General", ".....Gerente Propietario", ".....Gerente/ Jefe de Planificacion", ".....Analista de Planificacion", ".....Asistente de Planificacion", ".....Secretaria(o) de Gerencia/ Directorio", ".....Gerente/ Jefe de Comunicacion Interinstitucional/ Relaciones Publicas", ".....Asistente de Comunicacion Interinstitucional/ Relaciones Publicas", ".....Auditor Senior", ".....Auditor", ".....Auditor Junior", "Gerente de Informatica y/o de Tecnologia", ".....Encargado de Informatica\t", ".....Asistente Informatico", ".....Gerente de Analisis y Diseeño de Sistemas", ".....Arquitecto(a) de Sistemas", ".....Analista de Sistemas Informaticos", ".....Analista de Sistemas Informaticos Junior", ".....Jefe de Organizacion y Metodos", ".....Analista de Organizacion y Metodos", ".....Gerente/ Jefe de Desarrollo de Sistemas", ".....Analista-Programador/ Desarrollador", ".....Programador/ Desarrollador Senior", ".....Programador", ".....Tester o Controlador de Calidad de Software", ".....Programador Junior", ".....Tecnico en Implementacion de Sistemas Informaticos", ".....Gerente de Infraestructura", ".....Encargado de Infraestructura/ Mantenimiento (Hardware y/o Software)", ".....Jefe de Procesamiento de Datos u Operaciones", ".....Administrador de Base de Datos", ".....Administrador de Redes/ Servidores", ".....Webmaster", ".....Digitador / Operador de Computador/ Sistemas", ".....Encargado(a)/ Asistente de Codificacion", ".....Gerente/ Jefe/ Encargado de Seguridad Informatica", ".....Auditor(a) Informatico", ".....Gerente/ Jefe/ Encargado(a) de Desarrollo Web", ".....Diseño/ Desarrollador Web", ".....Diseño/ Desarrollador Web Junior\t", ".....Gerente/ Encargado de Soporte a Usuarios", ".....Tecnico en Soporte a Usuarios / Help Desk", ".....Tecnico en Investigacion y Desarrollo Informaticos/ Tecnologicos", "Gerente de Produccion", ".....Supervisor/ Jefe/ Encargado de Produccion", ".....Gerente/ Ingeniero de Producto/ Investigacion y Desarrollo", ".....Asistente de Investigacion y Desarrollo", ".....Jefe de Laboratorio industrial/ quimico/ bioquimico", ".....Tecnico o Asistente de Laboratorio", ".....Proyectista/ Diseño Industrial", ".....Asistente de Proyectista / Diseño Industrial\t", ".....Gerente de Planta/ Gerente Tecnico Industrial", ".....Jefe de Planificacion de la Produccion", ".....Ingeniero/ Tecnico/ de Procesos Industriales", ".....Asistente de Procesos Industriales", ".....Supervisor/ Jefe de Planta/ Sector", ".....Asistente de Planta/ Produccion", ".....Jefe de Turno", ".....Operario de Industria (general) ", ".....Operador de Maquinas/ Equipamiento ", ".....Jefe/ Encargado de Mantenimiento Industrial (Mecanico/ Electrico)", ".....Tecnico en Mantenimiento Industrial (Mecanico/ Electrico)", ".....Gerente/ Encargado de Control de Calidad", ".....Tecnico en Control de Calidad", ".....Asistente de Control de Calidad", ".....Tecnico en Seguridad e Higiene Industrial", "Gerente de Recursos Humanos", ".....Jefe(a)/ Encargado(a) de Recursos Humanos", ".....Auxiliar de Recursos Humanos", ".....Encargado(a) de Capacitacion y/o Desarrollo Organizacional", ".....Encargado(a) de Reclutamiento y/o Seleccion de Personal", ".....Encargado(a) de Administracion del Personal", ".....Liquidador(a) de Sueldos y Jornales", "Gerente de Compras/ Adquisiciones", ".....Jefe o Gerente de Comercio Exterior", ".....Analista o Encargado de Comercio Exterior", ".....Auxiliar Comercio Exterior", ".....Jefe/ Supervisor de Compras o Abastecimiento", ".....Encargado de Compras/ Abastecimiento", ".....Asistente/ Auxiliar de Compras/ Abastecimiento", ".....Jefe/ Supervisor/ Encargado de Licitaciones", ".....Asistente o Auxiliar de Licitaciones", ".....Jefe/ Supervisor/ Encargado de Logistica/ Despacho/ Transporte", ".....Asistente de Logistica/ Despacho/Transporte// Empaquetador/ Repartidor", ".....Repositor(a)", ".....Jefe/ Supervisor/ Encargado de Deposito", ".....Auxiliar de Deposito", ".....Administrativo area Stock/ Deposito/ Logistica", "Gerente/ Jefe(a)/ Encargado(a) de Servicios Generales", ".....Asistente Servicios Generales\t", ".....Secretario(a) General - Secretario(a) Ejecutivo - Office Manager", ".....Secretario(a)", ".....Asistente de Secretaria", ".....Atencion al Cliente/ Recepcionista", ".....Chofer", ".....Cadete u Ordenanza", ".....Chofer-Cobrador", ".....Cobrador(a)", ".....Gestor(a)", ".....Encargado(a) de Limpieza ", ".....Operario de Limpieza ", ".....Supervisor de Call Center", ".....Operador de Call Center / Atencion Telefonica al Cliente/ Telefonista\t", ".....Jefe o Auxiliar de Suministros", ".....Encargado(a) de Archivo / Centro de Documentacion/ Biblioteca", ".....Asistente de Archivo / Biblioteca / Documentacion", ".....Coordinador/ Supervisor de Eventos", ".....Asistente de Eventos", ".....Agente de Servicio Post Venta"}));
		cmbPuesto.setBounds(20, 50, 311, 27);
		pnlAsignacion.add(cmbPuesto);

		JPanel pnlNomina = new JPanel();
		pnlNomina.setBackground(SystemColor.activeCaption);
		pnlNomina.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Nomina:",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlNomina.setBounds(381, 334, 360, 201);
		contentPane.add(pnlNomina);
		pnlNomina.setLayout(null);

		cmbPeriodoNominal = new JComboBox();
		cmbPeriodoNominal.setModel(new DefaultComboBoxModel(new String[] {"----Selecciona un periodo nominal----", "Semanal", "Quincenal", "Mensual"}));
		cmbPeriodoNominal.setBounds(22, 61, 311, 27);
		pnlNomina.add(cmbPeriodoNominal);

		cmbDiaNomina = new JComboBox();
		cmbDiaNomina.setEnabled(false);
		cmbDiaNomina.setModel(new DefaultComboBoxModel(new String[] {"---------Selecciona un dia---------"}));
		cmbDiaNomina.setBounds(22, 124, 311, 27);
		pnlNomina.add(cmbDiaNomina);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(790, 480, 140, 30);
		contentPane.add(btnRegistrar);
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public JTextField getTxtNombres() {
		return txtNombres;
	}
	
	public void setTxtNombres(JTextField txtNombres) {
		this.txtNombres = txtNombres;
	}

	public void setTxtApePat(JTextField txtApePat) {
		this.txtApePat = txtApePat;
	}

	public void setTxtApeMat(JTextField txtApeMat) {
		this.txtApeMat = txtApeMat;
	}

	public void setTxtTelCel(JTextField txtTelCel) {
		this.txtTelCel = txtTelCel;
	}

	public void setTxtTelCasa(JTextField txtTelCasa) {
		this.txtTelCasa = txtTelCasa;
	}

	public void setTxtCalle(JTextField txtCalle) {
		this.txtCalle = txtCalle;
	}

	public void setTxtNumeroExt(JTextField txtNumeroExt) {
		this.txtNumeroExt = txtNumeroExt;
	}

	public void setTxtNumeroInt(JTextField txtNumeroInt) {
		this.txtNumeroInt = txtNumeroInt;
	}

	public void setCmbArea(JComboBox cmbArea) {
		this.cmbArea = cmbArea;
	}

	public void setCmbPeriodoNominal(JComboBox cmbPeriodoNominal) {
		this.cmbPeriodoNominal = cmbPeriodoNominal;
	}

	public void setCmbDiaNomina(JComboBox cmbDiaNomina) {
		this.cmbDiaNomina = cmbDiaNomina;
	}

	public void setCmbTurno(JComboBox cmbTurno) {
		this.cmbTurno = cmbTurno;
	}

	public void setLblFotografia(JLabel lblFotografia) {
		this.lblFotografia = lblFotografia;
	}

	public void setLblHuellaDigital(JLabel lblHuellaDigital) {
		this.lblHuellaDigital = lblHuellaDigital;
	}

	public JTextField getTxtApePat() {
		return txtApePat;
	}

	public JTextField getTxtApeMat() {
		return txtApeMat;
	}

	public JTextField getTxtTelCel() {
		return txtTelCel;
	}

	public JTextField getTxtTelCasa() {
		return txtTelCasa;
	}

	public JTextField getTxtCalle() {
		return txtCalle;
	}

	public JTextField getTxtNumeroExt() {
		return txtNumeroExt;
	}

	public JTextField getTxtNumeroInt() {
		return txtNumeroInt;
	}

	public JButton getBtnCapturarFoto() {
		return btnCapturarFoto;
	}

	public JButton getBtnLeerHuella() {
		return btnLeerHuella;
	}

	public JButton getBtnRegistrar() {
		return btnRegistrar;
	}

	public JComboBox getCmbArea() {
		return cmbArea;
	}

	public JComboBox getCmbPeriodoNominal() {
		return cmbPeriodoNominal;
	}

	public JComboBox getCmbDiaNomina() {
		return cmbDiaNomina;
	}

	public JDateChooser getFecha() {
		return dtfechaNac;
	}

	public JComboBox getCmbTurno() {
		return cmbTurno;
	}

	public JTextField getTxtCp() {
		return txtCp;
	}

	public void setTxtCp(JTextField txtCp) {
		this.txtCp = txtCp;
	}

	public JTextField getTxtEstado() {
		return txtEstado;
	}

	public void setTxtEstado(JTextField txtEstado) {
		this.txtEstado = txtEstado;
	}

	public JTextField getTxtMunicipio() {
		return txtMunicipio;
	}

	public void setTxtMunicipio(JTextField txtMunicipio) {
		this.txtMunicipio = txtMunicipio;
	}

	public JComboBox getCmbColonia() {
		return cmbColonia;
	}

	public void setCmbColonia(JComboBox cmbColonia) {
		this.cmbColonia = cmbColonia;
	}

	public JComboBox getCmbPuesto() {
		return cmbPuesto;
	}

	public void setCmbPuesto(JComboBox cmbPuesto) {
		this.cmbPuesto = cmbPuesto;
	}

	public JLabel getLblFotografia() {
		return lblFotografia;
	}

	public JLabel getLblHuellaDigital() {
		return lblHuellaDigital;
	}
}