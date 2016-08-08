package com.greenpear.it.scaepro.bo.justificarincidencias;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.greenpear.it.scaepro.dao.justificarIncidencias.JustificarIncidenciasDao;
import com.greenpear.it.scaepro.model.empleado.EmpleadoModel;
import com.greenpear.it.scaepro.model.incidencia.IncidenciaModel;
import com.greenpear.it.scaepro.model.incidencia.JustificanteIncidenciaModel;

public class JustificarIncidenciasBo {
	// Modelos
	@Autowired
	private IncidenciaModel incidenciaModel;

	@Autowired
	@Qualifier("justificarIncidenciasDao")
	private JustificarIncidenciasDao justificarIncidenciasDao;
	
	//Setters and Getters
	
	public IncidenciaModel getIncidenciaModel() {
		return incidenciaModel;
	}

	public JustificarIncidenciasDao getJustificarIncidenciasDao() {
		return justificarIncidenciasDao;
	}
	
	//End Setters and Getters
	
	
	
	public List<IncidenciaModel> consultaIncidenciasGeneral()throws SQLException {
		List<IncidenciaModel> listaEmpleados = new ArrayList<IncidenciaModel>();
		try {
			listaEmpleados = getJustificarIncidenciasDao().consultaGeneralEmpleados();
		} catch (SQLException t) {
			throw new SQLException(t.getMessage());
		}
		return listaEmpleados;
	}

	public EmpleadoModel consultarFoto(String idEmpleado) {
		EmpleadoModel empleadoModel = null;
		try {
			empleadoModel = getJustificarIncidenciasDao().consultarFotografia(idEmpleado);
		} catch (SQLException t) {
			JOptionPane.showMessageDialog(null, t.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return empleadoModel;
	}

	public JustificanteIncidenciaModel consultarComentarioArchivo(String idEmpleado) {
		JustificanteIncidenciaModel justificante=null;
		try{
			justificante=getJustificarIncidenciasDao().consultarComentarioArchivo(idEmpleado);
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return justificante;
	}


	
}
