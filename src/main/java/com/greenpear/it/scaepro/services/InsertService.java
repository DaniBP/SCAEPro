package com.greenpear.it.scaepro.services;

import java.sql.SQLException;

public interface InsertService <T>{

	/**
	 * Metodo que realiza la persistencia para insertar un registro a la BD
	 * @param t, objeto que contiene los datos a registrar
	 * @return String, resultado de la transacción
	 * @throws SQLException
	 */
	public String insertar(T t) throws SQLException;
	
}
