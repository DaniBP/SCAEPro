package com.greenpear.it.scaepro.bo.configurarempleados;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.greenpear.it.scaepro.controller.configurarempleados.ConfigurarEmpleadosController;
import com.greenpear.it.scaepro.dao.configurarempleados.ConfigurarEmpleadoDao;
import com.greenpear.it.scaepro.model.direccion.DireccionModelo;
import com.greenpear.it.scaepro.model.empleado.EmpleadoModel;
import com.greenpear.it.scaepro.model.gestionareas.ConsultaAreasModel;
import com.greenpear.it.scaepro.model.turno.TurnoModel;
import com.greenpear.it.scaepro.services.SelectAllService;
/**
 * 
 * @author RyuuZangetsu
 *
 * @param <T>
 */
public class ConfigurarEmpleadosBo implements SelectAllService<EmpleadoModel>{

	@Autowired
	@Qualifier("empleadoDaoService")
	private ConfigurarEmpleadoDao empleadoDao;
	@Autowired
	private DireccionModelo direccionModelo;
	@Autowired
	private ConfigurarEmpleadosController empleadoController;
	
	public ConfigurarEmpleadoDao getEmpleadoDao() {
		return empleadoDao;
	}

	public DireccionModelo getDireccionModelo() {
		return direccionModelo;
	}

	public ConfigurarEmpleadosController getEmpleadoController() {
		return empleadoController;
	}

	@Override
	public List<EmpleadoModel> consultaGeneral() throws SQLException {
		List<EmpleadoModel> lista=new ArrayList<EmpleadoModel>();
		try{
			lista=getEmpleadoDao().consultaGeneral();
		}catch(SQLException ex){
			throw new SQLException(ex.getMessage());
		}
		return lista;
	}


	public List<DireccionModelo> consultaDireccion() throws SQLException{
		List<DireccionModelo> lista=new ArrayList<DireccionModelo>();
		try{
			lista=getEmpleadoDao().consultarDireccion();
		}catch(SQLException ex){
			throw new SQLException(ex.getMessage());
		}
		return lista;
	}

	public List<TurnoModel> consultarTurno()throws SQLException {
		List<TurnoModel> lista=new ArrayList<TurnoModel>();
		try{
			lista=getEmpleadoDao().consultarTurno();
		}catch(SQLException ex){
			throw new SQLException(ex.getMessage());
		}
		return lista;
	}

	public String insertarDirEmpl(DireccionModelo direccionModelo2)throws SQLException {
		String acceso=null;
		try {
			acceso = getEmpleadoDao().insertarDireccionEmpleado(direccionModelo2);
		} catch (SQLException t) {
			throw new SQLException(t.getMessage());
		}
		return acceso;
	}
	
	public EmpleadoModel consultarIdEmpleado() {
		EmpleadoModel empleadoModel = null;
		try {
			empleadoModel =getEmpleadoDao().consultarIdEmpleado();
		} catch (SQLException t) {
			JOptionPane.showMessageDialog(null, t.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return empleadoModel;
	}

	public TurnoModel consultarIdArea(TurnoModel turnoModelo) {
		TurnoModel turnoModel=null;
		try {
			turnoModel =getEmpleadoDao().consultarIdArea(turnoModelo);
		} catch (SQLException t) {
			JOptionPane.showMessageDialog(null, t.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return turnoModel;
	}
	
	public TurnoModel consultarIdTurno(TurnoModel turnoModelo) {
		TurnoModel turnoModel=null;
		try {
			turnoModel =getEmpleadoDao().consultarIdTurno(turnoModelo);
		} catch (SQLException t) {
			JOptionPane.showMessageDialog(null, t.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return turnoModel;
	}

	public String registrarEmpleado(EmpleadoModel configurarEmpleadosModel)throws SQLException {
		String mensaje=null;
		if(configurarEmpleadosModel.getTelCel().length()<8 || configurarEmpleadosModel.getTelCel().length()>10){
			return "Por favor verifique numero de celular"
					+ " Minimo 8 Maximo 10 digitos";
		}else if(configurarEmpleadosModel.getTelCasa().length()<8 || configurarEmpleadosModel.getTelCasa().length()>10){
			return "Por favor verifique numero de casa"
					+ " Minimo 8 Maximo 10 digitos";
		}else if(getEmpleadoController().getRegistrarEmpleadoView().getTxtNumeroExt().getText().length()>5 || 
				empleadoController.getRegistrarEmpleadoView().getTxtNumeroInt().getText().length()>5){
			return "El numero acepta maximo 5 digitos";
		}
		try {
			mensaje = getEmpleadoDao().registrarEmpleado(configurarEmpleadosModel);
		} catch (SQLException t) {
			throw new SQLException(t.getMessage());
		}
		return mensaje;
	}

	public List<EmpleadoModel> consultaGeneralEmpleados()throws SQLException {
		List<EmpleadoModel> listaEmpleados=new ArrayList<EmpleadoModel>();
		try {
			listaEmpleados = getEmpleadoDao().consultaGeneralEmpleados();
		} catch (SQLException t) {
			throw new SQLException(t.getMessage());
		}
		return listaEmpleados;
	}

	public EmpleadoModel consultaModificarEmpleado(String idEmpleado)throws SQLException {
		EmpleadoModel empleadoModelo = new EmpleadoModel();
		try{
			empleadoModelo=getEmpleadoDao().consultaModificarEmpleado(idEmpleado);
		}catch(Exception e){
			throw new SQLException(e.getMessage());
		}
		return empleadoModelo;
	}

	public DireccionModelo consultarCpDireccionEmpleado(int idDireccionEmpleado)throws SQLException {
		DireccionModelo direccionModelo=new DireccionModelo();
		try{
			direccionModelo=getEmpleadoDao().consultarCpDireccionEmpleado(idDireccionEmpleado);
		}catch(SQLException e){
			throw new SQLException(e.getMessage());
		}
		return direccionModelo;
		
	}

	public TurnoModel consultarAreaEmpleado(int idTurno)throws SQLException {
		TurnoModel turnoModel= new TurnoModel();
		try{
			turnoModel=getEmpleadoDao().consultarAreaEmpleado(idTurno);
		}catch(SQLException e){
			throw new SQLException(e.getMessage());
		}
		return turnoModel;
	}

	public String ModificarEmpleado(EmpleadoModel configurarEmpleadosModel)throws SQLException {
		String mensaje=null;
		if(configurarEmpleadosModel.getTelCel().length()<8 || configurarEmpleadosModel.getTelCel().length()>10){
			return "Por favor verifique numero de celular"
					+ " Minimo 8 Maximo 10 digitos";
		}else if(configurarEmpleadosModel.getTelCasa().length()<8 || configurarEmpleadosModel.getTelCasa().length()>10){
			return "Por favor verifique numero de casa"
					+ " Minimo 8 Maximo 10 digitos";
		}else if(getEmpleadoController().getRegistrarEmpleadoView().getTxtNumeroExt().getText().length()>5 || 
				empleadoController.getRegistrarEmpleadoView().getTxtNumeroInt().getText().length()>5){
			return "El numero acepta maximo 5 digitos";
		}
		try {
			mensaje = getEmpleadoDao().modificarEmpleado(configurarEmpleadosModel);
		} catch (SQLException t) {
			throw new SQLException(t.getMessage());
		}
		return mensaje;
	}



	public String eliminarEmpleado(EmpleadoModel configurarEmpleadosModel)throws SQLException {
		String resultado="";
		try{
			resultado=getEmpleadoDao().eliminarEmpleado(configurarEmpleadosModel);
		}catch(Exception e){
			return e.getMessage();
		}
		return resultado;
	}

	public EmpleadoModel validarEmpleado(String nombres, String apepat, String apemat)throws SQLException {
		EmpleadoModel empleadoModel=new EmpleadoModel();
		try{
			empleadoModel=getEmpleadoDao().validarEmpleado(nombres,apepat,apemat);
		}catch(SQLException e){
			throw new SQLException(e.getMessage());
		}
		return empleadoModel;
	}

}
