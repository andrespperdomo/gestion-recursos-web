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
import com.claro.gestionrecursosweb.dto.EmpleadoDto;

@Controller
public class EmpleadoController extends BaseController {


@Controller
@RequestMapping("/Empleado")
public class PresupuestoController extends BaseController {
	
	@Value("${claro.dominio.empleado.nombre}")
	private String dominio;
	
	@Autowired
	private ApiService<EmpleadoDto, Integer> service;
		
	public void configurarService() {
		service.setapiservicename(dominio);
	}
	
	@GetMapping("/Filtro")
	public String filtro(Model model) {
		configurarService();
		
		Iterable<EmpleadoDto> dto = service.findAll(EmpleadoDto.class);
		
		model.addAttribute("modelo", dto);
		return dominio + "/Filtro";
	}
	
	@GetMapping("Crear")
	public String crear(Model modelo) {
		configurarService();
		modelo.addAttribute("cl_formaction", "Crear");
		
		modelo.addAttribute("modelo", new EmpleadoDto());
		cargarListas(modelo);
		return dominio + "/Empleado";
	}
	
	@PostMapping("/Crear")
	public String crear(EmpleadoDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
		configurarService();
		modelo.addAttribute("cl_formaction", "Crear");
		
		EmpleadoDto dtoResultado = service.insert(dto, EmpleadoDto.class);
		
		return redireccion("Editar", dtoResultado.getId().toString(), "S", "C", request);
	}
	
	@GetMapping("/Editar/{id}")
	public String editar(@PathVariable Integer id, Model modelo, @RequestParam(required = false) String cla) {
		configurarService();
		mostrarMensajes(modelo, cla);		
		modelo.addAttribute("cl_formaction", "Editar");
		
		Optional<EmpleadoDto> dtoResultado = service.findById(id, EmpleadoDto.class);
		if (dtoResultado == null)
			dtoResultado = Optional.of(new EmpleadoDto());
				
		modelo.addAttribute("modelo", dtoResultado.get());
		cargarListas(modelo);
		return dominio + "/Empleado";
	}
	
	@PostMapping("/Editar")
	public String editar(EmpleadoDto dto, BindingResult result, Model modelo) {
		configurarService();		
		modelo.addAttribute("cl_formaction", "Editar");
		
		EmpleadoDto dtoResultado = service.update(dto.getId(), dto, EmpleadoDto.class);				
		modelo.addAttribute("modelo", dtoResultado);
		
		mostrarMensajes(modelo, "S", "U");
		cargarListas(modelo);
		return dominio + "/Empleado";
	}
	
	private void cargarListas(Model modelo) {
		
	}

}
	
}
