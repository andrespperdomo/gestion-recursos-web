package com.claro.gestionrecursosweb.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class LineaProductoDto {
	
	private Integer id;

	private Date fechacreacion;

	private Date fechamodificacion;

	private String nombre;
	
	public String incorrectData;
	
	SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
	@JsonIgnore
	public String fechaCreacionString=format.format(new Date());
	@JsonIgnore
	public String fechaModificacionString=format.format(new Date());
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "LineaProductoDto [id=" + id + ", fechacreacion=" + fechacreacion + ", fechamodificacion="
				+ fechamodificacion + ", nombre=" + nombre + ", incorrectData=" + incorrectData + ", format=" + format
				+ ", fechaCreacionString=" + fechaCreacionString + ", fechaModificacionString="
				+ fechaModificacionString + "]";
	}

	
	
	
}
