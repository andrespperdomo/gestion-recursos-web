package com.claro.gestionrecursosweb.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LineaProductoDto {
	
	private int id;

	private Date fechacreacion;

	private Date fechamodificacion;

	private String nombre;
	
	public String incorrectData;
	
	SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechacreacion() {
		return fechacreacion;
	}
	public String getFechaCreacionString() {
		return format.format(fechacreacion);
	}

	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public Date getFechamodificacion() {
		return fechamodificacion;
	}
	public String getFechaModificacionString() {
		return format.format(fechamodificacion);
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

	
	
	
}
