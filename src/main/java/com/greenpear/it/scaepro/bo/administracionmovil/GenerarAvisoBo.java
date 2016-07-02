/**
 * 
 */
package com.greenpear.it.scaepro.bo.administracionmovil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.greenpear.it.scaepro.dao.administracionmovil.GenerarAvisoDao;
import com.greenpear.it.scaepro.model.administracionmovil.GenerarAvisoModel;
import com.greenpear.it.scaepro.model.administracionmovil.NoticiasModel;

/**
 * @author EDSONJOSUE
 *
 */
public class GenerarAvisoBo {
	@Autowired
	GenerarAvisoDao dao;

	public GenerarAvisoDao getDao() {
		return dao;
	}

	public List<GenerarAvisoModel> consultaGeneral() throws SQLException {
		List<GenerarAvisoModel> listaEmpleados = new ArrayList<GenerarAvisoModel>();
		try {
			listaEmpleados = getDao().consultaGeneral();
		} catch (SQLException t) {
			throw new SQLException(t.getMessage());
		}
		return listaEmpleados;
	}

	public List<GenerarAvisoModel> consultaAreas() throws SQLException {
		List<GenerarAvisoModel> listaEmpleados = new ArrayList<GenerarAvisoModel>();
		try {
			listaEmpleados = getDao().consultaAreas();
		} catch (SQLException t) {
			throw new SQLException(t.getMessage());
		}
		return listaEmpleados;
	}

	public GenerarAvisoModel consultaArea(String nombre) throws SQLException {
		GenerarAvisoModel modelo = new GenerarAvisoModel();
		try {
			modelo = getDao().consultaArea(nombre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelo;
	}

	public String insertarAvisoArea(GenerarAvisoModel modelo) throws SQLException {
		String registro = null;
		registro = getDao().insertarAvisoArea(modelo);
		return registro;
	}

	public GenerarAvisoModel consultaEmpleado(String nombre) throws SQLException{
		GenerarAvisoModel modelo = new GenerarAvisoModel();
		try {
			modelo = getDao().consultaEmpleado(nombre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelo;
	}

}
