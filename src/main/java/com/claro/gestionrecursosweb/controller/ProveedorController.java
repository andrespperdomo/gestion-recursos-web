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
import com.claro.gestionrecursosweb.dto.ProveedorDto;

@Controller
@RequestMapping("/Proveedor")
public class ProveedorController extends BaseController {
		
	@Autowired
	private ApiService<ProveedorDto, Integer> service;
		
	public void configurarService() {
		service.setapiservicename(dominio_proveedor);
	}
	
	@GetMapping("/Filtro")
	public String filtro(Model model) {
		configurarService();
		
		Iterable<ProveedorDto> dto = service.findAll(ProveedorDto.class);
		
		model.addAttribute("modelo", dto);
		return dominio_proveedor + "/Filtro";
	}
	
	@GetMapping("Crear")
	public String crear(Model modelo) {
		configurarService();
		modelo.addAttribute("cl_formaction", "Crear");
		
		modelo.addAttribute("modelo", new ProveedorDto());
		cargarListas(modelo);
		return dominio_proveedor + "/Proveedor";
	}
	
	@PostMapping("/Crear")
	public String crear(ProveedorDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
		configurarService();
		modelo.addAttribute("cl_formaction", "Crear");
		
		ProveedorDto dtoResultado = service.insert(dto, ProveedorDto.class);
		
		return redireccion("Editar", dtoResultado.getId().toString(), "S", "C", request);
	}
	
	@GetMapping("/Editar/{id}")
	public String editar(@PathVariable Integer id, Model modelo, @RequestParam(required = false) String cla) {
		configurarService();
		mostrarMensajes(modelo, cla);		
		modelo.addAttribute("cl_formaction", "Editar");
		
		Optional<ProveedorDto> dtoResultado = service.findById(id, ProveedorDto.class);
		if (dtoResultado == null)
			dtoResultado = Optional.of(new ProveedorDto());
				
		modelo.addAttribute("modelo", dtoResultado.get());
		cargarListas(modelo);
		return dominio_proveedor + "/Proveedor";
	}
	
	@PostMapping("/Editar")
	public String editar(ProveedorDto dto, BindingResult result, Model modelo) {
		configurarService();		
		modelo.addAttribute("cl_formaction", "Editar");
		
		ProveedorDto dtoResultado = service.update(dto.getId(), dto, ProveedorDto.class);				
		modelo.addAttribute("modelo", dtoResultado);
		
		mostrarMensajes(modelo, "S", "U");
		cargarListas(modelo);
		return dominio_proveedor + "/Proveedor";
	}
	
	private void cargarListas(Model modelo) {
		
	}

}
