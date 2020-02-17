package com.claro.gestionrecursosweb.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.claro.gestionrecursosweb.domain.SeguridadService;
import com.claro.gestionrecursosweb.dto.UsuarioDto;

@Service
public class SeguridadApplication {
	
	@Autowired
	private SeguridadService seguridadService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void configurarServices() {
		seguridadService.setapiservicename("seguridad");
	}
	
	public Integer iniciarSesion(UsuarioDto dto) {
		configurarServices();
		
		dto.setClave(passwordEncoder.encode(dto.getClave()));
		return seguridadService.iniciarSesion(dto);
	}
	
}
