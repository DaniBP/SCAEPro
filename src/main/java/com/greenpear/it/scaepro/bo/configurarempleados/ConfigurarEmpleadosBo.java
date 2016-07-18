package com.greenpear.it.scaepro.bo.configurarempleados;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.greenpear.it.scaepro.dao.configurarempleados.ConfigurarEmpleadoDao;
import com.greenpear.it.scaepro.model.direccion.DireccionModelo;
import com.greenpear.it.scaepro.model.empleado.EmpleadoModel;
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
	
	public ConfigurarEmpleadoDao getEmpleadoDao() {
		return empleadoDao;
	}

	public DireccionModelo getDireccionModelo() {
		return direccionModelo;
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
		System.out.println("lol1");

		EmpleadoModel empleadoModel = null;
		try {
			empleadoModel =getEmpleadoDao().consultarIdEmpleado();
		} catch (SQLException t) {
			JOptionPane.showMessageDialog(null, t.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return empleadoModel;
	}

}
