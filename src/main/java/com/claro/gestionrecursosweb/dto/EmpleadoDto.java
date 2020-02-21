package com.claro.gestionrecursosweb.dto;

import java.util.Date;

public class EmpleadoDto {

	private Integer id;	
	private Integer codpersona;
	private Integer codperfil;
	private Integer codperfiltipo;
	private Integer codlineaproducto;
	private Integer codproveedor;
	private Integer codperfilnivel;
	private String usuarioclaro;
	private Date fechainicio;
	private Date fechafin;
	private Date fechacreacion;
	private Date fechamodificacion;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCodpersona() {
		return codpersona;
	}
	public void setCodpersona(Integer codpersona) {
		this.codpersona = codpersona;
	}
	public Integer getCodperfil() {
		return codperfil;
	}
	public void setCodperfil(Integer codperfil) {
		this.codperfil = codperfil;
	}
	public Integer getCodperfiltipo() {
		return codperfiltipo;
	}
	public void setCodperfiltipo(Integer codperfiltipo) {
		this.codperfiltipo = codperfiltipo;
	}
	public Integer getCodlineaproducto() {
		return codlineaproducto;
	}
	public void setCodlineaproducto(Integer codlineaproducto) {
		this.codlineaproducto = codlineaproducto;
	}
	public Integer getCodproveedor() {
		return codproveedor;
	}
	public void setCodproveedor(Integer codproveedor) {
		this.codproveedor = codproveedor;
	}
	public Integer getCodperfilnivel() {
		return codperfilnivel;
	}
	public void setCodperfilnivel(Integer codperfilnivel) {
		this.codperfilnivel = codperfilnivel;
	}
	public String getUsuarioclaro() {
		return usuarioclaro;
	}
	public void setUsuarioclaro(String usuarioclaro) {
		this.usuarioclaro = usuarioclaro;
	}
	public Date getFechainicio() {
		return fechainicio;
	}
	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}
	public Date getFechafin() {
		return fechafin;
	}
	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
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
