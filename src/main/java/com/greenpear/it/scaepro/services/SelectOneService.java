package com.greenpear.it.scaepro.services;

import java.sql.SQLException;

public interface SelectOneService<T>{
	
	/**
	 * Metodo que realiza la percistencia para consultar un registro de la BD
	 * @param id, identificador del registro a consultar
	 * @return T, objeto con los datos del registro consultado
	 * @throws SQLException
	 */
	public T consultaIndividual(int id) throws SQLException;

}