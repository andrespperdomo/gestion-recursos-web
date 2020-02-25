package com.claro.gestionrecursosweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.claro.gestionrecursosweb.domain.ApiService;
import com.claro.gestionrecursosweb.dto.PersonaDto;
import com.claro.gestionrecursosweb.dto.ProyectoDto;
import com.claro.gestionrecursosweb.dto.TareaDto;
import com.claro.gestionrecursosweb.dto.TareaTipoDto;

@Controller
@RequestMapping("/Tareas")
public class TareaController extends BaseController {
		
	@Value("${claro.dominio.tarea.nombre}")
	private String dominio;
	@Value("${claro.dominio.proyecto.nombre}")
	private String dominio_proyecto;
	@Value("${claro.dominio.tareatipo.nombre}")
	private String dominio_tareatipo;
	@Value("${claro.dominio.persona.nombre}")
	private String dominio_persona;
	
	@Autowired
	private ApiService<ProyectoDto, Integer> serviceProyecto;
	@Autowired
	private ApiService<TareaTipoDto, Integer> serviceTareaTipo;
	@Autowired
	private ApiService<PersonaDto, Integer> servicePersona;
	
	@GetMapping("")
	public String Tareas(Model modelo) {
		
		serviceProyecto.setapiservicename(dominio_proyecto);
		Iterable<ProyectoDto> proyectos = serviceProyecto.findAll(ProyectoDto.class);
		
		modelo.addAttribute("proyectos", proyectos);
		modelo.addAttribute("modelotarea", new TareaDto());
		cargarListas(modelo);
		return dominio + "/Tarea";
	}
	
	@PostMapping
	public String Tareas(TareaDto dto, Model modelo, @RequestParam Integer codigopersona, @RequestParam Integer codigoproyecto) {
		//service.setapiservicename(dominio);
		
		//dto = service.insert(dto, TareaDto.class);
		
		
		return dominio + "/Tarea";
	}
	
	private void cargarListas(Model modelo) {
		serviceTareaTipo.setapiservicename(dominio_tareatipo);
		Iterable<TareaTipoDto> tareatipos = serviceTareaTipo.findAll(TareaTipoDto.class);
		modelo.addAttribute("tareatipos", tareatipos);
		
		servicePersona.setapiservicename(dominio_persona);
		Iterable<PersonaDto> personas = servicePersona.findAll(PersonaDto.class);
		modelo.addAttribute("personas", personas);
	}

}
