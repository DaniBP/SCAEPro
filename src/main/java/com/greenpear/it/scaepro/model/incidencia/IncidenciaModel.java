package com.greenpear.it.scaepro.model.incidencia;

/**
 * 
 * @author DanielBP
 *
 */
public class IncidenciaModel {
	private int idIncidencia;
	private int idEmpleado;
	private String nombreEmpleado;
	private String apePatEmpleado;
	private String apeMatEmpleado;
	private String area;
	private String tipo;
	private String puesto;
	private String turno;
	private int idTipoIncidencia;
	private int idEstatusIncidencia;
	private String EstatusIncidencia;
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
	
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}
	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getApePatEmpleado() {
		return apePatEmpleado;
	}
	public void setApePatEmpleado(String apePatEmpleado) {
		this.apePatEmpleado = apePatEmpleado;
	}
	public String getApeMatEmpleado() {
		return apeMatEmpleado;
	}
	public void setApeMatEmpleado(String apeMatEmpleado) {
		this.apeMatEmpleado = apeMatEmpleado;
	}
	
	public String getEstatusIncidencia() {
		return EstatusIncidencia;
	}
	public void setEstatusIncidencia(String estatusIncidencia) {
		EstatusIncidencia = estatusIncidencia;
	}
	
	public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public void limpiarModelo(){
		idIncidencia=0; 
		idEmpleado=0;
		idTipoIncidencia=0;
		idEstatusIncidencia=0;
		fechaIncidencia=null;
		nombreEmpleado=null;
		tipo=null;
		area=null;
		EstatusIncidencia=null;
		puesto=null;
		turno=null;
		
	}
}
