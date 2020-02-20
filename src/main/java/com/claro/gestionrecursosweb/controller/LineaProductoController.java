package com.claro.gestionrecursosweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.claro.gestionrecursosweb.domain.ApiService;
import com.claro.gestionrecursosweb.dto.LineaProductoDto;
import com.claro.gestionrecursosweb.dto.PersonaDto;

@Controller
@RequestMapping("/LineaProducto")
public class LineaProductoController extends BaseController {
	
	@Value("${claro.dominio.lineaproducto.nombre}")
	private String dominio;
	
	@Autowired
	private ApiService<LineaProductoDto, Integer> service;

	public void ConfigurarService() {
		service.setapiservicename(dominio);
	}
	
	@GetMapping("/filtro")
	public String filtro(Model model) {
		ConfigurarService();
		System.out.println("===============entro a filtro linea producto");
		Iterable<LineaProductoDto> dto = service.findAll(LineaProductoDto.class);
		for(LineaProductoDto l:dto) {
			System.out.println("###### "+l.getNombre());
		}
		
		model.addAttribute("modelo", dto);
		
		System.out.println(model.toString());
		
		return  dominio + "/LineaProducto";
	}
		
}
