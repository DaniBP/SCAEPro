/**
 * 
 */
package com.greenpear.it.scaepro.controller.solicitudreporte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.springframework.beans.factory.annotation.Autowired;

import com.greenpear.it.scaepro.bo.solicitudreporte.SolicitudReporteBo;
import com.greenpear.it.scaepro.controller.government.GovernmentService;
import com.greenpear.it.scaepro.model.solicitudreporte.SolicitudReporteModel;
import com.greenpear.it.scaepro.view.solicitudreporte.SolicitudReporteView;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * @author EDSONJOSUE
 *
 */
public class SolicitudReporteController implements ActionListener, ChangeListener {
	// ********ESTANCIAS***********************************
		@Autowired
		private GovernmentService government;

		@Autowired
		private SolicitudReporteView vista;
		
		@Autowired
		private SolicitudReporteModel modelo;
		
		@Autowired
		private SolicitudReporteBo bo;

		public GovernmentService getGovernment() {
			return government;
		}

		public SolicitudReporteView getVista() {
			return vista;
		}
		
		public SolicitudReporteModel getModelo() {
			return modelo;
		}
		public SolicitudReporteBo getBo() {
			return bo;
		}

		// ***********FIN DE ESTANCIAS****************
		public void mostrarVistaSolicitudReporte() {
			if (getVista().btnGenerarReporte.getActionListeners().length == 0) {
				getVista().btnGenerarReporte.addActionListener(this);
				getVista().rdbtnReporteGeneral.addChangeListener(this);
				getVista().rdbtnReportePorArea.addChangeListener(this);
			}
			llenarAreas();
			obtenerFechaActual();
			getVista().setVisible(true);
			getVista().toFront();
		}
		
		private void obtenerFechaActual() {
			Calendar calendar = new GregorianCalendar();
			String dia = Integer.toString(calendar.get(Calendar.DATE));
			String mes = Integer.toString(calendar.get(Calendar.MONTH));
			String annio = Integer.toString(calendar.get(Calendar.YEAR));
			getVista().lblFecha.setText(dia+"/"+mes+"/"+annio);
		}

		private void llenarAreas() {
			List<SolicitudReporteModel> listaAreas = new ArrayList<SolicitudReporteModel>();
			try {
				listaAreas = getBo().llenarAreas();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			Iterator<SolicitudReporteModel> itrAreas = listaAreas.iterator();
			while (itrAreas.hasNext()) {
				SolicitudReporteModel areas = itrAreas.next();
				getVista().cmbAreas.addItem(areas.getNombreArea());
			}
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(getVista().dataInicio.getDate()==null||getVista().dataFin.getDate()==null){
				JOptionPane.showMessageDialog(null, "¡Debe Rellenar Los Dos Campos De Fecha!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			Connection connection=null;
			String db="scaeprobd";
			String user="root";
			String password="";
			try
			{
				String ruta = "jdbc:mysql://";
				String servidor = "localhost:3306/";
				Class.forName("com.mysql.jdbc.Driver");
				connection=DriverManager.getConnection(ruta+servidor+db,user,password);
			}
			catch(ClassNotFoundException ex)
			{
				System.out.println(ex.getMessage());
				connection=null;
			}
			catch(SQLException ex)
			{			
				System.out.println(ex.getMessage());
				connection=null;
			}
			catch(Exception ex)
			{			
				System.out.println(ex.getMessage());
				connection=null;
			}
			if(e.getSource() == getVista().btnGenerarReporte && getVista().rdbtnReportePorArea.isSelected() == true){
				if(getVista().cmbAreas.getSelectedItem()=="Selecciona..."){
					JOptionPane.showMessageDialog(null, "¡Debe Seleccionar Un Área!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String nombreArea = getVista().cmbAreas.getSelectedItem().toString();
				     String formato = "yyyy/MM/d";
				     //Formato
				     Date dateInicio = getVista().dataInicio.getDate();
				     SimpleDateFormat sdf = new SimpleDateFormat(formato);
				     String fechaInicio = (sdf.format(dateInicio));
				     
				     Date dateFin = getVista().dataFin.getDate();
				     String fechaFin = (sdf.format(dateFin));
//				System.out.println("formato:"+formato+" fechaInicio:" +fechaInicio+ " fechaFin:"+ fechaFin);
				Map parametro = new HashMap(); 
				parametro.put("nombreArea",nombreArea);
				parametro.put("fechaInicio",fechaInicio);
				parametro.put("fechaFin",fechaFin);
				try
				{
					JasperPrint jasperPrint=JasperFillManager.fillReport(new File(".").getAbsolutePath()+"/src/main/resources/reportes/ReportePorArea.jasper", parametro, connection);
					JasperViewer jasperViewer=new JasperViewer(jasperPrint);
					jasperViewer.setVisible(true);
					jasperViewer.setDefaultCloseOperation(jasperViewer.HIDE_ON_CLOSE);
				}catch (JRException ex){
					System.out.println(ex.getMessage());
				}
			}else if(e.getSource() == getVista().btnGenerarReporte && getVista().rdbtnReporteGeneral.isSelected() == true){
				String formato = "yyyy/MM/d";
			     //Formato
			     Date dateInicio = getVista().dataInicio.getDate();
			     SimpleDateFormat sdf = new SimpleDateFormat(formato);
			     String fechaInicio = (sdf.format(dateInicio));
			     
			     Date dateFin = getVista().dataFin.getDate();
			     String fechaFin = (sdf.format(dateFin));
				Map parametro = new HashMap(); 
				parametro.put("fechaInicio",fechaInicio);
				parametro.put("fechaFin",fechaFin);
				try
				{
					JasperPrint jasperPrint=JasperFillManager.fillReport(new File(".").getAbsolutePath()+"/src/main/resources/reportes/ReporteGeneral.jasper", parametro, connection);
					JasperViewer jasperViewer=new JasperViewer(jasperPrint);
					jasperViewer.setVisible(true);
					jasperViewer.setDefaultCloseOperation(jasperViewer.HIDE_ON_CLOSE);
				}catch (JRException ex){
					System.out.println(ex.getMessage());
				}
			}
		}

		@Override
		public void stateChanged(ChangeEvent e) {
			if(getVista().rdbtnReporteGeneral.isSelected()){
				getVista().cmbAreas.setEnabled(false);
				getVista().lblArea.setEnabled(false);
				return;
			}else if(getVista().rdbtnReportePorArea.isSelected()){
				getVista().cmbAreas.setEnabled(true);
				getVista().lblArea.setEnabled(true);
				return;
			}
			
		}

}
