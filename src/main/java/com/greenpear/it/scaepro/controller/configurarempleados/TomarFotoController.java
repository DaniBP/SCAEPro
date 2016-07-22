package com.greenpear.it.scaepro.controller.configurarempleados;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.Thread.UncaughtExceptionHandler;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamDiscoveryEvent;
import com.github.sarxos.webcam.WebcamDiscoveryListener;
import com.github.sarxos.webcam.WebcamEvent;
import com.github.sarxos.webcam.WebcamListener;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamPicker;
import com.github.sarxos.webcam.WebcamResolution;
import com.greenpear.it.scaepro.bo.configurarempleados.ConfigurarEmpleadosBo;
import com.greenpear.it.scaepro.dao.configurarempleados.ConfigurarEmpleadoDao;
import com.greenpear.it.scaepro.model.empleado.EmpleadoModel;
import com.greenpear.it.scaepro.model.turno.TurnoModel;
import com.greenpear.it.scaepro.view.configurarempleados.RegistrarEmpleado;


/**
 * 
 * @author RyuuZangetsu
 */
@Controller
public class TomarFotoController extends JFrame implements Runnable, WebcamListener, WindowListener, UncaughtExceptionHandler, ItemListener, WebcamDiscoveryListener, ActionListener {
	
	// Modelos
//	private EmpleadoModel configurarEmpleadosModel=new EmpleadoModel();
	// Controladores
	@Autowired
	private ConfigurarEmpleadosController empleadosController;
	
//	// Vistas
//	@Autowired
//		private RegistrarEmpleado registrarEmpleadoView;
	// Bo
	
//		private ConfigurarEmpleadoDao empleadoDao = new ConfigurarEmpleadoDao();
	
//	private static final long serialVersionUID = 1L;
	private Webcam webcam = null;
	private WebcamPanel panel = null;
	private WebcamPicker picker = null;
	private JButton boton=new JButton("Tomar fotografia");
	
	@Override
	public void run() {

		Webcam.addDiscoveryListener(this);

		setTitle("Tomar fotografia de empleado");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());

		addWindowListener(this);

		picker = new WebcamPicker();
		picker.addItemListener(this);

		webcam = picker.getSelectedWebcam();

		if (webcam == null) {
			System.out.println("No se encontro ninguna camara");
			System.exit(1);
		}

		webcam.setViewSize(WebcamResolution.VGA.getSize());
		webcam.addWebcamListener(TomarFotoController.this);

		panel = new WebcamPanel(webcam, false);
		panel.setFPSDisplayed(true);

		add(picker, BorderLayout.NORTH);
		add(panel, BorderLayout.CENTER);
		add(boton, BorderLayout.SOUTH);
		
		boton.addActionListener(this);
		pack();
		setVisible(true);

		Thread t = new Thread() {

			@Override
			public void run() {
				panel.start();
			}
		};
		t.setName("Empezando");
		t.setDaemon(true);
		t.setUncaughtExceptionHandler(this);
		t.start();
	}

	public void iniciar() {
		SwingUtilities.invokeLater(new TomarFotoController());
	}

	@Override
	public void webcamOpen(WebcamEvent we) {
		System.out.println("Camara abierta");
	}

	@Override
	public void webcamClosed(WebcamEvent we) {
		System.out.println("Camara cerrada");
	}

	@Override
	public void webcamDisposed(WebcamEvent we) {
		System.out.println("Camara destruida XD");
	}

	@Override
	public void webcamImageObtained(WebcamEvent we) {
		// do nothing
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
		webcam.close();
	}

	@Override
	public void windowClosing(WindowEvent e) {
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		System.out.println("Resume de camara web");
		panel.resume();
	}

	@Override
	public void windowIconified(WindowEvent e) {
		System.out.println("Camara pausada");
		panel.pause();
	}

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.err.println(String.format("Exception in thread %s", t.getName()));
		e.printStackTrace();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getItem() != webcam) {
			if (webcam != null) {

				panel.stop();

				remove(panel);

				webcam.removeWebcamListener(this);
				webcam.close();

				webcam = (Webcam) e.getItem();
				webcam.setViewSize(WebcamResolution.VGA.getSize());
				webcam.addWebcamListener(this);

				System.out.println("Seleccionada  " + webcam.getName());

				panel = new WebcamPanel(webcam, false);
				panel.setFPSDisplayed(true);

				getContentPane().add(panel, BorderLayout.CENTER);
				pack();

				Thread t = new Thread() {

					@Override
					public void run() {
						panel.start();
					}
				};
				t.setName("Detenido");
				t.setDaemon(true);
				t.setUncaughtExceptionHandler(this);
				t.start();
			}
		}
	}

	@Override
	public void webcamFound(WebcamDiscoveryEvent event) {
		if (picker != null) {
			picker.addItem(event.getWebcam());
		}
	}

	@Override
	public void webcamGone(WebcamDiscoveryEvent event) {
		if (picker != null) {
			picker.removeItem(event.getWebcam());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(boton)){
			int idEmpleado=getEmpleadosController().consultarIdEmpleado();
			idEmpleado=idEmpleado+1;
			String idEmple=Integer.toString(idEmpleado);
			idEmple=validarNombre(idEmple);
			// get image
			BufferedImage image = webcam.getImage();
			// save image to PNG file
			try {
				ImageIO.write(image, "PNG", new File("src/main/resources/img/fotosempleados/"+idEmple));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.dispose();
			pegarFotoRegistro(idEmple);
		}
	}

	private void pegarFotoRegistro(String idEmpleado) {
		ImageIcon fotografia=new ImageIcon("src/main/resources/img/fotosempleados/"+idEmpleado);
		Icon icono=new ImageIcon(fotografia.getImage().getScaledInstance(getEmpleadosController().getRegistrarEmpleadoView().getLblFotografia().getWidth(),getEmpleadosController().getRegistrarEmpleadoView().getLblFotografia().getHeight() , Image.SCALE_DEFAULT));
		empleadosController.getRegistrarEmpleadoView().getLblFotografia().setIcon(icono);
		empleadosController.getConfigurarEmpleadosModel().setFotografia(idEmpleado);
	}

	private String validarNombre(String idEmpleado) {
		if(idEmpleado.length()==1){
			idEmpleado="Ft000000"+idEmpleado+".png";
		}else if(idEmpleado.length()==2){
			idEmpleado="Ft00000"+idEmpleado+".png";
		}else if(idEmpleado.length()==3){
			idEmpleado="Ft0000"+idEmpleado+".png";
		}else if(idEmpleado.length()==4){
			idEmpleado="Ft000"+idEmpleado+".png";
		}else if(idEmpleado.length()==5){
			idEmpleado="Ft00"+idEmpleado+".png";
		}else if(idEmpleado.length()==6){
			idEmpleado="Ft0"+idEmpleado+".png";
		}else if(idEmpleado.length()==7){
			idEmpleado="Ft"+idEmpleado+".png";
		}
		return idEmpleado;
	}

	//Setter and Getter

	public ConfigurarEmpleadosController getEmpleadosController() {
		return empleadosController;
	}	
}
