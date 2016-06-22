/**
 * 
 */
package com.greenpear.it.scaepro.bo.administracionmovil;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import com.greenpear.it.scaepro.dao.administracionmovil.NuevaNoticiaDao;
import com.greenpear.it.scaepro.model.administracionmovil.NoticiasModel;

/**
 * @author EDSONJOSUE
 *
 */
public class NuevaNoticiaBo {
	@Autowired
	private NuevaNoticiaDao dao;
	
	public NuevaNoticiaDao getDao() {
		return dao;
	}

	public String insertar(NoticiasModel modelo) throws SQLException {
		String registro=null;
//		
//		if(modelo.getUsuario().length()<5){
//			return "El nombre de usuario es muy corto";
//		}else if(modelo.getPassword().length()<3){
//			return "La contraseña es muy corta";
//		}
		
		registro = getDao().insertar(modelo);
		return registro;
	}

}
