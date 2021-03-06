package com.greenpear.it.scaepro.model.empleado;

import java.io.ByteArrayInputStream;

/**
 * 
 * @author DanielBP
 *
 */
public class EmpleadoModel {
	private int idEmpleado;
	private String nombreEmpleado;
	private String apePatEmpleado;
	private String apeMatEmpleado;
	private String fechaNacimiento;
	private String telCel;
	private String telCasa;
	private int idDireccionEmpleado;
	private int idTurno;
	private int idArea;
	private String fotografia;
	private String periodoNominal;
	private int diaDePago;
	private String nipEmpleado;
	private byte huellaEmpleado[];
	private String estado;
	private String puesto;
	private String area;
	private String nombreUsuario;
	private String password;
	private String nombreTurno;

	private ByteArrayInputStream datosHuella;
	private int tamanoHuella;

	/**
	 * @return the idEmpleado
	 */
	public int getIdEmpleado() {
		return idEmpleado;
	}

	/**
	 * @param idEmpleado
	 *            the idEmpleado to set
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
	 * @param nombreEmpleado
	 *            the nombreEmpleado to set
	 */
	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	/**
	 * @return the apePatEmpleado
	 */
	public String getApePatEmpleado() {
		return apePatEmpleado;
	}

	/**
	 * @param apePatEmpleado
	 *            the apePatEmpleado to set
	 */
	public void setApePatEmpleado(String apePatEmpleado) {
		this.apePatEmpleado = apePatEmpleado;
	}

	/**
	 * @return the apeMatEmpleado
	 */
	public String getApeMatEmpleado() {
		return apeMatEmpleado;
	}

	/**
	 * @param apeMatEmpleado
	 *            the apeMatEmpleado to set
	 */
	public void setApeMatEmpleado(String apeMatEmpleado) {
		this.apeMatEmpleado = apeMatEmpleado;
	}

	/**
	 * @return the fechaNacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @param fechaNacimiento
	 *            the fechaNacimiento to set
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * @return the telCel
	 */
	public String getTelCel() {
		return telCel;
	}

	/**
	 * @param telCel
	 *            the telCel to set
	 */
	public void setTelCel(String telCel) {
		this.telCel = telCel;
	}

	/**
	 * @return the telCasa
	 */
	public String getTelCasa() {
		return telCasa;
	}

	/**
	 * @param telCasa
	 *            the telCasa to set
	 */
	public void setTelCasa(String telCasa) {
		this.telCasa = telCasa;
	}

	/**
	 * @return the idDireccionEmpleado
	 */
	public int getIdDireccionEmpleado() {
		return idDireccionEmpleado;
	}

	/**
	 * @param idDireccionEmpleado
	 *            the idDireccionEmpleado to set
	 */
	public void setIdDireccionEmpleado(int idDireccionEmpleado) {
		this.idDireccionEmpleado = idDireccionEmpleado;
	}

	/**
	 * @return the idTurno
	 */
	public int getIdTurno() {
		return idTurno;
	}

	/**
	 * @param idTurno
	 *            the idTurno to set
	 */
	public void setIdTurno(int idTurno) {
		this.idTurno = idTurno;
	}

	/**
	 * @return the fotografia
	 */
	public String getFotografia() {
		return fotografia;
	}

	/**
	 * @param fotografia
	 *            the fotografia to set
	 */
	public void setFotografia(String fotografia) {
		this.fotografia = fotografia;
	}

	/**
	 * @return the periodoNominal
	 */
	public String getPeriodoNominal() {
		return periodoNominal;
	}

	/**
	 * @param periodoNominal
	 *            the periodoNominal to set
	 */
	public void setPeriodoNominal(String periodoNominal) {
		this.periodoNominal = periodoNominal;
	}

	/**
	 * @return the diaDePago
	 */
	public int getDiaDePago() {
		return diaDePago;
	}

	/**
	 * @param diaDePago
	 *            the diaDePago to set
	 */
	public void setDiaDePago(int diaDePago) {
		this.diaDePago = diaDePago;
	}

	/**
	 * @return the nipEmpleado
	 */
	public String getNipEmpleado() {
		return nipEmpleado;
	}

	/**
	 * @param nipEmpleado
	 *            the nipEmpleado to set
	 */
	public void setNipEmpleado(String nipEmpleado) {
		this.nipEmpleado = nipEmpleado;
	}

	/**
	 * @return the huellaEmpleado
	 */
	public byte[] getHuellaEmpleado() {
		return huellaEmpleado;
	}

	/**
	 * @param huellaEmpleado
	 *            the huellaEmpleado to set
	 */
	public void setHuellaEmpleado(byte[] huellaEmpleado) {
		this.huellaEmpleado = huellaEmpleado;
	}

	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * M�todo para limpiar el modelo
	 */
	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ByteArrayInputStream getDatosHuella() {
		return datosHuella;
	}

	public void setDatosHuella(ByteArrayInputStream datosHuella) {
		this.datosHuella = datosHuella;
	}

	public int getTamanoHuella() {
		return tamanoHuella;
	}

	public void setTamanoHuella(int tamanoHuella) {
		this.tamanoHuella = tamanoHuella;
	}

	public String getNombreTurno() {
		return nombreTurno;
	}

	public void setNombreTurno(String nombreTurno) {
		this.nombreTurno = nombreTurno;
	}

	public int getIdArea() {
		return idArea;
	}

	public void setIdArea(int idArea) {
		this.idArea = idArea;
	}

	public void limpiarModelo() {
		idEmpleado = 0;
		nombreEmpleado = null;
		apePatEmpleado = null;
		apeMatEmpleado = null;
		fechaNacimiento = null;
		telCel = null;
		telCasa = null;
		idDireccionEmpleado = 0;
		idTurno = 0;
		fotografia = null;
		periodoNominal = null;
		diaDePago = 0;
		nipEmpleado = null;
		area = null;
		huellaEmpleado = null;
		estado = null;
		puesto = null;
		datosHuella = null;
		nombreTurno = null;
		idArea = 0;
		tamanoHuella = 0;
	}

}
