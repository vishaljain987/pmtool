package io.bethebest.pmtool.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.bethebest.pmtool.model.Backlog;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog, Long>{
	
	public Backlog findByProjectIdentifier(String projectIdentifier);
	
}
