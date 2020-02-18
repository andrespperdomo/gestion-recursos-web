package com.claro.gestionrecursosweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.claro.gestionrecursosweb.dto.UsuarioDto;

@Controller
@RequestMapping("/Seguridad")
public class SeguridadController extends BaseController {

	public void ConfigurarService() {
		
	}
	
	@GetMapping("/Ingresar")
	public String ingresar(Model model) {
		ConfigurarService();
		
		model.addAttribute("modelo", new UsuarioDto());
		
		return "seguridad/Ingresar";
	}
			
}
