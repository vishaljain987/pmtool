package io.bethebest.pmtool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.bethebest.pmtool.exception.ProjectIdException;
import io.bethebest.pmtool.model.Project;
import io.bethebest.pmtool.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	public Project saveOrUpdate(Project project){
		//Logic
		project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
		try{
			return projectRepository.save(project);
		}catch(Exception e){
			throw new ProjectIdException("Project Id '"+project.getProjectIdentifier()+"' already exists");
		}
		
	}
}
