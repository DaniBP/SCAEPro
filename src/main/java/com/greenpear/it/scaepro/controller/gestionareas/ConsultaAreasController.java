/**
 * 
 */
package com.greenpear.it.scaepro.controller.gestionareas;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

import com.greenpear.it.scaepro.bo.gestionareas.ConsultaAreasBo;
import com.greenpear.it.scaepro.controller.government.GovernmentService;
import com.greenpear.it.scaepro.model.gestionareas.ConsultaAreasModel;
import com.greenpear.it.scaepro.view.gestionareas.ConsultaAreasView;

/**
 * @author EDSONJOSUE
 *
 */
public class ConsultaAreasController implements ActionListener{
	//********ESTANCIAS***********************************
	
	@Autowired
	private GovernmentService government;
	
	@Autowired
	private ConsultaAreasModel modelo;
	
	@Autowired
	@Qualifier("consultaAreasBo")
	private ConsultaAreasBo Bo;
	
	@Autowired
	private ConsultaAreasView vista;
	
	public GovernmentService getGovernment() {
		return government;
	}
	
	public ConsultaAreasModel getModelo() {
		return modelo;
	}
	
	public ConsultaAreasView getVista() {
		return vista;
	}
	
	public ConsultaAreasBo getBo() {
		return Bo;
	}

	//***********FIN DE ESTANCIAS****************
		 public void mostrarVistaConsultarAreas(){
			 if(getVista().btnBuscar.getActionListeners().length == 0){
			 getVista().btnBuscar.addActionListener(this);
		 }
			getVista().setVisible(true);
			
			List<ConsultaAreasModel> listaUsuarios=new ArrayList<ConsultaAreasModel>();
			try {
				listaUsuarios=getBo().consultaGeneral();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			
			Iterator<ConsultaAreasModel> itrPartidos = listaUsuarios.iterator();
//			DefaultTableModel tableModel = new DefaultTableModel();
			//Nombre de las columnas
			String[] columnNames = {"Nombre del Área", "Descripción","Editar","Eliminar"};
			
			final Class[] tiposColumnas = new Class[]{
					java.lang.String.class,
					java.lang.String.class,
					JButton.class,
					JButton.class
			};
			
//				Object[][] datos = new Object[][]{
//					{partido.getArea(),partido.getDescripcionArea(),new JButton("Editar"),new JButton("Eliminar")},
//					{partido.getArea(),partido.getDescripcionArea(),new JButton("Editar"),new JButton("Eliminar")}
//					};
					vista.tablaAreas.setModel(new javax.swing.table.DefaultTableModel(){
//				vista.tablaAreas.setModel(new javax.swing.table.DefaultTableModel(datos,columnNames){
					Class[] tipos = tiposColumnas;
					
					public Class getColumnClass(int columnIndex){
						return tipos[columnIndex];
					}
					
					public boolean isCellEditable(int row, int column){
						return !(this.getColumnClass(column).equals(JButton.class));
					}
				});

					vista.tablaAreas.setDefaultRenderer(JButton.class, new TableCellRenderer() {
						
						@Override
						public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado, boolean tieneElFoco, int fila,
								int columna) {
							// TODO Auto-generated method stub
							return (Component)objeto;
						}
					});
					
					vista.tablaAreas.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e){
							int fila = vista.tablaAreas.rowAtPoint(e.getPoint());
							int columna = vista.tablaAreas.columnAtPoint(e.getPoint());
							
							if(vista.tablaAreas.getModel().getColumnClass(columna).equals(JButton.class)){
								StringBuilder sb = new StringBuilder();
								for (int i = 0; i < vista.tablaAreas.getModel().getColumnCount(); i ++){
									if(!vista.tablaAreas.getModel().getColumnClass(i).equals(JButton.class)){
										sb.append("\n").append(vista.tablaAreas.getModel().getColumnName(i)).append(": ").append(vista.tablaAreas.getModel().getValueAt(fila, i));}
								}
								JOptionPane.showMessageDialog(null, "seleccionó fila : "+ fila + sb.toString());
							}
						}
					});

//					tableModel.setColumnIdentifiers(columnNames);
					DefaultTableModel modelo = (DefaultTableModel)vista.tablaAreas.getModel();
					modelo.setColumnIdentifiers(columnNames);
					Object[] fila = new Object[modelo.getColumnCount()];
					while(itrPartidos.hasNext()){
						ConsultaAreasModel partido = itrPartidos.next();
						JButton btn = new JButton("editar");
						JButton btn2 = new JButton("eliminar");
						System.out.println(partido.getArea() + " "
								+ partido.getDescripcionArea() + "-"
								+ partido.getId_area());		
				fila[0] = partido.getArea();
				fila[1] = partido.getDescripcionArea();
				fila[2] = btn;
				fila[3] = btn2;
				modelo.addRow(fila);
			}
		 }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == getVista().btnBuscar) {
			if(getVista().txtNombArea.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Ingrese el nombre del área","Registar Área",JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			getModelo().setArea(getVista().txtNombArea.getText());
			List<ConsultaAreasModel> acceso = new ArrayList<ConsultaAreasModel>();
			
			try {
				acceso=getBo().consultaGeneral();
			} catch (SQLException t) {
				JOptionPane.showMessageDialog(null, t.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
			
			if(acceso.equals("Bùsqueda exitosa!")){
				JOptionPane.showMessageDialog(null, acceso,"Acceso",JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null, acceso,"Acceso",JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
