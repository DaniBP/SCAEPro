/**
 * 
 */
package com.greenpear.it.scaepro.bo.gestionareas;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.greenpear.it.scaepro.dao.gestionareas.AltaAreasDao;
import com.greenpear.it.scaepro.model.gestionareas.ConsultaAreasModel;
import com.greenpear.it.scaepro.services.InsertService;

/**
 * @author EDSONJOSUE
 *
 */
@Service("altaAreasBo")
public class AltaAreasBo implements InsertService<ConsultaAreasModel>{
	
	@Autowired
	@Qualifier("altaAreasDao")
	private AltaAreasDao insertDaoService;
	
	private AltaAreasDao getInsertDaoService() {
		return insertDaoService;
	}

	@Override
	public String insertar(ConsultaAreasModel modelo) throws SQLException {
		String acceso=null;
//		
//		if(modelo.getUsuario().length()<5){
//			return "El nombre de usuario es muy corto";
//		}else if(modelo.getPassword().length()<3){
//			return "La contraseña es muy corta";
//		}
		
		try {
			acceso = getInsertDaoService().insertar(modelo);
		} catch (SQLException t) {
			throw new SQLException(t.getMessage());
		}
		return acceso;
	}

	public String editar(ConsultaAreasModel modelo) throws SQLException {
		String acceso=null;
		try {
			acceso = getInsertDaoService().editar(modelo);
		} catch (SQLException t) {
			throw new SQLException(t.getMessage());
		}
		return acceso;
	}

}
