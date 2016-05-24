package com.greenpear.it.scaepro.services;

import java.sql.SQLException;
import java.util.List;

public interface CrudService<T> {
	
	/**
	 * Metodo que realiza la persistencia para insertar un registro a la BD
	 * @param t, objeto que contiene los datos a registrar
	 * @return String, resultado de la transacción
	 * @throws SQLException
	 */
	public String insertar(T t) throws SQLException;
	
	
	/**
	 * Metodo que realiza la persistencia para editar un registro de la BD
	 * @param t, objeto con los datos del registro a editar
	 * @return String, resultado de la transacción
	 * @throws SQLException
	 */
	public String editar(T t) throws SQLException;
	
	
	/**
	 * Metodo que realiza la persistencia para eliminar un registro de la BD
	 * @param id, identificador del registro a eliminar
	 * @return String, resultado de la transacción
	 * @throws SQLException
	 */
	public String eliminar(int id) throws SQLException;
	
	
	/**
	 * Metodo que realiza la percistencia para consultar registros generales de la BD
	 * @return List<T>, lista de objetos con la informacion de los datos consultados
	 * @throws SQLException
	 */
	public List<T> consultaGeneral() throws SQLException;
	
	
	/**
	 * Metodo que realiza la percistencia para consultar un registro de la BD
	 * @param id, identificador del registro a consultar
	 * @return T, objeto con los datos del registro consultado
	 * @throws SQLException
	 */
	public T consultaIndividual(int id) throws SQLException;
	
}

