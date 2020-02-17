package com.claro.gestionrecursosweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController extends BaseController {

	public void ConfigurarService() {

	}

	@GetMapping("/")
	public String inicio(Model model) {
		ConfigurarService();

		return "home/Inicio";
	}

	@GetMapping("error")
	public String error(Model model) {
		ConfigurarService();

		return "home/Error";
	}

}
