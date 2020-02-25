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
import com.claro.gestionrecursosweb.dto.PresupuestoDto;

@Controller
@RequestMapping("/Presupuesto")
public class PresupuestoController extends BaseController {
	
	@Value("${claro.dominio.presupuesto.nombre}")
	private String dominio;
	
	@Autowired
	private ApiService<PresupuestoDto, Integer> service;
		
	public void configurarService() {
		service.setapiservicename(dominio);
	}
	
	@GetMapping("/Filtro")
	public String filtro(Model model) {
		configurarService();
		
		Iterable<PresupuestoDto> dto = service.findAll(PresupuestoDto.class);
		
		model.addAttribute("modelo", dto);
		return dominio + "/Filtro";
	}
	
	@GetMapping("Crear")
	public String crear(Model modelo) {
		configurarService();
		modelo.addAttribute("cl_formaction", "Crear");
		
		modelo.addAttribute("modelo", new PresupuestoDto());
		cargarListas(modelo);
		return dominio + "/Presupuesto";
	}
	
	@PostMapping("/Crear")
	public String crear(PresupuestoDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
		configurarService();
		modelo.addAttribute("cl_formaction", "Crear");
		
		PresupuestoDto dtoResultado = service.insert(dto, PresupuestoDto.class);
		
		return redireccion("Editar", dtoResultado.getId().toString(), "S", "C", request);
	}
	
	@GetMapping("/Editar/{id}")
	public String editar(@PathVariable Integer id, Model modelo, @RequestParam(required = false) String cla) {
		configurarService();
		mostrarMensajes(modelo, cla);		
		modelo.addAttribute("cl_formaction", "Editar");
		
		Optional<PresupuestoDto> dtoResultado = service.findById(id, PresupuestoDto.class);
		if (dtoResultado == null)
			dtoResultado = Optional.of(new PresupuestoDto());
				
		modelo.addAttribute("modelo", dtoResultado.get());
		cargarListas(modelo);
		return dominio + "/Presupuesto";
	}
	
	@PostMapping("/Editar")
	public String editar(PresupuestoDto dto, BindingResult result, Model modelo) {
		configurarService();		
		modelo.addAttribute("cl_formaction", "Editar");
		
		PresupuestoDto dtoResultado = service.update(dto.getId(), dto, PresupuestoDto.class);				
		modelo.addAttribute("modelo", dtoResultado);
		
		mostrarMensajes(modelo, "S", "U");
		cargarListas(modelo);
		return dominio + "/Presupuesto";
	}
	
	private void cargarListas(Model modelo) {
		
	}

}
