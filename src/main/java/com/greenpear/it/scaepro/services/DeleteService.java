package com.greenpear.it.scaepro.services;

import java.sql.SQLException;

public interface DeleteService {
	
	/**
	 * Metodo que realiza la persistencia para eliminar un registro de la BD
	 * @param id, identificador del registro a eliminar
	 * @return String, resultado de la transacción
	 * @throws SQLException
	 */
	public String eliminar(int id) throws SQLException;

}
