package com.greenpear.it.scaepro.model.controlacceso;

/**
 * 
 * @author DanielBP
 *
 */
public class ControlAccesoModel {

	private int idControlAcceso;
	private int idEmpleado;
	private String fecha;
	private int horasTrabajadas;
	
	private int idControlHoras;
	private String horaControl;
	private String horaRegistrada;
	
	/**
	 * @return the idControlAcceso
	 */
	public int getIdControlAcceso() {
		return idControlAcceso;
	}
	/**
	 * @param idControlAcceso the idControlAcceso to set
	 */
	public void setIdControlAcceso(int idControlAcceso) {
		this.idControlAcceso = idControlAcceso;
	}
	/**
	 * @return the idEmpleado
	 */
	public int getIdEmpleado() {
		return idEmpleado;
	}
	/**
	 * @param idEmpleado the idEmpleado to set
	 */
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the idControlHoras
	 */
	public int getIdControlHoras() {
		return idControlHoras;
	}
	/**
	 * @param idControlHoras the idControlHoras to set
	 */
	public void setIdControlHoras(int idControlHoras) {
		this.idControlHoras = idControlHoras;
	}
	/**
	 * @return the horaControl
	 */
	public String getHoraControl() {
		return horaControl;
	}
	/**
	 * @param horaControl the horaControl to set
	 */
	public void setHoraControl(String horaControl) {
		this.horaControl = horaControl;
	}
	/**
	 * @return the horaRegistrada
	 */
	public String getHoraRegistrada() {
		return horaRegistrada;
	}
	/**
	 * @param horaRegistrada the horaRegistrada to set
	 */
	public void setHoraRegistrada(String horaRegistrada) {
		this.horaRegistrada = horaRegistrada;
	}
	/**
	 * @return the horasTrabajadas
	 */
	public int getHorasTrabajadas() {
		return horasTrabajadas;
	}
	/**
	 * @param horasTrabajadas the horasTrabajadas to set
	 */
	public void setHorasTrabajadas(int horasTrabajadas) {
		this.horasTrabajadas = horasTrabajadas;
	}
	
	/**
	 * Método para limpiar modelo
	 */
	public void limpiarModelo(){
		idControlAcceso=0;
		idEmpleado=0;
		fecha=null;
		idControlHoras=0;
		horaControl=null;
		horaRegistrada=null;
		horasTrabajadas=0;
	}	
}
