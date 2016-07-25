/**
 * 
 */
package com.greenpear.it.scaepro.controller.administracionmovil;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.springframework.beans.factory.annotation.Autowired;

import com.greenpear.it.scaepro.bo.administracionmovil.NoticiasExistentesBo;
import com.greenpear.it.scaepro.controller.government.GovernmentService;
import com.greenpear.it.scaepro.model.administracionmovil.NoticiasModel;
import com.greenpear.it.scaepro.view.administracionmovil.NoticiasExistentesView;

/**
 * @author EDSONJOSUE
 *
 */
public class NoticiasExistentesController {
	// ********ESTANCIAS***********************************
	@Autowired
	private GovernmentService government;

	@Autowired
	private NoticiasExistentesView vista;

	@Autowired
	private NoticiasExistentesBo bo;

	@Autowired
	private NoticiasModel modelo;

	public GovernmentService getGovernment() {
		return government;
	}

	public NoticiasExistentesView getVista() {
		return vista;
	}

	public NoticiasExistentesBo getBo() {
		return bo;
	}

	public NoticiasModel getModelo() {
		return modelo;
	}

	// ***********FIN DE ESTANCIAS****************
	public void mostrarNoticiasExistentes() {
		getVista().setVisible(true);
		getVista().toFront();
		cargarNoticias();
	}

	private void cargarNoticias() {
		List<NoticiasModel> listaNoticias = new ArrayList<NoticiasModel>();
		try {
			listaNoticias = getBo().consultaGeneral();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		Iterator<NoticiasModel> itrPartidos = listaNoticias.iterator();
		String[] columnNames = { "Noticias", "Imágenes", "Detalles" };

		final Class[] tiposColumnas = new Class[] { java.lang.String.class, JButton.class, JButton.class };

		getVista().tablaNoticias.setModel(new javax.swing.table.DefaultTableModel() {
			Class[] tipos = tiposColumnas;

			@SuppressWarnings("unchecked")
			public Class getColumnClass(int columnIndex) {
				return tipos[columnIndex];
			}

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});

		getVista().tablaNoticias.setDefaultRenderer(JButton.class, new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado,
					boolean tieneElFoco, int fila, int columna) {
				return (Component) objeto;
			}
		});
		if (getVista().tablaNoticias.getMouseListeners().length == 2) {

			getVista().tablaNoticias.addMouseListener(new MouseAdapter() {
				String tituloNoticia;
				Object boton;
				private String delete;

				public void mouseClicked(MouseEvent e) {
					int fila = getVista().tablaNoticias.rowAtPoint(e.getPoint());
					int columna = getVista().tablaNoticias.columnAtPoint(e.getPoint());

					if (getVista().tablaNoticias.getModel().getColumnClass(columna).equals(JButton.class)) {
						boton = getVista().tablaNoticias.getModel().getValueAt(fila, columna);
						StringBuilder sb = new StringBuilder();
						for (int i = 0; i < vista.tablaNoticias.getModel().getColumnCount(); i++) {
							if (!getVista().tablaNoticias.getModel().getColumnClass(i).equals(JButton.class)) {
								sb.append("\n").append(getVista().tablaNoticias.getModel().getColumnName(i))
										.append(": ").append(vista.tablaNoticias.getModel().getValueAt(fila, i));
								if (getVista().tablaNoticias.getModel().getColumnName(i) == "Noticias") {
									tituloNoticia = getVista().tablaNoticias.getModel().getValueAt(fila, i).toString();
								}
							}
						}
						// Botón Editar--------
						if (boton.toString().contains("Detalle") == true) {
							NoticiasModel modeloConsulta = new NoticiasModel();
							modeloConsulta = getBo().consultaEditar(tituloNoticia);
							getModelo().setTituloNoticia(modeloConsulta.getTituloNoticia());
							getModelo().setDescNoticia(modeloConsulta.getDescNoticia());
							getModelo().setIdNoticia(modeloConsulta.getIdNoticia());
							getModelo().setImagenNoticia(modeloConsulta.getImagenNoticia());
							getModelo().setNombreVentana("Detalle Noticia");
							getGovernment().mostrarNuevaNoticia();
							return;
						} // Botón Img
						else {
							NoticiasModel modeloConsulta = new NoticiasModel();
							modeloConsulta = getBo().consultaEditar(tituloNoticia);
							getModelo().setImagenNoticia(modeloConsulta.getImagenNoticia());
							getGovernment().mostrarImagenAmpliada();
							return;
						}
					}
				}
			});
		}

		DefaultTableModel modelo = (DefaultTableModel) getVista().tablaNoticias.getModel();
		modelo.setColumnIdentifiers(columnNames);
		Object[] fila = new Object[modelo.getColumnCount()];
		while (itrPartidos.hasNext()) {
			NoticiasModel noticias = itrPartidos.next();
			JButton btn = new JButton("Detalle");
			JButton btn2 = new JButton();
			ImageIcon icono = new ImageIcon(
					"C:/Users/EDSONJOSUE/Documents/WorkSpaceSpringTools/SCAEPro/src/main/resources/img/imgsNoticias/"
							+ noticias.getImagenNoticia());
			Image img = icono.getImage();
			Image nuevaImg = img.getScaledInstance(75, 60, java.awt.Image.SCALE_SMOOTH);
			ImageIcon newIcono = new ImageIcon(nuevaImg);
			btn2.setIcon(newIcono);
			fila[0] = noticias.getTituloNoticia();
			fila[1] = btn2;
			fila[2] = btn;

			modelo.addRow(fila);
		}
		getVista().tablaNoticias.setRowHeight(60);
		getVista().tablaNoticias.getColumnModel().getColumn(0).setPreferredWidth(255);
		getVista().tablaNoticias.getColumnModel().getColumn(1).setPreferredWidth(75);
		getVista().tablaNoticias.getColumnModel().getColumn(2).setPreferredWidth(75);
	}
}
