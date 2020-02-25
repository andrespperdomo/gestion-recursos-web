<<<<<<< HEAD
package com.claro.gestionrecursosweb.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.claro.gestionrecursosweb.controller.BaseController;
import com.claro.gestionrecursosweb.dto.LineaProductoDto;


@Service
public class LineaProductoService {
	
	@Value("${claro.dominio.lineaproducto.nombre}")
	private String dominio;
	
	@Autowired
	private ApiService<LineaProductoDto, Integer> service;
	
	public void configurarService() {
		service.setapiservicename(dominio);
	}
	
	public String consultarLineaProducto(Model model) throws UsernameNotFoundException {
	    configurarService();
		
		Iterable<LineaProductoDto> dto = service.findAll(LineaProductoDto.class);	
		
		for(LineaProductoDto l:dto) {
			System.out.println("###### "+l.getNombre());
		}
		model.addAttribute("modelo", dto);
		return dominio + "/Filtro";
		
	}
	

}
=======
package com.claro.gestionrecursosweb.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.claro.gestionrecursosweb.controller.BaseController;
import com.claro.gestionrecursosweb.dto.LineaProductoDto;


@Service
public class LineaProductoService {
	
	@Value("${claro.dominio.lineaproducto.nombre}")
	private String dominio;
	
	@Autowired
	private ApiService<LineaProductoDto, Integer> service;
	
	public void configurarService() {
		service.setapiservicename(dominio);
	}
	
	public String consultarLineaProducto(Model model) throws UsernameNotFoundException {
	    configurarService();
		
		Iterable<LineaProductoDto> dto = service.findAll(LineaProductoDto.class);	
		
		for(LineaProductoDto l:dto) {
			System.out.println("###### "+l.getNombre());
		}
		model.addAttribute("modelo", dto);
		return dominio + "/Filtro";
		
	}
	

}
>>>>>>> 2a2d560f5ec48a25884b391a3a784b9e7f87d4f0
