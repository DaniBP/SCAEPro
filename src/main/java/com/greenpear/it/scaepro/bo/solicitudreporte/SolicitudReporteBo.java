/**
 * 
 */
package com.greenpear.it.scaepro.bo.solicitudreporte;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.greenpear.it.scaepro.dao.solicitudreporte.SolicitudReporteDao;
import com.greenpear.it.scaepro.model.solicitudreporte.SolicitudReporteModel;
/**
 * @author EDSONJOSUE
 *
 */
public class SolicitudReporteBo {
	@Autowired
	private SolicitudReporteDao Dao;
	
	public SolicitudReporteDao getDao() {
		return Dao;
	}

	public List<SolicitudReporteModel> llenarAreas() throws SQLException {
		List<SolicitudReporteModel> listaAreas = new ArrayList<SolicitudReporteModel>();
		try {
			listaAreas = getDao().llenarAreas();
		} catch (SQLException t) {
			throw new SQLException(t.getMessage());
		}
		return listaAreas;
	}

}
