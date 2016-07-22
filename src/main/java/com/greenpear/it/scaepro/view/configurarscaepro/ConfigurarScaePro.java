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
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Formatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Component;
import javax.swing.SpinnerNumberModel;
import java.awt.SystemColor;

public class ConfigurarScaePro extends JFrame {
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
	private JSpinner spnHoraSalidaG;
	private JSpinner spnHoraEntradaG;
	private JSpinner spnHoraSalidaComidaG;
	private JSpinner spnHoraEntradaComidaG;
	
	private SpinnerDateModel model;
	
	Date now;
	private JPanel tjpnlHorarios;
	private JLabel lblHEntradaLunes;
	private JLabel lblHSalidaLunes;
	private JLabel lblHoraSalidaComerLunes;
	private JLabel lblHEntradaComidaLunes;
	private JLabel lblHEntradaMartes;
	private JLabel lblHSalidaMartes;
	private JLabel lblHoraSalidaComerMartes;
	private JLabel lblHEntradaComidaMartes;
	private JLabel lblHEntradaComidaMiercoles;
	private JLabel lblHoraSalidaComerMiercoles;
	private JLabel lblHSalidaMiercoles;
	private JLabel lblHEntradaMiercoles;
	private JLabel lblHEntradaJueves;
	private JLabel lblHSalidaJueves;
	private JLabel lblHoraSalidaComerJueves;
	private JLabel lblHEntradaComidaJueves;
	private JLabel lblHEntradaViernes;
	private JLabel lblHSalidaViernes;
	private JLabel lblHoraSalidaComerViernes;
	private JLabel lblHEntradaComidaViernes;
	private JLabel lblHEntradaSabado;
	private JLabel lblHSalidaSabado;
	private JLabel lblHoraSalidaComerSabado;
	private JLabel lblHEntradaComidaSabado;
	private JLabel lblHEntradaComidaDomingo;
	private JLabel lblHoraSalidaComerDomingo;
	private JLabel lblHSalidaDomingo;
	private JLabel lblHEntradaDomingo;
	private JSpinner spnMinutosRetardo;
	private JSpinner spnMinutosFalta;
	
	public ConfigurarScaePro() {
		getContentPane().setBackground(SystemColor.activeCaption);
		getContentPane().setLayout(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 570);
		setLocationRelativeTo(null);
		super.setTitle("Configurar Scae Pro");
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Configuracion del \u00E1rea", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
		
		JLabel lblHoraEntradaG = new JLabel("Hora de entrada:");
		lblHoraEntradaG.setEnabled(false);
		lblHoraEntradaG.setBounds(355, 70, 147, 16);
		panel.add(lblHoraEntradaG);
		
		JLabel lblHoraDeSalidaG = new JLabel("Hora de salida:");
		lblHoraDeSalidaG.setEnabled(false);
		lblHoraDeSalidaG.setBounds(355, 100, 147, 16);
		panel.add(lblHoraDeSalidaG);
		
		JLabel lblHoraSalidaComidaG = new JLabel("Hora de salida (Comida):");
		lblHoraSalidaComidaG.setEnabled(false);
		lblHoraSalidaComidaG.setBounds(618, 70, 165, 16);
		panel.add(lblHoraSalidaComidaG);
		
		JLabel lblHoraDeEntradaComidaG = new JLabel("Hora de entrada (Comida):");
		lblHoraDeEntradaComidaG.setEnabled(false);
		lblHoraDeEntradaComidaG.setBounds(618, 100, 165, 16);
		panel.add(lblHoraDeEntradaComidaG);
		
		cmbArea = new JComboBox();
		cmbArea.setModel(new DefaultComboBoxModel(new String[] {"---------------Seleccione un \u00E1rea---------------"}));
		cmbArea.setBounds(159, 35, 265, 27);
		panel.add(cmbArea);
		
		chkHorarioGeneral = new JCheckBox("Aplicar un horario para el turno en general");
		chkHorarioGeneral.setBackground(SystemColor.activeCaption);
		chkHorarioGeneral.setEnabled(false);
		chkHorarioGeneral.setBounds(503, 35, 300, 23);
		panel.add(chkHorarioGeneral);
		
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
		
		try {
			now = sdf.parse("00:00 AM");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		
		spnHoraEntradaG = new JSpinner();
		spnHoraEntradaG.setEnabled(false);
		spnHoraEntradaG.setBounds(453, 68, 130, 23);
		spnHoraEntradaG.setModel(new SpinnerDateModel());
		spnHoraEntradaG.setEditor(new JSpinner.DateEditor(spnHoraEntradaG, "hh:mm a"));
		spnHoraEntradaG.setValue(now);
		panel.add(spnHoraEntradaG);
		
		model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		
		spnHoraSalidaG = new JSpinner();
		spnHoraSalidaG.setEnabled(false);
		spnHoraSalidaG.setBounds(453, 98, 130, 23);
		spnHoraSalidaG.setModel(model);
		spnHoraSalidaG.setEditor(new JSpinner.DateEditor(spnHoraSalidaG, "hh:mm a"));
		spnHoraSalidaG.setValue(now);
		panel.add(spnHoraSalidaG);
		
		model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		
		spnHoraSalidaComidaG = new JSpinner();
		spnHoraSalidaComidaG.setEnabled(false);
		spnHoraSalidaComidaG.setBounds(770, 68, 130, 23);
		spnHoraSalidaComidaG.setModel(model);
		spnHoraSalidaComidaG.setEditor(new JSpinner.DateEditor(spnHoraSalidaComidaG, "hh:mm a"));
		spnHoraSalidaComidaG.setValue(now);
		panel.add(spnHoraSalidaComidaG);
		
		model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		
		spnHoraEntradaComidaG = new JSpinner();
		spnHoraEntradaComidaG.setEnabled(false);
		spnHoraEntradaComidaG.setBounds(770, 98, 130, 23);
		spnHoraEntradaComidaG.setModel(model);
		spnHoraEntradaComidaG.setEditor(new JSpinner.DateEditor(spnHoraEntradaComidaG, "hh:mm a"));
		spnHoraEntradaComidaG.setValue(now);
		panel.add(spnHoraEntradaComidaG);		
		
		spnMinutosRetardo = new JSpinner();
		spnMinutosRetardo.setEnabled(false);
		spnMinutosRetardo.setModel(new SpinnerNumberModel(0, 0, 30, 1));
		spnMinutosRetardo.setBounds(190, 67, 130, 22);
		panel.add(spnMinutosRetardo);
		
		spnMinutosFalta = new JSpinner();
		spnMinutosFalta.setEnabled(false);
		spnMinutosFalta.setModel(new SpinnerNumberModel(0, 0, 30, 1));
		spnMinutosFalta.setBounds(190, 97, 130, 23);
		panel.add(spnMinutosFalta);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setVisible(false);
		tabbedPane.setBorder(new TitledBorder(null, "Configuracion de horarios:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tabbedPane.setBounds(0, 151, 788, 380);
		getContentPane().add(tabbedPane);
		
		tjpnlHorarios = new JPanel();
		tjpnlHorarios.setBackground(SystemColor.activeCaption);
		tabbedPane.addTab("Nuevo Turno", null, tjpnlHorarios, null);
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
		chckbxLunes.setBackground(SystemColor.activeCaption);
		chckbxLunes.setBounds(20, 60, 80, 23);
		tjpnlHorarios.add(chckbxLunes);
		
		lblHEntradaLunes = new JLabel("H.Entrada:");
		lblHEntradaLunes.setBounds(20, 90, 80, 16);
		tjpnlHorarios.add(lblHEntradaLunes);
		
		lblHSalidaLunes = new JLabel("H.Salida:");
		lblHSalidaLunes.setBounds(20, 140, 80, 16);
		tjpnlHorarios.add(lblHSalidaLunes);
		
		chkComidaLunes = new JCheckBox("Comida");
		chkComidaLunes.setBackground(SystemColor.activeCaption);
		chkComidaLunes.setEnabled(false);
		chkComidaLunes.setBounds(20, 190, 80, 23);
		tjpnlHorarios.add(chkComidaLunes);
		
		lblHoraSalidaComerLunes = new JLabel("H.Salida:");
		lblHoraSalidaComerLunes.setBounds(20, 220, 80, 16);
		tjpnlHorarios.add(lblHoraSalidaComerLunes);
		
		lblHEntradaComidaLunes = new JLabel("H.Entrada:");
		lblHEntradaComidaLunes.setBounds(20, 270, 80, 16);
		tjpnlHorarios.add(lblHEntradaComidaLunes);
		
		chkMartes = new JCheckBox("Martes");
		chkMartes.setBackground(SystemColor.activeCaption);
		chkMartes.setBounds(122, 60, 80, 23);
		tjpnlHorarios.add(chkMartes);
		
		lblHEntradaMartes = new JLabel("H.Entrada:");
		lblHEntradaMartes.setBounds(122, 90, 80, 16);
		tjpnlHorarios.add(lblHEntradaMartes);
		
		lblHSalidaMartes = new JLabel("H.Salida:");
		lblHSalidaMartes.setBounds(122, 140, 80, 16);
		tjpnlHorarios.add(lblHSalidaMartes);
		
		chkComidaMartes = new JCheckBox("Comida");
		chkComidaMartes.setBackground(SystemColor.activeCaption);
		chkComidaMartes.setEnabled(false);
		chkComidaMartes.setBounds(122, 190, 80, 23);
		tjpnlHorarios.add(chkComidaMartes);
		
		lblHoraSalidaComerMartes = new JLabel("H.Salida:");
		lblHoraSalidaComerMartes.setBounds(122, 220, 80, 16);
		tjpnlHorarios.add(lblHoraSalidaComerMartes);
		
		lblHEntradaComidaMartes = new JLabel("H.Entrada:");
		lblHEntradaComidaMartes.setBounds(122, 270, 80, 16);
		tjpnlHorarios.add(lblHEntradaComidaMartes);
		
		chkMiercoles = new JCheckBox("Miercoles");
		chkMiercoles.setBackground(SystemColor.activeCaption);
		chkMiercoles.setBounds(225, 60, 100, 23);
		tjpnlHorarios.add(chkMiercoles);
		
		lblHEntradaMiercoles = new JLabel("H.Entrada:");
		lblHEntradaMiercoles.setBounds(225, 90, 80, 16);
		tjpnlHorarios.add(lblHEntradaMiercoles);
		
		lblHSalidaMiercoles = new JLabel("H.Salida:");
		lblHSalidaMiercoles.setBounds(225, 140, 80, 16);
		tjpnlHorarios.add(lblHSalidaMiercoles);
		
		chkComidaMiercoles = new JCheckBox("Comida");
		chkComidaMiercoles.setBackground(SystemColor.activeCaption);
		chkComidaMiercoles.setEnabled(false);
		chkComidaMiercoles.setBounds(225, 190, 80, 23);
		tjpnlHorarios.add(chkComidaMiercoles);
		
		lblHoraSalidaComerMiercoles = new JLabel("H.Salida:");
		lblHoraSalidaComerMiercoles.setBounds(225, 220, 80, 16);
		tjpnlHorarios.add(lblHoraSalidaComerMiercoles);
		
		lblHEntradaComidaMiercoles = new JLabel("H.Entrada:");
		lblHEntradaComidaMiercoles.setBounds(225, 270, 80, 16);
		tjpnlHorarios.add(lblHEntradaComidaMiercoles);
		
		chkJueves = new JCheckBox("Jueves");
		chkJueves.setBackground(SystemColor.activeCaption);
		chkJueves.setBounds(330, 60, 80, 23);
		tjpnlHorarios.add(chkJueves);
		
		lblHEntradaJueves = new JLabel("H.Entrada:");
		lblHEntradaJueves.setBounds(330, 90, 80, 16);
		tjpnlHorarios.add(lblHEntradaJueves);
		
		lblHSalidaJueves = new JLabel("H.Salida:");
		lblHSalidaJueves.setBounds(330, 140, 80, 16);
		tjpnlHorarios.add(lblHSalidaJueves);
		
		chkComidaJueves = new JCheckBox("Comida");
		chkComidaJueves.setBackground(SystemColor.activeCaption);
		chkComidaJueves.setEnabled(false);
		chkComidaJueves.setBounds(330, 190, 80, 23);
		tjpnlHorarios.add(chkComidaJueves);
		
		lblHoraSalidaComerJueves = new JLabel("H.Salida:");
		lblHoraSalidaComerJueves.setBounds(330, 220, 80, 16);
		tjpnlHorarios.add(lblHoraSalidaComerJueves);
		
		lblHEntradaComidaJueves = new JLabel("H.Entrada:");
		lblHEntradaComidaJueves.setBounds(330, 270, 80, 16);
		tjpnlHorarios.add(lblHEntradaComidaJueves);
		
		chkViernes = new JCheckBox("Viernes");
		chkViernes.setBackground(SystemColor.activeCaption);
		chkViernes.setBounds(438, 60, 80, 23);
		tjpnlHorarios.add(chkViernes);
		
		lblHEntradaViernes = new JLabel("H.Entrada:");
		lblHEntradaViernes.setBounds(438, 90, 80, 16);
		tjpnlHorarios.add(lblHEntradaViernes);
		
		lblHSalidaViernes = new JLabel("H.Salida:");
		lblHSalidaViernes.setBounds(438, 140, 80, 16);
		tjpnlHorarios.add(lblHSalidaViernes);
		
		chkComidaViernes = new JCheckBox("Comida");
		chkComidaViernes.setBackground(SystemColor.activeCaption);
		chkComidaViernes.setEnabled(false);
		chkComidaViernes.setBounds(438, 190, 80, 23);
		tjpnlHorarios.add(chkComidaViernes);
		
		lblHoraSalidaComerViernes = new JLabel("H.Salida:");
		lblHoraSalidaComerViernes.setBounds(438, 220, 80, 16);
		tjpnlHorarios.add(lblHoraSalidaComerViernes);
		
		lblHEntradaComidaViernes = new JLabel("H.Entrada:");
		lblHEntradaComidaViernes.setBounds(438, 270, 80, 16);
		tjpnlHorarios.add(lblHEntradaComidaViernes);
		
		chkSabado = new JCheckBox("Sabado");
		chkSabado.setBackground(SystemColor.activeCaption);
		chkSabado.setBounds(542, 60, 80, 23);
		tjpnlHorarios.add(chkSabado);
		
		lblHEntradaSabado = new JLabel("H.Entrada:");
		lblHEntradaSabado.setBounds(542, 90, 80, 16);
		tjpnlHorarios.add(lblHEntradaSabado);
		
		lblHSalidaSabado = new JLabel("H.Salida:");
		lblHSalidaSabado.setBounds(542, 140, 80, 16);
		tjpnlHorarios.add(lblHSalidaSabado);
		
		chkComidaSabado = new JCheckBox("Comida");
		chkComidaSabado.setBackground(SystemColor.activeCaption);
		chkComidaSabado.setEnabled(false);
		chkComidaSabado.setBounds(542, 190, 80, 23);
		tjpnlHorarios.add(chkComidaSabado);
		
		lblHoraSalidaComerSabado = new JLabel("H.Salida:");
		lblHoraSalidaComerSabado.setBounds(542, 220, 80, 16);
		tjpnlHorarios.add(lblHoraSalidaComerSabado);
		
		lblHEntradaComidaSabado = new JLabel("H.Entrada:");
		lblHEntradaComidaSabado.setBounds(542, 270, 80, 16);
		tjpnlHorarios.add(lblHEntradaComidaSabado);
		
		chkDomingo = new JCheckBox("Domingo");
		chkDomingo.setBackground(SystemColor.activeCaption);
		chkDomingo.setBounds(645, 60, 80, 23);
		tjpnlHorarios.add(chkDomingo);
		
		lblHEntradaDomingo = new JLabel("H.Entrada:");
		lblHEntradaDomingo.setBounds(645, 90, 80, 16);
		tjpnlHorarios.add(lblHEntradaDomingo);
		
		lblHSalidaDomingo = new JLabel("H.Salida:");
		lblHSalidaDomingo.setBounds(645, 140, 80, 16);
		tjpnlHorarios.add(lblHSalidaDomingo);
		
		chkComidaDomingo = new JCheckBox("Comida");
		chkComidaDomingo.setBackground(SystemColor.activeCaption);
		chkComidaDomingo.setEnabled(false);
		chkComidaDomingo.setBounds(645, 190, 80, 23);
		tjpnlHorarios.add(chkComidaDomingo);
		
		lblHoraSalidaComerDomingo = new JLabel("H.Salida:");
		lblHoraSalidaComerDomingo.setBounds(645, 220, 80, 16);
		tjpnlHorarios.add(lblHoraSalidaComerDomingo);
		
		lblHEntradaComidaDomingo = new JLabel("H.Entrada:");
		lblHEntradaComidaDomingo.setBounds(645, 270, 80, 16);
		tjpnlHorarios.add(lblHEntradaComidaDomingo);
		
		model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		
		spnHEntradaLunes = new JSpinner();
		spnHEntradaLunes.setEnabled(false);
		spnHEntradaLunes.setBounds(20, 110, 80, 26);
		spnHEntradaLunes.setModel(model);
		spnHEntradaLunes.setEditor(new JSpinner.DateEditor(spnHEntradaLunes, "hh:mm a"));
		spnHEntradaLunes.setValue(now);
		tjpnlHorarios.add(spnHEntradaLunes);
		
		model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		
		spnHSalidaLunes = new JSpinner();
		spnHSalidaLunes.setEnabled(false);
		spnHSalidaLunes.setBounds(20, 160, 80, 26);
		spnHSalidaLunes.setModel(model);
		spnHSalidaLunes.setEditor(new JSpinner.DateEditor(spnHSalidaLunes, "hh:mm a"));
		spnHSalidaLunes.setValue(now);
		tjpnlHorarios.add(spnHSalidaLunes);
		
		model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		
		spnHSalidaComidaLunes = new JSpinner();
		spnHSalidaComidaLunes.setEnabled(false);
		spnHSalidaComidaLunes.setBounds(20, 240, 80, 26);
		spnHSalidaComidaLunes.setModel(model);
		spnHSalidaComidaLunes.setEditor(new JSpinner.DateEditor(spnHSalidaComidaLunes, "hh:mm a"));
		spnHSalidaComidaLunes.setValue(now);
		tjpnlHorarios.add(spnHSalidaComidaLunes);
		
		model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		
		spnHEntradaComidaLunes = new JSpinner();
		spnHEntradaComidaLunes.setEnabled(false);
		spnHEntradaComidaLunes.setBounds(20, 290, 80, 26);
		spnHEntradaComidaLunes.setModel(model);
		spnHEntradaComidaLunes.setEditor(new JSpinner.DateEditor(spnHEntradaComidaLunes, "hh:mm a"));
		spnHEntradaComidaLunes.setValue(now);
		tjpnlHorarios.add(spnHEntradaComidaLunes);
		
		model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		
		spnHEntradaMartes = new JSpinner();
		spnHEntradaMartes.setEnabled(false);
		spnHEntradaMartes.setBounds(122, 110, 80, 26);
		spnHEntradaMartes.setModel(model);
		spnHEntradaMartes.setEditor(new JSpinner.DateEditor(spnHEntradaMartes, "hh:mm a"));
		spnHEntradaMartes.setValue(now);
		tjpnlHorarios.add(spnHEntradaMartes);
		
		model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		
		spnHSalidaMartes = new JSpinner();
		spnHSalidaMartes.setEnabled(false);
		spnHSalidaMartes.setBounds(122, 160, 80, 26);
		spnHSalidaMartes.setModel(model);
		spnHSalidaMartes.setEditor(new JSpinner.DateEditor(spnHSalidaMartes, "hh:mm a"));
		spnHSalidaMartes.setValue(now);
		tjpnlHorarios.add(spnHSalidaMartes);
		
		model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		
		spnHSalidaComidaMartes = new JSpinner();
		spnHSalidaComidaMartes.setEnabled(false);
		spnHSalidaComidaMartes.setBounds(122, 240, 80, 26);
		spnHSalidaComidaMartes.setModel(model);
		spnHSalidaComidaMartes.setEditor(new JSpinner.DateEditor(spnHSalidaComidaMartes, "hh:mm a"));
		spnHSalidaComidaMartes.setValue(now);
		tjpnlHorarios.add(spnHSalidaComidaMartes);
		
		model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		
		spnHEntradaComidaMartes = new JSpinner();
		spnHEntradaComidaMartes.setEnabled(false);
		spnHEntradaComidaMartes.setBounds(122, 290, 80, 26);
		spnHEntradaComidaMartes.setModel(model);
		spnHEntradaComidaMartes.setEditor(new JSpinner.DateEditor(spnHEntradaComidaMartes, "hh:mm a"));
		spnHEntradaComidaMartes.setValue(now);
		tjpnlHorarios.add(spnHEntradaComidaMartes);
		
		model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		
		spnHEntradaMiercoles = new JSpinner();
		spnHEntradaMiercoles.setEnabled(false);
		spnHEntradaMiercoles.setBounds(225, 110, 80, 26);
		spnHEntradaMiercoles.setModel(model);
		spnHEntradaMiercoles.setEditor(new JSpinner.DateEditor(spnHEntradaMiercoles, "hh:mm a"));
		spnHEntradaMiercoles.setValue(now);
		tjpnlHorarios.add(spnHEntradaMiercoles);
		
		model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		
		spnHSalidaMiercoles = new JSpinner();
		spnHSalidaMiercoles.setEnabled(false);
		spnHSalidaMiercoles.setBounds(225, 160, 80, 26);
		spnHSalidaMiercoles.setModel(model);
		spnHSalidaMiercoles.setEditor(new JSpinner.DateEditor(spnHSalidaMiercoles, "hh:mm a"));
		spnHSalidaMiercoles.setValue(now);
		tjpnlHorarios.add(spnHSalidaMiercoles);
		
		model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		
		spnHSalidaComidaMiercoles = new JSpinner();
		spnHSalidaComidaMiercoles.setEnabled(false);
		spnHSalidaComidaMiercoles.setBounds(225, 240, 80, 26);
		spnHSalidaComidaMiercoles.setModel(model);
		spnHSalidaComidaMiercoles.setEditor(new JSpinner.DateEditor(spnHSalidaComidaMiercoles, "hh:mm a"));
		spnHSalidaComidaMiercoles.setValue(now);
		tjpnlHorarios.add(spnHSalidaComidaMiercoles);
		
		model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		
		spnHEntradaComidaMiercoles = new JSpinner();
		spnHEntradaComidaMiercoles.setEnabled(false);
		spnHEntradaComidaMiercoles.setBounds(225, 290, 80, 26);
		spnHEntradaComidaMiercoles.setModel(model);
		spnHEntradaComidaMiercoles.setEditor(new JSpinner.DateEditor(spnHEntradaComidaMiercoles, "hh:mm a"));
		spnHEntradaComidaMiercoles.setValue(now);
		tjpnlHorarios.add(spnHEntradaComidaMiercoles);
		
		model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		
		spnHEntradaJueves = new JSpinner();
		spnHEntradaJueves.setEnabled(false);
		spnHEntradaJueves.setBounds(330, 110, 80, 26);
		spnHEntradaJueves.setModel(model);
		spnHEntradaJueves.setEditor(new JSpinner.DateEditor(spnHEntradaJueves, "hh:mm a"));
		spnHEntradaJueves.setValue(now);
		tjpnlHorarios.add(spnHEntradaJueves);
		
		model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		
		spnHSalidaJueves = new JSpinner();
		spnHSalidaJueves.setEnabled(false);
		spnHSalidaJueves.setBounds(330, 160, 80, 26);
		spnHSalidaJueves.setModel(model);
		spnHSalidaJueves.setEditor(new JSpinner.DateEditor(spnHSalidaJueves, "hh:mm a"));
		spnHSalidaJueves.setValue(now);
		tjpnlHorarios.add(spnHSalidaJueves);
		
		model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		
		spnHSalidaComidaJueves = new JSpinner();
		spnHSalidaComidaJueves.setEnabled(false);
		spnHSalidaComidaJueves.setBounds(330, 240, 80, 26);
		spnHSalidaComidaJueves.setModel(model);
		spnHSalidaComidaJueves.setEditor(new JSpinner.DateEditor(spnHSalidaComidaJueves, "hh:mm a"));
		spnHSalidaComidaJueves.setValue(now);
		tjpnlHorarios.add(spnHSalidaComidaJueves);
		
		model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		
		spnHEntradaComidaJueves = new JSpinner();
		spnHEntradaComidaJueves.setEnabled(false);
		spnHEntradaComidaJueves.setBounds(330, 290, 80, 26);
		spnHEntradaComidaJueves.setModel(model);
		spnHEntradaComidaJueves.setEditor(new JSpinner.DateEditor(spnHEntradaComidaJueves, "hh:mm a"));
		spnHEntradaComidaJueves.setValue(now);
		tjpnlHorarios.add(spnHEntradaComidaJueves);
		
		model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		
		spnHEntradaComidaViernes = new JSpinner();
		spnHEntradaComidaViernes.setEnabled(false);
		spnHEntradaComidaViernes.setBounds(438, 290, 80, 26);
		spnHEntradaComidaViernes.setModel(model);
		spnHEntradaComidaViernes.setEditor(new JSpinner.DateEditor(spnHEntradaComidaJueves, "hh:mm a"));
		spnHEntradaComidaViernes.setValue(now);
		tjpnlHorarios.add(spnHEntradaComidaViernes);
		
		model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		
		spnHSalidaComidaViernes = new JSpinner();
		spnHSalidaComidaViernes.setEnabled(false);
		spnHSalidaComidaViernes.setBounds(438, 240, 80, 26);
		spnHSalidaComidaViernes.setModel(model);
		spnHSalidaComidaViernes.setEditor(new JSpinner.DateEditor(spnHSalidaComidaViernes, "hh:mm a"));
		spnHSalidaComidaViernes.setValue(now);
		tjpnlHorarios.add(spnHSalidaComidaViernes);
		
		model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		
		spnHSalidaViernes = new JSpinner();
		spnHSalidaViernes.setEnabled(false);
		spnHSalidaViernes.setBounds(438, 160, 80, 26);
		spnHSalidaViernes.setModel(model);
		spnHSalidaViernes.setEditor(new JSpinner.DateEditor(spnHSalidaViernes, "hh:mm a"));
		spnHSalidaViernes.setValue(now);
		tjpnlHorarios.add(spnHSalidaViernes);
		
		model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		
		spnHEntradaViernes = new JSpinner();
		spnHEntradaViernes.setEnabled(false);
		spnHEntradaViernes.setBounds(438, 110, 80, 26);
		spnHEntradaViernes.setModel(model);
		spnHEntradaViernes.setEditor(new JSpinner.DateEditor(spnHEntradaViernes, "hh:mm a"));
		spnHEntradaViernes.setValue(now);
		tjpnlHorarios.add(spnHEntradaViernes);
		
		model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		
		spnHEntradaSabado = new JSpinner();
		spnHEntradaSabado.setEnabled(false);
		spnHEntradaSabado.setBounds(542, 110, 80, 26);
		spnHEntradaSabado.setModel(model);
		spnHEntradaSabado.setEditor(new JSpinner.DateEditor(spnHEntradaSabado, "hh:mm a"));
		spnHEntradaSabado.setValue(now);
		tjpnlHorarios.add(spnHEntradaSabado);
		
		model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		
		spnHSalidaSabado = new JSpinner();
		spnHSalidaSabado.setEnabled(false);
		spnHSalidaSabado.setBounds(542, 160, 80, 26);
		spnHSalidaSabado.setModel(model);
		spnHSalidaSabado.setEditor(new JSpinner.DateEditor(spnHSalidaSabado, "hh:mm a"));
		spnHSalidaSabado.setValue(now);
		tjpnlHorarios.add(spnHSalidaSabado);
		
		model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		
		spnHSalidaComidaSabado = new JSpinner();
		spnHSalidaComidaSabado.setEnabled(false);
		spnHSalidaComidaSabado.setBounds(542, 240, 80, 26);
		spnHSalidaComidaSabado.setModel(model);
		spnHSalidaComidaSabado.setEditor(new JSpinner.DateEditor(spnHSalidaComidaSabado, "hh:mm a"));
		spnHSalidaComidaSabado.setValue(now);
		tjpnlHorarios.add(spnHSalidaComidaSabado);
		
		model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		
		spnHEntradaComidaSabado = new JSpinner();
		spnHEntradaComidaSabado.setEnabled(false);
		spnHEntradaComidaSabado.setBounds(542, 290, 80, 26);
		spnHEntradaComidaSabado.setModel(model);
		spnHEntradaComidaSabado.setEditor(new JSpinner.DateEditor(spnHEntradaComidaSabado, "hh:mm a"));
		spnHEntradaComidaSabado.setValue(now);
		tjpnlHorarios.add(spnHEntradaComidaSabado);
		
		model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		
		spnHEntradaDomingo = new JSpinner();
		spnHEntradaDomingo.setEnabled(false);
		spnHEntradaDomingo.setBounds(645, 110, 80, 26);
		spnHEntradaDomingo.setModel(model);
		spnHEntradaDomingo.setEditor(new JSpinner.DateEditor(spnHEntradaDomingo, "hh:mm a"));
		spnHEntradaDomingo.setValue(now);
		tjpnlHorarios.add(spnHEntradaDomingo);
		
		model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		
		spnHSalidaDomingo = new JSpinner();
		spnHSalidaDomingo.setEnabled(false);
		spnHSalidaDomingo.setBounds(645, 160, 80, 26);
		spnHSalidaDomingo.setModel(model);
		spnHSalidaDomingo.setEditor(new JSpinner.DateEditor(spnHSalidaDomingo, "hh:mm a"));
		spnHSalidaDomingo.setValue(now);
		tjpnlHorarios.add(spnHSalidaDomingo);
		
		model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		
		spnHSalidaComidaDomingo = new JSpinner();
		spnHSalidaComidaDomingo.setEnabled(false);
		spnHSalidaComidaDomingo.setBounds(645, 240, 80, 26);
		spnHSalidaComidaDomingo.setModel(model);
		spnHSalidaComidaDomingo.setEditor(new JSpinner.DateEditor(spnHSalidaComidaDomingo, "hh:mm a"));
		spnHSalidaComidaDomingo.setValue(now);
		tjpnlHorarios.add(spnHSalidaComidaDomingo);
		
		model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		
		spnHEntradaComidaDomingo = new JSpinner();
		spnHEntradaComidaDomingo.setEnabled(false);
		spnHEntradaComidaDomingo.setBounds(645, 290, 80, 26);
		spnHEntradaComidaDomingo.setModel(model);
		spnHEntradaComidaDomingo.setEditor(new JSpinner.DateEditor(spnHEntradaComidaDomingo, "hh:mm a"));
		spnHEntradaComidaDomingo.setValue(now);
		tjpnlHorarios.add(spnHEntradaComidaDomingo);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(820, 423, 100, 70);
		getContentPane().add(btnEliminar);
		
		btnNuevoTurno = new JButton("Nuevo");
		btnNuevoTurno.setEnabled(false);
		btnNuevoTurno.setBounds(820, 177, 100, 70);
		getContentPane().add(btnNuevoTurno);
		
		btnGuardarEditar = new JButton("Guardar");
		btnGuardarEditar.setEnabled(false);
		btnGuardarEditar.setBounds(820, 304, 100, 70);
		getContentPane().add(btnGuardarEditar);
	}

	public JTextField getTxtNombreTurno() {
		return txtNombreTurno;
	}

	public void setTxtNombreTurno(JTextField txtNombreTurno) {
		this.txtNombreTurno = txtNombreTurno;
	}

	public JComboBox getCmbArea() {
		return cmbArea;
	}

	public void setCmbArea(JComboBox cmbArea) {
		this.cmbArea = cmbArea;
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

	public JSpinner getSpnHoraSalidaG() {
		return spnHoraSalidaG;
	}

	public void setSpnHoraSalidaG(JSpinner spnHoraSalidaG) {
		this.spnHoraSalidaG = spnHoraSalidaG;
	}

	public JSpinner getSpnHoraEntradaG() {
		return spnHoraEntradaG;
	}

	public void setSpnHoraEntradaG(JSpinner spnHoraEntradaG) {
		this.spnHoraEntradaG = spnHoraEntradaG;
	}

	public JSpinner getSpnHoraSalidaComidaG() {
		return spnHoraSalidaComidaG;
	}

	public void setSpnHoraSalidaComidaG(JSpinner spnHoraSalidaComidaG) {
		this.spnHoraSalidaComidaG = spnHoraSalidaComidaG;
	}

	public JSpinner getSpnHoraEntradaComidaG() {
		return spnHoraEntradaComidaG;
	}

	public void setSpnHoraEntradaComidaG(JSpinner spnHoraEntradaComidaG) {
		this.spnHoraEntradaComidaG = spnHoraEntradaComidaG;
	}

	public JPanel getTjpnlHorarios() {
		return tjpnlHorarios;
	}

	public void setTjpnlHorarios(JPanel tjpnlHorarios) {
		this.tjpnlHorarios = tjpnlHorarios;
	}

	public JLabel getLblHEntradaLunes() {
		return lblHEntradaLunes;
	}

	public void setLblHEntradaLunes(JLabel lblHEntradaLunes) {
		this.lblHEntradaLunes = lblHEntradaLunes;
	}

	public JLabel getLblHSalidaLunes() {
		return lblHSalidaLunes;
	}

	public void setLblHSalidaLunes(JLabel lblHSalidaLunes) {
		this.lblHSalidaLunes = lblHSalidaLunes;
	}

	public JLabel getLblHoraSalidaComerLunes() {
		return lblHoraSalidaComerLunes;
	}

	public void setLblHoraSalidaComerLunes(JLabel lblHoraSalidaComerLunes) {
		this.lblHoraSalidaComerLunes = lblHoraSalidaComerLunes;
	}

	public JLabel getLblHEntradaComidaLunes() {
		return lblHEntradaComidaLunes;
	}

	public void setLblHEntradaComidaLunes(JLabel lblHEntradaComidaLunes) {
		this.lblHEntradaComidaLunes = lblHEntradaComidaLunes;
	}

	public JLabel getLblHEntradaMartes() {
		return lblHEntradaMartes;
	}

	public void setLblHEntradaMartes(JLabel lblHEntradaMartes) {
		this.lblHEntradaMartes = lblHEntradaMartes;
	}

	public JLabel getLblHSalidaMartes() {
		return lblHSalidaMartes;
	}

	public void setLblHSalidaMartes(JLabel lblHSalidaMartes) {
		this.lblHSalidaMartes = lblHSalidaMartes;
	}

	public JLabel getLblHoraSalidaComerMartes() {
		return lblHoraSalidaComerMartes;
	}

	public void setLblHoraSalidaComerMartes(JLabel lblHoraSalidaComerMartes) {
		this.lblHoraSalidaComerMartes = lblHoraSalidaComerMartes;
	}

	public JLabel getLblHEntradaComidaMartes() {
		return lblHEntradaComidaMartes;
	}

	public void setLblHEntradaComidaMartes(JLabel lblHEntradaComidaMartes) {
		this.lblHEntradaComidaMartes = lblHEntradaComidaMartes;
	}

	public JLabel getLblHEntradaComidaMiercoles() {
		return lblHEntradaComidaMiercoles;
	}

	public void setLblHEntradaComidaMiercoles(JLabel lblHEntradaComidaMiercoles) {
		this.lblHEntradaComidaMiercoles = lblHEntradaComidaMiercoles;
	}

	public JLabel getLblHoraSalidaComerMiercoles() {
		return lblHoraSalidaComerMiercoles;
	}

	public void setLblHoraSalidaComerMiercoles(JLabel lblHoraSalidaComerMiercoles) {
		this.lblHoraSalidaComerMiercoles = lblHoraSalidaComerMiercoles;
	}

	public JLabel getLblHSalidaMiercoles() {
		return lblHSalidaMiercoles;
	}

	public void setLblHSalidaMiercoles(JLabel lblHSalidaMiercoles) {
		this.lblHSalidaMiercoles = lblHSalidaMiercoles;
	}

	public JLabel getLblHEntradaMiercoles() {
		return lblHEntradaMiercoles;
	}

	public void setLblHEntradaMiercoles(JLabel lblHEntradaMiercoles) {
		this.lblHEntradaMiercoles = lblHEntradaMiercoles;
	}

	public JLabel getLblHEntradaJueves() {
		return lblHEntradaJueves;
	}

	public void setLblHEntradaJueves(JLabel lblHEntradaJueves) {
		this.lblHEntradaJueves = lblHEntradaJueves;
	}

	public JLabel getLblHSalidaJueves() {
		return lblHSalidaJueves;
	}

	public void setLblHSalidaJueves(JLabel lblHSalidaJueves) {
		this.lblHSalidaJueves = lblHSalidaJueves;
	}

	public JLabel getLblHoraSalidaComerJueves() {
		return lblHoraSalidaComerJueves;
	}

	public void setLblHoraSalidaComerJueves(JLabel lblHoraSalidaComerJueves) {
		this.lblHoraSalidaComerJueves = lblHoraSalidaComerJueves;
	}

	public JLabel getLblHEntradaComidaJueves() {
		return lblHEntradaComidaJueves;
	}

	public void setLblHEntradaComidaJueves(JLabel lblHEntradaComidaJueves) {
		this.lblHEntradaComidaJueves = lblHEntradaComidaJueves;
	}

	public JLabel getLblHEntradaViernes() {
		return lblHEntradaViernes;
	}

	public void setLblHEntradaViernes(JLabel lblHEntradaViernes) {
		this.lblHEntradaViernes = lblHEntradaViernes;
	}

	public JLabel getLblHSalidaViernes() {
		return lblHSalidaViernes;
	}

	public void setLblHSalidaViernes(JLabel lblHSalidaViernes) {
		this.lblHSalidaViernes = lblHSalidaViernes;
	}

	public JLabel getLblHoraSalidaComerViernes() {
		return lblHoraSalidaComerViernes;
	}

	public void setLblHoraSalidaComerViernes(JLabel lblHoraSalidaComerViernes) {
		this.lblHoraSalidaComerViernes = lblHoraSalidaComerViernes;
	}

	public JLabel getLblHEntradaComidaViernes() {
		return lblHEntradaComidaViernes;
	}

	public void setLblHEntradaComidaViernes(JLabel lblHEntradaComidaViernes) {
		this.lblHEntradaComidaViernes = lblHEntradaComidaViernes;
	}

	public JLabel getLblHEntradaSabado() {
		return lblHEntradaSabado;
	}

	public void setLblHEntradaSabado(JLabel lblHEntradaSabado) {
		this.lblHEntradaSabado = lblHEntradaSabado;
	}

	public JLabel getLblHSalidaSabado() {
		return lblHSalidaSabado;
	}

	public void setLblHSalidaSabado(JLabel lblHSalidaSabado) {
		this.lblHSalidaSabado = lblHSalidaSabado;
	}

	public JLabel getLblHoraSalidaComerSabado() {
		return lblHoraSalidaComerSabado;
	}

	public void setLblHoraSalidaComerSabado(JLabel lblHoraSalidaComerSabado) {
		this.lblHoraSalidaComerSabado = lblHoraSalidaComerSabado;
	}

	public JLabel getLblHEntradaComidaSabado() {
		return lblHEntradaComidaSabado;
	}

	public void setLblHEntradaComidaSabado(JLabel lblHEntradaComidaSabado) {
		this.lblHEntradaComidaSabado = lblHEntradaComidaSabado;
	}

	public JLabel getLblHEntradaComidaDomingo() {
		return lblHEntradaComidaDomingo;
	}

	public void setLblHEntradaComidaDomingo(JLabel lblHEntradaComidaDomingo) {
		this.lblHEntradaComidaDomingo = lblHEntradaComidaDomingo;
	}

	public JLabel getLblHoraSalidaComerDomingo() {
		return lblHoraSalidaComerDomingo;
	}

	public void setLblHoraSalidaComerDomingo(JLabel lblHoraSalidaComerDomingo) {
		this.lblHoraSalidaComerDomingo = lblHoraSalidaComerDomingo;
	}

	public JLabel getLblHSalidaDomingo() {
		return lblHSalidaDomingo;
	}

	public void setLblHSalidaDomingo(JLabel lblHSalidaDomingo) {
		this.lblHSalidaDomingo = lblHSalidaDomingo;
	}

	public JLabel getLblHEntradaDomingo() {
		return lblHEntradaDomingo;
	}

	public void setLblHEntradaDomingo(JLabel lblHEntradaDomingo) {
		this.lblHEntradaDomingo = lblHEntradaDomingo;
	}

	public JSpinner getSpnMinutosRetardo() {
		return spnMinutosRetardo;
	}

	public void setSpnMinutosRetardo(JSpinner spnMinutosRetardo) {
		this.spnMinutosRetardo = spnMinutosRetardo;
	}

	public JSpinner getSpnMinutosFalta() {
		return spnMinutosFalta;
	}

	public void setSpnMinutosFalta(JSpinner spnMinutosFalta) {
		this.spnMinutosFalta = spnMinutosFalta;
	}
	
	public void reiniciarControles(){
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
		
		chkComidaDomingo.setEnabled(false);
		chkComidaSabado.setEnabled(false);
		chkComidaViernes.setEnabled(false);
		chkComidaJueves.setEnabled(false);
		chkComidaMiercoles.setEnabled(false);
		chkComidaMartes.setEnabled(false);
		chkComidaLunes.setEnabled(false);
		
		spnHEntradaLunes.setValue(now);
		spnHSalidaLunes.setValue(now);
		spnHSalidaComidaLunes.setValue(now);
		spnHEntradaComidaLunes.setValue(now);
		spnHSalidaMartes.setValue(now);
		spnHSalidaComidaMartes.setValue(now);
		spnHEntradaComidaMartes.setValue(now);
		spnHEntradaMiercoles.setValue(now);
		spnHSalidaMiercoles.setValue(now);
		spnHSalidaComidaMiercoles.setValue(now);
		spnHEntradaComidaMiercoles.setValue(now);
		spnHEntradaJueves.setValue(now);
		spnHSalidaJueves.setValue(now);
		spnHSalidaComidaJueves.setValue(now);
		spnHEntradaComidaJueves.setValue(now);
		spnHEntradaComidaViernes.setValue(now);
		spnHSalidaComidaViernes.setValue(now);
		spnHSalidaViernes.setValue(now);
		spnHEntradaViernes.setValue(now);
		spnHEntradaSabado.setValue(now);
		spnHSalidaSabado.setValue(now);
		spnHSalidaComidaSabado.setValue(now);
		spnHEntradaComidaSabado.setValue(now);
		spnHEntradaDomingo.setValue(now);
		spnHSalidaDomingo.setValue(now);
		spnHSalidaComidaDomingo.setValue(now);
		spnHEntradaComidaDomingo.setValue(now);
		spnHEntradaMartes.setValue(now);
		
		spnHEntradaLunes.setEnabled(false);
		spnHSalidaLunes.setEnabled(false);
		spnHSalidaComidaLunes.setEnabled(false);
		spnHEntradaComidaLunes.setEnabled(false);
		spnHSalidaMartes.setEnabled(false);
		spnHSalidaComidaMartes.setEnabled(false);
		spnHEntradaComidaMartes.setEnabled(false);
		spnHEntradaMiercoles.setEnabled(false);
		spnHSalidaMiercoles.setEnabled(false);
		spnHSalidaComidaMiercoles.setEnabled(false);
		spnHEntradaComidaMiercoles.setEnabled(false);
		spnHEntradaJueves.setEnabled(false);
		spnHSalidaJueves.setEnabled(false);
		spnHSalidaComidaJueves.setEnabled(false);
		spnHEntradaComidaJueves.setEnabled(false);
		spnHEntradaComidaViernes.setEnabled(false);
		spnHSalidaComidaViernes.setEnabled(false);
		spnHSalidaViernes.setEnabled(false);
		spnHEntradaViernes.setEnabled(false);
		spnHEntradaSabado.setEnabled(false);
		spnHSalidaSabado.setEnabled(false);
		spnHSalidaComidaSabado.setEnabled(false);
		spnHEntradaComidaSabado.setEnabled(false);
		spnHEntradaDomingo.setEnabled(false);
		spnHSalidaDomingo.setEnabled(false);
		spnHSalidaComidaDomingo.setEnabled(false);
		spnHEntradaComidaDomingo.setEnabled(false);
		spnHEntradaMartes.setEnabled(false);
		
		chkHorarioGeneral.setSelected(false);
		
		spnHoraSalidaG.setValue(now);
		spnHoraEntradaG.setValue(now);
		spnHoraSalidaComidaG.setValue(now);
		spnHoraEntradaComidaG.setValue(now);
		
		spnHoraSalidaG.setEnabled(false);
		spnHoraEntradaG.setEnabled(false);
		spnHoraSalidaComidaG.setEnabled(false);
		spnHoraEntradaComidaG.setEnabled(false);
		
		txtNombreTurno.setText(null);
	}

	public void limpiarVentana(){
		spnMinutosRetardo.setValue(0);
		spnMinutosFalta.setValue(0);
		txtNombreTurno.setText(null);
		
		spnMinutosRetardo.setEnabled(false);
		spnMinutosFalta.setEnabled(false);
		txtNombreTurno.setEnabled(false);
		
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
		
		chkComidaDomingo.setEnabled(false);
		chkComidaSabado.setEnabled(false);
		chkComidaViernes.setEnabled(false);
		chkComidaJueves.setEnabled(false);
		chkComidaMiercoles.setEnabled(false);
		chkComidaMartes.setEnabled(false);
		chkComidaLunes.setEnabled(false);
		
		spnHEntradaLunes.setValue(now);
		spnHSalidaLunes.setValue(now);
		spnHSalidaComidaLunes.setValue(now);
		spnHEntradaComidaLunes.setValue(now);
		spnHSalidaMartes.setValue(now);
		spnHSalidaComidaMartes.setValue(now);
		spnHEntradaComidaMartes.setValue(now);
		spnHEntradaMiercoles.setValue(now);
		spnHSalidaMiercoles.setValue(now);
		spnHSalidaComidaMiercoles.setValue(now);
		spnHEntradaComidaMiercoles.setValue(now);
		spnHEntradaJueves.setValue(now);
		spnHSalidaJueves.setValue(now);
		spnHSalidaComidaJueves.setValue(now);
		spnHEntradaComidaJueves.setValue(now);
		spnHEntradaComidaViernes.setValue(now);
		spnHSalidaComidaViernes.setValue(now);
		spnHSalidaViernes.setValue(now);
		spnHEntradaViernes.setValue(now);
		spnHEntradaSabado.setValue(now);
		spnHSalidaSabado.setValue(now);
		spnHSalidaComidaSabado.setValue(now);
		spnHEntradaComidaSabado.setValue(now);
		spnHEntradaDomingo.setValue(now);
		spnHSalidaDomingo.setValue(now);
		spnHSalidaComidaDomingo.setValue(now);
		spnHEntradaComidaDomingo.setValue(now);
		spnHEntradaMartes.setValue(now);
		
		spnHEntradaLunes.setEnabled(false);
		spnHSalidaLunes.setEnabled(false);
		spnHSalidaComidaLunes.setEnabled(false);
		spnHEntradaComidaLunes.setEnabled(false);
		spnHSalidaMartes.setEnabled(false);
		spnHSalidaComidaMartes.setEnabled(false);
		spnHEntradaComidaMartes.setEnabled(false);
		spnHEntradaMiercoles.setEnabled(false);
		spnHSalidaMiercoles.setEnabled(false);
		spnHSalidaComidaMiercoles.setEnabled(false);
		spnHEntradaComidaMiercoles.setEnabled(false);
		spnHEntradaJueves.setEnabled(false);
		spnHSalidaJueves.setEnabled(false);
		spnHSalidaComidaJueves.setEnabled(false);
		spnHEntradaComidaJueves.setEnabled(false);
		spnHEntradaComidaViernes.setEnabled(false);
		spnHSalidaComidaViernes.setEnabled(false);
		spnHSalidaViernes.setEnabled(false);
		spnHEntradaViernes.setEnabled(false);
		spnHEntradaSabado.setEnabled(false);
		spnHSalidaSabado.setEnabled(false);
		spnHSalidaComidaSabado.setEnabled(false);
		spnHEntradaComidaSabado.setEnabled(false);
		spnHEntradaDomingo.setEnabled(false);
		spnHSalidaDomingo.setEnabled(false);
		spnHSalidaComidaDomingo.setEnabled(false);
		spnHEntradaComidaDomingo.setEnabled(false);
		spnHEntradaMartes.setEnabled(false);
		
		tabbedPane.setVisible(false);
		
		btnNuevoTurno.setEnabled(false);
		btnGuardarEditar.setEnabled(false);
		btnEliminar.setEnabled(false);
		
		chkHorarioGeneral.setSelected(false);
		chkHorarioGeneral.setEnabled(false);
		
		spnHoraSalidaG.setValue(now);
		spnHoraEntradaG.setValue(now);
		spnHoraSalidaComidaG.setValue(now);
		spnHoraEntradaComidaG.setValue(now);
		
		spnHoraSalidaG.setEnabled(false);
		spnHoraEntradaG.setEnabled(false);
		spnHoraSalidaComidaG.setEnabled(false);
		spnHoraEntradaComidaG.setEnabled(false);
		
		int c = tabbedPane.getTabCount();
		
		for (int i = 1; i < c; i++) {
			tabbedPane.remove(1);
		}
		
	}
}
