/**
 * 
 */
package com.greenpear.it.scaepro.bo.gestionusuarios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.greenpear.it.scaepro.dao.gestionareas.ConsultaAreasDao;
import com.greenpear.it.scaepro.dao.gestionusuarios.ConsultaUsuariosDao;
import com.greenpear.it.scaepro.model.administracionmovil.NoticiasModel;
import com.greenpear.it.scaepro.model.gestionareas.ConsultaAreasModel;
import com.greenpear.it.scaepro.model.gestionusuarios.ConsultaUsuariosModel;
import com.greenpear.it.scaepro.services.SelectAllService;

/**
 * @author Alan Aguilar
 * @param <T>
 *
 */
@Service("consultaUsuariosBo")
public class ConsultaUsuariosBo implements SelectAllService<ConsultaUsuariosModel>{
	@Autowired
	@Qualifier("consultaUsuariosDao")
	private ConsultaUsuariosDao selectAllService;
	
	public ConsultaUsuariosDao getSelectAllService() {
		return selectAllService;
	}

	@Override
	public List<ConsultaUsuariosModel> consultaGeneral() throws SQLException {
		List<ConsultaUsuariosModel> listaUsuarios=new ArrayList<ConsultaUsuariosModel>();
		try {
			listaUsuarios = getSelectAllService().consultaGeneral();
		} catch (SQLException t) {
			throw new SQLException(t.getMessage());
		}
		return listaUsuarios;
	}

	public String eliminarUsuario(ConsultaUsuariosModel modelo) {
		String resultado="";
		try{
			resultado=getSelectAllService().eliminarUsuario(modelo);
		}catch(Exception e){
			return e.getMessage();
		}
		return resultado;
		
	}

	public ConsultaUsuariosModel consultaIndividual(ConsultaUsuariosModel usuario) throws SQLException {
		try{
			usuario=getSelectAllService().consultaIndividual(usuario);
		}catch(Exception e){
			throw new SQLException(e.getMessage());
		}
		return usuario;
	}

	public ConsultaUsuariosModel consultaEditar(String usuario) throws SQLException {
		ConsultaUsuariosModel usuarioModel = new ConsultaUsuariosModel();
		try{
			usuarioModel=getSelectAllService().consultaEditar(usuario);
		}catch(Exception e){
			throw new SQLException(e.getMessage());
		}
		return usuarioModel;
	}
}
