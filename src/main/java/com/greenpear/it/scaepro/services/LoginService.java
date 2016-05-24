package com.greenpear.it.scaepro.services;

import java.sql.SQLException;

import com.greenpear.it.scaepro.model.accesosistema.AccesoAlSistemaModel;

public interface LoginService {

	/**
	 * Metodo que realiza la persistencia para autorizar el acceso a un usuario
	 * @param usuario, Modelo que contiene el nombre y la contraseña del usuario
	 * @return String, Estado de la persistencia
	 * @throws SQLClientInfoException
	 */
	public String consultarUsuario(AccesoAlSistemaModel usuario) throws SQLException;
	
}