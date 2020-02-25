package com.claro.gestionrecursosweb.controller;

import java.util.Date;
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
import com.claro.gestionrecursosweb.dto.LineaProductoDto;
import com.claro.gestionrecursosweb.dto.PersonaDto;
import com.claro.gestionrecursosweb.dto.ProveedorDto;

@Controller
@RequestMapping("/LineaProducto")
public class LineaProductoController extends BaseController {
	
	@Value("${claro.dominio.lineaproducto.nombre}")
	private String dominio;
	
	@Autowired
	private ApiService<LineaProductoDto, Integer> service;

	public void configurarService() {
		service.setapiservicename(dominio);
	}
	
	@GetMapping("/filtro")
	public String filtro(Model model) {
		configurarService();
		Iterable<LineaProductoDto> dto = service.findAll(LineaProductoDto.class);
		model.addAttribute("modelo", dto);		
		System.out.println(model.toString());
		
		return  dominio + "/LineaProducto";
	}
	
	@GetMapping("Crear")
	public String crear(Model modelo) {
		configurarService();
		modelo.addAttribute("cl_formaction", "Crear");
		modelo.addAttribute("modelo", new LineaProductoDto());
		return dominio + "/CrearLineaProducto";
	}
	
	@PostMapping("/Crear")
	public String crear(LineaProductoDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
		configurarService();
		modelo.addAttribute("cl_formaction", "Crear");
		dto.setFechacreacion(new Date());
		dto.setFechamodificacion(new Date());
		LineaProductoDto dtoResultado = service.insert(dto, LineaProductoDto.class);
		return redireccion("Editar", dtoResultado.getId().toString(), "S", "C", request);
	}
	
	@GetMapping("/Editar/{id}")
	public String editar(@PathVariable Integer id, Model modelo, @RequestParam(required = false) String cla) {
		configurarService();
		mostrarMensajes(modelo, cla);		
		modelo.addAttribute("cl_formaction", "Editar");
		
		Optional<LineaProductoDto> dtoResultado = service.findById(id, LineaProductoDto.class);
		System.out.println("########### valores "+dtoResultado.toString());
		if (dtoResultado == null)
			dtoResultado = Optional.of(new LineaProductoDto());
				
		modelo.addAttribute("modelo", dtoResultado.get());
		return dominio + "/CrearLineaProducto";
	}
	
	@PostMapping("/Editar")
	public String editar(LineaProductoDto dto, BindingResult result, Model modelo) {
		configurarService();		
		modelo.addAttribute("cl_formaction", "Editar");
		dto.setFechamodificacion(new Date());
		LineaProductoDto dtoResultado = service.update(dto.getId(), dto, LineaProductoDto.class);				
		modelo.addAttribute("modelo", dtoResultado);
		
		mostrarMensajes(modelo, "S", "U");
		return dominio + "/CrearLineaProducto";
	}
	
		
}
