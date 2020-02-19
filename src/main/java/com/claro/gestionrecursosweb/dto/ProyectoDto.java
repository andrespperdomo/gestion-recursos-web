package com.claro.gestionrecursosweb.dto;

import java.sql.Timestamp;
import java.util.Date;

public class ProyectoDto {

	private Integer id;	
	private Integer codproyectotipo;
	private Integer codpresupuesto;
	private String codigoproyecto;
	private String nombre;
	private String descripcion;
	private Boolean prioritario;
	private Date fechafin;
	private Date fechainicio;
	private Timestamp fechacreacion;
	private Timestamp fechamodificacion;
	public String incorrectData;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCodproyectotipo() {
		return codproyectotipo;
	}
	public void setCodproyectotipo(Integer codproyectotipo) {
		this.codproyectotipo = codproyectotipo;
	}
	public Integer getCodpresupuesto() {
		return codpresupuesto;
	}
	public void setCodpresupuesto(Integer codpresupuesto) {
		this.codpresupuesto = codpresupuesto;
	}
	public String getCodigoproyecto() {
		return codigoproyecto;
	}
	public void setCodigoproyecto(String codigoproyecto) {
		this.codigoproyecto = codigoproyecto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Boolean getPrioritario() {
		return prioritario;
	}
	public void setPrioritario(Boolean prioritario) {
		this.prioritario = prioritario;
	}
	public Date getFechafin() {
		return fechafin;
	}
	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}
	public Date getFechainicio() {
		return fechainicio;
	}
	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}
	public Timestamp getFechacreacion() {
		return fechacreacion;
	}
	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}
	public Timestamp getFechamodificacion() {
		return fechamodificacion;
	}
	public void setFechamodificacion(Timestamp fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}

	
}
