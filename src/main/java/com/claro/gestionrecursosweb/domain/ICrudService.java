package com.claro.gestionrecursosweb.domain;

import java.util.Optional;

public interface ICrudService<Entity, IdDataType> {	
		
	public Entity save(Entity entity, Class<Entity> tipo);
	
	public Optional<Entity> findById(IdDataType id, Class<Entity> tipo);

	public boolean existsById(IdDataType id);

	public Iterable<Entity> findAll();

	public long count();

	public void deleteById(IdDataType id);

	public void delete(Entity entity);

	public void deleteAll(Iterable<Entity> entities);

}
