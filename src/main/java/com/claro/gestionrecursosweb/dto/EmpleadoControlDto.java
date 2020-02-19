package com.claro.gestionrecursosweb.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class EmpleadoControlDto {

	private Integer id;	
	private Integer codempleado;	
	private Integer codproyecto;
	private String descripcion;
	private Timestamp fechacreacion;
	private Timestamp fechahorafin;
	private Timestamp fechahorainicio;
	private Timestamp fechamodificacion;
	private BigDecimal horas;

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

	public Timestamp getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public Timestamp getFechahorafin() {
		return this.fechahorafin;
	}

	public void setFechahorafin(Timestamp fechahorafin) {
		this.fechahorafin = fechahorafin;
	}

	public Timestamp getFechahorainicio() {
		return this.fechahorainicio;
	}

	public void setFechahorainicio(Timestamp fechahorainicio) {
		this.fechahorainicio = fechahorainicio;
	}

	public Timestamp getFechamodificacion() {
		return this.fechamodificacion;
	}

	public void setFechamodificacion(Timestamp fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}

	public BigDecimal getHoras() {
		return this.horas;
	}

	public void setHoras(BigDecimal horas) {
		this.horas = horas;
	}

}
