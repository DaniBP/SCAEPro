package com.greenpear.it.scaepro.services;

import java.sql.SQLException;
import java.util.List;

public interface SelectAllService<T> {
	
	/**
	 * Metodo que realiza la percistencia para consultar registros generales de la BD
	 * @return List<T>, lista de objetos con la informacion de los datos consultados
	 * @throws SQLException
	 */
	public List<T> consultaGeneral() throws SQLException;

}
