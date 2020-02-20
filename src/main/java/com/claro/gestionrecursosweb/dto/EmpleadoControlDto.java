package com.claro.gestionrecursosweb.dto;

import java.math.BigDecimal;
import java.util.Date;

public class EmpleadoControlDto {

	private Integer id;	
	private Integer codempleado;	
	private Integer codproyecto;
	private String descripcion;
	private BigDecimal horas;
	private Date fechahorafin;
	private Date fechahorainicio;
	private Date fechacreacion;
	private Date fechamodificacion;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getCodempleado() {
		return this.codempleado;
	}

	public void setCodempleado(Integer codempleado) {
		this.codempleado = codempleado;
	}
	
	public Integer getCodproyecto() {
		return this.codproyecto;
	}

	public void setCodproyecto(Integer codproyecto) {
		this.codproyecto = codproyecto;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public Date getFechahorafin() {
		return this.fechahorafin;
	}

	public void setFechahorafin(Date fechahorafin) {
		this.fechahorafin = fechahorafin;
	}

	public Date getFechahorainicio() {
		return this.fechahorainicio;
	}

	public void setFechahorainicio(Date fechahorainicio) {
		this.fechahorainicio = fechahorainicio;
	}

	public Date getFechamodificacion() {
		return this.fechamodificacion;
	}

	public void setFechamodificacion(Date fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}

	public BigDecimal getHoras() {
		return this.horas;
	}

	public void setHoras(BigDecimal horas) {
		this.horas = horas;
	}

}
