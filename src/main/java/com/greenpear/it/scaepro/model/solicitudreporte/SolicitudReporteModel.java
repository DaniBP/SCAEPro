/**
 * 
 */
package com.greenpear.it.scaepro.model.solicitudreporte;

/**
 * @author EDSONJOSUE
 *
 */
public class SolicitudReporteModel {
	private String nombreArea;
	private String nombreEmpleado;
	private String turno;
	private String bandera;
	private int idArea;
	private int idEmpleado;
	
	/**
	 * @return the nombreArea
	 */
	public String getNombreArea() {
		return nombreArea;
	}
	/**
	 * @param nombreArea the nombreArea to set
	 */
	public void setNombreArea(String nombreArea) {
		this.nombreArea = nombreArea;
	}
	/**
	 * @return the idArea
	 */
	public int getIdArea() {
		return idArea;
	}
	/**
	 * @param idArea the idArea to set
	 */
	public void setIdArea(int idArea) {
		this.idArea = idArea;
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
	 * @return the nombreEmpleado
	 */
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}
	/**
	 * @param nombreEmpleado the nombreEmpleado to set
	 */
	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}
	/**
	 * @return the turno
	 */
	public String getTurno() {
		return turno;
	}
	/**
	 * @param turno the turno to set
	 */
	public void setTurno(String turno) {
		this.turno = turno;
	}
	/**
	 * @return the bandera
	 */
	public String getBandera() {
		return bandera;
	}
	/**
	 * @param bandera the bandera to set
	 */
	public void setBandera(String bandera) {
		this.bandera = bandera;
	}

}
