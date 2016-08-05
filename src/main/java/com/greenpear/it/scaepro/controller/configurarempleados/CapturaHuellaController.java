package com.greenpear.it.scaepro.controller.configurarempleados;



import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;

import com.digitalpersona.onetouch.capture.event.DPFPDataAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPErrorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPErrorEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPSensorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;

import javax.swing.border.TitledBorder;

import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CapturaHuellaController extends JFrame {
	
	@Autowired
	private ConfigurarEmpleadosController empleadosController;

	public ConfigurarEmpleadosController getEmpleadosController() {
		return empleadosController;
	}

	//Varible que permite iniciar el dispositivo de lector de huella conectado
	// con sus distintos metodos.
	private DPFPCapture Lector = DPFPGlobal.getCaptureFactory().createCapture();

	//Varible que permite establecer las capturas de la huellas, para determina sus caracteristicas
	// y poder estimar la creacion de un template de la huella para luego poder guardarla
	private DPFPEnrollment Reclutador = DPFPGlobal.getEnrollmentFactory().createEnrollment();

	//Esta variable tambien captura una huella del lector y crea sus caracteristcas para auntetificarla
	// o verificarla con alguna guardada en la BD
	private DPFPVerification Verificador = DPFPGlobal.getVerificationFactory().createVerification();

	//Variable que para crear el template de la huella luego de que se hallan creado las caracteriticas
	// necesarias de la huella si no ha ocurrido ningun problema
	private DPFPTemplate template;
	public static String TEMPLATE_PROPERTY = "template";
	
	public DPFPFeatureSet featuresinscripcion;
	public DPFPFeatureSet featuresverificacion;
	
	private JPanel contentPane;
	private JPanel panHuellas;
	private JLabel lblImagenHuella;
	private JButton btnGuardar;
	private JTextArea txtArea;
	private JButton btnSalir;
	private Image image;

	/**
	 * Create the frame.
	 */
	public CapturaHuellaController() {
		setTitle("Huellas");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				Iniciar();
				start();
				EstadoHuellas();
				btnGuardar.setEnabled(false);
				btnSalir.grabFocus();
			}
			@Override
			public void windowClosing(WindowEvent e) {
				stop();
			}
		});
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null, "Imposible modificar el tema visual", 
//			"Lookandfeel inválido.",
//			JOptionPane.ERROR_MESSAGE);
//		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 485, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panHuellas = new JPanel();
		panHuellas.setBounds(5, 5, 457, 275);
		panHuellas.setBorder(new TitledBorder(null, "Huella Digital Capturada", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panHuellas);
		panHuellas.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panHuellas.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		lblImagenHuella = new JLabel("");
		panel.add(lblImagenHuella, BorderLayout.CENTER);
		
		JPanel panBtns = new JPanel();
		panBtns.setBounds(5, 291, 457, 158);
		panBtns.setBorder(new TitledBorder(null, "Acciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panBtns);
		panBtns.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(6, 11, 441, 41);
		panBtns.add(panel_1);
		panel_1.setLayout(null);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnSalir.setBounds(209, 11, 89, 23);
		panel_1.add(btnSalir);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardarHuella();
				Reclutador.clear();
				lblImagenHuella.setIcon(null);
				start();
			}
		});
		btnGuardar.setBounds(110, 11, 89, 23);
		panel_1.add(btnGuardar);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(6, 59, 441, 88);
		panBtns.add(panel_2);
		panel_2.setLayout(null);
		
		txtArea = new JTextArea();
		
		JScrollPane scrollPane = new JScrollPane(txtArea);
		scrollPane.setBounds(0, 0, 441, 88);
		panel_2.add(scrollPane);
		
		txtArea.setEditable(false);
	}
	
	
	
	/**
	 * @return the template
	 */
	public DPFPTemplate getTemplate() {
		return template;
	}

	/**
	 * @param template the template to set
	 */
	public void setTemplate(DPFPTemplate template) {
		DPFPTemplate old = this.template;
		this.template = template;
		firePropertyChange(TEMPLATE_PROPERTY, old, template);
	}

	protected void Iniciar(){
		   Lector.addDataListener(new DPFPDataAdapter() {
		    @Override public void dataAcquired(final DPFPDataEvent e) {
		    SwingUtilities.invokeLater(new Runnable() {	public void run() {
		    EnviarTexto("La Huella Digital ha sido Capturada");
		    ProcesarCaptura(e.getSample());
		    }});}
		   });

		   Lector.addReaderStatusListener(new DPFPReaderStatusAdapter() {
		    @Override public void readerConnected(final DPFPReaderStatusEvent e) {
		    SwingUtilities.invokeLater(new Runnable() {	public void run() {
		    EnviarTexto("El Sensor de Huella Digital esta Activado");
		    }});}
		    @Override public void readerDisconnected(final DPFPReaderStatusEvent e) {
		    SwingUtilities.invokeLater(new Runnable() {	public void run() {
		    EnviarTexto("El Sensor de Huella Digital esta Desactivado");
		    }});}
		   });

		   Lector.addSensorListener(new DPFPSensorAdapter() {
		    @Override public void fingerTouched(final DPFPSensorEvent e) {
		    SwingUtilities.invokeLater(new Runnable() {	public void run() {
		    EnviarTexto("El dedo ha sido colocado sobre el Lector");
		    }});}
		    @Override public void fingerGone(final DPFPSensorEvent e) {
		    SwingUtilities.invokeLater(new Runnable() {	public void run() {
		    EnviarTexto("El dedo ha sido quitado del Lector");
		    }});}
		   });

		   Lector.addErrorListener(new DPFPErrorAdapter(){
		    public void errorReader(final DPFPErrorEvent e){
		    SwingUtilities.invokeLater(new Runnable() {  public void run() {
		    EnviarTexto("Error: "+e.getError());
		    }});}
		   });
		}

	public  void ProcesarCaptura(DPFPSample sample){
	
		// Procesar la muestra de la huella y crear un conjunto de características con el propósito de inscripción.
		featuresinscripcion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);
		
		// Procesar la muestra de la huella y crear un conjunto de características con el propósito de verificacion.
		featuresverificacion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);
		
		// Comprobar la calidad de la muestra de la huella y lo añade a su reclutador si es bueno
		if (featuresinscripcion != null)
		try{
			System.out.println("Las Caracteristicas de la Huella han sido creada");
			Reclutador.addFeatures(featuresinscripcion);// Agregar las caracteristicas de la huella a la plantilla a crear
			
			// Dibuja la huella dactilar capturada.
			Image image=CrearImagenHuella(sample);
			DibujarHuella(image);
			
			}catch (DPFPImageQualityException ex) {
			System.err.println("Error: "+ex.getMessage());
			}

		finally {
			EstadoHuellas();
			// Comprueba si la plantilla se ha creado.
			switch(Reclutador.getTemplateStatus()){
	            case TEMPLATE_STATUS_READY:	// informe de éxito y detiene  la captura de huellas
	            	stop();
	            	setTemplate(Reclutador.getTemplate());
	            	EnviarTexto("La Plantilla de la Huella ha Sido Creada,\n ya es posible guardarla");
	            	btnGuardar.setEnabled(true);
	            	btnGuardar.grabFocus();
	            	break;

		    case TEMPLATE_STATUS_FAILED: // informe de fallas y reiniciar la captura de huellas
		    	Reclutador.clear();
	            stop();
	            EstadoHuellas();
	            setTemplate(null);
	            JOptionPane.showMessageDialog(CapturaHuellaController.this, "La Plantilla de la Huella no pudo ser creada, Repita el Proceso", "Inscripcion de Huellas Dactilares", JOptionPane.ERROR_MESSAGE);
	            start();
		    break;
			}
	     }
	}
	
	public  DPFPFeatureSet extraerCaracteristicas(DPFPSample sample, DPFPDataPurpose purpose){
	     DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
	     try {
	      return extractor.createFeatureSet(sample, purpose);
	     } catch (DPFPImageQualityException e) {
	      return null;
	     }
	}
	
	public  Image CrearImagenHuella(DPFPSample sample) {
		return DPFPGlobal.getSampleConversionFactory().createImage(sample);
	}

	public void DibujarHuella(Image image) {
		this.image=image;
		lblImagenHuella.setIcon(new ImageIcon(
		image.getScaledInstance(lblImagenHuella.getWidth(), lblImagenHuella.getHeight(), Image.SCALE_DEFAULT)));
		repaint();
	}

	public  void EstadoHuellas(){
		EnviarTexto("Coloque el dedo "+ Reclutador.getFeaturesNeeded()+" veces mas para guardar la huella");
	}
	
	public void EnviarTexto(String string) {
        txtArea.append(string + "\n");
	}
	
	public void start(){
		Lector.startCapture();
		EnviarTexto("Utilizando el Lector de Huella Dactilar ");
	}
	
	public void stop(){
		Lector.stopCapture();
		EnviarTexto("No se está usando el Lector de Huella Dactilar ");
	} 
	
	
	public void guardarHuella(){
		getEmpleadosController().getConfigurarEmpleadosModel().setDatosHuella(new ByteArrayInputStream(template.serialize()));
		getEmpleadosController().getConfigurarEmpleadosModel().setTamanoHuella(template.serialize().length);
		
		getEmpleadosController().getRegistrarEmpleadoView().getLblHuellaDigital().setIcon(new ImageIcon(
				image.getScaledInstance(getEmpleadosController().getRegistrarEmpleadoView().getLblHuellaDigital().getWidth(),
						getEmpleadosController().getRegistrarEmpleadoView().getLblHuellaDigital().getHeight(), 
						Image.SCALE_DEFAULT)));
		
		btnGuardar.setEnabled(false);
		setVisible(false);
	}
	
}
