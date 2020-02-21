package com.claro.gestionrecursosweb.dto;

import java.util.Date;

public class TareaDto {

	private Integer id;
	private Integer codtareapadre;
	private Integer codtareatipo;
	private Integer codtareaestado;
	private Integer codempleadocreo;
	private Integer codempleadoasignado;
	private Integer codproyecto;
	private String jerarquia;
	private String nombre;
	private Integer nivel;
	private String descripcion;
	private Date fechainiestimada;
	private Date fechafinestimada;
	private Date fechainireal;
	private Date fechafinreal;
	private Date fechacreacion;
	private Date fechamodificacion;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCodtareapadre() {
		return codtareapadre;
	}
	public void setCodtareapadre(Integer codtareapadre) {
		this.codtareapadre = codtareapadre;
	}
	public Integer getCodtareatipo() {
		return codtareatipo;
	}
	public void setCodtareatipo(Integer codtareatipo) {
		this.codtareatipo = codtareatipo;
	}
	public Integer getCodtareaestado() {
		return codtareaestado;
	}
	public void setCodtareaestado(Integer codtareaestado) {
		this.codtareaestado = codtareaestado;
	}
	public Integer getCodempleadocreo() {
		return codempleadocreo;
	}
	public void setCodempleadocreo(Integer codempleadocreo) {
		this.codempleadocreo = codempleadocreo;
	}
	public Integer getCodempleadoasignado() {
		return codempleadoasignado;
	}
	public void setCodempleadoasignado(Integer codempleadoasignado) {
		this.codempleadoasignado = codempleadoasignado;
	}
	public Integer getCodproyecto() {
		return codproyecto;
	}
	public void setCodproyecto(Integer codproyecto) {
		this.codproyecto = codproyecto;
	}
	public String getJerarquia() {
		return jerarquia;
	}
	public void setJerarquia(String jerarquia) {
		this.jerarquia = jerarquia;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getNivel() {
		return nivel;
	}
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFechainiestimada() {
		return fechainiestimada;
	}
	public void setFechainiestimada(Date fechainiestimada) {
		this.fechainiestimada = fechainiestimada;
	}
	public Date getFechafinestimada() {
		return fechafinestimada;
	}
	public void setFechafinestimada(Date fechafinestimada) {
		this.fechafinestimada = fechafinestimada;
	}
	public Date getFechainireal() {
		return fechainireal;
	}
	public void setFechainireal(Date fechainireal) {
		this.fechainireal = fechainireal;
	}
	public Date getFechafinreal() {
		return fechafinreal;
	}
	public void setFechafinreal(Date fechafinreal) {
		this.fechafinreal = fechafinreal;
	}
	public Date getFechacreacion() {
		return fechacreacion;
	}
	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}
	public Date getFechamodificacion() {
		return fechamodificacion;
	}
	public void setFechamodificacion(Date fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}
	
}
