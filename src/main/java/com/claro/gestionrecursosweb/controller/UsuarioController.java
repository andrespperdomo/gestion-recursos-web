package com.claro.gestionrecursosweb.controller;

import java.util.Optional;

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
import com.claro.gestionrecursosweb.dto.PersonaDto;

@Controller
@RequestMapping("Usuario")
public class UsuarioController extends BaseController {

	@Autowired
	private ApiService<PersonaDto, Integer> service;
		
	public void ConfigurarService() {
		service.setapiservicename("persona");
	}
	
	@GetMapping("/Filtro")
	public String Filtro(Model model) {
		ConfigurarService();
		
		Iterable<PersonaDto> dto = service.findAll();
		model.addAttribute("modelo", dto);
		return "usuario/Filtro";
	}
	
	@GetMapping("Crear")
	public String Crear(Model modelo) {
		ConfigurarService();
		modelo.addAttribute("cl_formaction", "Crear");
		
		modelo.addAttribute("modelo", new PersonaDto());
		return "usuario/Usuario";
	}
	
	@PostMapping("/Crear")
	public String Crear(PersonaDto dto, BindingResult result, Model modelo) {
		ConfigurarService();
		modelo.addAttribute("cl_formaction", "Crear");
		
		PersonaDto dtoResultado = service.save(dto, PersonaDto.class);
		
		return redireccion("Editar", dtoResultado.getId().toString(), "S", "C");
	}
	
	@GetMapping("/Editar/{id}")
	public String Editar(@PathVariable Integer id, Model modelo, @RequestParam(required = false) String cla) {
		ConfigurarService();
		mostrarMensajes(modelo, cla);		
		modelo.addAttribute("cl_formaction", "Editar");
		
		Optional<PersonaDto> dtoResultado = service.findById(id, PersonaDto.class);
		if (dtoResultado == null)
			dtoResultado = Optional.of(new PersonaDto());
				
		modelo.addAttribute("modelo", dtoResultado.get());
		return "usuario/Usuario";
	}
	
	@PostMapping("/Editar")
	public String Editar(PersonaDto dto, BindingResult result, Model modelo) {
		ConfigurarService();
		modelo.addAttribute("cl_formaction", "Editar");
		
		PersonaDto dtoResultado = service.save(dto, PersonaDto.class);
		
		mostrarMensajes(modelo, "S", "U");
		
		modelo.addAttribute("modelo", dtoResultado);
		return "usuario/Usuario";
	}
	
}
