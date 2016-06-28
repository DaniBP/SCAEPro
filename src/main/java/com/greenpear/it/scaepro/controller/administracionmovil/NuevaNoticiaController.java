/**
 * 
 */
package com.greenpear.it.scaepro.controller.administracionmovil;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.springframework.beans.factory.annotation.Autowired;

import com.greenpear.it.scaepro.bo.administracionmovil.NuevaNoticiaBo;
import com.greenpear.it.scaepro.controller.government.GovernmentService;
import com.greenpear.it.scaepro.model.administracionmovil.NoticiasModel;
import com.greenpear.it.scaepro.view.administracionmovil.NuevaNoticiaView;

/**
 * @author EDSONJOSUE
 *
 */
public class NuevaNoticiaController implements ActionListener {
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
			getVista().btnSubirFoto.addActionListener(this);
		}
		getVista().setVisible(true);
		getVista().txtTitulo.setText("");
		getVista().txtDescNoticia.setText("");
		getVista().btnRegistrar.setText("Registrar");
		getVista().btnSubirFoto.setText("Seleccionar imagen...");
		getVista().lblImg.setBackground(Color.WHITE);
		getVista().setTitle(getModelo().getNombreVentana());
		getVista().btnRegistrar.setBounds(101, 385, 168, 32);
		getVista().btnEliminar.setVisible(false);
		editarNoticia();
	}

	private void editarNoticia() {
		if (getModelo().getNombreVentana() == "Detalle Noticia") {
			getVista().txtTitulo.setText(getModelo().getTituloNoticia());
			getVista().txtDescNoticia.setText(getModelo().getDescNoticia());
			getVista().btnSubirFoto.setText(getModelo().getImagenNoticia());
			getVista().btnRegistrar.setText("Editar");
			getVista().btnRegistrar.setBounds(26, 388, 139, 32);
			getVista().setTitle(getModelo().getNombreVentana());
			getVista().btnEliminar.setVisible(true);
			ImageIcon icono = new ImageIcon("C:/Users/EDSONJOSUE/Documents/WorkSpaceSpringTools/SCAEPro/imgsNoticias/"+getModelo().getImagenNoticia());
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
	public void actionPerformed(ActionEvent e) {
		// ------Registrar--------
		if (e.getSource() == getVista().btnRegistrar && getVista().btnRegistrar.getText() == "Registrar") {
			if (getVista().txtTitulo.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Ingrese un título de la noticia", "Nueva Noticia",
						JOptionPane.WARNING_MESSAGE);
				return;
			} else if (getVista().txtDescNoticia.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Ingrese una descripción de la noticia", "Nueva Noticia",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			getModelo().setTituloNoticia(getVista().txtTitulo.getText());
			getModelo().setDescNoticia(getVista().txtDescNoticia.getText());
			getModelo().setImagenNoticia(getVista().btnSubirFoto.getText());
			String registro = null;

			try {
				registro = getBo().insertar(getModelo());
			} catch (SQLException t) {
				JOptionPane.showMessageDialog(null, t.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (registro == "Registro de Noticia Exitoso!") {
				JOptionPane.showMessageDialog(null, registro, "Acceso", JOptionPane.INFORMATION_MESSAGE);
				// copy file using Java 7 Files class
				File source = new File(archive);
				File dest = new File(
						"C:/Users/EDSONJOSUE/Documents/WorkSpaceSpringTools/SCAEPro/imgsNoticias/" + nombreArchivo);
				start = System.nanoTime();
				try {
					copyFileUsingJava7Files(source, dest);
				} catch (IOException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"Por algún error la imágen no ha podido ser cargada, intente de nuevo...", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				end = System.nanoTime();
			}else{
				JOptionPane.showMessageDialog(null, registro, "Acceso", JOptionPane.INFORMATION_MESSAGE);
			}
			return;
			// ------Editar--------
		}
		if (e.getSource() == getVista().btnSubirFoto) {
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
		} else if (e.getSource() == getVista().btnRegistrar && getVista().btnRegistrar.getText() == "Editar") {
			if (getVista().txtTitulo.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Ingrese un título de noticia", "Editar Noticia",
						JOptionPane.WARNING_MESSAGE);
				return;
			} else if (getVista().txtDescNoticia.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Ingrese la noticia", "Editar Noticia",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			getModelo().setTituloNoticia(getVista().txtTitulo.getText());
			getModelo().setDescNoticia(getVista().txtDescNoticia.getText());
			getModelo().setImagenNoticia(getVista().btnSubirFoto.getText());
			String acceso = null;
			try {
				acceso = getBo().editar(getModelo());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if (acceso == "Esta noticia ya existe!") {
				JOptionPane.showMessageDialog(null, acceso, "Acceso", JOptionPane.INFORMATION_MESSAGE);
				return;
			} else {
				JOptionPane.showMessageDialog(null, acceso, "Acceso", JOptionPane.INFORMATION_MESSAGE);
				// getGovernment().getConsultaAreasController().mostrarVistaConsultarAreas();
				getVista().setVisible(false);
				return;
			}
		}

	}

}
