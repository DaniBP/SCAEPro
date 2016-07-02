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
		
		registro = getDao().insertar(modelo);
		return registro;
	}

	public String editar(NoticiasModel modelo) throws SQLException {
		String update=null;
		try {
			update = getDao().editar(modelo);
		} catch (SQLException t) {
			throw new SQLException(t.getMessage());
		}
		return update;
	}

	public String eliminar(int idNoticia) throws SQLException {
		String delete=null;
		delete = getDao().eliminar(idNoticia);
		return delete;
	}

}
