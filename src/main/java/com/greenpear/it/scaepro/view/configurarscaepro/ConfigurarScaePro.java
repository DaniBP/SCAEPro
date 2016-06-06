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

public class ConfigurarScaePro extends JFrame {
	private JTextField txtMinutosRetardo;
	private JTextField txtMinutosFalta;
	private JTextField txtHoraEntradG;
	private JTextField txtHoraSalidaG;
	private JTextField txtHoraSalidaComidaG;
	private JTextField txtHoraEntradaComidaG;
	private JTextField textField;
	private JTextField txtHEntradaLunes;
	private JTextField txtHSalidaLunes;
	private JTextField txtHSalidaComidaLunes;
	private JTextField txtHEntradaComidaLunes;
	private JTextField txtHEntradaMartes;
	private JTextField txtHSalidaMartes;
	private JTextField txtHSalidaComidaMartes;
	private JTextField txtHEntradaComidaMartes;
	private JTextField txtHEntradaMiercoles;
	private JTextField txtHSalidaMiercoles;
	private JTextField txtHSalidaComidaMiercoles;
	private JTextField txtHEntradaComidaMiercoles;
	private JTextField txtHEntradaJueves;
	private JTextField txtHSalidaJueves;
	private JTextField txtHSalidaComidaJueves;
	private JTextField txtHEntradaComidaJueves;
	private JTextField txtHEntradaViernes;
	private JTextField txtHSalidaViernes;
	private JTextField txtHSalidaComidaViernes;
	private JTextField txtHEntradaComidaViernes;
	private JTextField txtHEntradaSabado;
	private JTextField txtHSalidaSabado;
	private JTextField txtHSalidaComidaSabado;
	private JTextField txtHEntradaComidaSabado;
	private JTextField txtHEntradaDomingo;
	private JTextField txtHSalidaDomingo;
	private JTextField txtHSalidaComidaDomingo;
	private JTextField txtHEntradaComidaDomingo;
	public ConfigurarScaePro() {
		getContentPane().setLayout(null);
		getContentPane().setSize(980, 570);
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
		txtMinutosRetardo.setBounds(213, 65, 130, 26);
		panel.add(txtMinutosRetardo);
		txtMinutosRetardo.setColumns(10);
		
		txtMinutosFalta = new JTextField();
		txtMinutosFalta.setEnabled(false);
		txtMinutosFalta.setColumns(10);
		txtMinutosFalta.setBounds(213, 95, 130, 26);
		panel.add(txtMinutosFalta);
		
		JLabel lblHoraEntradaG = new JLabel("Hora de entrada:");
		lblHoraEntradaG.setEnabled(false);
		lblHoraEntradaG.setBounds(355, 70, 147, 16);
		panel.add(lblHoraEntradaG);
		
		JLabel lblHoraDeSalidaG = new JLabel("Hora de salida");
		lblHoraDeSalidaG.setEnabled(false);
		lblHoraDeSalidaG.setBounds(355, 100, 147, 16);
		panel.add(lblHoraDeSalidaG);
		
		txtHoraEntradG = new JTextField();
		txtHoraEntradG.setEnabled(false);
		txtHoraEntradG.setColumns(10);
		txtHoraEntradG.setBounds(476, 65, 130, 26);
		panel.add(txtHoraEntradG);
		
		txtHoraSalidaG = new JTextField();
		txtHoraSalidaG.setEnabled(false);
		txtHoraSalidaG.setColumns(10);
		txtHoraSalidaG.setBounds(476, 95, 130, 26);
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
		txtHoraSalidaComidaG.setBounds(795, 65, 130, 26);
		panel.add(txtHoraSalidaComidaG);
		
		txtHoraEntradaComidaG = new JTextField();
		txtHoraEntradaComidaG.setEnabled(false);
		txtHoraEntradaComidaG.setColumns(10);
		txtHoraEntradaComidaG.setBounds(795, 95, 130, 26);
		panel.add(txtHoraEntradaComidaG);
		
		JComboBox cmbArea = new JComboBox();
		cmbArea.setModel(new DefaultComboBoxModel(new String[] {"---------------Seleccione un area---------------"}));
		cmbArea.setBounds(199, 35, 422, 27);
		panel.add(cmbArea);
		
		JCheckBox chkHorarioGeneral = new JCheckBox("Aplicar un horario para el area en general");
		chkHorarioGeneral.setBounds(636, 35, 300, 23);
		panel.add(chkHorarioGeneral);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new TitledBorder(null, "Configuracion de horarios:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tabbedPane.setBounds(10, 141, 788, 401);
		getContentPane().add(tabbedPane);
		
		JPanel tjpnlHorarios = new JPanel();
		tabbedPane.addTab("Turno 1", null, tjpnlHorarios, null);
		tjpnlHorarios.setLayout(null);
		
		JLabel lblNombreTurno = new JLabel("Nombre del turno:");
		lblNombreTurno.setBounds(17, 21, 120, 16);
		tjpnlHorarios.add(lblNombreTurno);
		
		textField = new JTextField();
		textField.setBounds(146, 16, 286, 26);
		tjpnlHorarios.add(textField);
		textField.setColumns(10);
		
		JCheckBox chckbxLunes = new JCheckBox("Lunes");
		chckbxLunes.setBounds(20, 60, 80, 23);
		tjpnlHorarios.add(chckbxLunes);
		
		JLabel lblHEntradaLunes = new JLabel("H.Entrada:");
		lblHEntradaLunes.setBounds(20, 90, 80, 16);
		tjpnlHorarios.add(lblHEntradaLunes);
		
		txtHEntradaLunes = new JTextField();
		txtHEntradaLunes.setBounds(20, 110, 80, 26);
		tjpnlHorarios.add(txtHEntradaLunes);
		txtHEntradaLunes.setColumns(10);
		
		JLabel lblHSalidaLunes = new JLabel("H.Salida:");
		lblHSalidaLunes.setBounds(20, 140, 80, 16);
		tjpnlHorarios.add(lblHSalidaLunes);
		
		txtHSalidaLunes = new JTextField();
		txtHSalidaLunes.setColumns(10);
		txtHSalidaLunes.setBounds(20, 160, 80, 26);
		tjpnlHorarios.add(txtHSalidaLunes);
		
		JCheckBox chkComidaLunes = new JCheckBox("Comida");
		chkComidaLunes.setBounds(20, 190, 80, 23);
		tjpnlHorarios.add(chkComidaLunes);
		
		JLabel lblHoraSalidaComerLunes = new JLabel("H.Salida:");
		lblHoraSalidaComerLunes.setBounds(20, 220, 80, 16);
		tjpnlHorarios.add(lblHoraSalidaComerLunes);
		
		txtHSalidaComidaLunes = new JTextField();
		txtHSalidaComidaLunes.setColumns(10);
		txtHSalidaComidaLunes.setBounds(20, 240, 80, 26);
		tjpnlHorarios.add(txtHSalidaComidaLunes);
		
		JLabel lblHEntradaComidaLunes = new JLabel("H.Entrada:");
		lblHEntradaComidaLunes.setBounds(20, 270, 80, 16);
		tjpnlHorarios.add(lblHEntradaComidaLunes);
		
		txtHEntradaComidaLunes = new JTextField();
		txtHEntradaComidaLunes.setColumns(10);
		txtHEntradaComidaLunes.setBounds(20, 290, 80, 26);
		tjpnlHorarios.add(txtHEntradaComidaLunes);
		
		JCheckBox chkMartes = new JCheckBox("Martes");
		chkMartes.setBounds(122, 60, 80, 23);
		tjpnlHorarios.add(chkMartes);
		
		JLabel lblHEntradaMartes = new JLabel("H.Entrada:");
		lblHEntradaMartes.setBounds(122, 90, 80, 16);
		tjpnlHorarios.add(lblHEntradaMartes);
		
		txtHEntradaMartes = new JTextField();
		txtHEntradaMartes.setColumns(10);
		txtHEntradaMartes.setBounds(122, 110, 80, 26);
		tjpnlHorarios.add(txtHEntradaMartes);
		
		JLabel lblHSalidaMartes = new JLabel("H.Salida:");
		lblHSalidaMartes.setBounds(122, 140, 80, 16);
		tjpnlHorarios.add(lblHSalidaMartes);
		
		txtHSalidaMartes = new JTextField();
		txtHSalidaMartes.setColumns(10);
		txtHSalidaMartes.setBounds(122, 160, 80, 26);
		tjpnlHorarios.add(txtHSalidaMartes);
		
		JCheckBox chkComidaMartes = new JCheckBox("Comida");
		chkComidaMartes.setBounds(122, 190, 80, 23);
		tjpnlHorarios.add(chkComidaMartes);
		
		JLabel lblHoraSalidaComerMartes = new JLabel("H.Salida:");
		lblHoraSalidaComerMartes.setBounds(122, 220, 80, 16);
		tjpnlHorarios.add(lblHoraSalidaComerMartes);
		
		txtHSalidaComidaMartes = new JTextField();
		txtHSalidaComidaMartes.setColumns(10);
		txtHSalidaComidaMartes.setBounds(122, 240, 80, 26);
		tjpnlHorarios.add(txtHSalidaComidaMartes);
		
		JLabel lblHEntradaComidaMartes = new JLabel("H.Entrada:");
		lblHEntradaComidaMartes.setBounds(122, 270, 80, 16);
		tjpnlHorarios.add(lblHEntradaComidaMartes);
		
		txtHEntradaComidaMartes = new JTextField();
		txtHEntradaComidaMartes.setColumns(10);
		txtHEntradaComidaMartes.setBounds(122, 290, 80, 26);
		tjpnlHorarios.add(txtHEntradaComidaMartes);
		
		JCheckBox chkMiercoles = new JCheckBox("Miercoles");
		chkMiercoles.setBounds(225, 60, 100, 23);
		tjpnlHorarios.add(chkMiercoles);
		
		JLabel lblHEntradaMiercoles = new JLabel("H.Entrada:");
		lblHEntradaMiercoles.setBounds(225, 90, 80, 16);
		tjpnlHorarios.add(lblHEntradaMiercoles);
		
		txtHEntradaMiercoles = new JTextField();
		txtHEntradaMiercoles.setColumns(10);
		txtHEntradaMiercoles.setBounds(225, 110, 80, 26);
		tjpnlHorarios.add(txtHEntradaMiercoles);
		
		JLabel lblHSalidaMiercoles = new JLabel("H.Salida:");
		lblHSalidaMiercoles.setBounds(225, 140, 80, 16);
		tjpnlHorarios.add(lblHSalidaMiercoles);
		
		txtHSalidaMiercoles = new JTextField();
		txtHSalidaMiercoles.setColumns(10);
		txtHSalidaMiercoles.setBounds(225, 160, 80, 26);
		tjpnlHorarios.add(txtHSalidaMiercoles);
		
		JCheckBox chkComidaMiercoles = new JCheckBox("Comida");
		chkComidaMiercoles.setBounds(225, 190, 80, 23);
		tjpnlHorarios.add(chkComidaMiercoles);
		
		JLabel lblHoraSalidaComerMiercoles = new JLabel("H.Salida:");
		lblHoraSalidaComerMiercoles.setBounds(225, 220, 80, 16);
		tjpnlHorarios.add(lblHoraSalidaComerMiercoles);
		
		txtHSalidaComidaMiercoles = new JTextField();
		txtHSalidaComidaMiercoles.setColumns(10);
		txtHSalidaComidaMiercoles.setBounds(225, 240, 80, 26);
		tjpnlHorarios.add(txtHSalidaComidaMiercoles);
		
		JLabel lblHEntradaComidaMiercoles = new JLabel("H.Entrada:");
		lblHEntradaComidaMiercoles.setBounds(225, 270, 80, 16);
		tjpnlHorarios.add(lblHEntradaComidaMiercoles);
		
		txtHEntradaComidaMiercoles = new JTextField();
		txtHEntradaComidaMiercoles.setColumns(10);
		txtHEntradaComidaMiercoles.setBounds(225, 290, 80, 26);
		tjpnlHorarios.add(txtHEntradaComidaMiercoles);
		
		JCheckBox chkJueves = new JCheckBox("Jueves");
		chkJueves.setBounds(330, 60, 80, 23);
		tjpnlHorarios.add(chkJueves);
		
		JLabel lblHEntradaJueves = new JLabel("H.Entrada:");
		lblHEntradaJueves.setBounds(330, 90, 80, 16);
		tjpnlHorarios.add(lblHEntradaJueves);
		
		txtHEntradaJueves = new JTextField();
		txtHEntradaJueves.setColumns(10);
		txtHEntradaJueves.setBounds(330, 110, 80, 26);
		tjpnlHorarios.add(txtHEntradaJueves);
		
		JLabel lblHSalidaJueves = new JLabel("H.Salida:");
		lblHSalidaJueves.setBounds(330, 140, 80, 16);
		tjpnlHorarios.add(lblHSalidaJueves);
		
		txtHSalidaJueves = new JTextField();
		txtHSalidaJueves.setColumns(10);
		txtHSalidaJueves.setBounds(330, 160, 80, 26);
		tjpnlHorarios.add(txtHSalidaJueves);
		
		JCheckBox chkComidaJueves = new JCheckBox("Comida");
		chkComidaJueves.setBounds(330, 190, 80, 23);
		tjpnlHorarios.add(chkComidaJueves);
		
		JLabel lblHoraSalidaComerJueves = new JLabel("H.Salida:");
		lblHoraSalidaComerJueves.setBounds(330, 220, 80, 16);
		tjpnlHorarios.add(lblHoraSalidaComerJueves);
		
		txtHSalidaComidaJueves = new JTextField();
		txtHSalidaComidaJueves.setColumns(10);
		txtHSalidaComidaJueves.setBounds(330, 240, 80, 26);
		tjpnlHorarios.add(txtHSalidaComidaJueves);
		
		JLabel lblHEntradaComidaJueves = new JLabel("H.Entrada:");
		lblHEntradaComidaJueves.setBounds(330, 270, 80, 16);
		tjpnlHorarios.add(lblHEntradaComidaJueves);
		
		txtHEntradaComidaJueves = new JTextField();
		txtHEntradaComidaJueves.setColumns(10);
		txtHEntradaComidaJueves.setBounds(330, 290, 80, 26);
		tjpnlHorarios.add(txtHEntradaComidaJueves);
		
		JCheckBox chkViernes = new JCheckBox("Viernes");
		chkViernes.setBounds(438, 60, 80, 23);
		tjpnlHorarios.add(chkViernes);
		
		JLabel lblHEntradaViernes = new JLabel("H.Entrada:");
		lblHEntradaViernes.setBounds(438, 90, 80, 16);
		tjpnlHorarios.add(lblHEntradaViernes);
		
		txtHEntradaViernes = new JTextField();
		txtHEntradaViernes.setColumns(10);
		txtHEntradaViernes.setBounds(438, 110, 80, 26);
		tjpnlHorarios.add(txtHEntradaViernes);
		
		JLabel lblHSalidaViernes = new JLabel("H.Salida:");
		lblHSalidaViernes.setBounds(438, 140, 80, 16);
		tjpnlHorarios.add(lblHSalidaViernes);
		
		txtHSalidaViernes = new JTextField();
		txtHSalidaViernes.setColumns(10);
		txtHSalidaViernes.setBounds(438, 160, 80, 26);
		tjpnlHorarios.add(txtHSalidaViernes);
		
		JCheckBox chkComidaViernes = new JCheckBox("Comida");
		chkComidaViernes.setBounds(438, 190, 80, 23);
		tjpnlHorarios.add(chkComidaViernes);
		
		JLabel lblHoraSalidaComerViernes = new JLabel("H.Salida:");
		lblHoraSalidaComerViernes.setBounds(438, 220, 80, 16);
		tjpnlHorarios.add(lblHoraSalidaComerViernes);
		
		txtHSalidaComidaViernes = new JTextField();
		txtHSalidaComidaViernes.setColumns(10);
		txtHSalidaComidaViernes.setBounds(438, 240, 80, 26);
		tjpnlHorarios.add(txtHSalidaComidaViernes);
		
		JLabel lblHEntradaComidaViernes = new JLabel("H.Entrada:");
		lblHEntradaComidaViernes.setBounds(438, 270, 80, 16);
		tjpnlHorarios.add(lblHEntradaComidaViernes);
		
		txtHEntradaComidaViernes = new JTextField();
		txtHEntradaComidaViernes.setColumns(10);
		txtHEntradaComidaViernes.setBounds(438, 290, 80, 26);
		tjpnlHorarios.add(txtHEntradaComidaViernes);
		
		JCheckBox chkSabado = new JCheckBox("Sabado");
		chkSabado.setBounds(542, 60, 80, 23);
		tjpnlHorarios.add(chkSabado);
		
		JLabel lblHEntradaSabado = new JLabel("H.Entrada:");
		lblHEntradaSabado.setBounds(542, 90, 80, 16);
		tjpnlHorarios.add(lblHEntradaSabado);
		
		txtHEntradaSabado = new JTextField();
		txtHEntradaSabado.setColumns(10);
		txtHEntradaSabado.setBounds(542, 110, 80, 26);
		tjpnlHorarios.add(txtHEntradaSabado);
		
		JLabel lblHSalidaSabado = new JLabel("H.Salida:");
		lblHSalidaSabado.setBounds(542, 140, 80, 16);
		tjpnlHorarios.add(lblHSalidaSabado);
		
		txtHSalidaSabado = new JTextField();
		txtHSalidaSabado.setColumns(10);
		txtHSalidaSabado.setBounds(542, 160, 80, 26);
		tjpnlHorarios.add(txtHSalidaSabado);
		
		JCheckBox chkComidaSabado = new JCheckBox("Comida");
		chkComidaSabado.setBounds(542, 190, 80, 23);
		tjpnlHorarios.add(chkComidaSabado);
		
		JLabel lblHoraSalidaComerSabado = new JLabel("H.Salida:");
		lblHoraSalidaComerSabado.setBounds(542, 220, 80, 16);
		tjpnlHorarios.add(lblHoraSalidaComerSabado);
		
		txtHSalidaComidaSabado = new JTextField();
		txtHSalidaComidaSabado.setColumns(10);
		txtHSalidaComidaSabado.setBounds(542, 240, 80, 26);
		tjpnlHorarios.add(txtHSalidaComidaSabado);
		
		JLabel lblHEntradaComidaSabado = new JLabel("H.Entrada:");
		lblHEntradaComidaSabado.setBounds(542, 270, 80, 16);
		tjpnlHorarios.add(lblHEntradaComidaSabado);
		
		txtHEntradaComidaSabado = new JTextField();
		txtHEntradaComidaSabado.setColumns(10);
		txtHEntradaComidaSabado.setBounds(542, 290, 80, 26);
		tjpnlHorarios.add(txtHEntradaComidaSabado);
		
		JCheckBox chkDomingo = new JCheckBox("Domingo");
		chkDomingo.setBounds(645, 60, 90, 23);
		tjpnlHorarios.add(chkDomingo);
		
		JLabel lblHEntradaDomingo = new JLabel("H.Entrada:");
		lblHEntradaDomingo.setBounds(645, 90, 80, 16);
		tjpnlHorarios.add(lblHEntradaDomingo);
		
		txtHEntradaDomingo = new JTextField();
		txtHEntradaDomingo.setColumns(10);
		txtHEntradaDomingo.setBounds(645, 110, 80, 26);
		tjpnlHorarios.add(txtHEntradaDomingo);
		
		JLabel lblHSalidaDomingo = new JLabel("H.Salida:");
		lblHSalidaDomingo.setBounds(645, 140, 80, 16);
		tjpnlHorarios.add(lblHSalidaDomingo);
		
		txtHSalidaDomingo = new JTextField();
		txtHSalidaDomingo.setColumns(10);
		txtHSalidaDomingo.setBounds(645, 160, 80, 26);
		tjpnlHorarios.add(txtHSalidaDomingo);
		
		JCheckBox chkComidaDomingo = new JCheckBox("Comida");
		chkComidaDomingo.setBounds(645, 190, 80, 23);
		tjpnlHorarios.add(chkComidaDomingo);
		
		JLabel lblHoraSalidaComerDomingo = new JLabel("H.Salida:");
		lblHoraSalidaComerDomingo.setBounds(645, 220, 80, 16);
		tjpnlHorarios.add(lblHoraSalidaComerDomingo);
		
		txtHSalidaComidaDomingo = new JTextField();
		txtHSalidaComidaDomingo.setColumns(10);
		txtHSalidaComidaDomingo.setBounds(645, 240, 80, 26);
		tjpnlHorarios.add(txtHSalidaComidaDomingo);
		
		JLabel lblHEntradaComidaDomingo = new JLabel("H.Entrada:");
		lblHEntradaComidaDomingo.setBounds(645, 270, 80, 16);
		tjpnlHorarios.add(lblHEntradaComidaDomingo);
		
		txtHEntradaComidaDomingo = new JTextField();
		txtHEntradaComidaDomingo.setColumns(10);
		txtHEntradaComidaDomingo.setBounds(645, 290, 80, 26);
		tjpnlHorarios.add(txtHEntradaComidaDomingo);
		
		JButton btnEditar = new JButton("");
		btnEditar.setIcon(new ImageIcon(ConfigurarScaePro.class.getResource("/img/Text-Edit-icon.png")));
		btnEditar.setBounds(850, 423, 70, 70);
		getContentPane().add(btnEditar);
		
		JButton btnGuardarTurno = new JButton("");
		btnGuardarTurno.setIcon(new ImageIcon(ConfigurarScaePro.class.getResource("/img/save.png")));
		btnGuardarTurno.setBounds(850, 177, 70, 70);
		getContentPane().add(btnGuardarTurno);
		
		JButton btnAñadir = new JButton("");
		btnAñadir.setIcon(new ImageIcon(ConfigurarScaePro.class.getResource("/img/math-add-icon.png")));
		btnAñadir.setBounds(850, 304, 70, 70);
		getContentPane().add(btnAñadir);
		super.setSize(980,570);
		super.setTitle("Configurar Scae Pro");
		super.setResizable(false);
	}
}
