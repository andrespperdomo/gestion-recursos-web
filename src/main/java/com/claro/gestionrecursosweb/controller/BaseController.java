package com.claro.gestionrecursosweb.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

@Controller
public class BaseController {

	/**
	 * Método para presentación de mensajes estándar
	 * @param modelo Modelo de la vista para agregar atributos estándar de mensajes
	 * @param tipo Tipo de mensaje: E(Error),S(Exito),W(Advertencia),I(Informativo)
	 * @param accion Accion de CRUD realizada: C(Crear),R(Consultar),U(Actualizar),D(Eliminar)
	 */
	protected void mostrarMensajes(Model modelo, String tipo, String accion) {
		String mensajeTipo = "alert-info";
		String mensaje = "";
		switch (tipo) {
		case "E":
			mensajeTipo = "alert-danger";
			break;
		case "S":
			mensajeTipo = "alert-success";
			break;
		case "W":
			mensajeTipo = "alert-warning";
			break;
		case "I":
			mensajeTipo = "alert-info";
			break;
		}
		
		switch (accion) {
		case "C":
			mensaje = "La información se guardó correctamente";
			break;
		case "R":
			mensaje = "La información se consultó correctamente";
			break;
		case "U":
			mensaje = "La información se actualizó correctamente";
			break;
		case "D":
			mensaje = "La información se eliminó correctamente";
		}
		
		modelo.addAttribute("cl_validacion_mensaje_tipo", mensajeTipo);
		modelo.addAttribute("cl_validation_mensaje", mensaje);
	}
	
	/**
	 * @param cla Parametro estandar para presentación de mensajes estandarizados
	 */
	protected void mostrarMensajes(Model modelo, String cla) {
		if (cla != null && cla != "") {
			if (cla.length() == 2) {
				mostrarMensajes(modelo, Character.toString(cla.charAt(0)), Character.toString(cla.charAt(1)));
			}
		}
	}
	
	/**
	 * Método para redireccionar entre acciones crud de un controlador
	 * @param tipo Tipo de mensaje: E(Error),S(Exito),W(Advertencia),I(Informativo)
	 * @param accion Accion de CRUD realizada: C(Crear),R(Consultar),U(Actualizar),D(Eliminar)
	 */
	protected String redireccion(String url, String id, String tipo, String accion, HttpServletRequest request) {		
		String parametrosEstandar = "";
		if (request.getParameter("clm") != null)
			parametrosEstandar += "&clm=" + request.getParameter("clm");
		
		return "redirect:" + url + "/" + id + "?cla=" + tipo + accion + parametrosEstandar;
	}
	
	/**
	 * Para dar el formato de fecha cuando se envía una fecha en un modelo desde el controlador hacia la interfaz gráfica
	 * @param binder
	 */
	@InitBinder	
	private void dateBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
	    binder.registerCustomEditor(Date.class, editor);
	}
	
}
