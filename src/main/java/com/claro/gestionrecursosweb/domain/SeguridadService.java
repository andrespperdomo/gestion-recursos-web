package com.claro.gestionrecursosweb.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.claro.gestionrecursosweb.dto.UsuarioDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SeguridadService extends ApiService<UsuarioDto, Integer> implements UserDetailsService {

	@Value("${claro.dominio.seguridad.nombre}")
	private String dominio;
	
	/**
	 * El objeto usuario ya debe traer la contraseña crifrada
	 * @param dto
	 * @return Retorna el Id del usuario
	 */
	public Integer iniciarSesion(UsuarioDto dto) {
		ResponseEntity<Integer> responseEntity = restTemplate.exchange(apiurl + "/" + apiservicename, HttpMethod.POST, new HttpEntity<UsuarioDto>(dto), new ParameterizedTypeReference<Integer>() {});
		Integer respuesta = new ObjectMapper().convertValue(responseEntity.getBody(), Integer.class);
		return respuesta;
	}

	@Override
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
		apiservicename = dominio;
		ResponseEntity<UsuarioDto> responseEntity = restTemplate.exchange(apiurl + "/" + apiservicename + "?usuario=" + usuario, HttpMethod.GET, null, new ParameterizedTypeReference<UsuarioDto>() {});
		UsuarioDto respuesta = new ObjectMapper().convertValue(responseEntity.getBody(), UsuarioDto.class);
		
		List<GrantedAuthority> rolesDelUsuario = new ArrayList<GrantedAuthority>();
		GrantedAuthority usuarioRoles = new SimpleGrantedAuthority(UsuarioRolEnum.valueOf(respuesta.getCodusuariorol()).toString());
		rolesDelUsuario.add(usuarioRoles);
		
		UserDetails usuarioAutenticado = (UserDetails)new User(respuesta.getUsuario(), respuesta.getClave(), rolesDelUsuario);
		return usuarioAutenticado;
	}
	
}
