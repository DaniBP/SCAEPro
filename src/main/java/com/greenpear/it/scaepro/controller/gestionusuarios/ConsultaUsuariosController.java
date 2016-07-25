/**
 * 
 */
package com.greenpear.it.scaepro.controller.gestionusuarios;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


import com.greenpear.it.scaepro.bo.gestionusuarios.ConsultaUsuariosBo;
import com.greenpear.it.scaepro.controller.accesosistema.PrincipalController;
import com.greenpear.it.scaepro.controller.government.GovernmentService;
import com.greenpear.it.scaepro.model.gestionusuarios.ConsultaUsuariosModel;
import com.greenpear.it.scaepro.view.gestionusuarios.ConsultaUsuariosView;

/**
 * @author Alan Aguilar
 *
 */
public class ConsultaUsuariosController implements ActionListener {
	// ********ESTANCIAS***********************************

	@Autowired
	private GovernmentService government;

	@Autowired
	private ConsultaUsuariosModel modelo;

	@Autowired
	@Qualifier("consultaUsuariosBo")
	private ConsultaUsuariosBo Bo;

	@Autowired
	private ConsultaUsuariosView vista;
	
	public GovernmentService getGovernment() {
		return government;
	}

	public ConsultaUsuariosModel getModelo() {
		return modelo;
	}

	public ConsultaUsuariosView getVista() {
		return vista;
	}

	public ConsultaUsuariosBo getBo() {
		return Bo;
	}

	// ***********FIN DE ESTANCIAS****************
	public void mostrarVistaConsultarUsuarios() {
		if (getVista().btnBuscar.getActionListeners().length == 0) {
			getVista().btnBuscar.addActionListener(this);
			
		}
		
		getVista().setVisible(true);
		getVista().toFront();
		
		tabla();		
	}

	@SuppressWarnings({ "rawtypes", "serial" })
	private void tabla() {
		List<ConsultaUsuariosModel> listaUsuarios = new ArrayList<ConsultaUsuariosModel>();
		try {
			listaUsuarios = getBo().consultaGeneral();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		Iterator<ConsultaUsuariosModel> itrPartidos = listaUsuarios.iterator();
		String[] columnNames = { "Nombre del Usuario", "Password", "Email", "EstatusUsuario", "EstatusEnvio", "Editar", "Eliminar" };

		final Class[] tiposColumnas = new Class[] { java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, JButton.class,
				JButton.class };

		vista.tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel() {
			Class[] tipos = tiposColumnas;

			@SuppressWarnings("unchecked")
			public Class getColumnClass(int columnIndex) {
				return tipos[columnIndex];
			}

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});

		vista.tablaUsuarios.setDefaultRenderer(JButton.class, new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado,
					boolean tieneElFoco, int fila, int columna) {
				return (Component) objeto;
			}
		});
		if (vista.tablaUsuarios.getMouseListeners().length == 2) {

			vista.tablaUsuarios.addMouseListener(new MouseAdapter() {
				String usuario;
				String password;
				String email;
				int estatusUsuario;
				int estatusEnvio;
				Object boton;
				private String delete;

				public void mouseClicked(MouseEvent e) {
					int fila = vista.tablaUsuarios.rowAtPoint(e.getPoint());
					int columna = vista.tablaUsuarios.columnAtPoint(e.getPoint());

					if (vista.tablaUsuarios.getModel().getColumnClass(columna).equals(JButton.class)) {
						boton = vista.tablaUsuarios.getModel().getValueAt(fila, columna);
						StringBuilder sb = new StringBuilder();
						for (int i = 0; i < vista.tablaUsuarios.getModel().getColumnCount(); i++) {
							if (!vista.tablaUsuarios.getModel().getColumnClass(i).equals(JButton.class)) {
								sb.append("\n").append(vista.tablaUsuarios.getModel().getColumnName(i)).append(": ")
										.append(vista.tablaUsuarios.getModel().getValueAt(fila, i));
								
								if (vista.tablaUsuarios.getModel().getColumnName(i) == "Nombre del Usuario") {
									usuario = vista.tablaUsuarios.getModel().getValueAt(fila, i).toString();
								}
								if (vista.tablaUsuarios.getModel().getColumnName(i) == "email") {
									email = vista.tablaUsuarios.getModel().getValueAt(fila, i).toString();
								}
								if (vista.tablaUsuarios.getModel().getColumnName(i) == "password") {
									password = vista.tablaUsuarios.getModel().getValueAt(fila, i).toString();
								}
								if (vista.tablaUsuarios.getModel().getColumnName(i) == "estatusUsuario") {
									estatusUsuario = Integer.parseInt(vista.tablaUsuarios.getModel().getValueAt(fila, i).toString());
								}
								if (vista.tablaUsuarios.getModel().getColumnName(i) == "estatusEnvio") {
									estatusEnvio = Integer.parseInt(vista.tablaUsuarios.getModel().getValueAt(fila, i).toString());
								}
							}
						}
						// Botón Editar--------
						if (boton.toString().contains("Editar") == true) {
							modelo.setNombreUsuario(usuario);
							ConsultaUsuariosModel modeloConsulta= new ConsultaUsuariosModel();
							try {
								modeloConsulta = getBo().consultaEditar(usuario);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							getModelo().setId_usuario(modeloConsulta.getId_usuario());
							getModelo().setNombreUsuario(modeloConsulta.getNombreUsuario());
							getModelo().setPasswordUsuario(modeloConsulta.getPasswordUsuario());
							getModelo().setCorreoUsuario(modeloConsulta.getCorreoUsuario());
							getModelo().setEstatusUsuario(modeloConsulta.getEstatusUsuario());
							getModelo().setEstatusEnvio(modeloConsulta.getEstatusEnvio());
							
							getGovernment().mostrarVistaRegistrarUsuarios();

							return;
							// Botón Eliminar------
						} else if (boton.toString().contains("Eliminar") == true) {
							if (JOptionPane.showConfirmDialog(null, "Seguro de Eliminar el Usuario :" + usuario) == 0) {
								modelo.setNombreUsuario(usuario);
								delete = getBo().eliminarUsuario(getModelo());
								if (delete.equals("El usuario fue eliminado correctamente!")) {
									JOptionPane.showMessageDialog(null, delete, "Eliminar usuario",
											JOptionPane.INFORMATION_MESSAGE);
								} else {
									JOptionPane.showMessageDialog(null, delete, "Eliminar usuario",
											JOptionPane.WARNING_MESSAGE);
								}
								tabla();
							}
							return;
						}
					}
				}
			});
		}

		DefaultTableModel modelo = (DefaultTableModel) vista.tablaUsuarios.getModel();
		modelo.setColumnIdentifiers(columnNames);
		Object[] fila = new Object[modelo.getColumnCount()];
		while (itrPartidos.hasNext()) {
			ConsultaUsuariosModel partido = itrPartidos.next();
			JButton btn = new JButton("Editar");
			JButton btn2 = new JButton("Eliminar");
			fila[0] = partido.getNombreUsuario();
			fila[1] = partido.getPasswordUsuario();
			fila[2] = partido.getCorreoUsuario();
			fila[3] = partido.getEstatusUsuario();
			fila[4] = partido.getEstatusEnvio();
			fila[5] = btn;
			fila[6] = btn2;
			modelo.addRow(fila);
		}
		vista.tablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(150);
		vista.tablaUsuarios.getColumnModel().getColumn(1).setPreferredWidth(150);
		vista.tablaUsuarios.getColumnModel().getColumn(2).setPreferredWidth(150);
		vista.tablaUsuarios.getColumnModel().getColumn(3).setPreferredWidth(150);
		vista.tablaUsuarios.getColumnModel().getColumn(4).setPreferredWidth(150);
		vista.tablaUsuarios.getColumnModel().getColumn(5).setPreferredWidth(150);
		vista.tablaUsuarios.getColumnModel().getColumn(6).setPreferredWidth(150);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == getVista().btnBuscar) {
			if (getVista().txtNombreUsuario.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Ingrese el nombre del usuario", "Buscar Usuario",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			getModelo().setNombreUsuario(getVista().txtNombreUsuario.getText());
			ConsultaUsuariosModel usuario = getModelo();
			try {
				usuario=getBo().consultaIndividual(usuario);
			} catch (SQLException e1) {
				System.out.print(e1.getMessage());
			}
			String[] columnNames = { "Nombre del Usuario", "Password", "Email", "EstatusUsuario", "EstatusEnvio", "Editar", "Eliminar" };

			final Class[] tiposColumnas = new Class[] { java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, JButton.class,
					JButton.class };

			vista.tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel() {
				Class[] tipos = tiposColumnas;

				@SuppressWarnings("unchecked")
				public Class getColumnClass(int columnIndex) {
					return tipos[columnIndex];
				}

				public boolean isCellEditable(int row, int column) {
					return false;
				}
			});

			vista.tablaUsuarios.setDefaultRenderer(JButton.class, new TableCellRenderer() {
				@Override
				public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado,
						boolean tieneElFoco, int fila, int columna) {
					return (Component) objeto;
				}
			});
			if (vista.tablaUsuarios.getMouseListeners().length == 2) {

				vista.tablaUsuarios.addMouseListener(new MouseAdapter() {
					String usuario;
					String password;
					String email;
					int estatusUsuario;
					int estatusEnvio;
					Object boton;
					private String delete;

					public void mouseClicked(MouseEvent e) {
						int fila = vista.tablaUsuarios.rowAtPoint(e.getPoint());
						int columna = vista.tablaUsuarios.columnAtPoint(e.getPoint());

						if (vista.tablaUsuarios.getModel().getColumnClass(columna).equals(JButton.class)) {
							boton = vista.tablaUsuarios.getModel().getValueAt(fila, columna);
							StringBuilder sb = new StringBuilder();
							for (int i = 0; i < vista.tablaUsuarios.getModel().getColumnCount(); i++) {
								if (!vista.tablaUsuarios.getModel().getColumnClass(i).equals(JButton.class)) {
									sb.append("\n").append(vista.tablaUsuarios.getModel().getColumnName(i)).append(": ")
											.append(vista.tablaUsuarios.getModel().getValueAt(fila, i));
									
									if (vista.tablaUsuarios.getModel().getColumnName(i) == "Nombre del Usuario") {
										usuario = vista.tablaUsuarios.getModel().getValueAt(fila, i).toString();
									}
									if (vista.tablaUsuarios.getModel().getColumnName(i) == "password") {
										password = vista.tablaUsuarios.getModel().getValueAt(fila, i).toString();
									}
									
									if (vista.tablaUsuarios.getModel().getColumnName(i) == "email") {
										email = vista.tablaUsuarios.getModel().getValueAt(fila, i).toString();
									}
									if (vista.tablaUsuarios.getModel().getColumnName(i) == "estatusUsuario") {
										estatusUsuario = Integer.parseInt (vista.tablaUsuarios.getModel().getValueAt(fila, i).toString());
									}
									if (vista.tablaUsuarios.getModel().getColumnName(i) == "estatusEnvio") {
										estatusEnvio = Integer.parseInt (vista.tablaUsuarios.getModel().getValueAt(fila, i).toString());
									}
								}
							}
							// Botón Editar--------
							if (boton.toString().contains("Editar") == true) {
								modelo.setNombreUsuario(usuario);
								modelo.setPasswordUsuario(password);
								modelo.setCorreoUsuario(email);
								modelo.setEstatusUsuario(estatusUsuario);
								modelo.setEstatusEnvio(estatusEnvio);
								
								getGovernment().mostrarVistaRegistrarUsuarios();

								return;
								// Botón Eliminar------
							} else if (boton.toString().contains("Eliminar") == true) {
								if (JOptionPane.showConfirmDialog(null, "Seguro de Eliminar el Usuario :" + usuario) == 0) {
									modelo.setNombreUsuario(usuario);
									delete = getBo().eliminarUsuario(getModelo());
									if (delete.equals("El usuario fue eliminado correctamente!")) {
										JOptionPane.showMessageDialog(null, delete, "Eliminar usuario",
												JOptionPane.INFORMATION_MESSAGE);
									} else {
										JOptionPane.showMessageDialog(null, delete, "Eliminar usuario",
												JOptionPane.WARNING_MESSAGE);
									}
									tabla();
								}
								return;
							}
						}
					}
				});
			}

			DefaultTableModel modelo = (DefaultTableModel) vista.tablaUsuarios.getModel();
			modelo.setColumnIdentifiers(columnNames);
			Object[] fila = new Object[modelo.getColumnCount()];
				JButton btn = new JButton("Editar");
				JButton btn2 = new JButton("Eliminar");
				fila[0] = usuario.getNombreUsuario();
				fila[1] = usuario.getPasswordUsuario();
				fila[2] = usuario.getCorreoUsuario();
				fila[3] = usuario.getEstatusUsuario();
				fila[4] = usuario.getEstatusEnvio();
				fila[5] = btn;
				fila[6] = btn2;
				modelo.addRow(fila);
				
				vista.tablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(150);
				vista.tablaUsuarios.getColumnModel().getColumn(1).setPreferredWidth(150);
				vista.tablaUsuarios.getColumnModel().getColumn(2).setPreferredWidth(150);
				vista.tablaUsuarios.getColumnModel().getColumn(3).setPreferredWidth(150);
				vista.tablaUsuarios.getColumnModel().getColumn(4).setPreferredWidth(150);
				vista.tablaUsuarios.getColumnModel().getColumn(5).setPreferredWidth(150);
				vista.tablaUsuarios.getColumnModel().getColumn(6).setPreferredWidth(150);
		}
	}
}
