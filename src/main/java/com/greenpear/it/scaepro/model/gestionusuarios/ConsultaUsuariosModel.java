package com.greenpear.it.scaepro.model.gestionusuarios;

/**
 * @author Alan Aguilar
 *
 */
public class ConsultaUsuariosModel {

	private int id_usuario;
	private String nombreUsuario;
	private String passwordUsuario;
	private String passwordUsuario2;
	private String correoUsuario;
	private String correoUsuario2;
	private int estatusUsuario;
	private int estatusEnvio;
	private String usuarioAnterior;
	
	
	
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreusuario) {
		this.nombreUsuario = nombreusuario;
	}
	public String getPasswordUsuario() {
		return passwordUsuario;
	}
	public void setPasswordUsuario(String passwordUsuario) {
		this.passwordUsuario = passwordUsuario;
	}
	public String getPasswordUsuario2() {
		return passwordUsuario2;
	}
	public void setPasswordUsuario2(String passwordUsuario2) {
		this.passwordUsuario2 = passwordUsuario2;
	}
	public String getCorreoUsuario() {
		return correoUsuario;
	}
	public void setCorreoUsuario(String correoUsuario) {
		this.correoUsuario = correoUsuario;
	}
	public String getCorreoUsuario2() {
		return correoUsuario2;
	}
	public void setCorreoUsuario2(String correoUsuario2) {
		this.correoUsuario2 = correoUsuario2;
	}
	public int getEstatusUsuario() {
		return estatusUsuario;
	}
	public void setEstatusUsuario(int estatusUsuario) {
		this.estatusUsuario = estatusUsuario;
	}
	public int getEstatusEnvio() {
		return estatusEnvio;
	}
	public void setEstatusEnvio(int estausEnvio) {
		this.estatusEnvio = estausEnvio;
	}
	
	public String getUsuarioAnterior() {
		return usuarioAnterior;
	}
	public void setUsuarioAnterior(String usuarioAnterior) {
		this.usuarioAnterior = usuarioAnterior;
	}
	
}
