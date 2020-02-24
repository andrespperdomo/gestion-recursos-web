package com.claro.gestionrecursosweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.claro.gestionrecursosweb.domain.ApiService;

/**
 * Controlador para solicitar información directamente desde Ajax al API
 * @author German Jesus Rojas
 */
@RestController
@RequestMapping("/api")
public class ApiController extends BaseController {

	@Autowired
	private ApiService<Object, Integer> service;
	
	@PostMapping("/{dominio}")
	public ResponseEntity<?> POST(@PathVariable String dominio) {
		service.setapiservicename(dominio);
		
		Object respuesta = service.findAll(Object.class);
		return new ResponseEntity<Object>(respuesta, HttpStatus.OK);
	}
	
	@PostMapping("/{dominio}/{id}")
	public ResponseEntity<?> POST(@PathVariable String dominio, @PathVariable Integer id) {
		service.setapiservicename(dominio);
		
		Object respuesta = service.findById(id, Object.class);
		return new ResponseEntity<Object>(respuesta, HttpStatus.OK);
	}
	
}
