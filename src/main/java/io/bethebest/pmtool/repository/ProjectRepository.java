package io.bethebest.pmtool.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.bethebest.pmtool.model.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long>{
	public Project findByProjectIdentifier(String projectIdentifier);
	
}
