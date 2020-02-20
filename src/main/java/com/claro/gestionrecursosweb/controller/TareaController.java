package com.claro.gestionrecursosweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.claro.gestionrecursosweb.domain.ApiService;
import com.claro.gestionrecursosweb.dto.ProyectoDto;
import com.claro.gestionrecursosweb.dto.TareaDto;

@Controller
@RequestMapping("/Tareas")
public class TareaController extends BaseController {
		
	@Value("${claro.dominio.tarea.nombre}")
	private String dominio;
	@Value("${claro.dominio.proyecto.nombre}")
	private String dominio_proyecto;
	
	@Autowired
	private ApiService<TareaDto, Integer> service;
	@Autowired
	private ApiService<ProyectoDto, Integer> serviceProyecto;
	
	@GetMapping("")
	public String Tareas(Model modelo) {
		
		serviceProyecto.setapiservicename(dominio_proyecto);
		Iterable<ProyectoDto> proyectos = serviceProyecto.findAll(ProyectoDto.class);
		
		modelo.addAttribute("proyectos", proyectos);
		return dominio + "/Tarea";
	}

}
