package com.greenpear.it.scaepro.services;

import java.sql.SQLException;

public interface UpdateService<T> {

	/**
	 * Metodo que realiza la persistencia para editar un registro de la BD
	 * @param t, objeto con los datos del registro a editar
	 * @return String, resultado de la transacción
	 * @throws SQLException
	 */
	public String editar(T t) throws SQLException;
	
}
