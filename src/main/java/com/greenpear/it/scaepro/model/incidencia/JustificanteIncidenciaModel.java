package com.greenpear.it.scaepro.model.incidencia;

public class JustificanteIncidenciaModel {
	private int idJustificante;
	private int idIncidencia;
	private int idEmpleado;
	private String justificante;
	private String comentario;
	private String fechaJustificacion;
	
	public int getIdJustificante() {
		return idJustificante;
	}
	public void setIdJustificante(int idJustificante) {
		this.idJustificante = idJustificante;
	}
	public int getIdIncidencia() {
		return idIncidencia;
	}
	public void setIdIncidencia(int idIncidencia) {
		this.idIncidencia = idIncidencia;
	}
	public String getJustificante() {
		return justificante;
	}
	public void setJustificante(String justificante) {
		this.justificante = justificante;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public String getFechaJustificacion() {
		return fechaJustificacion;
	}
	public void setFechaJustificacion(String fechaJustificacion) {
		this.fechaJustificacion = fechaJustificacion;
	}
	public int getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	
	
}
