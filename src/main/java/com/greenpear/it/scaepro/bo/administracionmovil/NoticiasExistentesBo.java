/**
 * 
 */
package com.greenpear.it.scaepro.bo.administracionmovil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.greenpear.it.scaepro.dao.administracionmovil.NoticiasExistentesDao;
import com.greenpear.it.scaepro.model.administracionmovil.NoticiasModel;

/**
 * @author EDSONJOSUE
 *
 */
public class NoticiasExistentesBo {
	@Autowired
	NoticiasExistentesDao dao;
	
	public NoticiasExistentesDao getDao() {
		return dao;
	}

	public List<NoticiasModel> consultaGeneral() throws SQLException {
		List<NoticiasModel> listaAreas=new ArrayList<NoticiasModel>();
		try {
			listaAreas = getDao().consultaGeneral();
		} catch (SQLException t) {
			throw new SQLException(t.getMessage());
		}
		return listaAreas;
	}

	public NoticiasModel consultaEditar(String tituloNoticia) {
		NoticiasModel modelo = new NoticiasModel();
		try {
			modelo = getDao().consultaEditar(tituloNoticia);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelo;
	}
}
