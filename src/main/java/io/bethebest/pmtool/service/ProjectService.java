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
		try{
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		}catch(Exception e){
			throw new ProjectIdException("Project Id '"+project.getProjectIdentifier()+"' already exists");
		}
		
	}
	
	public Project findByProjectIdentifier(String projectIdentifier){
		Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
		
		if(project == null){
			throw new ProjectIdException("Project does not exist");
		}
		return project;
	}
	
	public Iterable<Project> findAllProjects(){
		return projectRepository.findAll();
	}
	
	public void deleteByProjectIdentifier(String projectIdentifier){
		Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
		
		if(project == null){
			throw new ProjectIdException("Project does not exist");
		}
		projectRepository.deleteById(project.getId());
	}
}
