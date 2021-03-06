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
import com.greenpear.it.scaepro.model.administracionmovil.NoticiasModel;
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

	public String eliminarArea(ConsultaAreasModel modelo) throws SQLException{
		String resultado="";
		try{
			resultado=getSelectAllService().verificarEmpleados(modelo.getArea());
			if(!resultado.equals("Sin empleados en el area")){
				return resultado;
			}
		}catch(Exception e){
			throw new SQLException(e.getMessage());
		}
		
		try{
			resultado=getSelectAllService().eliminarArea(modelo);
		}catch(Exception e){
			throw new SQLException(e.getMessage());
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

	public ConsultaAreasModel consultaEditar(String area) throws SQLException {
		ConsultaAreasModel areaModel = new ConsultaAreasModel();
		try{
			areaModel=getSelectAllService().consultaEditar(area);
		}catch(Exception e){
			throw new SQLException(e.getMessage());
		}
		return areaModel;
	}
}
