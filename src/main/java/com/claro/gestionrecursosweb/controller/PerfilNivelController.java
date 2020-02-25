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
import com.claro.gestionrecursosweb.dto.PerfilnivelDto;

@Controller
@RequestMapping("/PerfilNivel")
public class PerfilNivelController extends BaseController {
	
	@Value("${claro.dominio.perfilnivel.nombre}")
	private String dominio;
	
	@Autowired
	private ApiService<PerfilnivelDto, Integer> service;
		
	public void configurarService() {
		service.setapiservicename(dominio);
	}
	
	@GetMapping("/Filtro")
	public String filtro(Model model) {
		configurarService();
		
		Iterable<PerfilnivelDto> dto = service.findAll(PerfilnivelDto.class);
		
		model.addAttribute("modelo", dto);
		return dominio + "/Filtro";
	}
	
	@GetMapping("Crear")
	public String crear(Model modelo) {
		configurarService();
		modelo.addAttribute("cl_formaction", "Crear");
		
		modelo.addAttribute("modelo", new PerfilnivelDto());
		cargarListas(modelo);
		return dominio + "/PerfilNivel";
	}
	
	@PostMapping("/Crear")
	public String crear(PerfilnivelDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
		configurarService();
		modelo.addAttribute("cl_formaction", "Crear");
		
		PerfilnivelDto dtoResultado = service.insert(dto, PerfilnivelDto.class);
		
		return redireccion("Editar", dtoResultado.getId().toString(), "S", "C", request);
	}
	
	@GetMapping("/Editar/{id}")
	public String editar(@PathVariable Integer id, Model modelo, @RequestParam(required = false) String cla) {
		configurarService();
		mostrarMensajes(modelo, cla);		
		modelo.addAttribute("cl_formaction", "Editar");
		
		Optional<PerfilnivelDto> dtoResultado = service.findById(id, PerfilnivelDto.class);
		if (dtoResultado == null)
			dtoResultado = Optional.of(new PerfilnivelDto());
				
		modelo.addAttribute("modelo", dtoResultado.get());
		cargarListas(modelo);
		return dominio + "/PerfilNivel";
	}
	
	@PostMapping("/Editar")
	public String editar(PerfilnivelDto dto, BindingResult result, Model modelo) {
		configurarService();		
		modelo.addAttribute("cl_formaction", "Editar");
		
		PerfilnivelDto dtoResultado = service.update(dto.getId(), dto, PerfilnivelDto.class);				
		modelo.addAttribute("modelo", dtoResultado);
		
		mostrarMensajes(modelo, "S", "U");
		cargarListas(modelo);
		return dominio + "/PerfilNivel";
	}
	
	private void cargarListas(Model modelo) {
		
	}

}
