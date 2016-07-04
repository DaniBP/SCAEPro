package com.greenpear.it.scaepro.controller.controlacceso;

import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.DPFPDataAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPErrorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPErrorEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPSensorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;
import com.greenpear.it.scaepro.model.empleado.EmpleadoModel;
import com.greenpear.it.scaepro.view.accesosistema.PrincipalView;

/**
 * 
 * @author DanielBP
 *
 */
public class LectorHuellasController {
	
	@Autowired
	private ControlAccesoController accesoController;
	
	public LectorHuellasController() {
		super();
	}

	//Varible que permite iniciar el dispositivo de lector de huella conectado
	// con sus distintos metodos.
	private DPFPCapture Lector = DPFPGlobal.getCaptureFactory().createCapture();

	//Esta variable captura una huella del lector y crea sus caracteristcas para auntetificarla
	// o verificarla con alguna guardada en la BD
	private DPFPVerification Verificador = DPFPGlobal.getVerificationFactory().createVerification();

	//Variable que para crear el template de la huella luego de que se hallan creado las caracteriticas
	// necesarias de la huella si no ha ocurrido ningun problema
	private DPFPTemplate Template;
	public static String TEMPLATE_PROPERTY = "template";
		
	public DPFPFeatureSet featuresverificacion;
	
	/**
	 * @return the template
	 */
	public DPFPTemplate getTemplate() {
		return Template;
	}

	/**
	 * @param template the template to set
	 */
	public void setTemplate(DPFPTemplate template) {
		this.Template = template;
	}
	
	/**
	 * Método para inicializar los eventos del lector
	 */
	protected void Iniciar(){
		
		Lector.addDataListener(new DPFPDataAdapter() {
			@Override public void dataAcquired(final DPFPDataEvent e) {
				SwingUtilities.invokeLater(new Runnable() {	public void run() {
					System.out.println("La Huella Digital ha sido Capturada");
					ProcesarCaptura(e.getSample());
					verificarHuella();
				}});
			}
		});
	
		Lector.addReaderStatusListener(new DPFPReaderStatusAdapter() {
			@Override public void readerConnected(final DPFPReaderStatusEvent e) {
				SwingUtilities.invokeLater(new Runnable() {	public void run() {
					System.out.println("El Sensor de Huella Digital esta Activado o Conectado");
				}});
			}
			@Override public void readerDisconnected(final DPFPReaderStatusEvent e) {
				SwingUtilities.invokeLater(new Runnable() {	public void run() {
					System.out.println("El Sensor de Huella Digital esta Desactivado o no Conectado");
				}});
			}
		});
	
		Lector.addSensorListener(new DPFPSensorAdapter() {
			@Override public void fingerTouched(final DPFPSensorEvent e) {
				SwingUtilities.invokeLater(new Runnable() {	public void run() {
					System.out.println("El dedo ha sido colocado sobre el Lector de Huella");
			    }});
			}
		    @Override public void fingerGone(final DPFPSensorEvent e) {
			    SwingUtilities.invokeLater(new Runnable() {	public void run() {
			    	System.out.println("El dedo ha sido quitado del Lector de Huella");
			    }});
		    }
		});
	
		Lector.addErrorListener(new DPFPErrorAdapter(){
		    public void errorReader(final DPFPErrorEvent e){
		    	SwingUtilities.invokeLater(new Runnable() {  public void run() {
		    		System.out.println("Error: "+e.getError());
			    }});
	    	}
		});
	}
	
	public void ProcesarCaptura(DPFPSample sample){
		
		ImageIcon identificando = new ImageIcon(PrincipalView.class.getResource("/img/Identificando.gif"));
		Icon iconoIde = new ImageIcon(identificando.getImage().getScaledInstance(
				accesoController.getControlAccesoView().imgEstado.getWidth(), accesoController.getControlAccesoView().imgEstado.getHeight(), Image.SCALE_DEFAULT));
		
		accesoController.getControlAccesoView().imgEstado.setIcon(iconoIde);
		
		// Procesar la muestra de la huella y crear un conjunto de características con el propósito de verificacion.
		featuresverificacion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);
		
		System.out.println("Las Caracteristicas de la Huella han sido creada");
		
		
		// Dibuja la huella dactilar capturada.
		Image image=CrearImagenHuella(sample);
		ImageIcon exito = new ImageIcon(image);
		Icon iconoExito = new ImageIcon(exito.getImage().getScaledInstance(
				accesoController.getControlAccesoView().imgEstado.getWidth(), accesoController.getControlAccesoView().imgEstado.getHeight(), Image.SCALE_DEFAULT));
		
		accesoController.getControlAccesoView().imgEstado.setIcon(iconoExito);
//			DibujarHuella(image);
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
	
	public void start(){
		Lector.startCapture();
		System.out.println("Utilizando el Lector de Huella Dactilar ");
	}
	
	public void stop(){
		Lector.stopCapture();
		System.out.println("No se está usando el Lector de Huella Dactilar ");
	} 
	

	public void verificarHuella() {
		try {
	       List<EmpleadoModel> empleados = new ArrayList<EmpleadoModel>();
	       
	       empleados=accesoController.verificarHuella();
	       
	       if(!empleados.isEmpty()){
	    	   for (int i = 0; i < empleados.size(); i++) {
	    		   	byte templateBuffer[] = empleados.get(i).getHuellaEmpleado();
	    	   		if(templateBuffer!=null){
		    	   		//Crea una nueva plantilla a partir de la guardada en la base de datos
				        DPFPTemplate referenceTemplate = DPFPGlobal.getTemplateFactory().createTemplate(templateBuffer);
				        //Envia la plantilla creada al objeto contendor de Template del componente de huella digital
				        setTemplate(referenceTemplate);
				        
				        // Compara las caracteriticas de la huella recientemente capturda con la
				        // plantilla guardada del empleado en la base de datos
				        DPFPVerificationResult result = Verificador.verify(featuresverificacion, getTemplate());
				        
				        if (result.isVerified()){
				        	accesoController.chequeo(empleados.get(i).getNipEmpleado());
				        	return;
				        }
		    	   	}
    	   		
    	   		ImageIcon error = new ImageIcon(PrincipalView.class.getResource("/img/Error.png"));
				Icon iconoError = new ImageIcon(error.getImage().getScaledInstance(
						accesoController.getControlAccesoView().imgEstado.getWidth(), accesoController.getControlAccesoView().imgEstado.getHeight(), Image.SCALE_DEFAULT));
				
				accesoController.getControlAccesoView().imgEstado.setIcon(iconoError);
				accesoController.esperar();
	    	   }
	       }else{
	    	   	ImageIcon error = new ImageIcon(PrincipalView.class.getResource("/img/Error.png"));
				Icon iconoError = new ImageIcon(error.getImage().getScaledInstance(
						accesoController.getControlAccesoView().imgEstado.getWidth(), accesoController.getControlAccesoView().imgEstado.getHeight(), Image.SCALE_DEFAULT));
				
				accesoController.getControlAccesoView().imgEstado.setIcon(iconoError);
				accesoController.esperar();
	       }
        } catch (SQLException e) {
	        //Si ocurre un error lo indica en la consola
	        System.err.println("Error al verificar los datos de la huella.");
        }
	}
}
