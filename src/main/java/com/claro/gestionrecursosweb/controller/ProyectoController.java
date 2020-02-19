package com.claro.gestionrecursosweb.controller;

import java.util.Optional;

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
import com.claro.gestionrecursosweb.dto.PresupuestoDto;
import com.claro.gestionrecursosweb.dto.ProyectoDto;
import com.claro.gestionrecursosweb.dto.ProyectoTipoDto;

@Controller
@RequestMapping("/Proyecto")
public class ProyectoController extends BaseController {
	
	@Value("${claro.dominio.proyecto.nombre}")
	private String dominio;
	@Value("${claro.dominio.presupuesto.nombre}")
	private String dominio_presupuesto;
	@Value("${claro.dominio.proyectotipo.nombre}")
	private String dominio_proyectotipo;
	
	@Autowired
	private ApiService<ProyectoDto, Integer> service;
	@Autowired
	private ApiService<PresupuestoDto, Integer> servicePresupuesto;
	@Autowired
	private ApiService<ProyectoTipoDto, Integer> serviceProyectoTipo;
		
	public void configurarService() {
		service.setapiservicename(dominio);
	}
	
	@GetMapping("/Filtro")
	public String filtro(Model model) {
		configurarService();
		
		Iterable<ProyectoDto> dto = service.findAll();
		
		model.addAttribute("modelo", dto);
		return dominio + "/Filtro";
	}
	
	@GetMapping("Crear")
	public String crear(Model modelo) {
		configurarService();
		cargarListas(modelo);
		modelo.addAttribute("cl_formaction", "Crear");
		
		modelo.addAttribute("modelo", new ProyectoDto());		
		return dominio + "/Proyecto";
	}
	
	@PostMapping("/Crear")
	public String crear(ProyectoDto dto, BindingResult result, Model modelo) {
		configurarService();
		modelo.addAttribute("cl_formaction", "Crear");
		
		ProyectoDto dtoResultado = service.insert(dto, ProyectoDto.class);
		
		return redireccion("Editar", dtoResultado.getId().toString(), "S", "C");
	}
	
	@GetMapping("/Editar/{id}")
	public String editar(@PathVariable Integer id, Model modelo, @RequestParam(required = false) String cla) {
		configurarService();
		cargarListas(modelo);
		mostrarMensajes(modelo, cla);		
		modelo.addAttribute("cl_formaction", "Editar");
		
		Optional<ProyectoDto> dtoResultado = service.findById(id, ProyectoDto.class);
		if (dtoResultado == null)
			dtoResultado = Optional.of(new ProyectoDto());
				
		modelo.addAttribute("modelo", dtoResultado.get());
		return dominio + "/Proyecto";
	}
	
	@PostMapping("/Editar")
	public String editar(ProyectoDto dto, BindingResult result, Model modelo) {
		configurarService();
		cargarListas(modelo);
		modelo.addAttribute("cl_formaction", "Editar");
		
		ProyectoDto dtoResultado = service.update(dto.getId(), dto, ProyectoDto.class);
		
		mostrarMensajes(modelo, "S", "U");
		
		modelo.addAttribute("modelo", dtoResultado);
		return dominio + "/Proyecto";
	}
	
	private void cargarListas(Model modelo) {
		servicePresupuesto.setapiservicename(dominio_presupuesto);
		Iterable<PresupuestoDto> presupuestos = servicePresupuesto.findAll();
		serviceProyectoTipo.setapiservicename(dominio_proyectotipo);
		Iterable<ProyectoTipoDto> proyectoTipos = serviceProyectoTipo.findAll();
		
		modelo.addAttribute("presupuestos", presupuestos);		
		modelo.addAttribute("proyectoTipos", proyectoTipos);
	}

}
