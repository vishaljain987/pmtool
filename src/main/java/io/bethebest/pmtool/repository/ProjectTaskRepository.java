package io.bethebest.pmtool.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.bethebest.pmtool.model.ProjectTask;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long>{
	public List<ProjectTask> findByProjectIdentifierOrderByPriority(String projectIdentifier);
}
