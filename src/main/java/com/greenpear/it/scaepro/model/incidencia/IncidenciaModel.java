package com.greenpear.it.scaepro.model.incidencia;

/**
 * 
 * @author DanielBP
 *
 */
public class IncidenciaModel {
	private int idIncidencia;
	private int idEmpleado;
	private int idTipoIncidencia;
	private int idEstatusIncidencia;
	private String fechaIncidencia;
	
	/**
	 * @return the idIncidencia
	 */
	public int getIdIncidencia() {
		return idIncidencia;
	}
	/**
	 * @param idIncidencia the idIncidencia to set
	 */
	public void setIdIncidencia(int idIncidencia) {
		this.idIncidencia = idIncidencia;
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
	 * @return the idTipoIncidencia
	 */
	public int getIdTipoIncidencia() {
		return idTipoIncidencia;
	}
	/**
	 * @param idTipoIncidencia the idTipoIncidencia to set
	 */
	public void setIdTipoIncidencia(int idTipoIncidencia) {
		this.idTipoIncidencia = idTipoIncidencia;
	}
	/**
	 * @return the idEstatusIncidencia
	 */
	public int getIdEstatusIncidencia() {
		return idEstatusIncidencia;
	}
	/**
	 * @param idEstatusIncidencia the idEstatusIncidencia to set
	 */
	public void setIdEstatusIncidencia(int idEstatusIncidencia) {
		this.idEstatusIncidencia = idEstatusIncidencia;
	}
	/**
	 * @return the fechaIncidencia
	 */
	public String getFechaIncidencia() {
		return fechaIncidencia;
	}
	/**
	 * @param fechaIncidencia the fechaIncidencia to set
	 */
	public void setFechaIncidencia(String fechaIncidencia) {
		this.fechaIncidencia = fechaIncidencia;
	}
	
	public void limpiarModelo(){
		idIncidencia=0;
		idEmpleado=0;
		idTipoIncidencia=0;
		idEstatusIncidencia=0;
		fechaIncidencia=null;
	}
}
