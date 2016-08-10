/**
 * 
 */
package com.greenpear.it.scaepro.controller.administracionmovil;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.springframework.beans.factory.annotation.Autowired;

import com.greenpear.it.scaepro.bo.administracionmovil.NuevaNoticiaBo;
import com.greenpear.it.scaepro.controller.government.GovernmentService;
import com.greenpear.it.scaepro.model.administracionmovil.GenerarAvisoModel;
import com.greenpear.it.scaepro.model.administracionmovil.NoticiasModel;
import com.greenpear.it.scaepro.view.administracionmovil.NuevaNoticiaView;

/**
 * @author EDSONJOSUE
 *
 */
public class NuevaNoticiaController implements ActionListener, KeyListener {
	private FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.JPG", "jpg");
	private long start;
	private long end;
	private String archive;
	private String nombreArchivo;
	// ********ESTANCIAS***********************************
	@Autowired
	private GovernmentService government;

	@Autowired
	private NuevaNoticiaView vista;

	@Autowired
	private NuevaNoticiaBo bo;

	@Autowired
	private NoticiasModel modelo;

	public GovernmentService getGovernment() {
		return government;
	}

	public NuevaNoticiaView getVista() {
		return vista;
	}

	public NuevaNoticiaBo getBo() {
		return bo;
	}

	public NoticiasModel getModelo() {
		return modelo;
	}
	// ***********FIN DE ESTANCIAS****************

	public void mostrarNuevaNoticia() {
		if (getVista().btnRegistrar.getActionListeners().length == 0) {
			getVista().btnRegistrar.addActionListener(this);
			getVista().btnEliminar.addActionListener(this);
			getVista().btnSubirFoto.addActionListener(this);
			getVista().txtTitulo.addKeyListener(this);
			getVista().txtDescNoticia.addKeyListener(this);
		}
		getVista().setVisible(true);
		getVista().txtTitulo.setText("");
		getVista().txtDescNoticia.setText("");
		getVista().btnRegistrar.setText("Registrar");
		getVista().btnRegistrar.setEnabled(false);
		getVista().btnSubirFoto.setText("Seleccionar imagen...");
		getVista().lblImg.setIcon(null);
		getVista().setTitle(getModelo().getNombreVentana());
		getVista().btnRegistrar.setBounds(101, 385, 168, 32);
		getVista().btnEliminar.setVisible(false);
		obtenerFechaActual();
		actualizarAvisos();
		getVista().toFront();
		editarNoticia();
	}
	private void obtenerFechaActual() {
		Calendar calendar = new GregorianCalendar();
		String dia = Integer.toString(calendar.get(Calendar.DATE));
		String mes = Integer.toString(calendar.get(Calendar.MONTH) + 1);
		String annio = Integer.toString(calendar.get(Calendar.YEAR));
		getVista().lblFecha.setText(annio + "/" + mes + "/" + dia);
	}
	
	private void actualizarAvisos() {
		List<NoticiasModel> fechasPagos = new ArrayList<NoticiasModel>();
		try {
			fechasPagos = getBo().consultaFechas();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		Iterator<NoticiasModel> itrFechasPagos = fechasPagos.iterator();
		while (itrFechasPagos.hasNext()) {
			NoticiasModel fechasAvisosModel = itrFechasPagos.next();
			// Dar formato a la fecha pagada consultada
			String formato = "yyyy-MM-dd";
			SimpleDateFormat formatoFecha = new SimpleDateFormat(formato);
			String strFecha = fechasAvisosModel.getFechaNoticia();
			Date fechaDate = null;
			try {
				fechaDate = formatoFecha.parse(strFecha);
			} catch (ParseException ex) {
				ex.printStackTrace();
			}
			String fechaPago = new SimpleDateFormat("dd/MM/yyyy").format(fechaDate);
//			System.out.println("fechaPago:" + fechaPago);
//			System.out.println(restar_fecha(fechaPago));
			
			if(restar_fecha(fechaPago).equals("Si")){
				String eliminarAviso = null;
				try {
					eliminarAviso = getBo().eliminar(fechasAvisosModel);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	public String restar_fecha(String fechaPago) {
		String fechaInicio = fechaPago;
		String actualiza = null;
		String[] aFechaIng = fechaInicio.split("/");
		
		Integer diaPago = Integer.parseInt(aFechaIng[0]);
		Integer mesPago = Integer.parseInt(aFechaIng[1]);
		Integer anioPago = Integer.parseInt(aFechaIng[2]);

		int dias = 0;
		
		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al dï¿½a 
		java.util.Date hoy = new Date(); //Fecha de hoy 
		
		int anio = anioPago; int mes = mesPago; int dia = diaPago; //Fecha anterior 
		Calendar calendar = new GregorianCalendar(anio, mes-1, dia); 
		java.sql.Date fecha = new java.sql.Date(calendar.getTimeInMillis());

		long diferencia = ( hoy.getTime() - fecha.getTime() )/MILLSECS_PER_DAY; 
//		System.out.println("Dï¿½as transcurridos: "+diferencia); 
		
		dias = (int) (diferencia);

			if (dias >= 15) {
				actualiza = "Si";
			}else{
				actualiza = "No";
			}
		return actualiza;
	}

	private void editarNoticia() {
		if (getModelo().getNombreVentana() == "Detalle Noticia") {
			getVista().txtTitulo.setText(getModelo().getTituloNoticia());
			getVista().txtDescNoticia.setText(getModelo().getDescNoticia());
			getVista().btnSubirFoto.setText(getModelo().getImagenNoticia());
			getVista().btnRegistrar.setText("Editar");
			getVista().btnRegistrar.setEnabled(false);
			getVista().btnRegistrar.setBounds(26, 388, 139, 32);
			getVista().setTitle(getModelo().getNombreVentana());
			getVista().btnEliminar.setVisible(true);
			ImageIcon icono = new ImageIcon(
					"src/main/resources/img/imgsNoticias/"
							+ getModelo().getImagenNoticia());
			Image img = icono.getImage();
			Image nuevaImg = img.getScaledInstance(213, 102, java.awt.Image.SCALE_SMOOTH);
			ImageIcon newIcono = new ImageIcon(nuevaImg);
			getVista().lblImg.setIcon(newIcono);
		}
	}

	@Autowired
	private static void copyFileUsingJava7Files(File source, File dest) throws IOException {
		Files.copy(source.toPath(), dest.toPath());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getSource() == getVista().txtTitulo) {
			getVista().btnRegistrar.setEnabled(true);
		} else if (e.getSource() == getVista().txtDescNoticia) {
			getVista().btnRegistrar.setEnabled(true);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// ------Registrar--------
		if (e.getSource() == getVista().btnRegistrar && getVista().btnRegistrar.getText() == "Registrar") {
			if (getVista().txtTitulo.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Ingrese Un Título De La Noticia", "Nueva Noticia",
						JOptionPane.WARNING_MESSAGE);
				return;
			} else if (getVista().txtDescNoticia.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Ingrese Una Descripción De La Noticia", "Nueva Noticia",
						JOptionPane.WARNING_MESSAGE);
				return;
			} else if (getVista().btnSubirFoto.getText() == "Seleccionar imagen...") {
				JOptionPane.showMessageDialog(null, "Ingrese Una Imágen De La Noticia", "Nueva Noticia",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			// Dar formato a la fecha actual
			String formato2 = "yyyy/MM/dd";
			SimpleDateFormat formatoFecha2 = new SimpleDateFormat(formato2);
			String strFechaActual = getVista().lblFecha.getText();
			Date fechaDate2 = null;
			try {
				fechaDate2 = formatoFecha2.parse(strFechaActual);
			} catch (ParseException ex) {
				ex.printStackTrace();
			}
			String fechaActual = new SimpleDateFormat("yyyy-MM-dd").format(fechaDate2);
//			System.out.println("fechaActual:" + fechaActual);
			String fechaNoticia = fechaActual;

			getModelo().setTituloNoticia(getVista().txtTitulo.getText());
			getModelo().setDescNoticia(getVista().txtDescNoticia.getText());
			getModelo().setFechaNoticia(fechaNoticia);
			
			String registro = null;

			try {
				registro = getBo().insertar(getModelo());
			} catch (SQLException t) {
				JOptionPane.showMessageDialog(null, t.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (registro.equals("!Registro De Noticia Exitoso!")) {
//				JOptionPane.showMessageDialog(null, getModelo().getIdNoticia(), "Acceso", JOptionPane.INFORMATION_MESSAGE);
				JOptionPane.showMessageDialog(null, registro, "Acceso", JOptionPane.INFORMATION_MESSAGE);
				// copy file using Java 7 Files class
				File source = new File(archive);
				File dest = new File(
						"src/main/resources/img/imgsNoticias/"
								+ "imagen"+getModelo().getIdNoticia()+".jpg");
				start = System.nanoTime();
					try {
						copyFileUsingJava7Files(source, dest);
					} catch (IOException e1) {
//						JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//						return;
					}
				end = System.nanoTime();
				getVista().txtTitulo.setText("");
				getVista().txtDescNoticia.setText("");
				getVista().btnSubirFoto.setText("Seleccionar imagen...");
				getVista().lblImg.setIcon(null);
			} else {
				JOptionPane.showMessageDialog(null, registro, "Acceso", JOptionPane.INFORMATION_MESSAGE);
			}
			return;
		}
		if (e.getSource() == getVista().btnSubirFoto) {
			getVista().btnRegistrar.setEnabled(true);
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File("C:/Users/EDSONJOSUE/Pictures"));
			fileChooser.setFileFilter(filtro);
			fileChooser.setMultiSelectionEnabled(false);
			int opcion = fileChooser.showOpenDialog(getVista().getContentPane());
			if (opcion == JFileChooser.APPROVE_OPTION) {
				archive = fileChooser.getSelectedFile().getPath();
				nombreArchivo = fileChooser.getSelectedFile().getName().toString();
				getVista().lblImg.setIcon(new ImageIcon(archive));
				ImageIcon icono = new ImageIcon(archive);
				Image img = icono.getImage();
				Image nuevaImg = img.getScaledInstance(213, 102, java.awt.Image.SCALE_SMOOTH);
				ImageIcon newIcono = new ImageIcon(nuevaImg);
				getVista().lblImg.setIcon(newIcono);
				getVista().lblImg.setSize(204, 102);
				getVista().btnSubirFoto.setText(nombreArchivo);
			}
			// ------Editar--------
		} else if (e.getSource() == getVista().btnRegistrar && getVista().btnRegistrar.getText().equals("Editar")) {
			if (getVista().txtTitulo.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Ingrese un título de noticia", "Editar Noticia",
						JOptionPane.WARNING_MESSAGE);
				return;
			} else if (getVista().txtDescNoticia.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Ingrese la noticia", "Editar Noticia",
						JOptionPane.WARNING_MESSAGE);
				return;
			} else if (getVista().lblImg.getIcon().getIconWidth() <= 0) {
				JOptionPane.showMessageDialog(null, "Ingrese Una Imágen De La Noticia", "Nueva Noticia",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			// Dar formato a la fecha actual
						String formato2 = "yyyy/MM/dd";
						SimpleDateFormat formatoFecha2 = new SimpleDateFormat(formato2);
						String strFechaActual = getVista().lblFecha.getText();
						Date fechaDate2 = null;
						try {
							fechaDate2 = formatoFecha2.parse(strFechaActual);
						} catch (ParseException ex) {
							ex.printStackTrace();
						}
						String fechaActual = new SimpleDateFormat("yyyy-MM-dd").format(fechaDate2);
//						System.out.println("fechaActual:" + fechaActual);
						String fechaNoticia = fechaActual;
			
			getModelo().setTituloNoticia(getVista().txtTitulo.getText());
			getModelo().setDescNoticia(getVista().txtDescNoticia.getText());
			getModelo().setFechaNoticia(fechaNoticia);
			getModelo().setImagenNoticia("imagen"+getModelo().getIdNoticia()+".jpg");
			String edicion = null;
			try {
				edicion = getBo().editar(getModelo());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			if (edicion.equals("¡Imágen Actualizada Correctamente!")) {
				System.out.println("src/main/resources/img/imgsNoticias/imagen"+getModelo().getIdNoticia()+".jpg");
				File fotoAEliminar = new File("src/main/resources/img/imgsNoticias/imagen"+getModelo().getIdNoticia()+".jpg");
				if(fotoAEliminar.delete()){
			          System.out.println("archivo eliminado");
			     }
				// copy file using Java 7 Files class
				File source = new File(archive);
				File dest = new File(
						"src/main/resources/img/imgsNoticias/"
								+ "imagen"+getModelo().getIdNoticia()+".jpg");
				start = System.nanoTime();
				try {
					try {
						copyFileUsingJava7Files(source, dest);
					} catch (FileAlreadyExistsException e1) {
//						e1.printStackTrace();
						JOptionPane.showMessageDialog(null,
								"¡Imágen Actualizada Correctamente!", "Error",
								JOptionPane.INFORMATION_MESSAGE);
						getVista().setVisible(false);
						getGovernment().getNoticiasExistentesController().mostrarNoticiasExistentes();
						return;
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				end = System.nanoTime();
				JOptionPane.showMessageDialog(null, edicion, "Actualización", JOptionPane.INFORMATION_MESSAGE);
				getVista().setVisible(false);
				getGovernment().getNoticiasExistentesController().mostrarNoticiasExistentes();
				return;
			} else if (edicion == "¡Noticia Actualizada Correctamente!") {
				System.out.println("src/main/resources/img/imgsNoticias/imagen"+getModelo().getIdNoticia()+".jpg");
				File fotoAEliminar = new File("src/main/resources/img/imgsNoticias/imagen"+getModelo().getIdNoticia()+".jpg");
				if(fotoAEliminar.delete()){
			          System.out.println("archivo eliminado");
			     }
				// copy file using Java 7 Files class
				File source = new File(archive);
				File dest = new File(
						"src/main/resources/img/imgsNoticias/"
								+ "imagen"+getModelo().getIdNoticia()+".jpg");
				start = System.nanoTime();
				try {
					try {
						copyFileUsingJava7Files(source, dest);
					} catch (FileAlreadyExistsException e1) {
//						e1.printStackTrace();
						JOptionPane.showMessageDialog(null,
								"¡Imágen Actualizada Correctamente!", "Error",
								JOptionPane.INFORMATION_MESSAGE);
						getVista().setVisible(false);
						getGovernment().getNoticiasExistentesController().mostrarNoticiasExistentes();
						return;
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				end = System.nanoTime();
				JOptionPane.showMessageDialog(null, edicion, "Actualización", JOptionPane.INFORMATION_MESSAGE);
				getVista().setVisible(false);
				getGovernment().getNoticiasExistentesController().mostrarNoticiasExistentes();
				return;
			}
			// ------Eliminar--------
		} else if (e.getSource() == getVista().btnEliminar) {
			if (JOptionPane.showConfirmDialog(null,
					"Seguro De Eliminar La Noticia: " + getVista().txtTitulo.getText()) == 0) {

				int idNoticia = getModelo().getIdNoticia();
				String delete = null;
				try {
					delete = getBo().eliminar(idNoticia);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				if (delete.equals("¡Noticia Eliminada Correctamente!")) {
					JOptionPane.showMessageDialog(null, delete, "Error", JOptionPane.INFORMATION_MESSAGE);
					getVista().setVisible(false);
					getGovernment().getNoticiasExistentesController().mostrarNoticiasExistentes();
					return;
				}
				return;
			}
		}

	}
}
