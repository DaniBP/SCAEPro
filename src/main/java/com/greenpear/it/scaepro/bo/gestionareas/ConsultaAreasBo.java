/**
 * 
 */
package com.greenpear.it.scaepro.bo.gestionareas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.greenpear.it.scaepro.dao.gestionareas.ConsultaAreasDao;
import com.greenpear.it.scaepro.model.gestionareas.ConsultaAreasModel;
import com.greenpear.it.scaepro.services.SelectAllService;

/**
 * @author EDSONJOSUE
 * @param <T>
 *
 */
@Service("consultaAreasBo")
public class ConsultaAreasBo implements SelectAllService<ConsultaAreasModel>{
	@Autowired
	@Qualifier("consultaAreasDao")
	private ConsultaAreasDao selectAllService;
	
	public ConsultaAreasDao getSelectAllService() {
		return selectAllService;
	}

	@Override
	public List<ConsultaAreasModel> consultaGeneral() throws SQLException {
		List<ConsultaAreasModel> listaAreas=new ArrayList<ConsultaAreasModel>();
		try {
			listaAreas = getSelectAllService().consultaGeneral();
		} catch (SQLException t) {
			throw new SQLException(t.getMessage());
		}
		return listaAreas;
	}

	public String eliminarArea(ConsultaAreasModel modelo) {
		String resultado="";
		try{
			resultado=getSelectAllService().eliminarArea(modelo);
		}catch(Exception e){
			return e.getMessage();
		}
		return resultado;
		
	}

	public ConsultaAreasModel consultaIndividual(ConsultaAreasModel area) throws SQLException {
		try{
			area=getSelectAllService().consultaIndividual(area);
		}catch(Exception e){
			throw new SQLException(e.getMessage());
		}
		return area;
	}
}
