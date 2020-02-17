package com.claro.gestionrecursosweb.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.claro.gestionrecursosweb.domain.SeguridadService;
import com.claro.gestionrecursosweb.dto.UsuarioDto;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
 
	@Autowired
	private SeguridadService seguridadService;
	
    @Override
    public Authentication authenticate(Authentication authentication) 
      throws AuthenticationException {
    	seguridadService.setapiservicename("Seguridad");
  
        String usuarionombre = authentication.getName();
        String password = authentication.getCredentials().toString();
         
        UsuarioDto usuario = new UsuarioDto();
        usuario.setUsuario(usuarionombre);
        usuario.setClave(password);        
        
        if (seguridadService.iniciarSesion(usuario) > 0) {
        	UserDetails usuarioDetalles = null;
        	try {
        		usuarioDetalles = seguridadService.loadUserByUsername(usuarionombre);
        	} catch (UsernameNotFoundException e) {
        		
        	}
        	System.out.println("*************************************************** Usuario autenticado: " + "Usuario: " + usuario.getNombre() + "; clave: " + usuario.getClave());
            return new UsernamePasswordAuthenticationToken(usuarioDetalles.getUsername(), usuarioDetalles.getPassword(), usuarioDetalles.getAuthorities());
        } else {
            return null;
        }
    }
 
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
