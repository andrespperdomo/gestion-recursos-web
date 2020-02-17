package com.claro.gestionrecursosweb.domain;

import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ApiService<Dto, IdDataType> implements ICrudService<Dto, IdDataType> {

	protected String apiservicename;
	
	@Autowired
	protected RestTemplate restTemplate;
	
	@Value("${custom.api.urlbase}")
	protected String apiurl;
	
	public void setapiservicename(String apiservicename) {
		this.apiservicename = apiservicename;
	}
	
	@Override
	public Dto save(Dto entity, Class<Dto> tipo) {		
		ResponseEntity<Dto> responseEntity = restTemplate.exchange(apiurl + "/" + apiservicename, HttpMethod.POST, new HttpEntity<Dto>(entity), new ParameterizedTypeReference<Dto>() {});
		Dto dtoRespuesta = new ObjectMapper().convertValue(responseEntity.getBody(), tipo);
		return dtoRespuesta;
	}

	@Override
	public Optional<Dto> findById(IdDataType id, Class<Dto> tipo) {
		ResponseEntity<Dto> responseEntity = restTemplate.exchange(apiurl + "/" + apiservicename + "/" + id, HttpMethod.GET, null, new ParameterizedTypeReference<Dto>() {});
		Dto dtoRespuesta = new ObjectMapper().convertValue(responseEntity.getBody(), tipo);

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
	public Iterable<Dto> findAll() {
		ResponseEntity<Iterable<Dto>> responseEntity = restTemplate.exchange(apiurl + "/" + apiservicename, HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<Dto>>() {});
		return responseEntity.getBody();
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
