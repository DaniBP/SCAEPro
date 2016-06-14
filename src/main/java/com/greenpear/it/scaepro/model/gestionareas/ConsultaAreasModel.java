/**
 * 
 */
package com.greenpear.it.scaepro.model.gestionareas;

import java.util.ArrayList;
import java.util.List;

/**
 * @author EDSONJOSUE
 *
 */
public class ConsultaAreasModel {
	private String area;
	private int id_area;
	private String descripcionArea;
	private List<String> listaUsuarios = new ArrayList();
	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}
	/**
	 * @param area the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}
	/**
	 * @return the id_area
	 */
	public int getId_area() {
		return id_area;
	}
	/**
	 * @param id_area the id_area to set
	 */
	public void setId_area(int id_area) {
		this.id_area = id_area;
	}
	/**
	 * @return the descripcionArea
	 */
	public String getDescripcionArea() {
		return descripcionArea;
	}
	/**
	 * @param descripcionArea the descripcionArea to set
	 */
	public void setDescripcionArea(String descripcionArea) {
		this.descripcionArea = descripcionArea;
	}
	/**
	 * @return the listaUsuarios
	 */
	public List<String> getListaUsuarios() {
		return listaUsuarios;
	}
	/**
	 * @param listaUsuarios the listaUsuarios to set
	 */
	public void setListaUsuarios(List<String> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
}
