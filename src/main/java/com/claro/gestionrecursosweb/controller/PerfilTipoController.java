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
import com.claro.gestionrecursosweb.dto.PerfiltipoDto;

@Controller
@RequestMapping("/PerfilTipo")
public class PerfilTipoController extends BaseController {
	
	@Value("${claro.dominio.perfiltipo.nombre}")
	private String dominio;
	
	@Autowired
	private ApiService<PerfiltipoDto, Integer> service;
		
	public void configurarService() {
		service.setapiservicename(dominio);
	}
	
	@GetMapping("/Filtro")
	public String filtro(Model model) {
		configurarService();
		
		Iterable<PerfiltipoDto> dto = service.findAll(PerfiltipoDto.class);
		
		model.addAttribute("modelo", dto);
		return dominio + "/Filtro";
	}
	
	@GetMapping("Crear")
	public String crear(Model modelo) {
		configurarService();
		modelo.addAttribute("cl_formaction", "Crear");
		
		modelo.addAttribute("modelo", new PerfiltipoDto());
		cargarListas(modelo);
		return dominio + "/PerfilTipo";
	}
	
	@PostMapping("/Crear")
	public String crear(PerfiltipoDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
		configurarService();
		modelo.addAttribute("cl_formaction", "Crear");
		
		PerfiltipoDto dtoResultado = service.insert(dto, PerfiltipoDto.class);
		
		return redireccion("Editar", dtoResultado.getId().toString(), "S", "C", request);
	}
	
	@GetMapping("/Editar/{id}")
	public String editar(@PathVariable Integer id, Model modelo, @RequestParam(required = false) String cla) {
		configurarService();
		mostrarMensajes(modelo, cla);		
		modelo.addAttribute("cl_formaction", "Editar");
		
		Optional<PerfiltipoDto> dtoResultado = service.findById(id, PerfiltipoDto.class);
		if (dtoResultado == null)
			dtoResultado = Optional.of(new PerfiltipoDto());
				
		modelo.addAttribute("modelo", dtoResultado.get());
		cargarListas(modelo);
		return dominio + "/PerfilTipo";
	}
	
	@PostMapping("/Editar")
	public String editar(PerfiltipoDto dto, BindingResult result, Model modelo) {
		configurarService();		
		modelo.addAttribute("cl_formaction", "Editar");
		
		PerfiltipoDto dtoResultado = service.update(dto.getId(), dto, PerfiltipoDto.class);				
		modelo.addAttribute("modelo", dtoResultado);
		
		mostrarMensajes(modelo, "S", "U");
		cargarListas(modelo);
		return dominio + "/PerfilTipo";
	}
	
	private void cargarListas(Model modelo) {
		
	}

}
