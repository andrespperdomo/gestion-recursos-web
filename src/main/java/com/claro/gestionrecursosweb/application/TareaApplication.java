package com.claro.gestionrecursosweb.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.claro.gestionrecursosweb.domain.ApiService;
import com.claro.gestionrecursosweb.dto.TareaDto;

@Service
public class TareaApplication {

	@Value("{claro.dominio.tarea.nombre}")
	private String dominio;
	
	@Autowired
	private ApiService<TareaDto, Integer> service;
	
	private void configurarService( ) {
		service.setapiservicename(dominio);
	}
	
	public TareaDto insertar(TareaDto dto, Integer codpersona, Integer codproyecto) {
		configurarService();

		dto = service.insert(dto, TareaDto.class);
		
		
		return dto;
	}
	
}
