/**
 * 
 */
package com.greenpear.it.scaepro.bo.administracionmovil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.greenpear.it.scaepro.dao.administracionmovil.EstatusPagoDao;
import com.greenpear.it.scaepro.model.administracionmovil.EstatusPagoModel;
import com.greenpear.it.scaepro.model.administracionmovil.GenerarAvisoModel;

/**
 * @author EDSONJOSUE
 *
 */
public class EstatusPagoBo {
	@Autowired
	private EstatusPagoDao Dao;
	
	public EstatusPagoDao getDao() {
		return Dao;
	}

	public List<EstatusPagoModel> consultaAreas(String area, String estatusPago) throws SQLException  {
		List<EstatusPagoModel> listaEmpleados = new ArrayList<EstatusPagoModel>();
		try {
			listaEmpleados = getDao().consultaAreas(area,estatusPago);
		} catch (SQLException t) {
			throw new SQLException(t.getMessage());
		}
		return listaEmpleados;
	}

	public List<EstatusPagoModel> llenarAreas() throws SQLException {
		List<EstatusPagoModel> listaEmpleados = new ArrayList<EstatusPagoModel>();
		try {
			listaEmpleados = getDao().llenarAreas();
		} catch (SQLException t) {
			throw new SQLException(t.getMessage());
		}
		return listaEmpleados;
	}

	public EstatusPagoModel consultaEmpleado(String empleado)  throws SQLException {
		EstatusPagoModel modeloConsulta = new EstatusPagoModel();
		try {
			modeloConsulta = getDao().consultaArea(empleado);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modeloConsulta;
	}

	public String insertarStatusPago(EstatusPagoModel modeloConsulta) throws SQLException {
		String registro = null;
		try {
		registro = getDao().insertarStatusPago(modeloConsulta);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return registro;
	}

	public List<EstatusPagoModel> consultaFechas() throws SQLException {
		List<EstatusPagoModel> consulta = new ArrayList<EstatusPagoModel>();
		try {
			consulta = getDao().consultaFechas();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return consulta;
	}

	public String editar(EstatusPagoModel fechasPagosModel) throws SQLException {
		String update=null;
		try {
			update = getDao().editar(fechasPagosModel);
		} catch (SQLException t) {
			throw new SQLException(t.getMessage());
		}
		return update;
	}
}
