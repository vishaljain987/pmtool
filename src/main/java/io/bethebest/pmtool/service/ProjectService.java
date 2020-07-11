package io.bethebest.pmtool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.bethebest.pmtool.model.Project;
import io.bethebest.pmtool.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	public Project saveOrUpdate(Project project){
		//Logic
		return projectRepository.save(project);
	}
}
