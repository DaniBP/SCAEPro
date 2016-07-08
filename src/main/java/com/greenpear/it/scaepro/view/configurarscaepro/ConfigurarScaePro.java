package com.greenpear.it.scaepro.view.configurarscaepro;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Formatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ConfigurarScaePro extends JFrame {
	private JTextField txtMinutosRetardo;
	private JTextField txtMinutosFalta;
	private JTextField txtHoraEntradG;
	private JTextField txtHoraSalidaG;
	private JTextField txtHoraSalidaComidaG;
	private JTextField txtHoraEntradaComidaG;
	private JTextField txtNombreTurno;
	private JComboBox cmbArea;
	private JCheckBox chckbxLunes;
	private JCheckBox chkMartes;
	private JCheckBox chkMiercoles;
	private JCheckBox chkJueves;
	private JCheckBox chkViernes;
	private JCheckBox chkComidaDomingo;
	private JCheckBox chkComidaSabado;
	private JCheckBox chkComidaViernes;
	private JCheckBox chkComidaJueves;
	private JCheckBox chkComidaMiercoles;
	private JCheckBox chkComidaMartes;
	private JCheckBox chkComidaLunes;
	private JCheckBox chkSabado;
	private JCheckBox chkDomingo;
	private JSpinner spnHEntradaLunes;
	private JSpinner spnHSalidaLunes;
	private JSpinner spnHSalidaComidaLunes;
	private JSpinner spnHEntradaComidaLunes;
	private JSpinner spnHSalidaMartes;
	private JSpinner spnHSalidaComidaMartes;
	private JSpinner spnHEntradaComidaMartes;
	private JSpinner spnHEntradaMiercoles;
	private JSpinner spnHSalidaMiercoles;
	private JSpinner spnHSalidaComidaMiercoles;
	private JSpinner spnHEntradaComidaMiercoles;
	private JSpinner spnHEntradaJueves;
	private JSpinner spnHSalidaJueves;
	private JSpinner spnHSalidaComidaJueves;
	private JSpinner spnHEntradaComidaJueves;
	private JSpinner spnHEntradaComidaViernes;
	private JSpinner spnHSalidaComidaViernes;
	private JSpinner spnHSalidaViernes;
	private JSpinner spnHEntradaViernes;
	private JSpinner spnHEntradaSabado;
	private JSpinner spnHSalidaSabado;
	private JSpinner spnHSalidaComidaSabado;
	private JSpinner spnHEntradaComidaSabado;
	private JSpinner spnHEntradaDomingo;
	private JSpinner spnHSalidaDomingo;
	private JSpinner spnHSalidaComidaDomingo;
	private JSpinner spnHEntradaComidaDomingo;
	private JSpinner spnHEntradaMartes;
	private JTabbedPane tabbedPane;
	private JButton btnNuevoTurno;
	private JButton btnGuardarEditar;
	private JButton btnEliminar;
	private JCheckBox chkHorarioGeneral;
	
	public ConfigurarScaePro() {
		getContentPane().setLayout(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 570);
		setLocationRelativeTo(null);
		super.setTitle("Configurar Scae Pro");
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Configuracion del area", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 10, 953, 130);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblArea = new JLabel("Area de la empresa:");
		lblArea.setBounds(15, 40, 190, 16);
		panel.add(lblArea);
		
		JLabel lblMinRetardos = new JLabel("Minutos tolerancia (Retardo):");
		lblMinRetardos.setEnabled(false);
		lblMinRetardos.setBounds(15, 70, 190, 16);
		panel.add(lblMinRetardos);
		
		JLabel lblMinutosFaltas = new JLabel("Minutos tolerancia (Falta):");
		lblMinutosFaltas.setEnabled(false);
		lblMinutosFaltas.setBounds(15, 100, 190, 16);
		panel.add(lblMinutosFaltas);
		
		txtMinutosRetardo = new JTextField();
		txtMinutosRetardo.setEnabled(false);
		txtMinutosRetardo.setBounds(190, 65, 130, 26);
		panel.add(txtMinutosRetardo);
		txtMinutosRetardo.setColumns(10);
		
		txtMinutosFalta = new JTextField();
		txtMinutosFalta.setEnabled(false);
		txtMinutosFalta.setColumns(10);
		txtMinutosFalta.setBounds(190, 95, 130, 26);
		panel.add(txtMinutosFalta);
		
		JLabel lblHoraEntradaG = new JLabel("Hora de entrada:");
		lblHoraEntradaG.setEnabled(false);
		lblHoraEntradaG.setBounds(355, 70, 147, 16);
		panel.add(lblHoraEntradaG);
		
		JLabel lblHoraDeSalidaG = new JLabel("Hora de salida:");
		lblHoraDeSalidaG.setEnabled(false);
		lblHoraDeSalidaG.setBounds(355, 100, 147, 16);
		panel.add(lblHoraDeSalidaG);
		
		txtHoraEntradG = new JTextField();
		txtHoraEntradG.setEnabled(false);
		txtHoraEntradG.setColumns(10);
		txtHoraEntradG.setBounds(453, 65, 130, 26);
		panel.add(txtHoraEntradG);
		
		txtHoraSalidaG = new JTextField();
		txtHoraSalidaG.setEnabled(false);
		txtHoraSalidaG.setColumns(10);
		txtHoraSalidaG.setBounds(453, 95, 130, 26);
		panel.add(txtHoraSalidaG);
		
		JLabel lblHoraSalidaComidaG = new JLabel("Hora de salida (Comida):");
		lblHoraSalidaComidaG.setEnabled(false);
		lblHoraSalidaComidaG.setBounds(618, 70, 165, 16);
		panel.add(lblHoraSalidaComidaG);
		
		JLabel lblHoraDeEntradaComidaG = new JLabel("Hora de entrada (Comida):");
		lblHoraDeEntradaComidaG.setEnabled(false);
		lblHoraDeEntradaComidaG.setBounds(618, 100, 165, 16);
		panel.add(lblHoraDeEntradaComidaG);
		
		txtHoraSalidaComidaG = new JTextField();
		txtHoraSalidaComidaG.setEnabled(false);
		txtHoraSalidaComidaG.setColumns(10);
		txtHoraSalidaComidaG.setBounds(770, 65, 130, 26);
		panel.add(txtHoraSalidaComidaG);
		
		txtHoraEntradaComidaG = new JTextField();
		txtHoraEntradaComidaG.setEnabled(false);
		txtHoraEntradaComidaG.setColumns(10);
		txtHoraEntradaComidaG.setBounds(770, 95, 130, 26);
		panel.add(txtHoraEntradaComidaG);
		
		cmbArea = new JComboBox();
		cmbArea.setModel(new DefaultComboBoxModel(new String[] {"---------------Seleccione un \u00E1rea---------------"}));
		cmbArea.setBounds(159, 35, 265, 27);
		panel.add(cmbArea);
		
		chkHorarioGeneral = new JCheckBox("Aplicar un horario para el area en general");
		chkHorarioGeneral.setEnabled(false);
		chkHorarioGeneral.setBounds(503, 35, 300, 23);
		panel.add(chkHorarioGeneral);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new TitledBorder(null, "Configuracion de horarios:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tabbedPane.setBounds(10, 141, 788, 401);
		getContentPane().add(tabbedPane);
		
		JPanel tjpnlHorarios = new JPanel();
		tabbedPane.addTab("Turno 1", null, tjpnlHorarios, null);
		tjpnlHorarios.setLayout(null);
		
		JLabel lblNombreTurno = new JLabel("Nombre del turno:");
		lblNombreTurno.setBounds(20, 21, 120, 16);
		tjpnlHorarios.add(lblNombreTurno);
		
		txtNombreTurno = new JTextField();
		txtNombreTurno.setEnabled(false);
		txtNombreTurno.setBounds(146, 16, 286, 26);
		tjpnlHorarios.add(txtNombreTurno);
		txtNombreTurno.setColumns(10);
		
		chckbxLunes = new JCheckBox("Lunes");
		chckbxLunes.setEnabled(false);
		chckbxLunes.setBounds(20, 60, 80, 23);
		tjpnlHorarios.add(chckbxLunes);
		
		JLabel lblHEntradaLunes = new JLabel("H.Entrada:");
		lblHEntradaLunes.setBounds(20, 90, 80, 16);
		tjpnlHorarios.add(lblHEntradaLunes);
		
		JLabel lblHSalidaLunes = new JLabel("H.Salida:");
		lblHSalidaLunes.setBounds(20, 140, 80, 16);
		tjpnlHorarios.add(lblHSalidaLunes);
		
		chkComidaLunes = new JCheckBox("Comida");
		chkComidaLunes.setEnabled(false);
		chkComidaLunes.setBounds(20, 190, 80, 23);
		tjpnlHorarios.add(chkComidaLunes);
		
		JLabel lblHoraSalidaComerLunes = new JLabel("H.Salida:");
		lblHoraSalidaComerLunes.setBounds(20, 220, 80, 16);
		tjpnlHorarios.add(lblHoraSalidaComerLunes);
		
		JLabel lblHEntradaComidaLunes = new JLabel("H.Entrada:");
		lblHEntradaComidaLunes.setBounds(20, 270, 80, 16);
		tjpnlHorarios.add(lblHEntradaComidaLunes);
		
		chkMartes = new JCheckBox("Martes");
		chkMartes.setEnabled(false);
		chkMartes.setBounds(122, 60, 80, 23);
		tjpnlHorarios.add(chkMartes);
		
		JLabel lblHEntradaMartes = new JLabel("H.Entrada:");
		lblHEntradaMartes.setBounds(122, 90, 80, 16);
		tjpnlHorarios.add(lblHEntradaMartes);
		
		JLabel lblHSalidaMartes = new JLabel("H.Salida:");
		lblHSalidaMartes.setBounds(122, 140, 80, 16);
		tjpnlHorarios.add(lblHSalidaMartes);
		
		chkComidaMartes = new JCheckBox("Comida");
		chkComidaMartes.setEnabled(false);
		chkComidaMartes.setBounds(122, 190, 80, 23);
		tjpnlHorarios.add(chkComidaMartes);
		
		JLabel lblHoraSalidaComerMartes = new JLabel("H.Salida:");
		lblHoraSalidaComerMartes.setBounds(122, 220, 80, 16);
		tjpnlHorarios.add(lblHoraSalidaComerMartes);
		
		JLabel lblHEntradaComidaMartes = new JLabel("H.Entrada:");
		lblHEntradaComidaMartes.setBounds(122, 270, 80, 16);
		tjpnlHorarios.add(lblHEntradaComidaMartes);
		
		chkMiercoles = new JCheckBox("Miercoles");
		chkMiercoles.setEnabled(false);
		chkMiercoles.setBounds(225, 60, 100, 23);
		tjpnlHorarios.add(chkMiercoles);
		
		JLabel lblHEntradaMiercoles = new JLabel("H.Entrada:");
		lblHEntradaMiercoles.setBounds(225, 90, 80, 16);
		tjpnlHorarios.add(lblHEntradaMiercoles);
		
		JLabel lblHSalidaMiercoles = new JLabel("H.Salida:");
		lblHSalidaMiercoles.setBounds(225, 140, 80, 16);
		tjpnlHorarios.add(lblHSalidaMiercoles);
		
		chkComidaMiercoles = new JCheckBox("Comida");
		chkComidaMiercoles.setEnabled(false);
		chkComidaMiercoles.setBounds(225, 190, 80, 23);
		tjpnlHorarios.add(chkComidaMiercoles);
		
		JLabel lblHoraSalidaComerMiercoles = new JLabel("H.Salida:");
		lblHoraSalidaComerMiercoles.setBounds(225, 220, 80, 16);
		tjpnlHorarios.add(lblHoraSalidaComerMiercoles);
		
		JLabel lblHEntradaComidaMiercoles = new JLabel("H.Entrada:");
		lblHEntradaComidaMiercoles.setBounds(225, 270, 80, 16);
		tjpnlHorarios.add(lblHEntradaComidaMiercoles);
		
		chkJueves = new JCheckBox("Jueves");
		chkJueves.setEnabled(false);
		chkJueves.setBounds(330, 60, 80, 23);
		tjpnlHorarios.add(chkJueves);
		
		JLabel lblHEntradaJueves = new JLabel("H.Entrada:");
		lblHEntradaJueves.setBounds(330, 90, 80, 16);
		tjpnlHorarios.add(lblHEntradaJueves);
		
		JLabel lblHSalidaJueves = new JLabel("H.Salida:");
		lblHSalidaJueves.setBounds(330, 140, 80, 16);
		tjpnlHorarios.add(lblHSalidaJueves);
		
		chkComidaJueves = new JCheckBox("Comida");
		chkComidaJueves.setEnabled(false);
		chkComidaJueves.setBounds(330, 190, 80, 23);
		tjpnlHorarios.add(chkComidaJueves);
		
		JLabel lblHoraSalidaComerJueves = new JLabel("H.Salida:");
		lblHoraSalidaComerJueves.setBounds(330, 220, 80, 16);
		tjpnlHorarios.add(lblHoraSalidaComerJueves);
		
		JLabel lblHEntradaComidaJueves = new JLabel("H.Entrada:");
		lblHEntradaComidaJueves.setBounds(330, 270, 80, 16);
		tjpnlHorarios.add(lblHEntradaComidaJueves);
		
		chkViernes = new JCheckBox("Viernes");
		chkViernes.setEnabled(false);
		chkViernes.setBounds(438, 60, 80, 23);
		tjpnlHorarios.add(chkViernes);
		
		JLabel lblHEntradaViernes = new JLabel("H.Entrada:");
		lblHEntradaViernes.setBounds(438, 90, 80, 16);
		tjpnlHorarios.add(lblHEntradaViernes);
		
		JLabel lblHSalidaViernes = new JLabel("H.Salida:");
		lblHSalidaViernes.setBounds(438, 140, 80, 16);
		tjpnlHorarios.add(lblHSalidaViernes);
		
		chkComidaViernes = new JCheckBox("Comida");
		chkComidaViernes.setEnabled(false);
		chkComidaViernes.setBounds(438, 190, 80, 23);
		tjpnlHorarios.add(chkComidaViernes);
		
		JLabel lblHoraSalidaComerViernes = new JLabel("H.Salida:");
		lblHoraSalidaComerViernes.setBounds(438, 220, 80, 16);
		tjpnlHorarios.add(lblHoraSalidaComerViernes);
		
		JLabel lblHEntradaComidaViernes = new JLabel("H.Entrada:");
		lblHEntradaComidaViernes.setBounds(438, 270, 80, 16);
		tjpnlHorarios.add(lblHEntradaComidaViernes);
		
		chkSabado = new JCheckBox("Sabado");
		chkSabado.setEnabled(false);
		chkSabado.setBounds(542, 60, 80, 23);
		tjpnlHorarios.add(chkSabado);
		
		JLabel lblHEntradaSabado = new JLabel("H.Entrada:");
		lblHEntradaSabado.setBounds(542, 90, 80, 16);
		tjpnlHorarios.add(lblHEntradaSabado);
		
		JLabel lblHSalidaSabado = new JLabel("H.Salida:");
		lblHSalidaSabado.setBounds(542, 140, 80, 16);
		tjpnlHorarios.add(lblHSalidaSabado);
		
		chkComidaSabado = new JCheckBox("Comida");
		chkComidaSabado.setEnabled(false);
		chkComidaSabado.setBounds(542, 190, 80, 23);
		tjpnlHorarios.add(chkComidaSabado);
		
		JLabel lblHoraSalidaComerSabado = new JLabel("H.Salida:");
		lblHoraSalidaComerSabado.setBounds(542, 220, 80, 16);
		tjpnlHorarios.add(lblHoraSalidaComerSabado);
		
		JLabel lblHEntradaComidaSabado = new JLabel("H.Entrada:");
		lblHEntradaComidaSabado.setBounds(542, 270, 80, 16);
		tjpnlHorarios.add(lblHEntradaComidaSabado);
		
		chkDomingo = new JCheckBox("Domingo");
		chkDomingo.setEnabled(false);
		chkDomingo.setBounds(645, 60, 90, 23);
		tjpnlHorarios.add(chkDomingo);
		
		JLabel lblHEntradaDomingo = new JLabel("H.Entrada:");
		lblHEntradaDomingo.setBounds(645, 90, 80, 16);
		tjpnlHorarios.add(lblHEntradaDomingo);
		
		JLabel lblHSalidaDomingo = new JLabel("H.Salida:");
		lblHSalidaDomingo.setBounds(645, 140, 80, 16);
		tjpnlHorarios.add(lblHSalidaDomingo);
		
		chkComidaDomingo = new JCheckBox("Comida");
		chkComidaDomingo.setEnabled(false);
		chkComidaDomingo.setBounds(645, 190, 80, 23);
		tjpnlHorarios.add(chkComidaDomingo);
		
		JLabel lblHoraSalidaComerDomingo = new JLabel("H.Salida:");
		lblHoraSalidaComerDomingo.setBounds(645, 220, 80, 16);
		tjpnlHorarios.add(lblHoraSalidaComerDomingo);
		
		JLabel lblHEntradaComidaDomingo = new JLabel("H.Entrada:");
		lblHEntradaComidaDomingo.setBounds(645, 270, 80, 16);
		tjpnlHorarios.add(lblHEntradaComidaDomingo);
		
		SpinnerDateModel model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		
		
		spnHEntradaLunes = new JSpinner();
		spnHEntradaLunes.setEnabled(false);
		spnHEntradaLunes.setBounds(20, 110, 80, 26);

		spnHEntradaLunes.setModel(model);
		spnHEntradaLunes.setEditor(new JSpinner.DateEditor(spnHEntradaLunes, "hh:mm a"));
		
		
//		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
//		Date now2 = null;
//		try {
//			now2 = sdf.parse("00:00 AM");
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
//		spnHEntradaLunes.setValue(now2);
//		spnHEntradaLunes.setValue("08:53 AM");
//		spnHEntradaLunes.setValue("Thu Jul 07 08:50:39 CDT 2016");
		tjpnlHorarios.add(spnHEntradaLunes);
		
		spnHSalidaLunes = new JSpinner();
		spnHSalidaLunes.setEnabled(false);
		spnHSalidaLunes.setBounds(20, 160, 80, 26);
		tjpnlHorarios.add(spnHSalidaLunes);
		
		spnHSalidaComidaLunes = new JSpinner();
		spnHSalidaComidaLunes.setEnabled(false);
		spnHSalidaComidaLunes.setBounds(20, 240, 80, 26);
		tjpnlHorarios.add(spnHSalidaComidaLunes);
		
		spnHEntradaComidaLunes = new JSpinner();
		spnHEntradaComidaLunes.setEnabled(false);
		spnHEntradaComidaLunes.setBounds(20, 290, 80, 26);
		tjpnlHorarios.add(spnHEntradaComidaLunes);
		
		spnHEntradaMartes = new JSpinner();
		spnHEntradaMartes.setEnabled(false);
		spnHEntradaMartes.setBounds(122, 110, 80, 26);
		tjpnlHorarios.add(spnHEntradaMartes);
		
		spnHSalidaMartes = new JSpinner();
		spnHSalidaMartes.setEnabled(false);
		spnHSalidaMartes.setBounds(122, 160, 80, 26);
		tjpnlHorarios.add(spnHSalidaMartes);
		
		spnHSalidaComidaMartes = new JSpinner();
		spnHSalidaComidaMartes.setEnabled(false);
		spnHSalidaComidaMartes.setBounds(122, 240, 80, 26);
		tjpnlHorarios.add(spnHSalidaComidaMartes);
		
		spnHEntradaComidaMartes = new JSpinner();
		spnHEntradaComidaMartes.setEnabled(false);
		spnHEntradaComidaMartes.setBounds(122, 290, 80, 26);
		tjpnlHorarios.add(spnHEntradaComidaMartes);
		
		spnHEntradaMiercoles = new JSpinner();
		spnHEntradaMiercoles.setEnabled(false);
		spnHEntradaMiercoles.setBounds(225, 110, 80, 26);
		tjpnlHorarios.add(spnHEntradaMiercoles);
		
		spnHSalidaMiercoles = new JSpinner();
		spnHSalidaMiercoles.setEnabled(false);
		spnHSalidaMiercoles.setBounds(225, 160, 80, 26);
		tjpnlHorarios.add(spnHSalidaMiercoles);
		
		spnHSalidaComidaMiercoles = new JSpinner();
		spnHSalidaComidaMiercoles.setEnabled(false);
		spnHSalidaComidaMiercoles.setBounds(225, 240, 80, 26);
		tjpnlHorarios.add(spnHSalidaComidaMiercoles);
		
		spnHEntradaComidaMiercoles = new JSpinner();
		spnHEntradaComidaMiercoles.setEnabled(false);
		spnHEntradaComidaMiercoles.setBounds(225, 290, 80, 26);
		tjpnlHorarios.add(spnHEntradaComidaMiercoles);
		
		spnHEntradaJueves = new JSpinner();
		spnHEntradaJueves.setEnabled(false);
		spnHEntradaJueves.setBounds(330, 110, 80, 26);
		tjpnlHorarios.add(spnHEntradaJueves);
		
		spnHSalidaJueves = new JSpinner();
		spnHSalidaJueves.setEnabled(false);
		spnHSalidaJueves.setBounds(330, 160, 80, 26);
		tjpnlHorarios.add(spnHSalidaJueves);
		
		spnHSalidaComidaJueves = new JSpinner();
		spnHSalidaComidaJueves.setEnabled(false);
		spnHSalidaComidaJueves.setBounds(330, 240, 80, 26);
		tjpnlHorarios.add(spnHSalidaComidaJueves);
		
		spnHEntradaComidaJueves = new JSpinner();
		spnHEntradaComidaJueves.setEnabled(false);
		spnHEntradaComidaJueves.setBounds(330, 290, 80, 26);
		tjpnlHorarios.add(spnHEntradaComidaJueves);
		
		spnHEntradaComidaViernes = new JSpinner();
		spnHEntradaComidaViernes.setEnabled(false);
		spnHEntradaComidaViernes.setBounds(438, 290, 80, 26);
		tjpnlHorarios.add(spnHEntradaComidaViernes);
		
		spnHSalidaComidaViernes = new JSpinner();
		spnHSalidaComidaViernes.setEnabled(false);
		spnHSalidaComidaViernes.setBounds(438, 240, 80, 26);
		tjpnlHorarios.add(spnHSalidaComidaViernes);
		
		spnHSalidaViernes = new JSpinner();
		spnHSalidaViernes.setEnabled(false);
		spnHSalidaViernes.setBounds(438, 160, 80, 26);
		tjpnlHorarios.add(spnHSalidaViernes);
		
		spnHEntradaViernes = new JSpinner();
		spnHEntradaViernes.setEnabled(false);
		spnHEntradaViernes.setBounds(438, 110, 80, 26);
		tjpnlHorarios.add(spnHEntradaViernes);
		
		spnHEntradaSabado = new JSpinner();
		spnHEntradaSabado.setEnabled(false);
		spnHEntradaSabado.setBounds(542, 110, 80, 26);
		tjpnlHorarios.add(spnHEntradaSabado);
		
		spnHSalidaSabado = new JSpinner();
		spnHSalidaSabado.setEnabled(false);
		spnHSalidaSabado.setBounds(542, 160, 80, 26);
		tjpnlHorarios.add(spnHSalidaSabado);
		
		spnHSalidaComidaSabado = new JSpinner();
		spnHSalidaComidaSabado.setEnabled(false);
		spnHSalidaComidaSabado.setBounds(542, 240, 80, 26);
		tjpnlHorarios.add(spnHSalidaComidaSabado);
		
		spnHEntradaComidaSabado = new JSpinner();
		spnHEntradaComidaSabado.setEnabled(false);
		spnHEntradaComidaSabado.setBounds(542, 290, 80, 26);
		tjpnlHorarios.add(spnHEntradaComidaSabado);
		
		spnHEntradaDomingo = new JSpinner();
		spnHEntradaDomingo.setEnabled(false);
		spnHEntradaDomingo.setBounds(645, 110, 80, 26);
		tjpnlHorarios.add(spnHEntradaDomingo);
		
		spnHSalidaDomingo = new JSpinner();
		spnHSalidaDomingo.setEnabled(false);
		spnHSalidaDomingo.setBounds(645, 160, 80, 26);
		tjpnlHorarios.add(spnHSalidaDomingo);
		
		spnHSalidaComidaDomingo = new JSpinner();
		spnHSalidaComidaDomingo.setEnabled(false);
		spnHSalidaComidaDomingo.setBounds(645, 240, 80, 26);
		tjpnlHorarios.add(spnHSalidaComidaDomingo);
		
		spnHEntradaComidaDomingo = new JSpinner();
		spnHEntradaComidaDomingo.setEnabled(false);
		spnHEntradaComidaDomingo.setBounds(645, 290, 80, 26);
		tjpnlHorarios.add(spnHEntradaComidaDomingo);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(850, 423, 70, 70);
		getContentPane().add(btnEliminar);
		
		btnNuevoTurno = new JButton("Nuevo");
		btnNuevoTurno.setEnabled(false);
		btnNuevoTurno.setBounds(850, 177, 70, 70);
		getContentPane().add(btnNuevoTurno);
		
		btnGuardarEditar = new JButton("Editar");
		btnGuardarEditar.setEnabled(false);
		btnGuardarEditar.setBounds(850, 304, 70, 70);
		getContentPane().add(btnGuardarEditar);
	}
	
	public JComboBox getCmbArea() {
		return cmbArea;
	}

	public JTextField getTxtMinutosRetardo() {
		return txtMinutosRetardo;
	}

	public void setTxtMinutosRetardo(JTextField txtMinutosRetardo) {
		this.txtMinutosRetardo = txtMinutosRetardo;
	}

	public JTextField getTxtMinutosFalta() {
		return txtMinutosFalta;
	}

	public void setTxtMinutosFalta(JTextField txtMinutosFalta) {
		this.txtMinutosFalta = txtMinutosFalta;
	}

	public JTextField getTxtHoraEntradG() {
		return txtHoraEntradG;
	}

	public void setTxtHoraEntradG(JTextField txtHoraEntradG) {
		this.txtHoraEntradG = txtHoraEntradG;
	}

	public JTextField getTxtHoraSalidaG() {
		return txtHoraSalidaG;
	}

	public void setTxtHoraSalidaG(JTextField txtHoraSalidaG) {
		this.txtHoraSalidaG = txtHoraSalidaG;
	}

	public JTextField getTxtHoraSalidaComidaG() {
		return txtHoraSalidaComidaG;
	}

	public void setTxtHoraSalidaComidaG(JTextField txtHoraSalidaComidaG) {
		this.txtHoraSalidaComidaG = txtHoraSalidaComidaG;
	}

	public JTextField getTxtHoraEntradaComidaG() {
		return txtHoraEntradaComidaG;
	}

	public void setTxtHoraEntradaComidaG(JTextField txtHoraEntradaComidaG) {
		this.txtHoraEntradaComidaG = txtHoraEntradaComidaG;
	}

	public JTextField getTxtNombreTurno() {
		return txtNombreTurno;
	}

	public void setTxtNombreTurno(JTextField txtNombreTurno) {
		this.txtNombreTurno = txtNombreTurno;
	}

	public JCheckBox getChckbxLunes() {
		return chckbxLunes;
	}

	public void setChckbxLunes(JCheckBox chckbxLunes) {
		this.chckbxLunes = chckbxLunes;
	}

	public JCheckBox getChkMartes() {
		return chkMartes;
	}

	public void setChkMartes(JCheckBox chkMartes) {
		this.chkMartes = chkMartes;
	}

	public JCheckBox getChkMiercoles() {
		return chkMiercoles;
	}

	public void setChkMiercoles(JCheckBox chkMiercoles) {
		this.chkMiercoles = chkMiercoles;
	}

	public JCheckBox getChkJueves() {
		return chkJueves;
	}

	public void setChkJueves(JCheckBox chkJueves) {
		this.chkJueves = chkJueves;
	}

	public JCheckBox getChkViernes() {
		return chkViernes;
	}

	public void setChkViernes(JCheckBox chkViernes) {
		this.chkViernes = chkViernes;
	}

	public JCheckBox getChkComidaDomingo() {
		return chkComidaDomingo;
	}

	public void setChkComidaDomingo(JCheckBox chkComidaDomingo) {
		this.chkComidaDomingo = chkComidaDomingo;
	}

	public JCheckBox getChkComidaSabado() {
		return chkComidaSabado;
	}

	public void setChkComidaSabado(JCheckBox chkComidaSabado) {
		this.chkComidaSabado = chkComidaSabado;
	}

	public JCheckBox getChkComidaViernes() {
		return chkComidaViernes;
	}

	public void setChkComidaViernes(JCheckBox chkComidaViernes) {
		this.chkComidaViernes = chkComidaViernes;
	}

	public JCheckBox getChkComidaJueves() {
		return chkComidaJueves;
	}

	public void setChkComidaJueves(JCheckBox chkComidaJueves) {
		this.chkComidaJueves = chkComidaJueves;
	}

	public JCheckBox getChkComidaMiercoles() {
		return chkComidaMiercoles;
	}

	public void setChkComidaMiercoles(JCheckBox chkComidaMiercoles) {
		this.chkComidaMiercoles = chkComidaMiercoles;
	}

	public JCheckBox getChkComidaMartes() {
		return chkComidaMartes;
	}

	public void setChkComidaMartes(JCheckBox chkComidaMartes) {
		this.chkComidaMartes = chkComidaMartes;
	}

	public JCheckBox getChkComidaLunes() {
		return chkComidaLunes;
	}

	public void setChkComidaLunes(JCheckBox chkComidaLunes) {
		this.chkComidaLunes = chkComidaLunes;
	}

	public JCheckBox getChkSabado() {
		return chkSabado;
	}

	public void setChkSabado(JCheckBox chkSabado) {
		this.chkSabado = chkSabado;
	}

	public JCheckBox getChkDomingo() {
		return chkDomingo;
	}

	public void setChkDomingo(JCheckBox chkDomingo) {
		this.chkDomingo = chkDomingo;
	}

	public JSpinner getSpnHEntradaLunes() {
		return spnHEntradaLunes;
	}

	public void setSpnHEntradaLunes(JSpinner spnHEntradaLunes) {
		this.spnHEntradaLunes = spnHEntradaLunes;
	}

	public JSpinner getSpnHSalidaLunes() {
		return spnHSalidaLunes;
	}

	public void setSpnHSalidaLunes(JSpinner spnHSalidaLunes) {
		this.spnHSalidaLunes = spnHSalidaLunes;
	}

	public JSpinner getSpnHSalidaComidaLunes() {
		return spnHSalidaComidaLunes;
	}

	public void setSpnHSalidaComidaLunes(JSpinner spnHSalidaComidaLunes) {
		this.spnHSalidaComidaLunes = spnHSalidaComidaLunes;
	}

	public JSpinner getSpnHEntradaComidaLunes() {
		return spnHEntradaComidaLunes;
	}

	public void setSpnHEntradaComidaLunes(JSpinner spnHEntradaComidaLunes) {
		this.spnHEntradaComidaLunes = spnHEntradaComidaLunes;
	}

	public JSpinner getSpnHSalidaMartes() {
		return spnHSalidaMartes;
	}

	public void setSpnHSalidaMartes(JSpinner spnHSalidaMartes) {
		this.spnHSalidaMartes = spnHSalidaMartes;
	}

	public JSpinner getSpnHSalidaComidaMartes() {
		return spnHSalidaComidaMartes;
	}

	public void setSpnHSalidaComidaMartes(JSpinner spnHSalidaComidaMartes) {
		this.spnHSalidaComidaMartes = spnHSalidaComidaMartes;
	}

	public JSpinner getSpnHEntradaComidaMartes() {
		return spnHEntradaComidaMartes;
	}

	public void setSpnHEntradaComidaMartes(JSpinner spnHEntradaComidaMartes) {
		this.spnHEntradaComidaMartes = spnHEntradaComidaMartes;
	}

	public JSpinner getSpnHEntradaMiercoles() {
		return spnHEntradaMiercoles;
	}

	public void setSpnHEntradaMiercoles(JSpinner spnHEntradaMiercoles) {
		this.spnHEntradaMiercoles = spnHEntradaMiercoles;
	}

	public JSpinner getSpnHSalidaMiercoles() {
		return spnHSalidaMiercoles;
	}

	public void setSpnHSalidaMiercoles(JSpinner spnHSalidaMiercoles) {
		this.spnHSalidaMiercoles = spnHSalidaMiercoles;
	}

	public JSpinner getSpnHSalidaComidaMiercoles() {
		return spnHSalidaComidaMiercoles;
	}

	public void setSpnHSalidaComidaMiercoles(JSpinner spnHSalidaComidaMiercoles) {
		this.spnHSalidaComidaMiercoles = spnHSalidaComidaMiercoles;
	}

	public JSpinner getSpnHEntradaComidaMiercoles() {
		return spnHEntradaComidaMiercoles;
	}

	public void setSpnHEntradaComidaMiercoles(JSpinner spnHEntradaComidaMiercoles) {
		this.spnHEntradaComidaMiercoles = spnHEntradaComidaMiercoles;
	}

	public JSpinner getSpnHEntradaJueves() {
		return spnHEntradaJueves;
	}

	public void setSpnHEntradaJueves(JSpinner spnHEntradaJueves) {
		this.spnHEntradaJueves = spnHEntradaJueves;
	}

	public JSpinner getSpnHSalidaJueves() {
		return spnHSalidaJueves;
	}

	public void setSpnHSalidaJueves(JSpinner spnHSalidaJueves) {
		this.spnHSalidaJueves = spnHSalidaJueves;
	}

	public JSpinner getSpnHSalidaComidaJueves() {
		return spnHSalidaComidaJueves;
	}

	public void setSpnHSalidaComidaJueves(JSpinner spnHSalidaComidaJueves) {
		this.spnHSalidaComidaJueves = spnHSalidaComidaJueves;
	}

	public JSpinner getSpnHEntradaComidaJueves() {
		return spnHEntradaComidaJueves;
	}

	public void setSpnHEntradaComidaJueves(JSpinner spnHEntradaComidaJueves) {
		this.spnHEntradaComidaJueves = spnHEntradaComidaJueves;
	}

	public JSpinner getSpnHEntradaComidaViernes() {
		return spnHEntradaComidaViernes;
	}

	public void setSpnHEntradaComidaViernes(JSpinner spnHEntradaComidaViernes) {
		this.spnHEntradaComidaViernes = spnHEntradaComidaViernes;
	}

	public JSpinner getSpnHSalidaComidaViernes() {
		return spnHSalidaComidaViernes;
	}

	public void setSpnHSalidaComidaViernes(JSpinner spnHSalidaComidaViernes) {
		this.spnHSalidaComidaViernes = spnHSalidaComidaViernes;
	}

	public JSpinner getSpnHSalidaViernes() {
		return spnHSalidaViernes;
	}

	public void setSpnHSalidaViernes(JSpinner spnHSalidaViernes) {
		this.spnHSalidaViernes = spnHSalidaViernes;
	}

	public JSpinner getSpnHEntradaViernes() {
		return spnHEntradaViernes;
	}

	public void setSpnHEntradaViernes(JSpinner spnHEntradaViernes) {
		this.spnHEntradaViernes = spnHEntradaViernes;
	}

	public JSpinner getSpnHEntradaSabado() {
		return spnHEntradaSabado;
	}

	public void setSpnHEntradaSabado(JSpinner spnHEntradaSabado) {
		this.spnHEntradaSabado = spnHEntradaSabado;
	}

	public JSpinner getSpnHSalidaSabado() {
		return spnHSalidaSabado;
	}

	public void setSpnHSalidaSabado(JSpinner spnHSalidaSabado) {
		this.spnHSalidaSabado = spnHSalidaSabado;
	}

	public JSpinner getSpnHSalidaComidaSabado() {
		return spnHSalidaComidaSabado;
	}

	public void setSpnHSalidaComidaSabado(JSpinner spnHSalidaComidaSabado) {
		this.spnHSalidaComidaSabado = spnHSalidaComidaSabado;
	}

	public JSpinner getSpnHEntradaComidaSabado() {
		return spnHEntradaComidaSabado;
	}

	public void setSpnHEntradaComidaSabado(JSpinner spnHEntradaComidaSabado) {
		this.spnHEntradaComidaSabado = spnHEntradaComidaSabado;
	}

	public JSpinner getSpnHEntradaDomingo() {
		return spnHEntradaDomingo;
	}

	public void setSpnHEntradaDomingo(JSpinner spnHEntradaDomingo) {
		this.spnHEntradaDomingo = spnHEntradaDomingo;
	}

	public JSpinner getSpnHSalidaDomingo() {
		return spnHSalidaDomingo;
	}

	public void setSpnHSalidaDomingo(JSpinner spnHSalidaDomingo) {
		this.spnHSalidaDomingo = spnHSalidaDomingo;
	}

	public JSpinner getSpnHSalidaComidaDomingo() {
		return spnHSalidaComidaDomingo;
	}

	public void setSpnHSalidaComidaDomingo(JSpinner spnHSalidaComidaDomingo) {
		this.spnHSalidaComidaDomingo = spnHSalidaComidaDomingo;
	}

	public JSpinner getSpnHEntradaComidaDomingo() {
		return spnHEntradaComidaDomingo;
	}

	public void setSpnHEntradaComidaDomingo(JSpinner spnHEntradaComidaDomingo) {
		this.spnHEntradaComidaDomingo = spnHEntradaComidaDomingo;
	}

	public JSpinner getSpnHEntradaMartes() {
		return spnHEntradaMartes;
	}

	public void setSpnHEntradaMartes(JSpinner spnHEntradaMartes) {
		this.spnHEntradaMartes = spnHEntradaMartes;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	public JButton getBtnNuevoTurno() {
		return btnNuevoTurno;
	}

	public void setBtnNuevoTurno(JButton btnNuevoTurno) {
		this.btnNuevoTurno = btnNuevoTurno;
	}

	public JButton getBtnGuardarEditar() {
		return btnGuardarEditar;
	}

	public void setBtnGuardarEditar(JButton btnGuardarEditar) {
		this.btnGuardarEditar = btnGuardarEditar;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}

	public JCheckBox getChkHorarioGeneral() {
		return chkHorarioGeneral;
	}

	public void setChkHorarioGeneral(JCheckBox chkHorarioGeneral) {
		this.chkHorarioGeneral = chkHorarioGeneral;
	}

	public void setCmbArea(JComboBox cmbArea) {
		this.cmbArea = cmbArea;
	}

	public void limpiarVentana(){
		txtMinutosRetardo.setText(null);
		txtMinutosFalta.setText(null);
		txtHoraEntradG.setText(null);
		txtHoraSalidaG.setText(null);
		txtHoraSalidaComidaG.setText(null);
		txtHoraEntradaComidaG.setText(null);
		txtNombreTurno.setText(null);
		
		txtMinutosRetardo.setEnabled(false);
		txtMinutosFalta.setEnabled(false);
		txtHoraEntradG.setEnabled(false);
		txtHoraSalidaG.setEnabled(false);
		txtHoraSalidaComidaG.setEnabled(false);
		txtHoraEntradaComidaG.setEnabled(false);
		txtNombreTurno.setEnabled(false);
		
		chkHorarioGeneral.setSelected(false);
		chckbxLunes.setSelected(false);
		chkMartes.setSelected(false);
		chkMiercoles.setSelected(false);
		chkJueves.setSelected(false);
		chkViernes.setSelected(false);
		chkComidaDomingo.setSelected(false);
		chkComidaSabado.setSelected(false);
		chkComidaViernes.setSelected(false);
		chkComidaJueves.setSelected(false);
		chkComidaMiercoles.setSelected(false);
		chkComidaMartes.setSelected(false);
		chkComidaLunes.setSelected(false);
		chkSabado.setSelected(false);
		chkDomingo.setSelected(false);
		
		chkHorarioGeneral.setEnabled(false);
		chckbxLunes.setEnabled(false);
		chkMartes.setEnabled(false);
		chkMiercoles.setEnabled(false);
		chkJueves.setEnabled(false);
		chkViernes.setEnabled(false);
		chkComidaDomingo.setEnabled(false);
		chkComidaSabado.setEnabled(false);
		chkComidaViernes.setEnabled(false);
		chkComidaJueves.setEnabled(false);
		chkComidaMiercoles.setEnabled(false);
		chkComidaMartes.setEnabled(false);
		chkComidaLunes.setEnabled(false);
		chkSabado.setEnabled(false);
		chkDomingo.setEnabled(false);
		
//		spnHEntradaLunes.setValue("00:00 AM");
//		spnHSalidaLunes;
//		spnHSalidaComidaLunes;
//		spnHEntradaComidaLunes;
//		spnHSalidaMartes;
//		spnHSalidaComidaMartes;
//		spnHEntradaComidaMartes;
//		spnHEntradaMiercoles;
//		spnHSalidaMiercoles;
//		spnHSalidaComidaMiercoles;
//		spnHEntradaComidaMiercoles;
//		spnHEntradaJueves;
//		spnHSalidaJueves;
//		spnHSalidaComidaJueves;
//		spnHEntradaComidaJueves;
//		spnHEntradaComidaViernes;
//		spnHSalidaComidaViernes;
//		spnHSalidaViernes;
//		spnHEntradaViernes;
//		spnHEntradaSabado;
//		spnHSalidaSabado;
//		spnHSalidaComidaSabado;
//		spnHEntradaComidaSabado;
//		spnHEntradaDomingo;
//		spnHSalidaDomingo;
//		spnHSalidaComidaDomingo;
//		spnHEntradaComidaDomingo;
//		spnHEntradaMartes;
//		
//		spnHEntradaLunes;
//		spnHSalidaLunes;
//		spnHSalidaComidaLunes;
//		spnHEntradaComidaLunes;
//		spnHSalidaMartes;
//		spnHSalidaComidaMartes;
//		spnHEntradaComidaMartes;
//		spnHEntradaMiercoles;
//		spnHSalidaMiercoles;
//		spnHSalidaComidaMiercoles;
//		spnHEntradaComidaMiercoles;
//		spnHEntradaJueves;
//		spnHSalidaJueves;
//		spnHSalidaComidaJueves;
//		spnHEntradaComidaJueves;
//		spnHEntradaComidaViernes;
//		spnHSalidaComidaViernes;
//		spnHSalidaViernes;
//		spnHEntradaViernes;
//		spnHEntradaSabado;
//		spnHSalidaSabado;
//		spnHSalidaComidaSabado;
//		spnHEntradaComidaSabado;
//		spnHEntradaDomingo;
//		spnHSalidaDomingo;
//		spnHSalidaComidaDomingo;
//		spnHEntradaComidaDomingo;
//		spnHEntradaMartes;
//		
//		tabbedPane;
//		
//		btnNuevoTurno;
//		btnGuardarEditar;
//		btnEliminar;
		
	}
}
