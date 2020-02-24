package com.claro.gestionrecursosweb.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.claro.gestionrecursosweb.domain.ApiService;
import com.claro.gestionrecursosweb.dto.PerfilDto;

@Controller
@RequestMapping("/Perfil")
public class PerfilController extends BaseController {
	
	@Autowired
	private ApiService<PerfilDto, Integer> service;
		
	public void configurarService() {
		service.setapiservicename(dominio_perfil);
	}
	
	@GetMapping("/Filtro")
	public String filtro(Model model) {
		configurarService();
		
		Iterable<PerfilDto> dto = service.findAll(PerfilDto.class);
		
		model.addAttribute("modelo", dto);
		return dominio_perfil + "/Filtro";
	}
	
	@GetMapping("Crear")
	public String crear(Model modelo) {
		configurarService();
		modelo.addAttribute("cl_formaction", "Crear");
		
		modelo.addAttribute("modelo", new PerfilDto());
		cargarListas(modelo);
		return dominio_perfil + "/Perfil";
	}
	
	@PostMapping("/Crear")
	public String crear(PerfilDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
		configurarService();
		modelo.addAttribute("cl_formaction", "Crear");
		
		PerfilDto dtoResultado = service.insert(dto, PerfilDto.class);
		
		return redireccion("Editar", dtoResultado.getId().toString(), "S", "C", request);
	}
	
	@GetMapping("/Editar/{id}")
	public String editar(@PathVariable Integer id, Model modelo, @RequestParam(required = false) String cla) {
		configurarService();
		mostrarMensajes(modelo, cla);		
		modelo.addAttribute("cl_formaction", "Editar");
		
		Optional<PerfilDto> dtoResultado = service.findById(id, PerfilDto.class);
		if (dtoResultado == null)
			dtoResultado = Optional.of(new PerfilDto());
				
		modelo.addAttribute("modelo", dtoResultado.get());
		cargarListas(modelo);
		return dominio_perfil + "/Perfil";
	}
	
	@PostMapping("/Editar")
	public String editar(PerfilDto dto, BindingResult result, Model modelo) {
		configurarService();		
		modelo.addAttribute("cl_formaction", "Editar");
		
		PerfilDto dtoResultado = service.update(dto.getId(), dto, PerfilDto.class);				
		modelo.addAttribute("modelo", dtoResultado);
		
		mostrarMensajes(modelo, "S", "U");
		cargarListas(modelo);
		return dominio_perfil + "/Perfil";
	}
	
	private void cargarListas(Model modelo) {
		
	}

}
