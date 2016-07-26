package com.greenpear.it.scaepro.bo.gestionusuarios;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.greenpear.it.scaepro.dao.gestionusuarios.AltaUsuariosDao;
import com.greenpear.it.scaepro.model.gestionusuarios.ConsultaUsuariosModel;
import com.greenpear.it.scaepro.services.InsertService;

/**
 * @author Alan Aguilar
 *
 */
@Service("altaUsuariosBo")
public class AltaUsuariosBo implements InsertService<ConsultaUsuariosModel>{
	
	@Autowired
	@Qualifier("altaUsuariosDao")
	private AltaUsuariosDao insertDaoService;
	
	private AltaUsuariosDao getInsertDaoService() {
		return insertDaoService;
	}

	@Override
	public String insertar(ConsultaUsuariosModel modelo) throws SQLException {
		String acceso=null;
		
		if (modelo.getNombreUsuario().length() < 4) {
			return "El nombre de usuario es muy corto";
		} else if (modelo.getPasswordUsuario().length() < 8) {
			return "La contraseña es muy corta";
		} else if (!modelo.getPasswordUsuario().equals(modelo.getPasswordUsuario2())) {
			return "Las contraseñas no coinciden";
		} else if (modelo.getPasswordUsuario2().length() < 8) {
			return "la contraseña es muy corta";
		} else if (modelo.getCorreoUsuario().length() < 8 || modelo.getCorreoUsuario().length() > 30){
				return "El correo debe contener de 8 a 30 caracteres";
		}else if(!modelo.getCorreoUsuario().matches(
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
		return "El formato del correo es incorrecto";
		} else if (!modelo.getCorreoUsuario().equals(modelo.getCorreoUsuario2())) {
			return "Los Correos no coinciden";
			
		}
		
		try {
			acceso = getInsertDaoService().insertar(modelo);
		} catch (SQLException t) {
			throw new SQLException(t.getMessage());
		}
		return acceso;
	}

	public String editar(ConsultaUsuariosModel modelo) throws SQLException {
		String acceso=null;
		
		if (modelo.getNombreUsuario().length() < 4) {
			return "El nombre de usuario es muy corto";
		} else if (modelo.getPasswordUsuario().length() < 8) {
			return "La contraseña es muy corta";
		} else if (!modelo.getPasswordUsuario().equals(modelo.getPasswordUsuario2())) {
			return "Las contraseñas no coinciden";
		} else if (modelo.getPasswordUsuario2().length() < 8) {
			return "la contraseña es muy corta";
		} else if (modelo.getCorreoUsuario().length() < 8 || modelo.getCorreoUsuario().length() > 30){
			return "El correo debe contener de 8 a 30 caracteres";
		}else if(!modelo.getCorreoUsuario().matches(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
			return "El formato del correo es incorrecto";
		} else if (!modelo.getCorreoUsuario2().equals(modelo.getCorreoUsuario())) {
			return "Los Correos no coinciden";
			
		}
		
		try {
			acceso = getInsertDaoService().editar(modelo);
		} catch (SQLException t) {
			throw new SQLException(t.getMessage());
		}
		return acceso;
	}

}
