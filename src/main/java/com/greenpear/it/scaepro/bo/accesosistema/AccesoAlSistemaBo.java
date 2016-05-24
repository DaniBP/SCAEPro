package com.greenpear.it.scaepro.bo.accesosistema;

import java.sql.SQLException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.greenpear.it.scaepro.dao.accesosistema.AccesoAlSistemaDao;
import com.greenpear.it.scaepro.model.accesosistema.AccesoAlSistemaModel;
import com.greenpear.it.scaepro.services.LoginService;

@Service("loginBoService")
public class AccesoAlSistemaBo implements LoginService{
	
	@Autowired
	@Qualifier("loginDaoService")
	private AccesoAlSistemaDao loginDaoService;
	
	private AccesoAlSistemaDao getLoginDaoService(){
		return loginDaoService;
	}

	@Override
	public String consultarUsuario(AccesoAlSistemaModel usuario) throws SQLException {
		String acceso=null;
		
		if(usuario.getUsuario().length()<5){
			return "El nombre de usuario es muy corto";
		}else if(usuario.getPassword().length()<3){
			return "La contraseña es muy corta";
		}
		
		try {
			acceso = getLoginDaoService().consultarUsuario(usuario);
		} catch (SQLException t) {
			throw new SQLException(t.getMessage());
		}
		return acceso;
	}
}