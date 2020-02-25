package com.claro.gestionrecursosweb.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.claro.gestionrecursosweb.domain.ApiService;
import com.claro.gestionrecursosweb.dto.EmpleadoControlDto;
import com.claro.gestionrecursosweb.dto.ProyectoDto;

@Controller
@RequestMapping("/EmpleadoControl")
public class EmpleadoControlController extends BaseController {

	@Value("${claro.dominio.empleadocontrol.nombre}")
	private String dominio;
	@Value("${claro.dominio.proyecto.nombre}")
	private String dominio_proyecto;
	
	@Autowired
	private ApiService<EmpleadoControlDto, Integer> service;
	@Autowired
	private ApiService<ProyectoDto, Integer> serviceProyecto;
		
	public void configurarService() {
		service.setapiservicename(dominio);
	}
	
	@GetMapping("/Filtro")
	public String filtro(Model model) {
		configurarService();
		
		Iterable<EmpleadoControlDto> dto = service.findAll(EmpleadoControlDto.class);
		
		model.addAttribute("modelo", dto);
		return dominio + "/Filtro";
	}
	
	@GetMapping("Crear")
	public String crear(Model modelo) {
		configurarService();
		modelo.addAttribute("cl_formaction", "Crear");
		
		modelo.addAttribute("modelo", new EmpleadoControlDto());
		cargarListas(modelo);
		return dominio + "/EmpleadoControl";
	}
	
	@PostMapping("/Crear")
	public String crear(EmpleadoControlDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
		configurarService();
		modelo.addAttribute("cl_formaction", "Crear");
		
		EmpleadoControlDto dtoResultado = service.insert(dto, EmpleadoControlDto.class);
		
		return redireccion("Editar", dtoResultado.getId().toString(), "S", "C", request);
	}
	
	@GetMapping("/Editar/{id}")
	public String editar(@PathVariable Integer id, Model modelo, @RequestParam(required = false) String cla) {
		configurarService();
		mostrarMensajes(modelo, cla);		
		modelo.addAttribute("cl_formaction", "Editar");
		
		Optional<EmpleadoControlDto> dtoResultado = service.findById(id, EmpleadoControlDto.class);
		if (dtoResultado == null)
			dtoResultado = Optional.of(new EmpleadoControlDto());
				
		modelo.addAttribute("modelo", dtoResultado.get());
		cargarListas(modelo);
		return dominio + "/EmpleadoControl";
	}
	
	@PostMapping("/Editar")
	public String editar(EmpleadoControlDto dto, BindingResult result, Model modelo) {
		configurarService();		
		modelo.addAttribute("cl_formaction", "Editar");
		
		EmpleadoControlDto dtoResultado = service.update(dto.getId(), dto, EmpleadoControlDto.class);				
		modelo.addAttribute("modelo", dtoResultado);
		
		mostrarMensajes(modelo, "S", "U");
		cargarListas(modelo);
		return dominio + "/EmpleadoControl";
	}
	
	private void cargarListas(Model modelo) {
		serviceProyecto.setapiservicename(dominio_proyecto);
		Iterable<ProyectoDto> proyectos = serviceProyecto.findAll(ProyectoDto.class);
		modelo.addAttribute("proyectos", proyectos);		
		
	}

}
