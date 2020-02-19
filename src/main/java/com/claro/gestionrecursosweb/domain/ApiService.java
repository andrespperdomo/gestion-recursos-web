package com.claro.gestionrecursosweb.domain;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.claro.gestionrecursosweb.dto.ProyectoDto;
import com.claro.gestionrecursosweb.model.RespuestaCustomizada;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ApiService<Dto, IdDataType> implements ICrudService<Dto, IdDataType> {

	protected String apiservicename;
	
	@Autowired
	protected RestTemplate restTemplate;
	
	@Value("${claro.api.urlbase}")
	protected String apiurl;
	
	public void setapiservicename(String apiservicename) {
		this.apiservicename = apiservicename;
	}
	
	@Override
	public Dto insert(Dto entity, Class<Dto> tipo) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		ResponseEntity<RespuestaCustomizada<Dto>> responseEntity = restTemplate.exchange(apiurl + "/" + apiservicename, HttpMethod.POST, new HttpEntity<Dto>(entity), new ParameterizedTypeReference<RespuestaCustomizada<Dto>>() {});
		RespuestaCustomizada<Dto> dtoRespuesta = mapper.convertValue(responseEntity.getBody(), RespuestaCustomizada.class);
		Dto dtoRespuesta2 = mapper.convertValue(dtoRespuesta.getData(), tipo);
		return dtoRespuesta2;
	}
	
	@Override
	public Dto update(IdDataType id, Dto entity, Class<Dto> tipo) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		ResponseEntity<RespuestaCustomizada<Dto>> responseEntity = restTemplate.exchange(apiurl + "/" + apiservicename + "/" + id, HttpMethod.PUT, new HttpEntity<Dto>(entity), new ParameterizedTypeReference<RespuestaCustomizada<Dto>>() {});
		RespuestaCustomizada<Dto> dtoRespuesta = mapper.convertValue(responseEntity.getBody(), RespuestaCustomizada.class);
		Dto dtoRespuesta2 = mapper.convertValue(dtoRespuesta.getData(), tipo);
		return dtoRespuesta2;
	}

	@Override
	public Optional<Dto> findById(IdDataType id, Class<Dto> tipo) {
		ResponseEntity<RespuestaCustomizada<Dto>> responseEntity = restTemplate.exchange(apiurl + "/" + apiservicename + "/" + id, HttpMethod.GET, null, new ParameterizedTypeReference<RespuestaCustomizada<Dto>>() {});
		ObjectMapper mapper = new ObjectMapper();
		Dto dtoRespuesta = mapper.convertValue(responseEntity.getBody().getData(), tipo);
		if (dtoRespuesta == null)
			return null;
		else
			return Optional.of(dtoRespuesta);
	}

	@Override
	public boolean existsById(IdDataType id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Dto> findAll(Class<Dto> tipo) {
		try {
			ResponseEntity<RespuestaCustomizada<Iterable<Dto>>> responseEntity = restTemplate.exchange(apiurl + "/" + apiservicename, HttpMethod.GET, null, new ParameterizedTypeReference<RespuestaCustomizada<Iterable<Dto>>>() {});
			ObjectMapper mapper = new ObjectMapper();
			Iterable<Dto> dtoRespuesta = mapper.convertValue(responseEntity.getBody().getData(), mapper.getTypeFactory().constructCollectionType(ArrayList.class, tipo));
			return dtoRespuesta;
		} catch(Exception e) {
			// Controlar errores correctamente, obtener errores!
			System.out.println("*****************************************************" + e.getMessage());
		}
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(IdDataType id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Dto entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<Dto> entities) {
		// TODO Auto-generated method stub
		
	}
	
}
